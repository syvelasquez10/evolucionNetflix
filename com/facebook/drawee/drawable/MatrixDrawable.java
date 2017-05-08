// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.drawee.drawable;

import android.graphics.Canvas;
import android.graphics.Rect;
import com.facebook.common.internal.Preconditions;
import android.graphics.drawable.Drawable;
import android.graphics.Matrix;

public class MatrixDrawable extends ForwardingDrawable
{
    private Matrix mDrawMatrix;
    private Matrix mMatrix;
    private int mUnderlyingHeight;
    private int mUnderlyingWidth;
    
    public MatrixDrawable(final Drawable drawable, final Matrix mMatrix) {
        super(Preconditions.checkNotNull(drawable));
        this.mUnderlyingWidth = 0;
        this.mUnderlyingHeight = 0;
        this.mMatrix = mMatrix;
    }
    
    private void configureBounds() {
        final Drawable current = this.getCurrent();
        final Rect bounds = this.getBounds();
        final int intrinsicWidth = current.getIntrinsicWidth();
        this.mUnderlyingWidth = intrinsicWidth;
        final int intrinsicHeight = current.getIntrinsicHeight();
        this.mUnderlyingHeight = intrinsicHeight;
        if (intrinsicWidth <= 0 || intrinsicHeight <= 0) {
            current.setBounds(bounds);
            this.mDrawMatrix = null;
            return;
        }
        current.setBounds(0, 0, intrinsicWidth, intrinsicHeight);
        this.mDrawMatrix = this.mMatrix;
    }
    
    private void configureBoundsIfUnderlyingChanged() {
        if (this.mUnderlyingWidth != this.getCurrent().getIntrinsicWidth() || this.mUnderlyingHeight != this.getCurrent().getIntrinsicHeight()) {
            this.configureBounds();
        }
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
        super.getTransform(matrix);
        if (this.mDrawMatrix != null) {
            matrix.preConcat(this.mDrawMatrix);
        }
    }
    
    @Override
    protected void onBoundsChange(final Rect rect) {
        super.onBoundsChange(rect);
        this.configureBounds();
    }
    
    @Override
    public Drawable setCurrent(Drawable setCurrent) {
        setCurrent = super.setCurrent(setCurrent);
        this.configureBounds();
        return setCurrent;
    }
}
