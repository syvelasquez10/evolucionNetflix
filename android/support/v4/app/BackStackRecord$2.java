// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.app;

import java.io.FileDescriptor;
import java.io.Writer;
import java.io.PrintWriter;
import android.support.v4.util.LogWriter;
import android.util.Log;
import android.os.Build$VERSION;
import android.support.v4.util.SimpleArrayMap;
import android.view.ViewGroup;
import java.util.Map;
import java.util.List;
import android.content.Context;
import android.util.SparseArray;
import android.support.v4.util.ArrayMap;
import java.util.Collection;
import java.util.ArrayList;
import android.view.View;
import android.view.ViewTreeObserver$OnPreDrawListener;

class BackStackRecord$2 implements ViewTreeObserver$OnPreDrawListener
{
    final /* synthetic */ BackStackRecord this$0;
    final /* synthetic */ Fragment val$inFragment;
    final /* synthetic */ boolean val$isBack;
    final /* synthetic */ Fragment val$outFragment;
    final /* synthetic */ View val$sceneRoot;
    final /* synthetic */ ArrayList val$sharedElementTargets;
    final /* synthetic */ Object val$sharedElementTransition;
    final /* synthetic */ BackStackRecord$TransitionState val$state;
    
    BackStackRecord$2(final BackStackRecord this$0, final View val$sceneRoot, final Object val$sharedElementTransition, final ArrayList val$sharedElementTargets, final BackStackRecord$TransitionState val$state, final boolean val$isBack, final Fragment val$inFragment, final Fragment val$outFragment) {
        this.this$0 = this$0;
        this.val$sceneRoot = val$sceneRoot;
        this.val$sharedElementTransition = val$sharedElementTransition;
        this.val$sharedElementTargets = val$sharedElementTargets;
        this.val$state = val$state;
        this.val$isBack = val$isBack;
        this.val$inFragment = val$inFragment;
        this.val$outFragment = val$outFragment;
    }
    
    public boolean onPreDraw() {
        this.val$sceneRoot.getViewTreeObserver().removeOnPreDrawListener((ViewTreeObserver$OnPreDrawListener)this);
        if (this.val$sharedElementTransition != null) {
            FragmentTransitionCompat21.removeTargets(this.val$sharedElementTransition, this.val$sharedElementTargets);
            this.val$sharedElementTargets.clear();
            final ArrayMap access$000 = this.this$0.mapSharedElementsIn(this.val$state, this.val$isBack, this.val$inFragment);
            if (access$000.isEmpty()) {
                this.val$sharedElementTargets.add(this.val$state.nonExistentView);
            }
            else {
                this.val$sharedElementTargets.addAll(access$000.values());
            }
            FragmentTransitionCompat21.addTargets(this.val$sharedElementTransition, this.val$sharedElementTargets);
            this.this$0.setEpicenterIn(access$000, this.val$state);
            this.this$0.callSharedElementEnd(this.val$state, this.val$inFragment, this.val$outFragment, this.val$isBack, access$000);
        }
        return true;
    }
}
