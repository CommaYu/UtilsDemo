package com.example.contentprovider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.HashMap;

/**
 * 告诉你一个好消息：一般我们都是用Android内置的ContentProvider，而不会自己实现ContentProvider
 ** TMD，一个晚上，都白看了。MarsChen说了如果你看不懂ContentProvider里面的方法实现，那是Java基础太烂
 *
 * Created by Administrator on 2016/11/28.
 * 继承这个就是为了，让别人访问我们的ContentProvider的时候，一定要先符合我们定下的规则才可以访问
 */

public class FirstContentProvider extends ContentProvider {
    String TAG = getClass().getSimpleName();
    DatabaseHelper dh;
    /* UriMather是一个工具类，作用：如果当别的应用程序来访问我们的ContentProvider的时候，
     这个就是检查对方使用的URI是否符合我们设定的标准的。符合：允许使用 不符合：拒绝使用
     ******判断原理：把URI和一个数字编号相关联，如果别的APP访问我们时给的Uri符合我们的规则，则返回相应数字编号比如“1”“2”
     */
    // 下面这个写法，有点参照UriMatcher在Google中官方文档的写法（UriMatcher中的#井号，代表某个不确定的位置id）
    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    public static final int INCOMING_USER_COLLECTION = 1;//传入uri是访问整个表时返回1
    public static final int INCOMING_USER_SINGLE = 2;//传入uri是访问表中单个数据时返回2

    // 定义Uri匹配规则，如果匹配前一个（访问整个表的URI）就返回1，下一个（访问表中某个数据的URI）就返回2
    static {
        //URI nodes may be exact match string, the token "*" that matches any text, or the token "#" that matches only numbers.
        //URI节点可以是完全匹配的字符串，标记"*"代表文本，标记"#"代表数字。
        //解读，当传入一个Uri的时候，如果匹配（FirstProviderMetaData.AUTHORITY, "/users",）或者（FirstProviderMetaData.AUTHORITY, "/users/#",）之间任何一个，则返回相应的数字
        uriMatcher.addURI(FirstProviderMetaData.AUTHORITY, "/users", INCOMING_USER_COLLECTION);//检查uri是否匹配访问全部表的uri
        uriMatcher.addURI(FirstProviderMetaData.AUTHORITY, "/users/#", INCOMING_USER_SINGLE);//检查uri是否匹配访问表中某个数据的uri
    }

    // 规定起别名，别问为什么，这是规定。MarsChen也觉得没必要
    public static HashMap<String, String> userProjectMap = new HashMap<>();
    static {
        userProjectMap.put(FirstProviderMetaData.UserTableMetaData._ID, FirstProviderMetaData.UserTableMetaData._ID);
        userProjectMap.put(FirstProviderMetaData.UserTableMetaData.USER_NAME, FirstProviderMetaData.UserTableMetaData.USER_NAME);
    }

    // 在ContentProvider创建的时候就执行了
    @Override
    public boolean onCreate() {
        Log.d(TAG, "onCreate: ");
        dh = new DatabaseHelper(getContext(), FirstProviderMetaData.DATABASE_NAME);
        return false;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        // 创建查询语句
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        switch (uriMatcher.match(uri)) {
            case INCOMING_USER_COLLECTION:
                qb.setTables(FirstProviderMetaData.UserTableMetaData.TABLE_NAME);
                qb.setProjectionMap(userProjectMap);
                break;
            case INCOMING_USER_SINGLE:
                qb.setTables(FirstProviderMetaData.UserTableMetaData.TABLE_NAME);
                qb.setProjectionMap(userProjectMap);
                qb.appendWhere(FirstProviderMetaData.UserTableMetaData._ID + "=" + uri.getPathSegments().get(1));
                //uri.getPathSegments().get(1))，比如当uri =content：//con.example.contentprovider.FirstContentProvider/users/1的时候
                //getPathSegments()=/users/1
                //getPathSegments().get(1) = 1
                break;
            // TODO: 2016/11/28 看视频太麻烦了，没写完，而且一般不用我们写这个，java功底也太差，看不懂 
            // FIXME: 2016/11/28 以后不用实现ContentProvider，只要用Android内置的就好了
        }
        return null;
    }

    /**
     * 根据传入的URI，返回该URI所表示的数据类型,整个表是1，表中单个数据是2
     *
     * @param uri
     * @return
     */
    @Nullable
    @Override
    public String getType(Uri uri) {
        Log.d(TAG, "getType: ");
        switch (uriMatcher.match(uri)) {
            case INCOMING_USER_COLLECTION:
                return FirstProviderMetaData.UserTableMetaData.CONTENT_TYPE;
            case INCOMING_USER_SINGLE:
                return FirstProviderMetaData.UserTableMetaData.CONTENT_TYPE_ITEM;
            default:
                throw new IllegalArgumentException("Unknown URI" + uri);//如果传入uri都不匹配就抛出异常
        }
    }

    /**
     * 该函数的返回值是一个Uri，这个Uri表示的是刚刚使用这个函数所插入的数据
     * content：//con.example.contentprovider.FirstContentProvider/users/1
     * @param uri
     * @param contentValues
     * @return
     */
    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        Log.d(TAG, "insert: ");
        SQLiteDatabase db = dh.getWritableDatabase();
        long rowId = db.insert(FirstProviderMetaData.UserTableMetaData.TABLE_NAME, null, contentValues);
        // 为什么要>0，因为“the row ID of the newly inserted row, or -1 if an error occurred”，这是上一个insert的返回值，= -1有问题
        if (rowId > 0) {
            Uri insertUserUri = ContentUris.withAppendedId(FirstProviderMetaData.UserTableMetaData.CONTENT_URI, rowId);
            // 通知监听器，数据已经改变(默认通知CursorAdapter得到这个信息）
            getContext().getContentResolver().notifyChange(insertUserUri, null);
            return insertUserUri;
        }
        throw new SQLException("Failed to insert row into" + uri);
    }

    @Override
    public int delete(Uri uri, String s, String[] strings) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {
        return 0;
    }
}
