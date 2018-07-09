package com.superc.shangjiaban.others;

import java.io.Serializable;

/**
 * 一个共用的类用来传递数据时使用，需要时可以添加一些必要的构造器，互相之间不会影响
 */

public class PublicBean implements Serializable {
    private String sheng;
    private String sheng_id;
    private String shi;
    private String shi_id;
    private String xian;
    private String xian_id;
    private String xiaoqu;
    private String xiaoqu_id;
    private String st_time;
    private String ed_time;
    private String dd_lx;
    private String dd_lx_id;
    private String zf_zt;
    private String zf_zt_id;
    private String fh_zt;
    private String fh_zt_id;
    private String dd_zt;
    private String dd_zt_id;
    private String other_one;/*其它情况时添加*/
    private String other_two;/*其他情况时添加*/

    /*这个构造器是给所有订单的订单搜索时进行使用的*/
    public PublicBean(String st_time, String ed_time, String dd_lx, String dd_lx_id, String zf_zt,
                      String zf_zt_id, String fh_zt, String fh_zt_id, String dd_zt, String dd_zt_id, String other_one) {
        this.st_time = st_time;
        this.ed_time = ed_time;
        this.dd_lx = dd_lx;
        this.dd_lx_id = dd_lx_id;
        this.zf_zt = zf_zt;
        this.zf_zt_id = zf_zt_id;
        this.fh_zt = fh_zt;
        this.fh_zt_id = fh_zt_id;
        this.dd_zt = dd_zt;
        this.dd_zt_id = dd_zt_id;
        this.other_one = other_one;
    }
    /*有需要时继续往下面添加构造器*/


    public String getSheng() {
        return sheng;
    }

    public void setSheng(String sheng) {
        this.sheng = sheng;
    }

    public String getSheng_id() {
        return sheng_id;
    }

    public void setSheng_id(String sheng_id) {
        this.sheng_id = sheng_id;
    }

    public String getShi() {
        return shi;
    }

    public void setShi(String shi) {
        this.shi = shi;
    }

    public String getShi_id() {
        return shi_id;
    }

    public void setShi_id(String shi_id) {
        this.shi_id = shi_id;
    }

    public String getXian() {
        return xian;
    }

    public void setXian(String xian) {
        this.xian = xian;
    }

    public String getXian_id() {
        return xian_id;
    }

    public void setXian_id(String xian_id) {
        this.xian_id = xian_id;
    }

    public String getXiaoqu() {
        return xiaoqu;
    }

    public void setXiaoqu(String xiaoqu) {
        this.xiaoqu = xiaoqu;
    }

    public String getXiaoqu_id() {
        return xiaoqu_id;
    }

    public void setXiaoqu_id(String xiaoqu_id) {
        this.xiaoqu_id = xiaoqu_id;
    }

    public String getSt_time() {
        return st_time;
    }

    public void setSt_time(String st_time) {
        this.st_time = st_time;
    }

    public String getEd_time() {
        return ed_time;
    }

    public void setEd_time(String ed_time) {
        this.ed_time = ed_time;
    }

    public String getDd_lx() {
        return dd_lx;
    }

    public void setDd_lx(String dd_lx) {
        this.dd_lx = dd_lx;
    }

    public String getDd_lx_id() {
        return dd_lx_id;
    }

    public void setDd_lx_id(String dd_lx_id) {
        this.dd_lx_id = dd_lx_id;
    }

    public String getZf_zt() {
        return zf_zt;
    }

    public void setZf_zt(String zf_zt) {
        this.zf_zt = zf_zt;
    }

    public String getZf_zt_id() {
        return zf_zt_id;
    }

    public void setZf_zt_id(String zf_zt_id) {
        this.zf_zt_id = zf_zt_id;
    }

    public String getFh_zt() {
        return fh_zt;
    }

    public void setFh_zt(String fh_zt) {
        this.fh_zt = fh_zt;
    }

    public String getFh_zt_id() {
        return fh_zt_id;
    }

    public void setFh_zt_id(String fh_zt_id) {
        this.fh_zt_id = fh_zt_id;
    }

    public String getDd_zt() {
        return dd_zt;
    }

    public void setDd_zt(String dd_zt) {
        this.dd_zt = dd_zt;
    }

    public String getDd_zt_id() {
        return dd_zt_id;
    }

    public void setDd_zt_id(String dd_zt_id) {
        this.dd_zt_id = dd_zt_id;
    }

    public String getOther_one() {
        return other_one;
    }

    public void setOther_one(String other_one) {
        this.other_one = other_one;
    }

    public String getOther_two() {
        return other_two;
    }

    public void setOther_two(String other_two) {
        this.other_two = other_two;
    }


    private String getString(String str){
        if(str==null||str.equals("")){
            return "";
        }else {
            return "、"+str;
        }
    }
    /*用来进行显示到搜索*/
    public String getThePj(){
        String result=getString(sheng)+getString(shi)+getString(xian)+getString(xiaoqu)+getString(dd_lx)+getString(st_time)+getString(ed_time)+getString(zf_zt)+getString(fh_zt)+getString(dd_zt)+getString(other_one)+getString(other_two);
        if(result.startsWith("、")){
            return result.substring(1,result.length());
        }else {
            return result;
        }

    }
}
