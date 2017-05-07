// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.app;

import android.view.animation.Animation;
import android.view.View;

class FragmentManagerImpl$5 extends FragmentManagerImpl$AnimateOnHWLayerIfNeededListener
{
    final /* synthetic */ FragmentManagerImpl this$0;
    final /* synthetic */ Fragment val$fragment;
    
    FragmentManagerImpl$5(final FragmentManagerImpl this$0, final View view, final Animation animation, final Fragment val$fragment) {
        this.this$0 = this$0;
        this.val$fragment = val$fragment;
        super(view, animation);
    }
    
    @Override
    public void onAnimationEnd(final Animation animation) {
        super.onAnimationEnd(animation);
        if (this.val$fragment.mAnimatingAway != null) {
            this.val$fragment.mAnimatingAway = null;
            this.this$0.moveToState(this.val$fragment, this.val$fragment.mStateAfterAnimating, 0, 0, false);
        }
    }
}
