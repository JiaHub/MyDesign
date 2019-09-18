package com.example.g40_70.coursedesign.sql_lite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by G40-70 on 2018/12/17.
 *
 */

public class PlayDao {

    private DatabaseHelper databaseHelper;

    public PlayDao(Context context) {
        databaseHelper = new DatabaseHelper(context);       //参数是上下文
    }

    //插入数据
    public void insert(Player player) {

        //得到一个可写的数据库
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        //ContentValues 键值的用法   key要跟列名一致
        ContentValues cv = new ContentValues();
        cv.put("name", player.getName());
        cv.put("sex", player.getSex());
        cv.put("age", player.getAge());
        cv.put("site", player.getSite());
        cv.put("foot", player.getFoot());
        cv.put("height", player.getHeight());
        cv.put("weight", player.getWeight());
        cv.put("number", player.getNumber());
        String name = player.getName();
        db.insert("hei", name,cv);
        db.close();

        Log.i("PlayDao","这里调用了插入方法");

    }

    //删除一条数据
    public void deletePlayer(String name) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        db.delete("hei", "name= ? ", new String[]{name});
        db.close();

        Log.i("PlayDao","这里调用了删除方法"+name);

    }

    //删除所有数据
    public void delete() {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        db.delete("hei", null, null);
        db.close();

        Log.i("PlayDao","这里调用了删除所有记录方法");

    }

    //修改数据
    public void update(Player player) {

        //得到一个可写的数据库
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        //ContentValues 键值的用法   key要跟列名一致
        ContentValues cv = new ContentValues();
        cv.put("name", player.getName());
        cv.put("sex", player.getSex());
        cv.put("age", player.getAge());
        cv.put("site", player.getSite());
        cv.put("foot", player.getFoot());
        cv.put("height", player.getHeight());
        cv.put("weight", player.getWeight());
        cv.put("number", player.getNumber());
        String name = player.getName();
        db.update("hei", cv, "name= ? ", new String[]{name});
        db.close();

        Log.i("PlayDao","这里调用了修改方法"+name);

    }

    //查询记录
    public Player queryPlayer(String name){

        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        name = name.trim();
        Cursor cs = db.query("hei", null, "name= ? ", new String[]{name}, null, null, null, null);
        Player player = null;

        if (cs != null && cs.getCount() > 0) {

            while (cs.moveToNext()) {
                player = new Player();
                // cs.getColumnIndex("_id")   id 这一列结果中的下标
                player.setUserId(cs.getInt(cs.getColumnIndex("_id")));
                player.setName(cs.getString(cs.getColumnIndex("name")));
                player.setAge(cs.getString(cs.getColumnIndex("age")));
                player.setSex(cs.getString(cs.getColumnIndex("sex")));
                player.setSite(cs.getString(cs.getColumnIndex("site")));
                player.setFoot(cs.getString(cs.getColumnIndex("foot")));
                player.setHeight(cs.getString(cs.getColumnIndex("height")));
                player.setWeight(cs.getString(cs.getColumnIndex("weight")));
                player.setNumber(cs.getString(cs.getColumnIndex("number")));
            }
            cs.close();
            Log.i("PlayDao", "这里调用了查询方法" + name);
        }
        db.close();
        Log.i("PlayDao", "关闭数据库！");

        return player;

    }

    //查询所有数据
    public List query() {
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        Cursor cs = db.query("hei", null, null, null, null, null, null);
        Player player = null;
        List<Player> list = new ArrayList<>();
        while (cs.moveToNext()) {
            player = new Player();
            // cs.getColumnIndex("_id")   id 这一列结果中的下标
            player.setUserId(cs.getInt(cs.getColumnIndex("_id")));
            player.setName(cs.getString(cs.getColumnIndex("name")));
            player.setAge(cs.getString(cs.getColumnIndex("age")));
            player.setSex(cs.getString(cs.getColumnIndex("sex")));
            player.setSite(cs.getString(cs.getColumnIndex("site")));
            player.setFoot(cs.getString(cs.getColumnIndex("foot")));
            player.setHeight(cs.getString(cs.getColumnIndex("height")));
            player.setWeight(cs.getString(cs.getColumnIndex("weight")));
            player.setNumber(cs.getString(cs.getColumnIndex("number")));
            list.add(player);
        }
        //关闭结果集
        cs.close();
        //关闭数据库
        db.close();

        Log.i("PlayDao","这里调用了查询所有数据方法");

        return list;
    }


}
