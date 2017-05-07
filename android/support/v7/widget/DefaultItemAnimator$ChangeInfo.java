// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

class DefaultItemAnimator$ChangeInfo
{
    public int fromX;
    public int fromY;
    public RecyclerView$ViewHolder newHolder;
    public RecyclerView$ViewHolder oldHolder;
    public int toX;
    public int toY;
    
    private DefaultItemAnimator$ChangeInfo(final RecyclerView$ViewHolder oldHolder, final RecyclerView$ViewHolder newHolder) {
        this.oldHolder = oldHolder;
        this.newHolder = newHolder;
    }
    
    private DefaultItemAnimator$ChangeInfo(final RecyclerView$ViewHolder recyclerView$ViewHolder, final RecyclerView$ViewHolder recyclerView$ViewHolder2, final int fromX, final int fromY, final int toX, final int toY) {
        this(recyclerView$ViewHolder, recyclerView$ViewHolder2);
        this.fromX = fromX;
        this.fromY = fromY;
        this.toX = toX;
        this.toY = toY;
    }
    
    @Override
    public String toString() {
        return "ChangeInfo{oldHolder=" + this.oldHolder + ", newHolder=" + this.newHolder + ", fromX=" + this.fromX + ", fromY=" + this.fromY + ", toX=" + this.toX + ", toY=" + this.toY + '}';
    }
}
