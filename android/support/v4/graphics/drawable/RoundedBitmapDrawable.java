// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.graphics.drawable;

import android.util.DisplayMetrics;
import android.graphics.Shader;
import android.graphics.ColorFilter;
import android.graphics.Canvas;
import android.graphics.Shader$TileMode;
import android.content.res.Resources;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Rect;
import android.graphics.BitmapShader;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

public abstract class RoundedBitmapDrawable extends Drawable
{
    private static final int DEFAULT_PAINT_FLAGS = 6;
    private boolean mApplyGravity;
    Bitmap mBitmap;
    private int mBitmapHeight;
    private BitmapShader mBitmapShader;
    private int mBitmapWidth;
    private float mCornerRadius;
    final Rect mDstRect;
    final RectF mDstRectF;
    private int mGravity;
    private Paint mPaint;
    private int mTargetDensity;
    
    RoundedBitmapDrawable(final Resources resources, final Bitmap mBitmap) {
        this.mTargetDensity = 160;
        this.mGravity = 119;
        this.mPaint = new Paint(6);
        this.mDstRect = new Rect();
        this.mDstRectF = new RectF();
        this.mApplyGravity = true;
        if (resources != null) {
            this.mTargetDensity = resources.getDisplayMetrics().densityDpi;
        }
        this.mBitmap = mBitmap;
        if (this.mBitmap != null) {
            this.computeBitmapSize();
            this.mBitmapShader = new BitmapShader(this.mBitmap, Shader$TileMode.CLAMP, Shader$TileMode.CLAMP);
            return;
        }
        this.mBitmapHeight = -1;
        this.mBitmapWidth = -1;
    }
    
    private void computeBitmapSize() {
        this.mBitmapWidth = this.mBitmap.getScaledWidth(this.mTargetDensity);
        this.mBitmapHeight = this.mBitmap.getScaledHeight(this.mTargetDensity);
    }
    
    private static boolean isGreaterThanZero(final float n) {
        return Float.compare(n, 0.0f) > 0;
    }
    
    public void draw(final Canvas canvas) {
        final Bitmap mBitmap = this.mBitmap;
        if (mBitmap == null) {
            return;
        }
        this.updateDstRect();
        final Paint mPaint = this.mPaint;
        if (mPaint.getShader() == null) {
            canvas.drawBitmap(mBitmap, (Rect)null, this.mDstRect, mPaint);
            return;
        }
        canvas.drawRoundRect(this.mDstRectF, this.mCornerRadius, this.mCornerRadius, mPaint);
    }
    
    public int getAlpha() {
        return this.mPaint.getAlpha();
    }
    
    public final Bitmap getBitmap() {
        return this.mBitmap;
    }
    
    public ColorFilter getColorFilter() {
        return this.mPaint.getColorFilter();
    }
    
    public float getCornerRadius() {
        return this.mCornerRadius;
    }
    
    public int getGravity() {
        return this.mGravity;
    }
    
    public int getIntrinsicHeight() {
        return this.mBitmapHeight;
    }
    
    public int getIntrinsicWidth() {
        return this.mBitmapWidth;
    }
    
    public int getOpacity() {
        if (this.mGravity == 119) {
            final Bitmap mBitmap = this.mBitmap;
            if (mBitmap != null && !mBitmap.hasAlpha() && this.mPaint.getAlpha() >= 255 && !isGreaterThanZero(this.mCornerRadius)) {
                return -1;
            }
        }
        return -3;
    }
    
    public final Paint getPaint() {
        return this.mPaint;
    }
    
    void gravityCompatApply(final int n, final int n2, final int n3, final Rect rect, final Rect rect2) {
        throw new UnsupportedOperationException();
    }
    
    public boolean hasAntiAlias() {
        return this.mPaint.isAntiAlias();
    }
    
    public boolean hasMipMap() {
        throw new UnsupportedOperationException();
    }
    
    public void setAlpha(final int alpha) {
        if (alpha != this.mPaint.getAlpha()) {
            this.mPaint.setAlpha(alpha);
            this.invalidateSelf();
        }
    }
    
    public void setAntiAlias(final boolean antiAlias) {
        this.mPaint.setAntiAlias(antiAlias);
        this.invalidateSelf();
    }
    
    public void setColorFilter(final ColorFilter colorFilter) {
        this.mPaint.setColorFilter(colorFilter);
        this.invalidateSelf();
    }
    
    public void setCornerRadius(final float mCornerRadius) {
        if (isGreaterThanZero(mCornerRadius)) {
            this.mPaint.setShader((Shader)this.mBitmapShader);
        }
        else {
            this.mPaint.setShader((Shader)null);
        }
        this.mCornerRadius = mCornerRadius;
    }
    
    public void setDither(final boolean dither) {
        this.mPaint.setDither(dither);
        this.invalidateSelf();
    }
    
    public void setFilterBitmap(final boolean filterBitmap) {
        this.mPaint.setFilterBitmap(filterBitmap);
        this.invalidateSelf();
    }
    
    public void setGravity(final int mGravity) {
        if (this.mGravity != mGravity) {
            this.mGravity = mGravity;
            this.mApplyGravity = true;
            this.invalidateSelf();
        }
    }
    
    public void setMipMap(final boolean b) {
        throw new UnsupportedOperationException();
    }
    
    public void setTargetDensity(final int n) {
        if (this.mTargetDensity != n) {
            int mTargetDensity;
            if ((mTargetDensity = n) == 0) {
                mTargetDensity = 160;
            }
            this.mTargetDensity = mTargetDensity;
            if (this.mBitmap != null) {
                this.computeBitmapSize();
            }
            this.invalidateSelf();
        }
    }
    
    public void setTargetDensity(final Canvas canvas) {
        this.setTargetDensity(canvas.getDensity());
    }
    
    public void setTargetDensity(final DisplayMetrics displayMetrics) {
        this.setTargetDensity(displayMetrics.densityDpi);
    }
    
    void updateDstRect() {
        if (this.mApplyGravity) {
            this.gravityCompatApply(this.mGravity, this.mBitmapWidth, this.mBitmapHeight, this.getBounds(), this.mDstRect);
            this.mDstRectF.set(this.mDstRect);
            this.mApplyGravity = false;
        }
    }
}
