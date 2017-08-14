package com.kenzz.weatherapp.beans;

/**
 * Created by huangdefa on 15/08/2017.
 */

public class AQIModel {
    private BasicAddress basic;
    private String status;
    private AirQualityWrapper aqi;

    public AQIModel() {
    }

    public AQIModel(BasicAddress basic, String status, AirQualityWrapper aqi) {
        this.basic = basic;
        this.status = status;
        this.aqi = aqi;
    }

    public BasicAddress getBasic() {
        return basic;
    }

    public void setBasic(BasicAddress basic) {
        this.basic = basic;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public AirQualityWrapper getAqi() {
        return aqi;
    }

    public void setAqi(AirQualityWrapper aqi) {
        this.aqi = aqi;
    }

    static class AirQualityWrapper{

        public AirQuality getCity() {
            return city;
        }

        public void setCity(AirQuality city) {
            this.city = city;
        }

        private AirQuality city;

        public AirQualityWrapper() {
        }

        public AirQualityWrapper(AirQuality city) {
            this.city = city;
        }
    }
}
