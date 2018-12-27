package com.tmxk.wscl.android.mvp.model;

import com.bin.david.form.annotation.SmartColumn;
import com.bin.david.form.annotation.SmartTable;

/***
 * 序号	站点名称	运营编号	控制ID	日期	水处理量	超限比例
 * 1	长浜河	XB10SD30T-023	23	2018-05-09	35	16.7%
 */
@SmartTable(name="站点超水量分析表")
public class TableWaterUp {
    @SmartColumn(id =1,name = "序号")
    private int sortNum;
    @SmartColumn(id =2,name = "站点名称")
    private String shortTitle;
    @SmartColumn(id =3,name = "运营编号")
    private String operatrNum;
    @SmartColumn(id =4,name = "控制ID")
    private int contrlId;
    @SmartColumn(id =5,name = "日期")
    private String date;
    @SmartColumn(id =6,name = "水处理量")
    private double waterMetric;
    @SmartColumn(id =7,name = "超限比例")
    private double bit;
    @SmartColumn(id =8,name = "状态")
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getSortNum() {
        return sortNum;
    }

    public void setSortNum(int sortNum) {
        this.sortNum = sortNum;
    }

    public String getShortTitle() {
        return shortTitle;
    }

    public void setShortTitle(String shortTitle) {
        this.shortTitle = shortTitle;
    }

    public String getOperatrNum() {
        return operatrNum;
    }

    public void setOperatrNum(String operatrNum) {
        this.operatrNum = operatrNum;
    }

    public int getContrlId() {
        return contrlId;
    }

    public void setContrlId(int contrlId) {
        this.contrlId = contrlId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getWaterMetric() {
        return waterMetric;
    }

    public void setWaterMetric(double waterMetric) {
        this.waterMetric = waterMetric;
    }

    public double getBit() {
        return bit;
    }

    public void setBit(double bit) {
        this.bit = bit;
    }
}
