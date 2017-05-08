// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

class ValueAnimatorCompatImplHoneycombMr1$2 extends AnimatorListenerAdapter
{
    final /* synthetic */ ValueAnimatorCompatImplHoneycombMr1 this$0;
    final /* synthetic */ ValueAnimatorCompat$Impl$AnimatorListenerProxy val$listener;
    
    ValueAnimatorCompatImplHoneycombMr1$2(final ValueAnimatorCompatImplHoneycombMr1 this$0, final ValueAnimatorCompat$Impl$AnimatorListenerProxy val$listener) {
        this.this$0 = this$0;
        this.val$listener = val$listener;
    }
    
    public void onAnimationCancel(final Animator animator) {
        this.val$listener.onAnimationCancel();
    }
    
    public void onAnimationEnd(final Animator animator) {
        this.val$listener.onAnimationEnd();
    }
    
    public void onAnimationStart(final Animator animator) {
        this.val$listener.onAnimationStart();
    }
}
