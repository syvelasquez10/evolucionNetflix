// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.app;

import java.util.Iterator;
import java.util.Map;
import android.view.View;
import java.util.ArrayList;
import android.transition.Transition;
import android.view.ViewTreeObserver$OnPreDrawListener;

final class FragmentTransitionCompat21$4 implements ViewTreeObserver$OnPreDrawListener
{
    final /* synthetic */ Transition val$enterTransition;
    final /* synthetic */ ArrayList val$enteringViews;
    final /* synthetic */ Transition val$exitTransition;
    final /* synthetic */ ArrayList val$exitingViews;
    final /* synthetic */ ArrayList val$hiddenViews;
    final /* synthetic */ View val$nonExistentView;
    final /* synthetic */ Transition val$overallTransition;
    final /* synthetic */ Map val$renamedViews;
    final /* synthetic */ View val$sceneRoot;
    final /* synthetic */ ArrayList val$sharedElementTargets;
    final /* synthetic */ Transition val$sharedElementTransition;
    
    FragmentTransitionCompat21$4(final View val$sceneRoot, final Transition val$enterTransition, final ArrayList val$enteringViews, final Transition val$exitTransition, final ArrayList val$exitingViews, final Transition val$sharedElementTransition, final ArrayList val$sharedElementTargets, final Map val$renamedViews, final ArrayList val$hiddenViews, final Transition val$overallTransition, final View val$nonExistentView) {
        this.val$sceneRoot = val$sceneRoot;
        this.val$enterTransition = val$enterTransition;
        this.val$enteringViews = val$enteringViews;
        this.val$exitTransition = val$exitTransition;
        this.val$exitingViews = val$exitingViews;
        this.val$sharedElementTransition = val$sharedElementTransition;
        this.val$sharedElementTargets = val$sharedElementTargets;
        this.val$renamedViews = val$renamedViews;
        this.val$hiddenViews = val$hiddenViews;
        this.val$overallTransition = val$overallTransition;
        this.val$nonExistentView = val$nonExistentView;
    }
    
    public boolean onPreDraw() {
        this.val$sceneRoot.getViewTreeObserver().removeOnPreDrawListener((ViewTreeObserver$OnPreDrawListener)this);
        if (this.val$enterTransition != null) {
            FragmentTransitionCompat21.removeTargets(this.val$enterTransition, this.val$enteringViews);
        }
        if (this.val$exitTransition != null) {
            FragmentTransitionCompat21.removeTargets(this.val$exitTransition, this.val$exitingViews);
        }
        if (this.val$sharedElementTransition != null) {
            FragmentTransitionCompat21.removeTargets(this.val$sharedElementTransition, this.val$sharedElementTargets);
        }
        for (final Map.Entry<K, View> entry : this.val$renamedViews.entrySet()) {
            entry.getValue().setTransitionName((String)entry.getKey());
        }
        for (int size = this.val$hiddenViews.size(), i = 0; i < size; ++i) {
            this.val$overallTransition.excludeTarget((View)this.val$hiddenViews.get(i), false);
        }
        this.val$overallTransition.excludeTarget(this.val$nonExistentView, false);
        return true;
    }
}
