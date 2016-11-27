package com.example.progressbar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    private ProgressBar pb_first, pb_second;
    private Button btn;
    private int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pb_first = (ProgressBar) findViewById(R.id.pb_first);
        //设置进度条最大值，可以在xml中设置，也可以在java中设置
        // pb_first.setMax(200);
        pb_second = (ProgressBar) findViewById(R.id.pb_second);

        findViewById(R.id.btn).setOnClickListener(new BtnListener());
    }

    private class BtnListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            if (i == 0) {
                pb_first.setProgress(0);
                pb_second.setProgress(0);
            } else if (i < pb_first.getMax()) {
                // 主进度条的当前值
                pb_first.setProgress(i);
                // 设置第二进度条的值
                pb_first.setSecondaryProgress(i + 20);
                // 圆形显示的进度条，是不可以显示状态的，只能一直转圈圈
                pb_second.setProgress(i);
            } else {
                pb_first.setProgress(0);
                pb_first.setSecondaryProgress(0);
                i = 0;
            }
            i += 10;
        }
    }
}
