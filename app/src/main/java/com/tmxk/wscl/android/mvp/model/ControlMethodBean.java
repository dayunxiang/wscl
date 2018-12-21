package com.tmxk.wscl.android.mvp.model;

public class ControlMethodBean {
    private int num;
    private String name;

    public ControlMethodBean(int num, String name) {
        this.num = num;
        this.name = name;
    }

    public ControlMethodBean() {
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
