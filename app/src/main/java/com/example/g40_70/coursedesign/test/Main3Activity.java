package com.example.g40_70.coursedesign.test;

import android.app.LocalActivityManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TabHost;
import android.widget.TextView;

import com.example.g40_70.coursedesign.R;
import com.example.g40_70.coursedesign.tab_activity.tb_home;
import com.example.g40_70.coursedesign.tab_activity.tb_news;
import com.example.g40_70.coursedesign.tab_activity.tb_player;
import com.example.g40_70.coursedesign.tab_activity.tb_team;

public class Main3Activity extends AppCompatActivity {

    private TabHost myTabHost;
    //用于添加每一个选项卡的id
    private String[] tags = {"1", "2", "3", "4"};
    //所添加选项卡的文本信息

    /**
     * 下面四行代码，不要用在字符串数组中。
     * String title = getResources().getString(R.string.tb_title_home);
     * String b = getResources().getString(R.string.tb_title_team);
     * String c = getResources().getString(R.string.tb_title_rest);
     * String d = getResources().getString(R.string.tb_title_player);
     */

    private String[] titles = {"首页", "队伍", "休息", "球员"};
    //所添加选项卡的图片信息
    private int[] images = {R.mipmap.first, R.mipmap.tb_team,
            R.mipmap.news, R.mipmap.players};
    //用于跳转至不同的Activity
    private Intent[] intents = new Intent[4];

    Toolbar toolbar;
    PopupWindow mPopupWindow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first);

//        /** Toolbar组件配置 **/
//        toolbar = findViewById(R.id.toolbar);
//        toolbar.setTitle("");           //设置title为空，去原有标题
//
//        TextView topText = findViewById(R.id.toolbar_title);       //设置标题
//        topText.setText("提升");
//        topText.setGravity(CENTER);
//
//        //取代原本的actionbar
//        setSupportActionBar(toolbar);
//
//        //这个左上角图标要放在setSupportActionBar(toolbar);后面
//        toolbar.setNavigationIcon(R.drawable.back);
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                Toast.makeText(myhome.this, "返回", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        //设置菜单监听
//        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem menuItem) {
//
//                switch (menuItem.getItemId()) {
//                    case R.id.action_overflow:
////                        Toast.makeText(myhome.this, "菜单", Toast.LENGTH_SHORT).show();
//                        popUpMyOverflow();
//                        return true;
//                    case R.id.action_share:
////                        Toast.makeText(myhome.this, "分享", Toast.LENGTH_SHORT).show();
//                        break;
//                }
//                return true;
//            }
//        });

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
        myTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener()

        {
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
//        ((TextView) myTabHost.getCurrentTabView().findViewById(R.id.title))
//                .setTextColor(getResources().getColor(R.color.colorPrimary));//第一次进入的时候将选中的Tab修改文字颜色

    }

    /**
     * 添加菜单
     */
//    public boolean onCreateOptionsMenu(Menu menu) {
//        super.onCreateOptionsMenu(menu);
//        getMenuInflater().inflate(R.menu.mypopmenu, menu);
//        return true;
//    }

    /**
     * 弹出自定义的popWindow
     */
//    public void popUpMyOverflow() {
//        //获取状态栏高度
//        Rect frame = new Rect();
//        getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
//        //状态栏高度+toolbar的高度
//        int yOffset = frame.top + toolbar.getHeight();
//        if (null == mPopupWindow) {
//            //初始化PopupWindow的布局
//            View popView = getLayoutInflater().inflate(R.layout.rightmenu, null);
//            //popView即popupWindow的布局，ture设置focusAble.
//            mPopupWindow = new PopupWindow(popView,
//                    ViewGroup.LayoutParams.WRAP_CONTENT,
//                    ViewGroup.LayoutParams.WRAP_CONTENT, true);
//            //必须设置BackgroundDrawable后setOutsideTouchable(true)才会有效
//            mPopupWindow.setBackgroundDrawable(new ColorDrawable());
//            //点击外部关闭。
//            mPopupWindow.setOutsideTouchable(true);
//            //设置一个动画。
//            mPopupWindow.setAnimationStyle(android.R.style.Animation_Dialog);
//            //设置Gravity，让它显示在右上角。
//            mPopupWindow.showAtLocation(toolbar, Gravity.RIGHT | Gravity.TOP, 10, yOffset);
//            //设置item的点击监听
//            popView.findViewById(R.id.list_item1).setOnClickListener(new menuItemListener());
//            popView.findViewById(R.id.list_item2).setOnClickListener(new menuItemListener());
//            popView.findViewById(R.id.list_item3).setOnClickListener(new menuItemListener());
//        } else {
//            mPopupWindow.showAtLocation(toolbar, Gravity.RIGHT | Gravity.TOP, 10, yOffset);
//        }

//    }

//    public class menuItemListener implements View.OnClickListener {
//
//        @Override
//        public void onClick(View v) {
//            switch (v.getId()) {
//                case R.id.list_item1:
//                    Toast.makeText(myhome.this, "文本", Toast.LENGTH_SHORT).show();
//                    break;
//                case R.id.list_item2:
//                    Toast.makeText(myhome.this, "编辑", Toast.LENGTH_SHORT).show();
//                    break;
//                case R.id.list_item3:
//                    Toast.makeText(myhome.this, "添加", Toast.LENGTH_SHORT).show();
//                    break;
//            }
//        }
//    }

    //每个页面放置的Activity
    public void init_intent() {

        intents[0] = new Intent(this, tb_home.class);
        intents[1] = new Intent(this, tb_team.class);
        intents[2] = new Intent(this, tb_news.class);
        intents[3] = new Intent(this, tb_player.class);

    }


}