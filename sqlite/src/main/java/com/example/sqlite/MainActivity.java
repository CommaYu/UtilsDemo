package com.example.sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button).setOnClickListener(new CreateDatabaseListener());
        findViewById(R.id.button2).setOnClickListener(new UpgradeDatabaseListener());
        findViewById(R.id.button3).setOnClickListener(new InsertListener());
        findViewById(R.id.button4).setOnClickListener(new UpdateListener());
        findViewById(R.id.button5).setOnClickListener(new QueryListener());

    }

    private class CreateDatabaseListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            DatabaseHelper dbHelper = new DatabaseHelper(MainActivity.this, "test_mars_db");
            // 只有调用getReadableDatabase才会执行onCreate方法执行db.execSQL方法创建数据库
            SQLiteDatabase db = dbHelper.getReadableDatabase();
        }
    }

    class UpgradeDatabaseListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            // 调用3参数构造函数，执行了DatabaseHelper的onUpgrade方法，跟新了数据库的版本号，可以在onUpgrade写任何想写的内容
            DatabaseHelper dbHelper = new DatabaseHelper(MainActivity.this, "test_mars_db", 2);
            SQLiteDatabase db = dbHelper.getReadableDatabase();
        }
    }

    class InsertListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            //生成ContentValues对象
            ContentValues values = new ContentValues();
            //想该对象当中插入键值对，其中键是列名，值是希望插入到这一列的值，值必须和数据库当中的数据类型一致
            values.put("id", 1);
            values.put("name", "zhangsan");
            DatabaseHelper dbHelper = new DatabaseHelper(MainActivity.this, "test_mars_db");
            // 向上转型（原来我看不懂这些，都是因为我的java不好，日了狗了）
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            db.insert("user", null, values);
        }
    }

    // 更新操作就相当于执行SQL语句中的update语句
    // UPDATE table_name SET XXCOL-XXX WHERE XXCOL=XX...
    class UpdateListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            // 得到一个可以写得SQLiteDatabase对象
            DatabaseHelper dbHelper = new DatabaseHelper(MainActivity.this, "test_mars_db");
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("name", "Lisi");
            //第1个参数：要跟新的表名
            //第2个参数：一个ContentValues对象
            //第3个参数：问号？是占位符，有几个占位符，后面的new String就有几个值。把id=1的"name"由"zhangsan"修改成"Lisi"
            db.update("user", values, "id=?", new String[]{"1"});
        }
    }

    class QueryListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            DatabaseHelper dbHelper = new DatabaseHelper(MainActivity.this, "test_mars_db");
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            Cursor cursor = db.query("user", new String[]{"id", "name"}, "id=?", new String[]{"1"}, null, null, null);
            while (cursor.moveToNext()) {
                String name = cursor.getString(cursor.getColumnIndex("name"));
                System.out.println("query--->" + name);
            }
        }
    }


}
