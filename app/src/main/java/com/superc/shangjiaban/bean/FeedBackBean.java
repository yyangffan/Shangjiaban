package com.superc.shangjiaban.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by user on 2018/3/23.
 */

public class FeedBackBean {

    /**
     * code : 200
     * info : 查询成功
     * date : [{"id":47,"userid":69,"xiaoqu_id":"915","phone":"15200078956","content":"兔兔我我","add_time":"1509419212","num":"1","delete":"0","nickname":"花花","community_id":"梧桐苑"}]
     * count : 22
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
         * id : 47
         * userid : 69
         * xiaoqu_id : 915
         * phone : 15200078956
         * content : 兔兔我我
         * add_time : 1509419212
         * num : 1
         * delete : 0
         * nickname : 花花
         * community_id : 梧桐苑
         */

        @SerializedName("id")
        private String id;
        @SerializedName("userid")
        private String userid;
        @SerializedName("xiaoqu_id")
        private String xiaoquId;
        @SerializedName("phone")
        private String phone;
        @SerializedName("content")
        private String content;
        @SerializedName("add_time")
        private String addTime;
        @SerializedName("num")
        private String num;
        @SerializedName("delete")
        private String delete;
        @SerializedName("nickname")
        private String nickname;
        @SerializedName("community_id")
        private String communityId;

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

        public String getXiaoquId() {
            return xiaoquId;
        }

        public void setXiaoquId(String xiaoquId) {
            this.xiaoquId = xiaoquId;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getAddTime() {
            return addTime;
        }

        public void setAddTime(String addTime) {
            this.addTime = addTime;
        }

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }

        public String getDelete() {
            return delete;
        }

        public void setDelete(String delete) {
            this.delete = delete;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getCommunityId() {
            return communityId;
        }

        public void setCommunityId(String communityId) {
            this.communityId = communityId;
        }
    }
}
