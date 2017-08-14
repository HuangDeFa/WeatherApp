package com.kenzz.weatherapp.beans;

/**
 * Created by huangdefa on 15/08/2017.
 */

public class NowWeatherModel {
    private BasicAddress basic;
    private NowWeather now;
    private String status;

    public NowWeatherModel(BasicAddress basic, NowWeather now, String status) {
        this.basic = basic;
        this.now = now;
        this.status = status;
    }

    public BasicAddress getBasic() {
        return basic;
    }

    public void setBasic(BasicAddress basic) {
        this.basic = basic;
    }

    public NowWeather getNow() {
        return now;
    }

    public void setNow(NowWeather now) {
        this.now = now;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
