package com.superc.shangjiaban.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by user on 2018/3/23.
 */

public class VillageDetailBean {

    /**
     * code : 200
     * info : 查询成功
     * date : {"id":453,"wuid":23,"province_id":"1","city_id":"16","district_id":"17","community_id":"测试小区12","delete":"0","content":"","sheng":"636","shi":"2400","qu":"2402","username":"APP测试12","phone":"13000012012","street":"中山东路","state":1,"address":"","deliver_fee":0,"distribution_time":"","shengname":"河北省","shiname":"承德市","quname":"双桥区"}
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
         * id : 453
         * wuid : 23
         * province_id : 1
         * city_id : 16
         * district_id : 17
         * community_id : 测试小区12
         * delete : 0
         * content :
         * sheng : 636
         * shi : 2400
         * qu : 2402
         * username : APP测试12
         * phone : 13000012012
         * street : 中山东路
         * state : 1
         * address :
         * deliver_fee : 0
         * distribution_time :
         * shengname : 河北省
         * shiname : 承德市
         * quname : 双桥区
         */

        @SerializedName("id")
        private int id;
        @SerializedName("wuid")
        private int wuid;
        @SerializedName("province_id")
        private String provinceId;
        @SerializedName("city_id")
        private String cityId;
        @SerializedName("district_id")
        private String districtId;
        @SerializedName("community_id")
        private String communityId;
        @SerializedName("delete")
        private String delete;
        @SerializedName("content")
        private String content;
        @SerializedName("sheng")
        private String sheng;
        @SerializedName("shi")
        private String shi;
        @SerializedName("qu")
        private String qu;
        @SerializedName("username")
        private String username;
        @SerializedName("phone")
        private String phone;
        @SerializedName("street")
        private String street;
        @SerializedName("state")
        private int state;
        @SerializedName("address")
        private String address;
        @SerializedName("deliver_fee")
        private int deliverFee;
        @SerializedName("distribution_time")
        private String distributionTime;
        @SerializedName("shengname")
        private String shengname;
        @SerializedName("shiname")
        private String shiname;
        @SerializedName("quname")
        private String quname;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getWuid() {
            return wuid;
        }

        public void setWuid(int wuid) {
            this.wuid = wuid;
        }

        public String getProvinceId() {
            return provinceId;
        }

        public void setProvinceId(String provinceId) {
            this.provinceId = provinceId;
        }

        public String getCityId() {
            return cityId;
        }

        public void setCityId(String cityId) {
            this.cityId = cityId;
        }

        public String getDistrictId() {
            return districtId;
        }

        public void setDistrictId(String districtId) {
            this.districtId = districtId;
        }

        public String getCommunityId() {
            return communityId;
        }

        public void setCommunityId(String communityId) {
            this.communityId = communityId;
        }

        public String getDelete() {
            return delete;
        }

        public void setDelete(String delete) {
            this.delete = delete;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getSheng() {
            return sheng;
        }

        public void setSheng(String sheng) {
            this.sheng = sheng;
        }

        public String getShi() {
            return shi;
        }

        public void setShi(String shi) {
            this.shi = shi;
        }

        public String getQu() {
            return qu;
        }

        public void setQu(String qu) {
            this.qu = qu;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getStreet() {
            return street;
        }

        public void setStreet(String street) {
            this.street = street;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getDeliverFee() {
            return deliverFee;
        }

        public void setDeliverFee(int deliverFee) {
            this.deliverFee = deliverFee;
        }

        public String getDistributionTime() {
            return distributionTime;
        }

        public void setDistributionTime(String distributionTime) {
            this.distributionTime = distributionTime;
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
    }
}
