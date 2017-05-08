// 
// Decompiled by Procyon v0.5.30
// 

package android.support.transition;

import android.animation.Animator;
import android.view.ViewGroup;
import android.transition.TransitionValues;
import android.transition.Visibility;

class VisibilityKitKat$VisibilityWrapper extends Visibility
{
    private final VisibilityInterface mVisibility;
    
    VisibilityKitKat$VisibilityWrapper(final VisibilityInterface mVisibility) {
        this.mVisibility = mVisibility;
    }
    
    public void captureEndValues(final TransitionValues transitionValues) {
        TransitionKitKat.wrapCaptureEndValues(this.mVisibility, transitionValues);
    }
    
    public void captureStartValues(final TransitionValues transitionValues) {
        TransitionKitKat.wrapCaptureStartValues(this.mVisibility, transitionValues);
    }
    
    public Animator createAnimator(final ViewGroup viewGroup, final TransitionValues transitionValues, final TransitionValues transitionValues2) {
        return this.mVisibility.createAnimator(viewGroup, TransitionKitKat.convertToSupport(transitionValues), TransitionKitKat.convertToSupport(transitionValues2));
    }
    
    public boolean isVisible(final TransitionValues transitionValues) {
        if (transitionValues == null) {
            return false;
        }
        final android.support.transition.TransitionValues transitionValues2 = new android.support.transition.TransitionValues();
        TransitionKitKat.copyValues(transitionValues, transitionValues2);
        return this.mVisibility.isVisible(transitionValues2);
    }
    
    public Animator onAppear(final ViewGroup viewGroup, final TransitionValues transitionValues, final int n, final TransitionValues transitionValues2, final int n2) {
        return this.mVisibility.onAppear(viewGroup, TransitionKitKat.convertToSupport(transitionValues), n, TransitionKitKat.convertToSupport(transitionValues2), n2);
    }
    
    public Animator onDisappear(final ViewGroup viewGroup, final TransitionValues transitionValues, final int n, final TransitionValues transitionValues2, final int n2) {
        return this.mVisibility.onDisappear(viewGroup, TransitionKitKat.convertToSupport(transitionValues), n, TransitionKitKat.convertToSupport(transitionValues2), n2);
    }
}
