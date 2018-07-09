package com.superc.shangjiaban.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by user on 2018/3/24.
 */

public class ShopFhBean {

    /**
     * code : 200
     * info : 查询成功
     * date : [{"order_id":470,"order_sn":"2018011218274013","consignee":"测试来的","mobile":"18813139617","order_status":1,"shipping_status":0,"pay_status":1,"goods_price":"20.00","add_time":1515752860,"pay_time":1515752867,"state":"1","order_prom_type":0,"goods_num":1},{"order_id":469,"order_sn":"2018011218141913","consignee":"测试来的","mobile":"18813139617","order_status":1,"shipping_status":0,"pay_status":1,"goods_price":"20.00","add_time":1515752059,"pay_time":1515752065,"state":"1","order_prom_type":0,"goods_num":1},{"order_id":441,"order_sn":"2018011210193413","consignee":"测试来的","mobile":"18813139617","order_status":1,"shipping_status":0,"pay_status":1,"goods_price":"99.00","add_time":1515723574,"pay_time":1515723582,"state":"1","order_prom_type":6,"goods_num":1},{"order_id":438,"order_sn":"2018011117454832","consignee":"马彬","mobile":"13303044499","order_status":1,"shipping_status":0,"pay_status":1,"goods_price":"79.80","add_time":1515663948,"pay_time":1515663976,"state":"1","order_prom_type":6,"goods_num":2},{"order_id":434,"order_sn":"2018011117251032","consignee":"马彬","mobile":"13303044499","order_status":1,"shipping_status":0,"pay_status":1,"goods_price":"110.00","add_time":1515662710,"pay_time":1515662722,"state":"1","order_prom_type":6,"goods_num":2},{"order_id":433,"order_sn":"2018011117220526","consignee":"流浪火星","mobile":"17367758880","order_status":1,"shipping_status":0,"pay_status":1,"goods_price":"55.00","add_time":1515662525,"pay_time":1515662543,"state":"1","order_prom_type":6,"goods_num":1},{"order_id":380,"order_sn":"2018011015440369","consignee":"花花","mobile":"15200015653","order_status":1,"shipping_status":0,"pay_status":1,"goods_price":"58.00","add_time":1515570243,"pay_time":1515570294,"state":"1","order_prom_type":6,"goods_num":1},{"order_id":379,"order_sn":"2018011015104069","consignee":"花花","mobile":"15200015653","order_status":1,"shipping_status":0,"pay_status":1,"goods_price":"152.90","add_time":1515568241,"pay_time":1515568276,"state":"1","order_prom_type":6,"goods_num":2},{"order_id":379,"order_sn":"2018011015104069","consignee":"花花","mobile":"15200015653","order_status":1,"shipping_status":0,"pay_status":1,"goods_price":"152.90","add_time":1515568241,"pay_time":1515568276,"state":"1","order_prom_type":6,"goods_num":1},{"order_id":376,"order_sn":"2018011014333013","consignee":"测试来的","mobile":"18813139617","order_status":1,"shipping_status":0,"pay_status":1,"goods_price":"11.00","add_time":1515566010,"pay_time":1515566017,"state":"1","order_prom_type":6,"goods_num":1},{"order_id":375,"order_sn":"2018011014330513","consignee":"测试来的","mobile":"18813139617","order_status":1,"shipping_status":0,"pay_status":1,"goods_price":"99.00","add_time":1515565985,"pay_time":1515565993,"state":"1","order_prom_type":6,"goods_num":1},{"order_id":368,"order_sn":"2018011011164569","consignee":"花花","mobile":"15200015653","order_status":1,"shipping_status":0,"pay_status":1,"goods_price":"270.90","add_time":1515554205,"pay_time":1515554287,"state":"1","order_prom_type":6,"goods_num":1},{"order_id":368,"order_sn":"2018011011164569","consignee":"花花","mobile":"15200015653","order_status":1,"shipping_status":0,"pay_status":1,"goods_price":"270.90","add_time":1515554205,"pay_time":1515554287,"state":"1","order_prom_type":6,"goods_num":1},{"order_id":368,"order_sn":"2018011011164569","consignee":"花花","mobile":"15200015653","order_status":1,"shipping_status":0,"pay_status":1,"goods_price":"270.90","add_time":1515554205,"pay_time":1515554287,"state":"1","order_prom_type":6,"goods_num":1},{"order_id":368,"order_sn":"2018011011164569","consignee":"花花","mobile":"15200015653","order_status":1,"shipping_status":0,"pay_status":1,"goods_price":"270.90","add_time":1515554205,"pay_time":1515554287,"state":"1","order_prom_type":6,"goods_num":3}]
     * count : 14
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
         * order_id : 470
         * order_sn : 2018011218274013
         * consignee : 测试来的
         * mobile : 18813139617
         * order_status : 1
         * shipping_status : 0
         * pay_status : 1
         * goods_price : 20.00
         * add_time : 1515752860
         * pay_time : 1515752867
         * state : 1
         * order_prom_type : 0
         * goods_num : 1
         */

        @SerializedName("order_id")
        private String orderId;
        @SerializedName("order_sn")
        private String orderSn;
        @SerializedName("consignee")
        private String consignee;
        @SerializedName("mobile")
        private String mobile;
        @SerializedName("order_status")
        private String orderStatus;
        @SerializedName("shipping_status")
        private String shippingStatus;
        @SerializedName("pay_status")
        private String payStatus;
        @SerializedName("goods_price")
        private String goodsPrice;
        @SerializedName("add_time")
        private String addTime;
        @SerializedName("pay_time")
        private String payTime;
        @SerializedName("state")
        private String state;
        @SerializedName("order_prom_type")
        private String orderPromType;
        @SerializedName("goods_num")
        private String goodsNum;
        private boolean isC;

        public boolean isC() {
            return isC;
        }

        public void setC(boolean c) {
            isC = c;
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

        public String getGoodsPrice() {
            return goodsPrice;
        }

        public void setGoodsPrice(String goodsPrice) {
            this.goodsPrice = goodsPrice;
        }

        public String getAddTime() {
            return addTime;
        }

        public void setAddTime(String addTime) {
            this.addTime = addTime;
        }

        public String getPayTime() {
            return payTime;
        }

        public void setPayTime(String payTime) {
            this.payTime = payTime;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getOrderPromType() {
            return orderPromType;
        }

        public void setOrderPromType(String orderPromType) {
            this.orderPromType = orderPromType;
        }

        public String getGoodsNum() {
            return goodsNum;
        }

        public void setGoodsNum(String goodsNum) {
            this.goodsNum = goodsNum;
        }
    }
}
