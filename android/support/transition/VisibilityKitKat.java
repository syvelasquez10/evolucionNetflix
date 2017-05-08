// 
// Decompiled by Procyon v0.5.30
// 

package android.support.transition;

import android.transition.Transition;
import android.animation.Animator;
import android.view.ViewGroup;
import android.transition.Visibility;
import android.annotation.TargetApi;

@TargetApi(19)
class VisibilityKitKat extends TransitionKitKat implements VisibilityImpl
{
    @Override
    public void init(final TransitionInterface mExternalTransition, final Object o) {
        this.mExternalTransition = mExternalTransition;
        if (o == null) {
            this.mTransition = (Transition)new VisibilityKitKat$VisibilityWrapper((VisibilityInterface)mExternalTransition);
            return;
        }
        this.mTransition = (Transition)o;
    }
    
    @Override
    public boolean isVisible(final TransitionValues transitionValues) {
        return ((Visibility)this.mTransition).isVisible(TransitionKitKat.convertToPlatform(transitionValues));
    }
    
    @Override
    public Animator onAppear(final ViewGroup viewGroup, final TransitionValues transitionValues, final int n, final TransitionValues transitionValues2, final int n2) {
        return ((Visibility)this.mTransition).onAppear(viewGroup, TransitionKitKat.convertToPlatform(transitionValues), n, TransitionKitKat.convertToPlatform(transitionValues2), n2);
    }
    
    @Override
    public Animator onDisappear(final ViewGroup viewGroup, final TransitionValues transitionValues, final int n, final TransitionValues transitionValues2, final int n2) {
        return ((Visibility)this.mTransition).onDisappear(viewGroup, TransitionKitKat.convertToPlatform(transitionValues), n, TransitionKitKat.convertToPlatform(transitionValues2), n2);
    }
}
