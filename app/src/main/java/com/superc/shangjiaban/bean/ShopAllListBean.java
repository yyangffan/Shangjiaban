package com.superc.shangjiaban.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * 所有订单的实体类
 */

public class ShopAllListBean {

    /**
     * code : 200
     * info : 查询成功
     * date : [{"order_id":587,"order_sn":"2018012917292573","user_id":73,"order_status":1,"shipping_status":0,"pay_status":0,"consignee":"2409635895","country":0,"province":1,"city":2,"district":9,"twon":310,"address":"1栋2单元3层996号","zipcode":"","mobile":"18731124090","email":"","shipping_code":"1000","shipping_name":"","pay_code":"","pay_name":"","invoice_title":"","goods_price":"55.00","shipping_price":"0.00","user_money":"0.00","coupon_price":"0.00","Stringegral":0,"Stringegral_money":"0.00","order_amount":"0.00","total_amount":"55.00","add_time":1517218165,"shipping_time":0,"confirm_time":0,"pay_time":0,"order_prom_type":6,"order_prom_id":null,"order_prom_amount":"0.00","discount":"0.00","user_note":"","admin_note":"","parent_sn":null,"is_distribut":0,"paid_money":"0.00","state":"1","supermarket_id":11},{"order_id":586,"order_sn":"2018012917221573","user_id":73,"order_status":1,"shipping_status":0,"pay_status":0,"consignee":"2409635895","country":0,"province":1,"city":2,"district":9,"twon":310,"address":"1栋2单元3层996号","zipcode":"","mobile":"18731124090","email":"","shipping_code":"1000","shipping_name":"","pay_code":"","pay_name":"","invoice_title":"","goods_price":"55.00","shipping_price":"0.00","user_money":"0.00","coupon_price":"0.00","Stringegral":0,"Stringegral_money":"0.00","order_amount":"0.00","total_amount":"55.00","add_time":1517217735,"shipping_time":0,"confirm_time":0,"pay_time":0,"order_prom_type":6,"order_prom_id":null,"order_prom_amount":"0.00","discount":"0.00","user_note":"","admin_note":"","parent_sn":null,"is_distribut":0,"paid_money":"0.00","state":"1","supermarket_id":11},{"order_id":585,"order_sn":"2018012917203373","user_id":73,"order_status":1,"shipping_status":0,"pay_status":0,"consignee":"2409635895","country":0,"province":1,"city":2,"district":9,"twon":310,"address":"1栋2单元3层996号","zipcode":"","mobile":"18731124090","email":"","shipping_code":"1000","shipping_name":"","pay_code":"","pay_name":"","invoice_title":"","goods_price":"55.00","shipping_price":"0.00","user_money":"0.00","coupon_price":"0.00","Stringegral":0,"Stringegral_money":"0.00","order_amount":"0.00","total_amount":"55.00","add_time":1517217633,"shipping_time":0,"confirm_time":0,"pay_time":0,"order_prom_type":6,"order_prom_id":null,"order_prom_amount":"0.00","discount":"0.00","user_note":"","admin_note":"","parent_sn":null,"is_distribut":0,"paid_money":"0.00","state":"1","supermarket_id":11},{"order_id":584,"order_sn":"2018012917115273","user_id":73,"order_status":1,"shipping_status":0,"pay_status":0,"consignee":"2409635895","country":0,"province":1,"city":2,"district":9,"twon":310,"address":"1栋2单元3层996号","zipcode":"","mobile":"18731124090","email":"","shipping_code":"1000","shipping_name":"","pay_code":"","pay_name":"","invoice_title":"","goods_price":"55.00","shipping_price":"0.00","user_money":"0.00","coupon_price":"0.00","Stringegral":0,"Stringegral_money":"0.00","order_amount":"0.00","total_amount":"55.00","add_time":1517217112,"shipping_time":0,"confirm_time":0,"pay_time":0,"order_prom_type":6,"order_prom_id":null,"order_prom_amount":"0.00","discount":"0.00","user_note":"","admin_note":"","parent_sn":null,"is_distribut":0,"paid_money":"0.00","state":"1","supermarket_id":11}]
     */

    @SerializedName("code")
    private String code;
    @SerializedName("info")
    private String info;
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

    public List<DateBean> getDate() {
        return date;
    }

    public void setDate(List<DateBean> date) {
        this.date = date;
    }

    public static class DateBean implements Serializable{
        /**
         * order_id : 587
         * order_sn : 2018012917292573
         * user_id : 73
         * order_status : 1
         * shipping_status : 0
         * pay_status : 0
         * consignee : 2409635895
         * country : 0
         * province : 1
         * city : 2
         * district : 9
         * twon : 310
         * address : 1栋2单元3层996号
         * zipcode :
         * mobile : 18731124090
         * email :
         * shipping_code : 1000
         * shipping_name :
         * pay_code :
         * pay_name :
         * invoice_title :
         * goods_price : 55.00
         * shipping_price : 0.00
         * user_money : 0.00
         * coupon_price : 0.00
         * Stringegral : 0
         * Stringegral_money : 0.00
         * order_amount : 0.00
         * total_amount : 55.00
         * add_time : 1517218165
         * shipping_time : 0
         * confirm_time : 0
         * pay_time : 0
         * order_prom_type : 6
         * order_prom_id : null
         * order_prom_amount : 0.00
         * discount : 0.00
         * user_note :
         * admin_note :
         * parent_sn : null
         * is_distribut : 0
         * paid_money : 0.00
         * state : 1
         * supermarket_id : 11
         */

        @SerializedName("order_id")
        private String orderId;
        @SerializedName("order_sn")
        private String orderSn;
        @SerializedName("user_id")
        private String userId;
        @SerializedName("order_status")
        private String orderStatus;
        @SerializedName("shipping_status")
        private String shippingStatus;
        @SerializedName("pay_status")
        private String payStatus;
        @SerializedName("consignee")
        private String consignee;
        @SerializedName("country")
        private String country;
        @SerializedName("province")
        private String province;
        @SerializedName("city")
        private String city;
        @SerializedName("district")
        private String district;
        @SerializedName("twon")
        private String twon;
        @SerializedName("address")
        private String address;
        @SerializedName("zipcode")
        private String zipcode;
        @SerializedName("mobile")
        private String mobile;
        @SerializedName("email")
        private String email;
        @SerializedName("shipping_code")
        private String shippingCode;
        @SerializedName("shipping_name")
        private String shippingName;
        @SerializedName("pay_code")
        private String payCode;
        @SerializedName("pay_name")
        private String payName;
        @SerializedName("invoice_title")
        private String invoiceTitle;
        @SerializedName("goods_price")
        private String goodsPrice;
        @SerializedName("shipping_price")
        private String shippingPrice;
        @SerializedName("user_money")
        private String userMoney;
        @SerializedName("coupon_price")
        private String couponPrice;
        @SerializedName("Stringegral")
        private String Stringegral;
        @SerializedName("Stringegral_money")
        private String StringegralMoney;
        @SerializedName("order_amount")
        private String orderAmount;
        @SerializedName("total_amount")
        private String totalAmount;
        @SerializedName("add_time")
        private String addTime;
        @SerializedName("shipping_time")
        private String shippingTime;
        @SerializedName("confirm_time")
        private String confirmTime;
        @SerializedName("pay_time")
        private String payTime;
        @SerializedName("order_prom_type")
        private String orderPromType;
        @SerializedName("order_prom_id")
        private Object orderPromId;
        @SerializedName("order_prom_amount")
        private String orderPromAmount;
        @SerializedName("discount")
        private String discount;
        @SerializedName("user_note")
        private String userNote;
        @SerializedName("admin_note")
        private String adminNote;
        @SerializedName("parent_sn")
        private Object parentSn;
        @SerializedName("is_distribut")
        private String isDistribut;
        @SerializedName("paid_money")
        private String paidMoney;
        @SerializedName("state")
        private String state;
        @SerializedName("supermarket_id")
        private String supermarketId;

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

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
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

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getDistrict() {
            return district;
        }

        public void setDistrict(String district) {
            this.district = district;
        }

        public String getTwon() {
            return twon;
        }

        public void setTwon(String twon) {
            this.twon = twon;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getZipcode() {
            return zipcode;
        }

        public void setZipcode(String zipcode) {
            this.zipcode = zipcode;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getShippingCode() {
            return shippingCode;
        }

        public void setShippingCode(String shippingCode) {
            this.shippingCode = shippingCode;
        }

        public String getShippingName() {
            return shippingName;
        }

        public void setShippingName(String shippingName) {
            this.shippingName = shippingName;
        }

        public String getPayCode() {
            return payCode;
        }

        public void setPayCode(String payCode) {
            this.payCode = payCode;
        }

        public String getPayName() {
            return payName;
        }

        public void setPayName(String payName) {
            this.payName = payName;
        }

        public String getInvoiceTitle() {
            return invoiceTitle;
        }

        public void setInvoiceTitle(String invoiceTitle) {
            this.invoiceTitle = invoiceTitle;
        }

        public String getGoodsPrice() {
            return goodsPrice;
        }

        public void setGoodsPrice(String goodsPrice) {
            this.goodsPrice = goodsPrice;
        }

        public String getShippingPrice() {
            return shippingPrice;
        }

        public void setShippingPrice(String shippingPrice) {
            this.shippingPrice = shippingPrice;
        }

        public String getUserMoney() {
            return userMoney;
        }

        public void setUserMoney(String userMoney) {
            this.userMoney = userMoney;
        }

        public String getCouponPrice() {
            return couponPrice;
        }

        public void setCouponPrice(String couponPrice) {
            this.couponPrice = couponPrice;
        }

        public String getStringegral() {
            return Stringegral;
        }

        public void setStringegral(String Stringegral) {
            this.Stringegral = Stringegral;
        }

        public String getStringegralMoney() {
            return StringegralMoney;
        }

        public void setStringegralMoney(String StringegralMoney) {
            this.StringegralMoney = StringegralMoney;
        }

        public String getOrderAmount() {
            return orderAmount;
        }

        public void setOrderAmount(String orderAmount) {
            this.orderAmount = orderAmount;
        }

        public String getTotalAmount() {
            return totalAmount;
        }

        public void setTotalAmount(String totalAmount) {
            this.totalAmount = totalAmount;
        }

        public String getAddTime() {
            return addTime;
        }

        public void setAddTime(String addTime) {
            this.addTime = addTime;
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

        public String getPayTime() {
            return payTime;
        }

        public void setPayTime(String payTime) {
            this.payTime = payTime;
        }

        public String getOrderPromType() {
            return orderPromType;
        }

        public void setOrderPromType(String orderPromType) {
            this.orderPromType = orderPromType;
        }

        public Object getOrderPromId() {
            return orderPromId;
        }

        public void setOrderPromId(Object orderPromId) {
            this.orderPromId = orderPromId;
        }

        public String getOrderPromAmount() {
            return orderPromAmount;
        }

        public void setOrderPromAmount(String orderPromAmount) {
            this.orderPromAmount = orderPromAmount;
        }

        public String getDiscount() {
            return discount;
        }

        public void setDiscount(String discount) {
            this.discount = discount;
        }

        public String getUserNote() {
            return userNote;
        }

        public void setUserNote(String userNote) {
            this.userNote = userNote;
        }

        public String getAdminNote() {
            return adminNote;
        }

        public void setAdminNote(String adminNote) {
            this.adminNote = adminNote;
        }

        public Object getParentSn() {
            return parentSn;
        }

        public void setParentSn(Object parentSn) {
            this.parentSn = parentSn;
        }

        public String getIsDistribut() {
            return isDistribut;
        }

        public void setIsDistribut(String isDistribut) {
            this.isDistribut = isDistribut;
        }

        public String getPaidMoney() {
            return paidMoney;
        }

        public void setPaidMoney(String paidMoney) {
            this.paidMoney = paidMoney;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getSupermarketId() {
            return supermarketId;
        }

        public void setSupermarketId(String supermarketId) {
            this.supermarketId = supermarketId;
        }
    }
}
