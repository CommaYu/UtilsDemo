package com.example.spinner;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

/**
 * 产看官网的Demo写法
 * file:///C:/Users/Administrator/AppData/Local/Android/sdk/docs/guide/topics/ui/controls/spinner.html#Populate
 */
public class MainActivity extends AppCompatActivity {
    String TAG = getClass().getSimpleName();
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = (Spinner) findViewById(R.id.spinner);
        // 静态为Spinner获取数据
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.planets_arrray, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // 动态为Spinner获取数据
//        List<String> list = new ArrayList<>();
//        list.add("test1");
//        list.add("test2");
//        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.item, R.id.tv_item, list);
        spinner.setAdapter(adapter);
        spinner.setPrompt("测试Spinner");
        spinner.setOnItemSelectedListener(new SpinnerItemListener());
    }

    class SpinnerItemListener implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            Log.d(TAG, "onItemSelected: int=" + i);
            Log.d(TAG, "onItemSelected: long=" + l);
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {
            Log.d(TAG, "onNothingSelected: ");
        }
    }
}
