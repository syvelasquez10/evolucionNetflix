// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

abstract class FloatingActionButtonGingerbread$ShadowAnimatorImpl extends ValueAnimatorCompat$AnimatorListenerAdapter implements ValueAnimatorCompat$AnimatorUpdateListener
{
    private float mShadowSizeEnd;
    private float mShadowSizeStart;
    private boolean mValidValues;
    final /* synthetic */ FloatingActionButtonGingerbread this$0;
    
    private FloatingActionButtonGingerbread$ShadowAnimatorImpl(final FloatingActionButtonGingerbread this$0) {
        this.this$0 = this$0;
    }
    
    protected abstract float getTargetShadowSize();
    
    @Override
    public void onAnimationEnd(final ValueAnimatorCompat valueAnimatorCompat) {
        this.this$0.mShadowDrawable.setShadowSize(this.mShadowSizeEnd);
        this.mValidValues = false;
    }
    
    @Override
    public void onAnimationUpdate(final ValueAnimatorCompat valueAnimatorCompat) {
        if (!this.mValidValues) {
            this.mShadowSizeStart = this.this$0.mShadowDrawable.getShadowSize();
            this.mShadowSizeEnd = this.getTargetShadowSize();
            this.mValidValues = true;
        }
        this.this$0.mShadowDrawable.setShadowSize(this.mShadowSizeStart + (this.mShadowSizeEnd - this.mShadowSizeStart) * valueAnimatorCompat.getAnimatedFraction());
    }
}
