package com.tmxk.wscl.android.mvp.model;

public class CharCommonBean {
    private String x;
    private double y;

    public CharCommonBean(String x, double y) {
        this.x = x;
        this.y = y;
    }

    public CharCommonBean() {
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}
