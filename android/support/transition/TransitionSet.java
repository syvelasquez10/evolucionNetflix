// 
// Decompiled by Procyon v0.5.30
// 

package android.support.transition;

import android.animation.Animator;
import android.view.ViewGroup;
import android.os.Build$VERSION;

public class TransitionSet extends Transition
{
    public TransitionSet() {
        super(true);
        if (Build$VERSION.SDK_INT < 19) {
            this.mImpl = new TransitionSetIcs(this);
            return;
        }
        this.mImpl = new TransitionSetKitKat(this);
    }
    
    public TransitionSet addTransition(final Transition transition) {
        ((TransitionSetImpl)this.mImpl).addTransition(transition.mImpl);
        return this;
    }
    
    @Override
    public void captureEndValues(final TransitionValues transitionValues) {
        this.mImpl.captureEndValues(transitionValues);
    }
    
    @Override
    public void captureStartValues(final TransitionValues transitionValues) {
        this.mImpl.captureStartValues(transitionValues);
    }
    
    @Override
    public Animator createAnimator(final ViewGroup viewGroup, final TransitionValues transitionValues, final TransitionValues transitionValues2) {
        return this.mImpl.createAnimator(viewGroup, transitionValues, transitionValues2);
    }
    
    public TransitionSet setOrdering(final int ordering) {
        ((TransitionSetImpl)this.mImpl).setOrdering(ordering);
        return this;
    }
}
