package com.example.animation;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2016/11/26.
 * 主要方法：LayoutAnimationController（为一些ViewGroup添加动画比如LinearLayout和ListView）
 * LinearLayout和ListView都是ViewGroup的直接或间接子类，视图组。
 * 学会：
 * 1.java中为ViewGroup加载动画的方法
 * 2.xml中为ViewGroup加载动画的方法android:layoutAnimation="@anim/list_anim_layout"
 */

public class SecondActivity extends Activity {
    private ListView lv;
    private Button btn_test, btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);

        lv = (ListView) findViewById(R.id.lv);
        findViewById(R.id.btn_test).setOnClickListener(new ButtonListener());
        findViewById(R.id.btn_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    /**
     * 存放数据到ListView
     */
    private ListAdapter buildListAdapter() {
        List<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();

        HashMap<String, String> hm = new HashMap<>();
        hm.put("user_name", "zhangsan");
        hm.put("user_gender", "male");

        HashMap<String, String> hm1 = new HashMap<>();
        hm1.put("user_name", "Lisi");
        hm1.put("user_gender", "female");

        HashMap<String, String> hm2 = new HashMap<>();
        hm2.put("user_name", "wangwu");
        hm2.put("user_gender", "male");

        list.add(hm);
        list.add(hm1);
        list.add(hm2);

        SimpleAdapter simpleAdapter = new SimpleAdapter(this,list,R.layout.item,new String[]
                {"user_name", "user_gender"},new int[]{R.id.user_name,R.id.user_gender});
        return simpleAdapter;
    }

    /**为ListView中的item添加动画显示效果*/
    private class ButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            lv.setAdapter(buildListAdapter());
            //android:layoutAnimation="@anim/list_anim_layout"可以直接在xml中ListView控件设置这个就有布局动画了
            //我把LinearLayout里面设置了这个，我进入的时候，button也是这么个显示效果，很好玩

            // java代码方式设置布局动画文件
            Animation animation = AnimationUtils.loadAnimation(SecondActivity.this, R.anim.list_anim);
            LayoutAnimationController lac = new LayoutAnimationController(animation);
            lac.setOrder(LayoutAnimationController.ORDER_REVERSE);
            lac.setDelay(0.2f);
            lv.setLayoutAnimation(lac);
        }
    }
}
