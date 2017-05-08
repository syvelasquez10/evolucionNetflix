// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

interface ViewInfoStore$ProcessCallback
{
    void processAppeared(final RecyclerView$ViewHolder p0, final RecyclerView$ItemAnimator$ItemHolderInfo p1, final RecyclerView$ItemAnimator$ItemHolderInfo p2);
    
    void processDisappeared(final RecyclerView$ViewHolder p0, final RecyclerView$ItemAnimator$ItemHolderInfo p1, final RecyclerView$ItemAnimator$ItemHolderInfo p2);
    
    void processPersistent(final RecyclerView$ViewHolder p0, final RecyclerView$ItemAnimator$ItemHolderInfo p1, final RecyclerView$ItemAnimator$ItemHolderInfo p2);
    
    void unused(final RecyclerView$ViewHolder p0);
}
