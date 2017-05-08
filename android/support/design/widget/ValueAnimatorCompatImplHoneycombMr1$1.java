// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator$AnimatorUpdateListener;

class ValueAnimatorCompatImplHoneycombMr1$1 implements ValueAnimator$AnimatorUpdateListener
{
    final /* synthetic */ ValueAnimatorCompatImplHoneycombMr1 this$0;
    final /* synthetic */ ValueAnimatorCompat$Impl$AnimatorUpdateListenerProxy val$updateListener;
    
    ValueAnimatorCompatImplHoneycombMr1$1(final ValueAnimatorCompatImplHoneycombMr1 this$0, final ValueAnimatorCompat$Impl$AnimatorUpdateListenerProxy val$updateListener) {
        this.this$0 = this$0;
        this.val$updateListener = val$updateListener;
    }
    
    public void onAnimationUpdate(final ValueAnimator valueAnimator) {
        this.val$updateListener.onAnimationUpdate();
    }
}
