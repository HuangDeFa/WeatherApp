package com.kenzz.weatherapp.activity;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.kenzz.weatherapp.R;
import com.kenzz.weatherapp.utils.ViewUtil;

import java.util.ArrayList;
import java.util.List;

public class CitySearchActivity extends BaseActivity {

    private TextView mCityHotLabel;
    private EditText mSearchEditText;
    private ImageView mBackView;
    private RecyclerView mRecyclerView;
    private List<String> mHotCityList;
    private MyItemDecoration mItemDecoration;
    private int mEdgeMargin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        resetRecyclerView(true);
    }

    private void initData() {
        mHotCityList = new ArrayList<>();
        mHotCityList.add("定位");
        mHotCityList.add("北京");
        mHotCityList.add("上海");
        mHotCityList.add("广州");
        mHotCityList.add("深圳");
        mHotCityList.add("珠海");
        mHotCityList.add("佛山");
        mHotCityList.add("南京");
        mHotCityList.add("苏州");
        mHotCityList.add("厦门");
        mHotCityList.add("长沙");
        mHotCityList.add("成都");
        mHotCityList.add("福州");
        mHotCityList.add("杭州");
        mHotCityList.add("武汉");
        mHotCityList.add("青岛");
        mHotCityList.add("西安");
        mHotCityList.add("太原");
        mHotCityList.add("石家庄");
        mHotCityList.add("沈阳");
        mHotCityList.add("重庆");
        mHotCityList.add("天津");
        mHotCityList.add("南宁");

        mEdgeMargin=ViewUtil.dpTopx(this,15);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_city_search;
    }

    @Override
    protected void setFullScreen() {
        ViewUtil.recoverStatusBar(this, 0);
    }

    @Override
    protected void initView() {
        mBackView = ViewUtil.findViewById(this, R.id.city_page_back);
        mSearchEditText = ViewUtil.findViewById(this, R.id.city_search_input);
        mCityHotLabel = ViewUtil.findViewById(this, R.id.city_search_hot_label);
        mRecyclerView = ViewUtil.findViewById(this, R.id.city_search_list);

        mBackView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        mSearchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mCityHotLabel.setVisibility(s.length() > 0 ? View.GONE : View.VISIBLE);
                if(s.length()>0){
                  if(!(mRecyclerView.getAdapter() instanceof SearchlistAdapter)){
                      resetRecyclerView(false);
                  }
                }else {
                    if(!(mRecyclerView.getAdapter() instanceof HotlistAdapter)){
                        resetRecyclerView(true);
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mItemDecoration = new MyItemDecoration();
    }

    private void resetRecyclerView(boolean showHotlistCity) {
        if (showHotlistCity) {
            mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(5,StaggeredGridLayoutManager.VERTICAL));
            mRecyclerView.removeItemDecoration(mItemDecoration);
            mRecyclerView.setAdapter(new HotlistAdapter());
        } else {
            mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
            mRecyclerView.addItemDecoration(mItemDecoration);
            mRecyclerView.setAdapter(new SearchlistAdapter());
        }

        ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) mRecyclerView.getLayoutParams();
        if(lp!=null){
          lp.leftMargin= lp.rightMargin=showHotlistCity?mEdgeMargin:0;
            mRecyclerView.setLayoutParams(lp);
        }
    }

    private class MyItemDecoration extends RecyclerView.ItemDecoration {
        private Paint mPaint;

        public MyItemDecoration() {
            mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
            mPaint.setColor(getResources().getColor(R.color.gray));
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            outRect.set(0, 0, 0, 2);
        }

        @Override
        public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
            super.onDraw(c, parent, state);
            final int left = parent.getPaddingLeft() + ViewUtil.dpTopx(parent.getContext(), 10);
            final int right = parent.getWidth() - parent.getPaddingRight();
            final int count = parent.getChildCount();
            for (int i = 0; i < count; i++) {
                View child = parent.getChildAt(i);
                c.drawRect(left, child.getBottom(), right, child.getBottom() + 2, mPaint);
            }
        }
    }

    private class HotlistVH extends CityActivity.CityViewHolder {
        public HotlistVH(View itemView) {
            super(itemView);
        }
    }

    private class HotlistAdapter extends RecyclerView.Adapter<HotlistVH> {

        @Override
        public HotlistVH onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = getLayoutInflater().inflate(R.layout.city_hotlist_item, parent, false);
            return new HotlistVH(itemView);
        }

        @Override
        public void onBindViewHolder(HotlistVH holder, int position) {
            TextView item = holder.getView(R.id.city_hotlist_name);
            ImageView iv = holder.getView(R.id.city_hotlist_location);
            item.setText(mHotCityList.get(position));
            iv.setVisibility(position == 0 ? View.VISIBLE : View.GONE);
        }

        @Override
        public int getItemCount() {
            return mHotCityList.size();
        }
    }

    private class SearchlistVH extends CityActivity.CityViewHolder {
        public SearchlistVH(View itemView) {
            super(itemView);
        }
    }

    private class SearchlistAdapter extends RecyclerView.Adapter<SearchlistVH> {

        @Override
        public SearchlistVH onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = getLayoutInflater().inflate(R.layout.city_searchlist_item, parent, false);
            return new SearchlistVH(itemView);
        }

        @Override
        public void onBindViewHolder(SearchlistVH holder, int position) {
            TextView item = holder.getView(R.id.city_searchlist_item_citymsg);
            item.setText(mHotCityList.get(position));
        }

        @Override
        public int getItemCount() {
            return mHotCityList.size();
        }
    }
}
