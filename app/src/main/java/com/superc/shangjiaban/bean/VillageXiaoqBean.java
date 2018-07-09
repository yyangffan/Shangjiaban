package com.superc.shangjiaban.bean;

import java.util.List;

/**
 * Created by user on 2018/4/28.
 */

public class VillageXiaoqBean {

    /**
     * code : 200
     * info : 查询成功
     * date : [{"id":310,"name":"栖凤园"},{"id":1576,"name":"高新香江岸"},{"id":2945,"name":"高柱新村 "}]
     * count : 3
     * keyword :
     */

    private int code;
    private String info;
    private int count;
    private String keyword;
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
         * id : 310
         * name : 栖凤园
         */

        private String id;
        private String name;

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
    }
}
