// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view;

import android.view.ViewGroup;

class ViewGroupCompatApi21
{
    public static boolean isTransitionGroup(final ViewGroup viewGroup) {
        return viewGroup.isTransitionGroup();
    }
    
    public static void setTransitionGroup(final ViewGroup viewGroup, final boolean transitionGroup) {
        viewGroup.setTransitionGroup(transitionGroup);
    }
}
