// 
// Decompiled by Procyon v0.5.30
// 

package android.support.transition;

import android.animation.Animator$AnimatorListener;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

class TransitionPort$2 extends AnimatorListenerAdapter
{
    final /* synthetic */ TransitionPort this$0;
    
    TransitionPort$2(final TransitionPort this$0) {
        this.this$0 = this$0;
    }
    
    public void onAnimationEnd(final Animator animator) {
        this.this$0.end();
        animator.removeListener((Animator$AnimatorListener)this);
    }
}
