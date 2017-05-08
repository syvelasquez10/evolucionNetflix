// 
// Decompiled by Procyon v0.5.30
// 

package com.BV.LinearGradient;

import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.bridge.ReadableArray;
import android.graphics.Canvas;
import android.graphics.Path$Direction;
import android.graphics.Shader;
import android.graphics.Shader$TileMode;
import android.content.Context;
import android.graphics.RectF;
import android.graphics.LinearGradient;
import android.graphics.Path;
import android.graphics.Paint;
import android.view.View;

public class LinearGradientView extends View
{
    private float[] mBorderRadii;
    private int[] mColors;
    private float[] mEndPos;
    private float[] mLocations;
    private final Paint mPaint;
    private Path mPathForBorderRadius;
    private LinearGradient mShader;
    private int[] mSize;
    private float[] mStartPos;
    private RectF mTempRectForBorderRadius;
    
    public LinearGradientView(final Context context) {
        super(context);
        this.mPaint = new Paint(1);
        this.mStartPos = new float[] { 0.0f, 0.0f };
        this.mEndPos = new float[] { 0.0f, 1.0f };
        this.mSize = new int[] { 0, 0 };
        this.mBorderRadii = new float[] { 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f };
    }
    
    private void drawGradient() {
        if (this.mColors == null || (this.mLocations != null && this.mColors.length != this.mLocations.length)) {
            return;
        }
        this.mShader = new LinearGradient(this.mStartPos[0] * this.mSize[0], this.mStartPos[1] * this.mSize[1], this.mEndPos[0] * this.mSize[0], this.mEndPos[1] * this.mSize[1], this.mColors, this.mLocations, Shader$TileMode.CLAMP);
        this.mPaint.setShader((Shader)this.mShader);
        this.invalidate();
    }
    
    private void updatePath() {
        if (this.mPathForBorderRadius == null) {
            this.mPathForBorderRadius = new Path();
            this.mTempRectForBorderRadius = new RectF();
        }
        this.mPathForBorderRadius.reset();
        this.mTempRectForBorderRadius.set(0.0f, 0.0f, (float)this.mSize[0], (float)this.mSize[1]);
        this.mPathForBorderRadius.addRoundRect(this.mTempRectForBorderRadius, this.mBorderRadii, Path$Direction.CW);
    }
    
    protected void onDraw(final Canvas canvas) {
        super.onDraw(canvas);
        if (this.mPathForBorderRadius == null) {
            canvas.drawPaint(this.mPaint);
            return;
        }
        canvas.drawPath(this.mPathForBorderRadius, this.mPaint);
    }
    
    protected void onSizeChanged(final int n, final int n2, final int n3, final int n4) {
        this.mSize = new int[] { n, n2 };
        this.updatePath();
        this.drawGradient();
    }
    
    public void setBorderRadii(final ReadableArray readableArray) {
        final float[] mBorderRadii = new float[readableArray.size()];
        for (int i = 0; i < mBorderRadii.length; ++i) {
            mBorderRadii[i] = PixelUtil.toPixelFromDIP((float)readableArray.getDouble(i));
        }
        this.mBorderRadii = mBorderRadii;
        this.updatePath();
        this.drawGradient();
    }
    
    public void setColors(final ReadableArray readableArray) {
        final int[] mColors = new int[readableArray.size()];
        for (int i = 0; i < mColors.length; ++i) {
            mColors[i] = readableArray.getInt(i);
        }
        this.mColors = mColors;
        this.drawGradient();
    }
    
    public void setEndPosition(final ReadableArray readableArray) {
        this.mEndPos = new float[] { (float)readableArray.getDouble(0), (float)readableArray.getDouble(1) };
        this.drawGradient();
    }
    
    public void setLocations(final ReadableArray readableArray) {
        final float[] mLocations = new float[readableArray.size()];
        for (int i = 0; i < mLocations.length; ++i) {
            mLocations[i] = (float)readableArray.getDouble(i);
        }
        this.mLocations = mLocations;
        this.drawGradient();
    }
    
    public void setStartPosition(final ReadableArray readableArray) {
        this.mStartPos = new float[] { (float)readableArray.getDouble(0), (float)readableArray.getDouble(1) };
        this.drawGradient();
    }
}
