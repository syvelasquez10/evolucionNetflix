// 
// Decompiled by Procyon v0.5.30
// 

package se.emilsjolander.stickylistheaders;

import android.content.res.TypedArray;
import android.widget.AbsListView$RecyclerListener;
import android.view.View$OnTouchListener;
import android.view.View$OnCreateContextMenuListener;
import android.widget.SectionIndexer;
import android.database.DataSetObserver;
import android.widget.ListAdapter;
import android.view.View$BaseSavedState;
import android.os.Parcelable;
import android.widget.ListView;
import android.annotation.TargetApi;
import android.view.View$OnClickListener;
import android.annotation.SuppressLint;
import android.view.ViewGroup$MarginLayoutParams;
import android.util.Log;
import android.view.View$MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup$LayoutParams;
import android.widget.FrameLayout$LayoutParams;
import android.graphics.Canvas;
import android.os.Build$VERSION;
import android.util.AttributeSet;
import android.content.Context;
import android.widget.AbsListView$OnScrollListener;
import android.view.View;
import android.graphics.drawable.Drawable;
import android.widget.FrameLayout;

public class StickyListHeadersListView extends FrameLayout
{
    private AdapterWrapper mAdapter;
    private boolean mAreHeadersSticky;
    private boolean mClippingToPadding;
    private StickyListHeadersListView$AdapterWrapperDataSetObserver mDataSetObserver;
    private Drawable mDivider;
    private int mDividerHeight;
    private View mHeader;
    private Long mHeaderId;
    private Integer mHeaderOffset;
    private Integer mHeaderPosition;
    private boolean mIsDrawingListUnderStickyHeader;
    private WrapperViewList mList;
    private StickyListHeadersListView$OnHeaderClickListener mOnHeaderClickListener;
    private AbsListView$OnScrollListener mOnScrollListenerDelegate;
    private StickyListHeadersListView$OnStickyHeaderChangedListener mOnStickyHeaderChangedListener;
    private StickyListHeadersListView$OnStickyHeaderOffsetChangedListener mOnStickyHeaderOffsetChangedListener;
    private int mPaddingBottom;
    private int mPaddingLeft;
    private int mPaddingRight;
    private int mPaddingTop;
    
    public StickyListHeadersListView(final Context context) {
        this(context, null);
    }
    
    public StickyListHeadersListView(final Context context, final AttributeSet set) {
        this(context, set, 0);
    }
    
    public StickyListHeadersListView(Context obtainStyledAttributes, final AttributeSet set, int n) {
        final boolean b = true;
        super(obtainStyledAttributes, set, n);
        this.mAreHeadersSticky = true;
        this.mClippingToPadding = true;
        this.mIsDrawingListUnderStickyHeader = true;
        this.mPaddingLeft = 0;
        this.mPaddingTop = 0;
        this.mPaddingRight = 0;
        this.mPaddingBottom = 0;
        this.mList = new WrapperViewList(obtainStyledAttributes);
        this.mDivider = this.mList.getDivider();
        this.mDividerHeight = this.mList.getDividerHeight();
        this.mList.setDivider((Drawable)null);
        this.mList.setDividerHeight(0);
        Label_0573: {
            if (set == null) {
                break Label_0573;
            }
            while (true) {
                obtainStyledAttributes = (Context)obtainStyledAttributes.getTheme().obtainStyledAttributes(set, R$styleable.StickyListHeadersListView, 0, 0);
                while (true) {
                    Label_0659: {
                        try {
                            n = ((TypedArray)obtainStyledAttributes).getDimensionPixelSize(1, 0);
                            this.mPaddingLeft = ((TypedArray)obtainStyledAttributes).getDimensionPixelSize(2, n);
                            this.mPaddingTop = ((TypedArray)obtainStyledAttributes).getDimensionPixelSize(3, n);
                            this.mPaddingRight = ((TypedArray)obtainStyledAttributes).getDimensionPixelSize(4, n);
                            this.mPaddingBottom = ((TypedArray)obtainStyledAttributes).getDimensionPixelSize(5, n);
                            this.setPadding(this.mPaddingLeft, this.mPaddingTop, this.mPaddingRight, this.mPaddingBottom);
                            this.mClippingToPadding = ((TypedArray)obtainStyledAttributes).getBoolean(8, true);
                            super.setClipToPadding(true);
                            this.mList.setClipToPadding(this.mClippingToPadding);
                            n = ((TypedArray)obtainStyledAttributes).getInt(6, 512);
                            this.mList.setVerticalScrollBarEnabled((n & 0x200) != 0x0);
                            this.mList.setHorizontalScrollBarEnabled((n & 0x100) != 0x0 && b);
                            if (Build$VERSION.SDK_INT >= 9) {
                                this.mList.setOverScrollMode(((TypedArray)obtainStyledAttributes).getInt(18, 0));
                            }
                            this.mList.setFadingEdgeLength(((TypedArray)obtainStyledAttributes).getDimensionPixelSize(7, this.mList.getVerticalFadingEdgeLength()));
                            n = ((TypedArray)obtainStyledAttributes).getInt(20, 0);
                            if (n == 4096) {
                                this.mList.setVerticalFadingEdgeEnabled(false);
                                this.mList.setHorizontalFadingEdgeEnabled(true);
                            }
                            else {
                                if (n != 8192) {
                                    break Label_0659;
                                }
                                this.mList.setVerticalFadingEdgeEnabled(true);
                                this.mList.setHorizontalFadingEdgeEnabled(false);
                            }
                            this.mList.setCacheColorHint(((TypedArray)obtainStyledAttributes).getColor(13, this.mList.getCacheColorHint()));
                            if (Build$VERSION.SDK_INT >= 11) {
                                this.mList.setChoiceMode(((TypedArray)obtainStyledAttributes).getInt(16, this.mList.getChoiceMode()));
                            }
                            this.mList.setDrawSelectorOnTop(((TypedArray)obtainStyledAttributes).getBoolean(10, false));
                            this.mList.setFastScrollEnabled(((TypedArray)obtainStyledAttributes).getBoolean(17, this.mList.isFastScrollEnabled()));
                            if (Build$VERSION.SDK_INT >= 11) {
                                this.mList.setFastScrollAlwaysVisible(((TypedArray)obtainStyledAttributes).getBoolean(19, this.mList.isFastScrollAlwaysVisible()));
                            }
                            this.mList.setScrollBarStyle(((TypedArray)obtainStyledAttributes).getInt(0, 0));
                            if (((TypedArray)obtainStyledAttributes).hasValue(9)) {
                                this.mList.setSelector(((TypedArray)obtainStyledAttributes).getDrawable(9));
                            }
                            this.mList.setScrollingCacheEnabled(((TypedArray)obtainStyledAttributes).getBoolean(11, this.mList.isScrollingCacheEnabled()));
                            if (((TypedArray)obtainStyledAttributes).hasValue(14)) {
                                this.mDivider = ((TypedArray)obtainStyledAttributes).getDrawable(14);
                            }
                            this.mDividerHeight = ((TypedArray)obtainStyledAttributes).getDimensionPixelSize(15, this.mDividerHeight);
                            this.mList.setTranscriptMode(((TypedArray)obtainStyledAttributes).getInt(12, 0));
                            this.mAreHeadersSticky = ((TypedArray)obtainStyledAttributes).getBoolean(21, true);
                            this.mIsDrawingListUnderStickyHeader = ((TypedArray)obtainStyledAttributes).getBoolean(22, true);
                            ((TypedArray)obtainStyledAttributes).recycle();
                            this.mList.setLifeCycleListener(new StickyListHeadersListView$WrapperViewListLifeCycleListener(this, null));
                            this.mList.setOnScrollListener((AbsListView$OnScrollListener)new StickyListHeadersListView$WrapperListScrollListener(this, null));
                            this.addView((View)this.mList);
                            return;
                        }
                        finally {
                            ((TypedArray)obtainStyledAttributes).recycle();
                        }
                    }
                    this.mList.setVerticalFadingEdgeEnabled(false);
                    this.mList.setHorizontalFadingEdgeEnabled(false);
                    continue;
                }
            }
        }
    }
    
    private void clearHeader() {
        if (this.mHeader != null) {
            this.removeView(this.mHeader);
            this.mHeader = null;
            this.mHeaderId = null;
            this.mHeaderPosition = null;
            this.mHeaderOffset = null;
            this.mList.setTopClippingLength(0);
            this.updateHeaderVisibilities();
        }
    }
    
    private void ensureHeaderHasCorrectLayoutParams(final View view) {
        final ViewGroup$LayoutParams layoutParams = view.getLayoutParams();
        Object layoutParams2;
        if (layoutParams == null) {
            layoutParams2 = new FrameLayout$LayoutParams(-1, -2);
        }
        else {
            layoutParams2 = layoutParams;
            if (layoutParams.height == -1) {
                layoutParams.height = -2;
                layoutParams2 = layoutParams;
            }
        }
        view.setLayoutParams((ViewGroup$LayoutParams)layoutParams2);
    }
    
    private int getHeaderOverlap(final int n) {
        int measuredHeight = 0;
        if (!this.isStartOfSection(Math.max(0, n - this.getHeaderViewsCount()))) {
            final View headerView = this.mAdapter.getHeaderView(n, null, (ViewGroup)this.mList);
            if (headerView == null) {
                throw new NullPointerException("header may not be null");
            }
            this.ensureHeaderHasCorrectLayoutParams(headerView);
            this.measureHeader(headerView);
            measuredHeight = headerView.getMeasuredHeight();
        }
        return measuredHeight;
    }
    
    private boolean isStartOfSection(final int n) {
        return n == 0 || this.mAdapter.getHeaderId(n) != this.mAdapter.getHeaderId(n - 1);
    }
    
    private void measureHeader(final View view) {
        if (view != null) {
            this.measureChild(view, View$MeasureSpec.makeMeasureSpec(this.getMeasuredWidth() - this.mPaddingLeft - this.mPaddingRight, 1073741824), View$MeasureSpec.makeMeasureSpec(0, 0));
        }
    }
    
    private boolean requireSdkVersion(final int n) {
        if (Build$VERSION.SDK_INT < n) {
            Log.e("StickyListHeaders", "Api lvl must be at least " + n + " to call this method");
            return false;
        }
        return true;
    }
    
    @SuppressLint({ "NewApi" })
    private void setHeaderOffet(final int n) {
        if (this.mHeaderOffset == null || this.mHeaderOffset != n) {
            this.mHeaderOffset = n;
            if (Build$VERSION.SDK_INT >= 11) {
                this.mHeader.setTranslationY((float)this.mHeaderOffset);
            }
            else {
                final ViewGroup$MarginLayoutParams layoutParams = (ViewGroup$MarginLayoutParams)this.mHeader.getLayoutParams();
                layoutParams.topMargin = this.mHeaderOffset;
                this.mHeader.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
            }
            if (this.mOnStickyHeaderOffsetChangedListener != null) {
                this.mOnStickyHeaderOffsetChangedListener.onStickyHeaderOffsetChanged(this, this.mHeader, -this.mHeaderOffset);
            }
        }
    }
    
    private void swapHeader(final View mHeader) {
        if (this.mHeader != null) {
            this.removeView(this.mHeader);
        }
        this.addView(this.mHeader = mHeader);
        this.mHeader.setOnClickListener((View$OnClickListener)new StickyListHeadersListView$1(this));
    }
    
    private void updateHeader(int mPaddingTop) {
        final boolean b = false;
        if (this.mHeaderPosition == null || this.mHeaderPosition != mPaddingTop) {
            this.mHeaderPosition = mPaddingTop;
            final long headerId = this.mAdapter.getHeaderId(mPaddingTop);
            if (this.mHeaderId == null || this.mHeaderId != headerId) {
                this.mHeaderId = headerId;
                final View headerView = this.mAdapter.getHeaderView(this.mHeaderPosition, this.mHeader, (ViewGroup)this);
                if (this.mHeader != headerView) {
                    if (headerView == null) {
                        throw new NullPointerException("header may not be null");
                    }
                    this.swapHeader(headerView);
                }
                this.ensureHeaderHasCorrectLayoutParams(this.mHeader);
                this.measureHeader(this.mHeader);
                if (this.mOnStickyHeaderChangedListener != null) {
                    this.mOnStickyHeaderChangedListener.onStickyHeaderChanged(this, this.mHeader, mPaddingTop, this.mHeaderId);
                }
                this.mHeaderOffset = null;
            }
        }
        final int measuredHeight = this.mHeader.getMeasuredHeight();
        if (this.mClippingToPadding) {
            mPaddingTop = this.mPaddingTop;
        }
        else {
            mPaddingTop = 0;
        }
        int n = 0;
        int min;
        while (true) {
            min = (b ? 1 : 0);
            if (n >= this.mList.getChildCount()) {
                break;
            }
            final View child = this.mList.getChildAt(n);
            boolean b2;
            if (child instanceof WrapperView && ((WrapperView)child).hasHeader()) {
                b2 = true;
            }
            else {
                b2 = false;
            }
            final boolean containsFooterView = this.mList.containsFooterView(child);
            final int top = child.getTop();
            int mPaddingTop2;
            if (this.mClippingToPadding) {
                mPaddingTop2 = this.mPaddingTop;
            }
            else {
                mPaddingTop2 = 0;
            }
            if (top >= mPaddingTop2 && (b2 || containsFooterView)) {
                min = Math.min(child.getTop() - (measuredHeight + mPaddingTop), 0);
                break;
            }
            ++n;
        }
        this.setHeaderOffet(min);
        if (!this.mIsDrawingListUnderStickyHeader) {
            this.mList.setTopClippingLength(this.mHeader.getMeasuredHeight() + this.mHeaderOffset);
        }
        this.updateHeaderVisibilities();
    }
    
    private void updateHeaderVisibilities() {
        int mPaddingTop;
        if (this.mHeader != null) {
            final int measuredHeight = this.mHeader.getMeasuredHeight();
            int intValue;
            if (this.mHeaderOffset != null) {
                intValue = this.mHeaderOffset;
            }
            else {
                intValue = 0;
            }
            mPaddingTop = intValue + measuredHeight;
        }
        else if (this.mClippingToPadding) {
            mPaddingTop = this.mPaddingTop;
        }
        else {
            mPaddingTop = 0;
        }
        for (int childCount = this.mList.getChildCount(), i = 0; i < childCount; ++i) {
            final View child = this.mList.getChildAt(i);
            if (child instanceof WrapperView) {
                final WrapperView wrapperView = (WrapperView)child;
                if (wrapperView.hasHeader()) {
                    final View mHeader = wrapperView.mHeader;
                    if (wrapperView.getTop() < mPaddingTop) {
                        if (mHeader.getVisibility() != 4) {
                            mHeader.setVisibility(4);
                        }
                    }
                    else if (mHeader.getVisibility() != 0) {
                        mHeader.setVisibility(0);
                    }
                }
            }
        }
    }
    
    private void updateOrClearHeader(int n) {
        final boolean b = false;
        int count;
        if (this.mAdapter == null) {
            count = 0;
        }
        else {
            count = this.mAdapter.getCount();
        }
        if (count == 0 || !this.mAreHeadersSticky) {
            return;
        }
        final int n2 = n - this.mList.getHeaderViewsCount();
        if (this.mList.getChildCount() != 0) {
            n = 1;
        }
        else {
            n = 0;
        }
        while (true) {
            Label_0150: {
                if (n == 0 || this.mList.getFirstVisiblePosition() != 0) {
                    break Label_0150;
                }
                final int top = this.mList.getChildAt(0).getTop();
                int mPaddingTop;
                if (this.mClippingToPadding) {
                    mPaddingTop = this.mPaddingTop;
                }
                else {
                    mPaddingTop = 0;
                }
                if (top <= mPaddingTop) {
                    break Label_0150;
                }
                final int n3 = 1;
                boolean b2 = false;
                Label_0123: {
                    if (n2 <= count - 1) {
                        b2 = b;
                        if (n2 >= 0) {
                            break Label_0123;
                        }
                    }
                    b2 = true;
                }
                if (n == 0 || b2 || n3 != 0) {
                    this.clearHeader();
                    return;
                }
                this.updateHeader(n2);
                return;
            }
            final int n3 = 0;
            continue;
        }
    }
    
    public void addHeaderView(final View view) {
        this.mList.addHeaderView(view);
    }
    
    public void addHeaderView(final View view, final Object o, final boolean b) {
        this.mList.addHeaderView(view, o, b);
    }
    
    @TargetApi(14)
    public boolean canScrollVertically(final int n) {
        return this.mList.canScrollVertically(n);
    }
    
    protected void dispatchDraw(final Canvas canvas) {
        if (this.mList.getVisibility() == 0 || this.mList.getAnimation() != null) {
            this.drawChild(canvas, (View)this.mList, 0L);
        }
    }
    
    public StickyListHeadersAdapter getAdapter() {
        if (this.mAdapter == null) {
            return null;
        }
        return this.mAdapter.mDelegate;
    }
    
    public int getCount() {
        return this.mList.getCount();
    }
    
    public View getHeader() {
        return this.mHeader;
    }
    
    public int getHeaderViewsCount() {
        return this.mList.getHeaderViewsCount();
    }
    
    @TargetApi(9)
    public int getOverScrollMode() {
        if (this.requireSdkVersion(9)) {
            return this.mList.getOverScrollMode();
        }
        return 0;
    }
    
    public int getPaddingBottom() {
        return this.mPaddingBottom;
    }
    
    public int getPaddingLeft() {
        return this.mPaddingLeft;
    }
    
    public int getPaddingRight() {
        return this.mPaddingRight;
    }
    
    public int getPaddingTop() {
        return this.mPaddingTop;
    }
    
    public int getScrollBarStyle() {
        return this.mList.getScrollBarStyle();
    }
    
    public ListView getWrappedList() {
        return this.mList;
    }
    
    public boolean isHorizontalScrollBarEnabled() {
        return this.mList.isHorizontalScrollBarEnabled();
    }
    
    public boolean isVerticalScrollBarEnabled() {
        return this.mList.isVerticalScrollBarEnabled();
    }
    
    protected void onLayout(final boolean b, int mPaddingTop, int topMargin, final int n, final int n2) {
        this.mList.layout(0, 0, this.mList.getMeasuredWidth(), this.getHeight());
        if (this.mHeader != null) {
            topMargin = ((ViewGroup$MarginLayoutParams)this.mHeader.getLayoutParams()).topMargin;
            if (this.mClippingToPadding) {
                mPaddingTop = this.mPaddingTop;
            }
            else {
                mPaddingTop = 0;
            }
            mPaddingTop += topMargin;
            this.mHeader.layout(this.mPaddingLeft, mPaddingTop, this.mHeader.getMeasuredWidth() + this.mPaddingLeft, this.mHeader.getMeasuredHeight() + mPaddingTop);
        }
    }
    
    protected void onMeasure(final int n, final int n2) {
        super.onMeasure(n, n2);
        this.measureHeader(this.mHeader);
    }
    
    public void onRestoreInstanceState(final Parcelable parcelable) {
        super.onRestoreInstanceState((Parcelable)View$BaseSavedState.EMPTY_STATE);
        this.mList.onRestoreInstanceState(parcelable);
    }
    
    public Parcelable onSaveInstanceState() {
        if (super.onSaveInstanceState() != View$BaseSavedState.EMPTY_STATE) {
            throw new IllegalStateException("Handling non empty state of parent class is not implemented");
        }
        return this.mList.onSaveInstanceState();
    }
    
    public void setAdapter(final StickyListHeadersAdapter stickyListHeadersAdapter) {
        if (stickyListHeadersAdapter == null) {
            this.mList.setAdapter((ListAdapter)null);
            this.clearHeader();
            return;
        }
        if (this.mAdapter != null) {
            this.mAdapter.unregisterDataSetObserver((DataSetObserver)this.mDataSetObserver);
        }
        if (stickyListHeadersAdapter instanceof SectionIndexer) {
            this.mAdapter = new SectionIndexerAdapterWrapper(this.getContext(), stickyListHeadersAdapter);
        }
        else {
            this.mAdapter = new AdapterWrapper(this.getContext(), stickyListHeadersAdapter);
        }
        this.mDataSetObserver = new StickyListHeadersListView$AdapterWrapperDataSetObserver(this, null);
        this.mAdapter.registerDataSetObserver((DataSetObserver)this.mDataSetObserver);
        if (this.mOnHeaderClickListener != null) {
            this.mAdapter.setOnHeaderClickListener(new StickyListHeadersListView$AdapterWrapperHeaderClickHandler(this, null));
        }
        else {
            this.mAdapter.setOnHeaderClickListener(null);
        }
        this.mAdapter.setDivider(this.mDivider, this.mDividerHeight);
        this.mList.setAdapter((ListAdapter)this.mAdapter);
        this.clearHeader();
    }
    
    public void setAreHeadersSticky(final boolean mAreHeadersSticky) {
        if (!(this.mAreHeadersSticky = mAreHeadersSticky)) {
            this.clearHeader();
        }
        else {
            this.updateOrClearHeader(this.mList.getFixedFirstVisibleItem());
        }
        this.mList.invalidate();
    }
    
    public void setClipToPadding(final boolean b) {
        if (this.mList != null) {
            this.mList.setClipToPadding(b);
        }
        this.mClippingToPadding = b;
    }
    
    public void setDivider(final Drawable mDivider) {
        this.mDivider = mDivider;
        if (this.mAdapter != null) {
            this.mAdapter.setDivider(this.mDivider, this.mDividerHeight);
        }
    }
    
    public void setHorizontalScrollBarEnabled(final boolean horizontalScrollBarEnabled) {
        this.mList.setHorizontalScrollBarEnabled(horizontalScrollBarEnabled);
    }
    
    public void setOnCreateContextMenuListener(final View$OnCreateContextMenuListener onCreateContextMenuListener) {
        this.mList.setOnCreateContextMenuListener(onCreateContextMenuListener);
    }
    
    public void setOnScrollListener(final AbsListView$OnScrollListener mOnScrollListenerDelegate) {
        this.mOnScrollListenerDelegate = mOnScrollListenerDelegate;
    }
    
    public void setOnTouchListener(final View$OnTouchListener view$OnTouchListener) {
        if (view$OnTouchListener != null) {
            this.mList.setOnTouchListener((View$OnTouchListener)new StickyListHeadersListView$2(this, view$OnTouchListener));
            return;
        }
        this.mList.setOnTouchListener((View$OnTouchListener)null);
    }
    
    @TargetApi(9)
    public void setOverScrollMode(final int overScrollMode) {
        if (this.requireSdkVersion(9) && this.mList != null) {
            this.mList.setOverScrollMode(overScrollMode);
        }
    }
    
    public void setPadding(final int mPaddingLeft, final int mPaddingTop, final int mPaddingRight, final int mPaddingBottom) {
        this.mPaddingLeft = mPaddingLeft;
        this.mPaddingTop = mPaddingTop;
        this.mPaddingRight = mPaddingRight;
        this.mPaddingBottom = mPaddingBottom;
        if (this.mList != null) {
            this.mList.setPadding(mPaddingLeft, mPaddingTop, mPaddingRight, mPaddingBottom);
        }
        super.setPadding(0, 0, 0, 0);
        this.requestLayout();
    }
    
    public void setRecyclerListener(final AbsListView$RecyclerListener recyclerListener) {
        this.mList.setRecyclerListener(recyclerListener);
    }
    
    public void setScrollBarStyle(final int scrollBarStyle) {
        this.mList.setScrollBarStyle(scrollBarStyle);
    }
    
    public void setSelection(final int n) {
        this.setSelectionFromTop(n, 0);
    }
    
    public void setSelectionFromTop(final int n, final int n2) {
        int mPaddingTop = 0;
        int headerOverlap;
        if (this.mAdapter == null) {
            headerOverlap = 0;
        }
        else {
            headerOverlap = this.getHeaderOverlap(n);
        }
        if (!this.mClippingToPadding) {
            mPaddingTop = this.mPaddingTop;
        }
        this.mList.setSelectionFromTop(n, headerOverlap + n2 - mPaddingTop);
    }
    
    public void setVerticalScrollBarEnabled(final boolean verticalScrollBarEnabled) {
        this.mList.setVerticalScrollBarEnabled(verticalScrollBarEnabled);
    }
    
    public boolean showContextMenu() {
        return this.mList.showContextMenu();
    }
    
    @TargetApi(11)
    public void smoothScrollToPositionFromTop(final int n, final int n2) {
        int mPaddingTop = 0;
        if (this.requireSdkVersion(11)) {
            int headerOverlap;
            if (this.mAdapter == null) {
                headerOverlap = 0;
            }
            else {
                headerOverlap = this.getHeaderOverlap(n);
            }
            if (!this.mClippingToPadding) {
                mPaddingTop = this.mPaddingTop;
            }
            this.mList.smoothScrollToPositionFromTop(n, headerOverlap + n2 - mPaddingTop);
        }
    }
}
