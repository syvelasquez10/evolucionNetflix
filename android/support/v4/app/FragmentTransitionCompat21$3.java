// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.app;

import java.util.Collection;
import java.util.List;
import android.transition.TransitionManager;
import android.view.ViewGroup;
import android.view.ViewTreeObserver$OnPreDrawListener;
import java.util.Map;
import android.transition.TransitionSet;
import android.view.View;
import java.util.ArrayList;
import android.transition.Transition;
import android.graphics.Rect;
import android.transition.Transition$EpicenterCallback;

final class FragmentTransitionCompat21$3 extends Transition$EpicenterCallback
{
    private Rect mEpicenter;
    final /* synthetic */ FragmentTransitionCompat21$EpicenterView val$epicenterView;
    
    FragmentTransitionCompat21$3(final FragmentTransitionCompat21$EpicenterView val$epicenterView) {
        this.val$epicenterView = val$epicenterView;
    }
    
    public Rect onGetEpicenter(final Transition transition) {
        if (this.mEpicenter == null && this.val$epicenterView.epicenter != null) {
            this.mEpicenter = getBoundsOnScreen(this.val$epicenterView.epicenter);
        }
        return this.mEpicenter;
    }
}
