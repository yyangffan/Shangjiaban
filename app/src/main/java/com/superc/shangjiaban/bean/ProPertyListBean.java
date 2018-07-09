package com.superc.shangjiaban.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * 物业管理用户列表实体类
 */

public class ProPertyListBean {

    /**
     * code : 200
     * info : 查询成功
     * date : [{"admin_id":31,"user_name":"百姓物业","email":"xiaochaokeji@126.com","add_time":1507864514,"distribut_money":20,"phone":null,"linkman":null}]
     * count : 2
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

    public static class DateBean implements Serializable{
        /**
         * admin_id : 31
         * user_name : 百姓物业
         * email : xiaochaokeji@126.com
         * add_time : 1507864514
         * distribut_money : 20
         * phone : null
         * linkman : null
         */

        @SerializedName("admin_id")
        private String adminId;
        @SerializedName("user_name")
        private String userName;
        @SerializedName("email")
        private String email;
        @SerializedName("add_time")
        private String addTime;
        @SerializedName("distribut_money")
        private String distributMoney;
        @SerializedName("phone")
        private String phone;
        @SerializedName("linkman")
        private String linkman;

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

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getAddTime() {
            return addTime;
        }

        public void setAddTime(String addTime) {
            this.addTime = addTime;
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
    }
}
