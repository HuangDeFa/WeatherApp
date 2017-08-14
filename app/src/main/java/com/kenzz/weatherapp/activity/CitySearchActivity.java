package com.kenzz.weatherapp.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.kenzz.weatherapp.R;
import com.kenzz.weatherapp.net.Manager;
import com.kenzz.weatherapp.utils.ViewUtil;

public class CitySearchActivity extends BaseActivity {

    private TextView mCityHotLabel;
    private EditText mSearchEditText;
    private ImageView mBackView;
    private RecyclerView mRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_city_search;
    }

    @Override
    protected void setFullScreen() {
       ViewUtil.recoverStatusBar(this,0);
    }

    @Override
    protected void initView() {
        mBackView= ViewUtil.findViewById(this,R.id.city_page_back);
        mSearchEditText=ViewUtil.findViewById(this,R.id.city_search_input);
        mCityHotLabel=ViewUtil.findViewById(this,R.id.city_search_hot_label);
        mRecyclerView=ViewUtil.findViewById(this,R.id.city_search_list);

        mBackView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // finish();
                Manager.getInstance().getWeather("beijing");
            }
        });
    }
}
