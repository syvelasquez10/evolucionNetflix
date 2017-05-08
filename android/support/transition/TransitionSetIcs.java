// 
// Decompiled by Procyon v0.5.30
// 

package android.support.transition;

import android.annotation.TargetApi;

@TargetApi(14)
class TransitionSetIcs extends TransitionIcs implements TransitionSetImpl
{
    private TransitionSetPort mTransitionSet;
    
    public TransitionSetIcs(final TransitionInterface transitionInterface) {
        this.init(transitionInterface, this.mTransitionSet = new TransitionSetPort());
    }
    
    @Override
    public TransitionSetIcs addTransition(final TransitionImpl transitionImpl) {
        this.mTransitionSet.addTransition(((TransitionIcs)transitionImpl).mTransition);
        return this;
    }
    
    @Override
    public TransitionSetIcs setOrdering(final int ordering) {
        this.mTransitionSet.setOrdering(ordering);
        return this;
    }
}
