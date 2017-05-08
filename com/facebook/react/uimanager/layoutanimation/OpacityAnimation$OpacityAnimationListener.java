// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.uimanager.layoutanimation;

import android.graphics.Paint;
import android.view.animation.Animation;
import android.view.View;
import android.view.animation.Animation$AnimationListener;

class OpacityAnimation$OpacityAnimationListener implements Animation$AnimationListener
{
    private boolean mLayerTypeChanged;
    private final View mView;
    
    public OpacityAnimation$OpacityAnimationListener(final View mView) {
        this.mLayerTypeChanged = false;
        this.mView = mView;
    }
    
    public void onAnimationEnd(final Animation animation) {
        if (this.mLayerTypeChanged) {
            this.mView.setLayerType(0, (Paint)null);
        }
    }
    
    public void onAnimationRepeat(final Animation animation) {
    }
    
    public void onAnimationStart(final Animation animation) {
        if (this.mView.hasOverlappingRendering() && this.mView.getLayerType() == 0) {
            this.mLayerTypeChanged = true;
            this.mView.setLayerType(2, (Paint)null);
        }
    }
}
