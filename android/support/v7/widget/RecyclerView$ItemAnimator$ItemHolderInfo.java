// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.view.View;

public class RecyclerView$ItemAnimator$ItemHolderInfo
{
    public int bottom;
    public int left;
    public int right;
    public int top;
    
    public RecyclerView$ItemAnimator$ItemHolderInfo setFrom(final RecyclerView$ViewHolder recyclerView$ViewHolder) {
        return this.setFrom(recyclerView$ViewHolder, 0);
    }
    
    public RecyclerView$ItemAnimator$ItemHolderInfo setFrom(final RecyclerView$ViewHolder recyclerView$ViewHolder, final int n) {
        final View itemView = recyclerView$ViewHolder.itemView;
        this.left = itemView.getLeft();
        this.top = itemView.getTop();
        this.right = itemView.getRight();
        this.bottom = itemView.getBottom();
        return this;
    }
}
