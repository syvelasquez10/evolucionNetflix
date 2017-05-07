// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.app;

import android.view.animation.Animation;
import android.view.animation.Animation$AnimationListener;

class FragmentManagerImpl$5 implements Animation$AnimationListener
{
    final /* synthetic */ FragmentManagerImpl this$0;
    final /* synthetic */ Fragment val$fragment;
    
    FragmentManagerImpl$5(final FragmentManagerImpl this$0, final Fragment val$fragment) {
        this.this$0 = this$0;
        this.val$fragment = val$fragment;
    }
    
    public void onAnimationEnd(final Animation animation) {
        if (this.val$fragment.mAnimatingAway != null) {
            this.val$fragment.mAnimatingAway = null;
            this.this$0.moveToState(this.val$fragment, this.val$fragment.mStateAfterAnimating, 0, 0, false);
        }
    }
    
    public void onAnimationRepeat(final Animation animation) {
    }
    
    public void onAnimationStart(final Animation animation) {
    }
}
