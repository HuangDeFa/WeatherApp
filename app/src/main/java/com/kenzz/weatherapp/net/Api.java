package com.kenzz.weatherapp.net;

import com.kenzz.weatherapp.beans.WeatherModel;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by ken.huang on 8/14/2017.
 */

public interface Api {
   public static final String ApiKey="e36170cff2dd451dbae6ff4e116bd186";
   public static final String BaseUrl = "https://free-api.heweather.com/v5/";

    @GET("weather")
    Call<WeatherModel> getWeather(@Query("city") String city, @Query("key") String apiKey);

    /**
     * 获取未来三天的预报信息
     * @param city
     * @param apiKey
     * @return
     */
    @GET("forecast")
    Call<WeatherModel> getDailyForecastWeather(@Query("city") String city, @Query("key") String apiKey);

    /**
     * 获取当前天气信息
     * @param city
     * @param apiKey
     * @return
     */
    @GET("now")
    Call<WeatherModel> getWeatherNow(@Query("city") String city, @Query("key") String apiKey);

    /**
     *  获取未来三小时天气状况
     * @param city
     * @param apiKey
     * @return
     */
    @GET("hourly")
    Call<WeatherModel> getHourForecastWeather(@Query("city") String city, @Query("key") String apiKey);

    /**
     *  获取空气质量
     * @param city
     * @param apiKey
     * @return
     */
    @GET("aqi")
    Call<WeatherModel> getAirQuality(@Query("city") String city, @Query("key") String apiKey);

    /**
     * 获取7大生活指数
     * @param city
     * @param apiKey
     * @return
     */
    @GET("suggestion")
    Call<WeatherModel> getSuggestion(@Query("city") String city, @Query("key") String apiKey);
}
