package com.example.g40_70.coursedesign.news_activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.g40_70.coursedesign.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class news_first extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView first_lv;

    final int[] pictures = new int[]{

            R.drawable.china, R.drawable.deguo, R.drawable.basa,
            R.drawable.bali, R.drawable.laisite, R.drawable.guomi,
            R.drawable.manlian, R.drawable.qieerxi, R.drawable.bairen
    };

    final String[] titles = new String[]{

            "中国", "德国", "巴萨",
            "巴黎", "莱斯特", "国米",
            "曼联", "切尔西", "拜仁",
    };
    final String[] contents = new String[]{
            "中国国家足球队前身是1949年以前的中华民国男子足球队（简称“中华队”），最早于1913年创建，主要代表中华民国参加远东运动会和奥运会足球赛。中国队的首次国际比赛是在1913年，而后在1913年到1934年间中国队连续参加的10届远东运动会足球比赛中曾九夺冠军。1924年中国足协成立并于1931年加入国际足球联合会（FIFA）。民国时期的中国队在远东地区鲜有敌手，在1934年远东运动会中中国队更是2:0击败了世界杯参赛队荷属东印度（今印尼）队，但由于国内局势不稳定和缺少经济支持，当时的中国队并没有参加世界杯的比赛，但是参加了1936年和1948年的奥运会足球赛，可惜第一轮即遭淘汰。",
            "德国国家队始于二十世纪初。二十世纪初德国足球运动还未普及，所取得值得称道的成绩是于1934年获得世界杯季军，但四年后却首轮出局。德国在二次大战后分裂成联邦德国和民主德国，并曾于1950年被禁止参赛，数年后才解禁。之后联邦德国（西德）才组成了参赛队伍，成绩相当骄人，早在1954年已赢得战后首个世界杯冠军。",
            "1899年，巴塞罗那俱乐部成立的时候，一家来自英格兰的公校泰勒商校对他们进行了帮助。这个公校有一支橄榄球队，所以巴萨俱乐部用球队队徽上球的图案，向这个公校致敬。而FCB则是巴塞罗那足球俱乐部的简写。",
            "巴黎圣日耳曼的主要元素是巴黎的象征：埃菲尔铁塔。铁塔下方的鸢尾花，历史上是法兰西王室的标志。球队队徽上还标注有俱乐部的名称：巴黎圣日耳曼",
            "莱斯特城足球俱乐部(Leicester City Football Club)是一家位于英国英格兰莱斯特城的职业足球俱乐部，2016年5月3日，莱斯特城提前两轮夺得2015-2016赛季英超冠军，成为英超联赛第六支冠军球队，同时也是俱乐部建立132年来第一座顶级联赛冠军。",
            "国米的球队队徽，以俱乐部的主色调蓝黑色为主，中间的字母组合“IMFC”，是俱乐部全称“国际米兰足球俱乐部”的缩写。",
            "曼联的球队队徽主要有两个元素：红魔鬼图案，以及运河和帆船图案。红魔鬼是曼联的吉祥物“红魔弗雷德”，寓意是让对手恐怖，凸显曼联的王者气质。红魔手中的三叉戟，是古希腊神话中海神波塞冬的武器。帆船代表了曼彻斯特大运河，以及曼彻斯特的港口贸易中心地位。也有一种说法，帆船也代表了一往无前的精神。",
            "切尔西的球队队徽主要有两部分：中间的狮子和权杖图案，以及边缘的足球和玫瑰图案。狮子和权杖的图案，来自卡多根伯爵。他当时是切尔西行政区当地的土地所有者，也是曾经的俱乐部主席。这个标志是他的纹章。红色的玫瑰，代表英格兰，而足球图案，代表着这项运动。",
            "拜仁的球队队徽设计比较简洁，外侧的字母标注了俱乐部的名称，里面蓝白相间的菱形，是巴伐利亚州旗帜的颜色。"};
    final int[] ids = new int[]{R.id.news_lv_image, R.id.news_lv_title, R.id.news_lv_content};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_first_layout);

        first_lv = findViewById(R.id.lv_first);

        SimpleAdapter adapter = new SimpleAdapter(this,          //定义SimpleAdapter
                getData(),                                      //定义要显示的数据（封装的List集合）
                R.layout.news_lv_item,                         //指定布局文件
                new String[]{"picture", "title", "content"},        // 每一个记录的列名称的列表
                ids); //列对应的ID值

        first_lv.setAdapter(adapter);                //把数据映射到adapter容器
        first_lv.setOnItemClickListener(this);

    }

    private List<Map<String, Object>> getData() {

        List<Map<String, Object>> list = new ArrayList<>();

        for (int i = 0; i < 9; i++) {

            Map<String, Object> map = new HashMap<>();
            map.put("title", titles[i]);
            map.put("content", contents[i]);
            map.put("picture", pictures[i]);
            list.add(map);

        }
        return list;
    }

    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        HashMap<String, Object> map = (HashMap<String, Object>) parent.getItemAtPosition(position);
        String Title = (String) map.get("title");
        String Content = (String) map.get("content");
        int PictureId = (int) map.get("picture");

//        传输数据方法一（会跳转页面）
//        Bundle bundle = new Bundle();
//        bundle.putInt("picture", PictureId);
//        bundle.putString(
//
//
// "title", Title);
//        bundle.putString("content", Content);
//        Intent intent = new Intent();
//        intent.putExtras(bundle);
//        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
//        intent.setClass(news_first.this, news_content.class);
//        startActivity(intent);

//        数据传输方法二（数据放在文件里）
        SharedPreferences.Editor saved = getSharedPreferences("data", Context.MODE_PRIVATE).edit();
        saved.putInt("picture", PictureId);
        saved.putString("title", Title);
        saved.putString("content", Content);
        saved.apply();

        Intent intent = new Intent();
        intent.setClass(news_first.this, news_content.class);
        startActivity(intent);

    }

}
