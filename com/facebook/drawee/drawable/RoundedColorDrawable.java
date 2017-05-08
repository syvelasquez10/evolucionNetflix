// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.drawee.drawable;

import com.facebook.common.internal.Preconditions;
import java.util.Arrays;
import android.graphics.ColorFilter;
import android.graphics.Rect;
import android.graphics.Paint$Style;
import android.graphics.Canvas;
import android.graphics.Path$Direction;
import android.graphics.drawable.ColorDrawable;
import android.graphics.RectF;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.Drawable;

public class RoundedColorDrawable extends Drawable implements Rounded
{
    private int mAlpha;
    private int mBorderColor;
    final Path mBorderPath;
    final float[] mBorderRadii;
    private float mBorderWidth;
    private int mColor;
    private boolean mIsCircle;
    private float mPadding;
    final Paint mPaint;
    final Path mPath;
    private final float[] mRadii;
    private final RectF mTempRect;
    
    public RoundedColorDrawable(final int color) {
        this.mRadii = new float[8];
        this.mBorderRadii = new float[8];
        this.mPaint = new Paint(1);
        this.mIsCircle = false;
        this.mBorderWidth = 0.0f;
        this.mPadding = 0.0f;
        this.mBorderColor = 0;
        this.mPath = new Path();
        this.mBorderPath = new Path();
        this.mColor = 0;
        this.mTempRect = new RectF();
        this.mAlpha = 255;
        this.setColor(color);
    }
    
    public static RoundedColorDrawable fromColorDrawable(final ColorDrawable colorDrawable) {
        return new RoundedColorDrawable(colorDrawable.getColor());
    }
    
    private void updatePath() {
        this.mPath.reset();
        this.mBorderPath.reset();
        this.mTempRect.set(this.getBounds());
        this.mTempRect.inset(this.mBorderWidth / 2.0f, this.mBorderWidth / 2.0f);
        if (this.mIsCircle) {
            this.mBorderPath.addCircle(this.mTempRect.centerX(), this.mTempRect.centerY(), Math.min(this.mTempRect.width(), this.mTempRect.height()) / 2.0f, Path$Direction.CW);
        }
        else {
            for (int i = 0; i < this.mBorderRadii.length; ++i) {
                this.mBorderRadii[i] = this.mRadii[i] + this.mPadding - this.mBorderWidth / 2.0f;
            }
            this.mBorderPath.addRoundRect(this.mTempRect, this.mBorderRadii, Path$Direction.CW);
        }
        this.mTempRect.inset(-this.mBorderWidth / 2.0f, -this.mBorderWidth / 2.0f);
        this.mTempRect.inset(this.mPadding, this.mPadding);
        if (this.mIsCircle) {
            this.mPath.addCircle(this.mTempRect.centerX(), this.mTempRect.centerY(), Math.min(this.mTempRect.width(), this.mTempRect.height()) / 2.0f, Path$Direction.CW);
        }
        else {
            this.mPath.addRoundRect(this.mTempRect, this.mRadii, Path$Direction.CW);
        }
        this.mTempRect.inset(-this.mPadding, -this.mPadding);
    }
    
    public void draw(final Canvas canvas) {
        this.mPaint.setColor(DrawableUtils.multiplyColorAlpha(this.mColor, this.mAlpha));
        this.mPaint.setStyle(Paint$Style.FILL);
        canvas.drawPath(this.mPath, this.mPaint);
        if (this.mBorderWidth != 0.0f) {
            this.mPaint.setColor(DrawableUtils.multiplyColorAlpha(this.mBorderColor, this.mAlpha));
            this.mPaint.setStyle(Paint$Style.STROKE);
            this.mPaint.setStrokeWidth(this.mBorderWidth);
            canvas.drawPath(this.mBorderPath, this.mPaint);
        }
    }
    
    public int getAlpha() {
        return this.mAlpha;
    }
    
    public int getOpacity() {
        return DrawableUtils.getOpacityFromColor(DrawableUtils.multiplyColorAlpha(this.mColor, this.mAlpha));
    }
    
    protected void onBoundsChange(final Rect rect) {
        super.onBoundsChange(rect);
        this.updatePath();
    }
    
    public void setAlpha(final int mAlpha) {
        if (mAlpha != this.mAlpha) {
            this.mAlpha = mAlpha;
            this.invalidateSelf();
        }
    }
    
    public void setBorder(final int mBorderColor, final float mBorderWidth) {
        if (this.mBorderColor != mBorderColor) {
            this.mBorderColor = mBorderColor;
            this.invalidateSelf();
        }
        if (this.mBorderWidth != mBorderWidth) {
            this.mBorderWidth = mBorderWidth;
            this.updatePath();
            this.invalidateSelf();
        }
    }
    
    public void setCircle(final boolean mIsCircle) {
        this.mIsCircle = mIsCircle;
        this.updatePath();
        this.invalidateSelf();
    }
    
    public void setColor(final int mColor) {
        if (this.mColor != mColor) {
            this.mColor = mColor;
            this.invalidateSelf();
        }
    }
    
    public void setColorFilter(final ColorFilter colorFilter) {
    }
    
    public void setPadding(final float mPadding) {
        if (this.mPadding != mPadding) {
            this.mPadding = mPadding;
            this.updatePath();
            this.invalidateSelf();
        }
    }
    
    public void setRadii(final float[] array) {
        if (array == null) {
            Arrays.fill(this.mRadii, 0.0f);
        }
        else {
            Preconditions.checkArgument(array.length == 8, (Object)"radii should have exactly 8 values");
            System.arraycopy(array, 0, this.mRadii, 0, 8);
        }
        this.updatePath();
        this.invalidateSelf();
    }
    
    public void setRadius(final float n) {
        Preconditions.checkArgument(n >= 0.0f, (Object)"radius should be non negative");
        Arrays.fill(this.mRadii, n);
        this.updatePath();
        this.invalidateSelf();
    }
}
