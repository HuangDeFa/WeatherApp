package com.kenzz.weatherapp.activity;

import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.kenzz.weatherapp.annotations.PermissionFailed;
import com.kenzz.weatherapp.annotations.PermissionSuccess;
import com.kenzz.weatherapp.utils.PermissionHelper;

public abstract class BaseActivity extends AppCompatActivity {

    private static final int REQ_PERMISSION_CODE = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(getLayoutId());
        setFullScreen();
        initView();
    }

    protected abstract int getLayoutId();

    protected abstract void initView();

    protected void setFullScreen() {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
    }

    @PermissionSuccess(requestCode =REQ_PERMISSION_CODE)
    protected void onRequestPermissionSuccess() {

    }

    @PermissionFailed(requestCode = REQ_PERMISSION_CODE)
    protected void onRequestPermissionFailed() {

    }

    @RequiresApi(Build.VERSION_CODES.M)
    protected void checkPermission(String permissionString) {
      PermissionHelper.with(this)
              .requestCode(REQ_PERMISSION_CODE)
              .requestPermissions(permissionString)
              .request();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQ_PERMISSION_CODE) {
            PermissionHelper.onRequestPermissionResult(this,requestCode,permissions,grantResults);
        }
    }
}
