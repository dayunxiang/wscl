package com.tmxk.wscl.android.mvp.model;

import java.util.List;

public class WaterTestListBean {
    /**
     * metaInfo : {"code":200,"offset":1,"limit":1000,"totalPage":1,"totalSize":1}
     * object : [{"id":1,"sewage":{"id":23,"area":{"id":9,"pid":1,"name":"无锡","coordinateX":31.67727,"coordinateY":120.43651,"introduce":"江苏省","isSendArea":"true"},"controlId":23,"administrator":{"id":1,"name":"wy","telephone":"18896724553","address":"wx","email":"wangyao@cmss.chinamobile.com"},"shortTitle":"长浜河","name":"锡北泾西长浜河","address":null,"coordinateX":41.963177,"coordinateY":116.400246,"equipment1state":"1","equipment2state":"2","equipment3state":"1","equipment4state":"2","equipment5state":"2","detection1":0,"detection1UL":50,"detection1DL":-10,"detection2":0,"detection2UL":14,"detection2DL":1,"detection3":0,"detection3UL":900,"detection3DL":-900,"detection4":0,"detection4UL":null,"detection4DL":null,"detection5":0,"detection5UL":15,"detection5DL":2,"detection6":0,"detection6UL":null,"detection6DL":0,"airSched":"22222222222222222222222 ","waterSched":"22222222222222222222222 ","mudSched":"22222222222222222222222 ","flowSched":"22222222222222222222222 ","backupSched":"22222222222222222222222 ","controlStrategy":"1111 ","deviceAlert":"1111 ","gratingDays":0,"confirmGratingTime":1535965787000,"action":1,"waterflow":0,"reduceCOD":200,"reduceNH3N":30,"reduceP":8,"operationNum":"XB10SD30T-041","controlMethod":2,"detection7":3,"detection8":0,"detection9":654.41,"updateTime":1535965850000,"detection10DL":0,"detection11DL":0,"detection12DL":0,"detection13DL":0,"detection14DL":0,"detection15DL":0,"detection16DL":0,"detection17DL":0,"detection18DL":0,"detection19DL":0,"detection20DL":0,"detection10UL":0,"detection11UL":0,"detection12UL":0,"detection13UL":0,"detection14UL":0,"detection15UL":0,"detection16UL":0,"detection17UL":0,"detection18UL":0,"detection19UL":0,"detection20UL":0,"detection7UL":null,"detection7DL":null,"detection8UL":null,"detection8DL":null,"detection9UL":null,"detection9DL":null,"runtimePeriod1":null,"stoptimePeriod1":null,"runtimePeriod2":null,"stoptimePeriod2":null,"runtimePeriod3":null,"stoptimePeriod3":null,"runtimePeriod4":null,"stoptimePeriod4":null,"runtimePeriod5":null,"stoptimePeriod5":null,"runtimePeriod6":null,"stoptimePeriod6":null,"runtimePeriod7":null,"stoptimePeriod7":null,"runtimePeriod8":null,"stoptimePeriod8":null,"runtimePeriod9":null,"stoptimePeriod9":null,"runtimePeriod10":null,"stoptimePeriod10":null,"videoUrl":"rtmp://61.160.70.100:10100/app/live?token=5da079b694f211e685fbc81f66d85ed311d1c6090c9143cce0a9d2ae90e500d1","tonnage":40,"emissionStandard":"一级B","videoPuid":"201115202316439695","equipment1Power":0,"equipment2Power":0,"equipment3Power":0,"equipment4Power":0,"equipment5Power":0},"testingTime":1556113245010,"outCod":1,"outNh3n":1,"outP":1,"inCod":1,"inNh3n":1,"inP":1,"reportNo":"001"}]
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
         * limit : 1000
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
         * id : 1
         * sewage : {"id":23,"area":{"id":9,"pid":1,"name":"无锡","coordinateX":31.67727,"coordinateY":120.43651,"introduce":"江苏省","isSendArea":"true"},"controlId":23,"administrator":{"id":1,"name":"wy","telephone":"18896724553","address":"wx","email":"wangyao@cmss.chinamobile.com"},"shortTitle":"长浜河","name":"锡北泾西长浜河","address":null,"coordinateX":41.963177,"coordinateY":116.400246,"equipment1state":"1","equipment2state":"2","equipment3state":"1","equipment4state":"2","equipment5state":"2","detection1":0,"detection1UL":50,"detection1DL":-10,"detection2":0,"detection2UL":14,"detection2DL":1,"detection3":0,"detection3UL":900,"detection3DL":-900,"detection4":0,"detection4UL":null,"detection4DL":null,"detection5":0,"detection5UL":15,"detection5DL":2,"detection6":0,"detection6UL":null,"detection6DL":0,"airSched":"22222222222222222222222 ","waterSched":"22222222222222222222222 ","mudSched":"22222222222222222222222 ","flowSched":"22222222222222222222222 ","backupSched":"22222222222222222222222 ","controlStrategy":"1111 ","deviceAlert":"1111 ","gratingDays":0,"confirmGratingTime":1535965787000,"action":1,"waterflow":0,"reduceCOD":200,"reduceNH3N":30,"reduceP":8,"operationNum":"XB10SD30T-041","controlMethod":2,"detection7":3,"detection8":0,"detection9":654.41,"updateTime":1535965850000,"detection10DL":0,"detection11DL":0,"detection12DL":0,"detection13DL":0,"detection14DL":0,"detection15DL":0,"detection16DL":0,"detection17DL":0,"detection18DL":0,"detection19DL":0,"detection20DL":0,"detection10UL":0,"detection11UL":0,"detection12UL":0,"detection13UL":0,"detection14UL":0,"detection15UL":0,"detection16UL":0,"detection17UL":0,"detection18UL":0,"detection19UL":0,"detection20UL":0,"detection7UL":null,"detection7DL":null,"detection8UL":null,"detection8DL":null,"detection9UL":null,"detection9DL":null,"runtimePeriod1":null,"stoptimePeriod1":null,"runtimePeriod2":null,"stoptimePeriod2":null,"runtimePeriod3":null,"stoptimePeriod3":null,"runtimePeriod4":null,"stoptimePeriod4":null,"runtimePeriod5":null,"stoptimePeriod5":null,"runtimePeriod6":null,"stoptimePeriod6":null,"runtimePeriod7":null,"stoptimePeriod7":null,"runtimePeriod8":null,"stoptimePeriod8":null,"runtimePeriod9":null,"stoptimePeriod9":null,"runtimePeriod10":null,"stoptimePeriod10":null,"videoUrl":"rtmp://61.160.70.100:10100/app/live?token=5da079b694f211e685fbc81f66d85ed311d1c6090c9143cce0a9d2ae90e500d1","tonnage":40,"emissionStandard":"一级B","videoPuid":"201115202316439695","equipment1Power":0,"equipment2Power":0,"equipment3Power":0,"equipment4Power":0,"equipment5Power":0}
         * testingTime : 1556113245010
         * outCod : 1
         * outNh3n : 1
         * outP : 1
         * inCod : 1
         * inNh3n : 1
         * inP : 1
         * reportNo : 001
         */

        private int id;
        private AssignmentOrderListBean.ObjectBean.SewageBean sewage;
        private long testingTime;
        private Float outCod;
        private Float outNh3n;
        private Float outP;
        private Float inCod;
        private Float inNh3n;
        private Float inP;
        private String reportNo;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
        public long getTestingTime() {
            return testingTime;
        }

        public void setTestingTime(long testingTime) {
            this.testingTime = testingTime;
        }

        public Float getOutCod() {
            return outCod;
        }

        public void setOutCod(Float outCod) {
            this.outCod = outCod;
        }

        public Float getOutNh3n() {
            return outNh3n;
        }

        public void setOutNh3n(Float outNh3n) {
            this.outNh3n = outNh3n;
        }

        public Float getOutP() {
            return outP;
        }

        public void setOutP(Float outP) {
            this.outP = outP;
        }

        public Float getInCod() {
            return inCod;
        }

        public void setInCod(Float inCod) {
            this.inCod = inCod;
        }

        public Float getInNh3n() {
            return inNh3n;
        }

        public void setInNh3n(Float inNh3n) {
            this.inNh3n = inNh3n;
        }

        public Float getInP() {
            return inP;
        }

        public void setInP(Float inP) {
            this.inP = inP;
        }

        public String getReportNo() {
            return reportNo;
        }

        public void setReportNo(String reportNo) {
            this.reportNo = reportNo;
        }

        public AssignmentOrderListBean.ObjectBean.SewageBean getSewage() {
            return sewage;
        }

        public void setSewage(AssignmentOrderListBean.ObjectBean.SewageBean sewage) {
            this.sewage = sewage;
        }
    }
}
