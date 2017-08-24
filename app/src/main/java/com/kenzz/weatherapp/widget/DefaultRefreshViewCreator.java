package com.kenzz.weatherapp.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.RotateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.kenzz.weatherapp.R;

/**
 * Created by ken.huang on 8/24/2017.
 */

public class DefaultRefreshViewCreator extends RefreshViewCreator {

    private TextView mTitleView;
    private ProgressBar mLoadingView;
    private ImageView mIndicatorView;
    private View container;
    private RotateAnimation upArrowAnim;
    private RotateAnimation downArrowAnim;

    private int lastStatus;

    public DefaultRefreshViewCreator(Context context) {
        super(context);
    }

    @Override
    public void pullToRefresh(int distance, int state) {
        if(lastStatus==state) return;
     if(state==RefreshRecyclerView.NORMAL_STATE){
         mTitleView.setText("下拉刷新");
         mLoadingView.setVisibility(View.GONE);
         if(downArrowAnim==null) {
             downArrowAnim = new RotateAnimation(180, 360, RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
             downArrowAnim.setFillAfter(true);
         }
         downArrowAnim.cancel();
         downArrowAnim.setFillAfter(true);
         mIndicatorView.startAnimation(downArrowAnim);
     }else if(state==RefreshRecyclerView.PULLTOREFRESH_STATE){
         mTitleView.setText("释放刷新");
         mLoadingView.setVisibility(View.GONE);
         if(upArrowAnim==null) {
             upArrowAnim = new RotateAnimation(0, 180, RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
             upArrowAnim.setFillAfter(true);
         }
         upArrowAnim.cancel();
         mIndicatorView.startAnimation(upArrowAnim);
     }
     lastStatus=state;
    }

    @Override
    public void onRefresh() {
        mTitleView.setText("正在刷新");
        mIndicatorView.clearAnimation();
        mIndicatorView.setVisibility(View.GONE);
        mLoadingView.setVisibility(View.VISIBLE);
    }

    @Override
    public void stopRefresh() {
        mTitleView.setText("下拉刷新");
        mIndicatorView.setVisibility(View.VISIBLE);
        mLoadingView.setVisibility(View.GONE);
        upArrowAnim.cancel();
        downArrowAnim.cancel();
    }

    @Override
    public View createRefreshView() {
        container= LayoutInflater.from(mContext).inflate(R.layout.refresh_head_view,null);
        mTitleView=container.findViewById(R.id.refresh_head_title);
        mLoadingView=container.findViewById(R.id.refresh_head_loading);
        mIndicatorView=container.findViewById(R.id.refresh_head_indicator);
        return container;
    }
}
