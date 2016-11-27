package com.example.contentprovider;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Administrator on 2016/11/27.
 * 内容提供者，中继数据，存放一些常量
 */

public class FirstProviderMetaData {
    public static final String AUTHORITY = "com.example.contentprovider";
    // 数据库名称
    public static final String DATABASE_NAME = "FisrtProvider.db";
    // 数据库的版本
    public static final int DATABASE_VERSION = 1;
    // 数据库表名
    public static final String USERS_TABLE_NAME = "users";

    // 实现BaseColums的作用：定义了"_id"和"_count"，规定ContentProvider必须有这两个。实现了BaseColumns后，就不需要定义刚才那连个了
    public static final class UserTableMetaData implements BaseColumns {
        // 表名
        public static final String TABLE_NAME = "users";
        // 访问该ContentProvider的URI
        public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/users");
        // 下面这个是MarChen参考官方文档写出来的
        // file:///C:/Users/Administrator/AppData/Local/Android/sdk/docs/reference/android/provider/ContactsContract.Contacts.html#CONTENT_TYPE
        // 该ContentProvider所返回的数据类型的定义
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.firstprovider.user";//访问整张表数据，返回给别人app的数据类型
        public static final String CONTENT_TYPE_ITEM = "vnd.android.cursor.item/vnd.firstprovider.user";//访问某一条数据，返回给别人app的数据类型
        // 列名，要在“users”表中存放数据的列名
        public static final String USER_NAME = "name";
        // 默认的排序方法，查询数据库的时候要进行排序
        public static final String DEFAULT_SORT_ORDER = "_id desc";
    }
}
