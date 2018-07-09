package com.superc.shangjiaban.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by user on 2018/3/24.
 */

public class UserZhuanZhangBean {

    /**
     * code : 200
     * info : 查询成功
     * date : [{"admin_id":23,"user_name":"阳光小区","linkman":"张经理","phone":"13012365124","channel":"工商银行","account":"62251452663521","id":1,"money":0.01}]
     * count : 1
     * keyword :
     */

    @SerializedName("code")
    private String code;
    @SerializedName("info")
    private String info;
    @SerializedName("count")
    private String count;
    @SerializedName("keyword")
    private String keyword;
    @SerializedName("date")
    private List<DateBean> date;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public List<DateBean> getDate() {
        return date;
    }

    public void setDate(List<DateBean> date) {
        this.date = date;
    }

    public static class DateBean {
        /**
         * admin_id : 23
         * user_name : 阳光小区
         * linkman : 张经理
         * phone : 13012365124
         * channel : 工商银行
         * account : 62251452663521
         * id : 1
         * money : 0.01
         */

        @SerializedName("admin_id")
        private String adminId;
        @SerializedName("user_name")
        private String userName;
        @SerializedName("linkman")
        private String linkman;
        @SerializedName("phone")
        private String phone;
        @SerializedName("channel")
        private String channel;
        @SerializedName("account")
        private String account;
        @SerializedName("id")
        private String id;
        @SerializedName("money")
        private String money;

        public String getAdminId() {
            return adminId;
        }

        public void setAdminId(String adminId) {
            this.adminId = adminId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getLinkman() {
            return linkman;
        }

        public void setLinkman(String linkman) {
            this.linkman = linkman;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getChannel() {
            return channel;
        }

        public void setChannel(String channel) {
            this.channel = channel;
        }

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }
    }
}
