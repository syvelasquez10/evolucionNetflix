// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view;

import android.view.ViewGroup;

class ViewGroupCompat$ViewGroupCompatApi21Impl extends ViewGroupCompat$ViewGroupCompatJellybeanMR2Impl
{
    @Override
    public boolean isTransitionGroup(final ViewGroup viewGroup) {
        return ViewGroupCompatApi21.isTransitionGroup(viewGroup);
    }
    
    @Override
    public void setTransitionGroup(final ViewGroup viewGroup, final boolean b) {
        ViewGroupCompatApi21.setTransitionGroup(viewGroup, b);
    }
}
