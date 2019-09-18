package com.example.g40_70.coursedesign.sql_lite;

/**
 * Created by G40-70 on 2018/12/17.
 */

public class Player {

    private int userId;
    private String age;
    private String sex;
    private String name;
    private String site;
    private String foot;
    private String height;
    private String weight;
    private String number;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getFoot() {
        return foot;
    }

    public void setFoot(String foot) {
        this.foot = foot;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Player{" +
                "userId=" + userId +
                ", age='" + age + '\'' +
                ", sex='" + sex + '\'' +
                ", name='" + name + '\'' +
                ", site='" + site + '\'' +
                ", foot='" + foot + '\'' +
                ", height='" + height + '\'' +
                ", weight='" + weight + '\'' +
                ", number='" + number + '\'' +
                '}';
    }
}
