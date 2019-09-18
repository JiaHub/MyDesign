package com.example.g40_70.coursedesign.tab_activity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.g40_70.coursedesign.R;
import com.example.g40_70.coursedesign.player_activity.player_config;
import com.example.g40_70.coursedesign.player_activity.player_feedback;

import java.util.Timer;
import java.util.TimerTask;

import static android.view.Gravity.CENTER;

public class tb_team extends AppCompatActivity {

    private Toolbar toolbar;
    private EditText zai_lu;

    private LayoutInflater inflater = null;
    PopupWindow menuWindow;

    private WebView webView = null;

    private TextView webname = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.team_layout);
        inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);         //不能少，少了popWindow用不了

        /** Toolbar组件配置 **/
        toolbar = findViewById(R.id.toolbar_team);
        toolbar.setTitle("");           //设置title为空，去原有标题

        TextView topText = findViewById(R.id.team_title);       //设置标题
        topText.setText("学习");
        topText.setGravity(CENTER);

        //取代原本的actionbar
        setSupportActionBar(toolbar);

        //这个左上角图标要放在setSupportActionBar(toolbar);后面
        toolbar.setNavigationIcon(R.mipmap.refresh);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (tb_team.this.webView.canGoBack()) {        //可以后退
                    tb_team.this.webView.goBack();
                } else {
                    Toast.makeText(tb_team.this, "没有上一页！", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //设置菜单监听
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                return true;
            }
        });

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        webView = findViewById(R.id.team_wb);

        this.webView.getSettings().setJavaScriptEnabled(true); //启用javascript
        this.webView.getSettings().setBuiltInZoomControls(true);    //控制页面缩放

        webView.setWebViewClient(new WebViewClient());
        this.webView.loadUrl("https://www.csdn.net/");

        //摘录
        Button zai = findViewById(R.id.excerpts_btn);
        zai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopwindow(getEditText());
            }
        });

        //清空
        Button delete = findViewById(R.id.delete_btn);
        delete.setOnClickListener(new deleteClickListener());

//        右上角文本框
        webname = findViewById(R.id.web_name);
        webname.setOnClickListener(new nameClickListener());
        webname.setText("脚本之家");

    }

    /**
     * 初始化popupWindow
     *
     * @param view
     */
    private void showPopwindow(View view) {
        menuWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        menuWindow.setFocusable(true);
        menuWindow.setBackgroundDrawable(new BitmapDrawable());
        menuWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
        menuWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                menuWindow = null;
            }
        });
    }

    private View getEditText() {
        View view = inflater.inflate(R.layout.xue_edit_layout, null);
        final EditText editText = (EditText) view.findViewById(R.id.editText);
        InputMethodManager manager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        manager.toggleSoftInput(0, InputMethodManager.HIDE_IMPLICIT_ONLY);
        Button btn_ok = (Button) view.findViewById(R.id.btn_ok);
        Button btn_cancel = (Button) view.findViewById(R.id.btn_cancel);
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                zai_lu = findViewById(R.id.excerpts);
                String content = editText.getText().toString();
                zai_lu.append(content);                                //append是补充；
                menuWindow.dismiss();
            }
        });
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menuWindow.dismiss();
            }
        });
        return view;
    }

    //清空事件
    private class deleteClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            zai_lu = findViewById(R.id.excerpts);
            String content = zai_lu.getText().toString();
            if (content.length() == 0) {
                Toast.makeText(tb_team.this, "没有内容", Toast.LENGTH_SHORT).show();
            } else {
                Dialog dialog = new AlertDialog.Builder(tb_team.this)      //实例化对象
                        .setIcon(R.mipmap.laert)          //写入图片
                        .setTitle("清空摘录")               //显示标题
                        .setMessage("确定一键清空摘录的所有内容？")     //显示内容
                        .setPositiveButton("确定",                          //添加确定按钮
                                new DialogInterface.OnClickListener() {      //设置监听
                                    public void onClick(DialogInterface dialog, int whichButton) {
//                                            Toast.makeText(tb_team.this, "对不起，没有权限！", Toast.LENGTH_SHORT).show();
//                                            Toast.makeText(tb_team.this, "不不不，开个玩笑！", Toast.LENGTH_SHORT).show();
                                        zai_lu.setText("");
                                    } //单击事件
                                })
                        .setNegativeButton("取消",                            //添加取消按钮
                                new DialogInterface.OnClickListener() {      //设置监听
                                    public void onClick(DialogInterface dialog, int whichButton) {
                                    }  //单击事件
                                })
                        .create();                          //创建对话框
                dialog.show();                      //显示对话框
            }
        }
    }

    //右上角文本点击事件
    private class nameClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {

            String name = webname.getText().toString();
            final String CSDN = "CSDN";
            final String JB51 = "脚本之家";

            if (name.equals(JB51)) {
                webname.setText(CSDN);
                webView.setWebViewClient(new WebViewClient());
                webView.loadUrl("https://m.jb51.net/list/index_1.htm");
            }else if (name.equals(CSDN)){
                webname.setText(JB51);
                webView.setWebViewClient(new WebViewClient());
                webView.loadUrl("https://www.csdn.net/");
            }

        }
    }
}