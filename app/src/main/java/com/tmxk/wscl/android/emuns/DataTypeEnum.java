package com.tmxk.wscl.android.emuns;

public enum DataTypeEnum {

    TYPE00(0, "类型1"),
    TYPE01(1, "类型2"),
    TYPE02(2, "类型3");

    private int typeId;
    private String typeName;

    DataTypeEnum(int typeId, String typeName) {
        this.typeId = typeId;
        this.typeName = typeName;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
