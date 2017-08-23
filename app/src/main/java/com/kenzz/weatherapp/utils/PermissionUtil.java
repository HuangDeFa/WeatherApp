package com.kenzz.weatherapp.utils;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;

import com.kenzz.weatherapp.annotations.PermissionFailed;
import com.kenzz.weatherapp.annotations.PermissionSuccess;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ken.huang on 8/23/2017.
 */

public class PermissionUtil {

    public static boolean isOverAndroidM() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
    }

    public static Activity getActivity(Object object){
        if(object instanceof Activity) return (Activity) object;
        else if(object instanceof Fragment) return ((Fragment)object).getActivity();
        else throw new IllegalArgumentException("object must be activity or fragment");
    }

    public static List<String> getDeniedPermissions(Object object, String... permissions){
        Activity activity=getActivity(object);
        List<String> deniedPermissions=new ArrayList<>();
        for (String permission : permissions) {
            int selfPermission = ActivityCompat.checkSelfPermission(activity, permission);
            if(selfPermission== PackageManager.PERMISSION_DENIED){
                deniedPermissions.add(permission);
            }
        }
        return deniedPermissions;
    }

    public static void executeSuccessMethod(Object object, int requestCode) {
        Method[] methods = object.getClass().getDeclaredMethods();
        for (Method method : methods) {
            PermissionSuccess permissionSuccess = method.getAnnotation(PermissionSuccess.class);
            if (permissionSuccess != null) {
                if (permissionSuccess.requestCode() == requestCode) {
                    executeMethod(object, method);
                }
            }
        }
    }

    public static void executeFaildMethod(Object object, int requestCode) {
        Method[] methods = object.getClass().getDeclaredMethods();
        for (Method method : methods) {
            PermissionFailed permissionSuccess = method.getAnnotation(PermissionFailed.class);
            if (permissionSuccess != null) {
                if (permissionSuccess.requestCode() == requestCode) {
                    executeMethod(object, method);
                }
            }
        }
    }

    private static void executeMethod(Object object, Method method) {
        method.setAccessible(true);
        try {
            method.invoke(object, new Object[]{});
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

}
