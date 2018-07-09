package com.superc.shangjiaban.base;

/**
 * 用来存放地址
 */

public class Constant {

    //测试地址http://api  正式地址http://bxgj
    private static String BASEURL="http://bxgj";

    public static final String REQUESTURL = BASEURL+".xiaochaokeji.com/housekeeper/";
    public static final String LOGIN =  BASEURL+".xiaochaokeji.com/admin/";
    public static final String DIQUURL =  BASEURL+".xiaochaokeji.com/server/";
    public static String REQUESTURL_IMAGE =  BASEURL+".xiaochaokeji.com";

    public static final String SYSTEM = REQUESTURL + "index/index";/*首页数据*/
    public static final String ADMINLOGIN = LOGIN + "admin/login";/*登录*/
    public static final String LOGNOUT = LOGIN + "admin/logout";/*登出*/
    public static final String USERLIST = REQUESTURL + "user/index";/*用户列表*/
    public static final String GOLDALLOWANCE = REQUESTURL + "user/goldAllowance";/*黄金抵费记录*/
    public static final String COMMUNITYLIST = REQUESTURL + "user/communityList";/*小区列表*/
    public static final String DIQU = DIQUURL + "Publics/DiQu";/*获取省市区信息*/
    public static final String ADDXIAOQU = REQUESTURL + "user/communityAdd";/*添加小区*/
    public static final String DELETEXIAOQU = REQUESTURL + "user/communityDel";/*删除小区*/
    public static final String FEEDBACKLIST = REQUESTURL + "user/feedBackList";/*反馈信息*/
    public static final String PROPERTYLIST = REQUESTURL + "user/propertyList";/*物业用户列表*/
    public static final String WITHDRAWALS = REQUESTURL + "user/withdrawals";/*佣金提现申请*/
    public static final String REMITTANCE = REQUESTURL + "user/remittance";/*佣金提现记录接口*/
    public static final String ADDAPPLY = REQUESTURL + "user/addApply";/*添加提现申请*/
    public static final String XQDETAIL = REQUESTURL + "user/communityInfo";/*单个小区详情接口*/
    public static final String PROPERDETAIL = REQUESTURL + "user/propertyInfo";/*单个物业商户详情接口*/
    public static final String UPDATEPROPERTY = REQUESTURL + "user/updateUserInfo";/*编辑物业用户*/
    public static final String SHOPINDEX = REQUESTURL + "order/index";/*订单列表*/
    public static final String DELIVERYLIST = REQUESTURL + "order/deliveryList";/*发货单列表--批量发货*/
    public static final String PENDINGLIST = REQUESTURL + "order/pendingList";/*待自提列表*/
    public static final String ACCOUNTSLIST = REQUESTURL + "user/accountsList";/*待转账列表*/
    public static final String ORDERDETAIL = REQUESTURL + "order/orderDetail";/*订单详情页面*/
    public static final String APPROVAL = REQUESTURL + "user/approval";/*批量、单个黄金抵费通过*/
    public static final String APPROVALCOMMISSION = REQUESTURL + "user/approvalCommission";/*批量或单个审批 佣金提现*/
    public static final String MAKEMONEY = REQUESTURL + "user/makeMoney";/*批量或单个审批 佣金提现确认打款*/
    public static final String PLAYMONEY = REQUESTURL + "user/playMoney";/*单个转账汇款操作*/
    public static final String DOSENDMSG = REQUESTURL + "user/doSendMessage";/*发送站内信*/
    public static final String DELIVERGOODSPAGE = REQUESTURL + "order/deliverGoodsPage";/*单独发货*/
    public static final String HASNEWDINGDAN = REQUESTURL + "order/isNewPayOrder";/*是否有新的付款订单*/
    public static final String ALLHUODONG = REQUESTURL + "order/activityList";/*查询活动*/
    public static final String SELECTVILLAGE = REQUESTURL + "order/selectVillage";/*待自提中的小区*/
    public static final String PICKEDUP = REQUESTURL + "order/pickedUp";/*待自提操作*/




}
