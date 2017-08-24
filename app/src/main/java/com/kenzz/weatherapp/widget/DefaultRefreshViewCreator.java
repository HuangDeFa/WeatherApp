package com.kenzz.weatherapp.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.RotateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.kenzz.weatherapp.R;

/**
 * Created by ken.huang on 8/24/2017.
 */

public class DefaultRefreshViewCreator extends RefreshViewCreator {

    private TextView mTitleView;
    private ImageView mLoadingView;
    private ImageView mIndicatorView;
    private ViewGroup container;
    private RotateAnimation upArrowAnim;
    private RotateAnimation downArrowAnim;
    private RotateAnimation mRotateAnimation;

    public DefaultRefreshViewCreator(Context context) {
        super(context);
    }

    @Override
    public void pullToRefresh(int distance, int state) {
     if(state==RefreshRecyclerView.PULLTOREFRESH_STATE){
         mTitleView.setText("释放刷新");
         mLoadingView.setVisibility(View.GONE);
         if(upArrowAnim==null)
         upArrowAnim=new RotateAnimation(90,270,RotateAnimation.RELATIVE_TO_SELF,0.5f,RotateAnimation.RELATIVE_TO_SELF,0.5f);
         upArrowAnim.cancel();
         mIndicatorView.startAnimation(upArrowAnim);
     }else if(state==RefreshRecyclerView.REFRESHING_STATE){
         mTitleView.setText("正在刷新");
         mIndicatorView.setVisibility(View.GONE);
         mLoadingView.setVisibility(View.VISIBLE);
         if(mRotateAnimation==null)
             mRotateAnimation=new RotateAnimation(0,360,RotateAnimation.RELATIVE_TO_SELF,0.5f,RotateAnimation.RELATIVE_TO_SELF,0.5f);
             mRotateAnimation.setRepeatMode(RotateAnimation.INFINITE);
          mLoadingView.startAnimation(mRotateAnimation);
     }else {

     }
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void stopRefresh() {

    }

    @Override
    public View createRefreshView() {
        container= (ViewGroup) LayoutInflater.from(mContext).inflate(R.layout.refresh_head_view,null);
        mTitleView=container.findViewById(R.id.refresh_head_title);
        mLoadingView=container.findViewById(R.id.refresh_head_loading);
        mIndicatorView=container.findViewById(R.id.refresh_head_indicator);
        return container;
    }
}
