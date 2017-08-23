package com.kenzz.weatherapp.widget;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by huangdefa on 23/08/2017.
 */

public class WrapperCommonAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private RecyclerView.Adapter mAdapter;
    private SparseArray<View> mHeaderViews;
    private SparseArray<View> mFooterViews;

    // 基本的头部类型开始位置  用于viewType
    private static int BASE_ITEM_TYPE_HEADER = 10000000;
    // 基本的底部类型开始位置  用于viewType
    private static int BASE_ITEM_TYPE_FOOTER = 20000000;

    public WrapperCommonAdapter(RecyclerView.Adapter adapter) {
        mAdapter = adapter;
        mHeaderViews = new SparseArray<>();
        mFooterViews = new SparseArray<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (isHeaderViewType(viewType)) {
            View head = mHeaderViews.get(viewType);
            return createHeadFooterViewHolder(head);
        } else if (isFooterViewType(viewType)) {
            View head = mFooterViews.get(viewType);
            return createHeadFooterViewHolder(head);
        } else {
            return mAdapter.onCreateViewHolder(parent, viewType);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
       if(isHeaderPosition(position) ||isFooterPosition(position)){
           return;
       }
       position=position-mHeaderViews.size();
       mAdapter.onBindViewHolder(holder,position);
    }

    @Override
    public int getItemCount() {
        return mHeaderViews.size() + mAdapter.getItemCount() + mFooterViews.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (isHeaderPosition(position)) {
            return mHeaderViews.keyAt(position);
        } else if (isFooterPosition(position)) {
            position = position - mHeaderViews.size() - mAdapter.getItemCount();
            return mFooterViews.keyAt(position);
        }
        position = position - mHeaderViews.size();
        return mAdapter.getItemViewType(position);
    }

    public void addHeader(View view) {
        int index = mHeaderViews.indexOfValue(view);
        if (index < 0) {
            mHeaderViews.put(BASE_ITEM_TYPE_HEADER++, view);
        }
        notifyDataSetChanged();
    }

    public boolean isHeaderViewType(int viewType) {
        int index = mHeaderViews.indexOfKey(viewType);
        return index > 0;
    }

    public boolean isHeaderPosition(int position) {
        return position < mHeaderViews.size();
    }

    public void removeHeadView(View view){
        int indexOfValue = mHeaderViews.indexOfValue(view);
        if(indexOfValue>0){
            mHeaderViews.removeAt(indexOfValue);
            notifyDataSetChanged();
        }
    }

    private RecyclerView.ViewHolder createHeadFooterViewHolder(View view) {
        return new RecyclerView.ViewHolder(view) {
        };
    }

    public void addFooter(View view) {
        int index = mFooterViews.indexOfValue(view);
        if (index < 0) {
            mFooterViews.put(BASE_ITEM_TYPE_FOOTER++, view);
        }
        notifyDataSetChanged();
    }

    public boolean isFooterViewType(int viewType) {
        int index = mFooterViews.indexOfKey(viewType);
        return index > 0;
    }

    public boolean isFooterPosition(int position) {
        return position > mHeaderViews.size() + mAdapter.getItemCount();
    }

    public void removeFooterView(View view){
        int indexOfValue = mFooterViews.indexOfValue(view);
        if(indexOfValue>0){
           mFooterViews.removeAt(indexOfValue);
            notifyDataSetChanged();
        }
    }

    /**
     * 调整Grid布局头部和尾部占据一行
     * @param recyclerView
     */
    public void adjustSpanSize(RecyclerView recyclerView){
        if(recyclerView.getLayoutManager() instanceof GridLayoutManager){
            final GridLayoutManager manager= (GridLayoutManager) recyclerView.getLayoutManager();
            manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    boolean isHeadOrFoot=isFooterPosition(position) || isHeaderPosition(position);
                    return isHeadOrFoot?manager.getSpanCount():1;
                }
            });
        }
    }
}
