package com.example.g40_70.coursedesign.tab_activity;

import android.app.LocalActivityManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;

import com.example.g40_70.coursedesign.R;
import com.example.g40_70.coursedesign.news_activity.news_basic;
import com.example.g40_70.coursedesign.news_activity.news_first;
import com.example.g40_70.coursedesign.news_activity.news_football;
import com.example.g40_70.coursedesign.news_activity.news_train;

import static android.view.Gravity.CENTER;

public class tb_news extends AppCompatActivity {

    private TabHost myTabHost;
    private String[] tags = {"1", "2", "3", "4"};                       //用于添加每一个选项卡的id
    private String[] titles = {"队伍", "训练", "足坛", "基础"};         //所添加选项卡的文本信息
    private Intent[] intents = new Intent[4];                           //用于跳转至不同的Activity
    private int title_count = titles.length;                            //标签个数

    private static final int SWIPE_MIN_DISTANCE = 120;

    private static final int SWIPE_MAX_OFF_PATH = 250;

    private static final int SWIPE_THRESHOLD_VELOCITY = 200;

    private GestureDetector gestureDetector;

    View.OnTouchListener gestureListener;
    //定义切换动画
    private Animation slideLeftIn;
    private Animation slideLeftOut;
    private Animation slideRightIn;
    private Animation slideRightOut;

    int currentView = 0;

    private static int maxTabIndex = 3;

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_layout);

        /** Toolbar组件配置 **/
        toolbar = findViewById(R.id.toolbar_news);
        toolbar.setTitle("");           //设置title为空，去原有标题

        TextView topText = findViewById(R.id.news_title);       //设置标题
        topText.setText("休息");
        topText.setGravity(CENTER);

        //取代原本的actionbar
        setSupportActionBar(toolbar);

        /*设置菜单监听*/
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                return true;
            }
        });


        /** Tabhost组件配置 **/
        myTabHost = (TabHost) findViewById(R.id.tabhost);

        //初始化activity管理者
        LocalActivityManager manager = new LocalActivityManager(this, false);
        //通过管理者保存当前页面状态
        manager.dispatchCreate(savedInstanceState);
        //将管理者类对象添加至TabHost

        myTabHost.setup(manager);                                      //初始化
        init_intent();                                                 //调用方法

        for (int i = 0; i < title_count; i++) {

            //创建选项卡
            TabHost.TabSpec spec = myTabHost.newTabSpec(tags[i]);
            spec.setIndicator(titles[i]);
            //设置每个页面的内容
            spec.setContent(intents[i]);
            //将创建的选项卡添加至tabHost上
            myTabHost.addTab(spec);

        }

        //实现滑动
        myTabHost.setCurrentTab(0);

        slideLeftIn = AnimationUtils.loadAnimation(this, R.anim.slide_left_in);

        slideLeftOut = AnimationUtils.loadAnimation(this, R.anim.slide_left_out);

        slideRightIn = AnimationUtils.loadAnimation(this, R.anim.slide_right_in);

        slideRightOut = AnimationUtils.loadAnimation(this, R.anim.slide_right_out);

        gestureDetector = new GestureDetector(new MyGestureDetector());

        gestureListener = new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {

                if (gestureDetector.onTouchEvent(event)) {
                    return true;
                }
                return false;
            }
        };

    }


    //每个页面放置的Activity
    public void init_intent() {

        intents[0] = new Intent(this, news_first.class);
        intents[1] = new Intent(this, news_train.class);
        intents[2] = new Intent(this, news_football.class);
        intents[3] = new Intent(this, news_basic.class);

    }

    //    实现滑动的方法
    class MyGestureDetector extends GestureDetector.SimpleOnGestureListener {

        @Override

        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

            TabHost tabHost = findViewById(R.id.tabhost);

            try {

                if (Math.abs(e1.getY() - e2.getY()) > SWIPE_MAX_OFF_PATH)

                    return false;

                if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE

                        && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {

                    if (currentView == maxTabIndex) {

                        currentView = 0;

                    } else {

                        currentView++;

                    }

                    tabHost.setCurrentTab(currentView);

                } else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE

                        && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {

                    if (currentView == 0) {

                        currentView = maxTabIndex;

                    } else {

                        currentView--;

                    }

                    tabHost.setCurrentTab(currentView);

                }

            } catch (Exception e) {

                //nothing

            }

            return false;

        }

    }

    @Override

    public boolean dispatchTouchEvent(MotionEvent event) {

        if (gestureDetector.onTouchEvent(event)) {

            event.setAction(MotionEvent.ACTION_CANCEL);

        }

        return super.dispatchTouchEvent(event);

    }

}