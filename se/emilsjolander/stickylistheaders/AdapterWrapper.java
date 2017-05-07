// 
// Decompiled by Procyon v0.5.30
// 

package se.emilsjolander.stickylistheaders;

import android.widget.Checkable;
import android.view.View$OnClickListener;
import android.view.ViewGroup;
import java.util.LinkedList;
import android.view.View;
import java.util.List;
import android.graphics.drawable.Drawable;
import android.database.DataSetObserver;
import android.content.Context;
import android.widget.BaseAdapter;

class AdapterWrapper extends BaseAdapter implements StickyListHeadersAdapter
{
    private final Context mContext;
    private DataSetObserver mDataSetObserver;
    final StickyListHeadersAdapter mDelegate;
    private Drawable mDivider;
    private int mDividerHeight;
    private final List<View> mHeaderCache;
    private OnHeaderClickListener mOnHeaderClickListener;
    
    AdapterWrapper(final Context mContext, final StickyListHeadersAdapter mDelegate) {
        this.mHeaderCache = new LinkedList<View>();
        this.mDataSetObserver = new DataSetObserver() {
            public void onChanged() {
                AdapterWrapper.access$201(AdapterWrapper.this);
            }
            
            public void onInvalidated() {
                AdapterWrapper.this.mHeaderCache.clear();
                AdapterWrapper.access$101(AdapterWrapper.this);
            }
        };
        this.mContext = mContext;
        (this.mDelegate = mDelegate).registerDataSetObserver(this.mDataSetObserver);
    }
    
    static /* synthetic */ void access$101(final AdapterWrapper adapterWrapper) {
        adapterWrapper.notifyDataSetInvalidated();
    }
    
    static /* synthetic */ void access$201(final AdapterWrapper adapterWrapper) {
        adapterWrapper.notifyDataSetChanged();
    }
    
    private View configureHeader(final WrapperView wrapperView, final int n) {
        View view;
        if (wrapperView.mHeader == null) {
            view = this.popHeader();
        }
        else {
            view = wrapperView.mHeader;
        }
        final View headerView = this.mDelegate.getHeaderView(n, view, wrapperView);
        if (headerView == null) {
            throw new NullPointerException("Header view must not be null.");
        }
        headerView.setClickable(true);
        headerView.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                if (AdapterWrapper.this.mOnHeaderClickListener != null) {
                    AdapterWrapper.this.mOnHeaderClickListener.onHeaderClick(view, n, AdapterWrapper.this.mDelegate.getHeaderId(n));
                }
            }
        });
        return headerView;
    }
    
    private View popHeader() {
        if (this.mHeaderCache.size() > 0) {
            return this.mHeaderCache.remove(0);
        }
        return null;
    }
    
    private boolean previousPositionHasSameHeader(final int n) {
        return n != 0 && this.mDelegate.getHeaderId(n) == this.mDelegate.getHeaderId(n - 1);
    }
    
    private void recycleHeaderIfExists(final WrapperView wrapperView) {
        final View mHeader = wrapperView.mHeader;
        if (mHeader != null) {
            mHeader.setVisibility(0);
            this.mHeaderCache.add(mHeader);
        }
    }
    
    public boolean areAllItemsEnabled() {
        return this.mDelegate.areAllItemsEnabled();
    }
    
    public boolean equals(final Object o) {
        return this.mDelegate.equals(o);
    }
    
    public int getCount() {
        return this.mDelegate.getCount();
    }
    
    public View getDropDownView(final int n, final View view, final ViewGroup viewGroup) {
        return ((BaseAdapter)this.mDelegate).getDropDownView(n, view, viewGroup);
    }
    
    public long getHeaderId(final int n) {
        return this.mDelegate.getHeaderId(n);
    }
    
    public View getHeaderView(final int n, final View view, final ViewGroup viewGroup) {
        return this.mDelegate.getHeaderView(n, view, viewGroup);
    }
    
    public Object getItem(final int n) {
        return this.mDelegate.getItem(n);
    }
    
    public long getItemId(final int n) {
        return this.mDelegate.getItemId(n);
    }
    
    public int getItemViewType(final int n) {
        return this.mDelegate.getItemViewType(n);
    }
    
    public WrapperView getView(final int n, final View view, final ViewGroup viewGroup) {
        WrapperView wrapperView;
        if (view == null) {
            wrapperView = new WrapperView(this.mContext);
        }
        else {
            wrapperView = (WrapperView)view;
        }
        final View view2 = this.mDelegate.getView(n, wrapperView.mItem, viewGroup);
        View configureHeader = null;
        if (this.previousPositionHasSameHeader(n)) {
            this.recycleHeaderIfExists(wrapperView);
        }
        else {
            configureHeader = this.configureHeader(wrapperView, n);
        }
        WrapperView wrapperView2;
        if (view2 instanceof Checkable && !(wrapperView instanceof CheckableWrapperView)) {
            wrapperView2 = new CheckableWrapperView(this.mContext);
        }
        else {
            wrapperView2 = wrapperView;
            if (!(view2 instanceof Checkable)) {
                wrapperView2 = wrapperView;
                if (wrapperView instanceof CheckableWrapperView) {
                    wrapperView2 = new WrapperView(this.mContext);
                }
            }
        }
        wrapperView2.update(view2, configureHeader, this.mDivider, this.mDividerHeight);
        return wrapperView2;
    }
    
    public int getViewTypeCount() {
        return this.mDelegate.getViewTypeCount();
    }
    
    public boolean hasStableIds() {
        return this.mDelegate.hasStableIds();
    }
    
    public int hashCode() {
        return this.mDelegate.hashCode();
    }
    
    public boolean isEmpty() {
        return this.mDelegate.isEmpty();
    }
    
    public boolean isEnabled(final int n) {
        return this.mDelegate.isEnabled(n);
    }
    
    public void notifyDataSetChanged() {
        ((BaseAdapter)this.mDelegate).notifyDataSetChanged();
    }
    
    public void notifyDataSetInvalidated() {
        ((BaseAdapter)this.mDelegate).notifyDataSetInvalidated();
    }
    
    void setDivider(final Drawable mDivider, final int mDividerHeight) {
        this.mDivider = mDivider;
        this.mDividerHeight = mDividerHeight;
        this.notifyDataSetChanged();
    }
    
    public void setOnHeaderClickListener(final OnHeaderClickListener mOnHeaderClickListener) {
        this.mOnHeaderClickListener = mOnHeaderClickListener;
    }
    
    public String toString() {
        return this.mDelegate.toString();
    }
    
    interface OnHeaderClickListener
    {
        void onHeaderClick(final View p0, final int p1, final long p2);
    }
}
