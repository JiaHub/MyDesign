package com.example.g40_70.coursedesign.news_activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.g40_70.coursedesign.R;

public class news_train extends AppCompatActivity {

    private ListView train_lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_train_layout);

        train_lv = findViewById(R.id.lv_train);

    }
}
