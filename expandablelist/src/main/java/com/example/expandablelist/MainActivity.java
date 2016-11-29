package com.example.expandablelist;

import android.app.ExpandableListActivity;
import android.os.Bundle;
import android.widget.SimpleExpandableListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 这个继承ExpandableListActivity和继承ListActivity有点类似
 */
public class MainActivity extends ExpandableListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //定义一个List，该List对象为一级条目提供数据
        List<Map<String, String>> groups = new ArrayList<Map<String, String>>();
        Map<String, String> group1 = new HashMap<String, String>();
        group1.put("group", "group1");
        Map<String, String> group2 = new HashMap<String, String>();
        group2.put("group", "group2");
        groups.add(group1);
        groups.add(group2);

        //定义一个List，该List对象为第一个一级条目提供二级条目的数据
        List<Map<String, String>> child1 = new ArrayList<Map<String, String>>();
        Map<String, String> child1Data1 = new HashMap<String, String>();
        child1Data1.put("child", "child1Data1");
        child1.add(child1Data1);
        Map<String, String> child1Data2 = new HashMap<String, String>();
        child1Data2.put("child", "child1Data2");
        child1.add(child1Data2);

        //定义一个List,该List对象为第二个一级条目提供二级条目的数据
        List<Map<String, String>> child2 = new ArrayList<Map<String, String>>();
        Map<String, String> child2Data = new HashMap<String, String>();
        child2Data.put("child", "child2Data");
        child2.add(child2Data);

        //定义一个List，该List对象用来存储所有的二级条目的数据
        List<List<Map<String, String>>> childs = new ArrayList<List<Map<String, String>>>();
        childs.add(child1);
        childs.add(child2);

        //生成一个SimpleExpandableListAdapter对象
        //1.context
        //2.一级条目的数据
        //3.用来设置一级条目样式的布局文件
        //4.new String[]{"group"}指定一级条目数据的key************************这个点我觉得非常的重要哦，这个key其实就是我们上面Map中定义的key值哦
        //5.指定一级条目数据显示控件的id
        //6.指定二级条目的数据
        //7.用来设置二级条目样式的布局文件
        //8.指定二级条目数据的key
        //9.指定二级条目数据显示控件的id
        SimpleExpandableListAdapter sela = new SimpleExpandableListAdapter(this, groups, R.layout.group, new String[]{"group"}, new int[]{R.id.groupTo}, childs, R.layout.child, new String[]{"child"}, new int[]{R.id.childTo});
        //将SimpleExpandableListAdapter对象设置给当前的ExpandableListActivity
        setListAdapter(sela);
    }
}
