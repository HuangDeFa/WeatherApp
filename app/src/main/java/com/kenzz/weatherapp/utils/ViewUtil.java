package com.kenzz.weatherapp.utils;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.ContextMenu;
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

    public static int dpTopx(Context context,float dp){
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return (int) (displayMetrics.scaledDensity*dp+0.5f);
    }

    public static int pxTodp(Context context,float px){
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return (int) (px/displayMetrics.scaledDensity-0.5f);
    }
}
