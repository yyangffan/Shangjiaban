package com.superc.shangjiaban.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by user on 2018/3/22.
 */

public class VillageCityBean {

    /**
     * code : 200
     * info : 获取成功
     * data : {"area":[{"id":3,"name":"裕华区","fu_id":2,"level":3,"isshow":1},{"id":4,"name":"长安区","fu_id":2,"level":3,"isshow":1},{"id":5,"name":"新华区","fu_id":2,"level":3,"isshow":1},{"id":6,"name":"鹿泉","fu_id":2,"level":3,"isshow":1},{"id":7,"name":"桥西区","fu_id":2,"level":3,"isshow":1},{"id":8,"name":"藁城","fu_id":2,"level":3,"isshow":1},{"id":9,"name":"高邑","fu_id":2,"level":3,"isshow":1},{"id":10,"name":"行唐","fu_id":2,"level":3,"isshow":1},{"id":11,"name":"晋州","fu_id":2,"level":3,"isshow":1},{"id":12,"name":"井陉矿区","fu_id":2,"level":3,"isshow":1},{"id":13,"name":"井陉","fu_id":2,"level":3,"isshow":1},{"id":14,"name":"栾城","fu_id":2,"level":3,"isshow":1},{"id":15,"name":"平山","fu_id":2,"level":3,"isshow":1},{"id":16,"name":"深泽","fu_id":2,"level":3,"isshow":1},{"id":17,"name":"辛集","fu_id":2,"level":3,"isshow":1},{"id":18,"name":"新乐","fu_id":2,"level":3,"isshow":1},{"id":19,"name":"元氏","fu_id":2,"level":3,"isshow":1},{"id":20,"name":"赞皇","fu_id":2,"level":3,"isshow":1},{"id":21,"name":"赵县","fu_id":2,"level":3,"isshow":1},{"id":22,"name":"正定","fu_id":2,"level":3,"isshow":1}],"xiaoqu":[{"id":4387,"wuyeid":31,"name":"龚尚嘉园","username":"马彬","phone":"13303044499","street":"","address":null},{"id":3789,"wuyeid":31,"name":"龙鼎天悦 ","username":"马彬","phone":"13303044499","street":"","address":null},{"id":4146,"wuyeid":31,"name":"龙溪城(别墅) ","username":"马彬","phone":"13303044499","street":"","address":null},{"id":3757,"wuyeid":31,"name":"龙溪城(公寓) ","username":"马彬","phone":"13303044499","street":"","address":null},{"id":4458,"wuyeid":31,"name":"黄金局宿舍 ","username":"马彬","phone":"13303044499","street":"","address":null},{"id":4322,"wuyeid":31,"name":"黄金公司宿舍","username":"马彬","phone":"13303044499","street":"","address":null},{"id":4213,"wuyeid":31,"name":"鸿名华府","username":"马彬","phone":"13303044499","street":"","address":null},{"id":3949,"wuyeid":31,"name":"高雅蓝庭 ","username":"马彬","phone":"13303044499","street":"","address":null},{"id":4006,"wuyeid":31,"name":"高远森林城 ","username":"马彬","phone":"13303044499","street":"","address":null},{"id":4158,"wuyeid":31,"name":"高远兰亭","username":"马彬","phone":"13303044499","street":"","address":null}]}
     */

    @SerializedName("code")
    private String code;
    @SerializedName("info")
    private String info;
    @SerializedName("data")
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        @SerializedName("area")
        private List<AreaBean> area;
        @SerializedName("xiaoqu")
        private List<XiaoquBean> xiaoqu;

        public List<AreaBean> getArea() {
            return area;
        }

        public void setArea(List<AreaBean> area) {
            this.area = area;
        }

        public List<XiaoquBean> getXiaoqu() {
            return xiaoqu;
        }

        public void setXiaoqu(List<XiaoquBean> xiaoqu) {
            this.xiaoqu = xiaoqu;
        }

        public static class AreaBean {
            /**
             * id : 3
             * name : 裕华区
             * fu_id : 2
             * level : 3
             * isshow : 1
             */

            @SerializedName("id")
            private String id;
            @SerializedName("name")
            private String name;
            @SerializedName("fu_id")
            private String fuId;
            @SerializedName("level")
            private String level;
            @SerializedName("isshow")
            private String isshow;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getFuId() {
                return fuId;
            }

            public void setFuId(String fuId) {
                this.fuId = fuId;
            }

            public String getLevel() {
                return level;
            }

            public void setLevel(String level) {
                this.level = level;
            }

            public String getIsshow() {
                return isshow;
            }

            public void setIsshow(String isshow) {
                this.isshow = isshow;
            }
        }

        public static class XiaoquBean {
            /**
             * id : 4387
             * wuyeid : 31
             * name : 龚尚嘉园
             * username : 马彬
             * phone : 13303044499
             * street : 
             * address : null
             */

            @SerializedName("id")
            private String id;
            @SerializedName("wuyeid")
            private String wuyeid;
            @SerializedName("name")
            private String name;
            @SerializedName("username")
            private String username;
            @SerializedName("phone")
            private String phone;
            @SerializedName("street")
            private String street;
            @SerializedName("address")
            private Object address;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getWuyeid() {
                return wuyeid;
            }

            public void setWuyeid(String wuyeid) {
                this.wuyeid = wuyeid;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
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

            public Object getAddress() {
                return address;
            }

            public void setAddress(Object address) {
                this.address = address;
            }
        }
    }
}
