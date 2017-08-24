package com.kenzz.weatherapp;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.os.Build;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kenzz.weatherapp.activity.BaseActivity;
import com.kenzz.weatherapp.activity.CityActivity;
import com.kenzz.weatherapp.utils.ViewUtil;
import com.kenzz.weatherapp.widget.CommonRecyclerViewHolder;
import com.kenzz.weatherapp.widget.CommonRecyclerviewAdapter;
import com.kenzz.weatherapp.widget.RefreshRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    private ImageView mCityManage;
    private ImageView mMenuView;
    private ViewPager mContentPage;
    private RelativeLayout mBottomLayout;
    private PopupWindow mPopupMenu;

    private RefreshRecyclerView mRecyclerView;
    private List<String> mList;
    {
        mList=new ArrayList<>();
        for(int i=0;i<50;i++){
            mList.add("Item "+i);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void setFullScreen() {
      ViewUtil.recoverStatusBar(this,0);

    }

    @Override
    protected void initView() {
        mCityManage= ViewUtil.findViewById(this,R.id.main_page_citymanage);
        mMenuView=ViewUtil.findViewById(this,R.id.main_page_menu);
        mContentPage=ViewUtil.findViewById(this,R.id.main_page_content);
        mBottomLayout=ViewUtil.findViewById(this,R.id.main_page_bottom);
        final View contentView=getLayoutInflater().inflate(R.layout.main_page_menu,mBottomLayout,false);
        mPopupMenu=new PopupWindow(contentView,-1,-2);
        mPopupMenu.setBackgroundDrawable(new ColorDrawable());
        mPopupMenu.setOutsideTouchable(true);
        mPopupMenu.setFocusable(true);
        mPopupMenu.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction()==MotionEvent.ACTION_OUTSIDE){
                    mPopupMenu.dismiss();
                    return true;
                }
                return false;
            }
        });
        mMenuView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!mPopupMenu.isShowing()){
                    mPopupMenu.showAsDropDown(mBottomLayout,0,10);
                }
            }
        });
        mCityManage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, CityActivity.class));
            }
        });

        mRecyclerView=ViewUtil.findViewById(this,R.id.myRV);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setRefreshListener(new RefreshRecyclerView.RefreshListener() {
            @Override
            public void onRefresh() {
                mRecyclerView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                       mRecyclerView.stopRefresh();
                    }
                },2000);
            }
        });
    }

    private CommonRecyclerviewAdapter mAdapter=new CommonRecyclerviewAdapter<String>(mList) {
        @Override
        public void onBindView(CommonRecyclerViewHolder holder, int position) {
            TextView tv= (TextView) holder.itemView;
            tv.setText(mList.get(position));
        }

        @Override
        public CommonRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView= LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1,parent,false);
            return new CommonRecyclerViewHolder(itemView);
        }
    };

}
