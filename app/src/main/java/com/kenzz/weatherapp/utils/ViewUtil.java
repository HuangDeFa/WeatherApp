package com.kenzz.weatherapp.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.ContextMenu;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.kenzz.weatherapp.R;

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

    public static int getStatusBarHeight(Context context){
        int resourceId=context.getResources().getIdentifier("status_bar_height","dimen","android");
        if(resourceId>0){
            return (int) context.getResources().getDimension(resourceId);
        }
        return 0;
    }

    /**
     *  沉浸式状态栏设置：理解window.addFlag. view.setSystemUiVisibility方法。
     *  沉浸式必须4.4以上：4.4-5.0之间可以获取状态栏的高度。将状态栏透明化,然后额外给decorView添加一个View伪装
     *  状态栏并设置颜色达到沉浸式效果
     *  5.0+ 以后可以直接通过API window.setStatusBarColor设置 前提是设置flag:FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS
     *  并清除FLAG_TRANSLUCENT_STATUS。
     *  另外6.0 view.setSystemUiVisibility API新加了Flag:SYSTEM_UI_FLAG_LIGHT_STATUS_BAR,设置该flag后
     *  状态栏的图标颜色根据状态栏颜色调整成白色或者黑色。eg:状态栏白色那么图标变成黑色。
     * @param activity
     * @param recoverColor
     */
    public static void recoverStatusBar(Activity activity,int recoverColor){
        if(recoverColor==0){
            recoverColor=activity.getResources().getColor(R.color.colorAccent);
        }
        //4.4 -5.0
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.KITKAT && Build.VERSION.SDK_INT<Build.VERSION_CODES.LOLLIPOP){
            //状态栏设置透明添加StatusBarView 并将statusBarView设置颜色即可
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            int h=getStatusBarHeight(activity);
            View view=new View(activity);
            view.setBackgroundColor(recoverColor);
            ViewGroup.LayoutParams lp=new FrameLayout.LayoutParams(-1,h);
            view.setLayoutParams(lp);
            ViewGroup decorView= (ViewGroup) activity.getWindow().getDecorView();
            decorView.addView(view);
        }
        else if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            activity.getWindow().setStatusBarColor(recoverColor);
            if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
                activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            }
        }
    }
}
