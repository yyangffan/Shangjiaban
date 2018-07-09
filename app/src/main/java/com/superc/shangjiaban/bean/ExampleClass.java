package com.superc.shangjiaban.bean;

import java.io.Serializable;

/**
 * Created by user on 2018/3/1.
 */

public class ExampleClass implements Serializable{
    private String number;
    private String name;
    private String other1;
    private String other2;
    private String phone;
    private String time;
    private boolean isGo;

    public boolean isGo() {
        return isGo;
    }

    public void setGo(boolean go) {
        isGo = go;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOther1() {
        return other1;
    }

    public void setOther1(String other1) {
        this.other1 = other1;
    }

    public String getOther2() {
        return other2;
    }

    public void setOther2(String other2) {
        this.other2 = other2;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public ExampleClass(String number) {
        this.number = number;
    }

    public ExampleClass(String number, String name) {
        this.number = number;
        this.name = name;
    }

    public ExampleClass(String number, String name, String other1) {
        this.number = number;
        this.name = name;
        this.other1 = other1;
    }

    public ExampleClass(String number, String name, String other1, String other2) {
        this.number = number;
        this.name = name;
        this.other1 = other1;
        this.other2 = other2;
    }

    public ExampleClass(String number, String name, String other1, String other2, String phone) {
        this.number = number;
        this.name = name;
        this.other1 = other1;
        this.other2 = other2;
        this.phone = phone;
    }

    public ExampleClass(String number, String name, String other1, String other2, String phone, String time) {
        this.number = number;
        this.name = name;
        this.other1 = other1;
        this.other2 = other2;
        this.phone = phone;
        this.time = time;
    }

}
