// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

class TabLayout$SlidingTabStrip$2 extends ValueAnimatorCompat$AnimatorListenerAdapter
{
    final /* synthetic */ TabLayout$SlidingTabStrip this$1;
    final /* synthetic */ int val$position;
    
    TabLayout$SlidingTabStrip$2(final TabLayout$SlidingTabStrip this$1, final int val$position) {
        this.this$1 = this$1;
        this.val$position = val$position;
    }
    
    @Override
    public void onAnimationEnd(final ValueAnimatorCompat valueAnimatorCompat) {
        this.this$1.mSelectedPosition = this.val$position;
        this.this$1.mSelectionOffset = 0.0f;
    }
}
