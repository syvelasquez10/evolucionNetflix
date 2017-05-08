// 
// Decompiled by Procyon v0.5.30
// 

package android.support.transition;

import android.animation.Animator;
import android.view.ViewGroup;

interface VisibilityImpl
{
    boolean isVisible(final TransitionValues p0);
    
    Animator onAppear(final ViewGroup p0, final TransitionValues p1, final int p2, final TransitionValues p3, final int p4);
    
    Animator onDisappear(final ViewGroup p0, final TransitionValues p1, final int p2, final TransitionValues p3, final int p4);
}
