package com.tmxk.wscl.android.mvp.model;

import java.util.List;

public class InspectionEntryBean {
    private long id;

    private Integer parentno;

    private Integer no;

    private String name;

    private String alias;

    private String type;

    private List<InspectionEntryBean> children;

    public List<InspectionEntryBean> getChildren() {
        return children;
    }

    public void setChildren(List<InspectionEntryBean> children) {
        this.children = children;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Integer getParentno() {
        return parentno;
    }

    public void setParentno(Integer parentno) {
        this.parentno = parentno;
    }

    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
