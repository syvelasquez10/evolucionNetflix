// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

class TabLayout$SlidingTabStrip$1 implements ValueAnimatorCompat$AnimatorUpdateListener
{
    final /* synthetic */ TabLayout$SlidingTabStrip this$1;
    final /* synthetic */ int val$startLeft;
    final /* synthetic */ int val$startRight;
    final /* synthetic */ int val$targetLeft;
    final /* synthetic */ int val$targetRight;
    
    TabLayout$SlidingTabStrip$1(final TabLayout$SlidingTabStrip this$1, final int val$startLeft, final int val$targetLeft, final int val$startRight, final int val$targetRight) {
        this.this$1 = this$1;
        this.val$startLeft = val$startLeft;
        this.val$targetLeft = val$targetLeft;
        this.val$startRight = val$startRight;
        this.val$targetRight = val$targetRight;
    }
    
    @Override
    public void onAnimationUpdate(final ValueAnimatorCompat valueAnimatorCompat) {
        final float animatedFraction = valueAnimatorCompat.getAnimatedFraction();
        this.this$1.setIndicatorPosition(AnimationUtils.lerp(this.val$startLeft, this.val$targetLeft, animatedFraction), AnimationUtils.lerp(this.val$startRight, this.val$targetRight, animatedFraction));
    }
}
