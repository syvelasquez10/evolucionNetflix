// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.app;

import android.os.Build$VERSION;
import android.graphics.Rect;
import android.support.v4.view.ViewCompat;
import java.util.Map;
import java.util.List;
import android.util.SparseArray;
import android.support.v4.util.ArrayMap;
import java.util.Collection;
import android.view.ViewGroup;
import android.view.View;
import java.util.ArrayList;
import android.view.ViewTreeObserver$OnPreDrawListener;

final class FragmentTransition$2 implements ViewTreeObserver$OnPreDrawListener
{
    final /* synthetic */ Object val$enterTransition;
    final /* synthetic */ ArrayList val$enteringViews;
    final /* synthetic */ Object val$exitTransition;
    final /* synthetic */ ArrayList val$exitingViews;
    final /* synthetic */ Fragment val$inFragment;
    final /* synthetic */ View val$nonExistentView;
    final /* synthetic */ ViewGroup val$sceneRoot;
    final /* synthetic */ ArrayList val$sharedElementsIn;
    
    FragmentTransition$2(final ViewGroup val$sceneRoot, final Object val$enterTransition, final View val$nonExistentView, final Fragment val$inFragment, final ArrayList val$sharedElementsIn, final ArrayList val$enteringViews, final ArrayList val$exitingViews, final Object val$exitTransition) {
        this.val$sceneRoot = val$sceneRoot;
        this.val$enterTransition = val$enterTransition;
        this.val$nonExistentView = val$nonExistentView;
        this.val$inFragment = val$inFragment;
        this.val$sharedElementsIn = val$sharedElementsIn;
        this.val$enteringViews = val$enteringViews;
        this.val$exitingViews = val$exitingViews;
        this.val$exitTransition = val$exitTransition;
    }
    
    public boolean onPreDraw() {
        this.val$sceneRoot.getViewTreeObserver().removeOnPreDrawListener((ViewTreeObserver$OnPreDrawListener)this);
        if (this.val$enterTransition != null) {
            FragmentTransitionCompat21.removeTarget(this.val$enterTransition, this.val$nonExistentView);
            this.val$enteringViews.addAll(configureEnteringExitingViews(this.val$enterTransition, this.val$inFragment, this.val$sharedElementsIn, this.val$nonExistentView));
        }
        if (this.val$exitingViews != null) {
            final ArrayList<View> list = new ArrayList<View>();
            list.add(this.val$nonExistentView);
            FragmentTransitionCompat21.replaceTargets(this.val$exitTransition, this.val$exitingViews, list);
            this.val$exitingViews.clear();
            this.val$exitingViews.add(this.val$nonExistentView);
        }
        return true;
    }
}
