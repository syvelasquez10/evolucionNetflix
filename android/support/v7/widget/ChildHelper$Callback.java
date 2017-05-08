// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.view.ViewGroup$LayoutParams;
import android.view.View;

interface ChildHelper$Callback
{
    void addView(final View p0, final int p1);
    
    void attachViewToParent(final View p0, final int p1, final ViewGroup$LayoutParams p2);
    
    void detachViewFromParent(final int p0);
    
    View getChildAt(final int p0);
    
    int getChildCount();
    
    RecyclerView$ViewHolder getChildViewHolder(final View p0);
    
    int indexOfChild(final View p0);
    
    void onEnteredHiddenState(final View p0);
    
    void onLeftHiddenState(final View p0);
    
    void removeAllViews();
    
    void removeViewAt(final int p0);
}
