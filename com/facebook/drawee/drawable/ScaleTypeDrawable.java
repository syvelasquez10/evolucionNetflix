// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.drawee.drawable;

import android.graphics.Canvas;
import android.graphics.Rect;
import com.facebook.common.internal.Preconditions;
import android.graphics.drawable.Drawable;
import android.graphics.PointF;
import android.graphics.Matrix;

public class ScaleTypeDrawable extends ForwardingDrawable
{
    Matrix mDrawMatrix;
    PointF mFocusPoint;
    ScalingUtils$ScaleType mScaleType;
    Object mScaleTypeState;
    private Matrix mTempMatrix;
    int mUnderlyingHeight;
    int mUnderlyingWidth;
    
    public ScaleTypeDrawable(final Drawable drawable, final ScalingUtils$ScaleType mScaleType) {
        super(Preconditions.checkNotNull(drawable));
        this.mFocusPoint = null;
        this.mUnderlyingWidth = 0;
        this.mUnderlyingHeight = 0;
        this.mTempMatrix = new Matrix();
        this.mScaleType = mScaleType;
    }
    
    private void configureBoundsIfUnderlyingChanged() {
        boolean b = false;
        boolean b2;
        if (this.mScaleType instanceof ScalingUtils$StatefulScaleType) {
            final Object state = ((ScalingUtils$StatefulScaleType)this.mScaleType).getState();
            if (state == null || !state.equals(this.mScaleTypeState)) {
                b2 = true;
            }
            else {
                b2 = false;
            }
            this.mScaleTypeState = state;
        }
        else {
            b2 = false;
        }
        if (this.mUnderlyingWidth != this.getCurrent().getIntrinsicWidth() || this.mUnderlyingHeight != this.getCurrent().getIntrinsicHeight()) {
            b = true;
        }
        if (b || b2) {
            this.configureBounds();
        }
    }
    
    void configureBounds() {
        float y = 0.5f;
        final Drawable current = this.getCurrent();
        final Rect bounds = this.getBounds();
        final int width = bounds.width();
        final int height = bounds.height();
        final int intrinsicWidth = current.getIntrinsicWidth();
        this.mUnderlyingWidth = intrinsicWidth;
        final int intrinsicHeight = current.getIntrinsicHeight();
        this.mUnderlyingHeight = intrinsicHeight;
        if (intrinsicWidth <= 0 || intrinsicHeight <= 0) {
            current.setBounds(bounds);
            this.mDrawMatrix = null;
            return;
        }
        if (intrinsicWidth == width && intrinsicHeight == height) {
            current.setBounds(bounds);
            this.mDrawMatrix = null;
            return;
        }
        if (this.mScaleType == ScalingUtils$ScaleType.FIT_XY) {
            current.setBounds(bounds);
            this.mDrawMatrix = null;
            return;
        }
        current.setBounds(0, 0, intrinsicWidth, intrinsicHeight);
        final ScalingUtils$ScaleType mScaleType = this.mScaleType;
        final Matrix mTempMatrix = this.mTempMatrix;
        float x;
        if (this.mFocusPoint != null) {
            x = this.mFocusPoint.x;
        }
        else {
            x = 0.5f;
        }
        if (this.mFocusPoint != null) {
            y = this.mFocusPoint.y;
        }
        mScaleType.getTransform(mTempMatrix, bounds, intrinsicWidth, intrinsicHeight, x, y);
        this.mDrawMatrix = this.mTempMatrix;
    }
    
    @Override
    public void draw(final Canvas canvas) {
        this.configureBoundsIfUnderlyingChanged();
        if (this.mDrawMatrix != null) {
            final int save = canvas.save();
            canvas.clipRect(this.getBounds());
            canvas.concat(this.mDrawMatrix);
            super.draw(canvas);
            canvas.restoreToCount(save);
            return;
        }
        super.draw(canvas);
    }
    
    @Override
    public void getTransform(final Matrix matrix) {
        this.getParentTransform(matrix);
        this.configureBoundsIfUnderlyingChanged();
        if (this.mDrawMatrix != null) {
            matrix.preConcat(this.mDrawMatrix);
        }
    }
    
    @Override
    protected void onBoundsChange(final Rect rect) {
        this.configureBounds();
    }
    
    @Override
    public Drawable setCurrent(Drawable setCurrent) {
        setCurrent = super.setCurrent(setCurrent);
        this.configureBounds();
        return setCurrent;
    }
    
    public void setFocusPoint(final PointF pointF) {
        if (this.mFocusPoint == null) {
            this.mFocusPoint = new PointF();
        }
        this.mFocusPoint.set(pointF);
        this.configureBounds();
        this.invalidateSelf();
    }
    
    public void setScaleType(final ScalingUtils$ScaleType mScaleType) {
        this.mScaleType = mScaleType;
        this.mScaleTypeState = null;
        this.configureBounds();
        this.invalidateSelf();
    }
}
