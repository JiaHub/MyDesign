package com.example.g40_70.coursedesign.extra_activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.g40_70.coursedesign.R;

import java.util.Timer;
import java.util.TimerTask;

public class the_first extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_first);

        Toast.makeText(this, "点击左上角跳过！", Toast.LENGTH_SHORT).show();

        Button skip = findViewById(R.id.first_skip);
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity();
            }
        });

//        //延迟方法
//        new Timer().schedule(new TimerTask() {
//            @Override
//            public void run() {
//                startActivity();
//            }
//        }, 4000);

    }



    private void startActivity() {
        Intent intent = new Intent(the_first.this, myhome.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_left_in, R.anim.slide_right_out);
        finish();
    }

}
