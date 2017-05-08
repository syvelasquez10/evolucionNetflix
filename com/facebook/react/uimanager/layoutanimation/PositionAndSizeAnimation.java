// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.uimanager.layoutanimation;

import android.view.animation.Transformation;
import android.view.View;
import android.view.animation.Animation;

class PositionAndSizeAnimation extends Animation implements HandleLayout
{
    private final int mDeltaHeight;
    private final int mDeltaWidth;
    private final float mDeltaX;
    private final float mDeltaY;
    private final int mStartHeight;
    private final int mStartWidth;
    private final float mStartX;
    private final float mStartY;
    private final View mView;
    
    public PositionAndSizeAnimation(final View mView, final int n, final int n2, final int n3, final int n4) {
        this.mView = mView;
        this.mStartX = mView.getX();
        this.mStartY = mView.getY();
        this.mStartWidth = mView.getWidth();
        this.mStartHeight = mView.getHeight();
        this.mDeltaX = n - this.mStartX;
        this.mDeltaY = n2 - this.mStartY;
        this.mDeltaWidth = n3 - this.mStartWidth;
        this.mDeltaHeight = n4 - this.mStartHeight;
    }
    
    protected void applyTransformation(final float n, final Transformation transformation) {
        final float n2 = this.mStartX + this.mDeltaX * n;
        final float n3 = this.mStartY + this.mDeltaY * n;
        this.mView.layout(Math.round(n2), Math.round(n3), Math.round(n2 + (this.mStartWidth + this.mDeltaWidth * n)), Math.round(n3 + (this.mStartHeight + this.mDeltaHeight * n)));
    }
    
    public boolean willChangeBounds() {
        return true;
    }
}
