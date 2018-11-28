package com.tmxk.wscl.android.emuns;

import java.util.ArrayList;
import java.util.List;

public enum SewageStationEnum {
    SEWAGE000(0, "不限"),
    SEWAGE001(1, "001堰桥街道姑里社区陈家巷"),
    SEWAGE002(2, "002堰桥街道姑里社区惠巷"),
    SEWAGE003(3, "003堰桥街道姑里社区华巷"),
    SEWAGE004(4, "004前洲街道铁路桥村端楷桥"),
    SEWAGE005(5, "005前洲街道铁路桥村澎湾里"),
    SEWAGE006(6, "006前洲街道万里村葑庄"),
    SEWAGE007(7, "007钱桥街道东风社区唐巷"),
    SEWAGE008(8, "008钱桥街道盛峰社区盛北"),
    SEWAGE009(9, "009钱桥街道盛峰社区弄里"),
    SEWAGE010(10, "010钱桥街道盛峰社区庄桥头"),
    SEWAGE011(11, "011钱桥街道盛峰社区犁轭基"),
    SEWAGE012(12, "012钱桥街道洋溪社区薛巷"),
    SEWAGE013(13, "013洛社镇秦巷村庙前"),
    SEWAGE014(14, "014洛社镇正明村虞巷"),
    SEWAGE015(15, "015洛社镇秦巷村蒋巷"),
    SEWAGE016(16, "016洛社镇秦巷村小浜、西街");
    private int sewageId;
    private String sewageName;

    SewageStationEnum(int sewageId, String sewageName) {
        this.sewageId = sewageId;
        this.sewageName = sewageName;
    }

    public int getSewageId() {
        return sewageId;
    }

    public String getSewageName() {
        return sewageName;
    }

    public static List<String> getSewageNameList() {
        List<String> sewageNameList = new ArrayList<>();
        for (SewageStationEnum sewageStationEnum : SewageStationEnum.values()) {
            sewageNameList.add(sewageStationEnum.sewageName);
        }
        return sewageNameList;
    }
}
