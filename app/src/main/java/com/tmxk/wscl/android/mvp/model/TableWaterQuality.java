package com.tmxk.wscl.android.mvp.model;

import com.bin.david.form.annotation.SmartColumn;
import com.bin.david.form.annotation.SmartTable;

/***
 * 序号	站点名称	运营编号	控制ID号	异常参数名称
 * 1	长浜河	XB10SD30T-001	23	PH
 * 2	长浜河	XB10SD30T-001	23	ORP
 *
 */
@SmartTable(name="实时水质异常表")
public class TableWaterQuality {
    @SmartColumn(id =1,name = "序号")
    private int sortNum;
    @SmartColumn(id =2,name = "站点名称")
    private String shortTitle;
    @SmartColumn(id =3,name = "运营编号")
    private String operatrNum;
    @SmartColumn(id =4,name = "控制ID号")
    private int contrlId;
    @SmartColumn(id =5,name = "异常参数名称")
    private String paramName;
    @SmartColumn(id =6,name = "参数值")
    private double paramVal;

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

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public double getParamVal() {
        return paramVal;
    }

    public void setParamVal(double paramVal) {
        this.paramVal = paramVal;
    }
}
