// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

class TabLayout$1 implements ValueAnimatorCompat$AnimatorUpdateListener
{
    final /* synthetic */ TabLayout this$0;
    
    TabLayout$1(final TabLayout this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onAnimationUpdate(final ValueAnimatorCompat valueAnimatorCompat) {
        this.this$0.scrollTo(valueAnimatorCompat.getAnimatedIntValue(), 0);
    }
}
