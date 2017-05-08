// 
// Decompiled by Procyon v0.5.30
// 

package android.support.transition;

class TransitionSetPort$TransitionSetListener extends TransitionPort$TransitionListenerAdapter
{
    TransitionSetPort mTransitionSet;
    
    TransitionSetPort$TransitionSetListener(final TransitionSetPort mTransitionSet) {
        this.mTransitionSet = mTransitionSet;
    }
    
    @Override
    public void onTransitionEnd(final TransitionPort transitionPort) {
        final TransitionSetPort mTransitionSet = this.mTransitionSet;
        --mTransitionSet.mCurrentListeners;
        if (this.mTransitionSet.mCurrentListeners == 0) {
            this.mTransitionSet.mStarted = false;
            this.mTransitionSet.end();
        }
        transitionPort.removeListener(this);
    }
    
    @Override
    public void onTransitionStart(final TransitionPort transitionPort) {
        if (!this.mTransitionSet.mStarted) {
            this.mTransitionSet.start();
            this.mTransitionSet.mStarted = true;
        }
    }
}
