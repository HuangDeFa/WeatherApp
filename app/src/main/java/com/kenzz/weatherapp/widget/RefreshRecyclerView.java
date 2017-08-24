package com.kenzz.weatherapp.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by huangdefa on 23/08/2017.
 * 支持下拉刷新的RecyclerView
 */

public class RefreshRecyclerView extends WrapperCommonRecyclerView {

    public final static int NORMAL_STATE = 100;
    public final static int PULLTOREFRESH_STATE = 101;
    public final static int REFRESHING_STATE = 102;

    private int mCurrentState = NORMAL_STATE;
    private int mDownY;
    private RefreshViewCreator mRefreshViewCreator;
    private View mRefreshView;
    private int mRefreshViewHeight;
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
        if (mRefreshViewCreator == null) {
            mRefreshViewCreator = new DefaultRefreshViewCreator(getContext());
            mRefreshView = mRefreshViewCreator.createRefreshView();
            addHeadView(mRefreshView);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (changed) {
            if (mRefreshView != null && mRefreshViewHeight <= 0) {
                mRefreshViewHeight = mRefreshView.getMeasuredHeight();
                if (mRefreshViewHeight > 0) {
                    MarginLayoutParams lp = (MarginLayoutParams) mRefreshView.getLayoutParams();
                    lp.width = -1;
                    lp.height = 0;
                    mRefreshView.setLayoutParams(lp);
                }
            }
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mDownY = (int) ev.getRawY();
                break;
            case MotionEvent.ACTION_UP:
                if (mIsDrag) {
                    restoreRefreshView();
                }
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    private void restoreRefreshView() {
        //正在刷新
        if (mCurrentState == REFRESHING_STATE) return;
        if (mCurrentState == PULLTOREFRESH_STATE) {
            mCurrentState = REFRESHING_STATE;
        } else {
            mCurrentState = NORMAL_STATE;
        }
        final MarginLayoutParams lp = (MarginLayoutParams) mRefreshView.getLayoutParams();
        ValueAnimator animator = ValueAnimator.ofInt(lp.height, mCurrentState == REFRESHING_STATE ? mRefreshViewHeight + 1 : 0);
        animator.setDuration(200);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                lp.height = (int) animation.getAnimatedValue();
                mRefreshView.setLayoutParams(lp);
            }
        });
        animator.start();
        mIsDrag = false;
        if (mCurrentState == REFRESHING_STATE) {
            mRefreshViewCreator.onRefresh();
            if(mRefreshListener!=null){
                mRefreshListener.onRefresh();
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        if (e.getAction() == MotionEvent.ACTION_MOVE) {
            if (canScroll() || mCurrentState == REFRESHING_STATE) {
                return super.onTouchEvent(e);
            }
            if (mIsDrag) {
                scrollToPosition(0);
            }

            int dy = (int) (e.getRawY() - mDownY);
            if (dy > 0) {
                mIsDrag = true;
                updateRefreshStatus(dy);
                if (mRefreshViewCreator != null) {
                    mRefreshViewCreator.pullToRefresh(dy, mCurrentState);
                }
                setRefreshViewHeight(dy);
                return false;
            }

        }
        return super.onTouchEvent(e);
    }

    private void setRefreshViewHeight(int marginTop) {
        MarginLayoutParams lp = (MarginLayoutParams) mRefreshView.getLayoutParams();
        if (marginTop < 1) {
            marginTop = 1;
        }
        lp.height = marginTop;
        mRefreshView.setLayoutParams(lp);
    }

    private void updateRefreshStatus(int distance) {
        distance = distance - mRefreshViewHeight;
        if (distance <= 0) {
            mCurrentState = NORMAL_STATE;
        } else if (distance > 0) {
            mCurrentState = PULLTOREFRESH_STATE;
        }
    }

    private boolean canScroll() {
        return canScrollVertically(-1);
    }

    public void stopRefresh(){
        if(mRefreshViewCreator!=null){
            mRefreshViewCreator.stopRefresh();
        }
        mCurrentState=NORMAL_STATE;
        restoreRefreshView();
    }

    private RefreshListener mRefreshListener;

    public void setRefreshListener(RefreshListener refreshListener) {
        mRefreshListener = refreshListener;
    }

    public static interface RefreshListener{
        void onRefresh();
    }
}
