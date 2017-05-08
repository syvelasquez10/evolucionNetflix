// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

class StateListAnimator$1 extends ValueAnimatorCompat$AnimatorListenerAdapter
{
    final /* synthetic */ StateListAnimator this$0;
    
    StateListAnimator$1(final StateListAnimator this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onAnimationEnd(final ValueAnimatorCompat valueAnimatorCompat) {
        if (this.this$0.mRunningAnimator == valueAnimatorCompat) {
            this.this$0.mRunningAnimator = null;
        }
    }
}
