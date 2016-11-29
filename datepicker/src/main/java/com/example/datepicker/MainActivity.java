package com.example.datepicker;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    String TAG = getClass().getSimpleName();
    // 该常量用于标识DatePickerDialog
    public static final int DATE_PICKER_ID = 1;
    // 该常量用于标识TimePickerDialog
    public static final int TIME_PICKER_ID = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_showDatePicker).setOnClickListener(new BtnDatePickerListener());
        findViewById(R.id.btn_showTimePicker).setOnClickListener(new BtnTimePickerListener());
    }

    private class BtnDatePickerListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            showDialog(DATE_PICKER_ID);
        }
    }

    private class BtnTimePickerListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            showDialog(TIME_PICKER_ID);
        }
    }

    //监听器，用户监听用户点下DatePikerDialog的确定按钮时，所设置的年月日
    DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            Log.d(TAG, "onDateSet: " + i + "year" + i1 + "month" + i2 + "dayOfMonth");
            Toast.makeText(MainActivity.this, i + "年" + i1 + "月" + i2 + "日", Toast.LENGTH_SHORT).show();
        }
    };

    TimePickerDialog.OnTimeSetListener onTimeChangedListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker timePicker, int i, int i1) {
            Toast.makeText(MainActivity.this, i + "时" + i1 + "分", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_PICKER_ID:
                // 里面的参数是初始化年月日，注意“年”，0-11代表1-12月，需要适配，此处设置11的是，其实是12月
                return new DatePickerDialog(this, onDateSetListener, 2010, 11, 25);
            case TIME_PICKER_ID:
                return new TimePickerDialog(this, onTimeChangedListener, 5, 20, true);
        }
        return null;
    }
}