// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

class AppBarLayout$Behavior$1 implements ValueAnimatorCompat$AnimatorUpdateListener
{
    final /* synthetic */ AppBarLayout$Behavior this$0;
    final /* synthetic */ AppBarLayout val$child;
    final /* synthetic */ CoordinatorLayout val$coordinatorLayout;
    
    AppBarLayout$Behavior$1(final AppBarLayout$Behavior this$0, final CoordinatorLayout val$coordinatorLayout, final AppBarLayout val$child) {
        this.this$0 = this$0;
        this.val$coordinatorLayout = val$coordinatorLayout;
        this.val$child = val$child;
    }
    
    @Override
    public void onAnimationUpdate(final ValueAnimatorCompat valueAnimatorCompat) {
        this.this$0.setHeaderTopBottomOffset(this.val$coordinatorLayout, this.val$child, valueAnimatorCompat.getAnimatedIntValue());
    }
}
