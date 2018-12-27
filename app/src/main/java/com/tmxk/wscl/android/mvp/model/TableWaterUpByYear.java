package com.tmxk.wscl.android.mvp.model;

import com.bin.david.form.annotation.SmartColumn;
import com.bin.david.form.annotation.SmartTable;

/***
 * 序号	站点名称	运营编号	控制ID	超水量天数	占比
 * 1	长浜河	XB10SD30T-015	23	50	13.7%
 * 2	孙巷	XB11SD20T-015	100	15	4.1%
 */
@SmartTable(name="年度超水量分析表")
public class TableWaterUpByYear {
    @SmartColumn(id =1,name = "序号")
    private int sortNum;
    @SmartColumn(id =2,name = "站点名称")
    private String shortTitle;
    @SmartColumn(id =3,name = "运营编号")
    private String operatrNum;
    @SmartColumn(id =4,name = "控制ID")
    private int contrlId;
    @SmartColumn(id =5,name = "超水量天数")
    private int upDay;
    @SmartColumn(id =6,name = "占比")
    private String bit;

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

    public int getUpDay() {
        return upDay;
    }

    public void setUpDay(int upDay) {
        this.upDay = upDay;
    }

    public String getBit() {
        return bit;
    }

    public void setBit(String bit) {
        this.bit = bit;
    }
}
