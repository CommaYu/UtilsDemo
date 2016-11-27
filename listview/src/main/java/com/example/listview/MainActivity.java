package com.example.listview;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 实现功能：了解ListActivity的用法，实现Adapter为空的时候，进行友好提示。
 *
 * ListActivity是继承Activity的,其实跟Activity用法差不多，多的就是自己添加了些方法而已
 * 这个继承ListActivity还是很棒的
 *
 * 1. ListActivity包含一个默认的含有ListView的layout布局（这个是看不出来的），意思就是继承了ListActivity，
 * 我们可以直接通过setListAdapter()的方法设置一个Adapter，就可以显示一个只有ListView的界面了。（然而大多数都需要我们自己定义布局）
 *
 * 2. 如果我们在extends ListActivity的类下的onCreate用setContentView设置自己的布局activity_main.xml。
 * 那个Listview的id必须是`android:id="@android:id/list"`，设置这个id，我们就不需要在继承ListActivity的类中
 * 通过findViewById找ListView了。直接用setListAdapter()方法就为我们的ListView设置适配器，并设置数据。（说实话这一点真的挺方便，继承ListActivity减少了很多代码）
 *
 * 3. 如果当Adapter中的数据为空的时候，我们可以为ListView设置一个友好的提示。一个空的界面（比如：驾驾行车中，当爱车为空的时候），
 * 把ListView替换成一个空的有字的图片。这时候要把设置为空的View（ImageView或者TextView）的id变成`android:id="@android:id/empty"`，
 * （当ListView内容为空的时候，这个自动替换ListView所在的位置）
 */
public class MainActivity extends ListActivity {
    String TAG = getClass().getSimpleName();
    private List<HashMap<String, String>> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setListAdapter(getSimpleAdapter());
            }
        });
    }

    private SimpleAdapter getSimpleAdapter() {
        HashMap<String, String> map1 = new HashMap<>();
        HashMap<String, String> map2 = new HashMap<>();
        HashMap<String, String> map3 = new HashMap<>();

        map1.put("name", "zhangsan");
        map1.put("gender", "male");
        map2.put("name", "lisi");
        map2.put("gender", "male");
        map3.put("name", "wangwu");
        map3.put("gender", "female");

        list.add(map1);
        list.add(map2);
        list.add(map3);

        SimpleAdapter simpleAdapter = new SimpleAdapter(this, list, R.layout.item, new String[]{"name", "gender"}, new int[]{R.id.name, R.id.gender});

        return simpleAdapter;
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Log.d(TAG, "onListItemClick: id" + id);
        Log.d(TAG, "onListItemClick: position" + position);
    }
}
