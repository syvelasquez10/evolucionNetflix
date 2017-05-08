// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util.gfx;

import com.netflix.mediaclient.util.ViewUtils;
import android.animation.Animator;
import android.view.View;
import android.animation.Animator$AnimatorListener;

public class AnimationUtils$AnimateOnHardwareLayer implements Animator$AnimatorListener
{
    private final View[] views;
    
    public AnimationUtils$AnimateOnHardwareLayer(final View... views) {
        this.views = views;
    }
    
    public void onAnimationCancel(final Animator animator) {
        this.onAnimationEnd(animator);
    }
    
    public void onAnimationEnd(final Animator animator) {
        final View[] views = this.views;
        for (int length = views.length, i = 0; i < length; ++i) {
            ViewUtils.setLayerType(views[i], 0);
        }
    }
    
    public void onAnimationRepeat(final Animator animator) {
    }
    
    public void onAnimationStart(final Animator animator) {
        final View[] views = this.views;
        for (int length = views.length, i = 0; i < length; ++i) {
            ViewUtils.setLayerType(views[i], 2);
        }
    }
}
