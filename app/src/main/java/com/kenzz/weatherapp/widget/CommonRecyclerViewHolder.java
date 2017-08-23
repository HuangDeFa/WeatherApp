package com.kenzz.weatherapp.widget;

import android.support.v4.util.SparseArrayCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by huangdefa on 23/08/2017.
 */

public class CommonRecyclerViewHolder extends RecyclerView.ViewHolder {
    private SparseArrayCompat<View> mViews;
    public CommonRecyclerViewHolder(View itemView) {
        super(itemView);
        mViews=new SparseArrayCompat<>();
    }

    public  <T extends View> T getView(int resId){
        T view;
        view= (T) mViews.get(resId);
        if(view==null){
          view=itemView.findViewById(resId);
          mViews.put(resId,view);
        }
        return view;
    }

    public void setTextView(int resId,CharSequence charSequence){
        TextView tv=getView(resId);
        tv.setText(charSequence);
    }
}
