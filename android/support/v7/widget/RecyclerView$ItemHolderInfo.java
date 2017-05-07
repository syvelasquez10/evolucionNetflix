// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

class RecyclerView$ItemHolderInfo
{
    int bottom;
    RecyclerView$ViewHolder holder;
    int left;
    int right;
    int top;
    
    RecyclerView$ItemHolderInfo(final RecyclerView$ViewHolder holder, final int left, final int top, final int right, final int bottom) {
        this.holder = holder;
        this.left = left;
        this.top = top;
        this.right = right;
        this.bottom = bottom;
    }
}
