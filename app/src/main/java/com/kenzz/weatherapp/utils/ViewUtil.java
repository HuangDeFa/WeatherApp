package com.kenzz.weatherapp.utils;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by ken.huang on 8/10/2017.
 */

public class ViewUtil {
    public static  <T extends View> T findViewById(@NonNull Activity context, int resourceId){
        return context.findViewById(resourceId);
    }

    public static  <T extends View> T findViewById(@NonNull View view,int resId){
        return view.findViewById(resId);
    }
}
