package com.superc.shangjiaban.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by user on 2018/3/23.
 */

public class PropertyYjBean {
    /**
     * code : 200
     * info : 查询成功
     * date : [{"id":3,"admin_id":23,"add_time":"1521708040","money":"1","state":"0","pay_money":"1","user_name":"阳光小区","phone":"13012365124","channel":"工商银行","account":"62251452663521"}]
     * count : 1
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
    @SerializedName("date")
    private List<DateBean> date;

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

    public List<DateBean> getDate() {
        return date;
    }

    public void setDate(List<DateBean> date) {
        this.date = date;
    }

    public static class DateBean {
        /**
         * id : 3
         * admin_id : 23
         * add_time : 1521708040
         * money : 1
         * state : 0
         * pay_money : 1
         * user_name : 阳光小区
         * phone : 13012365124
         * channel : 工商银行
         * account : 62251452663521
         */

        @SerializedName("id")
        private int id;
        @SerializedName("admin_id")
        private int adminId;
        @SerializedName("add_time")
        private String addTime;
        @SerializedName("money")
        private String money;
        @SerializedName("state")
        private String state;
        @SerializedName("pay_money")
        private String payMoney;
        @SerializedName("user_name")
        private String userName;
        @SerializedName("phone")
        private String phone;
        @SerializedName("channel")
        private String channel;
        @SerializedName("account")
        private String account;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getAdminId() {
            return adminId;
        }

        public void setAdminId(int adminId) {
            this.adminId = adminId;
        }

        public String getAddTime() {
            return addTime;
        }

        public void setAddTime(String addTime) {
            this.addTime = addTime;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getPayMoney() {
            return payMoney;
        }

        public void setPayMoney(String payMoney) {
            this.payMoney = payMoney;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
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
    }
}
