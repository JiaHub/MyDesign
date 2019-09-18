package com.example.g40_70.coursedesign.test;

import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.g40_70.coursedesign.R;
import com.example.g40_70.coursedesign.adapter.NumericWheelAdapter;
import com.example.g40_70.coursedesign.widget.WheelView;

import java.util.Calendar;
import java.util.Locale;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

public class test_wheel extends AppCompatActivity {

    private WheelView polo_number;
    private WheelView play_site;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_wheel);

        Button test = findViewById(R.id.test_btn);


        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               showPolo_number_Dialog();

            }
        });

        Button test1 = findViewById(R.id.test_btn1);


        test1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showPlay_site_Dialog();

            }
        });


    }

    /**
     * 初始化球衣号码
     */
    private void initPolo_number() {
        NumericWheelAdapter numericWheelAdapter = new NumericWheelAdapter(this,1, 99, "%02d");
        numericWheelAdapter.setLabel(" 号");
//		numericWheelAdapter.setTextSize(15);  设置字体大小
        polo_number.setViewAdapter(numericWheelAdapter);
        polo_number.setCyclic(true);
    }

    /**
     * 初始化场上位置
     * 定义数组
     */
    private void initPlay_site() {
        NumericWheelAdapter numericWheelAdapter = new NumericWheelAdapter(this,0, 11, "%02d");
        numericWheelAdapter.setLabel(" ");
//		numericWheelAdapter.setTextSize(15);  设置字体大小
        play_site.setViewAdapter(numericWheelAdapter);
        play_site.setCyclic(true);
    }

    /**
     * 显示球衣号码
     */
    public void showPolo_number_Dialog(){
        final AlertDialog dialog = new AlertDialog.Builder(this).create();
        dialog.show();
        Window window = dialog.getWindow();
        // 设置布局
        window.setContentView(R.layout.activity_wheel_select);
        // 设置宽高
        window.setLayout(MATCH_PARENT,WRAP_CONTENT);
//        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        // 设置弹出的动画效果
        window.setWindowAnimations(R.style.AnimBottom);

        Calendar c = Calendar.getInstance();
        int curMin = c.get(Calendar.MINUTE);

        polo_number = (WheelView) window.findViewById(R.id.wheel);
        initPolo_number();
        // 设置当前参数
        polo_number.setCurrentItem(curMin);

        polo_number.setVisibleItems(7);

        // 设置监听
        Button ok = (Button) window.findViewById(R.id.set);
        Button cancel = (Button) window.findViewById(R.id.cancel);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                String str = String.format(Locale.CHINA,"%2d", polo_number.getCurrentItem());
                Toast.makeText(getApplicationContext(), str, Toast.LENGTH_LONG).show();
                dialog.cancel();
            }
        });
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


    /**
     * 显示场上位置
     */
    public void showPlay_site_Dialog(){
        final AlertDialog dialog = new AlertDialog.Builder(this).create();
        dialog.show();
        Window window = dialog.getWindow();
        // 设置布局
        window.setContentView(R.layout.activity_wheel_select);
        // 设置宽高
        window.setLayout(MATCH_PARENT,WRAP_CONTENT);
//        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        // 设置弹出的动画效果
        window.setWindowAnimations(R.style.AnimBottom);

        Calendar c = Calendar.getInstance();
        int curMin = c.get(Calendar.MINUTE);

        play_site = (WheelView) window.findViewById(R.id.wheel);
        initPlay_site();
        // 设置当前参数
        play_site.setCurrentItem(10);


        play_site.setVisibleItems(7);

        // 设置监听
        Button ok = (Button) window.findViewById(R.id.set);
        Button cancel = (Button) window.findViewById(R.id.cancel);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                String str = String.format(Locale.CHINA,"%2d", polo_number.getCurrentItem());
//                Toast.makeText(getApplicationContext(), str, Toast.LENGTH_LONG).show();
                dialog.cancel();
            }
        });
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
