package com.tmxk.wscl.android.mvp.model;

import com.bin.david.form.annotation.SmartColumn;
import com.bin.david.form.annotation.SmartTable;

/***
 * 序号	站点名称	运营编号	控制ID号	最近更新时间
 * 1	长浜河	XB10SD30T-001	23	2018-04-01 00:02:55
 * 2	孙巷	XB10SD15T-002	100	2018-05-01 00:02:55
 *
 */
@SmartTable(name="实时数据传输查询表")
public class TableDataTrans {
    @SmartColumn(id =1,name = "序号")
    private int sortNum;
    @SmartColumn(id =2,name = "站点名称")
    private String shortTitle;
    @SmartColumn(id =3,name = "运营编号")
    private String operatrNum;
    @SmartColumn(id =4,name = "控制ID号")
    private int contrlId;
    @SmartColumn(id =5,name = "最近更新时间")
    private String recentUpdateTime;

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

    public String getRecentUpdateTime() {
        return recentUpdateTime;
    }

    public void setRecentUpdateTime(String recentUpdateTime) {
        this.recentUpdateTime = recentUpdateTime;
    }
}
