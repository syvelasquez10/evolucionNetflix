// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.app;

import android.os.Build$VERSION;
import android.graphics.Rect;
import android.support.v4.view.ViewCompat;
import java.util.Collection;
import java.util.Map;
import java.util.List;
import android.util.SparseArray;
import android.support.v4.util.ArrayMap;
import android.view.View;
import java.util.ArrayList;
import android.view.ViewGroup;
import android.view.ViewTreeObserver$OnPreDrawListener;

final class FragmentTransition$1 implements ViewTreeObserver$OnPreDrawListener
{
    final /* synthetic */ ViewGroup val$container;
    final /* synthetic */ ArrayList val$exitingViews;
    
    FragmentTransition$1(final ViewGroup val$container, final ArrayList val$exitingViews) {
        this.val$container = val$container;
        this.val$exitingViews = val$exitingViews;
    }
    
    public boolean onPreDraw() {
        this.val$container.getViewTreeObserver().removeOnPreDrawListener((ViewTreeObserver$OnPreDrawListener)this);
        setViewVisibility(this.val$exitingViews, 4);
        return true;
    }
}
