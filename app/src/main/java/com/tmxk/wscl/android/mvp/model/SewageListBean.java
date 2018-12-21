package com.tmxk.wscl.android.mvp.model;

import java.util.List;

public class SewageListBean {

    /**
     * metaInfo : {"code":200,"offset":1,"limit":10,"totalPage":1,"totalSize":1}
     * object : [{"id":23,"area":{"id":9,"pid":1,"name":"无锡","coordinateX":31.67727,"coordinateY":120.43651,"introduce":"江苏省","isSendArea":"true"},"controlId":23,"administrator":{"id":1,"name":"wy","telephone":"18896724553","address":"wx","email":"wangyao@cmss.chinamobile.com"},"shortTitle":"长浜河","name":"锡北泾西长浜河","address":null,"coordinateX":180,"coordinateY":180,"equipment1state":"1","equipment2state":"2","equipment3state":"1","equipment4state":"2","equipment5state":"2","detection1":0,"detection1UL":50,"detection1DL":-10,"detection2":0,"detection2UL":14,"detection2DL":1,"detection3":0,"detection3UL":900,"detection3DL":-900,"detection4":0,"detection4UL":null,"detection4DL":null,"detection5":0,"detection5UL":15,"detection5DL":2,"detection6":0,"detection6UL":null,"detection6DL":0,"airSched":"22222222222222222222222 ","waterSched":"22222222222222222222222 ","mudSched":"22222222222222222222222 ","flowSched":"22222222222222222222222 ","backupSched":"22222222222222222222222 ","controlStrategy":"1111 ","deviceAlert":"1111 ","gratingDays":0,"confirmGratingTime":1535965787000,"action":1,"waterflow":0,"reduceCOD":200,"reduceNH3N":30,"reduceP":8,"operationNum":"XB10SD30T-041","controlMethod":2,"detection7":3,"detection8":0,"detection9":654.41,"updateTime":1535965850000,"detection10DL":0,"detection11DL":0,"detection12DL":0,"detection13DL":0,"detection14DL":0,"detection15DL":0,"detection16DL":0,"detection17DL":0,"detection18DL":0,"detection19DL":0,"detection20DL":0,"detection10UL":0,"detection11UL":0,"detection12UL":0,"detection13UL":0,"detection14UL":0,"detection15UL":0,"detection16UL":0,"detection17UL":0,"detection18UL":0,"detection19UL":0,"detection20UL":0,"detection7UL":null,"detection7DL":null,"detection8UL":null,"detection8DL":null,"detection9UL":null,"detection9DL":null,"runtimePeriod1":null,"stoptimePeriod1":null,"runtimePeriod2":null,"stoptimePeriod2":null,"runtimePeriod3":null,"stoptimePeriod3":null,"runtimePeriod4":null,"stoptimePeriod4":null,"runtimePeriod5":null,"stoptimePeriod5":null,"runtimePeriod6":null,"stoptimePeriod6":null,"runtimePeriod7":null,"stoptimePeriod7":null,"runtimePeriod8":null,"stoptimePeriod8":null,"runtimePeriod9":null,"stoptimePeriod9":null,"runtimePeriod10":null,"stoptimePeriod10":null,"videoUrl":"rtmp://61.160.70.100:10100/app/live?token=5da079b694f211e685fbc81f66d85ed311d1c6090c9143cce0a9d2ae90e500d1","tonnage":40,"emissionStandard":"一级B","videoPuid":"201115202316439695","equipment1Power":0,"equipment2Power":0,"equipment3Power":0,"equipment4Power":0,"equipment5Power":0}]
     */

    private MetaInfoBean metaInfo;
    private List<ObjectBean> object;

    public MetaInfoBean getMetaInfo() {
        return metaInfo;
    }

    public void setMetaInfo(MetaInfoBean metaInfo) {
        this.metaInfo = metaInfo;
    }

    public List<ObjectBean> getObject() {
        return object;
    }

    public void setObject(List<ObjectBean> object) {
        this.object = object;
    }

    public static class MetaInfoBean {
        /**
         * code : 200
         * offset : 1
         * limit : 10
         * totalPage : 1
         * totalSize : 1
         */

        private int code;
        private int offset;
        private int limit;
        private int totalPage;
        private int totalSize;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public int getOffset() {
            return offset;
        }

        public void setOffset(int offset) {
            this.offset = offset;
        }

        public int getLimit() {
            return limit;
        }

        public void setLimit(int limit) {
            this.limit = limit;
        }

        public int getTotalPage() {
            return totalPage;
        }

        public void setTotalPage(int totalPage) {
            this.totalPage = totalPage;
        }

        public int getTotalSize() {
            return totalSize;
        }

        public void setTotalSize(int totalSize) {
            this.totalSize = totalSize;
        }
    }

    public static class ObjectBean {
        /**
         * id : 23
         * area : {"id":9,"pid":1,"name":"无锡","coordinateX":31.67727,"coordinateY":120.43651,"introduce":"江苏省","isSendArea":"true"}
         * controlId : 23
         * administrator : {"id":1,"name":"wy","telephone":"18896724553","address":"wx","email":"wangyao@cmss.chinamobile.com"}
         * shortTitle : 长浜河
         * name : 锡北泾西长浜河
         * address : null
         * coordinateX : 180
         * coordinateY : 180
         * equipment1state : 1
         * equipment2state : 2
         * equipment3state : 1
         * equipment4state : 2
         * equipment5state : 2
         * detection1 : 0
         * detection1UL : 50
         * detection1DL : -10
         * detection2 : 0
         * detection2UL : 14
         * detection2DL : 1
         * detection3 : 0
         * detection3UL : 900
         * detection3DL : -900
         * detection4 : 0
         * detection4UL : null
         * detection4DL : null
         * detection5 : 0
         * detection5UL : 15
         * detection5DL : 2
         * detection6 : 0
         * detection6UL : null
         * detection6DL : 0
         * airSched : 22222222222222222222222
         * waterSched : 22222222222222222222222
         * mudSched : 22222222222222222222222
         * flowSched : 22222222222222222222222
         * backupSched : 22222222222222222222222
         * controlStrategy : 1111
         * deviceAlert : 1111
         * gratingDays : 0
         * confirmGratingTime : 1535965787000
         * action : 1
         * waterflow : 0
         * reduceCOD : 200
         * reduceNH3N : 30
         * reduceP : 8
         * operationNum : XB10SD30T-041
         * controlMethod : 2
         * detection7 : 3
         * detection8 : 0
         * detection9 : 654.41
         * updateTime : 1535965850000
         * detection10DL : 0
         * detection11DL : 0
         * detection12DL : 0
         * detection13DL : 0
         * detection14DL : 0
         * detection15DL : 0
         * detection16DL : 0
         * detection17DL : 0
         * detection18DL : 0
         * detection19DL : 0
         * detection20DL : 0
         * detection10UL : 0
         * detection11UL : 0
         * detection12UL : 0
         * detection13UL : 0
         * detection14UL : 0
         * detection15UL : 0
         * detection16UL : 0
         * detection17UL : 0
         * detection18UL : 0
         * detection19UL : 0
         * detection20UL : 0
         * detection7UL : null
         * detection7DL : null
         * detection8UL : null
         * detection8DL : null
         * detection9UL : null
         * detection9DL : null
         * runtimePeriod1 : null
         * stoptimePeriod1 : null
         * runtimePeriod2 : null
         * stoptimePeriod2 : null
         * runtimePeriod3 : null
         * stoptimePeriod3 : null
         * runtimePeriod4 : null
         * stoptimePeriod4 : null
         * runtimePeriod5 : null
         * stoptimePeriod5 : null
         * runtimePeriod6 : null
         * stoptimePeriod6 : null
         * runtimePeriod7 : null
         * stoptimePeriod7 : null
         * runtimePeriod8 : null
         * stoptimePeriod8 : null
         * runtimePeriod9 : null
         * stoptimePeriod9 : null
         * runtimePeriod10 : null
         * stoptimePeriod10 : null
         * videoUrl : rtmp://61.160.70.100:10100/app/live?token=5da079b694f211e685fbc81f66d85ed311d1c6090c9143cce0a9d2ae90e500d1
         * tonnage : 40
         * emissionStandard : 一级B
         * videoPuid : 201115202316439695
         * equipment1Power : 0
         * equipment2Power : 0
         * equipment3Power : 0
         * equipment4Power : 0
         * equipment5Power : 0
         */

        private int id;
        private AreaBean area;
        private int controlId;
        private AdministratorBean administrator;
        private String shortTitle;
        private String name;
        private Object address;
        private int coordinateX;
        private int coordinateY;
        private String equipment1state;
        private String equipment2state;
        private String equipment3state;
        private String equipment4state;
        private String equipment5state;
        private int detection1;
        private int detection1UL;
        private int detection1DL;
        private int detection2;
        private int detection2UL;
        private int detection2DL;
        private int detection3;
        private int detection3UL;
        private int detection3DL;
        private int detection4;
        private Object detection4UL;
        private Object detection4DL;
        private int detection5;
        private int detection5UL;
        private int detection5DL;
        private int detection6;
        private Object detection6UL;
        private int detection6DL;
        private String airSched;
        private String waterSched;
        private String mudSched;
        private String flowSched;
        private String backupSched;
        private String controlStrategy;
        private String deviceAlert;
        private int gratingDays;
        private long confirmGratingTime;
        private int action;
        private int waterflow;
        private int reduceCOD;
        private int reduceNH3N;
        private int reduceP;
        private String operationNum;
        private int controlMethod;
        private int detection7;
        private int detection8;
        private double detection9;
        private long updateTime;
        private int detection10DL;
        private int detection11DL;
        private int detection12DL;
        private int detection13DL;
        private int detection14DL;
        private int detection15DL;
        private int detection16DL;
        private int detection17DL;
        private int detection18DL;
        private int detection19DL;
        private int detection20DL;
        private int detection10UL;
        private int detection11UL;
        private int detection12UL;
        private int detection13UL;
        private int detection14UL;
        private int detection15UL;
        private int detection16UL;
        private int detection17UL;
        private int detection18UL;
        private int detection19UL;
        private int detection20UL;
        private Object detection7UL;
        private Object detection7DL;
        private Object detection8UL;
        private Object detection8DL;
        private Object detection9UL;
        private Object detection9DL;
        private Object runtimePeriod1;
        private Object stoptimePeriod1;
        private Object runtimePeriod2;
        private Object stoptimePeriod2;
        private Object runtimePeriod3;
        private Object stoptimePeriod3;
        private Object runtimePeriod4;
        private Object stoptimePeriod4;
        private Object runtimePeriod5;
        private Object stoptimePeriod5;
        private Object runtimePeriod6;
        private Object stoptimePeriod6;
        private Object runtimePeriod7;
        private Object stoptimePeriod7;
        private Object runtimePeriod8;
        private Object stoptimePeriod8;
        private Object runtimePeriod9;
        private Object stoptimePeriod9;
        private Object runtimePeriod10;
        private Object stoptimePeriod10;
        private String videoUrl;
        private int tonnage;
        private String emissionStandard;
        private String videoPuid;
        private int equipment1Power;
        private int equipment2Power;
        private int equipment3Power;
        private int equipment4Power;
        private int equipment5Power;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public AreaBean getArea() {
            return area;
        }

        public void setArea(AreaBean area) {
            this.area = area;
        }

        public int getControlId() {
            return controlId;
        }

        public void setControlId(int controlId) {
            this.controlId = controlId;
        }

        public AdministratorBean getAdministrator() {
            return administrator;
        }

        public void setAdministrator(AdministratorBean administrator) {
            this.administrator = administrator;
        }

        public String getShortTitle() {
            return shortTitle;
        }

        public void setShortTitle(String shortTitle) {
            this.shortTitle = shortTitle;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Object getAddress() {
            return address;
        }

        public void setAddress(Object address) {
            this.address = address;
        }

        public int getCoordinateX() {
            return coordinateX;
        }

        public void setCoordinateX(int coordinateX) {
            this.coordinateX = coordinateX;
        }

        public int getCoordinateY() {
            return coordinateY;
        }

        public void setCoordinateY(int coordinateY) {
            this.coordinateY = coordinateY;
        }

        public String getEquipment1state() {
            return equipment1state;
        }

        public void setEquipment1state(String equipment1state) {
            this.equipment1state = equipment1state;
        }

        public String getEquipment2state() {
            return equipment2state;
        }

        public void setEquipment2state(String equipment2state) {
            this.equipment2state = equipment2state;
        }

        public String getEquipment3state() {
            return equipment3state;
        }

        public void setEquipment3state(String equipment3state) {
            this.equipment3state = equipment3state;
        }

        public String getEquipment4state() {
            return equipment4state;
        }

        public void setEquipment4state(String equipment4state) {
            this.equipment4state = equipment4state;
        }

        public String getEquipment5state() {
            return equipment5state;
        }

        public void setEquipment5state(String equipment5state) {
            this.equipment5state = equipment5state;
        }

        public int getDetection1() {
            return detection1;
        }

        public void setDetection1(int detection1) {
            this.detection1 = detection1;
        }

        public int getDetection1UL() {
            return detection1UL;
        }

        public void setDetection1UL(int detection1UL) {
            this.detection1UL = detection1UL;
        }

        public int getDetection1DL() {
            return detection1DL;
        }

        public void setDetection1DL(int detection1DL) {
            this.detection1DL = detection1DL;
        }

        public int getDetection2() {
            return detection2;
        }

        public void setDetection2(int detection2) {
            this.detection2 = detection2;
        }

        public int getDetection2UL() {
            return detection2UL;
        }

        public void setDetection2UL(int detection2UL) {
            this.detection2UL = detection2UL;
        }

        public int getDetection2DL() {
            return detection2DL;
        }

        public void setDetection2DL(int detection2DL) {
            this.detection2DL = detection2DL;
        }

        public int getDetection3() {
            return detection3;
        }

        public void setDetection3(int detection3) {
            this.detection3 = detection3;
        }

        public int getDetection3UL() {
            return detection3UL;
        }

        public void setDetection3UL(int detection3UL) {
            this.detection3UL = detection3UL;
        }

        public int getDetection3DL() {
            return detection3DL;
        }

        public void setDetection3DL(int detection3DL) {
            this.detection3DL = detection3DL;
        }

        public int getDetection4() {
            return detection4;
        }

        public void setDetection4(int detection4) {
            this.detection4 = detection4;
        }

        public Object getDetection4UL() {
            return detection4UL;
        }

        public void setDetection4UL(Object detection4UL) {
            this.detection4UL = detection4UL;
        }

        public Object getDetection4DL() {
            return detection4DL;
        }

        public void setDetection4DL(Object detection4DL) {
            this.detection4DL = detection4DL;
        }

        public int getDetection5() {
            return detection5;
        }

        public void setDetection5(int detection5) {
            this.detection5 = detection5;
        }

        public int getDetection5UL() {
            return detection5UL;
        }

        public void setDetection5UL(int detection5UL) {
            this.detection5UL = detection5UL;
        }

        public int getDetection5DL() {
            return detection5DL;
        }

        public void setDetection5DL(int detection5DL) {
            this.detection5DL = detection5DL;
        }

        public int getDetection6() {
            return detection6;
        }

        public void setDetection6(int detection6) {
            this.detection6 = detection6;
        }

        public Object getDetection6UL() {
            return detection6UL;
        }

        public void setDetection6UL(Object detection6UL) {
            this.detection6UL = detection6UL;
        }

        public int getDetection6DL() {
            return detection6DL;
        }

        public void setDetection6DL(int detection6DL) {
            this.detection6DL = detection6DL;
        }

        public String getAirSched() {
            return airSched;
        }

        public void setAirSched(String airSched) {
            this.airSched = airSched;
        }

        public String getWaterSched() {
            return waterSched;
        }

        public void setWaterSched(String waterSched) {
            this.waterSched = waterSched;
        }

        public String getMudSched() {
            return mudSched;
        }

        public void setMudSched(String mudSched) {
            this.mudSched = mudSched;
        }

        public String getFlowSched() {
            return flowSched;
        }

        public void setFlowSched(String flowSched) {
            this.flowSched = flowSched;
        }

        public String getBackupSched() {
            return backupSched;
        }

        public void setBackupSched(String backupSched) {
            this.backupSched = backupSched;
        }

        public String getControlStrategy() {
            return controlStrategy;
        }

        public void setControlStrategy(String controlStrategy) {
            this.controlStrategy = controlStrategy;
        }

        public String getDeviceAlert() {
            return deviceAlert;
        }

        public void setDeviceAlert(String deviceAlert) {
            this.deviceAlert = deviceAlert;
        }

        public int getGratingDays() {
            return gratingDays;
        }

        public void setGratingDays(int gratingDays) {
            this.gratingDays = gratingDays;
        }

        public long getConfirmGratingTime() {
            return confirmGratingTime;
        }

        public void setConfirmGratingTime(long confirmGratingTime) {
            this.confirmGratingTime = confirmGratingTime;
        }

        public int getAction() {
            return action;
        }

        public void setAction(int action) {
            this.action = action;
        }

        public int getWaterflow() {
            return waterflow;
        }

        public void setWaterflow(int waterflow) {
            this.waterflow = waterflow;
        }

        public int getReduceCOD() {
            return reduceCOD;
        }

        public void setReduceCOD(int reduceCOD) {
            this.reduceCOD = reduceCOD;
        }

        public int getReduceNH3N() {
            return reduceNH3N;
        }

        public void setReduceNH3N(int reduceNH3N) {
            this.reduceNH3N = reduceNH3N;
        }

        public int getReduceP() {
            return reduceP;
        }

        public void setReduceP(int reduceP) {
            this.reduceP = reduceP;
        }

        public String getOperationNum() {
            return operationNum;
        }

        public void setOperationNum(String operationNum) {
            this.operationNum = operationNum;
        }

        public int getControlMethod() {
            return controlMethod;
        }

        public void setControlMethod(int controlMethod) {
            this.controlMethod = controlMethod;
        }

        public int getDetection7() {
            return detection7;
        }

        public void setDetection7(int detection7) {
            this.detection7 = detection7;
        }

        public int getDetection8() {
            return detection8;
        }

        public void setDetection8(int detection8) {
            this.detection8 = detection8;
        }

        public double getDetection9() {
            return detection9;
        }

        public void setDetection9(double detection9) {
            this.detection9 = detection9;
        }

        public long getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(long updateTime) {
            this.updateTime = updateTime;
        }

        public int getDetection10DL() {
            return detection10DL;
        }

        public void setDetection10DL(int detection10DL) {
            this.detection10DL = detection10DL;
        }

        public int getDetection11DL() {
            return detection11DL;
        }

        public void setDetection11DL(int detection11DL) {
            this.detection11DL = detection11DL;
        }

        public int getDetection12DL() {
            return detection12DL;
        }

        public void setDetection12DL(int detection12DL) {
            this.detection12DL = detection12DL;
        }

        public int getDetection13DL() {
            return detection13DL;
        }

        public void setDetection13DL(int detection13DL) {
            this.detection13DL = detection13DL;
        }

        public int getDetection14DL() {
            return detection14DL;
        }

        public void setDetection14DL(int detection14DL) {
            this.detection14DL = detection14DL;
        }

        public int getDetection15DL() {
            return detection15DL;
        }

        public void setDetection15DL(int detection15DL) {
            this.detection15DL = detection15DL;
        }

        public int getDetection16DL() {
            return detection16DL;
        }

        public void setDetection16DL(int detection16DL) {
            this.detection16DL = detection16DL;
        }

        public int getDetection17DL() {
            return detection17DL;
        }

        public void setDetection17DL(int detection17DL) {
            this.detection17DL = detection17DL;
        }

        public int getDetection18DL() {
            return detection18DL;
        }

        public void setDetection18DL(int detection18DL) {
            this.detection18DL = detection18DL;
        }

        public int getDetection19DL() {
            return detection19DL;
        }

        public void setDetection19DL(int detection19DL) {
            this.detection19DL = detection19DL;
        }

        public int getDetection20DL() {
            return detection20DL;
        }

        public void setDetection20DL(int detection20DL) {
            this.detection20DL = detection20DL;
        }

        public int getDetection10UL() {
            return detection10UL;
        }

        public void setDetection10UL(int detection10UL) {
            this.detection10UL = detection10UL;
        }

        public int getDetection11UL() {
            return detection11UL;
        }

        public void setDetection11UL(int detection11UL) {
            this.detection11UL = detection11UL;
        }

        public int getDetection12UL() {
            return detection12UL;
        }

        public void setDetection12UL(int detection12UL) {
            this.detection12UL = detection12UL;
        }

        public int getDetection13UL() {
            return detection13UL;
        }

        public void setDetection13UL(int detection13UL) {
            this.detection13UL = detection13UL;
        }

        public int getDetection14UL() {
            return detection14UL;
        }

        public void setDetection14UL(int detection14UL) {
            this.detection14UL = detection14UL;
        }

        public int getDetection15UL() {
            return detection15UL;
        }

        public void setDetection15UL(int detection15UL) {
            this.detection15UL = detection15UL;
        }

        public int getDetection16UL() {
            return detection16UL;
        }

        public void setDetection16UL(int detection16UL) {
            this.detection16UL = detection16UL;
        }

        public int getDetection17UL() {
            return detection17UL;
        }

        public void setDetection17UL(int detection17UL) {
            this.detection17UL = detection17UL;
        }

        public int getDetection18UL() {
            return detection18UL;
        }

        public void setDetection18UL(int detection18UL) {
            this.detection18UL = detection18UL;
        }

        public int getDetection19UL() {
            return detection19UL;
        }

        public void setDetection19UL(int detection19UL) {
            this.detection19UL = detection19UL;
        }

        public int getDetection20UL() {
            return detection20UL;
        }

        public void setDetection20UL(int detection20UL) {
            this.detection20UL = detection20UL;
        }

        public Object getDetection7UL() {
            return detection7UL;
        }

        public void setDetection7UL(Object detection7UL) {
            this.detection7UL = detection7UL;
        }

        public Object getDetection7DL() {
            return detection7DL;
        }

        public void setDetection7DL(Object detection7DL) {
            this.detection7DL = detection7DL;
        }

        public Object getDetection8UL() {
            return detection8UL;
        }

        public void setDetection8UL(Object detection8UL) {
            this.detection8UL = detection8UL;
        }

        public Object getDetection8DL() {
            return detection8DL;
        }

        public void setDetection8DL(Object detection8DL) {
            this.detection8DL = detection8DL;
        }

        public Object getDetection9UL() {
            return detection9UL;
        }

        public void setDetection9UL(Object detection9UL) {
            this.detection9UL = detection9UL;
        }

        public Object getDetection9DL() {
            return detection9DL;
        }

        public void setDetection9DL(Object detection9DL) {
            this.detection9DL = detection9DL;
        }

        public Object getRuntimePeriod1() {
            return runtimePeriod1;
        }

        public void setRuntimePeriod1(Object runtimePeriod1) {
            this.runtimePeriod1 = runtimePeriod1;
        }

        public Object getStoptimePeriod1() {
            return stoptimePeriod1;
        }

        public void setStoptimePeriod1(Object stoptimePeriod1) {
            this.stoptimePeriod1 = stoptimePeriod1;
        }

        public Object getRuntimePeriod2() {
            return runtimePeriod2;
        }

        public void setRuntimePeriod2(Object runtimePeriod2) {
            this.runtimePeriod2 = runtimePeriod2;
        }

        public Object getStoptimePeriod2() {
            return stoptimePeriod2;
        }

        public void setStoptimePeriod2(Object stoptimePeriod2) {
            this.stoptimePeriod2 = stoptimePeriod2;
        }

        public Object getRuntimePeriod3() {
            return runtimePeriod3;
        }

        public void setRuntimePeriod3(Object runtimePeriod3) {
            this.runtimePeriod3 = runtimePeriod3;
        }

        public Object getStoptimePeriod3() {
            return stoptimePeriod3;
        }

        public void setStoptimePeriod3(Object stoptimePeriod3) {
            this.stoptimePeriod3 = stoptimePeriod3;
        }

        public Object getRuntimePeriod4() {
            return runtimePeriod4;
        }

        public void setRuntimePeriod4(Object runtimePeriod4) {
            this.runtimePeriod4 = runtimePeriod4;
        }

        public Object getStoptimePeriod4() {
            return stoptimePeriod4;
        }

        public void setStoptimePeriod4(Object stoptimePeriod4) {
            this.stoptimePeriod4 = stoptimePeriod4;
        }

        public Object getRuntimePeriod5() {
            return runtimePeriod5;
        }

        public void setRuntimePeriod5(Object runtimePeriod5) {
            this.runtimePeriod5 = runtimePeriod5;
        }

        public Object getStoptimePeriod5() {
            return stoptimePeriod5;
        }

        public void setStoptimePeriod5(Object stoptimePeriod5) {
            this.stoptimePeriod5 = stoptimePeriod5;
        }

        public Object getRuntimePeriod6() {
            return runtimePeriod6;
        }

        public void setRuntimePeriod6(Object runtimePeriod6) {
            this.runtimePeriod6 = runtimePeriod6;
        }

        public Object getStoptimePeriod6() {
            return stoptimePeriod6;
        }

        public void setStoptimePeriod6(Object stoptimePeriod6) {
            this.stoptimePeriod6 = stoptimePeriod6;
        }

        public Object getRuntimePeriod7() {
            return runtimePeriod7;
        }

        public void setRuntimePeriod7(Object runtimePeriod7) {
            this.runtimePeriod7 = runtimePeriod7;
        }

        public Object getStoptimePeriod7() {
            return stoptimePeriod7;
        }

        public void setStoptimePeriod7(Object stoptimePeriod7) {
            this.stoptimePeriod7 = stoptimePeriod7;
        }

        public Object getRuntimePeriod8() {
            return runtimePeriod8;
        }

        public void setRuntimePeriod8(Object runtimePeriod8) {
            this.runtimePeriod8 = runtimePeriod8;
        }

        public Object getStoptimePeriod8() {
            return stoptimePeriod8;
        }

        public void setStoptimePeriod8(Object stoptimePeriod8) {
            this.stoptimePeriod8 = stoptimePeriod8;
        }

        public Object getRuntimePeriod9() {
            return runtimePeriod9;
        }

        public void setRuntimePeriod9(Object runtimePeriod9) {
            this.runtimePeriod9 = runtimePeriod9;
        }

        public Object getStoptimePeriod9() {
            return stoptimePeriod9;
        }

        public void setStoptimePeriod9(Object stoptimePeriod9) {
            this.stoptimePeriod9 = stoptimePeriod9;
        }

        public Object getRuntimePeriod10() {
            return runtimePeriod10;
        }

        public void setRuntimePeriod10(Object runtimePeriod10) {
            this.runtimePeriod10 = runtimePeriod10;
        }

        public Object getStoptimePeriod10() {
            return stoptimePeriod10;
        }

        public void setStoptimePeriod10(Object stoptimePeriod10) {
            this.stoptimePeriod10 = stoptimePeriod10;
        }

        public String getVideoUrl() {
            return videoUrl;
        }

        public void setVideoUrl(String videoUrl) {
            this.videoUrl = videoUrl;
        }

        public int getTonnage() {
            return tonnage;
        }

        public void setTonnage(int tonnage) {
            this.tonnage = tonnage;
        }

        public String getEmissionStandard() {
            return emissionStandard;
        }

        public void setEmissionStandard(String emissionStandard) {
            this.emissionStandard = emissionStandard;
        }

        public String getVideoPuid() {
            return videoPuid;
        }

        public void setVideoPuid(String videoPuid) {
            this.videoPuid = videoPuid;
        }

        public int getEquipment1Power() {
            return equipment1Power;
        }

        public void setEquipment1Power(int equipment1Power) {
            this.equipment1Power = equipment1Power;
        }

        public int getEquipment2Power() {
            return equipment2Power;
        }

        public void setEquipment2Power(int equipment2Power) {
            this.equipment2Power = equipment2Power;
        }

        public int getEquipment3Power() {
            return equipment3Power;
        }

        public void setEquipment3Power(int equipment3Power) {
            this.equipment3Power = equipment3Power;
        }

        public int getEquipment4Power() {
            return equipment4Power;
        }

        public void setEquipment4Power(int equipment4Power) {
            this.equipment4Power = equipment4Power;
        }

        public int getEquipment5Power() {
            return equipment5Power;
        }

        public void setEquipment5Power(int equipment5Power) {
            this.equipment5Power = equipment5Power;
        }

        public static class AreaBean {
            /**
             * id : 9
             * pid : 1
             * name : 无锡
             * coordinateX : 31.67727
             * coordinateY : 120.43651
             * introduce : 江苏省
             * isSendArea : true
             */

            private int id;
            private int pid;
            private String name;
            private double coordinateX;
            private double coordinateY;
            private String introduce;
            private String isSendArea;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getPid() {
                return pid;
            }

            public void setPid(int pid) {
                this.pid = pid;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
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

            public String getIntroduce() {
                return introduce;
            }

            public void setIntroduce(String introduce) {
                this.introduce = introduce;
            }

            public String getIsSendArea() {
                return isSendArea;
            }

            public void setIsSendArea(String isSendArea) {
                this.isSendArea = isSendArea;
            }
        }

        public static class AdministratorBean {
            /**
             * id : 1
             * name : wy
             * telephone : 18896724553
             * address : wx
             * email : wangyao@cmss.chinamobile.com
             */

            private int id;
            private String name;
            private String telephone;
            private String address;
            private String email;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getTelephone() {
                return telephone;
            }

            public void setTelephone(String telephone) {
                this.telephone = telephone;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }
        }
    }
}
