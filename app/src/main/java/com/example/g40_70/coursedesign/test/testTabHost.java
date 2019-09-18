package com.example.g40_70.coursedesign.test;

import android.app.LocalActivityManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.example.g40_70.coursedesign.R;
import com.example.g40_70.coursedesign.tab_activity.tb_home;
import com.example.g40_70.coursedesign.tab_activity.tb_news;
import com.example.g40_70.coursedesign.tab_activity.tb_player;
import com.example.g40_70.coursedesign.tab_activity.tb_team;

import static android.view.Gravity.CENTER;

public class testTabHost extends AppCompatActivity {

    private TabHost myTabHost;
    //用于添加每一个选项卡的id
    private String[] tags = {"1","2","3","4"};
    //所添加选项卡的文本信息
    private String[] titles = {"新闻", "图片", "视频", "收藏"};
    //所添加选项卡的图片信息
    private int[] images = {R.drawable.first, R.drawable.info,
                            R.drawable.share, R.drawable.back};

    //用于跳转至不同的Activity
    private Intent[] intents = new Intent[4];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_tab);

        /** Toolbar组件配置 **/
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");           //设置title为空，去原有标题

        TextView top = findViewById(R.id.toolbar_title1);
        top.setText("提升");
        top.setGravity(CENTER);

        setSupportActionBar(toolbar);


        test(savedInstanceState);
        //这个左上角图标要放在setSupportActionBar(toolbar);后面
        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

    }

    private void test(Bundle savedInstanceState) {


        /** Tabhost组件配置 **/
        myTabHost = (TabHost) findViewById(R.id.tabHost);
//        TabWidget tabWidget = (TabWidget) findViewById(android.R.id.tabs);
//        tabWidget.setBackgroundColor(Color.WHITE);

        //初始化activity管理者
        LocalActivityManager manager = new LocalActivityManager(this, false);
        //通过管理者保存当前页面状态
        manager.dispatchCreate(savedInstanceState);
        //将管理者类对象添加至TabHost

        myTabHost.setup(manager);                                      //初始化
        init_intent();

        for (int i = 0; i < 4; i++) {

            //加载底部导航栏布局
            LayoutInflater inflater = this.getLayoutInflater();
            View view = inflater.inflate(R.layout.mytabhost, null);
            TextView textView = (TextView) view.findViewById(R.id.title);
            ImageView imageView = (ImageView) view.findViewById(R.id.icon);
            textView.setText(titles[i]);
            imageView.setImageResource(images[i]);
            //创建选项卡
            TabHost.TabSpec spec = myTabHost.newTabSpec(tags[i]);
            spec.setIndicator(view);
            //设置每个页面的内容
            spec.setContent(intents[i]);
            //将创建的选项卡添加至tabHost上
            myTabHost.addTab(spec);

        }


        /**
         * 添加tab
         * */
//        for (int i = 0; i < 4; i++) {
//            View view = LayoutInflater.from(this).inflate(R.layout.mytabhost, null, false);
//            TextView textView = (TextView) view.findViewById(R.id.title);
//            ImageView imageView = (ImageView) view.findViewById(R.id.icon);
//
//            switch (i) {
//                case 0:
//                    textView.setText("微信");
//                    imageView.setImageResource(R.drawable.first);
//                    myTabHost.addTab(myTabHost.newTabSpec("1").setIndicator(view).setContent(R.id.file));
//                    break;
//                case 1:
//                    textView.setText("通讯录");
//                    imageView.setImageResource(R.drawable.tb_team);
//                    myTabHost.addTab(myTabHost.newTabSpec("2").setIndicator(view).setContent(R.id.edit));
//                    break;
//                case 2:
//                    textView.setText("发现");
//                    imageView.setImageResource(R.drawable.first);
//                    myTabHost.addTab(myTabHost.newTabSpec("3").setIndicator(view).setContent(R.id.seek));
//                    break;
//                case 3:
//                    textView.setText("我的");
//                    imageView.setImageResource(R.drawable.time);
//                    myTabHost.addTab(myTabHost.newTabSpec("4").setIndicator(view).setContent(R.id.time));
//                    break;
//            }
//
//        }

//        /*设置标签切换监听器*/
//        myTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
//            @Override
//            public void onTabChanged(String tabId) {
//                for (int i = 0; i < 4; i++) {//颜色全部重置
//                    ((TextView) myTabHost.getTabWidget().getChildTabViewAt(i).findViewById(R.id.title))
//                            .setTextColor(getResources().getColor(R.color.colorAccent));
//                }
//                if (myTabHost.getCurrentTabTag() == tabId) {
//                    ((TextView) myTabHost.getCurrentTabView().findViewById(R.id.title))
//                            .setTextColor(getResources().getColor(R.color.colorPrimary));
//                }//选中的那个Tab文字颜色修改
//            }
//        });
//        ((TextView) myTabHost.getCurrentTabView().findViewById(R.id.title))
//                .setTextColor(getResources().getColor(R.color.colorPrimary));//第一次进入的时候讲选中的Tab修改文字颜色
//


    }

    //每个页面放置的Activity
    public void init_intent() {

        intents[0] = new Intent(this, tb_home.class);
        intents[1] = new Intent(this, tb_news.class);
        intents[2] = new Intent(this, tb_player.class);
        intents[3] = new Intent(this, tb_team.class);

    }

}
