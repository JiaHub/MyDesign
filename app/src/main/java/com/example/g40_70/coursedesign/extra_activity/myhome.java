package com.example.g40_70.coursedesign.extra_activity;

import android.app.LocalActivityManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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


/**
 * Created by G40-70 on 2018/11/15.
 * 建议以后觉得代码没错，就新建一个class，有毛病估计也没了
 * xml: Error: 'T'不是一个有效的基于文件的资源名字符:基于文件的资源名必须只包含小写字母a-z、0-9或下划线
 * 文件命名不要与变量同名，不然改文件名的时候很麻烦
 * 数组里的字符串，不要转换了放进去，会报错的。
 */

public class myhome extends AppCompatActivity {

    private TabHost myTabHost;
    //用于添加每一个选项卡的id
    private String[] tags = {"1", "2", "3", "4"};
    //所添加选项卡的文本信息
    private String[] titles = {"首页", "学习", "休息", "球员"};
    //所添加选项卡的图片信息
    private int[] images = {R.mipmap.first, R.mipmap.tb_team,
            R.mipmap.news, R.mipmap.players};
    //用于跳转至不同的Activity
    private Intent[] intents = new Intent[4];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first);


        /** Tabhost组件配置 **/
        myTabHost = (TabHost) findViewById(R.id.tabHost);

        //初始化activity管理者
        LocalActivityManager manager = new LocalActivityManager(this, false);
        //通过管理者保存当前页面状态
        manager.dispatchCreate(savedInstanceState);
        //将管理者类对象添加至TabHost

        myTabHost.setup(manager);                                      //初始化
        init_intent();

        /*添加tab*/
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

        /*设置标签切换监听器*/
        myTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                for (int i = 0; i < 4; i++) {//颜色全部重置
                    ((TextView) myTabHost.getTabWidget().getChildTabViewAt(i).findViewById(R.id.title))
                            .setTextColor(getResources().getColor(R.color.colorText));
                }
                if (myTabHost.getCurrentTabTag() == tabId) {
                    ((TextView) myTabHost.getCurrentTabView().findViewById(R.id.title))
                            .setTextColor(getResources().getColor(R.color.colorPrimary));
                }//选中的那个Tab文字颜色修改
            }
        });

    }

    //每个页面放置的Activity
    public void init_intent() {

        intents[0] = new Intent(this, tb_home.class);
        intents[1] = new Intent(this, tb_team.class);
        intents[2] = new Intent(this, tb_news.class);
        intents[3] = new Intent(this, tb_player.class);

    }

}