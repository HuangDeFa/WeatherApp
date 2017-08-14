package com.kenzz.weatherapp.beans;

/**
 * Created by ken.huang on 8/14/2017.
 */

public class WeatherModel {

    aqi mAqi;

    String status;

    static class aqi{
     city mCity;
    }

    static class city{
        int aqi;
        int co;
        int no2;
        int o3;
        float pm10;
        float pm25;
        String qlty;
        int so2;
    }

    static class basic{
        String city;
        String cnty;
        String id;
        String lat;
        String lon;

    }

    static class astor{
        String mr;
        String ms;
        String sr;
        String ss;
    }

    static class cond{
        int code_d;
        int code_n;
        String text_d;
        String text_n;
    }

    static class tmp{
        float max;
        float min;
    }

    static class wind{
        int deg;
        String dir;
        String sc;
        int spd;
    }

    /*********** Suggestion***************/

    static class suggesstion{

    }

    static class ari{String brf;String text;}

}
