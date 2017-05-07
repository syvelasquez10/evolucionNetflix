// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.common;

import android.animation.Animator$AnimatorListener;
import android.animation.TimeInterpolator;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.View;
import android.animation.ObjectAnimator;

public class CurrentTimeAnimation
{
    private ObjectAnimator animY;
    
    public CurrentTimeAnimation(final View view, final int n, final int n2) {
        (this.animY = ObjectAnimator.ofFloat((Object)view, "translationY", new float[] { n, n2 })).setInterpolator((TimeInterpolator)new AccelerateDecelerateInterpolator());
        this.animY.setDuration(300L);
    }
    
    public void addListener(final Animator$AnimatorListener animator$AnimatorListener) {
        this.animY.addListener(animator$AnimatorListener);
    }
    
    public void cancel() {
        this.animY.cancel();
    }
    
    public boolean isRunning() {
        return this.animY.isRunning();
    }
    
    public void start() {
        this.animY.start();
    }
}
