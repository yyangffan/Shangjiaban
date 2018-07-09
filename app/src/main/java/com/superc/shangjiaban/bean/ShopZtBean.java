package com.superc.shangjiaban.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by user on 2018/3/24.
 */

public class ShopZtBean {
    /**
     * code : 200
     * info : 查询成功
     * date : [{"order_id":3,"order_sn":"2017090500325215","order_status":2,"shipping_status":1,"pay_status":1,"consignee":"232321","mobile":"18631056825","goods_price":"69.00","shipping_time":1509506144,"confirm_time":1510125013,"goods_num":1,"spec_key_name":"黄色S纯棉"}]
     * count : 10
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
         * order_id : 3
         * order_sn : 2017090500325215
         * order_status : 2
         * shipping_status : 1
         * pay_status : 1
         * consignee : 232321
         * mobile : 18631056825
         * goods_price : 69.00
         * shipping_time : 1509506144
         * confirm_time : 1510125013
         * goods_num : 1
         * spec_key_name : 黄色S纯棉
         * order_prom_type
         */

        @SerializedName("order_id")
        private String orderId;
        @SerializedName("order_sn")
        private String orderSn;
        @SerializedName("order_status")
        private String orderStatus;
        @SerializedName("shipping_status")
        private String shippingStatus;
        @SerializedName("pay_status")
        private String payStatus;
        @SerializedName("consignee")
        private String consignee;
        @SerializedName("mobile")
        private String mobile;
        @SerializedName("goods_price")
        private String goodsPrice;
        @SerializedName("shipping_time")
        private String shippingTime;
        @SerializedName("confirm_time")
        private String confirmTime;
        @SerializedName("goods_num")
        private String goodsNum;
        @SerializedName("spec_key_name")
        private String specKeyName;
        @SerializedName("state")
        private String state;
        @SerializedName("order_prom_type")
        private String order_prom_type;
        @SerializedName("shipping_code")
        private String shipping_code;

        public String getShipping_code() {
            return shipping_code;
        }

        public void setShipping_code(String shipping_code) {
            this.shipping_code = shipping_code;
        }

        public String getOrder_prom_type() {
            return order_prom_type;
        }

        public void setOrder_prom_type(String order_prom_type) {
            this.order_prom_type = order_prom_type;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public String getOrderSn() {
            return orderSn;
        }

        public void setOrderSn(String orderSn) {
            this.orderSn = orderSn;
        }

        public String getOrderStatus() {
            return orderStatus;
        }

        public void setOrderStatus(String orderStatus) {
            this.orderStatus = orderStatus;
        }

        public String getShippingStatus() {
            return shippingStatus;
        }

        public void setShippingStatus(String shippingStatus) {
            this.shippingStatus = shippingStatus;
        }

        public String getPayStatus() {
            return payStatus;
        }

        public void setPayStatus(String payStatus) {
            this.payStatus = payStatus;
        }

        public String getConsignee() {
            return consignee;
        }

        public void setConsignee(String consignee) {
            this.consignee = consignee;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getGoodsPrice() {
            return goodsPrice;
        }

        public void setGoodsPrice(String goodsPrice) {
            this.goodsPrice = goodsPrice;
        }

        public String getShippingTime() {
            return shippingTime;
        }

        public void setShippingTime(String shippingTime) {
            this.shippingTime = shippingTime;
        }

        public String getConfirmTime() {
            return confirmTime;
        }

        public void setConfirmTime(String confirmTime) {
            this.confirmTime = confirmTime;
        }

        public String getGoodsNum() {
            return goodsNum;
        }

        public void setGoodsNum(String goodsNum) {
            this.goodsNum = goodsNum;
        }

        public String getSpecKeyName() {
            return specKeyName;
        }

        public void setSpecKeyName(String specKeyName) {
            this.specKeyName = specKeyName;
        }
    }
}
