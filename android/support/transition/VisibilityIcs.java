// 
// Decompiled by Procyon v0.5.30
// 

package android.support.transition;

import android.animation.Animator;
import android.view.ViewGroup;
import android.annotation.TargetApi;

@TargetApi(14)
class VisibilityIcs extends TransitionIcs implements VisibilityImpl
{
    @Override
    public void init(final TransitionInterface mExternalTransition, final Object o) {
        this.mExternalTransition = mExternalTransition;
        if (o == null) {
            this.mTransition = new VisibilityIcs$VisibilityWrapper((VisibilityInterface)mExternalTransition);
            return;
        }
        this.mTransition = (VisibilityPort)o;
    }
    
    @Override
    public boolean isVisible(final TransitionValues transitionValues) {
        return ((VisibilityPort)this.mTransition).isVisible(transitionValues);
    }
    
    @Override
    public Animator onAppear(final ViewGroup viewGroup, final TransitionValues transitionValues, final int n, final TransitionValues transitionValues2, final int n2) {
        return ((VisibilityPort)this.mTransition).onAppear(viewGroup, transitionValues, n, transitionValues2, n2);
    }
    
    @Override
    public Animator onDisappear(final ViewGroup viewGroup, final TransitionValues transitionValues, final int n, final TransitionValues transitionValues2, final int n2) {
        return ((VisibilityPort)this.mTransition).onDisappear(viewGroup, transitionValues, n, transitionValues2, n2);
    }
}
