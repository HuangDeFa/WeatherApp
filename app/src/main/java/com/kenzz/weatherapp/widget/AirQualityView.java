package com.kenzz.weatherapp.widget;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by ken.huang on 8/16/2017.
 */

public class AirQualityView extends View {
    private Path mPath;
    private Paint mPaint;
    private Paint mTextPaint;
    private int mBackColor;
    private int mProgressColor;
    private int mTextColor;
    private static final int DEFAULTRADIU = 70;
    private int w, h;

    public AirQualityView(Context context) {
        super(context);
        init();
    }

    public AirQualityView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public AirQualityView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int wsize = MeasureSpec.getSize(widthMeasureSpec);
        int wmode = MeasureSpec.getMode(widthMeasureSpec);
        int hsize = MeasureSpec.getSize(heightMeasureSpec);
        int hmode = MeasureSpec.getMode(heightMeasureSpec);

        if (wmode == MeasureSpec.EXACTLY && hmode == MeasureSpec.EXACTLY) {
            w = Math.max(Math.min(wsize, hsize), 2 * DEFAULTRADIU);
        } else {
            w = Math.max(Math.max(wsize, hsize), 2 * DEFAULTRADIU);
        }
        h = w;
        setMeasuredDimension(w, h);
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPath = new Path();
    }
}
