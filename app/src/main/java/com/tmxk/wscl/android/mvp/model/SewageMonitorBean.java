/**
 * Copyright (C), 2018-2018 tmxk
 * FileName: SewageMonitorBean
 * Author:   wangyao
 * Date:     2018/11/26 11:57
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.tmxk.wscl.android.mvp.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈站点监控返回字段〉
 *
 * @author wangyao
 * @date 2018/11/26
 * @since 1.0.0
 */
public class SewageMonitorBean {
    private SewageListBean.ObjectBean sewage;

//    @ApiModelProperty("设备状态")
    private List<String> equipName;

    //@ApiModelProperty("设备检测时间")
    private String testingTime;
    //水质监测：PH、ORP、DO、T、COD、NH3-N、P、SS
    //@ApiModelProperty("水质监测-PH")
    private BigDecimal waterDetectPh;
    //@ApiModelProperty("水质监测-ORP")
    private BigDecimal waterDetectOrp;
    //@ApiModelProperty("水质监测-DO")
    private BigDecimal waterDetectDo;
    //@ApiModelProperty("水质监测-T")
    private BigDecimal waterDetectT;
    //@ApiModelProperty("水质监测-COD")
    private BigDecimal waterDetectCod;
    //@ApiModelProperty("水质监测-NH3-N")
    private BigDecimal waterDetectNh3n;
    //@ApiModelProperty("水质监测-SS")
    private BigDecimal waterDetectSs;
    //@ApiModelProperty("水质监测-P")
    private BigDecimal waterDetectP;
    //@ApiModelProperty("日处理水量")
    private Float dailyWater;
    //@ApiModelProperty("月处理水量")
    private Float monthWater;
    //@ApiModelProperty("表显累计水量")
    private BigDecimal totalWater;

    public BigDecimal getTotalWater() {
        return totalWater;
    }

    public void setTotalWater(BigDecimal totalWater) {
        this.totalWater = totalWater;
    }

    public Float getMonthWater() {
        return monthWater;
    }

    public void setMonthWater(Float monthWater) {
        this.monthWater = monthWater;
    }

    public Float getDailyWater() {
        return dailyWater;
    }

    public void setDailyWater(Float dailyWater) {
        this.dailyWater = dailyWater;
    }

    public BigDecimal getWaterDetectPh() {
        return waterDetectPh;
    }

    public void setWaterDetectPh(BigDecimal waterDetectPh) {
        this.waterDetectPh = waterDetectPh;
    }

    public BigDecimal getWaterDetectOrp() {
        return waterDetectOrp;
    }

    public void setWaterDetectOrp(BigDecimal waterDetectOrp) {
        this.waterDetectOrp = waterDetectOrp;
    }

    public BigDecimal getWaterDetectDo() {
        return waterDetectDo;
    }

    public void setWaterDetectDo(BigDecimal waterDetectDo) {
        this.waterDetectDo = waterDetectDo;
    }

    public BigDecimal getWaterDetectT() {
        return waterDetectT;
    }

    public void setWaterDetectT(BigDecimal waterDetectT) {
        this.waterDetectT = waterDetectT;
    }

    public BigDecimal getWaterDetectCod() {
        return waterDetectCod;
    }

    public void setWaterDetectCod(BigDecimal waterDetectCod) {
        this.waterDetectCod = waterDetectCod;
    }

    public BigDecimal getWaterDetectNh3n() {
        return waterDetectNh3n;
    }

    public void setWaterDetectNh3n(BigDecimal waterDetectNh3n) {
        this.waterDetectNh3n = waterDetectNh3n;
    }

    public BigDecimal getWaterDetectSs() {
        return waterDetectSs;
    }

    public void setWaterDetectSs(BigDecimal waterDetectSs) {
        this.waterDetectSs = waterDetectSs;
    }

    public BigDecimal getWaterDetectP() {
        return waterDetectP;
    }

    public void setWaterDetectP(BigDecimal waterDetectP) {
        this.waterDetectP = waterDetectP;
    }

    public String getTestingTime() {
        return testingTime;
    }

    public void setTestingTime(String testingTime) {
        this.testingTime = testingTime;
    }

    public List<String> getEquipName() {
        return equipName;
    }

    public void setEquipName(List<String> equipName) {
        this.equipName = equipName;
    }

    public SewageListBean.ObjectBean getSewage() {
        return sewage;
    }

    public void setSewage(SewageListBean.ObjectBean sewage) {
        this.sewage = sewage;
    }
}
