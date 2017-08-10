package com.kenzz.weatherapp;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.kenzz.weatherapp.activity.BaseActivity;
import com.kenzz.weatherapp.activity.CityActivity;
import com.kenzz.weatherapp.utils.ViewUtil;

public class MainActivity extends BaseActivity {

    private ImageView mCityManage;
    private ImageView mMenuView;
    private ViewPager mContentPage;
    private RelativeLayout mBottomLayout;
    private PopupWindow mPopupMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void setFullScreen() {

    }

    @Override
    protected void initView() {
        mCityManage= ViewUtil.findViewById(this,R.id.main_page_citymanage);
        mMenuView=ViewUtil.findViewById(this,R.id.main_page_menu);
        mContentPage=ViewUtil.findViewById(this,R.id.main_page_content);
        mBottomLayout=ViewUtil.findViewById(this,R.id.main_page_bottom);
        final View contentView=getLayoutInflater().inflate(R.layout.main_page_menu,mBottomLayout,false);
        mPopupMenu=new PopupWindow(contentView,-1,-2);
        mPopupMenu.setBackgroundDrawable(new ColorDrawable());
        mPopupMenu.setOutsideTouchable(true);
        mPopupMenu.setFocusable(true);
        mPopupMenu.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction()==MotionEvent.ACTION_OUTSIDE){
                    mPopupMenu.dismiss();
                    return true;
                }
                return false;
            }
        });
        mMenuView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!mPopupMenu.isShowing()){
                    mPopupMenu.showAsDropDown(mBottomLayout,0,10);
                }
            }
        });
        mCityManage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, CityActivity.class));
            }
        });
    }

}
