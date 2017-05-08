// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

class TextInputLayout$5 implements ValueAnimatorCompat$AnimatorUpdateListener
{
    final /* synthetic */ TextInputLayout this$0;
    
    TextInputLayout$5(final TextInputLayout this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onAnimationUpdate(final ValueAnimatorCompat valueAnimatorCompat) {
        this.this$0.mCollapsingTextHelper.setExpansionFraction(valueAnimatorCompat.getAnimatedFloatValue());
    }
}
