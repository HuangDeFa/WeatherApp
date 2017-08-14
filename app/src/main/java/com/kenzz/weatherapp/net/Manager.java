package com.kenzz.weatherapp.net;

import android.util.Log;

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

    Retrofit mRetrofit;

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

    public void getWeather(String city){
        Api api = mRetrofit.create(Api.class);
        api.getWeather(city,Api.ApiKey).enqueue(new Callback<WeatherModel>() {
            @Override
            public void onResponse(Call<WeatherModel> call, Response<WeatherModel> response) {
               Log.d(TAG,response.toString());
            }

            @Override
            public void onFailure(Call<WeatherModel> call, Throwable t) {

            }
        });
    }
}
