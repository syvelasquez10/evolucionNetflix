// 
// Decompiled by Procyon v0.5.30
// 

package android.support.transition;

import android.animation.Animator;
import android.support.v4.util.ArrayMap;
import android.animation.AnimatorListenerAdapter;

class TransitionPort$1 extends AnimatorListenerAdapter
{
    final /* synthetic */ TransitionPort this$0;
    final /* synthetic */ ArrayMap val$runningAnimators;
    
    TransitionPort$1(final TransitionPort this$0, final ArrayMap val$runningAnimators) {
        this.this$0 = this$0;
        this.val$runningAnimators = val$runningAnimators;
    }
    
    public void onAnimationEnd(final Animator animator) {
        this.val$runningAnimators.remove(animator);
        this.this$0.mCurrentAnimators.remove(animator);
    }
    
    public void onAnimationStart(final Animator animator) {
        this.this$0.mCurrentAnimators.add(animator);
    }
}
