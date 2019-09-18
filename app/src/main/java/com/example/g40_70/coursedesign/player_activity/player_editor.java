package com.example.g40_70.coursedesign.player_activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.g40_70.coursedesign.R;
import com.example.g40_70.coursedesign.adapter.ArrayWheelAdapter;
import com.example.g40_70.coursedesign.adapter.NumericWheelAdapter;
import com.example.g40_70.coursedesign.extra_activity.CustomRoundView;
import com.example.g40_70.coursedesign.extra_activity.Db_lite;
import com.example.g40_70.coursedesign.sql_lite.PlayDao;
import com.example.g40_70.coursedesign.sql_lite.Player;
import com.example.g40_70.coursedesign.widget.WheelView;

import java.util.Calendar;
import java.util.Locale;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

public class player_editor extends AppCompatActivity {

    private CustomRoundView mRoundView;

    private LinearLayout l_number, l_site, l_age, l_height, l_weight;

    //内容
    private TextView t_number, t_site, t_age, t_height, t_weight;

    private RelativeLayout r_photo;

    private Button sex_man_btn, sex_woman_btn, left_foot_btn, right_foot_btn;

    private Button title_save, end_save;
    private ImageButton image_btn;

    private WheelView polo_number;
    private WheelView play_site;
    private WheelView play_age;
    private WheelView play_height;
    private WheelView play_weight;

    private PlayDao playDao;
    private final String TAG = "player_editor";

    private String check_sex = "男", check_foot = "右脚";

    private final String site_of_football[] = {
            "门将", "中后卫", "边后卫（左）", "边后卫（右）",
            "中场", "防守型中场", "中场（左）", "中场（右）",
            "进攻型中场", "边锋", "左边锋", "右边锋", "前锋"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_editor);

        //图片
        mRoundView = findViewById(R.id.round_picture);
        mRoundView.setImageResource(R.drawable.site);

        //标题栏返回
        ImageButton back = findViewById(R.id.player_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //获取嵌入布局
        l_number = findViewById(R.id.play_info_number);
        l_site = findViewById(R.id.play_info_site);
        l_age = findViewById(R.id.play_info_age);
        l_height = findViewById(R.id.play_info_height);
        l_weight = findViewById(R.id.play_info_weight);
        r_photo = findViewById(R.id.play_info_picture);

        image_btn = findViewById(R.id.camera);

        //内容
        t_number = findViewById(R.id.p_number);
        t_site = findViewById(R.id.p_site);
        t_age = findViewById(R.id.p_age);
        t_height = findViewById(R.id.p_height);
        t_weight = findViewById(R.id.p_weight);

        //嵌入布局监听
        l_number.setOnClickListener(new OnClickListenernumber());
        l_site.setOnClickListener(new OnClickListenersite());
        l_age.setOnClickListener(new OnClickListenerage());
        l_height.setOnClickListener(new OnClickListenerheight());
        l_weight.setOnClickListener(new OnClickListenerweight());
        r_photo.setOnClickListener(new OnClickListenerpicture());
        image_btn.setOnClickListener(new OnClickListenerpicture());

        //获取选择组件
        sex_man_btn = findViewById(R.id.sex_man);
        sex_woman_btn = findViewById(R.id.sex_woman);
        left_foot_btn = findViewById(R.id.left_foot);
        right_foot_btn = findViewById(R.id.right_foot);


        //注册监听
        sex_man_btn.setOnClickListener(new OnClickListenersex());
        sex_woman_btn.setOnClickListener(new OnClickListenersex());
        left_foot_btn.setOnClickListener(new OnClickListenerfoot());
        right_foot_btn.setOnClickListener(new OnClickListenerfoot());


        title_save = findViewById(R.id.button);             //标题栏保存
        end_save = findViewById(R.id.info_save);            //末尾保存

        //保存监听
        title_save.setOnClickListener(new OnClickListenersave());
        end_save.setOnClickListener(new OnClickListenersave());

        //数据初始化
        EditText Polo_name = findViewById(R.id.name);
        SharedPreferences data1 = getSharedPreferences("data", Context.MODE_PRIVATE);
        String name = data1.getString("name", String.valueOf(R.string.pre_polo_name));
        Polo_name.setText(name);

    }

    //嵌入布局监听
    private class OnClickListenernumber implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            showPolo_number_Dialog();
        }
    }

    private class OnClickListenersite implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            showPlay_site_Dialog();
        }
    }

    private class OnClickListenerage implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            showPlay_age_Dialog();
        }
    }

    private class OnClickListenerheight implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            showPlay_height_Dialog();
        }
    }

    private class OnClickListenerweight implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            showPlay_weight_Dialog();
        }
    }

    //选择响应
    private class OnClickListenersex implements View.OnClickListener {
        @Override
        public void onClick(View v) {

            switch (v.getId()) {
                case R.id.sex_man:
                    sex_man_btn.setBackground(getDrawable(R.drawable.checked_left));
                    sex_woman_btn.setBackground(getDrawable(R.drawable.check_right));
                    check_sex = "男";
                    break;
                case R.id.sex_woman:
                    sex_woman_btn.setBackground(getDrawable(R.drawable.checked_right));
                    sex_man_btn.setBackground(getDrawable(R.drawable.check_left));
                    check_sex = "女";
                    break;
            }

        }
    }

    private class OnClickListenerfoot implements View.OnClickListener {
        @Override
        public void onClick(View v) {

            switch (v.getId()) {
                case R.id.left_foot:
                    left_foot_btn.setBackground(getDrawable(R.drawable.checked_left));
                    right_foot_btn.setBackground(getDrawable(R.drawable.check_right));
                    check_foot = "左脚";
                    break;
                case R.id.right_foot:
                    right_foot_btn.setBackground(getDrawable(R.drawable.checked_right));
                    left_foot_btn.setBackground(getDrawable(R.drawable.check_left));
                    check_foot = "右脚";
                    break;
            }

        }
    }

    //头像背景
    private class OnClickListenerpicture implements View.OnClickListener {
        @Override
        public void onClick(View v) {

            final CharSequence[] items = {"打开相册", "打开相机"};

            //实例化对象
            AlertDialog.Builder builder = new AlertDialog.Builder(player_editor.this);

            builder.setItems(items, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    switch (which) {

                        case (0):
                            Toast.makeText(player_editor.this, "打开相册", Toast.LENGTH_SHORT).show();
                            break;
                        case (1):
                            Toast.makeText(player_editor.this, "打开相机", Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
            });
            AlertDialog alert = builder.create();
            alert.show();

        }
    }

    //滚动选择器

    /**
     * 初始化球衣号码
     */
    private void initPolo_number() {
        NumericWheelAdapter numericWheelAdapter = new NumericWheelAdapter(this, 1, 99, "%02d");
        numericWheelAdapter.setLabel(" 号");
//		numericWheelAdapter.setTextSize(15);  设置字体大小
        polo_number.setViewAdapter(numericWheelAdapter);
        polo_number.setCyclic(true);
    }

    /**
     * 初始化场上位置
     * 定义数组
     */
    private void initPlay_site() {
        ArrayWheelAdapter arrayWheelAdapter = new ArrayWheelAdapter(this, site_of_football);
        play_site.setViewAdapter(arrayWheelAdapter);
        play_site.setCyclic(true);
    }

    /**
     * 初始化年龄
     */
    private void initPlay_age() {
        NumericWheelAdapter numericWheelAdapter = new NumericWheelAdapter(this, 1, 99, "%2d");
        numericWheelAdapter.setLabel(" 岁");
//		numericWheelAdapter.setTextSize(15);  设置字体大小
        play_age.setViewAdapter(numericWheelAdapter);
        play_age.setCyclic(true);
    }

    /**
     * 初始化身高
     */
    private void initPlay_height() {
        NumericWheelAdapter numericWheelAdapter = new NumericWheelAdapter(this, 100, 250, "%2d");
        numericWheelAdapter.setLabel(" cm");
//		numericWheelAdapter.setTextSize(15);  设置字体大小
        play_height.setViewAdapter(numericWheelAdapter);
        play_height.setCyclic(true);
    }

    /**
     * 初始化体重
     */
    private void initPlay_weight() {
        NumericWheelAdapter numericWheelAdapter = new NumericWheelAdapter(this, 20, 200, "%2d");
        numericWheelAdapter.setLabel(" kg");
//		numericWheelAdapter.setTextSize(15);  设置字体大小
        play_weight.setViewAdapter(numericWheelAdapter);
        play_weight.setCyclic(true);
    }

    /**
     * 显示球衣号码
     */
    public void showPolo_number_Dialog() {
        final android.app.AlertDialog dialog = new android.app.AlertDialog.Builder(this).create();
        dialog.show();
        Window window = dialog.getWindow();
        // 设置布局
        window.setContentView(R.layout.activity_wheel_select);
        // 设置宽高
        window.setLayout(MATCH_PARENT, WRAP_CONTENT);
        // 设置弹出的动画效果
        window.setWindowAnimations(R.style.AnimBottom);

        Calendar c = Calendar.getInstance();
        int curMin = c.get(Calendar.MINUTE);

        polo_number = (WheelView) window.findViewById(R.id.wheel);
        initPolo_number();

        // 设置当前参数
        String number = t_number.getText().toString();
        if (number.length() <= 0) {
            polo_number.setCurrentItem(curMin);
        } else {
//            number = number.substring(1, number.length());
//            int number1 = Integer.parseInt(number);
            int number1 = polo_number.getCurrentItem();
            polo_number.setCurrentItem(number1);
        }

        polo_number.setVisibleItems(7);

        // 设置监听
        Button ok = (Button) window.findViewById(R.id.set);
        Button cancel = (Button) window.findViewById(R.id.cancel);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                String str = String.format(Locale.CHINA, "%2d", polo_number.getCurrentItem() + 1) + "号";
                t_number.setText(str);
                dialog.cancel();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                dialog.cancel();
            }
        });
        LinearLayout cancelLayout = (LinearLayout) window.findViewById(R.id.view_none);
        cancelLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                dialog.cancel();
                return false;
            }
        });
    }

    /**
     * 显示场上位置
     */
    public void showPlay_site_Dialog() {
        final android.app.AlertDialog dialog = new android.app.AlertDialog.Builder(this).create();
        dialog.show();
        Window window = dialog.getWindow();
        // 设置布局
        window.setContentView(R.layout.activity_wheel_select);
        // 设置宽高
        window.setLayout(MATCH_PARENT, WRAP_CONTENT);
//        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        // 设置弹出的动画效果
        window.setWindowAnimations(R.style.AnimBottom);


        play_site = (WheelView) window.findViewById(R.id.wheel);
        initPlay_site();
        // 设置当前参数
        String site = t_site.getText().toString();
        if (site.length() <= 0) {
            play_site.setCurrentItem(0);
        } else {
            int number1 = play_site.getCurrentItem();
            play_site.setCurrentItem(number1);
        }


        play_site.setVisibleItems(7);

        // 设置监听
        Button ok = (Button) window.findViewById(R.id.set);
        Button cancel = (Button) window.findViewById(R.id.cancel);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                int number1 = play_site.getCurrentItem();
                String site_t = site_of_football[number1];
                t_site.setText(site_t);
                dialog.cancel();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                dialog.cancel();
            }
        });
        LinearLayout cancelLayout = (LinearLayout) window.findViewById(R.id.view_none);
        cancelLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                dialog.cancel();
                return false;
            }
        });
    }

    /**
     * 显示球员年龄
     */
    public void showPlay_age_Dialog() {
        final android.app.AlertDialog dialog = new android.app.AlertDialog.Builder(this).create();
        dialog.show();
        Window window = dialog.getWindow();
        // 设置布局
        window.setContentView(R.layout.activity_wheel_select);
        // 设置宽高
        window.setLayout(MATCH_PARENT, WRAP_CONTENT);
//        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        // 设置弹出的动画效果
        window.setWindowAnimations(R.style.AnimBottom);


        play_age = (WheelView) window.findViewById(R.id.wheel);
        initPlay_age();

        String age = t_age.getText().toString();
        if (age.length() <= 0) {
            play_age.setCurrentItem(17);
        } else {
            int number1 = play_age.getCurrentItem();
            play_age.setCurrentItem(number1);
        }

        play_age.setVisibleItems(7);

        // 设置监听
        Button ok = (Button) window.findViewById(R.id.set);
        Button cancel = (Button) window.findViewById(R.id.cancel);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                String str = String.format(Locale.CHINA, "%2d", play_age.getCurrentItem() + 1) + "岁";
                t_age.setText(str);
                dialog.cancel();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                dialog.cancel();
            }
        });
        LinearLayout cancelLayout = (LinearLayout) window.findViewById(R.id.view_none);
        cancelLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                dialog.cancel();
                return false;
            }
        });
    }

    /**
     * 显示球员身高
     */
    public void showPlay_height_Dialog() {
        final android.app.AlertDialog dialog = new android.app.AlertDialog.Builder(this).create();
        dialog.show();
        Window window = dialog.getWindow();
        // 设置布局
        window.setContentView(R.layout.activity_wheel_select);
        // 设置宽高
        window.setLayout(MATCH_PARENT, WRAP_CONTENT);
//        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        // 设置弹出的动画效果
        window.setWindowAnimations(R.style.AnimBottom);

        play_height = (WheelView) window.findViewById(R.id.wheel);
        initPlay_height();
        // 设置当前参数
        String height = t_height.getText().toString();
        if (height.length() <= 0) {
            play_height.setCurrentItem(80);
        } else {
            int number1 = play_height.getCurrentItem();
            play_height.setCurrentItem(number1);
        }

        play_height.setVisibleItems(7);

        // 设置监听
        Button ok = (Button) window.findViewById(R.id.set);
        Button cancel = (Button) window.findViewById(R.id.cancel);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                String height_t = String.format(Locale.CHINA, "%2d", play_height.getCurrentItem() + 100) + "cm";
                t_height.setText(height_t);
                dialog.cancel();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                dialog.cancel();
            }
        });
        LinearLayout cancelLayout = (LinearLayout) window.findViewById(R.id.view_none);
        cancelLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                dialog.cancel();
                return false;
            }
        });
    }

    /**
     * 显示球员体重
     */
    public void showPlay_weight_Dialog() {
        final android.app.AlertDialog dialog = new android.app.AlertDialog.Builder(this).create();
        dialog.show();
        Window window = dialog.getWindow();
        // 设置布局
        window.setContentView(R.layout.activity_wheel_select);
        // 设置宽高
        window.setLayout(MATCH_PARENT, WRAP_CONTENT);
//        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        // 设置弹出的动画效果
        window.setWindowAnimations(R.style.AnimBottom);

        Calendar c = Calendar.getInstance();
        int curMin = c.get(Calendar.MINUTE);

        play_weight = (WheelView) window.findViewById(R.id.wheel);
        initPlay_weight();
        // 设置当前参数
        String weight = t_weight.getText().toString();
        if (weight.length() <= 0) {
            play_weight.setCurrentItem(50);
        } else {
            int number1 = play_weight.getCurrentItem();
            play_weight.setCurrentItem(number1);
        }


        play_weight.setVisibleItems(7);

        // 设置监听
        Button ok = (Button) window.findViewById(R.id.set);
        Button cancel = (Button) window.findViewById(R.id.cancel);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                String weight = String.format(Locale.CHINA, "%2d", play_weight.getCurrentItem() + 20) + "Kg";
                t_weight.setText(weight);
                dialog.cancel();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                dialog.cancel();
            }
        });
        LinearLayout cancelLayout = (LinearLayout) window.findViewById(R.id.view_none);
        cancelLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                dialog.cancel();
                return false;
            }
        });
    }

    //保存监听响应
    private class OnClickListenersave implements View.OnClickListener {
        @Override
        public void onClick(View v) {

            //数据存储到文件
            SharedPreferences.Editor saved = getSharedPreferences("data", Context.MODE_PRIVATE).edit();
            saved.putString("age", t_age.getText().toString());
            saved.putString("site", t_site.getText().toString());
            saved.putString("height", t_height.getText().toString());
            saved.putString("weight", t_weight.getText().toString());
            saved.putString("number", t_number.getText().toString());

            saved.putString("sex", check_sex);
            saved.putString("foot", check_foot);

            EditText name = findViewById(R.id.name);
            saved.putString("name", name.getText().toString());

            saved.apply();

            String str = t_age.getText().toString() + t_site.getText().toString() + t_height.getText().toString() + check_sex +
                    t_weight.getText().toString() + t_number.getText().toString() + check_foot + name.getText().toString();
            Log.i("player_editor", "存储的数据" + str);


            //存储到数据库
            playDao = new PlayDao(player_editor.this);
            String in_name = name.getText().toString();
            Player player1 = playDao.queryPlayer(in_name);
            if (!in_name.isEmpty()) {            //用户名不为空

                if (player1 == null) {       //且不存在该用户名

                    Player player = new Player();
                    player.setName(name.getText().toString());
                    player.setAge(t_age.getText().toString());
                    player.setSex(check_sex);
                    player.setSite(t_site.getText().toString());
                    player.setFoot(check_foot);
                    player.setHeight(t_height.getText().toString());
                    player.setWeight(t_weight.getText().toString());
                    player.setNumber(t_number.getText().toString());

                    playDao.insert(player);
                    Log.w(TAG, "添加用户" + name.getText().toString());
                } else {

                    Player player = playDao.queryPlayer(in_name);
                    player.setName(name.getText().toString());
                    player.setAge(t_age.getText().toString());
                    player.setSex(check_sex);
                    player.setSite(t_site.getText().toString());
                    player.setFoot(check_foot);
                    player.setHeight(t_height.getText().toString());
                    player.setWeight(t_weight.getText().toString());
                    player.setNumber(t_number.getText().toString());
                    playDao.update(player);

                    Log.w(TAG, "修改成功" + name.getText().toString());



                }

                Toast.makeText(player_editor.this, "资料已更新！", Toast.LENGTH_SHORT).show();
                finish();

            } else {
                Toast.makeText(player_editor.this, "请输入你的大名！", Toast.LENGTH_SHORT).show();
            }

            /**
             *
             * apply没有返回值而commit返回boolean表明修改是否提交成功
             * 调用apply的函数的将会直接覆盖前面的内存数据，这样从一定程度上提高了很多效率。
             *有的可能在其他activity里获取不到值，检查有没有忘记commit，或者改下Context.MODE_PRIVATE 存储模式。
             *如果对提交的结果不关心的话，建议使用apply，当然需要确保提交成功且有后续操作的话，还是需要用commit的。
             *
             */

//获取数据
//            SharedPreferences show = getSharedPreferences("data",MODE_PRIVATE);
//            String a = show.getString("name","");
//            Toast.makeText(player_editor.this, a, Toast.LENGTH_SHORT).show();

        }
    }

}
