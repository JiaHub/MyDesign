package com.example.g40_70.coursedesign.player_activity;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.g40_70.coursedesign.R;

import static android.view.Gravity.CENTER;

public class player_feedback extends AppCompatActivity {

    Toolbar toolbar;
    EditText content;
    Button submit;

    TextView max_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_feedback);

        /** Toolbar组件配置 **/
        toolbar = findViewById(R.id.toolbar_feedback);
        toolbar.setTitle("");           //设置title为空，去原有标题

        TextView topText = findViewById(R.id.feedback_title);       //设置标题
        topText.setText(R.string.feedback);
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

        content = findViewById(R.id.feedback_content);
        max_view = findViewById(R.id.max_num);

        /** 添加字数限制 */
        max_view.setText(0+"/140");  //140是我限制的最大输入字符数
        content.addTextChangedListener(new TextWatcher() {

            int current_Length = 0;

            public void onTextChanged (CharSequence s,int start, int before, int count){
                if (current_Length < 140) {
                    current_Length = content.getText().length();
                }
            }
            public void beforeTextChanged (CharSequence s,int start, int count, int after){
                max_view.setText(current_Length + "/140");
            }
            public void afterTextChanged (Editable s){
                max_view.setText(current_Length + "/140");
            }
        });


        submit = findViewById(R.id.feedback_submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String feedback_count = content.getText().toString();

                if (feedback_count.length() == 0) {
                    Toast.makeText(player_feedback.this, "内容为空！", Toast.LENGTH_SHORT).show();
                } else {
                    Dialog dialog = new AlertDialog.Builder(player_feedback.this)      //实例化对象
                            .setIcon(R.drawable.info)          //写入图片
                            .setTitle("意见反馈")               //显示标题
                            .setMessage("我们收到您的建议后，会尽快处理！")     //显示内容
                            .create();                          //创建对话框
                    dialog.show();                      //显示对话框

                    SharedPreferences.Editor saved = getSharedPreferences("data", Context.MODE_PRIVATE).edit();
                    saved.putString("info", feedback_count);
                    saved.apply();

                }
            }
        });

    }

}
