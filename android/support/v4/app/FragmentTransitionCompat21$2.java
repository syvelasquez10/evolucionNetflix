// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.app;

import android.transition.Transition$EpicenterCallback;
import android.transition.TransitionSet;
import android.transition.TransitionManager;
import android.view.ViewGroup;
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
    final /* synthetic */ FragmentTransitionCompat21$ViewRetriever val$inFragment;
    final /* synthetic */ Map val$nameOverrides;
    final /* synthetic */ Map val$renamedViews;
    
    FragmentTransitionCompat21$2(final View val$container, final FragmentTransitionCompat21$ViewRetriever val$inFragment, final Map val$nameOverrides, final Map val$renamedViews, final Transition val$enterTransition, final ArrayList val$enteringViews) {
        this.val$container = val$container;
        this.val$inFragment = val$inFragment;
        this.val$nameOverrides = val$nameOverrides;
        this.val$renamedViews = val$renamedViews;
        this.val$enterTransition = val$enterTransition;
        this.val$enteringViews = val$enteringViews;
    }
    
    public boolean onPreDraw() {
        this.val$container.getViewTreeObserver().removeOnPreDrawListener((ViewTreeObserver$OnPreDrawListener)this);
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
                FragmentTransitionCompat21.addTargets(this.val$enterTransition, this.val$enteringViews);
            }
        }
        return true;
    }
}
