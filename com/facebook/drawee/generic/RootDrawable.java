// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.drawee.generic;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import com.facebook.drawee.drawable.VisibilityCallback;
import android.graphics.drawable.Drawable;
import com.facebook.drawee.drawable.VisibilityAwareDrawable;
import com.facebook.drawee.drawable.ForwardingDrawable;

public class RootDrawable extends ForwardingDrawable implements VisibilityAwareDrawable
{
    Drawable mControllerOverlay;
    private VisibilityCallback mVisibilityCallback;
    
    public RootDrawable(final Drawable drawable) {
        super(drawable);
        this.mControllerOverlay = null;
    }
    
    @SuppressLint({ "WrongCall" })
    @Override
    public void draw(final Canvas canvas) {
        if (this.isVisible()) {
            if (this.mVisibilityCallback != null) {
                this.mVisibilityCallback.onDraw();
            }
            super.draw(canvas);
            if (this.mControllerOverlay != null) {
                this.mControllerOverlay.setBounds(this.getBounds());
                this.mControllerOverlay.draw(canvas);
            }
        }
    }
    
    @Override
    public int getIntrinsicHeight() {
        return -1;
    }
    
    @Override
    public int getIntrinsicWidth() {
        return -1;
    }
    
    public void setControllerOverlay(final Drawable mControllerOverlay) {
        this.mControllerOverlay = mControllerOverlay;
        this.invalidateSelf();
    }
    
    @Override
    public void setVisibilityCallback(final VisibilityCallback mVisibilityCallback) {
        this.mVisibilityCallback = mVisibilityCallback;
    }
    
    @Override
    public boolean setVisible(final boolean b, final boolean b2) {
        if (this.mVisibilityCallback != null) {
            this.mVisibilityCallback.onVisibilityChange(b);
        }
        return super.setVisible(b, b2);
    }
}
