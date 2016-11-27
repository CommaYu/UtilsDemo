package com.example.contentprovider;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

// TODO 这个项目太麻烦，没做完

/**
 * 告诉你一个好消息：一般我们都是用Android内置的ContentProvider，而不会自己实现ContentProvider
 * TMD，一个晚上，都白看了。MarsChen说了如果你看不懂ContentProvider里面的方法实现，那是Java基础太烂
 */

public class MainActivity extends AppCompatActivity {
    String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_insert).setOnClickListener(new InsertListener());
        findViewById(R.id.btn_query).setOnClickListener(new QueryListener());
    }

    private class InsertListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            ContentValues values = new ContentValues();
            values.put(FirstProviderMetaData.UserTableMetaData.USER_NAME, "zhangsan");
            Uri uri = getContentResolver().insert(FirstProviderMetaData.UserTableMetaData.CONTENT_URI, values);
            Log.d(TAG, "onClick_insert: uri=" + uri.toString());
        }
    }

    private class QueryListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Cursor c = getContentResolver().query(FirstProviderMetaData.UserTableMetaData.CONTENT_URI, null, null, null, null);
            while (c.moveToNext()) {
                Log.d(TAG, "onClick_query: String=" + c.getString(c.getColumnIndex(FirstProviderMetaData.UserTableMetaData.USER_NAME)));
            }
        }
    }
}
