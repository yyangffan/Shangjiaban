package com.superc.shangjiaban.bean;

/**
 * Created by user on 2018/3/13.
 */

public class HomeIndexBean {

    /**
     * code : 200
     * info : 查询成功
     * date : {"xiaoqu":442,"order":176,"users":48,"commission":1,"status":0,"sale":{"today_sale":0,"thismonth":0}}
     * count : 0
     * keyword :
     */

    private String code;
    private String info;
    private DateBean date;
    private String count;
    private String keyword;

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

    public DateBean getDate() {
        return date;
    }

    public void setDate(DateBean date) {
        this.date = date;
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

    public static class DateBean {
        /**
         * xiaoqu : 442
         * order : 176
         * users : 48
         * commission : 1
         * status : 0
         * sale : {"today_sale":0,"thismonth":0}
         */

        private String xiaoqu;
        private String order;
        private String users;
        private String commission;
        private String status;
        private SaleBean sale;

        public String getXiaoqu() {
            return xiaoqu;
        }

        public void setXiaoqu(String xiaoqu) {
            this.xiaoqu = xiaoqu;
        }

        public String getOrder() {
            return order;
        }

        public void setOrder(String order) {
            this.order = order;
        }

        public String getUsers() {
            return users;
        }

        public void setUsers(String users) {
            this.users = users;
        }

        public String getCommission() {
            return commission;
        }

        public void setCommission(String commission) {
            this.commission = commission;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public SaleBean getSale() {
            return sale;
        }

        public void setSale(SaleBean sale) {
            this.sale = sale;
        }

        public static class SaleBean {
            /**
             * today_sale : 0
             * thismonth : 0
             */

            private String today_sale;
            private String thismonth;

            public String getToday_sale() {
                return today_sale;
            }

            public void setToday_sale(String today_sale) {
                this.today_sale = today_sale;
            }

            public String getThismonth() {
                return thismonth;
            }

            public void setThismonth(String thismonth) {
                this.thismonth = thismonth;
            }
        }
    }
}
