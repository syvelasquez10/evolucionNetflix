// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.uimanager.layoutanimation;

import android.view.animation.Animation;
import android.view.animation.Animation$AnimationListener;

class LayoutAnimationController$1 implements Animation$AnimationListener
{
    final /* synthetic */ LayoutAnimationController this$0;
    final /* synthetic */ LayoutAnimationListener val$listener;
    
    LayoutAnimationController$1(final LayoutAnimationController this$0, final LayoutAnimationListener val$listener) {
        this.this$0 = this$0;
        this.val$listener = val$listener;
    }
    
    public void onAnimationEnd(final Animation animation) {
        this.val$listener.onAnimationEnd();
    }
    
    public void onAnimationRepeat(final Animation animation) {
    }
    
    public void onAnimationStart(final Animation animation) {
    }
}
