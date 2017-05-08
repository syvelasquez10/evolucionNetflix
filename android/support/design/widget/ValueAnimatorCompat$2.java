// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

class ValueAnimatorCompat$2 implements ValueAnimatorCompat$Impl$AnimatorListenerProxy
{
    final /* synthetic */ ValueAnimatorCompat this$0;
    final /* synthetic */ ValueAnimatorCompat$AnimatorListener val$listener;
    
    ValueAnimatorCompat$2(final ValueAnimatorCompat this$0, final ValueAnimatorCompat$AnimatorListener val$listener) {
        this.this$0 = this$0;
        this.val$listener = val$listener;
    }
    
    @Override
    public void onAnimationCancel() {
        this.val$listener.onAnimationCancel(this.this$0);
    }
    
    @Override
    public void onAnimationEnd() {
        this.val$listener.onAnimationEnd(this.this$0);
    }
    
    @Override
    public void onAnimationStart() {
        this.val$listener.onAnimationStart(this.this$0);
    }
}
