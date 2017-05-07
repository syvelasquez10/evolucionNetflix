// 
// Decompiled by Procyon v0.5.30
// 

package com.tonicartos.widget.stickygridheaders;

import android.widget.FrameLayout$LayoutParams;
import android.view.ViewGroup$LayoutParams;
import android.widget.FrameLayout;
import android.view.View$MeasureSpec;
import android.util.AttributeSet;
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
        this.mDataSetObserver = new DataSetObserver() {
            public void onChanged() {
                StickyGridHeadersBaseAdapterWrapper.this.updateCount();
                StickyGridHeadersBaseAdapterWrapper.this.notifyDataSetChanged();
            }
            
            public void onInvalidated() {
                StickyGridHeadersBaseAdapterWrapper.this.mCounted = false;
                StickyGridHeadersBaseAdapterWrapper.this.notifyDataSetInvalidated();
            }
        };
        this.mNumColumns = 1;
        this.mContext = mContext;
        this.mDelegate = mDelegate;
        this.mGridView = mGridView;
        mDelegate.registerDataSetObserver(this.mDataSetObserver);
    }
    
    private FillerView getFillerView(final View view, final ViewGroup viewGroup, final View measureTarget) {
        FillerView fillerView;
        if ((fillerView = (FillerView)view) == null) {
            fillerView = new FillerView(this.mContext);
        }
        fillerView.setMeasureTarget(measureTarget);
        return fillerView;
    }
    
    private HeaderFillerView getHeaderFillerView(final int n, final View view, final ViewGroup viewGroup) {
        HeaderFillerView headerFillerView;
        if ((headerFillerView = (HeaderFillerView)view) == null) {
            headerFillerView = new HeaderFillerView(this.mContext);
        }
        return headerFillerView;
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
        for (int i = 0; i < numHeaders; ++i) {
            this.mCount += this.mDelegate.getCountForHeader(i) + this.unFilledSpacesInHeaderGroup(i) + this.mNumColumns;
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
    
    public Object getItem(final int n) throws ArrayIndexOutOfBoundsException {
        final Position translatePosition = this.translatePosition(n);
        if (translatePosition.mPosition == -1 || translatePosition.mPosition == -2) {
            return null;
        }
        return this.mDelegate.getItem(translatePosition.mPosition);
    }
    
    public long getItemId(final int n) {
        final Position translatePosition = this.translatePosition(n);
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
        final Position translatePosition = this.translatePosition(n);
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
        final Position translatePosition = this.translatePosition(n);
        if (translatePosition.mPosition == -2) {
            final HeaderFillerView headerFillerView = this.getHeaderFillerView(translatePosition.mHeader, view, viewGroup);
            final View headerView = this.mDelegate.getHeaderView(translatePosition.mHeader, (View)headerFillerView.getTag(), viewGroup);
            this.mGridView.detachHeader((View)headerFillerView.getTag());
            headerFillerView.setTag((Object)headerView);
            this.mGridView.attachHeader(headerView);
            ((HeaderFillerView)(this.mLastHeaderViewSeen = (View)headerFillerView)).forceLayout();
            return (View)headerFillerView;
        }
        if (translatePosition.mPosition == -3) {
            final FillerView fillerView = this.getFillerView(view, viewGroup, this.mLastHeaderViewSeen);
            fillerView.forceLayout();
            return fillerView;
        }
        if (translatePosition.mPosition == -1) {
            final FillerView fillerView2 = this.getFillerView(view, viewGroup, this.mLastViewSeen);
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
        final Position translatePosition = this.translatePosition(n);
        return translatePosition.mPosition != -1 && translatePosition.mPosition != -2 && this.mDelegate.isEnabled(translatePosition.mPosition);
    }
    
    public void registerDataSetObserver(final DataSetObserver dataSetObserver) {
        this.mDelegate.registerDataSetObserver(dataSetObserver);
    }
    
    public void setNumColumns(final int mNumColumns) {
        this.mNumColumns = mNumColumns;
        this.mCounted = false;
    }
    
    protected Position translatePosition(int i) {
        final int numHeaders = this.mDelegate.getNumHeaders();
        if (numHeaders != 0) {
            int n = i;
            final int n2 = 0;
            int n3 = i;
            int countForHeader;
            int n4;
            int n5;
            int unFilledSpacesInHeaderGroup;
            for (i = n2; i < numHeaders; ++i) {
                countForHeader = this.mDelegate.getCountForHeader(i);
                if (n3 == 0) {
                    return new Position(-2, i);
                }
                n4 = n3 - this.mNumColumns;
                if (n4 < 0) {
                    return new Position(-3, i);
                }
                n5 = n - this.mNumColumns;
                if (n4 < countForHeader) {
                    return new Position(n5, i);
                }
                unFilledSpacesInHeaderGroup = this.unFilledSpacesInHeaderGroup(i);
                n = n5 - unFilledSpacesInHeaderGroup;
                n3 = n4 - (countForHeader + unFilledSpacesInHeaderGroup);
                if (n3 < 0) {
                    return new Position(-1, i);
                }
            }
            return new Position(-1, i);
        }
        if (i >= this.mDelegate.getCount()) {
            return new Position(-1, 0);
        }
        return new Position(i, 0);
    }
    
    public void unregisterDataSetObserver(final DataSetObserver dataSetObserver) {
        this.mDelegate.unregisterDataSetObserver(dataSetObserver);
    }
    
    protected void updateCount() {
        this.mCount = 0;
        final int numHeaders = this.mDelegate.getNumHeaders();
        if (numHeaders == 0) {
            this.mCount = this.mDelegate.getCount();
            this.mCounted = true;
            return;
        }
        for (int i = 0; i < numHeaders; ++i) {
            this.mCount += this.mDelegate.getCountForHeader(i) + this.mNumColumns;
        }
        this.mCounted = true;
    }
    
    protected class FillerView extends View
    {
        private View mMeasureTarget;
        
        public FillerView(final Context context) {
            super(context);
        }
        
        public FillerView(final Context context, final AttributeSet set) {
            super(context, set);
        }
        
        public FillerView(final Context context, final AttributeSet set, final int n) {
            super(context, set, n);
        }
        
        protected void onMeasure(final int n, final int n2) {
            super.onMeasure(n, View$MeasureSpec.makeMeasureSpec(this.mMeasureTarget.getMeasuredHeight(), 1073741824));
        }
        
        public void setMeasureTarget(final View mMeasureTarget) {
            this.mMeasureTarget = mMeasureTarget;
        }
    }
    
    protected class HeaderFillerView extends FrameLayout
    {
        private int mHeaderId;
        
        public HeaderFillerView(final Context context) {
            super(context);
        }
        
        public HeaderFillerView(final Context context, final AttributeSet set) {
            super(context, set);
        }
        
        public HeaderFillerView(final Context context, final AttributeSet set, final int n) {
            super(context, set, n);
        }
        
        protected FrameLayout$LayoutParams generateDefaultLayoutParams() {
            return new FrameLayout$LayoutParams(-1, -1);
        }
        
        public int getHeaderId() {
            return this.mHeaderId;
        }
        
        protected void onMeasure(final int n, int childMeasureSpec) {
            final View view = (View)this.getTag();
            Object layoutParams;
            if ((layoutParams = view.getLayoutParams()) == null) {
                layoutParams = this.generateDefaultLayoutParams();
                view.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
            }
            if (view.getVisibility() != 8) {
                childMeasureSpec = getChildMeasureSpec(View$MeasureSpec.makeMeasureSpec(0, 0), 0, ((ViewGroup$LayoutParams)layoutParams).height);
                view.measure(getChildMeasureSpec(View$MeasureSpec.makeMeasureSpec(StickyGridHeadersBaseAdapterWrapper.this.mGridView.getWidth(), 1073741824), 0, ((ViewGroup$LayoutParams)layoutParams).width), childMeasureSpec);
            }
            this.setMeasuredDimension(View$MeasureSpec.getSize(n), view.getMeasuredHeight());
        }
        
        public void setHeaderId(final int mHeaderId) {
            this.mHeaderId = mHeaderId;
        }
    }
    
    protected class HeaderHolder
    {
        protected View mHeaderView;
    }
    
    protected class Position
    {
        protected int mHeader;
        protected int mPosition;
        
        protected Position(final int mPosition, final int mHeader) {
            this.mPosition = mPosition;
            this.mHeader = mHeader;
        }
    }
}
