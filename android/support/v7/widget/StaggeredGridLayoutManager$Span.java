// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.view.View;
import java.util.ArrayList;

class StaggeredGridLayoutManager$Span
{
    int mCachedEnd;
    int mCachedStart;
    int mDeletedSize;
    final int mIndex;
    ArrayList<View> mViews;
    final /* synthetic */ StaggeredGridLayoutManager this$0;
    
    StaggeredGridLayoutManager$Span(final StaggeredGridLayoutManager this$0, final int mIndex) {
        this.this$0 = this$0;
        this.mViews = new ArrayList<View>();
        this.mCachedStart = Integer.MIN_VALUE;
        this.mCachedEnd = Integer.MIN_VALUE;
        this.mDeletedSize = 0;
        this.mIndex = mIndex;
    }
    
    void appendToSpan(final View view) {
        final StaggeredGridLayoutManager$LayoutParams layoutParams = this.getLayoutParams(view);
        layoutParams.mSpan = this;
        this.mViews.add(view);
        this.mCachedEnd = Integer.MIN_VALUE;
        if (this.mViews.size() == 1) {
            this.mCachedStart = Integer.MIN_VALUE;
        }
        if (layoutParams.isItemRemoved() || layoutParams.isItemChanged()) {
            this.mDeletedSize += this.this$0.mPrimaryOrientation.getDecoratedMeasurement(view);
        }
    }
    
    void cacheReferenceLineAndClear(final boolean b, final int n) {
        int n2;
        if (b) {
            n2 = this.getEndLine(Integer.MIN_VALUE);
        }
        else {
            n2 = this.getStartLine(Integer.MIN_VALUE);
        }
        this.clear();
        if (n2 != Integer.MIN_VALUE && (!b || n2 >= this.this$0.mPrimaryOrientation.getEndAfterPadding()) && (b || n2 <= this.this$0.mPrimaryOrientation.getStartAfterPadding())) {
            int n3 = n2;
            if (n != Integer.MIN_VALUE) {
                n3 = n2 + n;
            }
            this.mCachedEnd = n3;
            this.mCachedStart = n3;
        }
    }
    
    void calculateCachedEnd() {
        final View view = this.mViews.get(this.mViews.size() - 1);
        final StaggeredGridLayoutManager$LayoutParams layoutParams = this.getLayoutParams(view);
        this.mCachedEnd = this.this$0.mPrimaryOrientation.getDecoratedEnd(view);
        if (layoutParams.mFullSpan) {
            final StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem fullSpanItem = this.this$0.mLazySpanLookup.getFullSpanItem(layoutParams.getViewLayoutPosition());
            if (fullSpanItem != null && fullSpanItem.mGapDir == 1) {
                this.mCachedEnd += fullSpanItem.getGapForSpan(this.mIndex);
            }
        }
    }
    
    void calculateCachedStart() {
        final View view = this.mViews.get(0);
        final StaggeredGridLayoutManager$LayoutParams layoutParams = this.getLayoutParams(view);
        this.mCachedStart = this.this$0.mPrimaryOrientation.getDecoratedStart(view);
        if (layoutParams.mFullSpan) {
            final StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem fullSpanItem = this.this$0.mLazySpanLookup.getFullSpanItem(layoutParams.getViewLayoutPosition());
            if (fullSpanItem != null && fullSpanItem.mGapDir == -1) {
                this.mCachedStart -= fullSpanItem.getGapForSpan(this.mIndex);
            }
        }
    }
    
    void clear() {
        this.mViews.clear();
        this.invalidateCache();
        this.mDeletedSize = 0;
    }
    
    public int getDeletedSize() {
        return this.mDeletedSize;
    }
    
    int getEndLine() {
        if (this.mCachedEnd != Integer.MIN_VALUE) {
            return this.mCachedEnd;
        }
        this.calculateCachedEnd();
        return this.mCachedEnd;
    }
    
    int getEndLine(int mCachedEnd) {
        if (this.mCachedEnd != Integer.MIN_VALUE) {
            mCachedEnd = this.mCachedEnd;
        }
        else if (this.mViews.size() != 0) {
            this.calculateCachedEnd();
            return this.mCachedEnd;
        }
        return mCachedEnd;
    }
    
    public View getFocusableViewAfter(final int n, int i) {
        final View view = null;
        View view2 = null;
        if (i == -1) {
            int size;
            View view3;
            for (size = this.mViews.size(), i = 0; i < size; ++i, view2 = view3) {
                view3 = this.mViews.get(i);
                if (!view3.isFocusable() || this.this$0.getPosition(view3) > n != this.this$0.mReverseLayout) {
                    break;
                }
            }
            return view2;
        }
        i = this.mViews.size() - 1;
        View view4 = view;
        while (i >= 0) {
            final View view5 = this.mViews.get(i);
            if (!view5.isFocusable()) {
                break;
            }
            int n2;
            if (this.this$0.getPosition(view5) > n) {
                n2 = 1;
            }
            else {
                n2 = 0;
            }
            int n3;
            if (!this.this$0.mReverseLayout) {
                n3 = 1;
            }
            else {
                n3 = 0;
            }
            if (n2 != n3) {
                break;
            }
            --i;
            view4 = view5;
        }
        return view4;
    }
    
    StaggeredGridLayoutManager$LayoutParams getLayoutParams(final View view) {
        return (StaggeredGridLayoutManager$LayoutParams)view.getLayoutParams();
    }
    
    int getStartLine() {
        if (this.mCachedStart != Integer.MIN_VALUE) {
            return this.mCachedStart;
        }
        this.calculateCachedStart();
        return this.mCachedStart;
    }
    
    int getStartLine(int mCachedStart) {
        if (this.mCachedStart != Integer.MIN_VALUE) {
            mCachedStart = this.mCachedStart;
        }
        else if (this.mViews.size() != 0) {
            this.calculateCachedStart();
            return this.mCachedStart;
        }
        return mCachedStart;
    }
    
    void invalidateCache() {
        this.mCachedStart = Integer.MIN_VALUE;
        this.mCachedEnd = Integer.MIN_VALUE;
    }
    
    void onOffset(final int n) {
        if (this.mCachedStart != Integer.MIN_VALUE) {
            this.mCachedStart += n;
        }
        if (this.mCachedEnd != Integer.MIN_VALUE) {
            this.mCachedEnd += n;
        }
    }
    
    void popEnd() {
        final int size = this.mViews.size();
        final View view = this.mViews.remove(size - 1);
        final StaggeredGridLayoutManager$LayoutParams layoutParams = this.getLayoutParams(view);
        layoutParams.mSpan = null;
        if (layoutParams.isItemRemoved() || layoutParams.isItemChanged()) {
            this.mDeletedSize -= this.this$0.mPrimaryOrientation.getDecoratedMeasurement(view);
        }
        if (size == 1) {
            this.mCachedStart = Integer.MIN_VALUE;
        }
        this.mCachedEnd = Integer.MIN_VALUE;
    }
    
    void popStart() {
        final View view = this.mViews.remove(0);
        final StaggeredGridLayoutManager$LayoutParams layoutParams = this.getLayoutParams(view);
        layoutParams.mSpan = null;
        if (this.mViews.size() == 0) {
            this.mCachedEnd = Integer.MIN_VALUE;
        }
        if (layoutParams.isItemRemoved() || layoutParams.isItemChanged()) {
            this.mDeletedSize -= this.this$0.mPrimaryOrientation.getDecoratedMeasurement(view);
        }
        this.mCachedStart = Integer.MIN_VALUE;
    }
    
    void prependToSpan(final View view) {
        final StaggeredGridLayoutManager$LayoutParams layoutParams = this.getLayoutParams(view);
        layoutParams.mSpan = this;
        this.mViews.add(0, view);
        this.mCachedStart = Integer.MIN_VALUE;
        if (this.mViews.size() == 1) {
            this.mCachedEnd = Integer.MIN_VALUE;
        }
        if (layoutParams.isItemRemoved() || layoutParams.isItemChanged()) {
            this.mDeletedSize += this.this$0.mPrimaryOrientation.getDecoratedMeasurement(view);
        }
    }
    
    void setLine(final int n) {
        this.mCachedStart = n;
        this.mCachedEnd = n;
    }
}
