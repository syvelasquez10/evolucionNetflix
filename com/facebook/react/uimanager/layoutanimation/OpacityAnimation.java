// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.uimanager.layoutanimation;

import android.view.animation.Transformation;
import android.view.animation.Animation$AnimationListener;
import android.view.View;
import android.view.animation.Animation;

class OpacityAnimation extends Animation
{
    private final float mDeltaOpacity;
    private final float mStartOpacity;
    private final View mView;
    
    public OpacityAnimation(final View mView, final float mStartOpacity, final float n) {
        this.mView = mView;
        this.mStartOpacity = mStartOpacity;
        this.mDeltaOpacity = n - mStartOpacity;
        this.setAnimationListener((Animation$AnimationListener)new OpacityAnimation$OpacityAnimationListener(mView));
    }
    
    protected void applyTransformation(final float n, final Transformation transformation) {
        this.mView.setAlpha(this.mStartOpacity + this.mDeltaOpacity * n);
    }
    
    public boolean willChangeBounds() {
        return false;
    }
}
