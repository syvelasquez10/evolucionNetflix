// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

class DefaultItemAnimator$MoveInfo
{
    public int fromX;
    public int fromY;
    public RecyclerView$ViewHolder holder;
    public int toX;
    public int toY;
    
    private DefaultItemAnimator$MoveInfo(final RecyclerView$ViewHolder holder, final int fromX, final int fromY, final int toX, final int toY) {
        this.holder = holder;
        this.fromX = fromX;
        this.fromY = fromY;
        this.toX = toX;
        this.toY = toY;
    }
}
