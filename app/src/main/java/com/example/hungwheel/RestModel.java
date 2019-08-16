package com.example.hungwheel;

import android.widget.RadioButton;

import java.util.ArrayList;

public class RestModel {

    String strUserUid;
    private String resname;
    private String res_key;
    private String cityname;
    private String owner;
    private String notowner;
    private String resnumber;
    private String placeopen;
    private String placeopensoon;
    private String resaddress;
    private String seat;
    private String noseat;
    private String cashandcard;
    private String cashonly;
    private String category;
    private String fromtime;
    private String totime;
    private String strUid;
    ArrayList<String> stringArrayList;
    private RadioButton ownerStatus;
    private RadioButton openingStatus;
    private RadioButton seatingStatus;
    private RadioButton paymentStatus;
    private String payment;
    private String foodType;
    private String money;

    public String getStrUserUid() {
        return strUserUid;
    }

    public void setStrUserUid(String strUserUid) {
        this.strUserUid = strUserUid;
    }

    public String getRes_key() {
        return res_key;
    }

    public void setRes_key(String res_key) {
        this.res_key = res_key;
    }

    public ArrayList<String> getStringArrayList() {
        return stringArrayList;
    }

    public void setStringArrayList(ArrayList<String> stringArrayList) {
        this.stringArrayList = stringArrayList;
    }

    public String getResname() {
        return resname;
    }

    public void setResname(String resname) {
        this.resname = resname;
    }

    public String getCityname() {
        return cityname;
    }

    public void setCityname(String cityname) {
        this.cityname = cityname;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getNotowner() {
        return notowner;
    }

    public void setNotowner(String notowner) {
        this.notowner = notowner;
    }

    public String getResnumber() {
        return resnumber;
    }

    public void setResnumber(String resnumber) {
        this.resnumber = resnumber;
    }

    public String getPlaceopen() {
        return placeopen;
    }

    public void setPlaceopen(String placeopen) {
        this.placeopen = placeopen;
    }

    public String getPlaceopensoon() {
        return placeopensoon;
    }

    public void setPlaceopensoon(String placeopensoon) {
        this.placeopensoon = placeopensoon;
    }

    public String getResaddress() {
        return resaddress;
    }

    public void setResaddress(String resaddress) {
        this.resaddress = resaddress;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public String getNoseat() {
        return noseat;
    }

    public void setNoseat(String noseat) {
        this.noseat = noseat;
    }

    public String getCashandcard() {
        return cashandcard;
    }

    public void setCashandcard(String cashandcard) {
        this.cashandcard = cashandcard;
    }

    public String getCashonly() {
        return cashonly;
    }

    public void setCashonly(String cashonly) {
        this.cashonly = cashonly;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getFromtime() {
        return fromtime;
    }

    public void setFromtime(String fromtime) {
        this.fromtime = fromtime;
    }

    public String getTotime() {
        return totime;
    }

    public void setTotime(String totime) {
        this.totime = totime;
    }

    public String getStrUid() {
        return strUid;
    }

    public void setStrUid(String strUid) {
        this.strUid = strUid;
    }


    public RadioButton getOwnerStatus() {
        return ownerStatus;
    }


    public RadioButton getOpeningStatus() {
        return openingStatus;
    }


    public RadioButton getSeatingStatus() {
        return seatingStatus;
    }


    public RadioButton getPaymentStatus() {
        return paymentStatus;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getPayment() {
        return payment;
    }

    public void setFoodType(String foodType) {
        this.foodType = foodType;
    }

    public String getFoodType() {
        return foodType;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getMoney() {
        return money;
    }
}
