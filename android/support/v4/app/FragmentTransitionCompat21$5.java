// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.app;

import android.view.View;
import android.transition.Transition;
import java.util.ArrayList;
import android.transition.Transition$TransitionListener;

final class FragmentTransitionCompat21$5 implements Transition$TransitionListener
{
    final /* synthetic */ Object val$enterTransition;
    final /* synthetic */ ArrayList val$enteringViews;
    final /* synthetic */ Object val$exitTransition;
    final /* synthetic */ ArrayList val$exitingViews;
    final /* synthetic */ Object val$sharedElementTransition;
    final /* synthetic */ ArrayList val$sharedElementsIn;
    
    FragmentTransitionCompat21$5(final Object val$enterTransition, final ArrayList val$enteringViews, final Object val$exitTransition, final ArrayList val$exitingViews, final Object val$sharedElementTransition, final ArrayList val$sharedElementsIn) {
        this.val$enterTransition = val$enterTransition;
        this.val$enteringViews = val$enteringViews;
        this.val$exitTransition = val$exitTransition;
        this.val$exitingViews = val$exitingViews;
        this.val$sharedElementTransition = val$sharedElementTransition;
        this.val$sharedElementsIn = val$sharedElementsIn;
    }
    
    public void onTransitionCancel(final Transition transition) {
    }
    
    public void onTransitionEnd(final Transition transition) {
    }
    
    public void onTransitionPause(final Transition transition) {
    }
    
    public void onTransitionResume(final Transition transition) {
    }
    
    public void onTransitionStart(final Transition transition) {
        if (this.val$enterTransition != null) {
            FragmentTransitionCompat21.replaceTargets(this.val$enterTransition, this.val$enteringViews, null);
        }
        if (this.val$exitTransition != null) {
            FragmentTransitionCompat21.replaceTargets(this.val$exitTransition, this.val$exitingViews, null);
        }
        if (this.val$sharedElementTransition != null) {
            FragmentTransitionCompat21.replaceTargets(this.val$sharedElementTransition, this.val$sharedElementsIn, null);
        }
    }
}
