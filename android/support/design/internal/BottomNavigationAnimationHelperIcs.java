// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.internal;

import android.support.transition.TransitionManager;
import android.view.ViewGroup;
import android.support.transition.Transition;
import android.animation.TimeInterpolator;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.transition.AutoTransition;
import android.support.transition.TransitionSet;

class BottomNavigationAnimationHelperIcs extends BottomNavigationAnimationHelperBase
{
    private static final long ACTIVE_ANIMATION_DURATION_MS = 115L;
    private final TransitionSet mSet;
    
    BottomNavigationAnimationHelperIcs() {
        (this.mSet = new AutoTransition()).setOrdering(0);
        this.mSet.setDuration(115L);
        this.mSet.setInterpolator((TimeInterpolator)new FastOutSlowInInterpolator());
        this.mSet.addTransition(new TextScale());
    }
    
    @Override
    void beginDelayedTransition(final ViewGroup viewGroup) {
        TransitionManager.beginDelayedTransition(viewGroup, this.mSet);
    }
}
