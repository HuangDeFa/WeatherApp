package com.kenzz.weatherapp;

import android.app.Application;

import com.kenzz.weatherapp.net.Api;
import com.kenzz.weatherapp.net.Manager;

/**
 * Created by huangdefa on 10/08/2017.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {

        super.onCreate();
        Manager.getInstance().Build(Api.BaseUrl);
    }
}
