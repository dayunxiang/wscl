package com.tmxk.wscl.android.mvp.presenter;

import com.tmxk.wscl.android.emuns.DataTypeEnum;
import com.tmxk.wscl.android.mvp.model.AreaListBean;
import com.tmxk.wscl.android.mvp.model.AssignOrderPutBody;
import com.tmxk.wscl.android.mvp.model.AssignmentOrderListBean;
import com.tmxk.wscl.android.mvp.model.DeviceReplaceCreateBody;
import com.tmxk.wscl.android.mvp.model.DeviceReplaceListBean;
import com.tmxk.wscl.android.mvp.model.EquipRepairRecordListBean;
import com.tmxk.wscl.android.mvp.model.GatherProblemBean;
import com.tmxk.wscl.android.mvp.model.InspectionEntryBean;
import com.tmxk.wscl.android.mvp.model.InspectionEntryListBean;
import com.tmxk.wscl.android.mvp.model.InspectionInfoListBean;
import com.tmxk.wscl.android.mvp.model.InspectionUrls;
import com.tmxk.wscl.android.mvp.model.RepairmentBean;
import com.tmxk.wscl.android.mvp.model.RepairmentListBean;
import com.tmxk.wscl.android.mvp.model.SewageListBean;
import com.tmxk.wscl.android.mvp.model.UploadPicResBean;
import com.tmxk.wscl.android.mvp.model.UserListBean;
import com.tmxk.wscl.android.mvp.model.WaterTestListBean;
import com.tmxk.wscl.android.mvp.model.WaterTestManageBodyBean;
import com.tmxk.wscl.android.mvp.view.SewageArchiveView;
import com.tmxk.wscl.android.retrofit.ApiCallback;

import java.io.File;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.http.Multipart;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class OperatePresenter extends BasePresenter<SewageArchiveView> {
    private int page = 1;
    public OperatePresenter(SewageArchiveView view) {
        attachView(view);
    }

    public void getSewages(boolean isRefresh, int areaId) {
        if (isRefresh) {
            page = 1;
        } else {
            page++;
        }
        addSubscription(apiService.getSewageByArea(page, 1000, areaId),
                new ApiCallback<SewageListBean>() {
                    @Override
                    public void onSuccess(SewageListBean sewageListBean) {
                        mvpView.getDataSuccess(sewageListBean, null);
                    }

                    @Override
                    public void onFailure(String msg) {
                        mvpView.getDataFail(msg);
                    }

                    @Override
                    public void onFinish() {
                        mvpView.hideLoading();
                    }
                });
    }

    public void getAllAreas() {
        addSubscription(apiService.getAllArea(page, 1000),
                new ApiCallback<AreaListBean>() {
                    @Override
                    public void onSuccess(AreaListBean areaListBean) {
                        mvpView.getDataSuccess(areaListBean, null);
                    }

                    @Override
                    public void onFailure(String msg) {
                        mvpView.getDataFail(msg);
                    }

                    @Override
                    public void onFinish() {
                        mvpView.hideLoading();
                    }
                });
    }

    public void getAllAssignOrder(boolean isRefresh) {
        if (isRefresh) {
            page = 1;
        } else {
            page++;
        }
        addSubscription(apiService.getAllAssignOrder(page, 20),
                new ApiCallback<AssignmentOrderListBean>() {
                    @Override
                    public void onSuccess(AssignmentOrderListBean assignmentOrderListBean) {
                        mvpView.getDataSuccess(assignmentOrderListBean, null);
                    }

                    @Override
                    public void onFailure(String msg) {
                        mvpView.getDataFail(msg);
                    }

                    @Override
                    public void onFinish() {
                        mvpView.hideLoading();
                    }
                });
    }

    public void getAssignOrderByCondition(boolean isRefresh,int sysuserId,
                                          String taskStatus,String startTime,String endTime) {
        if (isRefresh) {
            page = 1;
        } else {
            page++;
        }
        addSubscription(apiService.getAssignOrderByCondition(sysuserId, taskStatus, startTime ,endTime ,page, 20),
                new ApiCallback<AssignmentOrderListBean>() {
                    @Override
                    public void onSuccess(AssignmentOrderListBean assignmentOrderListBean) {
                        mvpView.getDataSuccess(assignmentOrderListBean, null);
                    }

                    @Override
                    public void onFailure(String msg) {
                        mvpView.getDataFail(msg);
                    }

                    @Override
                    public void onFinish() {
                        mvpView.hideLoading();
                    }
                });
    }

    public void getAssignOrderByCondition(boolean isRefresh,String startTime,String endTime) {
        if (isRefresh) {
            page = 1;
        } else {
            page++;
        }
        addSubscription(apiService.getAssignOrderByCondition_3(startTime ,endTime ,page, 20),
                new ApiCallback<AssignmentOrderListBean>() {
                    @Override
                    public void onSuccess(AssignmentOrderListBean assignmentOrderListBean) {
                        mvpView.getDataSuccess(assignmentOrderListBean, null);
                    }

                    @Override
                    public void onFailure(String msg) {
                        mvpView.getDataFail(msg);
                    }

                    @Override
                    public void onFinish() {
                        mvpView.hideLoading();
                    }
                });
    }

    public void getAssignOrderByCondition(boolean isRefresh,String taskStatus,String startTime,String endTime) {
        if (isRefresh) {
            page = 1;
        } else {
            page++;
        }
        addSubscription(apiService.getAssignOrderByCondition_2(taskStatus, startTime ,endTime ,page, 20),
                new ApiCallback<AssignmentOrderListBean>() {
                    @Override
                    public void onSuccess(AssignmentOrderListBean assignmentOrderListBean) {
                        mvpView.getDataSuccess(assignmentOrderListBean, null);
                    }

                    @Override
                    public void onFailure(String msg) {
                        mvpView.getDataFail(msg);
                    }

                    @Override
                    public void onFinish() {
                        mvpView.hideLoading();
                    }
                });
    }

    public void getAssignOrderByCondition(boolean isRefresh,int sysuserId,String startTime,String endTime) {
        if (isRefresh) {
            page = 1;
        } else {
            page++;
        }
        addSubscription(apiService.getAssignOrderByCondition_1(sysuserId, startTime ,endTime ,page, 20),
                new ApiCallback<AssignmentOrderListBean>() {
                    @Override
                    public void onSuccess(AssignmentOrderListBean assignmentOrderListBean) {
                        mvpView.getDataSuccess(assignmentOrderListBean, null);
                    }

                    @Override
                    public void onFailure(String msg) {
                        mvpView.getDataFail(msg);
                    }

                    @Override
                    public void onFinish() {
                        mvpView.hideLoading();
                    }
                });
    }

    public void getRepairmentCondition(boolean isRefresh,int sewageId, String status, String startTime, String endTime) {
        if (isRefresh) {
            page = 1;
        } else {
            page++;
        }
        addSubscription(apiService.getRepairmentCondition(sewageId, status, startTime ,endTime ,page, 20),
                new ApiCallback<RepairmentListBean>() {
                    @Override
                    public void onSuccess(RepairmentListBean repairmentListBean) {
                        mvpView.getDataSuccess(repairmentListBean, null);
                    }

                    @Override
                    public void onFailure(String msg) {
                        mvpView.getDataFail(msg);
                    }

                    @Override
                    public void onFinish() {
                        mvpView.hideLoading();
                    }
                });
    }

    public void getRepairmentCondition(boolean isRefresh,String startTime, String endTime) {
        if (isRefresh) {
            page = 1;
        } else {
            page++;
        }
        addSubscription(apiService.getRepairmentCondition(startTime ,endTime ,page, 20),
                new ApiCallback<RepairmentListBean>() {
                    @Override
                    public void onSuccess(RepairmentListBean repairmentListBean) {
                        mvpView.getDataSuccess(repairmentListBean, null);
                    }

                    @Override
                    public void onFailure(String msg) {
                        mvpView.getDataFail(msg);
                    }

                    @Override
                    public void onFinish() {
                        mvpView.hideLoading();
                    }
                });
    }

    public void getRepairmentCondition(boolean isRefresh,int sewageId,String startTime, String endTime) {
        if (isRefresh) {
            page = 1;
        } else {
            page++;
        }
        addSubscription(apiService.getRepairmentCondition(sewageId, startTime ,endTime ,page, 20),
                new ApiCallback<RepairmentListBean>() {
                    @Override
                    public void onSuccess(RepairmentListBean repairmentListBean) {
                        mvpView.getDataSuccess(repairmentListBean, null);
                    }

                    @Override
                    public void onFailure(String msg) {
                        mvpView.getDataFail(msg);
                    }

                    @Override
                    public void onFinish() {
                        mvpView.hideLoading();
                    }
                });
    }

    public void getRepairmentCondition(boolean isRefresh, String status, String startTime, String endTime) {
        if (isRefresh) {
            page = 1;
        } else {
            page++;
        }
        addSubscription(apiService.getRepairmentCondition(status, startTime ,endTime ,page, 20),
                new ApiCallback<RepairmentListBean>() {
                    @Override
                    public void onSuccess(RepairmentListBean repairmentListBean) {
                        mvpView.getDataSuccess(repairmentListBean, null);
                    }

                    @Override
                    public void onFailure(String msg) {
                        mvpView.getDataFail(msg);
                    }

                    @Override
                    public void onFinish() {
                        mvpView.hideLoading();
                    }
                });
    }

    public void getInspectionCondition(boolean isRefresh, int sewage, String startTime, String endTime) {
        if (isRefresh) {
            page = 1;
        } else {
            page++;
        }
        addSubscription(apiService.getInspectionCondition(sewage, startTime ,endTime ,page, 20),
                new ApiCallback<InspectionInfoListBean>() {
                    @Override
                    public void onSuccess(InspectionInfoListBean inspectionInfoListBean) {
                        mvpView.getDataSuccess(inspectionInfoListBean, null);
                    }

                    @Override
                    public void onFailure(String msg) {
                        mvpView.getDataFail(msg);
                    }

                    @Override
                    public void onFinish() {
                        mvpView.hideLoading();
                    }
                });
    }

    public void getInspectionCondition(boolean isRefresh, String startTime, String endTime) {
        if (isRefresh) {
            page = 1;
        } else {
            page++;
        }
        addSubscription(apiService.getInspectionCondition(startTime ,endTime ,page, 20),
                new ApiCallback<InspectionInfoListBean>() {
                    @Override
                    public void onSuccess(InspectionInfoListBean inspectionInfoListBean) {
                        mvpView.getDataSuccess(inspectionInfoListBean, null);
                    }

                    @Override
                    public void onFailure(String msg) {
                        mvpView.getDataFail(msg);
                    }

                    @Override
                    public void onFinish() {
                        mvpView.hideLoading();
                    }
                });
    }

    public void getAllSysuser() {
        addSubscription(apiService.getSysUsers(1, 10000),
                new ApiCallback<UserListBean>() {
                    @Override
                    public void onSuccess(UserListBean userListBean) {
                        mvpView.getDataSuccess(userListBean, null);
                    }

                    @Override
                    public void onFailure(String msg) {
                        mvpView.getDataFail(msg);
                    }

                    @Override
                    public void onFinish() {
                        mvpView.hideLoading();
                    }
                });
    }

    public void updateOrderTypeStatus(int id, AssignOrderPutBody assignOrderPutBody) {
        addSubscription(apiService.updateOrderTypeStatus(id, assignOrderPutBody),
                new ApiCallback<ResponseBody>() {
                    @Override
                    public void onSuccess(ResponseBody responseBody) {
                        mvpView.getDataSuccess("", null);
                    }

                    @Override
                    public void onFailure(String msg) {
                        mvpView.getDataFail(msg);
                    }

                    @Override
                    public void onFinish() {
                        mvpView.hideLoading();
                    }
                });
    }

    public void createDeviceReplace(DeviceReplaceCreateBody deviceReplaceBody) {
        addSubscription(apiService.createDeviceReplace(deviceReplaceBody),
                new ApiCallback<DeviceReplaceCreateBody>() {
                    @Override
                    public void onSuccess(DeviceReplaceCreateBody deviceReplaceBody) {
                        mvpView.getDataSuccess(deviceReplaceBody, null);
                    }

                    @Override
                    public void onFailure(String msg) {
                        mvpView.getDataFail(msg);
                    }

                    @Override
                    public void onFinish() {
                        mvpView.hideLoading();
                    }
                });
    }

    public void createGatherProblem(GatherProblemBean gatherProblemBean) {
        addSubscription(apiService.createGatherProblem(gatherProblemBean),
                new ApiCallback<GatherProblemBean>() {
                    @Override
                    public void onSuccess(GatherProblemBean gatherProblem) {
                        mvpView.getDataSuccess(gatherProblem, null);
                    }

                    @Override
                    public void onFailure(String msg) {
                        mvpView.getDataFail(msg);
                    }

                    @Override
                    public void onFinish() {
                        mvpView.hideLoading();
                    }
                });
    }

    public void createInspectionUrl(InspectionUrls inspectionUrls) {
        addSubscription(apiService.createInspectionUrl(inspectionUrls),
                new ApiCallback<InspectionUrls>() {
                    @Override
                    public void onSuccess(InspectionUrls inspectionUrls) {
                        mvpView.getDataSuccess(inspectionUrls, null);
                    }

                    @Override
                    public void onFailure(String msg) {
                        mvpView.getDataFail(msg);
                    }

                    @Override
                    public void onFinish() {
                        mvpView.hideLoading();
                    }
                });
    }

    public void createRepairment(RepairmentBean repairmentBean) {
        addSubscription(apiService.createRepairment(repairmentBean),
                new ApiCallback<RepairmentBean>() {
                    @Override
                    public void onSuccess(RepairmentBean repairmentBean) {
                        mvpView.getDataSuccess(repairmentBean, null);
                    }

                    @Override
                    public void onFailure(String msg) {
                        mvpView.getDataFail(msg);
                    }

                    @Override
                    public void onFinish() {
                        mvpView.hideLoading();
                    }
                });
    }

    public void updateRepairStatus(RepairmentBean repairmentBean) {
        addSubscription(apiService.updateRepairStatus(repairmentBean),
                new ApiCallback<ResponseBody>() {
                    @Override
                    public void onSuccess(ResponseBody responseBody) {
                        mvpView.getDataSuccess("", null);
                    }

                    @Override
                    public void onFailure(String msg) {
                        mvpView.getDataFail(msg);
                    }

                    @Override
                    public void onFinish() {
                        mvpView.hideLoading();
                    }
                });
    }

    public void getAllSewages() {
        addSubscription(apiService.getAllSewage(1, 1000),
                new ApiCallback<SewageListBean>() {
                    @Override
                    public void onSuccess(SewageListBean sewageListBean) {
                        mvpView.getDataSuccess(sewageListBean, null);
                    }

                    @Override
                    public void onFailure(String msg) {
                        mvpView.getDataFail(msg);
                    }

                    @Override
                    public void onFinish() {
                        mvpView.hideLoading();
                    }
                });
    }

    public void uploadFile(String folderPath, String filename, MultipartBody.Part file) {
        addSubscription(apiService.uploadFile(folderPath, filename, file),
                new ApiCallback<UploadPicResBean>() {
                    @Override
                    public void onSuccess(UploadPicResBean uploadPicResBean) {
                        mvpView.getDataSuccess(uploadPicResBean, null);
                    }

                    @Override
                    public void onFailure(String msg) {
                        mvpView.getDataFail(msg);
                    }

                    @Override
                    public void onFinish() {
                        mvpView.hideLoading();
                    }
                });
    }

    public void getDeviceReplaceByCondition(boolean isRefresh, int sewageId,String proStartTime,
                                            String proEndTime,String repStartTime,String repEndTime) {
        if (isRefresh) {
            page = 1;
        } else {
            page++;
        }
        addSubscription(apiService.getDeviceReplaceByCondition(sewageId, proStartTime, proEndTime,
                repStartTime, repEndTime ,page, 20),
                new ApiCallback<DeviceReplaceListBean>() {
                    @Override
                    public void onSuccess(DeviceReplaceListBean deviceReplaceListBean) {
                        mvpView.getDataSuccess(deviceReplaceListBean, null);
                    }

                    @Override
                    public void onFailure(String msg) {
                        mvpView.getDataFail(msg);
                    }

                    @Override
                    public void onFinish() {
                        mvpView.hideLoading();
                    }
                });
    }

    public void getDeviceReplaceByCondition(boolean isRefresh,String proStartTime,
                                            String proEndTime,String repStartTime,String repEndTime) {
        if (isRefresh) {
            page = 1;
        } else {
            page++;
        }
        addSubscription(apiService.getDeviceReplaceByCondition(proStartTime, proEndTime,
                repStartTime, repEndTime ,page, 20),
                new ApiCallback<DeviceReplaceListBean>() {
                    @Override
                    public void onSuccess(DeviceReplaceListBean deviceReplaceListBean) {
                        mvpView.getDataSuccess(deviceReplaceListBean, null);
                    }

                    @Override
                    public void onFailure(String msg) {
                        mvpView.getDataFail(msg);
                    }

                    @Override
                    public void onFinish() {
                        mvpView.hideLoading();
                    }
                });
    }

    public void getEquipRepairRecordByCondition(boolean isRefresh,int sewageId, String repairMan,
                                                String startTime, String endTime) {
        if (isRefresh) {
            page = 1;
        } else {
            page++;
        }
        addSubscription(apiService.getEquipRepairRecordByCondition(sewageId, repairMan,
                startTime, endTime ,page, 20),
                new ApiCallback<EquipRepairRecordListBean>() {
                    @Override
                    public void onSuccess(EquipRepairRecordListBean equipRepairRecordListBean) {
                        mvpView.getDataSuccess(equipRepairRecordListBean, null);
                    }

                    @Override
                    public void onFailure(String msg) {
                        mvpView.getDataFail(msg);
                    }

                    @Override
                    public void onFinish() {
                        mvpView.hideLoading();
                    }
                });
    }

    public void getEquipRepairRecordByCondition(boolean isRefresh,int sewageId,
                                                String startTime, String endTime) {
        if (isRefresh) {
            page = 1;
        } else {
            page++;
        }
        addSubscription(apiService.getEquipRepairRecordByCondition(sewageId,
                startTime, endTime ,page, 20),
                new ApiCallback<EquipRepairRecordListBean>() {
                    @Override
                    public void onSuccess(EquipRepairRecordListBean equipRepairRecordListBean) {
                        mvpView.getDataSuccess(equipRepairRecordListBean, null);
                    }

                    @Override
                    public void onFailure(String msg) {
                        mvpView.getDataFail(msg);
                    }

                    @Override
                    public void onFinish() {
                        mvpView.hideLoading();
                    }
                });
    }

    public void getEquipRepairRecordByCondition(boolean isRefresh,String repairMan,
                                                String startTime, String endTime) {
        if (isRefresh) {
            page = 1;
        } else {
            page++;
        }
        addSubscription(apiService.getEquipRepairRecordByCondition(repairMan,
                startTime, endTime ,page, 20),
                new ApiCallback<EquipRepairRecordListBean>() {
                    @Override
                    public void onSuccess(EquipRepairRecordListBean equipRepairRecordListBean) {
                        mvpView.getDataSuccess(equipRepairRecordListBean, null);
                    }

                    @Override
                    public void onFailure(String msg) {
                        mvpView.getDataFail(msg);
                    }

                    @Override
                    public void onFinish() {
                        mvpView.hideLoading();
                    }
                });
    }

    public void getEquipRepairRecordByCondition(boolean isRefresh, String startTime, String endTime) {
        if (isRefresh) {
            page = 1;
        } else {
            page++;
        }
        addSubscription(apiService.getEquipRepairRecordByCondition(startTime, endTime ,page, 20),
                new ApiCallback<EquipRepairRecordListBean>() {
                    @Override
                    public void onSuccess(EquipRepairRecordListBean equipRepairRecordListBean) {
                        mvpView.getDataSuccess(equipRepairRecordListBean, null);
                    }

                    @Override
                    public void onFailure(String msg) {
                        mvpView.getDataFail(msg);
                    }

                    @Override
                    public void onFinish() {
                        mvpView.hideLoading();
                    }
                });
    }

    public void getWaterTestManagerByCondition(boolean isRefresh,int sewageId,
                                                String startTime, String endTime) {
        if (isRefresh) {
            page = 1;
        } else {
            page++;
        }
        addSubscription(apiService.getWaterTestManagerByCondition(sewageId,
                startTime, endTime ,page, 20),
                new ApiCallback<WaterTestListBean>() {
                    @Override
                    public void onSuccess(WaterTestListBean waterTestListBean) {
                        mvpView.getDataSuccess(waterTestListBean, null);
                    }

                    @Override
                    public void onFailure(String msg) {
                        mvpView.getDataFail(msg);
                    }

                    @Override
                    public void onFinish() {
                        mvpView.hideLoading();
                    }
                });
    }

    public void getWaterTestManagerByCondition(boolean isRefresh,
                                                String startTime, String endTime) {
        if (isRefresh) {
            page = 1;
        } else {
            page++;
        }
        addSubscription(apiService.getWaterTestManagerByCondition(startTime, endTime ,page, 20),
                new ApiCallback<WaterTestListBean>() {
                    @Override
                    public void onSuccess(WaterTestListBean waterTestListBean) {
                        mvpView.getDataSuccess(waterTestListBean, null);
                    }

                    @Override
                    public void onFailure(String msg) {
                        mvpView.getDataFail(msg);
                    }

                    @Override
                    public void onFinish() {
                        mvpView.hideLoading();
                    }
                });
    }

    public void getWaterTestTop12(int sewageId) {
        addSubscription(apiService.getWaterTestTop12(sewageId),
                new ApiCallback<WaterTestListBean>() {
                    @Override
                    public void onSuccess(WaterTestListBean waterTestListBean) {
                        mvpView.getDataSuccess(waterTestListBean, null);
                    }

                    @Override
                    public void onFailure(String msg) {
                        mvpView.getDataFail(msg);
                    }

                    @Override
                    public void onFinish() {
                        mvpView.hideLoading();
                    }
                });
    }

    public void createWaterTestManager(WaterTestManageBodyBean waterTest) {
        addSubscription(apiService.createWaterTestManager(waterTest),
                new ApiCallback<WaterTestManageBodyBean>() {
                    @Override
                    public void onSuccess(WaterTestManageBodyBean waterTest) {
                        mvpView.getDataSuccess(waterTest, null);
                    }

                    @Override
                    public void onFailure(String msg) {
                        mvpView.getDataFail(msg);
                    }

                    @Override
                    public void onFinish() {
                        mvpView.hideLoading();
                    }
                });
    }

    public void createInspectionInfo(InspectionInfoListBean.ObjectBean objectBean) {
        addSubscription(apiService.createInspectionInfo(objectBean),
                new ApiCallback<InspectionInfoListBean.ObjectBean>() {
                    @Override
                    public void onSuccess(InspectionInfoListBean.ObjectBean objectBean) {
                        mvpView.getDataSuccess(objectBean, null);
                    }

                    @Override
                    public void onFailure(String msg) {
                        mvpView.getDataFail(msg);
                    }

                    @Override
                    public void onFinish() {
                        mvpView.hideLoading();
                    }
                });
    }

    public void getAllInspectinEntries() {
        addSubscription(apiService.getAllInspectinEntries(1,1000),
                new ApiCallback<InspectionEntryListBean>() {
                    @Override
                    public void onSuccess(InspectionEntryListBean inspectionEntryListBean) {
                        mvpView.getDataSuccess(inspectionEntryListBean, DataTypeEnum.TYPE00);
                    }

                    @Override
                    public void onFailure(String msg) {
                        mvpView.getDataFail(msg);
                    }

                    @Override
                    public void onFinish() {
                        mvpView.hideLoading();
                    }
                });
    }

    public void getInspectinEntriesByParentno(int parentno) {
        addSubscription(apiService.getInspectinEntriesByParentno(parentno, 1,1000),
                new ApiCallback<InspectionEntryListBean>() {
                    @Override
                    public void onSuccess(InspectionEntryListBean inspectionEntryListBean) {
                        mvpView.getDataSuccess(inspectionEntryListBean, DataTypeEnum.TYPE01);
                    }

                    @Override
                    public void onFailure(String msg) {
                        mvpView.getDataFail(msg);
                    }

                    @Override
                    public void onFinish() {
                        mvpView.hideLoading();
                    }
                });
    }

    public void getInspectinEntriesByType(String type) {
        addSubscription(apiService.getInspectinEntriesByType(type, 1,1000),
                new ApiCallback<InspectionEntryListBean>() {
                    @Override
                    public void onSuccess(InspectionEntryListBean inspectionEntryListBean) {
                        mvpView.getDataSuccess(inspectionEntryListBean, DataTypeEnum.TYPE02);
                    }

                    @Override
                    public void onFailure(String msg) {
                        mvpView.getDataFail(msg);
                    }

                    @Override
                    public void onFinish() {
                        mvpView.hideLoading();
                    }
                });
    }

    public void getInspectinEntriesByNoAndChildren(int no) {
        addSubscription(apiService.getInspectinEntriesByNoAndChildren(no),
                new ApiCallback<InspectionEntryBean>() {
                    @Override
                    public void onSuccess(InspectionEntryBean inspectionEntryBean) {
                        mvpView.getDataSuccess(inspectionEntryBean, null);
                    }

                    @Override
                    public void onFailure(String msg) {
                        mvpView.getDataFail(msg);
                    }

                    @Override
                    public void onFinish() {
                        mvpView.hideLoading();
                    }
                });
    }

}