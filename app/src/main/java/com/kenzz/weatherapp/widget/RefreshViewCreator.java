package com.kenzz.weatherapp.widget;

import android.content.Context;
import android.view.View;

/**
 * Created by ken.huang on 8/24/2017.
 */

public abstract class RefreshViewCreator {

    protected  Context mContext;
    public RefreshViewCreator(Context context) {
        mContext=context;
    }

    public abstract void pullToRefresh(int distance, int state);

    public abstract void onRefresh();

    public abstract void stopRefresh();

    public abstract View createRefreshView();
}
