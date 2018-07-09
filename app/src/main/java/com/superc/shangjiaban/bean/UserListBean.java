package com.superc.shangjiaban.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by user on 2018/3/13.
 */

public class UserListBean {

    /**
     * code : 200
     * info : 查询成功
     * users : [{"userid":26,"username":"","email":"","phone":"17367758880","nickname":"流浪火星","sex":0,"password":"9084347d0f89a18f9fa5b622a2be4c93","head_url":"/public/uploads/images/20170919/2d956a1dfff9c1304f5da6ba4e632e78.jpg","home_address":0,"community_id":0,"user_type":null,"modifier":null,"deletedby":null,"Insertdate":1505292825,"modifydate":"1516155045","deletedate":null,"num":"1","mobile":null,"total_amount":null,"user_money":88,"user_gold":0,"is_first":1},{"userid":32,"username":"","email":"","phone":"13303044499","nickname":"马彬","sex":0,"password":"324d1907d9ca6733d399b87affe48c74","head_url":"/public/uploads/images/20180112/c0219bac4813164c45fb63f87f3ee1ba.jpg","home_address":0,"community_id":0,"user_type":null,"modifier":null,"deletedby":null,"Insertdate":1505725954,"modifydate":"1516261687","deletedate":null,"num":"1","mobile":null,"total_amount":null,"user_money":73,"user_gold":0,"is_first":2},{"userid":13,"username":"","email":"","phone":"18813139617","nickname":"测试来的","sex":0,"password":"d8578edf8458ce06fbc5bb76a58c5ca4","head_url":"/public/uploads/images/20170912/0629892d575d06fcd49601676b05e232.jpg","home_address":0,"community_id":0,"user_type":null,"modifier":null,"deletedby":null,"Insertdate":1504171688,"modifydate":"1515988255","deletedate":null,"num":"1","mobile":null,"total_amount":null,"user_money":179,"user_gold":3599,"is_first":2},{"userid":25,"username":"","email":"","phone":"18831187787","nickname":"9686094266","sex":0,"password":"f46ef81f2464441ba58aeecbf654ee41","head_url":"/public/uploads/images/20170923/ce24511aa12793b8e5cc3690d6b70d83.jpg","home_address":0,"community_id":0,"user_type":null,"modifier":null,"deletedby":null,"Insertdate":1505291571,"modifydate":"1516602027","deletedate":null,"num":"1","mobile":null,"total_amount":null,"user_money":92,"user_gold":0,"is_first":1},{"userid":61,"username":null,"email":null,"phone":"18631056826","nickname":"6522955582","sex":0,"password":"e10adc3949ba59abbe56e057f20f883e","head_url":null,"home_address":0,"community_id":0,"user_type":null,"modifier":null,"deletedby":null,"Insertdate":1507882782,"modifydate":"0","deletedate":null,"num":"1","mobile":null,"total_amount":null,"user_money":97,"user_gold":0,"is_first":2},{"userid":61,"username":null,"email":null,"phone":"18631056826","nickname":"6522955582","sex":0,"password":"e10adc3949ba59abbe56e057f20f883e","head_url":null,"home_address":0,"community_id":0,"user_type":null,"modifier":null,"deletedby":null,"Insertdate":1507882782,"modifydate":"0","deletedate":null,"num":"1","mobile":null,"total_amount":null,"user_money":97,"user_gold":0,"is_first":2},{"userid":14,"username":"","email":"","phone":"15226572650","nickname":"哦哦哦莫","sex":0,"password":"e10adc3949ba59abbe56e057f20f883e","head_url":"/public/uploads/images/20170925/6300a49c87e70cb40776d75b94751e8c.jpg","home_address":0,"community_id":0,"user_type":null,"modifier":null,"deletedby":null,"Insertdate":null,"modifydate":"1516162531","deletedate":null,"num":"1","mobile":null,"total_amount":null,"user_money":302,"user_gold":7500,"is_first":2},{"userid":64,"username":null,"email":null,"phone":"13315161025","nickname":"冯总","sex":0,"password":"1b7206e41a0933bc42ec9624196f7bac","head_url":"/public/uploads/images/20171016/32c4bcda82f1c5a1ead8d97ca5fdd30f.jpg","home_address":0,"community_id":0,"user_type":null,"modifier":null,"deletedby":null,"Insertdate":1508125367,"modifydate":"1508126735","deletedate":null,"num":"1","mobile":null,"total_amount":null,"user_money":100,"user_gold":0,"is_first":2},{"userid":21,"username":"","email":"","phone":"15690326696","nickname":"5861587041","sex":0,"password":"e10adc3949ba59abbe56e057f20f883e","head_url":"/public/uploads/images/20171017/68a18808b4c6ba94a69496cc7e60a19d.jpg","home_address":0,"community_id":0,"user_type":null,"modifier":null,"deletedby":null,"Insertdate":1504752023,"modifydate":"1508226063","deletedate":null,"num":"1","mobile":null,"total_amount":null}]
     */

    @SerializedName("code")
    private String code;
    @SerializedName("info")
    private String info;
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

    public List<UsersBean> getUsers() {
        return users;
    }

    public void setUsers(List<UsersBean> users) {
        this.users = users;
    }

    public static class UsersBean {
        /**
         * userid : 26
         * username :
         * email :
         * phone : 17367758880
         * nickname : 流浪火星
         * sex : 0
         * password : 9084347d0f89a18f9fa5b622a2be4c93
         * head_url : /public/uploads/images/20170919/2d956a1dfff9c1304f5da6ba4e632e78.jpg
         * home_address : 0
         * community_id : 0
         * user_type : null
         * modifier : null
         * deletedby : null
         * Insertdate : 1505292825
         * modifydate : 1516155045
         * deletedate : null
         * num : 1
         * mobile : null
         * total_amount : null
         * user_money : 88
         * user_gold : 0
         * is_first : 1
         */

        @SerializedName("userid")
        private String userid;
        @SerializedName("username")
        private String username;
        @SerializedName("email")
        private String email;
        @SerializedName("phone")
        private String phone;
        @SerializedName("nickname")
        private String nickname;
        @SerializedName("sex")
        private String sex;
        @SerializedName("password")
        private String password;
        @SerializedName("head_url")
        private String headUrl;
        @SerializedName("home_address")
        private String homeAddress;
        @SerializedName("community_id")
        private String communityId;
        @SerializedName("user_type")
        private Object userType;
        @SerializedName("modifier")
        private Object modifier;
        @SerializedName("deletedby")
        private Object deletedby;
        @SerializedName("Insertdate")
        private String Insertdate;
        @SerializedName("modifydate")
        private String modifydate;
        @SerializedName("deletedate")
        private Object deletedate;
        @SerializedName("num")
        private String num;
        @SerializedName("mobile")
        private Object mobile;
        @SerializedName("total_amount")
        private Object totalAmount;
        @SerializedName("user_money")
        private String userMoney;
        @SerializedName("user_gold")
        private String userGold;
        @SerializedName("is_first")
        private String isFirst;

        private boolean isC;

        public boolean isC() {
            return isC;
        }

        public void setC(boolean c) {
            isC = c;
        }

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getHeadUrl() {
            return headUrl;
        }

        public void setHeadUrl(String headUrl) {
            this.headUrl = headUrl;
        }

        public String getHomeAddress() {
            return homeAddress;
        }

        public void setHomeAddress(String homeAddress) {
            this.homeAddress = homeAddress;
        }

        public String getCommunityId() {
            return communityId;
        }

        public void setCommunityId(String communityId) {
            this.communityId = communityId;
        }

        public Object getUserType() {
            return userType;
        }

        public void setUserType(Object userType) {
            this.userType = userType;
        }

        public Object getModifier() {
            return modifier;
        }

        public void setModifier(Object modifier) {
            this.modifier = modifier;
        }

        public Object getDeletedby() {
            return deletedby;
        }

        public void setDeletedby(Object deletedby) {
            this.deletedby = deletedby;
        }

        public String getInsertdate() {
            return Insertdate;
        }

        public void setInsertdate(String Insertdate) {
            this.Insertdate = Insertdate;
        }

        public String getModifydate() {
            return modifydate;
        }

        public void setModifydate(String modifydate) {
            this.modifydate = modifydate;
        }

        public Object getDeletedate() {
            return deletedate;
        }

        public void setDeletedate(Object deletedate) {
            this.deletedate = deletedate;
        }

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }

        public Object getMobile() {
            return mobile;
        }

        public void setMobile(Object mobile) {
            this.mobile = mobile;
        }

        public Object getTotalAmount() {
            return totalAmount;
        }

        public void setTotalAmount(Object totalAmount) {
            this.totalAmount = totalAmount;
        }

        public String getUserMoney() {
            return userMoney;
        }

        public void setUserMoney(String userMoney) {
            this.userMoney = userMoney;
        }

        public String getUserGold() {
            return userGold;
        }

        public void setUserGold(String userGold) {
            this.userGold = userGold;
        }

        public String getIsFirst() {
            return isFirst;
        }

        public void setIsFirst(String isFirst) {
            this.isFirst = isFirst;
        }
    }
}
