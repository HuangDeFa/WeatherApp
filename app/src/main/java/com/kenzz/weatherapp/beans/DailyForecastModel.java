package com.kenzz.weatherapp.beans;

import java.util.List;

/**
 * Created by huangdefa on 15/08/2017.
 */

public class DailyForecastModel {
    private BasicAddress basic;
    private DailyForecast daily_forecast;
    private String status;

    public DailyForecastModel(BasicAddress basic, DailyForecast daily_forecast, String status) {
        this.basic = basic;
        this.daily_forecast = daily_forecast;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static class DailyForecastModelWrapper{
        private List<DailyForecastModel> HeWeather5;

        public List<DailyForecastModel> getHeWeather5() {
            return HeWeather5;
        }

        public void setHeWeather5(List<DailyForecastModel> heWeather5) {
            HeWeather5 = heWeather5;
        }
    }
}
