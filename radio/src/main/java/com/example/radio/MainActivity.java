package com.example.radio;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

/**
 * RadioGroup和RadioButton用法，不过实在太简单了
 * CheckBox的用法
 */
public class MainActivity extends AppCompatActivity {
    String TAG = getClass().getSimpleName();
    private RadioGroup rg_gender;
    private RadioButton rb_male, rb_female;
    private CheckBox cb_run;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rg_gender = (RadioGroup) findViewById(R.id.rg_gender);
        rb_male = (RadioButton) findViewById(R.id.rb_male);
        rb_female = (RadioButton) findViewById(R.id.rb_female);
        cb_run = (CheckBox) findViewById(R.id.cb_run);

        rg_gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (rb_male.getId() == i) {
                    Log.d(TAG, "onCheckedChanged: male");
                    Toast.makeText(MainActivity.this, "male", Toast.LENGTH_SHORT).show();
                } else if (i == rb_female.getId()) {
                    Log.d(TAG, "onCheckedChanged: female");
                    Toast.makeText(MainActivity.this, "female", Toast.LENGTH_SHORT).show();
                }
            }
        });

        cb_run.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    Log.d(TAG, "onCheckedChanged: 选中" + b);
                } else {
                    Log.d(TAG, "onCheckedChanged: 没选中" + b);
                }
            }
        });
    }
}
