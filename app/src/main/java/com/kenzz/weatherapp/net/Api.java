package com.kenzz.weatherapp.net;

import com.kenzz.weatherapp.beans.AQIModel;
import com.kenzz.weatherapp.beans.AddressModel;
import com.kenzz.weatherapp.beans.DailyForecastModel;
import com.kenzz.weatherapp.beans.HourForecastModel;
import com.kenzz.weatherapp.beans.LifeSuggestionModel;
import com.kenzz.weatherapp.beans.NowWeatherModel;
import com.kenzz.weatherapp.beans.WeatherModel;

import java.util.List;

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
    Call<WeatherModel.WeatherModelWrapper> getWeather(@Query("city") String city, @Query("key") String apiKey);

    /**
     * 获取未来三天的预报信息
     * @param city
     * @param apiKey
     * @return
     */
    @GET("forecast")
    Call<DailyForecastModel.DailyForecastModelWrapper> getDailyForecastWeather(@Query("city") String city, @Query("key") String apiKey);

    /**
     * 获取当前天气信息
     * @param city
     * @param apiKey
     * @return
     */
    @GET("now")
    Call<NowWeatherModel.NowWeatherModelWrapper> getWeatherNow(@Query("city") String city, @Query("key") String apiKey);

    /**
     *  获取未来三小时天气状况
     * @param city
     * @param apiKey
     * @return
     */
    @GET("hourly")
    Call<HourForecastModel.HourForecastModelWrapper> getHourForecastWeather(@Query("city") String city, @Query("key") String apiKey);

    /**
     *  获取空气质量
     * @param city
     * @param apiKey
     * @return
     */
    @GET("aqi")
    Call<AQIModel.AQIModelWrapper> getAirQuality(@Query("city") String city, @Query("key") String apiKey);

    /**
     * 获取7大生活指数
     * @param city
     * @param apiKey
     * @return
     */
    @GET("suggestion")
    Call<LifeSuggestionModel.LifeSuggestionModelWrapper> getSuggestion(@Query("city") String city, @Query("key") String apiKey);

    /**
     * 获取城市
     * @param city 名称/拼音/ID/经纬度/IP；
     * @param apiKey
     * @return
     */
    @GET("search")
    Call<AddressModel.AddressModelWrapper> searchAddress(@Query("city") String city,@Query("key") String apiKey);
}
