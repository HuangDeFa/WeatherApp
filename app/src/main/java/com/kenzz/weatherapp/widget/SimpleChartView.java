package com.kenzz.weatherapp.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.kenzz.weatherapp.R;
import com.kenzz.weatherapp.utils.ViewUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huangdefa on 21/08/2017.
 * 简单的折线图
 *
 */

public class SimpleChartView extends View {
    private Paint mPaint;
    private Path mPath;
    //画轴线
    private Paint axiosPaint;
    private final int defaultTextSize=30;
    private int mWidth,mHeight,mRealWidth,mRealHeight;
    private Paint.FontMetricsInt mAxiosFM;

    //X轴单位距离
    private float spaceX;
    //Y轴单位距离
    private float spaceY;
    //折线连接点的半径
    private float mRadius;
    //是否显示折线连接点
    private boolean showTopRound=true;
    private int breakLineColor;
    private List<PointF> mPoints;

    public SimpleChartView(Context context) {
        super(context);
        init(context,null,0);
    }

    public SimpleChartView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs,0);
    }

    public SimpleChartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs,defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int wsize=MeasureSpec.getSize(widthMeasureSpec);
        int wmode=MeasureSpec.getMode(widthMeasureSpec);
        int hsize=MeasureSpec.getSize(heightMeasureSpec);
        int hmode=MeasureSpec.getMode(heightMeasureSpec);
        if(wmode==MeasureSpec.AT_MOST || hmode==MeasureSpec.AT_MOST){
          if(hmode==MeasureSpec.AT_MOST){
             hsize= ViewUtil.dpTopx(getContext(),70f);
          }
        }
        setMeasuredDimension(wsize,hsize);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth=w;
        mHeight=h;
    }

    private void init(Context context, AttributeSet attrs, int styleAttr){

        mPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPath=new Path();

        axiosPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        axiosPaint.setStrokeCap(Paint.Cap.ROUND);
        axiosPaint.setStrokeJoin(Paint.Join.ROUND);
        axiosPaint.setColor(getResources().getColor(R.color.gray));
        axiosPaint.setStrokeWidth(2);
        axiosPaint.setTextSize(defaultTextSize);
        axiosPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mAxiosFM=axiosPaint.getFontMetricsInt();
        spaceX=ViewUtil.dpTopx(context,20f);

        mRadius=9;
        breakLineColor=context.getResources().getColor(R.color.colorAccent);
        mPoints=new ArrayList<>();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawAxis(canvas);
        drawBreakLine(canvas);
    }

    /**
     * 绘制折线
     * @param canvas
     */
    private void drawBreakLine(Canvas canvas) {
        mPaint.setColor(breakLineColor);
        if(mPoints.size()>0){
            createPoints();
        }
        mPath.reset();

    }


    private void createPoints() {
        int axisPadding=mAxiosFM.bottom-mAxiosFM.top;
        if(showTopRound){
           mRealHeight= (int) (mHeight-axisPadding-axisPadding-mRadius*2);

        }else {

        }
    }

    /**
     * 绘制坐标轴
     * @param canvas
     */
    private void drawAxis(Canvas canvas){
      int axisPadding=mAxiosFM.bottom-mAxiosFM.top;
        //绘制X轴
      int y=mHeight-axisPadding;
      canvas.drawLine(0,y,mWidth,y,axiosPaint);
      //TODO 绘制X轴下标
      for(int i=0;i<25;i++){
          canvas.drawText(i+"",i*spaceX,mHeight-mAxiosFM.bottom,axiosPaint);
      }
      //绘制Y轴
      int x= (int) (getPaddingLeft()+axiosPaint.getStrokeWidth());
      canvas.drawLine(x,y,x,axisPadding+18,axiosPaint);
    }
}
