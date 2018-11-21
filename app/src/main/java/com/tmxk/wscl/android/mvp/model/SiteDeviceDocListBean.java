package com.tmxk.wscl.android.mvp.model;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class SiteDeviceDocListBean implements Serializable {

    /**
     * metaInfo : {"code":200,"offset":1,"limit":1,"totalPage":3,"totalSize":3}
     * object : [{"id":1,"number":10,"deviceName":"testDevice","deviceType":"天马","setupTime":1536200843273,"lastRepairTime":1536200843273,"maintainCycleDay":10,"limitYears":10,"repairRecord":10,"sewage":{"id":23,"area":{"id":9,"pid":1,"name":"??","coordinateX":31.67727,"coordinateY":120.43651,"introduce":null,"isSendArea":"true"},"controlId":23,"administratorId":1,"shortTitle":"长浜河","name":"锡北泾西长浜河","address":null,"coordinateX":120.44298,"coordinateY":31.68138,"equipment1state":"1","equipment2state":"2","equipment3state":"1","equipment4state":"2","equipment5state":"2","detection1":0,"detection1UL":50,"detection1DL":-10,"detection2":0,"detection2UL":14,"detection2DL":1,"detection3":0,"detection3UL":900,"detection3DL":-900,"detection4":0,"detection4UL":null,"detection4DL":null,"detection5":0,"detection5UL":15,"detection5DL":2,"detection6":0,"detection6UL":null,"detection6DL":0,"airSched":"22222222222222222222222 ","waterSched":"22222222222222222222222 ","mudSched":"22222222222222222222222 ","flowSched":"22222222222222222222222 ","backupSched":"22222222222222222222222 ","controlStrategy":"1111 ","deviceAlert":"1111 ","gratingDays":0,"confirmGratingTime":1535965787000,"action":1,"waterflow":0,"reduceCOD":200,"reduceNH3N":30,"reduceP":8,"operationNum":"XB10SD30T-041","controlMethod":2,"detection7":3,"detection8":0,"detection9":654.41,"updateTime":1535965850000,"detection10DL":0,"detection11DL":0,"detection12DL":0,"detection13DL":0,"detection14DL":0,"detection15DL":0,"detection16DL":0,"detection17DL":0,"detection18DL":0,"detection19DL":0,"detection20DL":0,"detection10UL":0,"detection11UL":0,"detection12UL":0,"detection13UL":0,"detection14UL":0,"detection15UL":0,"detection16UL":0,"detection17UL":0,"detection18UL":0,"detection19UL":0,"detection20UL":0,"detection7UL":null,"detection7DL":null,"detection8UL":null,"detection8DL":null,"detection9UL":null,"detection9DL":null,"runtimePeriod1":null,"stoptimePeriod1":null,"runtimePeriod2":null,"stoptimePeriod2":null,"runtimePeriod3":null,"stoptimePeriod3":null,"runtimePeriod4":null,"stoptimePeriod4":null,"runtimePeriod5":null,"stoptimePeriod5":null,"runtimePeriod6":null,"stoptimePeriod6":null,"runtimePeriod7":null,"stoptimePeriod7":null,"runtimePeriod8":null,"stoptimePeriod8":null,"runtimePeriod9":null,"stoptimePeriod9":null,"runtimePeriod10":null,"stoptimePeriod10":null,"videoUrl":"rtmp://61.160.70.100:10100/app/live?token=5da079b694f211e685fbc81f66d85ed311d1c6090c9143cce0a9d2ae90e500d1","tonnage":40,"emissionStandard":"一级B","videoPuid":"201115202316439695","equipment1Power":0,"equipment2Power":0,"equipment3Power":0,"equipment4Power":0,"equipment5Power":0},"deviceNo":"1000000","devicePower":10}]
     */

    private MetaInfoBean metaInfo;
    private List<SiteDeviceDocBean> object;

    @Data
    public static class MetaInfoBean implements Serializable {
        /**
         * code : 200
         * offset : 1
         * limit : 1
         * totalPage : 3
         * totalSize : 3
         */

        private int code;
        private int offset;
        private int limit;
        private int totalPage;
        private int totalSize;
    }
}
