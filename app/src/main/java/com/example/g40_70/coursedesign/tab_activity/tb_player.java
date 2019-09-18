package com.example.g40_70.coursedesign.tab_activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.g40_70.coursedesign.R;
import com.example.g40_70.coursedesign.extra_activity.Db_lite;
import com.example.g40_70.coursedesign.player_activity.player_config;
import com.example.g40_70.coursedesign.player_activity.player_editor;
import com.example.g40_70.coursedesign.sql_lite.PlayDao;
import com.example.g40_70.coursedesign.sql_lite.Player;

import static android.view.Gravity.CENTER;

public class tb_player extends AppCompatActivity {

    private Toolbar toolbar;
    private Button day_check;
    private RelativeLayout r_age, r_sex, r_height, r_weight, r_check;
    private TextView t_age, t_sex, t_height, t_weight, t_name, t_number, t_check_conut;

    private PlayDao playDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player_layout);

        /** Toolbar组件配置 **/
        toolbar = findViewById(R.id.toolbar_player);
        toolbar.setTitle("");           //设置title为空，去原有标题

        TextView topText = findViewById(R.id.player_title);       //设置标题
        topText.setText(R.string.my);
        topText.setGravity(CENTER);

        //取代原本的actionbar
        setSupportActionBar(toolbar);

        //这个左上角图标要放在setSupportActionBar(toolbar);后面
        toolbar.setNavigationIcon(R.mipmap.config);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(tb_player.this, player_config.class);
                startActivity(intent);

//                Toast.makeText(myhome.this, "返回", Toast.LENGTH_SHORT).show();
            }
        });

        //设置菜单监听
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                Intent intent = new Intent();
                intent.setClass(tb_player.this, player_editor.class);
                startActivity(intent);
                return true;
            }
        });

        playDao = new PlayDao(this);

//        获取文本框
        t_name = findViewById(R.id.polo_name);
        t_age = findViewById(R.id.player_age_text);
        t_sex = findViewById(R.id.player_sex_text);
        t_number = findViewById(R.id.polo_number);
        t_height = findViewById(R.id.player_height_text);
        t_weight = findViewById(R.id.player_weight_text);
        t_check_conut = findViewById(R.id.player_check_text);

        //获取布局
        r_age = findViewById(R.id.player_age);
        r_sex = findViewById(R.id.player_sex);
        r_height = findViewById(R.id.player_height);
        r_weight = findViewById(R.id.player_weight);
        r_check = findViewById(R.id.player_check);

        //注册监听布局
        r_age.setOnClickListener(new ageListener());
        r_sex.setOnClickListener(new sexListener());
        r_height.setOnClickListener(new heightListener());
        r_weight.setOnClickListener(new weightListener());
        r_check.setOnClickListener(new checkListener());

        //刷新
        day_check = findViewById(R.id.day_check);
        day_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences data1 = getSharedPreferences("data", Context.MODE_PRIVATE);
                String name = data1.getString("name", String.valueOf(R.string.pre_polo_name));
                t_name.setText(name);
                initDate();
            }
        });


    }

    /**
     * 数据初始化
     * 当页面可见时刷新数据
     */
    protected void onStart(){
        super.onStart();

        SharedPreferences date = getSharedPreferences("data",Context.MODE_PRIVATE);
        String name = date.getString("name",getString(R.string.pre_polo_name));

        t_name.setText(name);

        initDate();

    }

    /**
     * 写入数据
     */
    private void initDate() {

        //获取内容

        String name = t_name.getText().toString();

        //存储数据到编辑
        SharedPreferences.Editor saved = getSharedPreferences("data", Context.MODE_PRIVATE).edit();
        saved.putString("name", name);
        saved.apply();

        if (!name.isEmpty()) {         //判断用户名是否为空

            Player player = playDao.queryPlayer(name);

            if (player != null) {       //判断是否存在记录

                //获取数据
                String tv_name = player.getName();
                String tv_age = player.getAge();
                String tv_sex = player.getSex();
                String tv_height = player.getHeight();
                String tv_weight = player.getWeight();
                String tv_number = player.getNumber();

                t_age.setText(tv_age);
                t_sex.setText(tv_sex);
                t_name.setText(tv_name);
                t_height.setText(tv_height);
                t_weight.setText(tv_weight);
                t_number.setText(tv_number);

            }

        }
    }

    /**
     * 添加菜单
     */
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.mypopmenu, menu);
        return true;
    }

    /**
     * 布局监听方法
     */
    private class ageListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {

            String age = t_age.getText().toString();
            String text_age = getResources().getString((R.string.age)) + "：" + age;
            Toast.makeText(tb_player.this, text_age, Toast.LENGTH_SHORT).show();

        }
    }

    private class sexListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {

            String sex = t_sex.getText().toString();
            String text_sex = getResources().getString((R.string.sex)) + "：" + sex;
            Toast.makeText(tb_player.this, text_sex, Toast.LENGTH_SHORT).show();

        }
    }

    private class heightListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {

            String height = t_height.getText().toString();
            String text_height = getResources().getString((R.string.height)) + "：" + height;
            Toast.makeText(tb_player.this, text_height, Toast.LENGTH_SHORT).show();

        }
    }

    private class weightListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {

            String weight = t_weight.getText().toString();
            String text_weight = getResources().getString((R.string.weight)) + "：" + weight;
            Toast.makeText(tb_player.this, text_weight, Toast.LENGTH_SHORT).show();

        }
    }

    private class checkListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {

            //接收数据
            SharedPreferences date = getSharedPreferences("data", Context.MODE_PRIVATE);
            String time = date.getString("time", "");
            int check_count = date.getInt("count", 0);
            String check = String.valueOf(check_count);
            t_check_conut.setText(check);
            Toast.makeText(tb_player.this, "签到时间：" + time, Toast.LENGTH_SHORT).show();

        }
    }
}