// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.view.ViewGroup$MarginLayoutParams;
import android.view.ViewGroup$LayoutParams;
import android.util.AttributeSet;
import android.content.Context;

public class GridLayoutManager$LayoutParams extends RecyclerView$LayoutParams
{
    private int mSpanIndex;
    private int mSpanSize;
    
    public GridLayoutManager$LayoutParams(final int n, final int n2) {
        super(n, n2);
        this.mSpanIndex = -1;
        this.mSpanSize = 0;
    }
    
    public GridLayoutManager$LayoutParams(final Context context, final AttributeSet set) {
        super(context, set);
        this.mSpanIndex = -1;
        this.mSpanSize = 0;
    }
    
    public GridLayoutManager$LayoutParams(final ViewGroup$LayoutParams viewGroup$LayoutParams) {
        super(viewGroup$LayoutParams);
        this.mSpanIndex = -1;
        this.mSpanSize = 0;
    }
    
    public GridLayoutManager$LayoutParams(final ViewGroup$MarginLayoutParams viewGroup$MarginLayoutParams) {
        super(viewGroup$MarginLayoutParams);
        this.mSpanIndex = -1;
        this.mSpanSize = 0;
    }
    
    public int getSpanIndex() {
        return this.mSpanIndex;
    }
    
    public int getSpanSize() {
        return this.mSpanSize;
    }
}
