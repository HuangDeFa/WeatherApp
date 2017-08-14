package com.kenzz.weatherapp.net;

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
    Call<ResponseBody> getWeather(@Query("city") String city,@Query("key") String apiKey);
}
