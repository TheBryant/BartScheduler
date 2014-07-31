package com.unknottedb.bartscheduler;


public class BartStation {
    private String mName;
    private String mAbbr;
    private String mAddress;
    private String mCity;
    private String mCounty;
    private int mZip;
    private Long mLatitude;
    private Long mLongitude;

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getAbbr() {
        return mAbbr;
    }

    public void setAbbr(String abbr) {
        mAbbr = abbr;
    }

    public String getAddress() {
        return mAddress;
    }

    public void setAddress(String address) {
        mAddress = address;
    }

    public String getCity() {
        return mCity;
    }

    public void setCity(String city) {
        mCity = city;
    }

    public String getCounty() {
        return mCounty;
    }

    public void setCounty(String county) {
        mCounty = county;
    }

    public int getZip() {
        return mZip;
    }

    public void setZip(int zip) {
        mZip = zip;
    }

    public Long getLatitude() {
        return mLatitude;
    }

    public void setLatitude(Long latitude) {
        mLatitude = latitude;
    }

    public Long getLongitude() {
        return mLongitude;
    }

    public void setLongitude(Long longitude) {
        mLongitude = longitude;
    }
}
