// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.app;

import android.support.v4.util.ArrayMap;
import java.util.Map;
import java.util.ArrayList;
import android.view.View;
import android.view.ViewTreeObserver$OnPreDrawListener;

class BackStackRecord$2 implements ViewTreeObserver$OnPreDrawListener
{
    final /* synthetic */ BackStackRecord this$0;
    final /* synthetic */ Object val$enterTransition;
    final /* synthetic */ Object val$exitTransition;
    final /* synthetic */ Fragment val$inFragment;
    final /* synthetic */ boolean val$isBack;
    final /* synthetic */ Fragment val$outFragment;
    final /* synthetic */ View val$sceneRoot;
    final /* synthetic */ ArrayList val$sharedElementTargets;
    final /* synthetic */ Object val$sharedElementTransition;
    final /* synthetic */ BackStackRecord$TransitionState val$state;
    
    BackStackRecord$2(final BackStackRecord this$0, final View val$sceneRoot, final Object val$sharedElementTransition, final ArrayList val$sharedElementTargets, final BackStackRecord$TransitionState val$state, final Object val$enterTransition, final Object val$exitTransition, final boolean val$isBack, final Fragment val$inFragment, final Fragment val$outFragment) {
        this.this$0 = this$0;
        this.val$sceneRoot = val$sceneRoot;
        this.val$sharedElementTransition = val$sharedElementTransition;
        this.val$sharedElementTargets = val$sharedElementTargets;
        this.val$state = val$state;
        this.val$enterTransition = val$enterTransition;
        this.val$exitTransition = val$exitTransition;
        this.val$isBack = val$isBack;
        this.val$inFragment = val$inFragment;
        this.val$outFragment = val$outFragment;
    }
    
    public boolean onPreDraw() {
        this.val$sceneRoot.getViewTreeObserver().removeOnPreDrawListener((ViewTreeObserver$OnPreDrawListener)this);
        FragmentTransitionCompat21.removeTargets(this.val$sharedElementTransition, this.val$sharedElementTargets);
        this.val$sharedElementTargets.remove(this.val$state.nonExistentView);
        FragmentTransitionCompat21.excludeSharedElementViews(this.val$enterTransition, this.val$exitTransition, this.val$sharedElementTransition, this.val$sharedElementTargets, false);
        this.val$sharedElementTargets.clear();
        final ArrayMap<String, View> mapSharedElementsIn = this.this$0.mapSharedElementsIn(this.val$state, this.val$isBack, this.val$inFragment);
        FragmentTransitionCompat21.setSharedElementTargets(this.val$sharedElementTransition, this.val$state.nonExistentView, mapSharedElementsIn, this.val$sharedElementTargets);
        this.this$0.setEpicenterIn(mapSharedElementsIn, this.val$state);
        this.this$0.callSharedElementEnd(this.val$state, this.val$inFragment, this.val$outFragment, this.val$isBack, mapSharedElementsIn);
        FragmentTransitionCompat21.excludeSharedElementViews(this.val$enterTransition, this.val$exitTransition, this.val$sharedElementTransition, this.val$sharedElementTargets, true);
        return true;
    }
}
