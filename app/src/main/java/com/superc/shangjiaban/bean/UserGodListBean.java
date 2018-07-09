package com.superc.shangjiaban.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by user on 2018/3/14.
 */

public class UserGodListBean {

    /**
     * code : 200
     * info : 查询成功
     * users : [{"id":51,"userid":13,"money":"10","add_time":"1508853364","state":"2","pay_money":"2","nickname":"测试来的","phone":"18813139617","title":null,"shengname":"河北省","shiname":"石家庄市","quname":"裕华区","xiaoquname":"高新香江岸"}]
     * start_time :
     * end_time :
     * keyword :
     */

    @SerializedName("code")
    private String code;
    @SerializedName("info")
    private String info;
    @SerializedName("start_time")
    private String startTime;
    @SerializedName("end_time")
    private String endTime;
    @SerializedName("keyword")
    private String keyword;
    @SerializedName("users")
    private List<UsersBean> users;

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

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public List<UsersBean> getUsers() {
        return users;
    }

    public void setUsers(List<UsersBean> users) {
        this.users = users;
    }

    public static class UsersBean {
        /**
         * id : 51
         * userid : 13
         * money : 10
         * add_time : 1508853364
         * state : 2
         * pay_money : 2
         * nickname : 测试来的
         * phone : 18813139617
         * title : null
         * shengname : 河北省
         * shiname : 石家庄市
         * quname : 裕华区
         * xiaoquname : 高新香江岸
         */

        @SerializedName("id")
        private String id;
        @SerializedName("userid")
        private String userid;
        @SerializedName("money")
        private String money;
        @SerializedName("add_time")
        private String addTime;
        @SerializedName("state")
        private String state;
        @SerializedName("pay_money")
        private String payMoney;
        @SerializedName("nickname")
        private String nickname;
        @SerializedName("phone")
        private String phone;
        @SerializedName("title")
        private Object title;
        @SerializedName("shengname")
        private String shengname;
        @SerializedName("shiname")
        private String shiname;
        @SerializedName("quname")
        private String quname;
        @SerializedName("xiaoquname")
        private String xiaoquname;

        private boolean isC;

        public boolean isC() {
            return isC;
        }

        public void setC(boolean c) {
            isC = c;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getAddTime() {
            return addTime;
        }

        public void setAddTime(String addTime) {
            this.addTime = addTime;
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

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public Object getTitle() {
            return title;
        }

        public void setTitle(Object title) {
            this.title = title;
        }

        public String getShengname() {
            return shengname;
        }

        public void setShengname(String shengname) {
            this.shengname = shengname;
        }

        public String getShiname() {
            return shiname;
        }

        public void setShiname(String shiname) {
            this.shiname = shiname;
        }

        public String getQuname() {
            return quname;
        }

        public void setQuname(String quname) {
            this.quname = quname;
        }

        public String getXiaoquname() {
            return xiaoquname;
        }

        public void setXiaoquname(String xiaoquname) {
            this.xiaoquname = xiaoquname;
        }
    }
}
