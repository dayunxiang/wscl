package com.tmxk.wscl.android.mvp.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class SiteDeviceDocBean implements Serializable {

    /**
     * deviceName : string
     * deviceNo : string
     * devicePower : 0
     * deviceType : string
     * id : 0
     * lastRepairTime : 2018-11-06T05:53:26.210Z
     * limitYears : 0
     * maintainCycleDay : 0
     * number : 0
     * repairRecord : 0
     * setupTime : 2018-11-06T05:53:26.210Z
     * sewage : {"action":0,"address":"string","administratorId":0,"airSched":"string","area":{"coordinateX":0,"coordinateY":0,"id":0,"introduce":"string","isSendArea":"string","name":"string","pid":0},"backupSched":"string","confirmGratingTime":"2018-11-06T05:53:26.210Z","controlId":0,"controlMethod":0,"controlStrategy":"string","coordinateX":0,"coordinateY":0,"detection1":0,"detection10DL":0,"detection10UL":0,"detection11DL":0,"detection11UL":0,"detection12DL":0,"detection12UL":0,"detection13DL":0,"detection13UL":0,"detection14DL":0,"detection14UL":0,"detection15DL":0,"detection15UL":0,"detection16DL":0,"detection16UL":0,"detection17DL":0,"detection17UL":0,"detection18DL":0,"detection18UL":0,"detection19DL":0,"detection19UL":0,"detection1DL":0,"detection1UL":0,"detection2":0,"detection20DL":0,"detection20UL":0,"detection2DL":0,"detection2UL":0,"detection3":0,"detection3DL":0,"detection3UL":0,"detection4":0,"detection4DL":0,"detection4UL":0,"detection5":0,"detection5DL":0,"detection5UL":0,"detection6":0,"detection6DL":0,"detection6UL":0,"detection7":0,"detection7DL":0,"detection7UL":0,"detection8":0,"detection8DL":0,"detection8UL":0,"detection9":0,"detection9DL":0,"detection9UL":0,"deviceAlert":"string","emissionStandard":"string","equipment1Power":0,"equipment1state":"string","equipment2Power":0,"equipment2state":"string","equipment3Power":0,"equipment3state":"string","equipment4Power":0,"equipment4state":"string","equipment5Power":0,"equipment5state":"string","flowSched":"string","gratingDays":0,"id":0,"mudSched":"string","name":"string","operationNum":"string","reduceCOD":0,"reduceNH3N":0,"reduceP":0,"runtimePeriod1":0,"runtimePeriod10":0,"runtimePeriod2":0,"runtimePeriod3":0,"runtimePeriod4":0,"runtimePeriod5":0,"runtimePeriod6":0,"runtimePeriod7":0,"runtimePeriod8":0,"runtimePeriod9":0,"shortTitle":"string","stoptimePeriod1":0,"stoptimePeriod10":0,"stoptimePeriod2":0,"stoptimePeriod3":0,"stoptimePeriod4":0,"stoptimePeriod5":0,"stoptimePeriod6":0,"stoptimePeriod7":0,"stoptimePeriod8":0,"stoptimePeriod9":0,"tonnage":0,"updateTime":"2018-11-06T05:53:26.212Z","videoPuid":"string","videoUrl":"string","waterSched":"string","waterflow":0}
     */

    private String deviceName;
    private String deviceNo;
    private int devicePower;
    private String deviceType;
    private int id;
    private String lastRepairTime;
    private int limitYears;
    private int maintainCycleDay;
    private int number;
    private int repairRecord;
    private String setupTime;
    private SewageListBean.ObjectBean sewage;
}
