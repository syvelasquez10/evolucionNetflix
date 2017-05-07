// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.widget;

import android.animation.Animator$AnimatorListener;
import android.animation.TimeInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.animation.Animator;
import android.view.View;
import com.netflix.mediaclient.util.OnAnimationEndListener;

class ScalePressedStateHandler$StartAnimationCompleteListener extends OnAnimationEndListener
{
    final /* synthetic */ ScalePressedStateHandler this$0;
    private final View view;
    
    public ScalePressedStateHandler$StartAnimationCompleteListener(final ScalePressedStateHandler this$0, final View view) {
        this.this$0 = this$0;
        this.view = view;
    }
    
    public void onAnimationEnd(final Animator animator) {
        if (this.this$0.shouldPerformCompleteAnimation) {
            this.this$0.performResetAnimation(this.view);
            return;
        }
        this.this$0.shouldPerformCompleteAnimation = true;
    }
}
