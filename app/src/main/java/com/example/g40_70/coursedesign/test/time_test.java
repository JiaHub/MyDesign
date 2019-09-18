package com.example.g40_70.coursedesign.test;

import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.g40_70.coursedesign.R;
import com.example.g40_70.coursedesign.adapter.NumericWheelAdapter;
import com.example.g40_70.coursedesign.widget.WheelView;

import java.util.Calendar;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

public class time_test extends AppCompatActivity {

    private WheelView mins;

    Button tv_time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_test);

        tv_time = (Button) findViewById(R.id.t_time);//时间选择器

        tv_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                showTimeDialog();
            }
        });

    }

    /**
     * 初始化分
     */
    private void initMins() {
        NumericWheelAdapter numericWheelAdapter = new NumericWheelAdapter(this,0, 59, "%02d");
        numericWheelAdapter.setLabel(" 分");
        mins.setViewAdapter(numericWheelAdapter);
        mins.setCyclic(true);
    }


    /**
     * 显示时间
     */
    private void showTimeDialog(){
        final AlertDialog dialog = new AlertDialog.Builder(time_test.this)
                .create();
        dialog.show();
        Window window = dialog.getWindow();
        // 设置布局
        window.setContentView(R.layout.select_time);
        // 设置宽高
        window.setLayout(MATCH_PARENT,WRAP_CONTENT);
//        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        // 设置弹出的动画效果
        window.setWindowAnimations(R.style.AnimBottom);

        Calendar c = Calendar.getInstance();
//        int curHour = c.get(Calendar.HOUR_OF_DAY);
        int curMin = c.get(Calendar.MINUTE);


//        hour = (WheelView) window.findViewById(R.id.hour);
//        initHour();
        mins = (WheelView) window.findViewById(R.id.mins);
        initMins();
        // 设置当前时间
//        hour.setCurrentItem(curHour);
        mins.setCurrentItem(curMin);


//        hour.setVisibleItems(7);
        mins.setVisibleItems(7);

        // 设置监听
        Button ok = (Button) window.findViewById(R.id.set);
        Button cancel = (Button) window.findViewById(R.id.cancel);
//        ok.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // TODO Auto-generated method stub
//                String str = String.format(Locale.CHINA,"%2d:%2d",hour.getCurrentItem(), mins.getCurrentItem());
//                Toast.makeText(time_test.this, str, Toast.LENGTH_LONG).show();
//                dialog.cancel();
//            }
//        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                dialog.cancel();
            }
        });
        LinearLayout cancelLayout = (LinearLayout) window.findViewById(R.id.view_none);
        cancelLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                dialog.cancel();
                return false;
            }
        });
    }

}
