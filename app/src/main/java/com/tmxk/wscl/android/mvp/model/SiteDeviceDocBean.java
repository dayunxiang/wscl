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
    private SewageBean sewage;

    @Data
    public static class SewageBean implements Serializable {
        /**
         * action : 0
         * address : string
         * administratorId : 0
         * airSched : string
         * area : {"coordinateX":0,"coordinateY":0,"id":0,"introduce":"string","isSendArea":"string","name":"string","pid":0}
         * backupSched : string
         * confirmGratingTime : 2018-11-06T05:53:26.210Z
         * controlId : 0
         * controlMethod : 0
         * controlStrategy : string
         * coordinateX : 0
         * coordinateY : 0
         * detection1 : 0
         * detection10DL : 0
         * detection10UL : 0
         * detection11DL : 0
         * detection11UL : 0
         * detection12DL : 0
         * detection12UL : 0
         * detection13DL : 0
         * detection13UL : 0
         * detection14DL : 0
         * detection14UL : 0
         * detection15DL : 0
         * detection15UL : 0
         * detection16DL : 0
         * detection16UL : 0
         * detection17DL : 0
         * detection17UL : 0
         * detection18DL : 0
         * detection18UL : 0
         * detection19DL : 0
         * detection19UL : 0
         * detection1DL : 0
         * detection1UL : 0
         * detection2 : 0
         * detection20DL : 0
         * detection20UL : 0
         * detection2DL : 0
         * detection2UL : 0
         * detection3 : 0
         * detection3DL : 0
         * detection3UL : 0
         * detection4 : 0
         * detection4DL : 0
         * detection4UL : 0
         * detection5 : 0
         * detection5DL : 0
         * detection5UL : 0
         * detection6 : 0
         * detection6DL : 0
         * detection6UL : 0
         * detection7 : 0
         * detection7DL : 0
         * detection7UL : 0
         * detection8 : 0
         * detection8DL : 0
         * detection8UL : 0
         * detection9 : 0
         * detection9DL : 0
         * detection9UL : 0
         * deviceAlert : string
         * emissionStandard : string
         * equipment1Power : 0
         * equipment1state : string
         * equipment2Power : 0
         * equipment2state : string
         * equipment3Power : 0
         * equipment3state : string
         * equipment4Power : 0
         * equipment4state : string
         * equipment5Power : 0
         * equipment5state : string
         * flowSched : string
         * gratingDays : 0
         * id : 0
         * mudSched : string
         * name : string
         * operationNum : string
         * reduceCOD : 0
         * reduceNH3N : 0
         * reduceP : 0
         * runtimePeriod1 : 0
         * runtimePeriod10 : 0
         * runtimePeriod2 : 0
         * runtimePeriod3 : 0
         * runtimePeriod4 : 0
         * runtimePeriod5 : 0
         * runtimePeriod6 : 0
         * runtimePeriod7 : 0
         * runtimePeriod8 : 0
         * runtimePeriod9 : 0
         * shortTitle : string
         * stoptimePeriod1 : 0
         * stoptimePeriod10 : 0
         * stoptimePeriod2 : 0
         * stoptimePeriod3 : 0
         * stoptimePeriod4 : 0
         * stoptimePeriod5 : 0
         * stoptimePeriod6 : 0
         * stoptimePeriod7 : 0
         * stoptimePeriod8 : 0
         * stoptimePeriod9 : 0
         * tonnage : 0
         * updateTime : 2018-11-06T05:53:26.212Z
         * videoPuid : string
         * videoUrl : string
         * waterSched : string
         * waterflow : 0
         */

        private int action;
        private String address;
        private int administratorId;
        private String airSched;
        private AreaBean area;
        private String backupSched;
        private String confirmGratingTime;
        private int controlId;
        private int controlMethod;
        private String controlStrategy;
        private int coordinateX;
        private int coordinateY;
        private int detection1;
        private int detection10DL;
        private int detection10UL;
        private int detection11DL;
        private int detection11UL;
        private int detection12DL;
        private int detection12UL;
        private int detection13DL;
        private int detection13UL;
        private int detection14DL;
        private int detection14UL;
        private int detection15DL;
        private int detection15UL;
        private int detection16DL;
        private int detection16UL;
        private int detection17DL;
        private int detection17UL;
        private int detection18DL;
        private int detection18UL;
        private int detection19DL;
        private int detection19UL;
        private int detection1DL;
        private int detection1UL;
        private int detection2;
        private int detection20DL;
        private int detection20UL;
        private int detection2DL;
        private int detection2UL;
        private int detection3;
        private int detection3DL;
        private int detection3UL;
        private int detection4;
        private int detection4DL;
        private int detection4UL;
        private int detection5;
        private int detection5DL;
        private int detection5UL;
        private int detection6;
        private int detection6DL;
        private int detection6UL;
        private int detection7;
        private int detection7DL;
        private int detection7UL;
        private int detection8;
        private int detection8DL;
        private int detection8UL;
        private int detection9;
        private int detection9DL;
        private int detection9UL;
        private String deviceAlert;
        private String emissionStandard;
        private int equipment1Power;
        private String equipment1state;
        private int equipment2Power;
        private String equipment2state;
        private int equipment3Power;
        private String equipment3state;
        private int equipment4Power;
        private String equipment4state;
        private int equipment5Power;
        private String equipment5state;
        private String flowSched;
        private int gratingDays;
        private int id;
        private String mudSched;
        private String name;
        private String operationNum;
        private int reduceCOD;
        private int reduceNH3N;
        private int reduceP;
        private int runtimePeriod1;
        private int runtimePeriod10;
        private int runtimePeriod2;
        private int runtimePeriod3;
        private int runtimePeriod4;
        private int runtimePeriod5;
        private int runtimePeriod6;
        private int runtimePeriod7;
        private int runtimePeriod8;
        private int runtimePeriod9;
        private String shortTitle;
        private int stoptimePeriod1;
        private int stoptimePeriod10;
        private int stoptimePeriod2;
        private int stoptimePeriod3;
        private int stoptimePeriod4;
        private int stoptimePeriod5;
        private int stoptimePeriod6;
        private int stoptimePeriod7;
        private int stoptimePeriod8;
        private int stoptimePeriod9;
        private int tonnage;
        private String updateTime;
        private String videoPuid;
        private String videoUrl;
        private String waterSched;
        private int waterflow;

        @Data
        public static class AreaBean implements Serializable {
            /**
             * coordinateX : 0
             * coordinateY : 0
             * id : 0
             * introduce : string
             * isSendArea : string
             * name : string
             * pid : 0
             */

            private int coordinateX;
            private int coordinateY;
            private int id;
            private String introduce;
            private String isSendArea;
            private String name;
            private int pid;
        }
    }
}
