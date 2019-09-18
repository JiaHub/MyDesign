package com.example.g40_70.coursedesign.extra_activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.g40_70.coursedesign.sql_lite.PlayDao;
import com.example.g40_70.coursedesign.sql_lite.Player;
import com.example.g40_70.coursedesign.R;

public class Db_lite extends AppCompatActivity {

    private EditText tb_name, tb_age, tb_sex, tb_site, tb_foot, tb_height, tb_weight, tb_number;
    private Button insert, delete, update, query, deleteAll;
    private ImageButton back;
    private TextView result;

    private PlayDao playDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dn_lite);

        playDao = new PlayDao(this);

        //获取编辑框
        tb_age = findViewById(R.id.db_et_age);
        tb_sex = findViewById(R.id.db_et_sex);
        tb_name = findViewById(R.id.db_et_name);
        tb_site = findViewById(R.id.db_et_site);
        tb_foot = findViewById(R.id.db_et_foot);
        tb_height = findViewById(R.id.db_et_height);
        tb_weight = findViewById(R.id.db_et_weight);
        tb_number = findViewById(R.id.db_et_number);

        //内容显示
        result = findViewById(R.id.db_tv_content);
        result.setText("");

        //获取按钮
        query = findViewById(R.id.db_btn_query);
        insert = findViewById(R.id.db_btn_insert);
        delete = findViewById(R.id.db_btn_delete);
        update = findViewById(R.id.db_btn_update);
        deleteAll = findViewById(R.id.db_btn_deleteAll);

        //设置监听
        query.setOnClickListener(new queryListener());
        insert.setOnClickListener(new insertListener());
        delete.setOnClickListener(new deleteListener());
        update.setOnClickListener(new updateListener());
        deleteAll.setOnClickListener(new deleteAllListener());

        //返回
        back = findViewById(R.id.db_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        setUser();

    }

    /**
     * 填入当前用户名
     */
    private void setUser() {
        SharedPreferences content = getSharedPreferences("date", Context.MODE_PRIVATE);
        String user = content.getString("name",getString(R.string.pre_polo_name));
        tb_name.setText(user);
    }

    /**
     * 查询单个用户
     */
    private class queryListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {

            String name = tb_name.getText().toString();
            Player player = playDao.queryPlayer(name);

            if (!name.isEmpty()) {         //判断用户名是否为空

                if (player != null) {       //判断是否存在记录

                    //获取数据
                    String tv_name = player.getName();
                    String tv_age = player.getAge();
                    String tv_sex = player.getSex();
                    String tv_site = player.getSite();
                    String tv_foot = player.getFoot();
                    String tv_height = player.getHeight();
                    String tv_weight = player.getWeight();
                    String tv_number = player.getNumber();

                    tb_age.setText(tv_age);
                    tb_sex.setText(tv_sex);
                    tb_name.setText(tv_name);
                    tb_site.setText(tv_site);
                    tb_foot.setText(tv_foot);
                    tb_height.setText(tv_height);
                    tb_weight.setText(tv_weight);
                    tb_number.setText(tv_number);

                    result.setText(" 球服名:" + tv_name + " 年龄：" + tv_age + " 性别：" + tv_sex + " 位置：" + tv_site +
                            "\n 惯用脚：" + tv_foot + " 身高：" + tv_height + " 体重：" + tv_weight + " 号码：" + tv_number);

                } else {
                    Toast.makeText(Db_lite.this, "用户不存在！", Toast.LENGTH_SHORT).show();
                }

            } else {
                Toast.makeText(Db_lite.this, "请检查是否输入用户名！", Toast.LENGTH_SHORT).show();
            }

        }
    }

    /**
     * 添加用户
     */
    private class insertListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {

            String name = tb_name.getText().toString();
            Player player1 = playDao.queryPlayer(name);
            if (!name.isEmpty()) {            //用户名不为空

                if (player1 == null) {       //且不存在该用户名

                    Player player = new Player();
                    player.setName(tb_name.getText().toString());
                    player.setAge(tb_age.getText().toString());
                    player.setSex(tb_sex.getText().toString());
                    player.setSite(tb_site.getText().toString());
                    player.setFoot(tb_foot.getText().toString());
                    player.setHeight(tb_height.getText().toString());
                    player.setWeight(tb_weight.getText().toString());
                    player.setNumber(tb_number.getText().toString());

                    Log.d("out", "onClick: " + playDao);
                    Log.d("out", "onClick: " + player.toString());

                    playDao.insert(player);

                    Toast.makeText(Db_lite.this, "增加成功！", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Db_lite.this, "用户已存在！", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(Db_lite.this, "请输入用户名！", Toast.LENGTH_SHORT).show();
            }

        }
    }

    /**
     * 删除单个用户
     */
    private class deleteListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {

            String name = tb_name.getText().toString();
            if (!name.isEmpty()) {
                playDao.deletePlayer(name);
                tb_name.setText("");
                tb_age.setText("");
                tb_sex.setText("");
                tb_foot.setText("");
                tb_site.setText("");
                tb_height.setText("");
                tb_weight.setText("");
                tb_number.setText("");
            } else {
                Toast.makeText(Db_lite.this, "请输入用户名！", Toast.LENGTH_SHORT).show();
            }

        }
    }

    /**
     * 更新用户
     */
    private class updateListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {

            String name = tb_name.getText().toString();
            if (!name.isEmpty()) {
                Player player = playDao.queryPlayer(name);
                player.setName(tb_name.getText().toString());
                player.setAge(tb_age.getText().toString());
                player.setSex(tb_sex.getText().toString());
                player.setSite(tb_site.getText().toString());
                player.setFoot(tb_foot.getText().toString());
                player.setHeight(tb_height.getText().toString());
                player.setWeight(tb_weight.getText().toString());
                player.setNumber(tb_number.getText().toString());
                playDao.update(player);
                Toast.makeText(Db_lite.this, "修改成功！", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(Db_lite.this, "请输入用户名！", Toast.LENGTH_SHORT).show();
            }

        }
    }

    /**
     * 清空所有用户数据
     */
    private class deleteAllListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {

            String pass = tb_name.getText().toString();

            if(pass.equals("admin")) {

                playDao.delete();
                tb_name.setText("");
                tb_age.setText("");
                tb_sex.setText("");
                tb_foot.setText("");
                tb_site.setText("");
                tb_height.setText("");
                tb_weight.setText("");
                tb_number.setText("");

                Toast.makeText(Db_lite.this, "已清空数据库！", Toast.LENGTH_SHORT).show();

            }else{
                Toast.makeText(Db_lite.this, "没有权限！\n请输入最高权限！", Toast.LENGTH_SHORT).show();
            }

        }
    }
}
