package com.superc.shangjiaban.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by user on 2018/3/23.
 */

public class PropertyDetailBean {

    /**
     * code : 200
     * info : 查询成功
     * date : {"admin_id":23,"user_name":"阳光小区","email":"126@126.com","role_id":2,"distribut_money":59,"phone":"13012365124","linkman":"张经理","channel":"工商银行","account":"62251452663521"}
     * count : 0
     * keyword :
     */

    @SerializedName("code")
    private int code;
    @SerializedName("info")
    private String info;
    @SerializedName("date")
    private DateBean date;
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

    public DateBean getDate() {
        return date;
    }

    public void setDate(DateBean date) {
        this.date = date;
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

    public static class DateBean {
        /**
         * admin_id : 23
         * user_name : 阳光小区
         * email : 126@126.com
         * role_id : 2
         * distribut_money : 59
         * phone : 13012365124
         * linkman : 张经理
         * channel : 工商银行
         * account : 62251452663521
         */

        @SerializedName("admin_id")
        private int adminId;
        @SerializedName("user_name")
        private String userName;
        @SerializedName("email")
        private String email;
        @SerializedName("role_id")
        private int roleId;
        @SerializedName("distribut_money")
        private String distributMoney;
        @SerializedName("phone")
        private String phone;
        @SerializedName("linkman")
        private String linkman;
        @SerializedName("channel")
        private String channel;
        @SerializedName("account")
        private String account;

        public int getAdminId() {
            return adminId;
        }

        public void setAdminId(int adminId) {
            this.adminId = adminId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public int getRoleId() {
            return roleId;
        }

        public void setRoleId(int roleId) {
            this.roleId = roleId;
        }

        public String getDistributMoney() {
            return distributMoney;
        }

        public void setDistributMoney(String distributMoney) {
            this.distributMoney = distributMoney;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getLinkman() {
            return linkman;
        }

        public void setLinkman(String linkman) {
            this.linkman = linkman;
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
    }
}
