package com.example.g40_70.coursedesign.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.g40_70.coursedesign.R;

public class testTopbar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_top);
        Topbar topbar = findViewById(R.id.topbar);

        //根据自定义监听，监听topbar
        topbar.setOnTopbarClickListener(new Topbar.topbarClickListener() {
            @Override
            public void leftClick() {
                Toast.makeText(testTopbar.this, "left", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void rightClick() {
                Toast.makeText(testTopbar.this, "right", Toast.LENGTH_SHORT).show();
            }
        });
    }
}