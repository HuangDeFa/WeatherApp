package com.kenzz.weatherapp.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PathEffect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.kenzz.weatherapp.R;

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
    Paint.FontMetricsInt mFontMetricsInt;

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
        int hsize = MeasureSpec.getSize(heightMeasureSpec);
        w = Math.max(Math.min(wsize, hsize), 2 * DEFAULTRADIU);
        h = w;
        setMeasuredDimension(w, h);
    }

    private void init() {
        mBackColor=getContext().getResources().getColor(R.color.gray);
        mProgressColor=Color.GREEN;
        mTextColor=Color.GREEN;

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(mBackColor);
        mPaint.setStrokeWidth(5);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);

        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mTextPaint.setColor(mTextColor);
        mTextPaint.setTextSize(80);
        mTextPaint.setTypeface(Typeface.MONOSPACE);
        mTextPaint.setStrokeWidth(1);
        mPath = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int x= (int) (w-mPaint.getStrokeWidth());
        mPath.reset();
        RectF rectF=new RectF(0,0,x,x);

        mPaint.setColor(mProgressColor);
        mPath.arcTo(rectF,130,30,false);
        canvas.drawPath(mPath,mPaint);
        mPath.reset();
        mPath.arcTo(rectF,162,242,false);
        mPaint.setColor(mBackColor);
        canvas.drawPath(mPath,mPaint);

       mFontMetricsInt= mTextPaint.getFontMetricsInt();
        float l=mTextPaint.measureText("69",0,2);
        canvas.drawText("69",(w-l)/2,mFontMetricsInt.ascent+ w/2-mFontMetricsInt.descent,mTextPaint);

        //canvas.drawRect(rectF,mTextPaint);
    }
}
