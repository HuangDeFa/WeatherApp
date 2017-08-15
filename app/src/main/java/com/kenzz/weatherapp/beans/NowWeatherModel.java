package com.kenzz.weatherapp.beans;

import java.util.List;

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

    public static class NowWeatherModelWrapper{
        private List<NowWeatherModel> HeWeather5;

        public List<NowWeatherModel> getHeWeather5() {
            return HeWeather5;
        }

        public void setHeWeather5(List<NowWeatherModel> heWeather5) {
            HeWeather5 = heWeather5;
        }
    }
}
