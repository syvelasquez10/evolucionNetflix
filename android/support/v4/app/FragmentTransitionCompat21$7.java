// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.app;

import android.view.View;
import java.util.ArrayList;
import android.view.ViewGroup;
import java.util.Map;
import android.view.ViewTreeObserver$OnPreDrawListener;

final class FragmentTransitionCompat21$7 implements ViewTreeObserver$OnPreDrawListener
{
    final /* synthetic */ Map val$nameOverrides;
    final /* synthetic */ ViewGroup val$sceneRoot;
    final /* synthetic */ ArrayList val$sharedElementsIn;
    
    FragmentTransitionCompat21$7(final ViewGroup val$sceneRoot, final ArrayList val$sharedElementsIn, final Map val$nameOverrides) {
        this.val$sceneRoot = val$sceneRoot;
        this.val$sharedElementsIn = val$sharedElementsIn;
        this.val$nameOverrides = val$nameOverrides;
    }
    
    public boolean onPreDraw() {
        this.val$sceneRoot.getViewTreeObserver().removeOnPreDrawListener((ViewTreeObserver$OnPreDrawListener)this);
        for (int size = this.val$sharedElementsIn.size(), i = 0; i < size; ++i) {
            final View view = this.val$sharedElementsIn.get(i);
            view.setTransitionName((String)this.val$nameOverrides.get(view.getTransitionName()));
        }
        return true;
    }
}
