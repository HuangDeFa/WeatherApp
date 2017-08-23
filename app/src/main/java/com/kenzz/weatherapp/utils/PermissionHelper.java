package com.kenzz.weatherapp.utils;

import android.app.Activity;
import android.app.Fragment;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.view.ViewOutlineProvider;

import java.util.List;

/**
 * Created by ken.huang on 8/23/2017.
 */

public class PermissionHelper {
    //Activity 或者是 Fragment
    private Object mObject;
    //请求码
    private int mRequestCode;
    //请求的权限
    private String[] mRequestPermissions;

    private PermissionHelper(Object object){ this.mObject=object;}

    public static PermissionHelper with(Activity activity){
       return new PermissionHelper(activity);
    }

    public static PermissionHelper with(Fragment fragment){
       return new PermissionHelper(fragment);
    }

    public PermissionHelper requestCode(int requestCode){
        this.mRequestCode =requestCode;
        return this;
    }

    public PermissionHelper requestPermissions(String ... requestPermissions){
        this.mRequestPermissions =requestPermissions;
        return this;
    }

    public static void requestPermission(Activity activity,int requestCode,String... requestPermissions){
        PermissionHelper.with(activity)
                .requestCode(requestCode)
                .requestPermissions(requestPermissions)
                .request();
    }

    public static void requestPermission(Fragment fragment,int requestCode,String... requestPermissions){
        PermissionHelper.with(fragment)
                .requestCode(requestCode)
                .requestPermissions(requestPermissions)
                .request();
    }

    public void request(){
        if(PermissionUtil.isOverAndroidM()){
            List<String> deniedPermissions = PermissionUtil.getDeniedPermissions(mObject, mRequestPermissions);
            if(deniedPermissions.size()==0){
                PermissionUtil.executeSuccessMethod(mObject,mRequestCode);
            }else {
                ActivityCompat.requestPermissions(PermissionUtil.getActivity(mObject),
                        deniedPermissions.toArray(new String[deniedPermissions.size()]),mRequestCode);
            }
        }else {
            PermissionUtil.executeSuccessMethod(mObject,mRequestCode);
        }
    }

    public static void onRequestPermissionResult(Object object,int requestCode, @NonNull String[] permissions,@NonNull int[] grantResults){
        for (int result :
                grantResults) {
            if (result == PackageManager.PERMISSION_DENIED){
                PermissionUtil.executeFaildMethod(object,requestCode);
                return;
            }
        }
       PermissionUtil.executeSuccessMethod(object,requestCode);
    }

}
