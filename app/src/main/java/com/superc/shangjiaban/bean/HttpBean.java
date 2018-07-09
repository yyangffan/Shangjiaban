package com.superc.shangjiaban.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by user on 2018/3/23.
 */

public class HttpBean {

    /**
     * code : 200
     * info : 删除成功
     * date :
     * count : 0
     * keyword :
     */

    @SerializedName("code")
    private int code;
    @SerializedName("info")
    private String info;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
