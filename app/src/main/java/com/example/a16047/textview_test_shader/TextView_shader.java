package com.example.a16047.textview_test_shader;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by 16047 on 2018/3/5.
 */

public class TextView_shader extends android.support.v7.widget.AppCompatTextView {

    Paint mpaint=null;
    int mViewWidth=0;
    LinearGradient mLinearGradient=null;
    Matrix mGradientMatrix=null;
    int mTranslate=0;
    public TextView_shader(Context context) {
        super(context);
    }

    public TextView_shader(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TextView_shader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(mGradientMatrix!=null){
            mTranslate+=mViewWidth/5;
            if(mTranslate>2*mViewWidth){
                mTranslate=-mViewWidth;
            }
            mGradientMatrix.setTranslate(mTranslate,0);
            mLinearGradient.setLocalMatrix(mGradientMatrix);
            postInvalidateDelayed(100);
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if(mViewWidth==0){
            mViewWidth=getMeasuredWidth();
            if(mViewWidth>0){
                mpaint=getPaint();
                mLinearGradient=new LinearGradient(
                        0,
                        0,
                        mViewWidth,
                        0,
                        new int[]{
                                Color.BLUE,Color.YELLOW,
                                Color.BLUE},
                        null,
                        Shader.TileMode.CLAMP);
                mpaint.setShader(mLinearGradient);
                mGradientMatrix=new Matrix();
            }
        }
    }
}
