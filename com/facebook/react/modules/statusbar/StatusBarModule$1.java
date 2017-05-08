// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.modules.statusbar;

import android.annotation.TargetApi;
import android.animation.ValueAnimator$AnimatorUpdateListener;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.animation.ArgbEvaluator;
import android.app.Activity;

class StatusBarModule$1 implements Runnable
{
    final /* synthetic */ StatusBarModule this$0;
    final /* synthetic */ Activity val$activity;
    final /* synthetic */ boolean val$animated;
    final /* synthetic */ int val$color;
    
    StatusBarModule$1(final StatusBarModule this$0, final boolean val$animated, final Activity val$activity, final int val$color) {
        this.this$0 = this$0;
        this.val$animated = val$animated;
        this.val$activity = val$activity;
        this.val$color = val$color;
    }
    
    @TargetApi(21)
    @Override
    public void run() {
        if (this.val$animated) {
            final ValueAnimator ofObject = ValueAnimator.ofObject((TypeEvaluator)new ArgbEvaluator(), new Object[] { this.val$activity.getWindow().getStatusBarColor(), this.val$color });
            ofObject.addUpdateListener((ValueAnimator$AnimatorUpdateListener)new StatusBarModule$1$1(this));
            ofObject.setDuration(300L).setStartDelay(0L);
            ofObject.start();
            return;
        }
        this.val$activity.getWindow().setStatusBarColor(this.val$color);
    }
}
