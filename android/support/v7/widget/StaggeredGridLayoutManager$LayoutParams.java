// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.view.ViewGroup$MarginLayoutParams;
import android.view.ViewGroup$LayoutParams;
import android.util.AttributeSet;
import android.content.Context;

public class StaggeredGridLayoutManager$LayoutParams extends RecyclerView$LayoutParams
{
    boolean mFullSpan;
    StaggeredGridLayoutManager$Span mSpan;
    
    public StaggeredGridLayoutManager$LayoutParams(final int n, final int n2) {
        super(n, n2);
    }
    
    public StaggeredGridLayoutManager$LayoutParams(final Context context, final AttributeSet set) {
        super(context, set);
    }
    
    public StaggeredGridLayoutManager$LayoutParams(final ViewGroup$LayoutParams viewGroup$LayoutParams) {
        super(viewGroup$LayoutParams);
    }
    
    public StaggeredGridLayoutManager$LayoutParams(final ViewGroup$MarginLayoutParams viewGroup$MarginLayoutParams) {
        super(viewGroup$MarginLayoutParams);
    }
    
    public final int getSpanIndex() {
        if (this.mSpan == null) {
            return -1;
        }
        return this.mSpan.mIndex;
    }
    
    public boolean isFullSpan() {
        return this.mFullSpan;
    }
}
