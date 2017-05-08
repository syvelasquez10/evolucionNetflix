// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

class CollapsingToolbarLayout$2 implements ValueAnimatorCompat$AnimatorUpdateListener
{
    final /* synthetic */ CollapsingToolbarLayout this$0;
    
    CollapsingToolbarLayout$2(final CollapsingToolbarLayout this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onAnimationUpdate(final ValueAnimatorCompat valueAnimatorCompat) {
        this.this$0.setScrimAlpha(valueAnimatorCompat.getAnimatedIntValue());
    }
}
