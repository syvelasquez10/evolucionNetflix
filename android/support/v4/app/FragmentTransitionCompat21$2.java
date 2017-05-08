// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.app;

import android.transition.Transition$EpicenterCallback;
import java.util.List;
import android.transition.TransitionManager;
import android.view.ViewGroup;
import android.transition.TransitionSet;
import android.graphics.Rect;
import java.util.Iterator;
import java.util.Collection;
import java.util.Map;
import java.util.ArrayList;
import android.transition.Transition;
import android.view.View;
import android.view.ViewTreeObserver$OnPreDrawListener;

final class FragmentTransitionCompat21$2 implements ViewTreeObserver$OnPreDrawListener
{
    final /* synthetic */ View val$container;
    final /* synthetic */ Transition val$enterTransition;
    final /* synthetic */ ArrayList val$enteringViews;
    final /* synthetic */ Transition val$exitTransition;
    final /* synthetic */ FragmentTransitionCompat21$ViewRetriever val$inFragment;
    final /* synthetic */ Map val$nameOverrides;
    final /* synthetic */ View val$nonExistentView;
    final /* synthetic */ Map val$renamedViews;
    
    FragmentTransitionCompat21$2(final View val$container, final Transition val$enterTransition, final View val$nonExistentView, final FragmentTransitionCompat21$ViewRetriever val$inFragment, final Map val$nameOverrides, final Map val$renamedViews, final ArrayList val$enteringViews, final Transition val$exitTransition) {
        this.val$container = val$container;
        this.val$enterTransition = val$enterTransition;
        this.val$nonExistentView = val$nonExistentView;
        this.val$inFragment = val$inFragment;
        this.val$nameOverrides = val$nameOverrides;
        this.val$renamedViews = val$renamedViews;
        this.val$enteringViews = val$enteringViews;
        this.val$exitTransition = val$exitTransition;
    }
    
    public boolean onPreDraw() {
        this.val$container.getViewTreeObserver().removeOnPreDrawListener((ViewTreeObserver$OnPreDrawListener)this);
        if (this.val$enterTransition != null) {
            this.val$enterTransition.removeTarget(this.val$nonExistentView);
        }
        if (this.val$inFragment != null) {
            final View view = this.val$inFragment.getView();
            if (view != null) {
                if (!this.val$nameOverrides.isEmpty()) {
                    FragmentTransitionCompat21.findNamedViews(this.val$renamedViews, view);
                    this.val$renamedViews.keySet().retainAll(this.val$nameOverrides.values());
                    for (final Map.Entry<K, String> entry : this.val$nameOverrides.entrySet()) {
                        final View view2 = this.val$renamedViews.get(entry.getValue());
                        if (view2 != null) {
                            view2.setTransitionName((String)entry.getKey());
                        }
                    }
                }
                if (this.val$enterTransition != null) {
                    captureTransitioningViews(this.val$enteringViews, view);
                    this.val$enteringViews.removeAll(this.val$renamedViews.values());
                    this.val$enteringViews.add(this.val$nonExistentView);
                    FragmentTransitionCompat21.addTargets(this.val$enterTransition, this.val$enteringViews);
                }
            }
        }
        excludeViews(this.val$exitTransition, this.val$enterTransition, this.val$enteringViews, true);
        return true;
    }
}
