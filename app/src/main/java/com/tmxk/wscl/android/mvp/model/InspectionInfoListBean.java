package com.tmxk.wscl.android.mvp.model;

import java.util.List;

public class InspectionInfoListBean {

    /**
     * metaInfo : {"code":200,"offset":1,"limit":1000,"totalPage":1,"totalSize":2}
     * object : [{"id":2,"sewage":{"id":23,"area":{"id":9,"pid":1,"name":"无锡","coordinateX":31.67727,"coordinateY":120.43651,"introduce":"江苏省","isSendArea":"true"},"controlId":23,"administrator":{"id":1,"name":"wy","telephone":"18896724553","address":"wx","email":"wangyao@cmss.chinamobile.com"},"shortTitle":"长浜河","name":"锡北泾西长浜河","address":null,"coordinateX":41.963177,"coordinateY":116.400246,"equipment1state":"1","equipment2state":"2","equipment3state":"1","equipment4state":"2","equipment5state":"2","detection1":0,"detection1UL":50,"detection1DL":-10,"detection2":0,"detection2UL":14,"detection2DL":1,"detection3":0,"detection3UL":900,"detection3DL":-900,"detection4":0,"detection4UL":null,"detection4DL":null,"detection5":0,"detection5UL":15,"detection5DL":2,"detection6":0,"detection6UL":null,"detection6DL":0,"airSched":"22222222222222222222222 ","waterSched":"22222222222222222222222 ","mudSched":"22222222222222222222222 ","flowSched":"22222222222222222222222 ","backupSched":"22222222222222222222222 ","controlStrategy":"1111 ","deviceAlert":"1111 ","gratingDays":0,"confirmGratingTime":1535965787000,"action":1,"waterflow":0,"reduceCOD":200,"reduceNH3N":30,"reduceP":8,"operationNum":"XB10SD30T-041","controlMethod":2,"detection7":3,"detection8":0,"detection9":654.41,"updateTime":1535965850000,"detection10DL":0,"detection11DL":0,"detection12DL":0,"detection13DL":0,"detection14DL":0,"detection15DL":0,"detection16DL":0,"detection17DL":0,"detection18DL":0,"detection19DL":0,"detection20DL":0,"detection10UL":0,"detection11UL":0,"detection12UL":0,"detection13UL":0,"detection14UL":0,"detection15UL":0,"detection16UL":0,"detection17UL":0,"detection18UL":0,"detection19UL":0,"detection20UL":0,"detection7UL":null,"detection7DL":null,"detection8UL":null,"detection8DL":null,"detection9UL":null,"detection9DL":null,"runtimePeriod1":null,"stoptimePeriod1":null,"runtimePeriod2":null,"stoptimePeriod2":null,"runtimePeriod3":null,"stoptimePeriod3":null,"runtimePeriod4":null,"stoptimePeriod4":null,"runtimePeriod5":null,"stoptimePeriod5":null,"runtimePeriod6":null,"stoptimePeriod6":null,"runtimePeriod7":null,"stoptimePeriod7":null,"runtimePeriod8":null,"stoptimePeriod8":null,"runtimePeriod9":null,"stoptimePeriod9":null,"runtimePeriod10":null,"stoptimePeriod10":null,"videoUrl":"rtmp://61.160.70.100:10100/app/live?token=5da079b694f211e685fbc81f66d85ed311d1c6090c9143cce0a9d2ae90e500d1","tonnage":40,"emissionStandard":"一级B","videoPuid":"201115202316439695","equipment1Power":0,"equipment2Power":0,"equipment3Power":0,"equipment4Power":0,"equipment5Power":0},"electricityMeterNo":"001","electricityMeterValue":"","inspectionTeamWork":"001","inspectionDate":1551369600000,"entries":"{\"0-1-6\":\"0-1-6-57\",\"0-1-7\":\"0-1-7-59\",\"0-1-8\":\"0-1-8-61\",\"0-1-9\":\"0-1-9-65\",\"0-1-10\":\"0-1-10-69\",\"0-1-11\":\"0-1-11-73\",\"0-1-12\":\"0-1-12-76\",\"0-1-13\":\"0-1-13-79\",\"0-1-14\":\"0-1-14-83\",\"0-1-15\":\"0-1-15-87\",\"0-1-16\":\"0-1-16-90\",\"0-1-17\":\"0-1-17-93\",\"0-1-18\":\"0-1-18-96\",\"0-1-19\":\"0-1-19-99\",\"0-1-20\":\"0-1-20-102\",\"0-1-21\":\"0-1-21-105\",\"0-1-22\":\"0-1-22-108\",\"0-1-23\":\"0-1-23-111\",\"0-1-24\":\"0-1-24-114\",\"0-1-25\":\"0-1-25-117\",\"0-2-26\":\"0-2-26-123\",\"0-2-27\":\"0-2-27-130\",\"0-2-28\":\"0-2-28-135\",\"0-2-29\":\"0-2-29-140\",\"0-2-30\":\"0-2-30-146\",\"0-2-31\":\"0-2-31-153\",\"0-2-32\":\"0-2-32-157\",\"0-2-33\":\"0-2-33-161\",\"0-3-34\":\"0-3-34-167\",\"0-3-35\":\"0-3-35-170\",\"0-3-36\":\"0-3-36-174\",\"0-3-37\":\"0-3-37-179\",\"0-3-38\":\"0-3-38-184\",\"0-3-39\":\"0-3-39-189\",\"0-3-40\":\"0-3-40-193\",\"0-3-41\":\"0-3-41-198\",\"0-3-42\":\"0-3-42-204\",\"0-3-43\":\"0-3-43-210\",\"0-4-44\":\"0-4-44-212\",\"0-4-45\":\"0-4-45-214\",\"0-4-46\":\"0-4-46-216\",\"0-4-47\":\"0-4-47-218\",\"0-4-48\":\"0-4-48-220\",\"0-4-49\":\"0-4-49-222\",\"0-4-50\":\"0-4-50-224\",\"0-4-51\":\"0-4-51-226\",\"0-4-52\":\"0-4-52-228\",\"0-5-53\":\"0-5-53-230\",\"0-5-54\":\"0-5-54-232\"}"},{"id":3,"sewage":{"id":23,"area":{"id":9,"pid":1,"name":"无锡","coordinateX":31.67727,"coordinateY":120.43651,"introduce":"江苏省","isSendArea":"true"},"controlId":23,"administrator":{"id":1,"name":"wy","telephone":"18896724553","address":"wx","email":"wangyao@cmss.chinamobile.com"},"shortTitle":"长浜河","name":"锡北泾西长浜河","address":null,"coordinateX":41.963177,"coordinateY":116.400246,"equipment1state":"1","equipment2state":"2","equipment3state":"1","equipment4state":"2","equipment5state":"2","detection1":0,"detection1UL":50,"detection1DL":-10,"detection2":0,"detection2UL":14,"detection2DL":1,"detection3":0,"detection3UL":900,"detection3DL":-900,"detection4":0,"detection4UL":null,"detection4DL":null,"detection5":0,"detection5UL":15,"detection5DL":2,"detection6":0,"detection6UL":null,"detection6DL":0,"airSched":"22222222222222222222222 ","waterSched":"22222222222222222222222 ","mudSched":"22222222222222222222222 ","flowSched":"22222222222222222222222 ","backupSched":"22222222222222222222222 ","controlStrategy":"1111 ","deviceAlert":"1111 ","gratingDays":0,"confirmGratingTime":1535965787000,"action":1,"waterflow":0,"reduceCOD":200,"reduceNH3N":30,"reduceP":8,"operationNum":"XB10SD30T-041","controlMethod":2,"detection7":3,"detection8":0,"detection9":654.41,"updateTime":1535965850000,"detection10DL":0,"detection11DL":0,"detection12DL":0,"detection13DL":0,"detection14DL":0,"detection15DL":0,"detection16DL":0,"detection17DL":0,"detection18DL":0,"detection19DL":0,"detection20DL":0,"detection10UL":0,"detection11UL":0,"detection12UL":0,"detection13UL":0,"detection14UL":0,"detection15UL":0,"detection16UL":0,"detection17UL":0,"detection18UL":0,"detection19UL":0,"detection20UL":0,"detection7UL":null,"detection7DL":null,"detection8UL":null,"detection8DL":null,"detection9UL":null,"detection9DL":null,"runtimePeriod1":null,"stoptimePeriod1":null,"runtimePeriod2":null,"stoptimePeriod2":null,"runtimePeriod3":null,"stoptimePeriod3":null,"runtimePeriod4":null,"stoptimePeriod4":null,"runtimePeriod5":null,"stoptimePeriod5":null,"runtimePeriod6":null,"stoptimePeriod6":null,"runtimePeriod7":null,"stoptimePeriod7":null,"runtimePeriod8":null,"stoptimePeriod8":null,"runtimePeriod9":null,"stoptimePeriod9":null,"runtimePeriod10":null,"stoptimePeriod10":null,"videoUrl":"rtmp://61.160.70.100:10100/app/live?token=5da079b694f211e685fbc81f66d85ed311d1c6090c9143cce0a9d2ae90e500d1","tonnage":40,"emissionStandard":"一级B","videoPuid":"201115202316439695","equipment1Power":0,"equipment2Power":0,"equipment3Power":0,"equipment4Power":0,"equipment5Power":0},"electricityMeterNo":"0001","electricityMeterValue":"","inspectionTeamWork":"test01","inspectionDate":1551369600000,"entries":"{\"0-1-6\":\"0-1-6-57\",\"0-1-7\":\"0-1-7-59\",\"0-1-8\":\"0-1-8-61\",\"0-1-9\":\"0-1-9-65\",\"0-1-10\":\"0-1-10-69\",\"0-1-11\":\"0-1-11-73\",\"0-1-12\":\"0-1-12-76\",\"0-1-13\":\"0-1-13-79\",\"0-1-14\":\"0-1-14-83\",\"0-1-15\":\"0-1-15-87\",\"0-1-16\":\"0-1-16-90\",\"0-1-17\":\"0-1-17-93\",\"0-1-18\":\"0-1-18-96\",\"0-1-19\":\"0-1-19-99\",\"0-1-20\":\"0-1-20-102\",\"0-1-21\":\"0-1-21-105\",\"0-1-22\":\"0-1-22-108\",\"0-1-23\":\"0-1-23-111\",\"0-1-24\":\"0-1-24-114\",\"0-1-25\":\"0-1-25-117\",\"0-2-26\":\"0-2-26-123\",\"0-2-27\":\"0-2-27-130\",\"0-2-28\":\"0-2-28-135\",\"0-2-29\":\"0-2-29-140\",\"0-2-30\":\"0-2-30-146\",\"0-2-31\":\"0-2-31-153\",\"0-2-32\":\"0-2-32-157\",\"0-2-33\":\"0-2-33-161\",\"0-3-34\":\"0-3-34-167\",\"0-3-35\":\"0-3-35-170\",\"0-3-36\":\"0-3-36-174\",\"0-3-37\":\"0-3-37-179\",\"0-3-38\":\"0-3-38-184\",\"0-3-39\":\"0-3-39-189\",\"0-3-40\":\"0-3-40-193\",\"0-3-41\":\"0-3-41-198\",\"0-3-42\":\"0-3-42-204\",\"0-3-43\":\"0-3-43-210\",\"0-4-44\":\"0-4-44-212\",\"0-4-45\":\"0-4-45-214\",\"0-4-46\":\"0-4-46-216\",\"0-4-47\":\"0-4-47-218\",\"0-4-48\":\"0-4-48-220\",\"0-4-49\":\"0-4-49-222\",\"0-4-50\":\"0-4-50-224\",\"0-4-51\":\"0-4-51-226\",\"0-4-52\":\"0-4-52-228\",\"0-5-53\":\"0-5-53-229\",\"0-5-54\":\"0-5-54-231\"}"}]
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
         * totalSize : 2
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
         * id : 2
         * sewage : {"id":23,"area":{"id":9,"pid":1,"name":"无锡","coordinateX":31.67727,"coordinateY":120.43651,"introduce":"江苏省","isSendArea":"true"},"controlId":23,"administrator":{"id":1,"name":"wy","telephone":"18896724553","address":"wx","email":"wangyao@cmss.chinamobile.com"},"shortTitle":"长浜河","name":"锡北泾西长浜河","address":null,"coordinateX":41.963177,"coordinateY":116.400246,"equipment1state":"1","equipment2state":"2","equipment3state":"1","equipment4state":"2","equipment5state":"2","detection1":0,"detection1UL":50,"detection1DL":-10,"detection2":0,"detection2UL":14,"detection2DL":1,"detection3":0,"detection3UL":900,"detection3DL":-900,"detection4":0,"detection4UL":null,"detection4DL":null,"detection5":0,"detection5UL":15,"detection5DL":2,"detection6":0,"detection6UL":null,"detection6DL":0,"airSched":"22222222222222222222222 ","waterSched":"22222222222222222222222 ","mudSched":"22222222222222222222222 ","flowSched":"22222222222222222222222 ","backupSched":"22222222222222222222222 ","controlStrategy":"1111 ","deviceAlert":"1111 ","gratingDays":0,"confirmGratingTime":1535965787000,"action":1,"waterflow":0,"reduceCOD":200,"reduceNH3N":30,"reduceP":8,"operationNum":"XB10SD30T-041","controlMethod":2,"detection7":3,"detection8":0,"detection9":654.41,"updateTime":1535965850000,"detection10DL":0,"detection11DL":0,"detection12DL":0,"detection13DL":0,"detection14DL":0,"detection15DL":0,"detection16DL":0,"detection17DL":0,"detection18DL":0,"detection19DL":0,"detection20DL":0,"detection10UL":0,"detection11UL":0,"detection12UL":0,"detection13UL":0,"detection14UL":0,"detection15UL":0,"detection16UL":0,"detection17UL":0,"detection18UL":0,"detection19UL":0,"detection20UL":0,"detection7UL":null,"detection7DL":null,"detection8UL":null,"detection8DL":null,"detection9UL":null,"detection9DL":null,"runtimePeriod1":null,"stoptimePeriod1":null,"runtimePeriod2":null,"stoptimePeriod2":null,"runtimePeriod3":null,"stoptimePeriod3":null,"runtimePeriod4":null,"stoptimePeriod4":null,"runtimePeriod5":null,"stoptimePeriod5":null,"runtimePeriod6":null,"stoptimePeriod6":null,"runtimePeriod7":null,"stoptimePeriod7":null,"runtimePeriod8":null,"stoptimePeriod8":null,"runtimePeriod9":null,"stoptimePeriod9":null,"runtimePeriod10":null,"stoptimePeriod10":null,"videoUrl":"rtmp://61.160.70.100:10100/app/live?token=5da079b694f211e685fbc81f66d85ed311d1c6090c9143cce0a9d2ae90e500d1","tonnage":40,"emissionStandard":"一级B","videoPuid":"201115202316439695","equipment1Power":0,"equipment2Power":0,"equipment3Power":0,"equipment4Power":0,"equipment5Power":0}
         * electricityMeterNo : 001
         * electricityMeterValue :
         * inspectionTeamWork : 001
         * inspectionDate : 1551369600000
         * entries : {"0-1-6":"0-1-6-57","0-1-7":"0-1-7-59","0-1-8":"0-1-8-61","0-1-9":"0-1-9-65","0-1-10":"0-1-10-69","0-1-11":"0-1-11-73","0-1-12":"0-1-12-76","0-1-13":"0-1-13-79","0-1-14":"0-1-14-83","0-1-15":"0-1-15-87","0-1-16":"0-1-16-90","0-1-17":"0-1-17-93","0-1-18":"0-1-18-96","0-1-19":"0-1-19-99","0-1-20":"0-1-20-102","0-1-21":"0-1-21-105","0-1-22":"0-1-22-108","0-1-23":"0-1-23-111","0-1-24":"0-1-24-114","0-1-25":"0-1-25-117","0-2-26":"0-2-26-123","0-2-27":"0-2-27-130","0-2-28":"0-2-28-135","0-2-29":"0-2-29-140","0-2-30":"0-2-30-146","0-2-31":"0-2-31-153","0-2-32":"0-2-32-157","0-2-33":"0-2-33-161","0-3-34":"0-3-34-167","0-3-35":"0-3-35-170","0-3-36":"0-3-36-174","0-3-37":"0-3-37-179","0-3-38":"0-3-38-184","0-3-39":"0-3-39-189","0-3-40":"0-3-40-193","0-3-41":"0-3-41-198","0-3-42":"0-3-42-204","0-3-43":"0-3-43-210","0-4-44":"0-4-44-212","0-4-45":"0-4-45-214","0-4-46":"0-4-46-216","0-4-47":"0-4-47-218","0-4-48":"0-4-48-220","0-4-49":"0-4-49-222","0-4-50":"0-4-50-224","0-4-51":"0-4-51-226","0-4-52":"0-4-52-228","0-5-53":"0-5-53-230","0-5-54":"0-5-54-232"}
         */

        private int id;
        private AssignmentOrderListBean.ObjectBean.SewageBean sewage;
        private String electricityMeterNo;
        private String electricityMeterValue;
        private String inspectionTeamWork;
        private String inspectionDate;
        private String entries;

        public ObjectBean() {
        }

        public ObjectBean(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public AssignmentOrderListBean.ObjectBean.SewageBean getSewage() {
            return sewage;
        }

        public void setSewage(AssignmentOrderListBean.ObjectBean.SewageBean sewage) {
            this.sewage = sewage;
        }

        public String getElectricityMeterNo() {
            return electricityMeterNo;
        }

        public void setElectricityMeterNo(String electricityMeterNo) {
            this.electricityMeterNo = electricityMeterNo;
        }

        public String getElectricityMeterValue() {
            return electricityMeterValue;
        }

        public void setElectricityMeterValue(String electricityMeterValue) {
            this.electricityMeterValue = electricityMeterValue;
        }

        public String getInspectionTeamWork() {
            return inspectionTeamWork;
        }

        public void setInspectionTeamWork(String inspectionTeamWork) {
            this.inspectionTeamWork = inspectionTeamWork;
        }

        public String getInspectionDate() {
            return inspectionDate;
        }

        public void setInspectionDate(String inspectionDate) {
            this.inspectionDate = inspectionDate;
        }

        public String getEntries() {
            return entries;
        }

        public void setEntries(String entries) {
            this.entries = entries;
        }
    }
}
