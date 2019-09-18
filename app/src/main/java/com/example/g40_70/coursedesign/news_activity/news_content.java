package com.example.g40_70.coursedesign.news_activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.g40_70.coursedesign.R;

import static android.view.Gravity.CENTER;

public class news_content extends AppCompatActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_content);

        /** Toolbar组件配置 **/
        toolbar = findViewById(R.id.toolbar_home);
        toolbar.setTitle("");           //设置title为空，去原有标题

        TextView topText = findViewById(R.id.home_title);       //设置标题
        topText.setText(R.string.content);
        topText.setGravity(CENTER);

        //取代原本的actionbar
        setSupportActionBar(toolbar);

        //这个左上角图标要放在setSupportActionBar(toolbar);后面
        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //设置菜单监听
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                return true;
            }
        });

        //接收数据(方法一)
//        Bundle bundle = getIntent().getExtras();
//        int id = bundle.getInt("picture");
//        String title = bundle.getString("title");
//        String content = bundle.getString("content");

        //接收数据
        SharedPreferences date = getSharedPreferences("data", Context.MODE_PRIVATE);
        int id = date.getInt("picture",0);
        String title = date.getString("title","");
        String content = date.getString("content","");

        ImageView news_image = findViewById(R.id.news_image);
        TextView news_title = findViewById(R.id.news_title);
        TextView news_content = findViewById(R.id.news_content);

        news_image.setImageResource(id);
        news_title.setText("队名："+title);
        news_content.setText(content);

    }
}
