// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.views.recyclerview;

import android.support.v7.widget.RecyclerView$ViewHolder;
import android.support.v7.widget.RecyclerView$ItemAnimator;

class NotAnimatedItemAnimator extends RecyclerView$ItemAnimator
{
    @Override
    public void endAnimation(final RecyclerView$ViewHolder recyclerView$ViewHolder) {
    }
    
    @Override
    public void endAnimations() {
    }
    
    @Override
    public boolean isRunning() {
        return false;
    }
    
    @Override
    public void runPendingAnimations() {
    }
}
