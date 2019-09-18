package com.example.g40_70.coursedesign.player_activity;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.g40_70.coursedesign.R;
import com.example.g40_70.coursedesign.extra_activity.Db_lite;

import static android.view.Gravity.CENTER;

public class player_config extends AppCompatActivity {

    private Toolbar toolbar;
    private RelativeLayout r_info, r_feedback, r_cache, r_about;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_config);

        /** Toolbar组件配置 **/
        toolbar = findViewById(R.id.toolbar_config);
        toolbar.setTitle("");           //设置title为空，去原有标题

        TextView topText = findViewById(R.id.config_title);       //设置标题
        topText.setText(R.string.config);
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

        //获取布局
        r_info = findViewById(R.id.info_config);
        r_cache = findViewById(R.id.cache_config);
        r_feedback = findViewById(R.id.feedback_config);
        r_about = findViewById(R.id.about_config);
        //注册监听
        r_info.setOnClickListener(new infoListener());
        r_cache.setOnClickListener(new cacheListener());
        r_feedback.setOnClickListener(new feedbackListener());
        r_about.setOnClickListener(new aboutListener());

        Button exit_button = findViewById(R.id.exit_login);
        exit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exit();
            }
        });

    }

    //布局监听

    /**
     * 我的信息
     */
    private class infoListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {

            LinearLayout layout = new LinearLayout(getApplicationContext());
            layout.setOrientation(LinearLayout.VERTICAL);
            ImageView t_imageView = new ImageView(getApplicationContext());
            t_imageView.setImageResource(R.drawable.info);//设置一张图片
            TextView t_text = new TextView(getApplicationContext());
            t_text.setText("别点了\n作者不打算让你看！");
            layout.addView(t_imageView, new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            layout.addView(t_text, new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));

            Toast toast = new Toast(player_config.this);

            toast.setView(layout);
            toast.setDuration(Toast.LENGTH_SHORT);//设置显示时长
            toast.show();

        }
    }

    /**
     * 数据库
     */
    private class cacheListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {

            LayoutInflater factory = LayoutInflater.from(player_config.this);//提示框
            final View view = factory.inflate(R.layout.into_db_layout, null);

            final EditText in_name = (EditText) view.findViewById(R.id.into_db_name);
            final EditText in_pass = (EditText) view.findViewById(R.id.into_db_pass);

            in_name.setHint("用户名：");
            in_pass.setHint("口令码：");

            //加入弹窗
            Dialog dialog = new AlertDialog.Builder(player_config.this)      //实例化对象
                    .setTitle("请输入口令")
                    .setView(view)
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            if (in_name.getText().toString().isEmpty() && in_pass.getText().toString().isEmpty()) {
                                Intent intent = new Intent(player_config.this, Db_lite.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(player_config.this, "口令错误！", Toast.LENGTH_SHORT).show();
                            }
                        }
                    })
                    .setNegativeButton("取消", null)         //null则使用系统默认的事件监听
                    .create();
            dialog.show();

        }
    }

    /**
     * 意见反馈
     */
    private class feedbackListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {

            Intent intent = new Intent(player_config.this, player_feedback.class);
            startActivity(intent);

        }
    }

    /**
     * 关于软件
     */
    private class aboutListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {

            Dialog dialog = new AlertDialog.Builder(player_config.this)      //实例化对象
                    .setIcon(R.drawable.info)          //写入图片
                    .setTitle("关于软件")               //显示标题
                    .setMessage("本软件参考练球后，" +
                            "\n相比之下拿不出手。" +
                            "\n这是一次课程设计！" +
                            "\n还是需要严肃对待。" +
                            "\n如果遇到Bug请速与我联系！" +
                            "\n联系方式（QQ）：**********")     //显示内容
                    .setPositiveButton("显示",                          //添加确定按钮
                            new DialogInterface.OnClickListener() {      //设置监听
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    Toast.makeText(player_config.this, "没有权限！", Toast.LENGTH_SHORT).show();
                                } //单击事件
                            })
                    .setNegativeButton("关闭",                            //添加取消按钮
                            new DialogInterface.OnClickListener() {      //设置监听
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    finish();
                                }  //单击事件
                            })
                    .create();                          //创建对话框
            dialog.show();                      //显示对话框

        }
    }

    /**
     * 退出程序
     **/
    protected void exit() {
        Intent startMain = new Intent(Intent.ACTION_MAIN);
        startMain.addCategory(Intent.CATEGORY_HOME);
        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(startMain);
        System.exit(0);
    }

}
