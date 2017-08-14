package com.kenzz.weatherapp.beans;

import java.util.List;

/**
 * Created by ken.huang on 8/14/2017.
 */

public class WeatherModel {

   private AQIModel.AirQualityWrapper aqi;

   private String status;

    private BasicAddress basic;

   private   DailyForecast daily_forecast;
   private HourForeCast hourly_forecast;
   private NowWeather now;
   private LifeSuggestionModle.LifeSuggestionWraper suggesstion;

    public WeatherModel() {
    }

    public WeatherModel(AQIModel.AirQualityWrapper aqi, String status, BasicAddress basic, DailyForecast daily_forecast, HourForeCast hourly_forecast, NowWeather now, LifeSuggestionModle.LifeSuggestionWraper suggesstion) {
        this.aqi = aqi;
        this.status = status;
        this.basic = basic;
        this.daily_forecast = daily_forecast;
        this.hourly_forecast = hourly_forecast;
        this.now = now;
        this.suggesstion = suggesstion;
    }

    public AQIModel.AirQualityWrapper getAqi() {
        return aqi;
    }

    public void setAqi(AQIModel.AirQualityWrapper aqi) {
        this.aqi = aqi;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BasicAddress getBasic() {
        return basic;
    }

    public void setBasic(BasicAddress basic) {
        this.basic = basic;
    }

    public DailyForecast getDaily_forecast() {
        return daily_forecast;
    }

    public void setDaily_forecast(DailyForecast daily_forecast) {
        this.daily_forecast = daily_forecast;
    }

    public HourForeCast getHourly_forecast() {
        return hourly_forecast;
    }

    public void setHourly_forecast(HourForeCast hourly_forecast) {
        this.hourly_forecast = hourly_forecast;
    }

    public NowWeather getNow() {
        return now;
    }

    public void setNow(NowWeather now) {
        this.now = now;
    }

    public LifeSuggestionModle.LifeSuggestionWraper getSuggesstion() {
        return suggesstion;
    }

    public void setSuggesstion(LifeSuggestionModle.LifeSuggestionWraper suggesstion) {
        this.suggesstion = suggesstion;
    }
}
