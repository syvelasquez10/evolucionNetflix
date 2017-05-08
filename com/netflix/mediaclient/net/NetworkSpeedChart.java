// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.net;

import android.graphics.Canvas;
import android.graphics.Paint$Style;
import android.graphics.Color;
import android.graphics.Paint$Align;
import android.util.AttributeSet;
import android.content.Context;
import android.text.TextPaint;
import android.graphics.PointF;
import android.graphics.Paint;
import android.view.View;

public class NetworkSpeedChart extends View
{
    private Paint mAxisPaint;
    private String mAxisXLabel;
    private String mAxisYLabel;
    private final int mBottomOffsetDp;
    private double[] mDataX;
    private double[] mDataY;
    private float mDensityScale;
    private String[] mHorizontalLineLabels;
    private Paint mHorizontalLinePaint;
    private double[] mHorizontalLines;
    private final int mLeftOffsetDp;
    private Paint mLinePaint;
    private double mMaxValueX;
    private double mMaxValueY;
    private double mMinValueX;
    private double mMinValueY;
    private PointF[] mScreenPoints;
    private TextPaint mTextPaint;
    
    public NetworkSpeedChart(final Context context) {
        super(context);
        this.mBottomOffsetDp = 16;
        this.mLeftOffsetDp = 16;
        this.init();
    }
    
    public NetworkSpeedChart(final Context context, final AttributeSet set) {
        super(context, set);
        this.mBottomOffsetDp = 16;
        this.mLeftOffsetDp = 16;
        this.init();
    }
    
    public NetworkSpeedChart(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.mBottomOffsetDp = 16;
        this.mLeftOffsetDp = 16;
        this.init();
    }
    
    private void init() {
        this.mDensityScale = (float)(this.getResources().getDisplayMetrics().densityDpi * 1.0 / 160.0);
        if (this.mDensityScale < 1.0f) {
            this.mDensityScale = 1.0f;
        }
        (this.mTextPaint = new TextPaint()).setAntiAlias(true);
        this.mTextPaint.setTextAlign(Paint$Align.LEFT);
        this.mTextPaint.setTextSize(16.0f * this.mDensityScale);
        this.mTextPaint.setColor(Color.parseColor("#00bfff"));
        (this.mHorizontalLinePaint = new Paint()).setAntiAlias(true);
        this.mHorizontalLinePaint.setStyle(Paint$Style.STROKE);
        this.mHorizontalLinePaint.setStrokeWidth(this.mDensityScale * 1.0f);
        this.mHorizontalLinePaint.setColor(Color.parseColor("#00bfff"));
        (this.mLinePaint = new Paint()).setAntiAlias(true);
        this.mLinePaint.setStyle(Paint$Style.STROKE);
        this.mLinePaint.setStrokeWidth(2.0f * this.mDensityScale);
        this.mLinePaint.setColor(Color.parseColor("#00bfff"));
        (this.mAxisPaint = new Paint()).setAntiAlias(true);
        this.mAxisPaint.setStyle(Paint$Style.STROKE);
        this.mAxisPaint.setStrokeWidth(this.mDensityScale * 1.0f);
        this.mAxisPaint.setColor(-16776961);
    }
    
    public void addHorizontalLinesWithLabel(final double[] mHorizontalLines, final String[] mHorizontalLineLabels) {
        this.mHorizontalLines = mHorizontalLines;
        this.mHorizontalLineLabels = mHorizontalLineLabels;
    }
    
    protected void onDraw(final Canvas canvas) {
        if (this.mDataX != null && this.mDataY != null && this.mDataX.length != 0 && this.mDataY.length != 0) {
            final int n = (int)(16.0f * this.mDensityScale);
            final int n2 = (int)(16.0f * this.mDensityScale);
            final float n3 = this.getHeight();
            final float n4 = this.getWidth();
            final float n5 = n3 - n2;
            final float n6 = n;
            final double mMaxValueX = this.mMaxValueX;
            final double mMinValueX = this.mMinValueX;
            final double mMaxValueY = this.mMaxValueY;
            final double mMinValueY = this.mMinValueY;
            canvas.drawLine((float)n, n5, (float)n, 0.0f, this.mAxisPaint);
            canvas.drawLine((float)n, n5, n4, n5, this.mAxisPaint);
            this.mTextPaint.setTextAlign(Paint$Align.CENTER);
            canvas.drawText(this.mAxisXLabel, n4 - n - 4.0f * this.mDensityScale, n3 - n2 - 4.0f * this.mDensityScale, (Paint)this.mTextPaint);
            this.mTextPaint.setTextAlign(Paint$Align.LEFT);
            canvas.drawText(this.mAxisYLabel, n + 1.0f * this.mDensityScale, n2 + 1.0f * this.mDensityScale, (Paint)this.mTextPaint);
            if (this.mHorizontalLines != null && this.mHorizontalLines.length > 0) {
                for (int i = 0; i < this.mHorizontalLines.length; ++i) {
                    final float n7 = n5 - (float)(this.mHorizontalLines[i] / this.mMaxValueY * n5);
                    canvas.drawLine((float)n, n7, n4, n7, this.mHorizontalLinePaint);
                    canvas.drawText(this.mHorizontalLineLabels[i], n + 1.0f * this.mDensityScale, 16.0f * this.mDensityScale + n7, (Paint)this.mTextPaint);
                }
            }
            if (this.mScreenPoints == null || this.mScreenPoints.length < this.mDataX.length) {
                this.mScreenPoints = new PointF[this.mDataX.length];
                for (int j = 0; j < this.mDataX.length; ++j) {
                    this.mScreenPoints[j] = new PointF();
                }
            }
            for (int k = 0; k < this.mDataX.length; ++k) {
                this.mScreenPoints[k].set((float)((this.mDataX[k] - this.mMinValueX) / (mMaxValueX - mMinValueX) * (n4 - n6) + n), (float)(n5 - (this.mDataY[k] - this.mMinValueY) / (mMaxValueY - mMinValueY) * n5));
                if (k > 0) {
                    canvas.drawLine(this.mScreenPoints[k - 1].x, this.mScreenPoints[k - 1].y, this.mScreenPoints[k].x, this.mScreenPoints[k].y, this.mLinePaint);
                }
            }
        }
    }
    
    public void setMaxValueX(final double mMaxValueX) {
        this.mMaxValueX = mMaxValueX;
    }
    
    public void setMaxValueY(final double mMaxValueY) {
        this.mMaxValueY = mMaxValueY;
    }
    
    public void setMinValueX(final double mMinValueX) {
        this.mMinValueX = mMinValueX;
    }
    
    public void setMinValueY(final double mMinValueY) {
        this.mMinValueY = mMinValueY;
    }
    
    public void setXAxisLabel(final String mAxisXLabel) {
        this.mAxisXLabel = mAxisXLabel;
    }
    
    public void setYAxisLabel(final String mAxisYLabel) {
        this.mAxisYLabel = mAxisYLabel;
    }
    
    public void updateData(final double[] mDataX, final double[] mDataY) {
        this.mDataX = mDataX;
        this.mDataY = mDataY;
    }
}
