package com.kenzz.weatherapp.activity;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.util.SparseArrayCompat;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kenzz.weatherapp.R;
import com.kenzz.weatherapp.utils.ViewUtil;

public class CityActivity extends BaseActivity {

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_city;
    }

    @Override
    protected void initView() {
        mRecyclerView = ViewUtil.findViewById(this, R.id.city_page_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new CityItemDecoration());
        mRecyclerView.setAdapter(new CityAdapter());
    }

    static class CityItemDecoration extends RecyclerView.ItemDecoration {
        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            if(parent.getChildAdapterPosition(view)==parent.getAdapter().getItemCount()-1) {
                outRect.set(20, 20, 20, ViewUtil.dpTopx(parent.getContext(),60));
            }else
            outRect.set(20, 20, 20, 20);
        }
    }

    static class CityViewHolder extends RecyclerView.ViewHolder{
        private SparseArrayCompat<View> mSparseArrayCompat;
        public CityViewHolder(View itemView) {
            super(itemView);
            mSparseArrayCompat=new SparseArrayCompat<>();
        }

        public <T extends View> T getView(int resId){
            T view;
            view= (T) mSparseArrayCompat.get(resId);
            if(view==null){
                view=ViewUtil.findViewById(itemView,resId);
                mSparseArrayCompat.put(resId,view);
            }
            return view;
        }
    }

    static class CityAdapter extends RecyclerView.Adapter<CityViewHolder>{

        @Override
        public CityViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
           View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.city_list_item,parent,false);
            return new CityViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(CityViewHolder holder, int position) {
          TextView t1= holder.getView(R.id.city_list_item_name);
          TextView t2= holder.getView(R.id.city_list_item_region);
          TextView t3= holder.getView(R.id.city_list_item_air);
          TextView t4= holder.getView(R.id.city_list_item_temperature);
          TextView t5= holder.getView(R.id.city_list_item_temperatures);

          t1.setText("恩平");
            t2.setText("江门，广东");
            t3.setText("天气晴朗|湿度80%|东南风4级");
            t4.setText("32°");
            t5.setText("27℃/34℃");
        }

        @Override
        public int getItemCount() {
            return 30;
        }
    }

}
