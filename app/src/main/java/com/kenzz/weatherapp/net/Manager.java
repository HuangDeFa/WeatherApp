package com.kenzz.weatherapp.net;

import android.util.Log;
import android.view.ViewOutlineProvider;

import com.kenzz.weatherapp.beans.AQIModel;
import com.kenzz.weatherapp.beans.AddressModel;
import com.kenzz.weatherapp.beans.DailyForecastModel;
import com.kenzz.weatherapp.beans.HourForecastModel;
import com.kenzz.weatherapp.beans.LifeSuggestionModel;
import com.kenzz.weatherapp.beans.NowWeatherModel;
import com.kenzz.weatherapp.beans.WeatherModel;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ken.huang on 8/14/2017.
 */

public class Manager {

    private static final String TAG=Manager.class.getSimpleName();

   private Retrofit mRetrofit;
   private Api mApi;
    public Manager Build(String baseUrl){
        if(mRetrofit==null){
            mRetrofit=new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return instance;
    }

    private Manager(){}
    static Manager instance=new Manager();
    public static Manager getInstance(){
        return instance;
    }

    private Api getApi(){
        if(mApi==null){
            mApi=mRetrofit.create(Api.class);
        }
        return mApi;
    }

    /**
     * 获取详细天气
     * @param city 城市名称/拼音/代码
     */
    public void getWeather(String city){
        getApi().getWeather(city,Api.ApiKey).enqueue(new Callback<WeatherModel.WeatherModelWrapper>() {
            @Override
            public void onResponse(Call<WeatherModel.WeatherModelWrapper> call, Response<WeatherModel.WeatherModelWrapper> response) {
                if(response.isSuccessful()){
                    //成功

                }else {
                    //失败

                }
               Log.d(TAG,response.toString());
            }

            @Override
            public void onFailure(Call<WeatherModel.WeatherModelWrapper> call, Throwable t) {

            }
        });
    }

    /**
     * 获取未来三天的天气
     * @param city 城市名称/拼音/代码
     */
    public void getDailyForecastWeather(String city){
       getApi().getDailyForecastWeather(city,Api.ApiKey).enqueue(new Callback<DailyForecastModel.DailyForecastModelWrapper>() {
           @Override
           public void onResponse(Call<DailyForecastModel.DailyForecastModelWrapper> call, Response<DailyForecastModel.DailyForecastModelWrapper> response) {

           }

           @Override
           public void onFailure(Call<DailyForecastModel.DailyForecastModelWrapper> call, Throwable t) {

           }
       });
    }

    /**
     * 获取未来三小时天气预报
     * @param city 城市名称/拼音/代码
     */
    public void getHourForecastWeather(String city){
        getApi().getHourForecastWeather(city,Api.ApiKey).enqueue(new Callback<HourForecastModel.HourForecastModelWrapper>() {
            @Override
            public void onResponse(Call<HourForecastModel.HourForecastModelWrapper> call, Response<HourForecastModel.HourForecastModelWrapper> response) {

            }

            @Override
            public void onFailure(Call<HourForecastModel.HourForecastModelWrapper> call, Throwable t) {

            }
        });
    }

    /**
     * 获取当前天气
     * @param city 城市名称/拼音/代码
     */
    public void getNowWeather(String city){
        getApi().getWeatherNow(city,Api.ApiKey).enqueue(new Callback<NowWeatherModel.NowWeatherModelWrapper>() {
            @Override
            public void onResponse(Call<NowWeatherModel.NowWeatherModelWrapper> call, Response<NowWeatherModel.NowWeatherModelWrapper> response) {

            }

            @Override
            public void onFailure(Call<NowWeatherModel.NowWeatherModelWrapper> call, Throwable t) {

            }
        });
    }

    /**
     * 获取空气质量
     * @param city 城市名称/拼音/代码
     */
    public void getAirQuality(String city){
        getApi().getAirQuality(city,Api.ApiKey).enqueue(new Callback<AQIModel.AQIModelWrapper>() {
            @Override
            public void onResponse(Call<AQIModel.AQIModelWrapper> call, Response<AQIModel.AQIModelWrapper> response) {

            }

            @Override
            public void onFailure(Call<AQIModel.AQIModelWrapper> call, Throwable t) {

            }
        });
    }

    /**
     *  获取生活指数
     * @param city 城市名称/拼音/代码
     */
    public void getLifeSuggestion(String city){
        getApi().getSuggestion(city,Api.ApiKey).enqueue(new Callback<LifeSuggestionModel.LifeSuggestionModelWrapper>() {
            @Override
            public void onResponse(Call<LifeSuggestionModel.LifeSuggestionModelWrapper> call, Response<LifeSuggestionModel.LifeSuggestionModelWrapper> response) {

            }

            @Override
            public void onFailure(Call<LifeSuggestionModel.LifeSuggestionModelWrapper> call, Throwable t) {

            }
        });
    }

    /**
     * 获取城市
     * @param city 名称/拼音/ID/经纬度/IP；
     */
    public void searchAddress(String city){
        getApi().searchAddress(city,Api.ApiKey).enqueue(new Callback<AddressModel.AddressModelWrapper>() {
            @Override
            public void onResponse(Call<AddressModel.AddressModelWrapper> call, Response<AddressModel.AddressModelWrapper> response) {

            }

            @Override
            public void onFailure(Call<AddressModel.AddressModelWrapper> call, Throwable t) {

            }
        });
    }
}
