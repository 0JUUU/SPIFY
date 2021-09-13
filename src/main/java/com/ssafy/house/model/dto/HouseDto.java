package com.ssafy.house.model.dto;

public class HouseDto {
    private int no;
    private String aptName;
    private String  dong;
    private String jibun;
    private String floor;
    private String type;
    private String gunguCode;
    private String dealAmount;
    private String buildYear;
    private String area;
    private String lat;
    private String lng;

    public HouseDto() {
        super();
        // TODO Auto-generated constructor stub
    }
    public HouseDto(int no, String aptName, String dong, String jibun, String floor, String type, String gunguCode,
                    String dealAmount, String buildYear, String area, String lat, String lng) {
        super();
        this.no = no;
        this.aptName = aptName;
        this.dong = dong;
        this.jibun = jibun;
        this.floor = floor;
        this.type = type;
        this.gunguCode = gunguCode;
        this.dealAmount = dealAmount;
        this.buildYear = buildYear;
        this.area = area;
        this.lat = lat;
        this.lng = lng;
    }
    public int getNo() {
        return no;
    }
    public void setNo(int no) {
        this.no = no;
    }
    public String getAptName() {
        return aptName;
    }
    public void setAptName(String aptName) {
        this.aptName = aptName;
    }
    public String getDong() {
        return dong;
    }
    public void setDong(String dong) {
        this.dong = dong;
    }
    public String getJibun() {
        return jibun;
    }
    public void setJibun(String jibun) {
        this.jibun = jibun;
    }
    public String getFloor() {
        return floor;
    }
    public void setFloor(String floor) {
        this.floor = floor;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getGunguCode() {
        return gunguCode;
    }
    public void setGunguCode(String gunguCode) {
        this.gunguCode = gunguCode;
    }
    public String getDealAmount() {
        return dealAmount;
    }
    public void setDealAmount(String dealAmount) {
        this.dealAmount = dealAmount;
    }
    public String getBuildYear() {
        return buildYear;
    }
    public void setBuildYear(String buildYear) {
        this.buildYear = buildYear;
    }
    public String getArea() {
        return area;
    }
    public void setArea(String area) {
        this.area = area;
    }
    public String getLat() {
        return lat;
    }
    public void setLat(String lat) {
        this.lat = lat;
    }
    public String getLng() {
        return lng;
    }
    public void setLng(String lng) {
        this.lng = lng;
    }
}
