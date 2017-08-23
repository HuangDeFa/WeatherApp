package com.kenzz.weatherapp.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by huangdefa on 23/08/2017.
 *  支持添加Head和Foot的RecyclerView
 */

public class WrapperCommonRecyclerView extends RecyclerView {

    private WrapperCommonAdapter mWrapperAdapter;
    private Adapter mAdapter;

    public WrapperCommonRecyclerView(Context context) {
        super(context);
    }

    public WrapperCommonRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public WrapperCommonRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void setAdapter(Adapter adapter) {
        //防止多次设置Adapter
        if(mAdapter!=null){
            mAdapter.unregisterAdapterDataObserver(mDataObserver);
            mAdapter=null;
        }
        mAdapter = adapter;
        if(adapter instanceof WrapperCommonAdapter){
            mWrapperAdapter= (WrapperCommonAdapter) adapter;
        }else {
            mWrapperAdapter=new WrapperCommonAdapter(adapter);
        }
        super.setAdapter(mWrapperAdapter);
        mAdapter.registerAdapterDataObserver(mDataObserver);
        mWrapperAdapter.adjustSpanSize(this);
    }

    public void addHeadView(View view){
        if(mWrapperAdapter!=null){
            mWrapperAdapter.addHeader(view);
        }
    }

    public void removeHeadView(View view){
        if(mWrapperAdapter!=null){
            mWrapperAdapter.removeHeadView(view);
        }
    }

    public void addFooterView(View view){
        if(mWrapperAdapter!=null){
            mWrapperAdapter.addFooter(view);
        }
    }

    public void removeFooterView(View view){
        if(mWrapperAdapter!=null){
            mWrapperAdapter.removeFooterView(view);
        }
    }

    private AdapterDataObserver mDataObserver=new AdapterDataObserver() {
        @Override
        public void onChanged() {
           if(mAdapter==null) return;
            if(mWrapperAdapter!=mAdapter)
                mWrapperAdapter.notifyDataSetChanged();
        }

        @Override
        public void onItemRangeChanged(int positionStart, int itemCount) {
            if(mAdapter==null) return;
            if(mWrapperAdapter!=mAdapter)
                mWrapperAdapter.notifyItemRangeChanged(positionStart,itemCount);
        }

        @Override
        public void onItemRangeChanged(int positionStart, int itemCount, Object payload) {
            if(mAdapter==null) return;
            if(mWrapperAdapter!=mAdapter)
                mWrapperAdapter.notifyItemChanged(positionStart,payload);
        }

        @Override
        public void onItemRangeInserted(int positionStart, int itemCount) {
            if(mAdapter==null) return;
            if(mWrapperAdapter!=mAdapter)
                mWrapperAdapter.notifyItemInserted(positionStart);
        }

        @Override
        public void onItemRangeRemoved(int positionStart, int itemCount) {
            if(mAdapter==null) return;
            if(mWrapperAdapter!=mAdapter)
                mWrapperAdapter.notifyItemRemoved(positionStart);
        }

        @Override
        public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
            if(mAdapter==null) return;
            if(mWrapperAdapter!=mAdapter)
                mWrapperAdapter.notifyItemMoved(fromPosition,toPosition);
        }
    };
}
