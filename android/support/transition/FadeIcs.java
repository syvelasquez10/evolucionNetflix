// 
// Decompiled by Procyon v0.5.30
// 

package android.support.transition;

import android.animation.Animator;
import android.view.ViewGroup;
import android.annotation.TargetApi;

@TargetApi(14)
class FadeIcs extends TransitionIcs implements VisibilityImpl
{
    public FadeIcs(final TransitionInterface transitionInterface) {
        this.init(transitionInterface, new FadePort());
    }
    
    public FadeIcs(final TransitionInterface transitionInterface, final int n) {
        this.init(transitionInterface, new FadePort(n));
    }
    
    @Override
    public boolean isVisible(final TransitionValues transitionValues) {
        return ((FadePort)this.mTransition).isVisible(transitionValues);
    }
    
    @Override
    public Animator onAppear(final ViewGroup viewGroup, final TransitionValues transitionValues, final int n, final TransitionValues transitionValues2, final int n2) {
        return ((FadePort)this.mTransition).onAppear(viewGroup, transitionValues, n, transitionValues2, n2);
    }
    
    @Override
    public Animator onDisappear(final ViewGroup viewGroup, final TransitionValues transitionValues, final int n, final TransitionValues transitionValues2, final int n2) {
        return ((FadePort)this.mTransition).onDisappear(viewGroup, transitionValues, n, transitionValues, n);
    }
}
