// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.drawee.drawable;

import android.graphics.ColorFilter;
import android.graphics.Rect;
import android.graphics.Canvas;
import android.content.res.Resources;
import android.graphics.Paint;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

public class LightBitmapDrawable extends Drawable
{
    private Bitmap mBitmap;
    private int mBitmapHeight;
    private int mBitmapWidth;
    private final Paint mPaint;
    private int mTargetDensity;
    
    public LightBitmapDrawable(final Resources resources, final Bitmap bitmap) {
        this(resources, bitmap, null);
    }
    
    public LightBitmapDrawable(final Resources resources, final Bitmap mBitmap, final Paint paint) {
        this.mBitmap = null;
        this.mTargetDensity = 160;
        this.mPaint = new Paint(6);
        if (paint != null) {
            this.mPaint.set(paint);
        }
        this.mBitmap = mBitmap;
        this.mTargetDensity = resources.getDisplayMetrics().densityDpi;
        this.computeBitmapSize();
    }
    
    private void computeBitmapSize() {
        if (this.mBitmap != null) {
            this.mBitmapWidth = this.mBitmap.getScaledWidth(this.mTargetDensity);
            this.mBitmapHeight = this.mBitmap.getScaledHeight(this.mTargetDensity);
            return;
        }
        this.mBitmapHeight = -1;
        this.mBitmapWidth = -1;
    }
    
    public void draw(final Canvas canvas) {
        if (this.mBitmap == null) {
            return;
        }
        canvas.drawBitmap(this.mBitmap, (Rect)null, this.getBounds(), this.mPaint);
    }
    
    public int getAlpha() {
        return this.mPaint.getAlpha();
    }
    
    public Bitmap getBitmap() {
        return this.mBitmap;
    }
    
    public ColorFilter getColorFilter() {
        return this.mPaint.getColorFilter();
    }
    
    public int getIntrinsicHeight() {
        return this.mBitmapHeight;
    }
    
    public int getIntrinsicWidth() {
        return this.mBitmapWidth;
    }
    
    public int getOpacity() {
        if (this.mBitmap == null || this.mBitmap.hasAlpha() || this.mPaint.getAlpha() < 255) {
            return -3;
        }
        return -1;
    }
    
    public Paint getPaint() {
        return this.mPaint;
    }
    
    public void setAlpha(final int alpha) {
        if (alpha != this.mPaint.getAlpha()) {
            this.mPaint.setAlpha(alpha);
            this.invalidateSelf();
        }
    }
    
    public void setBitmap(final Bitmap mBitmap) {
        if (this.mBitmap != mBitmap) {
            this.mBitmap = mBitmap;
            this.computeBitmapSize();
            this.invalidateSelf();
        }
    }
    
    public void setColorFilter(final ColorFilter colorFilter) {
        this.mPaint.setColorFilter(colorFilter);
        this.invalidateSelf();
    }
    
    public void setDither(final boolean dither) {
        this.mPaint.setDither(dither);
        this.invalidateSelf();
    }
    
    public void setFilterBitmap(final boolean filterBitmap) {
        this.mPaint.setFilterBitmap(filterBitmap);
        this.invalidateSelf();
    }
}
