package com.example.g40_70.coursedesign.test;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.g40_70.coursedesign.R;

import static android.view.Gravity.CENTER;

/**
 * Created by G40-70 on 2018/11/19.
 * Topbar模板
 * 根据常用调用，重写方法
 */

public class Topbar extends RelativeLayout {

    private Button leftButton,rightButton;
    private TextView tvTitle;

    private String   leftText;
    private int      leftTextColor;
    private Drawable leftBackground;

    private String rightText;
    private int rightTextColor;
    private Drawable rightBackground;

    private float titleTextSize;
    private int   titleTextColor;
    private String title;

    private LayoutParams leftParams,rightParams,titleParams;

    private topbarClickListener listener;

    //定义接口
    public interface topbarClickListener{
        void leftClick();
        void rightClick();
    }

    //定义监听方法
    public void setOnTopbarClickListener(topbarClickListener listener) {
        this.listener = listener;
    }

    //创建属于本模板的setTitle，将获取的title，添加到标题文本框里
    //这里的方法不完善，如果在调用的时候将title设为空，输出将会是空的标题，默认提取布局文件的title
    public void setTitle (CharSequence title) {
        this.tvTitle.setText(title);
    }


    public Topbar(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.Topbar);

        //取出自定义属性值
        leftText = typedArray.getString(R.styleable.Topbar_leftText);
        leftTextColor = typedArray.getColor(R.styleable.Topbar_leftTextColor,0);
        leftBackground = typedArray.getDrawable(R.styleable.Topbar_leftBackground);

        rightText = typedArray.getString(R.styleable.Topbar_rightText);
        rightTextColor = typedArray.getColor(R.styleable.Topbar_rightTextColor,0);
        rightBackground = typedArray.getDrawable(R.styleable.Topbar_rightBackground);

        title = typedArray.getString(R.styleable.Topbar_title);
        titleTextSize = typedArray.getDimension(R.styleable.Topbar_titleTextSize,0);
        titleTextColor = typedArray.getColor(R.styleable.Topbar_titleTextColor,0);

        //回收，避免浪费资源
        typedArray.recycle();

        //自定义（重写）控件
        leftButton = new Button(context);
        rightButton = new Button(context);
        tvTitle = new TextView(context);

        leftButton.setTextColor(leftTextColor);
        leftButton.setBackground(leftBackground);
        leftButton.setText(leftText);

        rightButton.setTextColor(rightTextColor);
        rightButton.setBackground(rightBackground);
        rightButton.setText(rightText);

        tvTitle.setText(title);
        tvTitle.setTextSize(titleTextSize);
        tvTitle.setTextColor(titleTextColor);
        tvTitle.setGravity(CENTER);

        setBackgroundColor(0xFF6a7cdd);

        //传入布局数据
        leftParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        leftParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT,TRUE);
        //加入View
        addView(leftButton,leftParams);

        rightParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        rightParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT,TRUE);

        addView(rightButton,rightParams);

        titleParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        titleParams.addRule(RelativeLayout.CENTER_IN_PARENT,TRUE);

        addView(tvTitle,titleParams);

        //定义左键监听
        leftButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.leftClick();
            }
        });

        //定义右键监听
        rightButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.rightClick();
            }
        });



    }
}
