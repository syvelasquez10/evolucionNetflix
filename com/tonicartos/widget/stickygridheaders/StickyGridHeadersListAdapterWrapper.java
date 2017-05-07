// 
// Decompiled by Procyon v0.5.30
// 

package com.tonicartos.widget.stickygridheaders;

import android.view.ViewGroup;
import android.view.View;
import android.widget.ListAdapter;
import android.database.DataSetObserver;
import android.widget.BaseAdapter;

public class StickyGridHeadersListAdapterWrapper extends BaseAdapter implements StickyGridHeadersBaseAdapter
{
    private DataSetObserver mDataSetObserver;
    private ListAdapter mDelegate;
    
    public StickyGridHeadersListAdapterWrapper(final ListAdapter mDelegate) {
        this.mDataSetObserver = new StickyGridHeadersListAdapterWrapper$1(this);
        this.mDelegate = mDelegate;
        if (mDelegate != null) {
            mDelegate.registerDataSetObserver(this.mDataSetObserver);
        }
    }
    
    public int getCount() {
        if (this.mDelegate == null) {
            return 0;
        }
        return this.mDelegate.getCount();
    }
    
    public int getCountForHeader(final int n) {
        return 0;
    }
    
    public View getHeaderView(final int n, final View view, final ViewGroup viewGroup) {
        return null;
    }
    
    public Object getItem(final int n) {
        if (this.mDelegate == null) {
            return null;
        }
        return this.mDelegate.getItem(n);
    }
    
    public long getItemId(final int n) {
        return this.mDelegate.getItemId(n);
    }
    
    public int getItemViewType(final int n) {
        return this.mDelegate.getItemViewType(n);
    }
    
    public int getNumHeaders() {
        return 0;
    }
    
    public View getView(final int n, final View view, final ViewGroup viewGroup) {
        return this.mDelegate.getView(n, view, viewGroup);
    }
    
    public int getViewTypeCount() {
        return this.mDelegate.getViewTypeCount();
    }
    
    public boolean hasStableIds() {
        return this.mDelegate.hasStableIds();
    }
}
