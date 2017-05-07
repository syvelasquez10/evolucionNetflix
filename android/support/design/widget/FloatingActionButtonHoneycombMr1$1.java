// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

import android.animation.Animator$AnimatorListener;
import android.animation.TimeInterpolator;
import android.view.View;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

class FloatingActionButtonHoneycombMr1$1 extends AnimatorListenerAdapter
{
    final /* synthetic */ FloatingActionButtonHoneycombMr1 this$0;
    
    FloatingActionButtonHoneycombMr1$1(final FloatingActionButtonHoneycombMr1 this$0) {
        this.this$0 = this$0;
    }
    
    public void onAnimationCancel(final Animator animator) {
        this.this$0.mIsHiding = false;
    }
    
    public void onAnimationEnd(final Animator animator) {
        this.this$0.mIsHiding = false;
        this.this$0.mView.setVisibility(8);
    }
    
    public void onAnimationStart(final Animator animator) {
        this.this$0.mIsHiding = true;
    }
}
