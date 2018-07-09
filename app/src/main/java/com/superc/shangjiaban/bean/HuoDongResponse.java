package com.superc.shangjiaban.bean;

import java.util.List;

/**
 * Created by user on 2018/4/27.
 */

public class HuoDongResponse {
    /**
     * code : 200
     * info : 查询成功
     * date : [{"id":9,"title":"测试34"}]
     * count : 1
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
         * id : 9
         * title : 测试34
         */

        private String id;
        private String title;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
