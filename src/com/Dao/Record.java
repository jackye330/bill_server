package com.Dao;

import java.sql.Date;
import java.sql.Timestamp;

public class Record {
    private int id;
    private int uid;
    private int type;
    private double amount;
    private String date;
    private String remark;
    private String category;
    private String uuid;
    private long time;
    public Record(){

    }
    public Record(int uid, double amount, String category, int type, String uuid, String date, String remark){
        this.uid = uid;
        this.amount = amount;
        this.category = category;
        this.type = type;
        this.uuid = uuid;
        this.date = date;
        this.remark = remark;
    }

    public Record(int uid, double amount, String category, int type, String uuid, String date, String remark, long time){
        this.uid = uid;
        this.amount = amount;
        this.category = category;
        this.type = type;
        this.uuid = uuid;
        this.date = date;
        this.remark = remark;
        this.time = time;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

}
