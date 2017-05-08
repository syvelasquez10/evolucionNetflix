// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.app;

import android.view.View;
import java.util.ArrayList;
import android.view.ViewTreeObserver$OnPreDrawListener;

final class FragmentTransitionCompat21$3 implements ViewTreeObserver$OnPreDrawListener
{
    final /* synthetic */ ArrayList val$inNames;
    final /* synthetic */ int val$numSharedElements;
    final /* synthetic */ ArrayList val$outNames;
    final /* synthetic */ View val$sceneRoot;
    final /* synthetic */ ArrayList val$sharedElementsIn;
    final /* synthetic */ ArrayList val$sharedElementsOut;
    
    FragmentTransitionCompat21$3(final View val$sceneRoot, final int val$numSharedElements, final ArrayList val$sharedElementsIn, final ArrayList val$inNames, final ArrayList val$sharedElementsOut, final ArrayList val$outNames) {
        this.val$sceneRoot = val$sceneRoot;
        this.val$numSharedElements = val$numSharedElements;
        this.val$sharedElementsIn = val$sharedElementsIn;
        this.val$inNames = val$inNames;
        this.val$sharedElementsOut = val$sharedElementsOut;
        this.val$outNames = val$outNames;
    }
    
    public boolean onPreDraw() {
        this.val$sceneRoot.getViewTreeObserver().removeOnPreDrawListener((ViewTreeObserver$OnPreDrawListener)this);
        for (int i = 0; i < this.val$numSharedElements; ++i) {
            ((View)this.val$sharedElementsIn.get(i)).setTransitionName((String)this.val$inNames.get(i));
            ((View)this.val$sharedElementsOut.get(i)).setTransitionName((String)this.val$outNames.get(i));
        }
        return true;
    }
}
