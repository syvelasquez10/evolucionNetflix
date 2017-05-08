// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.app;

import android.transition.Transition$EpicenterCallback;
import android.transition.Transition$TransitionListener;
import java.util.Collection;
import android.graphics.Rect;
import java.util.Iterator;
import java.util.List;
import android.transition.TransitionManager;
import android.view.ViewGroup;
import android.transition.TransitionSet;
import android.transition.Transition;
import android.annotation.TargetApi;
import java.util.ArrayList;
import android.view.View;
import java.util.Map;
import android.view.ViewTreeObserver$OnPreDrawListener;

final class FragmentTransitionCompat21$4 implements ViewTreeObserver$OnPreDrawListener
{
    final /* synthetic */ Map val$nameOverrides;
    final /* synthetic */ View val$sceneRoot;
    final /* synthetic */ ArrayList val$sharedElementsIn;
    
    FragmentTransitionCompat21$4(final View val$sceneRoot, final ArrayList val$sharedElementsIn, final Map val$nameOverrides) {
        this.val$sceneRoot = val$sceneRoot;
        this.val$sharedElementsIn = val$sharedElementsIn;
        this.val$nameOverrides = val$nameOverrides;
    }
    
    public boolean onPreDraw() {
        this.val$sceneRoot.getViewTreeObserver().removeOnPreDrawListener((ViewTreeObserver$OnPreDrawListener)this);
        for (int size = this.val$sharedElementsIn.size(), i = 0; i < size; ++i) {
            final View view = this.val$sharedElementsIn.get(i);
            final String transitionName = view.getTransitionName();
            if (transitionName != null) {
                view.setTransitionName(findKeyForValue(this.val$nameOverrides, transitionName));
            }
        }
        return true;
    }
}
