package com.tmxk.wscl.android.mvp.model;
//更新处理派单状态
public class AssignOrderPutBody {
    private String taskStatus;

    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    public AssignOrderPutBody(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    public AssignOrderPutBody() {
    }
}
