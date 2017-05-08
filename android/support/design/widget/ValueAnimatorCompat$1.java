// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

class ValueAnimatorCompat$1 implements ValueAnimatorCompat$Impl$AnimatorUpdateListenerProxy
{
    final /* synthetic */ ValueAnimatorCompat this$0;
    final /* synthetic */ ValueAnimatorCompat$AnimatorUpdateListener val$updateListener;
    
    ValueAnimatorCompat$1(final ValueAnimatorCompat this$0, final ValueAnimatorCompat$AnimatorUpdateListener val$updateListener) {
        this.this$0 = this$0;
        this.val$updateListener = val$updateListener;
    }
    
    @Override
    public void onAnimationUpdate() {
        this.val$updateListener.onAnimationUpdate(this.this$0);
    }
}
