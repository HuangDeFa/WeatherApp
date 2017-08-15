package com.kenzz.weatherapp.beans;

import java.util.List;

/**
 * Created by huangdefa on 15/08/2017.
 */

public class HourForecastModel {
    private BasicAddress basic;
    private HourForeCast hourly_forecast;
    private String status;

    public HourForecastModel(BasicAddress basic, HourForeCast hourly_forecast, String status) {
        this.basic = basic;
        this.hourly_forecast = hourly_forecast;
        this.status = status;
    }

    public BasicAddress getBasic() {
        return basic;
    }

    public void setBasic(BasicAddress basic) {
        this.basic = basic;
    }

    public HourForeCast getHourly_forecast() {
        return hourly_forecast;
    }

    public void setHourly_forecast(HourForeCast hourly_forecast) {
        this.hourly_forecast = hourly_forecast;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static class HourForecastModelWrapper{
        private List<HourForecastModel> HeWeather5;

        public List<HourForecastModel> getHeWeather5() {
            return HeWeather5;
        }

        public void setHeWeather5(List<HourForecastModel> heWeather5) {
            HeWeather5 = heWeather5;
        }
    }
}
