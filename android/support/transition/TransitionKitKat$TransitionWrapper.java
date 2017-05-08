// 
// Decompiled by Procyon v0.5.30
// 

package android.support.transition;

import android.animation.Animator;
import android.view.ViewGroup;
import android.transition.TransitionValues;
import android.transition.Transition;

class TransitionKitKat$TransitionWrapper extends Transition
{
    private TransitionInterface mTransition;
    
    public TransitionKitKat$TransitionWrapper(final TransitionInterface mTransition) {
        this.mTransition = mTransition;
    }
    
    public void captureEndValues(final TransitionValues transitionValues) {
        TransitionKitKat.wrapCaptureEndValues(this.mTransition, transitionValues);
    }
    
    public void captureStartValues(final TransitionValues transitionValues) {
        TransitionKitKat.wrapCaptureStartValues(this.mTransition, transitionValues);
    }
    
    public Animator createAnimator(final ViewGroup viewGroup, final TransitionValues transitionValues, final TransitionValues transitionValues2) {
        return this.mTransition.createAnimator(viewGroup, TransitionKitKat.convertToSupport(transitionValues), TransitionKitKat.convertToSupport(transitionValues2));
    }
}
