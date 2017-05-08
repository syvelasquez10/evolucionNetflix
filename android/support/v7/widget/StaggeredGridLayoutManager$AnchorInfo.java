// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

class StaggeredGridLayoutManager$AnchorInfo
{
    boolean mInvalidateOffsets;
    boolean mLayoutFromEnd;
    int mOffset;
    int mPosition;
    boolean mValid;
    final /* synthetic */ StaggeredGridLayoutManager this$0;
    
    public StaggeredGridLayoutManager$AnchorInfo(final StaggeredGridLayoutManager this$0) {
        this.this$0 = this$0;
        this.reset();
    }
    
    void assignCoordinateFromPadding() {
        int mOffset;
        if (this.mLayoutFromEnd) {
            mOffset = this.this$0.mPrimaryOrientation.getEndAfterPadding();
        }
        else {
            mOffset = this.this$0.mPrimaryOrientation.getStartAfterPadding();
        }
        this.mOffset = mOffset;
    }
    
    void assignCoordinateFromPadding(final int n) {
        if (this.mLayoutFromEnd) {
            this.mOffset = this.this$0.mPrimaryOrientation.getEndAfterPadding() - n;
            return;
        }
        this.mOffset = this.this$0.mPrimaryOrientation.getStartAfterPadding() + n;
    }
    
    void reset() {
        this.mPosition = -1;
        this.mOffset = Integer.MIN_VALUE;
        this.mLayoutFromEnd = false;
        this.mInvalidateOffsets = false;
        this.mValid = false;
    }
}
