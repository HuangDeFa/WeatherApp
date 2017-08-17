package com.kenzz.weatherapp.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PathEffect;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.text.TextUtils;
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
    private int DEFAULTRADIU;
    private int w, h;
    //单位 px
    private int mTextSize;
    Paint.FontMetricsInt mFontMetricsInt;

    private float totalValue;
    private float progressValue;
    //360-100；100为预留的空白位置
    private final int totalAngle=260;
    //以90为中间计算：左边起点 90+50=140;
    private final int startAngle=140;
    private int progressAngle;
    //文字
    private String firstText;
    private String secondText;
    private String thirdText;

    public AirQualityView(Context context) {
        super(context);
        init(context,null,0);
    }

    public AirQualityView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs,0);
    }

    public AirQualityView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs,defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int wsize = MeasureSpec.getSize(widthMeasureSpec);
        int hsize = MeasureSpec.getSize(heightMeasureSpec);
        w = Math.max(Math.min(wsize, hsize), 2 * DEFAULTRADIU);
        h = w;
        relativeTextSize();
        setMeasuredDimension(w, h);
    }

    private void init(Context context,AttributeSet attrs,int defStyleAttr) {
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
        DEFAULTRADIU= (int) dpTopx(35f);
        mTextSize=70;

        TypedArray typedArray = context.getResources().obtainAttributes(attrs, R.styleable.AirQualityView);
        progressValue= typedArray.getFloat(R.styleable.AirQualityView_progressValue,0f);
        totalValue= typedArray.getFloat(R.styleable.AirQualityView_totalValue,0f);
        firstText=typedArray.getString(R.styleable.AirQualityView_first_text);
        secondText=typedArray.getString(R.styleable.AirQualityView_second_text);
        thirdText=typedArray.getString(R.styleable.AirQualityView_third_text);

        typedArray.recycle();
        if(progressValue>0 && totalAngle>0) {
            progressAngle = (int) ((progressValue * 1.0f / totalValue) * totalAngle);
        }
    }

    private float dpTopx(float dp){
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dp,getContext().getResources().getDisplayMetrics());
    }

    private void relativeTextSize(){
       int rotation= (w/2)/DEFAULTRADIU;
       mTextSize=70*rotation;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int x= (int) (w-mPaint.getStrokeWidth());
        mPath.reset();
        RectF rectF=new RectF(0,0,x,x);

        mPaint.setColor(mProgressColor);
        mPath.arcTo(rectF,startAngle,progressAngle,false);
        canvas.drawPath(mPath,mPaint);
        mPath.reset();
        mPath.arcTo(rectF,startAngle+progressAngle+2,totalAngle-progressAngle-2,false);
        mPaint.setColor(mBackColor);
        canvas.drawPath(mPath,mPaint);
        if(!TextUtils.isEmpty(firstText)) {
            //绘制第一个文字
            int offset = w / 2 / 6;
            mTextPaint.setTextSize(mTextSize);
            mTextPaint.setColor(mProgressColor);
            mFontMetricsInt = mTextPaint.getFontMetricsInt();
            float l = mTextPaint.measureText(firstText, 0, firstText.length());
            int baseLineY = w / 2 - (mFontMetricsInt.top + mFontMetricsInt.bottom) / 2;
            canvas.drawText(firstText, (w - l) / 2, baseLineY - offset, mTextPaint);

            //绘制第二行
            int nextTop = baseLineY + mFontMetricsInt.bottom;
            mTextPaint.setTextSize(mTextSize / 2);
            mTextPaint.setColor(mBackColor);
            if(!TextUtils.isEmpty(secondText)) {
                l = mTextPaint.measureText(secondText, 0, secondText.length());
                mFontMetricsInt = mTextPaint.getFontMetricsInt();
                canvas.drawText(secondText, (w - l) / 2, nextTop - mFontMetricsInt.top - offset, mTextPaint);
            }
            //绘制最后一行
            String s = "空气质量指数";
            l = mTextPaint.measureText(thirdText, 0, thirdText.length());
            canvas.drawText(thirdText, (x - l) / 2, w - mFontMetricsInt.bottom, mTextPaint);
        }
    }
}
