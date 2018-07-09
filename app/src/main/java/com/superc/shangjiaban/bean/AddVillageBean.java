package com.superc.shangjiaban.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by user on 2018/3/23.
 */

public class AddVillageBean {


    /**
     * code : 200
     * info : 添加成功
     * date :
     * count : 0
     * keyword :
     */

    @SerializedName("code")
    private int code;
    @SerializedName("info")
    private String info;
    @SerializedName("count")
    private int count;
    @SerializedName("keyword")
    private String keyword;

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


    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
