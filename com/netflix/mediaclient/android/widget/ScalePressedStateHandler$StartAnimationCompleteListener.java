// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.widget;

import android.animation.TimeInterpolator;
import android.animation.Animator$AnimatorListener;
import android.view.animation.AccelerateDecelerateInterpolator;
import com.netflix.mediaclient.Log;
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
        Log.v("PressedStateHandler", "Animation complete");
        if (this.this$0.shouldPerformCompleteAnimation) {
            this.this$0.performResetAnimation(this.view);
            return;
        }
        this.this$0.shouldPerformCompleteAnimation = true;
    }
}
