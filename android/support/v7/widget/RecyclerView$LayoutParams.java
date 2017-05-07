// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.view.ViewGroup$LayoutParams;
import android.util.AttributeSet;
import android.content.Context;
import android.graphics.Rect;
import android.view.ViewGroup$MarginLayoutParams;

public class RecyclerView$LayoutParams extends ViewGroup$MarginLayoutParams
{
    final Rect mDecorInsets;
    boolean mInsetsDirty;
    boolean mPendingInvalidate;
    RecyclerView$ViewHolder mViewHolder;
    
    public RecyclerView$LayoutParams(final int n, final int n2) {
        super(n, n2);
        this.mDecorInsets = new Rect();
        this.mInsetsDirty = true;
        this.mPendingInvalidate = false;
    }
    
    public RecyclerView$LayoutParams(final Context context, final AttributeSet set) {
        super(context, set);
        this.mDecorInsets = new Rect();
        this.mInsetsDirty = true;
        this.mPendingInvalidate = false;
    }
    
    public RecyclerView$LayoutParams(final RecyclerView$LayoutParams recyclerView$LayoutParams) {
        super((ViewGroup$LayoutParams)recyclerView$LayoutParams);
        this.mDecorInsets = new Rect();
        this.mInsetsDirty = true;
        this.mPendingInvalidate = false;
    }
    
    public RecyclerView$LayoutParams(final ViewGroup$LayoutParams viewGroup$LayoutParams) {
        super(viewGroup$LayoutParams);
        this.mDecorInsets = new Rect();
        this.mInsetsDirty = true;
        this.mPendingInvalidate = false;
    }
    
    public RecyclerView$LayoutParams(final ViewGroup$MarginLayoutParams viewGroup$MarginLayoutParams) {
        super(viewGroup$MarginLayoutParams);
        this.mDecorInsets = new Rect();
        this.mInsetsDirty = true;
        this.mPendingInvalidate = false;
    }
    
    public int getViewPosition() {
        return this.mViewHolder.getPosition();
    }
    
    public boolean isItemChanged() {
        return this.mViewHolder.isChanged();
    }
    
    public boolean isItemRemoved() {
        return this.mViewHolder.isRemoved();
    }
}
