package com.example.fastjson;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    String TAG = getClass().getSimpleName();
    // JSON单个对象
    private String jsonData = "{\"id\":1,\"name\":\"zhangsan\"}";
    // JSON数组
    private String jsonArray = "[{\"id\":1,\"name\":\"ZhangSan\"},{\"id\":2,\"name\":\"WangWU\"}]";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // JSON序列化（JSON=>Object）

        User guestUser = new User(1, "ZhangSan");
        User rootUser = new User(2, "WangWU");

        List<User> list = new ArrayList<>();
        list.add(guestUser);
        list.add(rootUser);

        Group group = new Group(0, "admin", list);

        String jsonString = JSON.toJSONString(group);
        Log.d(TAG, "onCreate: " + jsonString);
        System.out.println(jsonString);

        // JSON反序列化（Object=>JSON）
        //JSON解析单个对象
        User singleUser = JSON.parseObject(jsonData, User.class);
        Log.d(TAG, "onCreate: " + "singleId=" + singleUser.getId() + " singleName=" + singleUser.getName());
        // JSON解析数组
        List<User> list2 = new ArrayList<User>(JSONArray.parseArray(jsonArray, User.class));
        User user1 = list2.get(0);
        Log.d(TAG, "onCreate: " + "id=" + user1.getId() + " name=" + user1.getName());
    }
}
