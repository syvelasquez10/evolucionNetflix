// 
// Decompiled by Procyon v0.5.30
// 

package com.tonicartos.widget.stickygridheaders;

import android.view.ViewGroup;
import android.view.View;
import android.database.DataSetObserver;
import android.content.Context;
import android.widget.BaseAdapter;

public class StickyGridHeadersBaseAdapterWrapper extends BaseAdapter
{
    protected static final int ID_FILLER = -2;
    protected static final int ID_HEADER = -1;
    protected static final int ID_HEADER_FILLER = -3;
    protected static final int POSITION_FILLER = -1;
    protected static final int POSITION_HEADER = -2;
    protected static final int POSITION_HEADER_FILLER = -3;
    protected static final int VIEW_TYPE_FILLER = 0;
    protected static final int VIEW_TYPE_HEADER = 1;
    protected static final int VIEW_TYPE_HEADER_FILLER = 2;
    private static final int sNumViewTypes = 3;
    private final Context mContext;
    private int mCount;
    private boolean mCounted;
    private final DataSetObserver mDataSetObserver;
    private final StickyGridHeadersBaseAdapter mDelegate;
    private final StickyGridHeadersGridView mGridView;
    private View mLastHeaderViewSeen;
    private View mLastViewSeen;
    private int mNumColumns;
    
    public StickyGridHeadersBaseAdapterWrapper(final Context mContext, final StickyGridHeadersGridView mGridView, final StickyGridHeadersBaseAdapter mDelegate) {
        this.mCounted = false;
        this.mDataSetObserver = new StickyGridHeadersBaseAdapterWrapper$1(this);
        this.mNumColumns = 1;
        this.mContext = mContext;
        this.mDelegate = mDelegate;
        this.mGridView = mGridView;
        mDelegate.registerDataSetObserver(this.mDataSetObserver);
    }
    
    private StickyGridHeadersBaseAdapterWrapper$FillerView getFillerView(View view, final ViewGroup viewGroup, final View measureTarget) {
        if (view == null || !(view instanceof StickyGridHeadersBaseAdapterWrapper$FillerView)) {
            view = new StickyGridHeadersBaseAdapterWrapper$FillerView(this, this.mContext);
        }
        final StickyGridHeadersBaseAdapterWrapper$FillerView stickyGridHeadersBaseAdapterWrapper$FillerView = (StickyGridHeadersBaseAdapterWrapper$FillerView)view;
        stickyGridHeadersBaseAdapterWrapper$FillerView.setMeasureTarget(measureTarget);
        return stickyGridHeadersBaseAdapterWrapper$FillerView;
    }
    
    private StickyGridHeadersBaseAdapterWrapper$HeaderFillerView getHeaderFillerView(final int n, View view, final ViewGroup viewGroup) {
        if (view == null || !(view instanceof StickyGridHeadersBaseAdapterWrapper$HeaderFillerView)) {
            view = (View)new StickyGridHeadersBaseAdapterWrapper$HeaderFillerView(this, this.mContext);
        }
        return (StickyGridHeadersBaseAdapterWrapper$HeaderFillerView)view;
    }
    
    private int unFilledSpacesInHeaderGroup(int n) {
        n = this.mDelegate.getCountForHeader(n) % this.mNumColumns;
        if (n == 0) {
            return 0;
        }
        return this.mNumColumns - n;
    }
    
    public boolean areAllItemsEnabled() {
        return false;
    }
    
    public int getCount() {
        int i = 0;
        if (this.mCounted) {
            return this.mCount;
        }
        this.mCount = 0;
        final int numHeaders = this.mDelegate.getNumHeaders();
        if (numHeaders == 0) {
            this.mCount = this.mDelegate.getCount();
            this.mCounted = true;
            return this.mCount;
        }
        while (i < numHeaders) {
            this.mCount += this.mDelegate.getCountForHeader(i) + this.unFilledSpacesInHeaderGroup(i) + this.mNumColumns;
            ++i;
        }
        this.mCounted = true;
        return this.mCount;
    }
    
    protected long getHeaderId(final int n) {
        return this.translatePosition(n).mHeader;
    }
    
    protected View getHeaderView(final int n, final View view, final ViewGroup viewGroup) {
        if (this.mDelegate.getNumHeaders() == 0) {
            return null;
        }
        return this.mDelegate.getHeaderView(this.translatePosition(n).mHeader, view, viewGroup);
    }
    
    public Object getItem(final int n) {
        final StickyGridHeadersBaseAdapterWrapper$Position translatePosition = this.translatePosition(n);
        if (translatePosition.mPosition == -1 || translatePosition.mPosition == -2) {
            return null;
        }
        return this.mDelegate.getItem(translatePosition.mPosition);
    }
    
    public long getItemId(final int n) {
        final StickyGridHeadersBaseAdapterWrapper$Position translatePosition = this.translatePosition(n);
        if (translatePosition.mPosition == -2) {
            return -1L;
        }
        if (translatePosition.mPosition == -1) {
            return -2L;
        }
        if (translatePosition.mPosition == -3) {
            return -3L;
        }
        return this.mDelegate.getItemId(translatePosition.mPosition);
    }
    
    public int getItemViewType(int n) {
        final StickyGridHeadersBaseAdapterWrapper$Position translatePosition = this.translatePosition(n);
        if (translatePosition.mPosition == -2) {
            n = 1;
        }
        else {
            if (translatePosition.mPosition == -1) {
                return 0;
            }
            if (translatePosition.mPosition == -3) {
                return 2;
            }
            final int itemViewType = this.mDelegate.getItemViewType(translatePosition.mPosition);
            if ((n = itemViewType) != -1) {
                return itemViewType + 3;
            }
        }
        return n;
    }
    
    public View getView(final int n, View view, final ViewGroup viewGroup) {
        final StickyGridHeadersBaseAdapterWrapper$Position translatePosition = this.translatePosition(n);
        if (translatePosition.mPosition == -2) {
            final StickyGridHeadersBaseAdapterWrapper$HeaderFillerView headerFillerView = this.getHeaderFillerView(translatePosition.mHeader, view, viewGroup);
            final View headerView = this.mDelegate.getHeaderView(translatePosition.mHeader, (View)headerFillerView.getTag(), viewGroup);
            this.mGridView.detachHeader((View)headerFillerView.getTag());
            headerFillerView.setTag((Object)headerView);
            this.mGridView.attachHeader(headerView);
            ((StickyGridHeadersBaseAdapterWrapper$HeaderFillerView)(this.mLastHeaderViewSeen = (View)headerFillerView)).forceLayout();
            return (View)headerFillerView;
        }
        if (translatePosition.mPosition == -3) {
            final StickyGridHeadersBaseAdapterWrapper$FillerView fillerView = this.getFillerView(view, viewGroup, this.mLastHeaderViewSeen);
            fillerView.forceLayout();
            return fillerView;
        }
        if (translatePosition.mPosition == -1) {
            final StickyGridHeadersBaseAdapterWrapper$FillerView fillerView2 = this.getFillerView(view, viewGroup, this.mLastViewSeen);
            fillerView2.forceLayout();
            return fillerView2;
        }
        view = this.mDelegate.getView(translatePosition.mPosition, view, viewGroup);
        return this.mLastViewSeen = view;
    }
    
    public int getViewTypeCount() {
        return this.mDelegate.getViewTypeCount() + 3;
    }
    
    public StickyGridHeadersBaseAdapter getWrappedAdapter() {
        return this.mDelegate;
    }
    
    public boolean hasStableIds() {
        return this.mDelegate.hasStableIds();
    }
    
    public boolean isEmpty() {
        return this.mDelegate.isEmpty();
    }
    
    public boolean isEnabled(final int n) {
        final StickyGridHeadersBaseAdapterWrapper$Position translatePosition = this.translatePosition(n);
        return translatePosition.mPosition != -1 && translatePosition.mPosition != -2 && this.mDelegate.isEnabled(translatePosition.mPosition);
    }
    
    public void registerDataSetObserver(final DataSetObserver dataSetObserver) {
        this.mDelegate.registerDataSetObserver(dataSetObserver);
    }
    
    public void setNumColumns(final int mNumColumns) {
        this.mNumColumns = mNumColumns;
        this.mCounted = false;
    }
    
    protected StickyGridHeadersBaseAdapterWrapper$Position translatePosition(int i) {
        final int n = 0;
        final int numHeaders = this.mDelegate.getNumHeaders();
        if (numHeaders != 0) {
            int n2 = i;
            int n3 = i;
            int countForHeader;
            int n4;
            int n5;
            int unFilledSpacesInHeaderGroup;
            for (i = n; i < numHeaders; ++i, n2 = n5 - unFilledSpacesInHeaderGroup) {
                countForHeader = this.mDelegate.getCountForHeader(i);
                if (n3 == 0) {
                    return new StickyGridHeadersBaseAdapterWrapper$Position(this, -2, i);
                }
                n4 = n3 - this.mNumColumns;
                if (n4 < 0) {
                    return new StickyGridHeadersBaseAdapterWrapper$Position(this, -3, i);
                }
                n5 = n2 - this.mNumColumns;
                if (n4 < countForHeader) {
                    return new StickyGridHeadersBaseAdapterWrapper$Position(this, n5, i);
                }
                unFilledSpacesInHeaderGroup = this.unFilledSpacesInHeaderGroup(i);
                n3 = n4 - (unFilledSpacesInHeaderGroup + countForHeader);
                if (n3 < 0) {
                    return new StickyGridHeadersBaseAdapterWrapper$Position(this, -1, i);
                }
            }
            return new StickyGridHeadersBaseAdapterWrapper$Position(this, -1, i);
        }
        if (i >= this.mDelegate.getCount()) {
            return new StickyGridHeadersBaseAdapterWrapper$Position(this, -1, 0);
        }
        return new StickyGridHeadersBaseAdapterWrapper$Position(this, i, 0);
    }
    
    public void unregisterDataSetObserver(final DataSetObserver dataSetObserver) {
        this.mDelegate.unregisterDataSetObserver(dataSetObserver);
    }
    
    protected void updateCount() {
        int i = 0;
        this.mCount = 0;
        final int numHeaders = this.mDelegate.getNumHeaders();
        if (numHeaders == 0) {
            this.mCount = this.mDelegate.getCount();
            this.mCounted = true;
            return;
        }
        while (i < numHeaders) {
            this.mCount += this.mDelegate.getCountForHeader(i) + this.mNumColumns;
            ++i;
        }
        this.mCounted = true;
    }
}
