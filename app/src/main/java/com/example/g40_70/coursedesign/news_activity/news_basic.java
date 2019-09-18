package com.example.g40_70.coursedesign.news_activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.g40_70.coursedesign.R;

public class news_basic extends AppCompatActivity {

    private ListView basic_lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_basic_layout);

        basic_lv = findViewById(R.id.lv_basic);

    }
}
