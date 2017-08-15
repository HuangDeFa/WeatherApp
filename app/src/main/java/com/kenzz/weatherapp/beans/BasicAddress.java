package com.kenzz.weatherapp.beans;

import java.security.Timestamp;

/**
 * Created by huangdefa on 14/08/2017.
 */

public class BasicAddress {
    //城市名
    private String city;
    //国家
    private String cnty;
    //城市ID
    private String id;
    //城市维度
    private String lat;
    //城市经度
    private String lon;

    public TimeStamp getUpdate() {
        return update;
    }

    public void setUpdate(TimeStamp update) {
        this.update = update;
    }

    private TimeStamp update;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCnty() {
        return cnty;
    }

    public void setCnty(String cnty) {
        this.cnty = cnty;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getProv() {
        return prov;
    }

    public void setProv(String prov) {
        this.prov = prov;
    }


    //所属省份
    public String prov;

    public BasicAddress() {
    }

    public BasicAddress(String city, String cnty, String id, String lat, String lon, String prov, TimeStamp update) {
        this.city = city;
        this.cnty = cnty;
        this.id = id;
        this.lat = lat;
        this.lon = lon;
        this.prov = prov;
        this.update=update;
    }

   public static class TimeStamp{
        //当地时间
        private String loc;
        //UTC 时间
        private String utc;

        public String getLoc() {
            return loc;
        }

        public void setLoc(String loc) {
            this.loc = loc;
        }

        public String getUtc() {
            return utc;
        }

        public void setUtc(String utc) {
            this.utc = utc;
        }

        public TimeStamp() {
        }

        public TimeStamp(String loc, String utc) {
            this.loc = loc;
            this.utc = utc;
        }
    }
}
