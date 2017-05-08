// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.modules.statusbar;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator$AnimatorUpdateListener;

class StatusBarModule$1$1 implements ValueAnimator$AnimatorUpdateListener
{
    final /* synthetic */ StatusBarModule$1 this$1;
    
    StatusBarModule$1$1(final StatusBarModule$1 this$1) {
        this.this$1 = this$1;
    }
    
    public void onAnimationUpdate(final ValueAnimator valueAnimator) {
        this.this$1.val$activity.getWindow().setStatusBarColor((int)valueAnimator.getAnimatedValue());
    }
}
