// 
// Decompiled by Procyon v0.5.30
// 

package android.support.transition;

import android.animation.Animator;
import android.view.ViewGroup;
import android.os.Build$VERSION;

public class ChangeBounds extends Transition
{
    public ChangeBounds() {
        super(true);
        if (Build$VERSION.SDK_INT < 19) {
            this.mImpl = new ChangeBoundsIcs(this);
            return;
        }
        this.mImpl = new ChangeBoundsKitKat(this);
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
}
