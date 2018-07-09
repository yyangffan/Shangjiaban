package com.superc.shangjiaban.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by user on 2018/3/14.
 */

public class VillageAllBean {
    /**
     * code : 200
     * info : 查询成功
     * users : [{"shengname":"河北省","shiname":"石家庄市","quname":"正定","community_id":"化肥四区","id":4462,"wuid":31},{"shengname":"河北省","shiname":"石家庄市","quname":"正定","community_id":"阳光青年城 ","id":4461,"wuid":31},{"shengname":"河北省","shiname":"石家庄市","quname":"正定","community_id":"永泰生活 ","id":4460,"wuid":31},{"shengname":"河北省","shiname":"石家庄市","quname":"正定","community_id":"石家庄市技术监督局宿舍 ","id":4459,"wuid":31},{"shengname":"河北省","shiname":"石家庄市","quname":"正定","community_id":"黄金局宿舍 ","id":4458,"wuid":31},{"shengname":"河北省","shiname":"石家庄市","quname":"正定","community_id":"糖烟酒宿舍(青园街)","id":4457,"wuid":31},{"shengname":"河北省","shiname":"石家庄市","quname":"正定","community_id":"盛世长安 ","id":4456,"wuid":31},{"shengname":"河北省","shiname":"石家庄市","quname":"正定","community_id":"柳林花园 ","id":4455,"wuid":31},{"shengname":"河北省","shiname":"石家庄市","quname":"正定","community_id":"市卫生局宿舍 ","id":4454,"wuid":31},{"shengname":"河北省","shiname":"石家庄市","quname":"正定","community_id":"天街商务壹号 ","id":4453,"wuid":31},{"shengname":"河北省","shiname":"石家庄市","quname":"正定","community_id":"桃源小镇","id":4452,"wuid":31},{"shengname":"河北省","shiname":"石家庄市","quname":"正定","community_id":"东方怡园 ","id":4451,"wuid":31},{"shengname":"河北省","shiname":"石家庄市","quname":"正定","community_id":"城建宿舍 ","id":4450,"wuid":31},{"shengname":"河北省","shiname":"石家庄市","quname":"正定","community_id":"紫香兰庭 ","id":4449,"wuid":31},{"shengname":"河北省","shiname":"石家庄市","quname":"正定","community_id":"海源小区 ","id":4448,"wuid":31}]
     * province_id :
     * city_id :
     * district_id :
     * key_word :
     */

    @SerializedName("code")
    private String code;
    @SerializedName("info")
    private String info;
    @SerializedName("province_id")
    private String provinceId;
    @SerializedName("city_id")
    private String cityId;
    @SerializedName("district_id")
    private String districtId;
    @SerializedName("key_word")
    private String keyWord;
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

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public List<UsersBean> getUsers() {
        return users;
    }

    public void setUsers(List<UsersBean> users) {
        this.users = users;
    }

    public static class UsersBean implements Serializable{
        /**
         * shengname : 河北省
         * shiname : 石家庄市
         * quname : 正定
         * community_id : 化肥四区
         * id : 4462
         * wuid : 31
         */

        @SerializedName("shengname")
        private String shengname;
        @SerializedName("shiname")
        private String shiname;
        @SerializedName("quname")
        private String quname;
        @SerializedName("community_id")
        private String communityId;
        @SerializedName("id")
        private String id;
        @SerializedName("wuid")
        private String wuid;

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

        public String getCommunityId() {
            return communityId;
        }

        public void setCommunityId(String communityId) {
            this.communityId = communityId;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getWuid() {
            return wuid;
        }

        public void setWuid(String wuid) {
            this.wuid = wuid;
        }
    }
}
