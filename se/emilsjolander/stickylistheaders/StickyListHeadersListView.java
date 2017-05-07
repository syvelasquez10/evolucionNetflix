// 
// Decompiled by Procyon v0.5.30
// 

package se.emilsjolander.stickylistheaders;

import android.content.res.TypedArray;
import android.widget.AbsListView;
import android.widget.AbsListView$RecyclerListener;
import android.view.MotionEvent;
import android.view.View$OnTouchListener;
import android.widget.AdapterView$OnItemLongClickListener;
import android.widget.AdapterView$OnItemClickListener;
import android.view.View$OnCreateContextMenuListener;
import android.widget.AbsListView$MultiChoiceModeListener;
import android.widget.SectionIndexer;
import android.database.DataSetObserver;
import android.widget.ListAdapter;
import android.view.View$BaseSavedState;
import android.os.Parcelable;
import android.widget.ListView;
import android.util.SparseBooleanArray;
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
    private AdapterWrapperDataSetObserver mDataSetObserver;
    private Drawable mDivider;
    private int mDividerHeight;
    private View mHeader;
    private Long mHeaderId;
    private Integer mHeaderOffset;
    private Integer mHeaderPosition;
    private boolean mIsDrawingListUnderStickyHeader;
    private WrapperViewList mList;
    private OnHeaderClickListener mOnHeaderClickListener;
    private AbsListView$OnScrollListener mOnScrollListenerDelegate;
    private OnStickyHeaderChangedListener mOnStickyHeaderChangedListener;
    private OnStickyHeaderOffsetChangedListener mOnStickyHeaderOffsetChangedListener;
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
        Label_0569: {
            if (set == null) {
                break Label_0569;
            }
            while (true) {
                obtainStyledAttributes = (Context)obtainStyledAttributes.getTheme().obtainStyledAttributes(set, R.styleable.StickyListHeadersListView, 0, 0);
                while (true) {
                    Label_0655: {
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
                            this.mList.setHorizontalScrollBarEnabled((n & 0x100) != 0x0);
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
                                    break Label_0655;
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
                            this.mList.setLifeCycleListener((WrapperViewList.LifeCycleListener)new WrapperViewListLifeCycleListener());
                            this.mList.setOnScrollListener((AbsListView$OnScrollListener)new WrapperListScrollListener());
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
    
    static /* synthetic */ boolean access$1200(final StickyListHeadersListView stickyListHeadersListView, final Canvas canvas, final View view, final long n) {
        return stickyListHeadersListView.drawChild(canvas, view, n);
    }
    
    static /* synthetic */ boolean access$1300(final StickyListHeadersListView stickyListHeadersListView, final Canvas canvas, final View view, final long n) {
        return stickyListHeadersListView.drawChild(canvas, view, n);
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
        this.mHeader.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                if (StickyListHeadersListView.this.mOnHeaderClickListener != null) {
                    StickyListHeadersListView.this.mOnHeaderClickListener.onHeaderClick(StickyListHeadersListView.this, StickyListHeadersListView.this.mHeader, StickyListHeadersListView.this.mHeaderPosition, StickyListHeadersListView.this.mHeaderId, true);
                }
            }
        });
    }
    
    private void updateHeader(int mPaddingTop) {
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
        final boolean b = false;
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
            mPaddingTop = measuredHeight + intValue;
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
    
    public void addFooterView(final View view) {
        this.mList.addFooterView(view);
    }
    
    public void addHeaderView(final View view) {
        this.mList.addHeaderView(view);
    }
    
    public void addHeaderView(final View view, final Object o, final boolean b) {
        this.mList.addHeaderView(view, o, b);
    }
    
    public boolean areHeadersSticky() {
        return this.mAreHeadersSticky;
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
    
    @Deprecated
    public boolean getAreHeadersSticky() {
        return this.areHeadersSticky();
    }
    
    @TargetApi(11)
    public int getCheckedItemCount() {
        if (this.requireSdkVersion(11)) {
            return this.mList.getCheckedItemCount();
        }
        return 0;
    }
    
    @TargetApi(8)
    public long[] getCheckedItemIds() {
        if (this.requireSdkVersion(8)) {
            return this.mList.getCheckedItemIds();
        }
        return null;
    }
    
    @TargetApi(11)
    public int getCheckedItemPosition() {
        return this.mList.getCheckedItemPosition();
    }
    
    @TargetApi(11)
    public SparseBooleanArray getCheckedItemPositions() {
        return this.mList.getCheckedItemPositions();
    }
    
    public int getCount() {
        return this.mList.getCount();
    }
    
    public Drawable getDivider() {
        return this.mDivider;
    }
    
    public int getDividerHeight() {
        return this.mDividerHeight;
    }
    
    public View getEmptyView() {
        return this.mList.getEmptyView();
    }
    
    public int getFirstVisiblePosition() {
        return this.mList.getFirstVisiblePosition();
    }
    
    public int getFooterViewsCount() {
        return this.mList.getFooterViewsCount();
    }
    
    public View getHeader() {
        return this.mHeader;
    }
    
    public int getHeaderViewsCount() {
        return this.mList.getHeaderViewsCount();
    }
    
    public Object getItemAtPosition(final int n) {
        return this.mList.getItemAtPosition(n);
    }
    
    public long getItemIdAtPosition(final int n) {
        return this.mList.getItemIdAtPosition(n);
    }
    
    public int getLastVisiblePosition() {
        return this.mList.getLastVisiblePosition();
    }
    
    public View getListChildAt(final int n) {
        return this.mList.getChildAt(n);
    }
    
    public int getListChildCount() {
        return this.mList.getChildCount();
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
    
    public int getPositionForView(final View view) {
        return this.mList.getPositionForView(view);
    }
    
    public int getScrollBarStyle() {
        return this.mList.getScrollBarStyle();
    }
    
    public ListView getWrappedList() {
        return this.mList;
    }
    
    public void invalidateViews() {
        this.mList.invalidateViews();
    }
    
    public boolean isDrawingListUnderStickyHeader() {
        return this.mIsDrawingListUnderStickyHeader;
    }
    
    @TargetApi(11)
    public boolean isFastScrollAlwaysVisible() {
        return Build$VERSION.SDK_INT >= 11 && this.mList.isFastScrollAlwaysVisible();
    }
    
    public boolean isHorizontalScrollBarEnabled() {
        return this.mList.isHorizontalScrollBarEnabled();
    }
    
    public boolean isVerticalScrollBarEnabled() {
        return this.mList.isVerticalScrollBarEnabled();
    }
    
    protected void onLayout(final boolean b, int mPaddingTop, int topMargin, final int n, final int n2) {
        mPaddingTop = 0;
        this.mList.layout(0, 0, this.mList.getMeasuredWidth(), this.getHeight());
        if (this.mHeader != null) {
            topMargin = ((ViewGroup$MarginLayoutParams)this.mHeader.getLayoutParams()).topMargin;
            if (this.mClippingToPadding) {
                mPaddingTop = this.mPaddingTop;
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
    
    protected void recomputePadding() {
        this.setPadding(this.mPaddingLeft, this.mPaddingTop, this.mPaddingRight, this.mPaddingBottom);
    }
    
    public void removeFooterView(final View view) {
        this.mList.removeFooterView(view);
    }
    
    public void removeHeaderView(final View view) {
        this.mList.removeHeaderView(view);
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
        this.mDataSetObserver = new AdapterWrapperDataSetObserver();
        this.mAdapter.registerDataSetObserver((DataSetObserver)this.mDataSetObserver);
        if (this.mOnHeaderClickListener != null) {
            this.mAdapter.setOnHeaderClickListener((AdapterWrapper.OnHeaderClickListener)new AdapterWrapperHeaderClickHandler());
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
    
    @TargetApi(11)
    public void setChoiceMode(final int choiceMode) {
        this.mList.setChoiceMode(choiceMode);
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
    
    public void setDividerHeight(final int mDividerHeight) {
        this.mDividerHeight = mDividerHeight;
        if (this.mAdapter != null) {
            this.mAdapter.setDivider(this.mDivider, this.mDividerHeight);
        }
    }
    
    public void setDrawingListUnderStickyHeader(final boolean mIsDrawingListUnderStickyHeader) {
        this.mIsDrawingListUnderStickyHeader = mIsDrawingListUnderStickyHeader;
        this.mList.setTopClippingLength(0);
    }
    
    public void setEmptyView(final View emptyView) {
        this.mList.setEmptyView(emptyView);
    }
    
    @TargetApi(11)
    public void setFastScrollAlwaysVisible(final boolean fastScrollAlwaysVisible) {
        if (this.requireSdkVersion(11)) {
            this.mList.setFastScrollAlwaysVisible(fastScrollAlwaysVisible);
        }
    }
    
    public void setFastScrollEnabled(final boolean fastScrollEnabled) {
        this.mList.setFastScrollEnabled(fastScrollEnabled);
    }
    
    public void setHorizontalScrollBarEnabled(final boolean horizontalScrollBarEnabled) {
        this.mList.setHorizontalScrollBarEnabled(horizontalScrollBarEnabled);
    }
    
    @TargetApi(11)
    public void setItemChecked(final int n, final boolean b) {
        this.mList.setItemChecked(n, b);
    }
    
    @TargetApi(11)
    public void setMultiChoiceModeListener(final AbsListView$MultiChoiceModeListener multiChoiceModeListener) {
        if (this.requireSdkVersion(11)) {
            this.mList.setMultiChoiceModeListener(multiChoiceModeListener);
        }
    }
    
    public void setOnCreateContextMenuListener(final View$OnCreateContextMenuListener onCreateContextMenuListener) {
        this.mList.setOnCreateContextMenuListener(onCreateContextMenuListener);
    }
    
    public void setOnHeaderClickListener(final OnHeaderClickListener mOnHeaderClickListener) {
        this.mOnHeaderClickListener = mOnHeaderClickListener;
        if (this.mAdapter != null) {
            if (this.mOnHeaderClickListener == null) {
                this.mAdapter.setOnHeaderClickListener(null);
                return;
            }
            this.mAdapter.setOnHeaderClickListener((AdapterWrapper.OnHeaderClickListener)new AdapterWrapperHeaderClickHandler());
        }
    }
    
    public void setOnItemClickListener(final AdapterView$OnItemClickListener onItemClickListener) {
        this.mList.setOnItemClickListener(onItemClickListener);
    }
    
    public void setOnItemLongClickListener(final AdapterView$OnItemLongClickListener onItemLongClickListener) {
        this.mList.setOnItemLongClickListener(onItemLongClickListener);
    }
    
    public void setOnScrollListener(final AbsListView$OnScrollListener mOnScrollListenerDelegate) {
        this.mOnScrollListenerDelegate = mOnScrollListenerDelegate;
    }
    
    public void setOnStickyHeaderChangedListener(final OnStickyHeaderChangedListener mOnStickyHeaderChangedListener) {
        this.mOnStickyHeaderChangedListener = mOnStickyHeaderChangedListener;
    }
    
    public void setOnStickyHeaderOffsetChangedListener(final OnStickyHeaderOffsetChangedListener mOnStickyHeaderOffsetChangedListener) {
        this.mOnStickyHeaderOffsetChangedListener = mOnStickyHeaderOffsetChangedListener;
    }
    
    public void setOnTouchListener(final View$OnTouchListener view$OnTouchListener) {
        if (view$OnTouchListener != null) {
            this.mList.setOnTouchListener((View$OnTouchListener)new View$OnTouchListener() {
                public boolean onTouch(final View view, final MotionEvent motionEvent) {
                    return view$OnTouchListener.onTouch((View)StickyListHeadersListView.this, motionEvent);
                }
            });
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
    
    public void setSelectionAfterHeaderView() {
        this.mList.setSelectionAfterHeaderView();
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
        this.mList.setSelectionFromTop(n, n2 + headerOverlap - mPaddingTop);
    }
    
    public void setSelector(final int selector) {
        this.mList.setSelector(selector);
    }
    
    public void setSelector(final Drawable selector) {
        this.mList.setSelector(selector);
    }
    
    public void setTranscriptMode(final int transcriptMode) {
        this.mList.setTranscriptMode(transcriptMode);
    }
    
    public void setVerticalScrollBarEnabled(final boolean verticalScrollBarEnabled) {
        this.mList.setVerticalScrollBarEnabled(verticalScrollBarEnabled);
    }
    
    public boolean showContextMenu() {
        return this.mList.showContextMenu();
    }
    
    @TargetApi(8)
    public void smoothScrollBy(final int n, final int n2) {
        if (this.requireSdkVersion(8)) {
            this.mList.smoothScrollBy(n, n2);
        }
    }
    
    @TargetApi(11)
    public void smoothScrollByOffset(final int n) {
        if (this.requireSdkVersion(11)) {
            this.mList.smoothScrollByOffset(n);
        }
    }
    
    @SuppressLint({ "NewApi" })
    @TargetApi(8)
    public void smoothScrollToPosition(final int n) {
        int mPaddingTop = 0;
        if (this.requireSdkVersion(8)) {
            if (Build$VERSION.SDK_INT >= 11) {
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
                this.mList.smoothScrollToPositionFromTop(n, headerOverlap - mPaddingTop);
                return;
            }
            this.mList.smoothScrollToPosition(n);
        }
    }
    
    @TargetApi(8)
    public void smoothScrollToPosition(final int n, final int n2) {
        if (this.requireSdkVersion(8)) {
            this.mList.smoothScrollToPosition(n, n2);
        }
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
            this.mList.smoothScrollToPositionFromTop(n, n2 + headerOverlap - mPaddingTop);
        }
    }
    
    @TargetApi(11)
    public void smoothScrollToPositionFromTop(final int n, final int n2, final int n3) {
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
            this.mList.smoothScrollToPositionFromTop(n, n2 + headerOverlap - mPaddingTop, n3);
        }
    }
    
    private class AdapterWrapperDataSetObserver extends DataSetObserver
    {
        public void onChanged() {
            StickyListHeadersListView.this.clearHeader();
        }
        
        public void onInvalidated() {
            StickyListHeadersListView.this.clearHeader();
        }
    }
    
    private class AdapterWrapperHeaderClickHandler implements AdapterWrapper.OnHeaderClickListener
    {
        @Override
        public void onHeaderClick(final View view, final int n, final long n2) {
            StickyListHeadersListView.this.mOnHeaderClickListener.onHeaderClick(StickyListHeadersListView.this, view, n, n2, false);
        }
    }
    
    public interface OnHeaderClickListener
    {
        void onHeaderClick(final StickyListHeadersListView p0, final View p1, final int p2, final long p3, final boolean p4);
    }
    
    public interface OnStickyHeaderChangedListener
    {
        void onStickyHeaderChanged(final StickyListHeadersListView p0, final View p1, final int p2, final long p3);
    }
    
    public interface OnStickyHeaderOffsetChangedListener
    {
        void onStickyHeaderOffsetChanged(final StickyListHeadersListView p0, final View p1, final int p2);
    }
    
    private class WrapperListScrollListener implements AbsListView$OnScrollListener
    {
        public void onScroll(final AbsListView absListView, final int n, final int n2, final int n3) {
            if (StickyListHeadersListView.this.mOnScrollListenerDelegate != null) {
                StickyListHeadersListView.this.mOnScrollListenerDelegate.onScroll(absListView, n, n2, n3);
            }
            StickyListHeadersListView.this.updateOrClearHeader(StickyListHeadersListView.this.mList.getFixedFirstVisibleItem());
        }
        
        public void onScrollStateChanged(final AbsListView absListView, final int n) {
            if (StickyListHeadersListView.this.mOnScrollListenerDelegate != null) {
                StickyListHeadersListView.this.mOnScrollListenerDelegate.onScrollStateChanged(absListView, n);
            }
        }
    }
    
    private class WrapperViewListLifeCycleListener implements LifeCycleListener
    {
        @Override
        public void onDispatchDrawOccurred(final Canvas canvas) {
            if (Build$VERSION.SDK_INT < 8) {
                StickyListHeadersListView.this.updateOrClearHeader(StickyListHeadersListView.this.mList.getFixedFirstVisibleItem());
            }
            if (StickyListHeadersListView.this.mHeader != null) {
                if (!StickyListHeadersListView.this.mClippingToPadding) {
                    StickyListHeadersListView.access$1300(StickyListHeadersListView.this, canvas, StickyListHeadersListView.this.mHeader, 0L);
                    return;
                }
                canvas.save();
                canvas.clipRect(0, StickyListHeadersListView.this.mPaddingTop, StickyListHeadersListView.this.getRight(), StickyListHeadersListView.this.getBottom());
                StickyListHeadersListView.access$1200(StickyListHeadersListView.this, canvas, StickyListHeadersListView.this.mHeader, 0L);
                canvas.restore();
            }
        }
    }
}
