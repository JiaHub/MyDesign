package com.example.g40_70.coursedesign.tab_activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.g40_70.coursedesign.R;
import com.example.g40_70.coursedesign.sql_lite.PlayDao;
import com.example.g40_70.coursedesign.sql_lite.Player;

import java.text.SimpleDateFormat;
import java.util.Date;

import static android.view.Gravity.CENTER;
import static android.view.Gravity.END;
import static android.view.Gravity.RIGHT;

public class tb_home extends AppCompatActivity {

    Toolbar toolbar;
    TextView re_1, re_2, re_3;          //热点新闻
    private TextView right_text;

    private int check_count = 0;        //签到次数

    String number = null;               //右上角显示的号码

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_layout);

        // Toolbar组件配置
        toolbar = findViewById(R.id.toolbar_home);
        toolbar.setTitle("");           //设置title为空，去原有标题

        TextView topText = findViewById(R.id.home_title);       //设置标题
        topText.setText(R.string.train_place);
        topText.setGravity(CENTER);

        right_text = findViewById(R.id.home_right);

        right_text.setText(R.string.pre_polo_number);
        right_text.setGravity(END);

        //取代原本的actionbar
        setSupportActionBar(toolbar);

        //这个左上角图标要放在setSupportActionBar(toolbar);后面
        toolbar.setNavigationIcon(R.mipmap.sign);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //获取时间
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日   HH:mm:ss");
                Date curDate = new Date(System.currentTimeMillis());
                String time = formatter.format(curDate);

                check_count = check_count + 1;

                SharedPreferences.Editor saved = getSharedPreferences("data", Context.MODE_PRIVATE).edit();
                saved.putString("time", time);
                saved.putInt("count", check_count);
                saved.apply();

                Toast.makeText(tb_home.this, getString(R.string.check_day), Toast.LENGTH_SHORT).show();
            }
        });

        //设置菜单监听
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                return true;
            }
        });

        right_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences data = getSharedPreferences("data", Context.MODE_PRIVATE);
                String name = data.getString("name", getString(R.string.pre_polo_name));

                //数据来源
                if (name.isEmpty()) {
                    number = data.getString("number", "28");
                } else {
                    PlayDao playDao = new PlayDao(tb_home.this);
                    Player player = playDao.queryPlayer(name);
                    if (player != null) {
                        number = player.getNumber();
                    }else {
                        number = getString(R.string.pre_polo_number);
                    }
                }

                //处理要显示的数据
                if (!number.isEmpty()) {
                    number = number.substring(0, 2);
                }
                right_text.setText(number);
            }
        });


        //热门推荐
        re_1 = findViewById(R.id.tv_2_1);
        re_2 = findViewById(R.id.tv_2_2);
        re_3 = findViewById(R.id.tv_2_3);

        re_1.setOnClickListener(new re1_ClickListener());
        re_2.setOnClickListener(new re1_ClickListener());
        re_3.setOnClickListener(new re1_ClickListener());


    }

    private class re1_ClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Toast.makeText(tb_home.this, "还在加紧施工，敬请期待！", Toast.LENGTH_SHORT).show();
        }
    }
}