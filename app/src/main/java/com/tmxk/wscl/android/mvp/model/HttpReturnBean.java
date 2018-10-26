package com.tmxk.wscl.android.mvp.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class HttpReturnBean implements Serializable {
    private int status;
    private String result;
}