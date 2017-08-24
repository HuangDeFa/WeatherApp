package com.kenzz.weatherapp.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by huangdefa on 23/08/2017.
 * 支持下拉刷新的RecyclerView
 */

public class RefreshRecyclerView extends WrapperCommonRecyclerView {

    public final static int NORMAL_STATE=100;
    public final static int PULLTOREFRESH_STATE=101;
    public final static int REFRESHING_STATE=102;

    private int mCurrentState=NORMAL_STATE;
    private int mDownY;
    private RefreshViewCreator mRefreshViewCreator;
    private View mRefreshView;
    private int mRefreshViewTop;
    private boolean mIsDrag;

    public RefreshRecyclerView(Context context) {
        super(context);
    }

    public RefreshRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public RefreshRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }


    @Override
    public void setAdapter(Adapter adapter) {
        super.setAdapter(adapter);
        if(mRefreshViewCreator==null){
            mRefreshViewCreator=new DefaultRefreshViewCreator(getContext());
            mRefreshView=mRefreshViewCreator.createRefreshView();
            addHeadView(mRefreshView);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if(mRefreshViewTop<=0){
           mRefreshViewTop=mRefreshView.getMeasuredHeight();
        }
        MarginLayoutParams lp= (MarginLayoutParams) mRefreshView.getLayoutParams();
        lp.topMargin=-mRefreshViewTop;
        mRefreshView.setLayoutParams(lp);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                mDownY= (int) ev.getRawY();
                mIsDrag=false;
                break;
            case MotionEvent.ACTION_UP:
                if(mIsDrag){
                    restoreRefreshView();
                }
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    private void restoreRefreshView() {
        //正在刷新
      if(mCurrentState==REFRESHING_STATE){
          final MarginLayoutParams lp= (MarginLayoutParams) mRefreshView.getLayoutParams();
          ValueAnimator animator= ValueAnimator.ofInt(lp.topMargin,mRefreshViewTop+1);
          animator.setDuration(200);
                animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                      @Override
                      public void onAnimationUpdate(ValueAnimator animation) {
                          lp.topMargin=(int)animation.getAnimatedValue();
                          mRefreshView.setLayoutParams(lp);
                      }
                  });
          animator.start();
          mRefreshViewCreator.onRefresh();
      }else {
          final MarginLayoutParams lp= (MarginLayoutParams) mRefreshView.getLayoutParams();
          ValueAnimator animator= ValueAnimator.ofInt(lp.topMargin,-mRefreshViewTop+1);
          animator.setDuration(200);
          animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
              @Override
              public void onAnimationUpdate(ValueAnimator animation) {
                  lp.topMargin=(int)animation.getAnimatedValue();
                  mRefreshView.setLayoutParams(lp);
              }
          });
          animator.start();
      }
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        if(e.getAction()==MotionEvent.ACTION_MOVE){
            if(canScroll() || mCurrentState==REFRESHING_STATE){
                return super.onTouchEvent(e);
            }
            int dy= (int) (e.getRawY()-mDownY);
            if(dy>0){
                mIsDrag=true;
                updateRefreshStatus(dy);
                if(mRefreshViewCreator!=null){
                    mRefreshViewCreator.pullToRefresh(dy,mCurrentState);
                }
            }
        }
        return super.onTouchEvent(e);
    }

    private void updateRefreshStatus(int distance) {
        if(distance>mRefreshViewTop+1){
            mCurrentState=REFRESHING_STATE;
        }else {
            mCurrentState=PULLTOREFRESH_STATE;
        }
    }

    private boolean canScroll(){
      return canScrollVertically(-1);
    }
}
