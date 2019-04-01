package com.tmxk.wscl.android.mvp.model;

public class CreateCarGpsBySysuserBean {
    /**
     * actuallyDriver : wy
     * coordinateX : 39.254
     * coordinateY : 48.566
     * sysuserId : 11
     */

    private String actuallyDriver;
    private double coordinateX;
    private double coordinateY;
    private int sysuserId;

    public String getActuallyDriver() {
        return actuallyDriver;
    }

    public void setActuallyDriver(String actuallyDriver) {
        this.actuallyDriver = actuallyDriver;
    }

    public double getCoordinateX() {
        return coordinateX;
    }

    public void setCoordinateX(double coordinateX) {
        this.coordinateX = coordinateX;
    }

    public double getCoordinateY() {
        return coordinateY;
    }

    public void setCoordinateY(double coordinateY) {
        this.coordinateY = coordinateY;
    }

    public int getSysuserId() {
        return sysuserId;
    }

    public void setSysuserId(int sysuserId) {
        this.sysuserId = sysuserId;
    }
}
