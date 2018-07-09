package com.superc.shangjiaban.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 *订单详情实体类
 */

public class OrderDetailBean {

    /**
     * code : 200
     * info : 操作成功
     * date : {"order_id":587,"order_sn":"2018012917292573","user_id":73,"order_status":1,"shipping_status":0,"pay_status":0,"consignee":"2409635895","country":0,"province":1,"city":2,"district":9,"twon":310,"address":"1栋2单元3层996号","zipcode":"","mobile":"18731124090","email":"","shipping_code":"1000","shipping_name":"","pay_code":"","pay_name":"","invoice_title":"","goods_price":"55.00","shipping_price":"0.00","user_money":"0.00","coupon_price":"0.00","Stringegral":0,"Stringegral_money":"0.00","order_amount":"0.00","total_amount":"55.00","add_time":1517218165,"shipping_time":0,"confirm_time":0,"pay_time":0,"order_prom_type":6,"order_prom_id":null,"order_prom_amount":"0.00","discount":"0.00","user_note":"","admin_note":"","parent_sn":null,"is_distribut":0,"paid_money":"0.00","state":"1","supermarket_id":11,"address2":"河北省,石家庄市,高邑,栖凤园1栋2单元3层996号"}
     * count : [{"goods_id":17,"cat_id":9,"user_id":null,"extend_cat_id":0,"goods_sn":"TP0000017","goods_name":"赤峰原生态小米","click_count":0,"brand_id":0,"store_count":9900,"comment_count":0,"weight":0,"market_price":"85.00","shop_price":"55.00","cost_price":"0.00","price_ladder":null,"keywords":"","goods_remark":"赤峰原生态小米","goods_content":"&lt;p&gt;&lt;img title=&quot;小米.jpg&quot; src=&quot;/public/upload/goods/2017/10-13/73feac908297d697dadb92d01421e5b0.jpg&quot;/&gt;&lt;/p&gt;","original_img":"/public/upload/goods/2017/10-14/04cbd0bf69a44552985c4757bfca89b2.png","is_real":1,"is_on_sale":1,"is_free_shipping":0,"on_time":1515132904,"in_time":null,"out_time":null,"sort":50,"is_recommend":0,"is_new":0,"is_hot":0,"last_update":0,"goods_type":5,"spec_type":5,"give_Stringegral":0,"exchange_Stringegral":0,"suppliers_id":0,"sales_sum":0,"prom_type":6,"prom_id":0,"commission":"5.00","spu":"","sku":"","shipping_area_ids":"","chandi":"内蒙古","changjia":"河北怀谷农业科技有限公司","type":"goods","fuzeren":"余经理","rec_id":727,"order_id":587,"goods_num":1,"goods_price":"55.00","member_goods_price":"0.00","spec_key":"","spec_key_name":"","bar_code":"","is_comment":0,"is_send":0,"delivery_id":0,"goods_total":"0.00"}]
     * keyword : 
     */

    @SerializedName("code")
    private String code;
    @SerializedName("info")
    private String info;
    @SerializedName("date")
    private DateBean date;
    @SerializedName("keyword")
    private String keyword;
    @SerializedName("count")
    private List<CountBean> count;

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

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public List<CountBean> getCount() {
        return count;
    }

    public void setCount(List<CountBean> count) {
        this.count = count;
    }

    public static class DateBean {
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
         * address2 : 河北省,石家庄市,高邑,栖凤园1栋2单元3层996号
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
        @SerializedName("address2")
        private String address2;

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

        public String getAddress2() {
            return address2;
        }

        public void setAddress2(String address2) {
            this.address2 = address2;
        }
    }

    public static class CountBean {
        /**
         * goods_id : 17
         * cat_id : 9
         * user_id : null
         * extend_cat_id : 0
         * goods_sn : TP0000017
         * goods_name : 赤峰原生态小米
         * click_count : 0
         * brand_id : 0
         * store_count : 9900
         * comment_count : 0
         * weight : 0
         * market_price : 85.00
         * shop_price : 55.00
         * cost_price : 0.00
         * price_ladder : null
         * keywords : 
         * goods_remark : 赤峰原生态小米
         * goods_content : &lt;p&gt;&lt;img title=&quot;小米.jpg&quot; src=&quot;/public/upload/goods/2017/10-13/73feac908297d697dadb92d01421e5b0.jpg&quot;/&gt;&lt;/p&gt;
         * original_img : /public/upload/goods/2017/10-14/04cbd0bf69a44552985c4757bfca89b2.png
         * is_real : 1
         * is_on_sale : 1
         * is_free_shipping : 0
         * on_time : 1515132904
         * in_time : null
         * out_time : null
         * sort : 50
         * is_recommend : 0
         * is_new : 0
         * is_hot : 0
         * last_update : 0
         * goods_type : 5
         * spec_type : 5
         * give_Stringegral : 0
         * exchange_Stringegral : 0
         * suppliers_id : 0
         * sales_sum : 0
         * prom_type : 6
         * prom_id : 0
         * commission : 5.00
         * spu : 
         * sku : 
         * shipping_area_ids : 
         * chandi : 内蒙古
         * changjia : 河北怀谷农业科技有限公司
         * type : goods
         * fuzeren : 余经理
         * rec_id : 727
         * order_id : 587
         * goods_num : 1
         * goods_price : 55.00
         * member_goods_price : 0.00
         * spec_key : 
         * spec_key_name : 
         * bar_code : 
         * is_comment : 0
         * is_send : 0
         * delivery_id : 0
         * goods_total : 0.00
         */

        @SerializedName("goods_id")
        private String goodsId;
        @SerializedName("cat_id")
        private String catId;
        @SerializedName("user_id")
        private Object userId;
        @SerializedName("extend_cat_id")
        private String extendCatId;
        @SerializedName("goods_sn")
        private String goodsSn;
        @SerializedName("goods_name")
        private String goodsName;
        @SerializedName("click_count")
        private String clickCount;
        @SerializedName("brand_id")
        private String brandId;
        @SerializedName("store_count")
        private String storeCount;
        @SerializedName("comment_count")
        private String commentCount;
        @SerializedName("weight")
        private String weight;
        @SerializedName("market_price")
        private String marketPrice;
        @SerializedName("shop_price")
        private String shopPrice;
        @SerializedName("cost_price")
        private String costPrice;
        @SerializedName("price_ladder")
        private Object priceLadder;
        @SerializedName("keywords")
        private String keywords;
        @SerializedName("goods_remark")
        private String goodsRemark;
        @SerializedName("goods_content")
        private String goodsContent;
        @SerializedName("original_img")
        private String originalImg;
        @SerializedName("is_real")
        private String isReal;
        @SerializedName("is_on_sale")
        private String isOnSale;
        @SerializedName("is_free_shipping")
        private String isFreeShipping;
        @SerializedName("on_time")
        private String onTime;
        @SerializedName("in_time")
        private Object Stringime;
        @SerializedName("out_time")
        private Object outTime;
        @SerializedName("sort")
        private String sort;
        @SerializedName("is_recommend")
        private String isRecommend;
        @SerializedName("is_new")
        private String isNew;
        @SerializedName("is_hot")
        private String isHot;
        @SerializedName("last_update")
        private String lastUpdate;
        @SerializedName("goods_type")
        private String goodsType;
        @SerializedName("spec_type")
        private String specType;
        @SerializedName("give_Stringegral")
        private String giveStringegral;
        @SerializedName("exchange_Stringegral")
        private String exchangeStringegral;
        @SerializedName("suppliers_id")
        private String suppliersId;
        @SerializedName("sales_sum")
        private String salesSum;
        @SerializedName("prom_type")
        private String promType;
        @SerializedName("prom_id")
        private String promId;
        @SerializedName("commission")
        private String commission;
        @SerializedName("spu")
        private String spu;
        @SerializedName("sku")
        private String sku;
        @SerializedName("shipping_area_ids")
        private String shippingAreaIds;
        @SerializedName("chandi")
        private String chandi;
        @SerializedName("changjia")
        private String changjia;
        @SerializedName("type")
        private String type;
        @SerializedName("fuzeren")
        private String fuzeren;
        @SerializedName("rec_id")
        private String recId;
        @SerializedName("order_id")
        private String orderId;
        @SerializedName("goods_num")
        private String goodsNum;
        @SerializedName("goods_price")
        private String goodsPrice;
        @SerializedName("member_goods_price")
        private String memberGoodsPrice;
        @SerializedName("spec_key")
        private String specKey;
        @SerializedName("spec_key_name")
        private String specKeyName;
        @SerializedName("bar_code")
        private String barCode;
        @SerializedName("is_comment")
        private String isComment;
        @SerializedName("is_send")
        private String isSend;
        @SerializedName("delivery_id")
        private String deliveryId;
        @SerializedName("goods_total")
        private String goodsTotal;

        public String getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(String goodsId) {
            this.goodsId = goodsId;
        }

        public String getCatId() {
            return catId;
        }

        public void setCatId(String catId) {
            this.catId = catId;
        }

        public Object getUserId() {
            return userId;
        }

        public void setUserId(Object userId) {
            this.userId = userId;
        }

        public String getExtendCatId() {
            return extendCatId;
        }

        public void setExtendCatId(String extendCatId) {
            this.extendCatId = extendCatId;
        }

        public String getGoodsSn() {
            return goodsSn;
        }

        public void setGoodsSn(String goodsSn) {
            this.goodsSn = goodsSn;
        }

        public String getGoodsName() {
            return goodsName;
        }

        public void setGoodsName(String goodsName) {
            this.goodsName = goodsName;
        }

        public String getClickCount() {
            return clickCount;
        }

        public void setClickCount(String clickCount) {
            this.clickCount = clickCount;
        }

        public String getBrandId() {
            return brandId;
        }

        public void setBrandId(String brandId) {
            this.brandId = brandId;
        }

        public String getStoreCount() {
            return storeCount;
        }

        public void setStoreCount(String storeCount) {
            this.storeCount = storeCount;
        }

        public String getCommentCount() {
            return commentCount;
        }

        public void setCommentCount(String commentCount) {
            this.commentCount = commentCount;
        }

        public String getWeight() {
            return weight;
        }

        public void setWeight(String weight) {
            this.weight = weight;
        }

        public String getMarketPrice() {
            return marketPrice;
        }

        public void setMarketPrice(String marketPrice) {
            this.marketPrice = marketPrice;
        }

        public String getShopPrice() {
            return shopPrice;
        }

        public void setShopPrice(String shopPrice) {
            this.shopPrice = shopPrice;
        }

        public String getCostPrice() {
            return costPrice;
        }

        public void setCostPrice(String costPrice) {
            this.costPrice = costPrice;
        }

        public Object getPriceLadder() {
            return priceLadder;
        }

        public void setPriceLadder(Object priceLadder) {
            this.priceLadder = priceLadder;
        }

        public String getKeywords() {
            return keywords;
        }

        public void setKeywords(String keywords) {
            this.keywords = keywords;
        }

        public String getGoodsRemark() {
            return goodsRemark;
        }

        public void setGoodsRemark(String goodsRemark) {
            this.goodsRemark = goodsRemark;
        }

        public String getGoodsContent() {
            return goodsContent;
        }

        public void setGoodsContent(String goodsContent) {
            this.goodsContent = goodsContent;
        }

        public String getOriginalImg() {
            return originalImg;
        }

        public void setOriginalImg(String originalImg) {
            this.originalImg = originalImg;
        }

        public String getIsReal() {
            return isReal;
        }

        public void setIsReal(String isReal) {
            this.isReal = isReal;
        }

        public String getIsOnSale() {
            return isOnSale;
        }

        public void setIsOnSale(String isOnSale) {
            this.isOnSale = isOnSale;
        }

        public String getIsFreeShipping() {
            return isFreeShipping;
        }

        public void setIsFreeShipping(String isFreeShipping) {
            this.isFreeShipping = isFreeShipping;
        }

        public String getOnTime() {
            return onTime;
        }

        public void setOnTime(String onTime) {
            this.onTime = onTime;
        }

        public Object getStringime() {
            return Stringime;
        }

        public void setStringime(Object Stringime) {
            this.Stringime = Stringime;
        }

        public Object getOutTime() {
            return outTime;
        }

        public void setOutTime(Object outTime) {
            this.outTime = outTime;
        }

        public String getSort() {
            return sort;
        }

        public void setSort(String sort) {
            this.sort = sort;
        }

        public String getIsRecommend() {
            return isRecommend;
        }

        public void setIsRecommend(String isRecommend) {
            this.isRecommend = isRecommend;
        }

        public String getIsNew() {
            return isNew;
        }

        public void setIsNew(String isNew) {
            this.isNew = isNew;
        }

        public String getIsHot() {
            return isHot;
        }

        public void setIsHot(String isHot) {
            this.isHot = isHot;
        }

        public String getLastUpdate() {
            return lastUpdate;
        }

        public void setLastUpdate(String lastUpdate) {
            this.lastUpdate = lastUpdate;
        }

        public String getGoodsType() {
            return goodsType;
        }

        public void setGoodsType(String goodsType) {
            this.goodsType = goodsType;
        }

        public String getSpecType() {
            return specType;
        }

        public void setSpecType(String specType) {
            this.specType = specType;
        }

        public String getGiveStringegral() {
            return giveStringegral;
        }

        public void setGiveStringegral(String giveStringegral) {
            this.giveStringegral = giveStringegral;
        }

        public String getExchangeStringegral() {
            return exchangeStringegral;
        }

        public void setExchangeStringegral(String exchangeStringegral) {
            this.exchangeStringegral = exchangeStringegral;
        }

        public String getSuppliersId() {
            return suppliersId;
        }

        public void setSuppliersId(String suppliersId) {
            this.suppliersId = suppliersId;
        }

        public String getSalesSum() {
            return salesSum;
        }

        public void setSalesSum(String salesSum) {
            this.salesSum = salesSum;
        }

        public String getPromType() {
            return promType;
        }

        public void setPromType(String promType) {
            this.promType = promType;
        }

        public String getPromId() {
            return promId;
        }

        public void setPromId(String promId) {
            this.promId = promId;
        }

        public String getCommission() {
            return commission;
        }

        public void setCommission(String commission) {
            this.commission = commission;
        }

        public String getSpu() {
            return spu;
        }

        public void setSpu(String spu) {
            this.spu = spu;
        }

        public String getSku() {
            return sku;
        }

        public void setSku(String sku) {
            this.sku = sku;
        }

        public String getShippingAreaIds() {
            return shippingAreaIds;
        }

        public void setShippingAreaIds(String shippingAreaIds) {
            this.shippingAreaIds = shippingAreaIds;
        }

        public String getChandi() {
            return chandi;
        }

        public void setChandi(String chandi) {
            this.chandi = chandi;
        }

        public String getChangjia() {
            return changjia;
        }

        public void setChangjia(String changjia) {
            this.changjia = changjia;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getFuzeren() {
            return fuzeren;
        }

        public void setFuzeren(String fuzeren) {
            this.fuzeren = fuzeren;
        }

        public String getRecId() {
            return recId;
        }

        public void setRecId(String recId) {
            this.recId = recId;
        }

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public String getGoodsNum() {
            return goodsNum;
        }

        public void setGoodsNum(String goodsNum) {
            this.goodsNum = goodsNum;
        }

        public String getGoodsPrice() {
            return goodsPrice;
        }

        public void setGoodsPrice(String goodsPrice) {
            this.goodsPrice = goodsPrice;
        }

        public String getMemberGoodsPrice() {
            return memberGoodsPrice;
        }

        public void setMemberGoodsPrice(String memberGoodsPrice) {
            this.memberGoodsPrice = memberGoodsPrice;
        }

        public String getSpecKey() {
            return specKey;
        }

        public void setSpecKey(String specKey) {
            this.specKey = specKey;
        }

        public String getSpecKeyName() {
            return specKeyName;
        }

        public void setSpecKeyName(String specKeyName) {
            this.specKeyName = specKeyName;
        }

        public String getBarCode() {
            return barCode;
        }

        public void setBarCode(String barCode) {
            this.barCode = barCode;
        }

        public String getIsComment() {
            return isComment;
        }

        public void setIsComment(String isComment) {
            this.isComment = isComment;
        }

        public String getIsSend() {
            return isSend;
        }

        public void setIsSend(String isSend) {
            this.isSend = isSend;
        }

        public String getDeliveryId() {
            return deliveryId;
        }

        public void setDeliveryId(String deliveryId) {
            this.deliveryId = deliveryId;
        }

        public String getGoodsTotal() {
            return goodsTotal;
        }

        public void setGoodsTotal(String goodsTotal) {
            this.goodsTotal = goodsTotal;
        }
    }
}
