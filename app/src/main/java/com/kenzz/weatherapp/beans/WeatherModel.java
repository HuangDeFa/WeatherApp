package com.kenzz.weatherapp.beans;

import java.util.List;

/**
 * Created by ken.huang on 8/14/2017.
 */

public class WeatherModel {

   private AQIModel.AirQualityWrapper aqi;

   private String status;

    private BasicAddress basic;

   private List<DailyForecast> daily_forecast;
   private List<HourForeCast> hourly_forecast;
   private NowWeather now;
   private LifeSuggestionModel.LifeSuggestionWrapper suggestion;

    public WeatherModel() {
    }

    public WeatherModel(AQIModel.AirQualityWrapper aqi, String status, BasicAddress basic, List<DailyForecast> daily_forecast, List<HourForeCast> hourly_forecast, NowWeather now, LifeSuggestionModel.LifeSuggestionWrapper suggestion) {
        this.aqi = aqi;
        this.status = status;
        this.basic = basic;
        this.daily_forecast = daily_forecast;
        this.hourly_forecast = hourly_forecast;
        this.now = now;
        this.suggestion = suggestion;
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

    public List<DailyForecast> getDaily_forecast() {
        return daily_forecast;
    }

    public void setDaily_forecast(List<DailyForecast> daily_forecast) {
        this.daily_forecast = daily_forecast;
    }

    public List<HourForeCast> getHourly_forecast() {
        return hourly_forecast;
    }

    public void setHourly_forecast(List<HourForeCast> hourly_forecast) {
        this.hourly_forecast = hourly_forecast;
    }

    public NowWeather getNow() {
        return now;
    }

    public void setNow(NowWeather now) {
        this.now = now;
    }

    public LifeSuggestionModel.LifeSuggestionWrapper getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(LifeSuggestionModel.LifeSuggestionWrapper suggestion) {
        this.suggestion = suggestion;
    }

    public static class WeatherModelWrapper{
        private List<WeatherModel> HeWeather5;

        public List<WeatherModel> getHeWeather5() {
            return HeWeather5;
        }

        public void setHeWeather5(List<WeatherModel> heWeather5) {
            HeWeather5 = heWeather5;
        }
    }
}
