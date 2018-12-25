package com.tmxk.wscl.android.mvp.model;

import com.bin.david.form.annotation.SmartColumn;
import com.bin.david.form.annotation.SmartTable;

/***
 * 序号	9站点名称	运营编号	控制ID号	故障设备名称
 * 1	长浜河	XB10SD30T-001	23	曝气机故障
 * 2	长浜河	XB10SD30T-001	23	提升泵故障
 *
 */
@SmartTable(name="实时设备故障表")
public class TableEquipStatus {
    @SmartColumn(id =1,name = "序号")
    private int sortNum;
    @SmartColumn(id =2,name = "站点名称")
    private String shortTitle;
    @SmartColumn(id =3,name = "运营编号")
    private String operatrNum;
    @SmartColumn(id =4,name = "控制ID号")
    private int contrlId;
    @SmartColumn(id =5,name = "故障设备名称")
    private String equitStatus;

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

    public String getEquitStatus() {
        return equitStatus;
    }

    public void setEquitStatus(String equitStatus) {
        this.equitStatus = equitStatus;
    }
}
