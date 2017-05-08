// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.drawee.drawable;

import android.annotation.TargetApi;
import android.graphics.ColorFilter;
import android.graphics.RectF;
import android.graphics.Rect;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable$Callback;
import android.graphics.drawable.Drawable;

public class ForwardingDrawable extends Drawable implements Drawable$Callback, DrawableParent, TransformAwareDrawable, TransformCallback
{
    private static final Matrix sTempTransform;
    private Drawable mCurrentDelegate;
    private final DrawableProperties mDrawableProperties;
    protected TransformCallback mTransformCallback;
    
    static {
        sTempTransform = new Matrix();
    }
    
    public ForwardingDrawable(final Drawable mCurrentDelegate) {
        this.mDrawableProperties = new DrawableProperties();
        DrawableUtils.setCallbacks(this.mCurrentDelegate = mCurrentDelegate, (Drawable$Callback)this, this);
    }
    
    public void draw(final Canvas canvas) {
        this.mCurrentDelegate.draw(canvas);
    }
    
    public Drawable getCurrent() {
        return this.mCurrentDelegate;
    }
    
    public Drawable getDrawable() {
        return this.getCurrent();
    }
    
    public int getIntrinsicHeight() {
        return this.mCurrentDelegate.getIntrinsicHeight();
    }
    
    public int getIntrinsicWidth() {
        return this.mCurrentDelegate.getIntrinsicWidth();
    }
    
    public int getOpacity() {
        return this.mCurrentDelegate.getOpacity();
    }
    
    public boolean getPadding(final Rect rect) {
        return this.mCurrentDelegate.getPadding(rect);
    }
    
    protected void getParentTransform(final Matrix matrix) {
        if (this.mTransformCallback != null) {
            this.mTransformCallback.getTransform(matrix);
            return;
        }
        matrix.reset();
    }
    
    public void getRootBounds(final RectF rectF) {
        if (this.mTransformCallback != null) {
            this.mTransformCallback.getRootBounds(rectF);
            return;
        }
        rectF.set(this.getBounds());
    }
    
    public void getTransform(final Matrix matrix) {
        this.getParentTransform(matrix);
    }
    
    public void invalidateDrawable(final Drawable drawable) {
        this.invalidateSelf();
    }
    
    public boolean isStateful() {
        return this.mCurrentDelegate.isStateful();
    }
    
    public Drawable mutate() {
        this.mCurrentDelegate.mutate();
        return this;
    }
    
    protected void onBoundsChange(final Rect bounds) {
        this.mCurrentDelegate.setBounds(bounds);
    }
    
    protected boolean onLevelChange(final int level) {
        return this.mCurrentDelegate.setLevel(level);
    }
    
    protected boolean onStateChange(final int[] state) {
        return this.mCurrentDelegate.setState(state);
    }
    
    public void scheduleDrawable(final Drawable drawable, final Runnable runnable, final long n) {
        this.scheduleSelf(runnable, n);
    }
    
    public void setAlpha(final int n) {
        this.mDrawableProperties.setAlpha(n);
        this.mCurrentDelegate.setAlpha(n);
    }
    
    public void setColorFilter(final ColorFilter colorFilter) {
        this.mDrawableProperties.setColorFilter(colorFilter);
        this.mCurrentDelegate.setColorFilter(colorFilter);
    }
    
    public Drawable setCurrent(Drawable setCurrentWithoutInvalidate) {
        setCurrentWithoutInvalidate = this.setCurrentWithoutInvalidate(setCurrentWithoutInvalidate);
        this.invalidateSelf();
        return setCurrentWithoutInvalidate;
    }
    
    protected Drawable setCurrentWithoutInvalidate(final Drawable mCurrentDelegate) {
        final Drawable mCurrentDelegate2 = this.mCurrentDelegate;
        DrawableUtils.setCallbacks(mCurrentDelegate2, null, null);
        DrawableUtils.setCallbacks(mCurrentDelegate, null, null);
        DrawableUtils.setDrawableProperties(mCurrentDelegate, this.mDrawableProperties);
        DrawableUtils.copyProperties(mCurrentDelegate, this);
        DrawableUtils.setCallbacks(mCurrentDelegate, (Drawable$Callback)this, this);
        this.mCurrentDelegate = mCurrentDelegate;
        return mCurrentDelegate2;
    }
    
    public void setDither(final boolean b) {
        this.mDrawableProperties.setDither(b);
        this.mCurrentDelegate.setDither(b);
    }
    
    public Drawable setDrawable(final Drawable current) {
        return this.setCurrent(current);
    }
    
    public void setFilterBitmap(final boolean b) {
        this.mDrawableProperties.setFilterBitmap(b);
        this.mCurrentDelegate.setFilterBitmap(b);
    }
    
    @TargetApi(21)
    public void setHotspot(final float n, final float n2) {
        this.mCurrentDelegate.setHotspot(n, n2);
    }
    
    public void setTransformCallback(final TransformCallback mTransformCallback) {
        this.mTransformCallback = mTransformCallback;
    }
    
    public boolean setVisible(final boolean b, final boolean b2) {
        super.setVisible(b, b2);
        return this.mCurrentDelegate.setVisible(b, b2);
    }
    
    public void unscheduleDrawable(final Drawable drawable, final Runnable runnable) {
        this.unscheduleSelf(runnable);
    }
}
