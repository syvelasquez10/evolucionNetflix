// 
// Decompiled by Procyon v0.5.30
// 

package com.makeramen;

import android.graphics.ColorFilter;
import android.graphics.Rect;
import android.graphics.Matrix$ScaleToFit;
import android.util.Log;
import android.graphics.drawable.LayerDrawable;
import android.graphics.Canvas;
import android.graphics.Bitmap$Config;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.Shader;
import android.graphics.Paint$Style;
import android.graphics.Shader$TileMode;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.widget.ImageView$ScaleType;
import android.content.res.ColorStateList;
import android.graphics.BitmapShader;
import android.graphics.RectF;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;

public class RoundedDrawable extends Drawable
{
    public static final int DEFAULT_BORDER_COLOR = -16777216;
    public static final String TAG = "RoundedDrawable";
    private final int mBitmapHeight;
    private final Paint mBitmapPaint;
    private final RectF mBitmapRect;
    private final BitmapShader mBitmapShader;
    private final int mBitmapWidth;
    private ColorStateList mBorderColor;
    private final Paint mBorderPaint;
    private final RectF mBorderRect;
    private float mBorderWidth;
    private final RectF mBounds;
    private float mCornerRadius;
    private final RectF mDrawableRect;
    private boolean mOval;
    private ImageView$ScaleType mScaleType;
    private final Matrix mShaderMatrix;
    
    public RoundedDrawable(final Bitmap bitmap) {
        this.mBounds = new RectF();
        this.mDrawableRect = new RectF();
        this.mBitmapRect = new RectF();
        this.mBorderRect = new RectF();
        this.mShaderMatrix = new Matrix();
        this.mCornerRadius = 0.0f;
        this.mOval = false;
        this.mBorderWidth = 0.0f;
        this.mBorderColor = ColorStateList.valueOf(-16777216);
        this.mScaleType = ImageView$ScaleType.FIT_CENTER;
        this.mBitmapWidth = bitmap.getWidth();
        this.mBitmapHeight = bitmap.getHeight();
        this.mBitmapRect.set(0.0f, 0.0f, (float)this.mBitmapWidth, (float)this.mBitmapHeight);
        (this.mBitmapShader = new BitmapShader(bitmap, Shader$TileMode.CLAMP, Shader$TileMode.CLAMP)).setLocalMatrix(this.mShaderMatrix);
        (this.mBitmapPaint = new Paint()).setStyle(Paint$Style.FILL);
        this.mBitmapPaint.setAntiAlias(true);
        this.mBitmapPaint.setShader((Shader)this.mBitmapShader);
        (this.mBorderPaint = new Paint()).setStyle(Paint$Style.STROKE);
        this.mBorderPaint.setAntiAlias(true);
        this.mBorderPaint.setColor(this.mBorderColor.getColorForState(this.getState(), -16777216));
        this.mBorderPaint.setStrokeWidth(this.mBorderWidth);
    }
    
    public static Bitmap drawableToBitmap(final Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable)drawable).getBitmap();
        }
        final int max = Math.max(drawable.getIntrinsicWidth(), 1);
        final int max2 = Math.max(drawable.getIntrinsicHeight(), 1);
        try {
            final Bitmap bitmap = Bitmap.createBitmap(max, max2, Bitmap$Config.ARGB_8888);
            final Canvas canvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
            return bitmap;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public static RoundedDrawable fromBitmap(final Bitmap bitmap) {
        if (bitmap != null) {
            return new RoundedDrawable(bitmap);
        }
        return null;
    }
    
    public static Drawable fromDrawable(final Drawable drawable) {
        Drawable drawable2 = drawable;
        if (drawable != null) {
            if (drawable instanceof RoundedDrawable) {
                drawable2 = drawable;
            }
            else if (drawable instanceof LayerDrawable) {
                final LayerDrawable layerDrawable = (LayerDrawable)drawable;
                final int numberOfLayers = layerDrawable.getNumberOfLayers();
                int n = 0;
                while (true) {
                    drawable2 = (Drawable)layerDrawable;
                    if (n >= numberOfLayers) {
                        break;
                    }
                    layerDrawable.setDrawableByLayerId(layerDrawable.getId(n), fromDrawable(layerDrawable.getDrawable(n)));
                    ++n;
                }
            }
            else {
                final Bitmap drawableToBitmap = drawableToBitmap(drawable);
                if (drawableToBitmap != null) {
                    return new RoundedDrawable(drawableToBitmap);
                }
                Log.w("RoundedDrawable", "Failed to create bitmap from drawable!");
                return drawable;
            }
        }
        return drawable2;
    }
    
    private void updateShaderMatrix() {
        float n = 0.0f;
        switch (RoundedDrawable$1.$SwitchMap$android$widget$ImageView$ScaleType[this.mScaleType.ordinal()]) {
            default: {
                this.mBorderRect.set(this.mBitmapRect);
                this.mShaderMatrix.setRectToRect(this.mBitmapRect, this.mBounds, Matrix$ScaleToFit.CENTER);
                this.mShaderMatrix.mapRect(this.mBorderRect);
                this.mBorderRect.inset(this.mBorderWidth / 2.0f, this.mBorderWidth / 2.0f);
                this.mShaderMatrix.setRectToRect(this.mBitmapRect, this.mBorderRect, Matrix$ScaleToFit.FILL);
                break;
            }
            case 1: {
                this.mBorderRect.set(this.mBounds);
                this.mBorderRect.inset(this.mBorderWidth / 2.0f, this.mBorderWidth / 2.0f);
                this.mShaderMatrix.set((Matrix)null);
                this.mShaderMatrix.setTranslate((float)(int)((this.mBorderRect.width() - this.mBitmapWidth) * 0.5f + 0.5f), (float)(int)((this.mBorderRect.height() - this.mBitmapHeight) * 0.5f + 0.5f));
                break;
            }
            case 2: {
                this.mBorderRect.set(this.mBounds);
                this.mBorderRect.inset(this.mBorderWidth / 2.0f, this.mBorderWidth / 2.0f);
                this.mShaderMatrix.set((Matrix)null);
                float n2;
                float n3;
                if (this.mBitmapWidth * this.mBorderRect.height() > this.mBorderRect.width() * this.mBitmapHeight) {
                    n2 = this.mBorderRect.height() / this.mBitmapHeight;
                    n3 = (this.mBorderRect.width() - this.mBitmapWidth * n2) * 0.5f;
                }
                else {
                    n2 = this.mBorderRect.width() / this.mBitmapWidth;
                    final float height = this.mBorderRect.height();
                    final float n4 = this.mBitmapHeight;
                    n3 = 0.0f;
                    n = (height - n4 * n2) * 0.5f;
                }
                this.mShaderMatrix.setScale(n2, n2);
                this.mShaderMatrix.postTranslate((int)(n3 + 0.5f) + this.mBorderWidth, (int)(n + 0.5f) + this.mBorderWidth);
                break;
            }
            case 3: {
                this.mShaderMatrix.set((Matrix)null);
                float min;
                if (this.mBitmapWidth <= this.mBounds.width() && this.mBitmapHeight <= this.mBounds.height()) {
                    min = 1.0f;
                }
                else {
                    min = Math.min(this.mBounds.width() / this.mBitmapWidth, this.mBounds.height() / this.mBitmapHeight);
                }
                final float n5 = (int)((this.mBounds.width() - this.mBitmapWidth * min) * 0.5f + 0.5f);
                final float n6 = (int)((this.mBounds.height() - this.mBitmapHeight * min) * 0.5f + 0.5f);
                this.mShaderMatrix.setScale(min, min);
                this.mShaderMatrix.postTranslate(n5, n6);
                this.mBorderRect.set(this.mBitmapRect);
                this.mShaderMatrix.mapRect(this.mBorderRect);
                this.mBorderRect.inset(this.mBorderWidth / 2.0f, this.mBorderWidth / 2.0f);
                this.mShaderMatrix.setRectToRect(this.mBitmapRect, this.mBorderRect, Matrix$ScaleToFit.FILL);
                break;
            }
            case 5: {
                this.mBorderRect.set(this.mBitmapRect);
                this.mShaderMatrix.setRectToRect(this.mBitmapRect, this.mBounds, Matrix$ScaleToFit.END);
                this.mShaderMatrix.mapRect(this.mBorderRect);
                this.mBorderRect.inset(this.mBorderWidth / 2.0f, this.mBorderWidth / 2.0f);
                this.mShaderMatrix.setRectToRect(this.mBitmapRect, this.mBorderRect, Matrix$ScaleToFit.FILL);
                break;
            }
            case 6: {
                this.mBorderRect.set(this.mBitmapRect);
                this.mShaderMatrix.setRectToRect(this.mBitmapRect, this.mBounds, Matrix$ScaleToFit.START);
                this.mShaderMatrix.mapRect(this.mBorderRect);
                this.mBorderRect.inset(this.mBorderWidth / 2.0f, this.mBorderWidth / 2.0f);
                this.mShaderMatrix.setRectToRect(this.mBitmapRect, this.mBorderRect, Matrix$ScaleToFit.FILL);
                break;
            }
            case 7: {
                this.mBorderRect.set(this.mBounds);
                this.mBorderRect.inset(this.mBorderWidth / 2.0f, this.mBorderWidth / 2.0f);
                this.mShaderMatrix.set((Matrix)null);
                this.mShaderMatrix.setRectToRect(this.mBitmapRect, this.mBorderRect, Matrix$ScaleToFit.FILL);
                break;
            }
        }
        this.mDrawableRect.set(this.mBorderRect);
        this.mBitmapShader.setLocalMatrix(this.mShaderMatrix);
    }
    
    public void draw(final Canvas canvas) {
        if (this.mOval) {
            if (this.mBorderWidth > 0.0f) {
                canvas.drawOval(this.mDrawableRect, this.mBitmapPaint);
                canvas.drawOval(this.mBorderRect, this.mBorderPaint);
                return;
            }
            canvas.drawOval(this.mDrawableRect, this.mBitmapPaint);
        }
        else {
            if (this.mBorderWidth > 0.0f) {
                canvas.drawRoundRect(this.mDrawableRect, Math.max(this.mCornerRadius, 0.0f), Math.max(this.mCornerRadius, 0.0f), this.mBitmapPaint);
                canvas.drawRoundRect(this.mBorderRect, this.mCornerRadius, this.mCornerRadius, this.mBorderPaint);
                return;
            }
            canvas.drawRoundRect(this.mDrawableRect, this.mCornerRadius, this.mCornerRadius, this.mBitmapPaint);
        }
    }
    
    public int getBorderColor() {
        return this.mBorderColor.getDefaultColor();
    }
    
    public ColorStateList getBorderColors() {
        return this.mBorderColor;
    }
    
    public float getBorderWidth() {
        return this.mBorderWidth;
    }
    
    public float getCornerRadius() {
        return this.mCornerRadius;
    }
    
    public int getIntrinsicHeight() {
        return this.mBitmapHeight;
    }
    
    public int getIntrinsicWidth() {
        return this.mBitmapWidth;
    }
    
    public int getOpacity() {
        return -3;
    }
    
    public ImageView$ScaleType getScaleType() {
        return this.mScaleType;
    }
    
    public boolean isOval() {
        return this.mOval;
    }
    
    public boolean isStateful() {
        return this.mBorderColor.isStateful();
    }
    
    protected void onBoundsChange(final Rect rect) {
        super.onBoundsChange(rect);
        this.mBounds.set(rect);
        this.updateShaderMatrix();
    }
    
    protected boolean onStateChange(final int[] array) {
        final int colorForState = this.mBorderColor.getColorForState(array, 0);
        if (this.mBorderPaint.getColor() != colorForState) {
            this.mBorderPaint.setColor(colorForState);
            return true;
        }
        return super.onStateChange(array);
    }
    
    public void setAlpha(final int alpha) {
        this.mBitmapPaint.setAlpha(alpha);
        this.invalidateSelf();
    }
    
    public RoundedDrawable setBorderColor(final int n) {
        return this.setBorderColors(ColorStateList.valueOf(n));
    }
    
    public RoundedDrawable setBorderColors(ColorStateList value) {
        if (value == null) {
            value = ColorStateList.valueOf(0);
        }
        this.mBorderColor = value;
        this.mBorderPaint.setColor(this.mBorderColor.getColorForState(this.getState(), -16777216));
        return this;
    }
    
    public RoundedDrawable setBorderWidth(final int n) {
        this.mBorderWidth = n;
        this.mBorderPaint.setStrokeWidth(this.mBorderWidth);
        return this;
    }
    
    public void setColorFilter(final ColorFilter colorFilter) {
        this.mBitmapPaint.setColorFilter(colorFilter);
        this.invalidateSelf();
    }
    
    public RoundedDrawable setCornerRadius(final float mCornerRadius) {
        this.mCornerRadius = mCornerRadius;
        return this;
    }
    
    public void setDither(final boolean dither) {
        this.mBitmapPaint.setDither(dither);
        this.invalidateSelf();
    }
    
    public void setFilterBitmap(final boolean filterBitmap) {
        this.mBitmapPaint.setFilterBitmap(filterBitmap);
        this.invalidateSelf();
    }
    
    public RoundedDrawable setOval(final boolean mOval) {
        this.mOval = mOval;
        return this;
    }
    
    public RoundedDrawable setScaleType(final ImageView$ScaleType imageView$ScaleType) {
        ImageView$ScaleType fit_CENTER = imageView$ScaleType;
        if (imageView$ScaleType == null) {
            fit_CENTER = ImageView$ScaleType.FIT_CENTER;
        }
        if (this.mScaleType != fit_CENTER) {
            this.mScaleType = fit_CENTER;
            this.updateShaderMatrix();
        }
        return this;
    }
    
    public Bitmap toBitmap() {
        return drawableToBitmap(this);
    }
}
