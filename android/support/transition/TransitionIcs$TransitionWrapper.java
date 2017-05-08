// 
// Decompiled by Procyon v0.5.30
// 

package android.support.transition;

import android.animation.Animator;
import android.view.ViewGroup;

class TransitionIcs$TransitionWrapper extends TransitionPort
{
    private TransitionInterface mTransition;
    
    public TransitionIcs$TransitionWrapper(final TransitionInterface mTransition) {
        this.mTransition = mTransition;
    }
    
    @Override
    public void captureEndValues(final TransitionValues transitionValues) {
        this.mTransition.captureEndValues(transitionValues);
    }
    
    @Override
    public void captureStartValues(final TransitionValues transitionValues) {
        this.mTransition.captureStartValues(transitionValues);
    }
    
    @Override
    public Animator createAnimator(final ViewGroup viewGroup, final TransitionValues transitionValues, final TransitionValues transitionValues2) {
        return this.mTransition.createAnimator(viewGroup, transitionValues, transitionValues2);
    }
}
