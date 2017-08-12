package com.kenzz.weatherapp.activity;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.RippleDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.util.SparseArrayCompat;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.kenzz.weatherapp.R;
import com.kenzz.weatherapp.utils.ViewUtil;

import java.util.Collections;

public class CityActivity extends BaseActivity {

    private RecyclerView mRecyclerView;
    private TextView mTVBack;
    private ImageView mIVEdit;
    private TextView mTVCancel;
    private TextView mTVConfirm;
    private TextView mTVTitle;
    private FloatingActionButton mButton;
    private int statusBarHeight;
    private CityAdapter mCityAdapter;
    private ItemTouchHelper mItemTouchHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        statusBarHeight=ViewUtil.getStatusBarHeight(this);
    }

    @Override
    protected void setFullScreen() {
        ViewUtil.recoverStatusBar(this,0);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_city;
    }

    @Override
    protected void initView() {
        mRecyclerView = ViewUtil.findViewById(this, R.id.city_page_list);
        mIVEdit = ViewUtil.findViewById(this, R.id.city_page_edit);
        mTVBack = ViewUtil.findViewById(this, R.id.city_page_back);
        mTVCancel = ViewUtil.findViewById(this, R.id.city_page_cancel);
        mTVConfirm = ViewUtil.findViewById(this, R.id.city_page_confirm);
        mTVTitle = ViewUtil.findViewById(this, R.id.city_page_title);
        mButton=ViewUtil.findViewById(this,R.id.city_page_fab);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new CityItemDecoration());
        mCityAdapter=new CityAdapter(this);
        mRecyclerView.setAdapter(mCityAdapter);
        mItemTouchHelper=new ItemTouchHelper(new ItemTouchHelper.Callback() {
            @Override
            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                return makeMovementFlags(ItemTouchHelper.DOWN|ItemTouchHelper.UP,0);
            }

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
               final int from=recyclerView.getChildAdapterPosition(viewHolder.itemView);
                final int to=recyclerView.getChildAdapterPosition(target.itemView);
                mCityAdapter.onMove(from,to);
                return true;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

            }

            @Override
            public float getMoveThreshold(RecyclerView.ViewHolder viewHolder) {
                return 0.1f;
            }

            @Override
            public boolean isLongPressDragEnabled() {
                return false;
            }
        });
        mItemTouchHelper.attachToRecyclerView(mRecyclerView);

        mIVEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: change the city List to edit mode
                changePgeMode(true);
                mCityAdapter.updateCityListMode(true);
            }
        });

        mTVBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             finish();
            }
        });
        mTVCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changePgeMode(false);
                mCityAdapter.updateCityListMode(false);
            }
        });
        mTVConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changePgeMode(false);
                mCityAdapter.updateCityListMode(false);
            }
        });
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: Open a view for city Search
            }
        });
    }

    private void changePgeMode(boolean iseditMode) {
        if (iseditMode) {
            mTVBack.setVisibility(View.GONE);
            mIVEdit.setVisibility(View.GONE);
            mTVTitle.setVisibility(View.VISIBLE);
            mTVCancel.setVisibility(View.VISIBLE);
            mTVConfirm.setVisibility(View.VISIBLE);
            mButton.setVisibility(View.GONE);
            viewAnimation(true);
        } else {
            mTVBack.setVisibility(View.VISIBLE);
            mIVEdit.setVisibility(View.VISIBLE);
            mTVTitle.setVisibility(View.GONE);
            mTVCancel.setVisibility(View.GONE);
            mTVConfirm.setVisibility(View.GONE);
            mButton.setVisibility(View.VISIBLE);
            viewAnimation(false);
        }
    }

    private void viewAnimation(boolean in){
        TranslateAnimation animation=new TranslateAnimation(0,0,in?-3*statusBarHeight:0,in?0:-3*statusBarHeight);
        AlphaAnimation alphaAnimation=new AlphaAnimation(in?0:1,in?1:0);
        AnimationSet animationSet=new AnimationSet(true);
        animationSet.setDuration(200);
        animationSet.setInterpolator(new DecelerateInterpolator());
        animationSet.addAnimation(animation);
        animationSet.addAnimation(alphaAnimation);
        mTVTitle.startAnimation(animationSet);
        mTVCancel.startAnimation(animationSet);
        mTVConfirm.startAnimation(animationSet);
        // FloatActionButton must be out when the title in and otherwise out;
        int h=ViewUtil.dpTopx(this,25);
        TranslateAnimation translateAnimation=new TranslateAnimation(0,0,in?0:h,in?h:0);
        translateAnimation.setDuration(200);
        mButton.startAnimation(translateAnimation);
    }

    static class CityItemDecoration extends RecyclerView.ItemDecoration {
        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            if (parent.getChildAdapterPosition(view) == parent.getAdapter().getItemCount() - 1) {
                outRect.set(20, 20, 20, ViewUtil.dpTopx(parent.getContext(), 60));
            } else
                outRect.set(20, 20, 20, 20);
        }
    }

    static class CityViewHolder extends RecyclerView.ViewHolder {
        private SparseArrayCompat<View> mSparseArrayCompat;

        public CityViewHolder(View itemView) {
            super(itemView);
            mSparseArrayCompat = new SparseArrayCompat<>();
        }

        public <T extends View> T getView(int resId) {
            T view;
            view = (T) mSparseArrayCompat.get(resId);
            if (view == null) {
                view = ViewUtil.findViewById(itemView, resId);
                mSparseArrayCompat.put(resId, view);
            }
            return view;
        }
    }

    static class CityAdapter extends RecyclerView.Adapter<CityViewHolder> {
         private boolean mEditMode;
         private int mItems=30;
         private CityActivity mCityActivity;
        public CityAdapter(CityActivity cityActivity) {
            this.mCityActivity=cityActivity;
        }

        @Override
        public CityViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.city_list_item, parent, false);
            return new CityViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(final CityViewHolder holder, final int position) {

            TextView t1 = holder.getView(R.id.city_list_item_name);
            TextView t2 = holder.getView(R.id.city_list_item_region);
            TextView t3 = holder.getView(R.id.city_list_item_air);
            TextView t4 = holder.getView(R.id.city_list_item_temperature);
            TextView t5 = holder.getView(R.id.city_list_item_temperatures);
            ImageView iv1=holder.getView(R.id.city_list_item_delete);
            ImageView iv2=holder.getView(R.id.city_list_item_move);
            ImageView iv3=holder.getView(R.id.city_list_item_weather);
            ImageView iv4=holder.getView(R.id.city_list_item_location);
            View line=holder.getView(R.id.city_list_item_line);
            if(mEditMode){
               line.setVisibility(View.GONE);
                t3.setVisibility(View.GONE);
                t4.setVisibility(View.GONE);
                t5.setVisibility(View.GONE);
                iv1.setVisibility(View.VISIBLE);
                iv2.setVisibility(View.VISIBLE);
                iv3.setVisibility(View.GONE);

                iv1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //TODO: delete this item
                        mItems-=1;
                        notifyItemRemoved(position);
                    }
                });

                iv2.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        mCityActivity.mItemTouchHelper.startDrag(holder);
                        return true;
                    }
                });
            }else {
                line.setVisibility(View.VISIBLE);
                t3.setVisibility(View.VISIBLE);
                t4.setVisibility(View.VISIBLE);
                t5.setVisibility(View.VISIBLE);
                iv1.setVisibility(View.GONE);
                iv2.setVisibility(View.GONE);
                iv3.setVisibility(View.VISIBLE);
            }
            t1.setText("恩平");
            t2.setText("江门，广东");
            t3.setText("天气晴朗|湿度80%|东南风4级");
            t4.setText("32°");
            t5.setText("27℃/34℃");
            iv4.setVisibility(position==0?View.VISIBLE:View.GONE);
        }

        public void updateCityListMode(boolean editMode){
            if(mEditMode==editMode) return;
            mEditMode=editMode;
            notifyDataSetChanged();
        }

        public boolean onMove(int from,int to){
            //TODO: implement the item move logic,often like: Collections.swap(list,from,to)
            notifyItemMoved(from,to);
            return false;
        }

        @Override
        public int getItemCount() {
            return mItems;
        }
    }

}
