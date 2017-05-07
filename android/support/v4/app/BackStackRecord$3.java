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
import java.util.Collection;
import android.content.Context;
import android.util.SparseArray;
import android.support.v4.util.ArrayMap;
import java.util.ArrayList;
import android.view.View;
import android.view.ViewTreeObserver$OnPreDrawListener;

class BackStackRecord$3 implements ViewTreeObserver$OnPreDrawListener
{
    final /* synthetic */ BackStackRecord this$0;
    final /* synthetic */ int val$containerId;
    final /* synthetic */ View val$sceneRoot;
    final /* synthetic */ BackStackRecord$TransitionState val$state;
    final /* synthetic */ Object val$transition;
    
    BackStackRecord$3(final BackStackRecord this$0, final View val$sceneRoot, final BackStackRecord$TransitionState val$state, final int val$containerId, final Object val$transition) {
        this.this$0 = this$0;
        this.val$sceneRoot = val$sceneRoot;
        this.val$state = val$state;
        this.val$containerId = val$containerId;
        this.val$transition = val$transition;
    }
    
    public boolean onPreDraw() {
        this.val$sceneRoot.getViewTreeObserver().removeOnPreDrawListener((ViewTreeObserver$OnPreDrawListener)this);
        this.this$0.excludeHiddenFragments(this.val$state, this.val$containerId, this.val$transition);
        return true;
    }
}
