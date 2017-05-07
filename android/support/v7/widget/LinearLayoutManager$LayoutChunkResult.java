// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

public class LinearLayoutManager$LayoutChunkResult
{
    public int mConsumed;
    public boolean mFinished;
    public boolean mFocusable;
    public boolean mIgnoreConsumed;
    
    void resetInternal() {
        this.mConsumed = 0;
        this.mFinished = false;
        this.mIgnoreConsumed = false;
        this.mFocusable = false;
    }
}
