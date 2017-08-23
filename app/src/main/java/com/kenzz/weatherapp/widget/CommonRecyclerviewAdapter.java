package com.kenzz.weatherapp.widget;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by huangdefa on 23/08/2017.
 * 通用Adapter
 */

public abstract class CommonRecyclerviewAdapter<E> extends RecyclerView.Adapter<CommonRecyclerViewHolder> {

    private List<E> mDataList;

    public CommonRecyclerviewAdapter(List<E> dataList) {
        mDataList = dataList;
    }

    @Override
    public CommonRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(CommonRecyclerViewHolder holder, int position) {
        onBindView(holder,position);
    }

    public abstract void onBindView(CommonRecyclerViewHolder holder, int position);

    @Override
    public int getItemCount() {
        return mDataList.size();
    }
}
