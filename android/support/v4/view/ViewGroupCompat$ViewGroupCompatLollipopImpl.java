// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view;

import android.view.ViewGroup;

class ViewGroupCompat$ViewGroupCompatLollipopImpl extends ViewGroupCompat$ViewGroupCompatJellybeanMR2Impl
{
    @Override
    public int getNestedScrollAxes(final ViewGroup viewGroup) {
        return ViewGroupCompatLollipop.getNestedScrollAxes(viewGroup);
    }
    
    @Override
    public boolean isTransitionGroup(final ViewGroup viewGroup) {
        return ViewGroupCompatLollipop.isTransitionGroup(viewGroup);
    }
    
    @Override
    public void setTransitionGroup(final ViewGroup viewGroup, final boolean b) {
        ViewGroupCompatLollipop.setTransitionGroup(viewGroup, b);
    }
}
