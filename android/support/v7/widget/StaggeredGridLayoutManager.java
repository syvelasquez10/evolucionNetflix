// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.os.Parcelable;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat$CollectionItemInfoCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v4.view.accessibility.AccessibilityRecordCompat;
import android.support.v4.view.accessibility.AccessibilityEventCompat;
import android.view.accessibility.AccessibilityEvent;
import android.view.ViewGroup$MarginLayoutParams;
import android.view.ViewGroup$LayoutParams;
import android.graphics.PointF;
import java.util.Arrays;
import android.view.View$MeasureSpec;
import android.view.View;
import android.util.AttributeSet;
import android.content.Context;
import android.graphics.Rect;
import java.util.BitSet;

public class StaggeredGridLayoutManager extends RecyclerView$LayoutManager implements RecyclerView$SmoothScroller$ScrollVectorProvider
{
    private final StaggeredGridLayoutManager$AnchorInfo mAnchorInfo;
    private final Runnable mCheckForGapsRunnable;
    private int mFullSizeSpec;
    private int mGapStrategy;
    private boolean mLaidOutInvalidFullSpan;
    private boolean mLastLayoutFromEnd;
    private boolean mLastLayoutRTL;
    private final LayoutState mLayoutState;
    StaggeredGridLayoutManager$LazySpanLookup mLazySpanLookup;
    private int mOrientation;
    private StaggeredGridLayoutManager$SavedState mPendingSavedState;
    int mPendingScrollPosition;
    int mPendingScrollPositionOffset;
    private int[] mPrefetchDistances;
    OrientationHelper mPrimaryOrientation;
    private BitSet mRemainingSpans;
    boolean mReverseLayout;
    OrientationHelper mSecondaryOrientation;
    boolean mShouldReverseLayout;
    private int mSizePerSpan;
    private boolean mSmoothScrollbarEnabled;
    private int mSpanCount;
    StaggeredGridLayoutManager$Span[] mSpans;
    private final Rect mTmpRect;
    
    public StaggeredGridLayoutManager(final int spanCount, final int mOrientation) {
        boolean autoMeasureEnabled = true;
        this.mSpanCount = -1;
        this.mReverseLayout = false;
        this.mShouldReverseLayout = false;
        this.mPendingScrollPosition = -1;
        this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
        this.mLazySpanLookup = new StaggeredGridLayoutManager$LazySpanLookup();
        this.mGapStrategy = 2;
        this.mTmpRect = new Rect();
        this.mAnchorInfo = new StaggeredGridLayoutManager$AnchorInfo(this);
        this.mLaidOutInvalidFullSpan = false;
        this.mSmoothScrollbarEnabled = true;
        this.mCheckForGapsRunnable = new StaggeredGridLayoutManager$1(this);
        this.mOrientation = mOrientation;
        this.setSpanCount(spanCount);
        if (this.mGapStrategy == 0) {
            autoMeasureEnabled = false;
        }
        this.setAutoMeasureEnabled(autoMeasureEnabled);
        this.mLayoutState = new LayoutState();
        this.createOrientationHelpers();
    }
    
    public StaggeredGridLayoutManager(final Context context, final AttributeSet set, final int n, final int n2) {
        boolean autoMeasureEnabled = true;
        this.mSpanCount = -1;
        this.mReverseLayout = false;
        this.mShouldReverseLayout = false;
        this.mPendingScrollPosition = -1;
        this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
        this.mLazySpanLookup = new StaggeredGridLayoutManager$LazySpanLookup();
        this.mGapStrategy = 2;
        this.mTmpRect = new Rect();
        this.mAnchorInfo = new StaggeredGridLayoutManager$AnchorInfo(this);
        this.mLaidOutInvalidFullSpan = false;
        this.mSmoothScrollbarEnabled = true;
        this.mCheckForGapsRunnable = new StaggeredGridLayoutManager$1(this);
        final RecyclerView$LayoutManager$Properties properties = RecyclerView$LayoutManager.getProperties(context, set, n, n2);
        this.setOrientation(properties.orientation);
        this.setSpanCount(properties.spanCount);
        this.setReverseLayout(properties.reverseLayout);
        if (this.mGapStrategy == 0) {
            autoMeasureEnabled = false;
        }
        this.setAutoMeasureEnabled(autoMeasureEnabled);
        this.mLayoutState = new LayoutState();
        this.createOrientationHelpers();
    }
    
    private void appendViewToAllSpans(final View view) {
        for (int i = this.mSpanCount - 1; i >= 0; --i) {
            this.mSpans[i].appendToSpan(view);
        }
    }
    
    private void applyPendingSavedState(final StaggeredGridLayoutManager$AnchorInfo staggeredGridLayoutManager$AnchorInfo) {
        if (this.mPendingSavedState.mSpanOffsetsSize > 0) {
            if (this.mPendingSavedState.mSpanOffsetsSize == this.mSpanCount) {
                for (int i = 0; i < this.mSpanCount; ++i) {
                    this.mSpans[i].clear();
                    final int n = this.mPendingSavedState.mSpanOffsets[i];
                    int line;
                    if ((line = n) != Integer.MIN_VALUE) {
                        if (this.mPendingSavedState.mAnchorLayoutFromEnd) {
                            line = n + this.mPrimaryOrientation.getEndAfterPadding();
                        }
                        else {
                            line = n + this.mPrimaryOrientation.getStartAfterPadding();
                        }
                    }
                    this.mSpans[i].setLine(line);
                }
            }
            else {
                this.mPendingSavedState.invalidateSpanInfo();
                this.mPendingSavedState.mAnchorPosition = this.mPendingSavedState.mVisibleAnchorPosition;
            }
        }
        this.mLastLayoutRTL = this.mPendingSavedState.mLastLayoutRTL;
        this.setReverseLayout(this.mPendingSavedState.mReverseLayout);
        this.resolveShouldLayoutReverse();
        if (this.mPendingSavedState.mAnchorPosition != -1) {
            this.mPendingScrollPosition = this.mPendingSavedState.mAnchorPosition;
            staggeredGridLayoutManager$AnchorInfo.mLayoutFromEnd = this.mPendingSavedState.mAnchorLayoutFromEnd;
        }
        else {
            staggeredGridLayoutManager$AnchorInfo.mLayoutFromEnd = this.mShouldReverseLayout;
        }
        if (this.mPendingSavedState.mSpanLookupSize > 1) {
            this.mLazySpanLookup.mData = this.mPendingSavedState.mSpanLookup;
            this.mLazySpanLookup.mFullSpanItems = this.mPendingSavedState.mFullSpanItems;
        }
    }
    
    private void attachViewToSpans(final View view, final StaggeredGridLayoutManager$LayoutParams staggeredGridLayoutManager$LayoutParams, final LayoutState layoutState) {
        if (layoutState.mLayoutDirection == 1) {
            if (staggeredGridLayoutManager$LayoutParams.mFullSpan) {
                this.appendViewToAllSpans(view);
                return;
            }
            staggeredGridLayoutManager$LayoutParams.mSpan.appendToSpan(view);
        }
        else {
            if (staggeredGridLayoutManager$LayoutParams.mFullSpan) {
                this.prependViewToAllSpans(view);
                return;
            }
            staggeredGridLayoutManager$LayoutParams.mSpan.prependToSpan(view);
        }
    }
    
    private int calculateScrollDirectionForPosition(int n) {
        final int n2 = -1;
        if (this.getChildCount() != 0) {
            if (n < this.getFirstChildPosition() != this.mShouldReverseLayout) {
                n = n2;
            }
            else {
                n = 1;
            }
            return n;
        }
        if (this.mShouldReverseLayout) {
            return 1;
        }
        return -1;
    }
    
    private boolean checkSpanForGap(final StaggeredGridLayoutManager$Span staggeredGridLayoutManager$Span) {
        boolean b = true;
        if (this.mShouldReverseLayout) {
            if (staggeredGridLayoutManager$Span.getEndLine() < this.mPrimaryOrientation.getEndAfterPadding()) {
                return !staggeredGridLayoutManager$Span.getLayoutParams(staggeredGridLayoutManager$Span.mViews.get(staggeredGridLayoutManager$Span.mViews.size() - 1)).mFullSpan;
            }
        }
        else if (staggeredGridLayoutManager$Span.getStartLine() > this.mPrimaryOrientation.getStartAfterPadding()) {
            if (staggeredGridLayoutManager$Span.getLayoutParams(staggeredGridLayoutManager$Span.mViews.get(0)).mFullSpan) {
                b = false;
            }
            return b;
        }
        return false;
    }
    
    private int computeScrollExtent(final RecyclerView$State recyclerView$State) {
        final boolean b = true;
        if (this.getChildCount() == 0) {
            return 0;
        }
        return ScrollbarHelper.computeScrollExtent(recyclerView$State, this.mPrimaryOrientation, this.findFirstVisibleItemClosestToStart(!this.mSmoothScrollbarEnabled), this.findFirstVisibleItemClosestToEnd(!this.mSmoothScrollbarEnabled && b), this, this.mSmoothScrollbarEnabled);
    }
    
    private int computeScrollOffset(final RecyclerView$State recyclerView$State) {
        final boolean b = true;
        if (this.getChildCount() == 0) {
            return 0;
        }
        return ScrollbarHelper.computeScrollOffset(recyclerView$State, this.mPrimaryOrientation, this.findFirstVisibleItemClosestToStart(!this.mSmoothScrollbarEnabled), this.findFirstVisibleItemClosestToEnd(!this.mSmoothScrollbarEnabled && b), this, this.mSmoothScrollbarEnabled, this.mShouldReverseLayout);
    }
    
    private int computeScrollRange(final RecyclerView$State recyclerView$State) {
        final boolean b = true;
        if (this.getChildCount() == 0) {
            return 0;
        }
        return ScrollbarHelper.computeScrollRange(recyclerView$State, this.mPrimaryOrientation, this.findFirstVisibleItemClosestToStart(!this.mSmoothScrollbarEnabled), this.findFirstVisibleItemClosestToEnd(!this.mSmoothScrollbarEnabled && b), this, this.mSmoothScrollbarEnabled);
    }
    
    private int convertFocusDirectionToLayoutDirection(int n) {
        final int n2 = -1;
        final int n3 = Integer.MIN_VALUE;
        final int n4 = 1;
        switch (n) {
            default: {
                n = Integer.MIN_VALUE;
                break;
            }
            case 1: {
                n = n2;
                if (this.mOrientation == 1) {
                    break;
                }
                n = n2;
                if (this.isLayoutRTL()) {
                    return 1;
                }
                break;
            }
            case 2: {
                if (this.mOrientation == 1) {
                    return 1;
                }
                n = n2;
                if (!this.isLayoutRTL()) {
                    return 1;
                }
                break;
            }
            case 33: {
                n = n2;
                if (this.mOrientation != 1) {
                    return Integer.MIN_VALUE;
                }
                break;
            }
            case 130: {
                n = n3;
                if (this.mOrientation == 1) {
                    n = 1;
                }
                return n;
            }
            case 17: {
                n = n2;
                if (this.mOrientation != 0) {
                    return Integer.MIN_VALUE;
                }
                break;
            }
            case 66: {
                if (this.mOrientation == 0) {
                    n = n4;
                }
                else {
                    n = Integer.MIN_VALUE;
                }
                return n;
            }
        }
        return n;
    }
    
    private StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem createFullSpanItemFromEnd(final int n) {
        final StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem staggeredGridLayoutManager$LazySpanLookup$FullSpanItem = new StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem();
        staggeredGridLayoutManager$LazySpanLookup$FullSpanItem.mGapPerSpan = new int[this.mSpanCount];
        for (int i = 0; i < this.mSpanCount; ++i) {
            staggeredGridLayoutManager$LazySpanLookup$FullSpanItem.mGapPerSpan[i] = n - this.mSpans[i].getEndLine(n);
        }
        return staggeredGridLayoutManager$LazySpanLookup$FullSpanItem;
    }
    
    private StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem createFullSpanItemFromStart(final int n) {
        final StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem staggeredGridLayoutManager$LazySpanLookup$FullSpanItem = new StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem();
        staggeredGridLayoutManager$LazySpanLookup$FullSpanItem.mGapPerSpan = new int[this.mSpanCount];
        for (int i = 0; i < this.mSpanCount; ++i) {
            staggeredGridLayoutManager$LazySpanLookup$FullSpanItem.mGapPerSpan[i] = this.mSpans[i].getStartLine(n) - n;
        }
        return staggeredGridLayoutManager$LazySpanLookup$FullSpanItem;
    }
    
    private void createOrientationHelpers() {
        this.mPrimaryOrientation = OrientationHelper.createOrientationHelper(this, this.mOrientation);
        this.mSecondaryOrientation = OrientationHelper.createOrientationHelper(this, 1 - this.mOrientation);
    }
    
    private int fill(final RecyclerView$Recycler recyclerView$Recycler, final LayoutState layoutState, final RecyclerView$State recyclerView$State) {
        this.mRemainingSpans.set(0, this.mSpanCount, true);
        int n;
        if (this.mLayoutState.mInfinite) {
            if (layoutState.mLayoutDirection == 1) {
                n = Integer.MAX_VALUE;
            }
            else {
                n = Integer.MIN_VALUE;
            }
        }
        else if (layoutState.mLayoutDirection == 1) {
            n = layoutState.mEndLine + layoutState.mAvailable;
        }
        else {
            n = layoutState.mStartLine - layoutState.mAvailable;
        }
        this.updateAllRemainingSpans(layoutState.mLayoutDirection, n);
        int n2;
        if (this.mShouldReverseLayout) {
            n2 = this.mPrimaryOrientation.getEndAfterPadding();
        }
        else {
            n2 = this.mPrimaryOrientation.getStartAfterPadding();
        }
        boolean b = false;
        while (layoutState.hasMore(recyclerView$State) && (this.mLayoutState.mInfinite || !this.mRemainingSpans.isEmpty())) {
            final View next = layoutState.next(recyclerView$Recycler);
            final StaggeredGridLayoutManager$LayoutParams staggeredGridLayoutManager$LayoutParams = (StaggeredGridLayoutManager$LayoutParams)next.getLayoutParams();
            final int viewLayoutPosition = staggeredGridLayoutManager$LayoutParams.getViewLayoutPosition();
            final int span = this.mLazySpanLookup.getSpan(viewLayoutPosition);
            boolean b2;
            if (span == -1) {
                b2 = true;
            }
            else {
                b2 = false;
            }
            StaggeredGridLayoutManager$Span nextSpan;
            if (b2) {
                if (staggeredGridLayoutManager$LayoutParams.mFullSpan) {
                    nextSpan = this.mSpans[0];
                }
                else {
                    nextSpan = this.getNextSpan(layoutState);
                }
                this.mLazySpanLookup.setSpan(viewLayoutPosition, nextSpan);
            }
            else {
                nextSpan = this.mSpans[span];
            }
            staggeredGridLayoutManager$LayoutParams.mSpan = nextSpan;
            if (layoutState.mLayoutDirection == 1) {
                this.addView(next);
            }
            else {
                this.addView(next, 0);
            }
            this.measureChildWithDecorationsAndMargin(next, staggeredGridLayoutManager$LayoutParams, false);
            int n4;
            int n5;
            if (layoutState.mLayoutDirection == 1) {
                int n3;
                if (staggeredGridLayoutManager$LayoutParams.mFullSpan) {
                    n3 = this.getMaxEnd(n2);
                }
                else {
                    n3 = nextSpan.getEndLine(n2);
                }
                n4 = n3 + this.mPrimaryOrientation.getDecoratedMeasurement(next);
                if (b2 && staggeredGridLayoutManager$LayoutParams.mFullSpan) {
                    final StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem fullSpanItemFromEnd = this.createFullSpanItemFromEnd(n3);
                    fullSpanItemFromEnd.mGapDir = -1;
                    fullSpanItemFromEnd.mPosition = viewLayoutPosition;
                    this.mLazySpanLookup.addFullSpanItem(fullSpanItemFromEnd);
                    n5 = n3;
                }
                else {
                    n5 = n3;
                }
            }
            else {
                int n6;
                if (staggeredGridLayoutManager$LayoutParams.mFullSpan) {
                    n6 = this.getMinStart(n2);
                }
                else {
                    n6 = nextSpan.getStartLine(n2);
                }
                n5 = n6 - this.mPrimaryOrientation.getDecoratedMeasurement(next);
                if (b2 && staggeredGridLayoutManager$LayoutParams.mFullSpan) {
                    final StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem fullSpanItemFromStart = this.createFullSpanItemFromStart(n6);
                    fullSpanItemFromStart.mGapDir = 1;
                    fullSpanItemFromStart.mPosition = viewLayoutPosition;
                    this.mLazySpanLookup.addFullSpanItem(fullSpanItemFromStart);
                }
                n4 = n6;
            }
            if (staggeredGridLayoutManager$LayoutParams.mFullSpan && layoutState.mItemDirection == -1) {
                if (b2) {
                    this.mLaidOutInvalidFullSpan = true;
                }
                else {
                    int n7;
                    if (layoutState.mLayoutDirection == 1) {
                        if (!this.areAllEndsEqual()) {
                            n7 = 1;
                        }
                        else {
                            n7 = 0;
                        }
                    }
                    else if (!this.areAllStartsEqual()) {
                        n7 = 1;
                    }
                    else {
                        n7 = 0;
                    }
                    if (n7 != 0) {
                        final StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem fullSpanItem = this.mLazySpanLookup.getFullSpanItem(viewLayoutPosition);
                        if (fullSpanItem != null) {
                            fullSpanItem.mHasUnwantedGapAfter = true;
                        }
                        this.mLaidOutInvalidFullSpan = true;
                    }
                }
            }
            this.attachViewToSpans(next, staggeredGridLayoutManager$LayoutParams, layoutState);
            int n9;
            int startAfterPadding;
            if (this.isLayoutRTL() && this.mOrientation == 1) {
                int endAfterPadding;
                if (staggeredGridLayoutManager$LayoutParams.mFullSpan) {
                    endAfterPadding = this.mSecondaryOrientation.getEndAfterPadding();
                }
                else {
                    endAfterPadding = this.mSecondaryOrientation.getEndAfterPadding() - (this.mSpanCount - 1 - nextSpan.mIndex) * this.mSizePerSpan;
                }
                final int n8 = endAfterPadding - this.mSecondaryOrientation.getDecoratedMeasurement(next);
                n9 = endAfterPadding;
                startAfterPadding = n8;
            }
            else {
                if (staggeredGridLayoutManager$LayoutParams.mFullSpan) {
                    startAfterPadding = this.mSecondaryOrientation.getStartAfterPadding();
                }
                else {
                    startAfterPadding = nextSpan.mIndex * this.mSizePerSpan + this.mSecondaryOrientation.getStartAfterPadding();
                }
                n9 = startAfterPadding + this.mSecondaryOrientation.getDecoratedMeasurement(next);
            }
            if (this.mOrientation == 1) {
                this.layoutDecoratedWithMargins(next, startAfterPadding, n5, n9, n4);
            }
            else {
                this.layoutDecoratedWithMargins(next, n5, startAfterPadding, n4, n9);
            }
            if (staggeredGridLayoutManager$LayoutParams.mFullSpan) {
                this.updateAllRemainingSpans(this.mLayoutState.mLayoutDirection, n);
            }
            else {
                this.updateRemainingSpans(nextSpan, this.mLayoutState.mLayoutDirection, n);
            }
            this.recycle(recyclerView$Recycler, this.mLayoutState);
            if (this.mLayoutState.mStopInFocusable && next.isFocusable()) {
                if (staggeredGridLayoutManager$LayoutParams.mFullSpan) {
                    this.mRemainingSpans.clear();
                }
                else {
                    this.mRemainingSpans.set(nextSpan.mIndex, false);
                }
            }
            b = true;
        }
        if (!b) {
            this.recycle(recyclerView$Recycler, this.mLayoutState);
        }
        int n10;
        if (this.mLayoutState.mLayoutDirection == -1) {
            n10 = this.mPrimaryOrientation.getStartAfterPadding() - this.getMinStart(this.mPrimaryOrientation.getStartAfterPadding());
        }
        else {
            n10 = this.getMaxEnd(this.mPrimaryOrientation.getEndAfterPadding()) - this.mPrimaryOrientation.getEndAfterPadding();
        }
        if (n10 > 0) {
            return Math.min(layoutState.mAvailable, n10);
        }
        return 0;
    }
    
    private int findFirstReferenceChildPosition(final int n) {
        for (int childCount = this.getChildCount(), i = 0; i < childCount; ++i) {
            final int position = this.getPosition(this.getChildAt(i));
            if (position >= 0 && position < n) {
                return position;
            }
        }
        return 0;
    }
    
    private int findLastReferenceChildPosition(final int n) {
        for (int i = this.getChildCount() - 1; i >= 0; --i) {
            final int position = this.getPosition(this.getChildAt(i));
            if (position >= 0 && position < n) {
                return position;
            }
        }
        return 0;
    }
    
    private void fixEndGap(final RecyclerView$Recycler recyclerView$Recycler, final RecyclerView$State recyclerView$State, final boolean b) {
        final int maxEnd = this.getMaxEnd(Integer.MIN_VALUE);
        if (maxEnd != Integer.MIN_VALUE) {
            final int n = this.mPrimaryOrientation.getEndAfterPadding() - maxEnd;
            if (n > 0) {
                final int n2 = n - -this.scrollBy(-n, recyclerView$Recycler, recyclerView$State);
                if (b && n2 > 0) {
                    this.mPrimaryOrientation.offsetChildren(n2);
                }
            }
        }
    }
    
    private void fixStartGap(final RecyclerView$Recycler recyclerView$Recycler, final RecyclerView$State recyclerView$State, final boolean b) {
        final int minStart = this.getMinStart(Integer.MAX_VALUE);
        if (minStart != Integer.MAX_VALUE) {
            final int n = minStart - this.mPrimaryOrientation.getStartAfterPadding();
            if (n > 0) {
                final int n2 = n - this.scrollBy(n, recyclerView$Recycler, recyclerView$State);
                if (b && n2 > 0) {
                    this.mPrimaryOrientation.offsetChildren(-n2);
                }
            }
        }
    }
    
    private int getMaxEnd(final int n) {
        int endLine = this.mSpans[0].getEndLine(n);
        int n2;
        for (int i = 1; i < this.mSpanCount; ++i, endLine = n2) {
            final int endLine2 = this.mSpans[i].getEndLine(n);
            if (endLine2 > (n2 = endLine)) {
                n2 = endLine2;
            }
        }
        return endLine;
    }
    
    private int getMaxStart(final int n) {
        int startLine = this.mSpans[0].getStartLine(n);
        int n2;
        for (int i = 1; i < this.mSpanCount; ++i, startLine = n2) {
            final int startLine2 = this.mSpans[i].getStartLine(n);
            if (startLine2 > (n2 = startLine)) {
                n2 = startLine2;
            }
        }
        return startLine;
    }
    
    private int getMinEnd(final int n) {
        int endLine = this.mSpans[0].getEndLine(n);
        int n2;
        for (int i = 1; i < this.mSpanCount; ++i, endLine = n2) {
            final int endLine2 = this.mSpans[i].getEndLine(n);
            if (endLine2 < (n2 = endLine)) {
                n2 = endLine2;
            }
        }
        return endLine;
    }
    
    private int getMinStart(final int n) {
        int startLine = this.mSpans[0].getStartLine(n);
        int n2;
        for (int i = 1; i < this.mSpanCount; ++i, startLine = n2) {
            final int startLine2 = this.mSpans[i].getStartLine(n);
            if (startLine2 < (n2 = startLine)) {
                n2 = startLine2;
            }
        }
        return startLine;
    }
    
    private StaggeredGridLayoutManager$Span getNextSpan(final LayoutState layoutState) {
        final StaggeredGridLayoutManager$Span staggeredGridLayoutManager$Span = null;
        final StaggeredGridLayoutManager$Span staggeredGridLayoutManager$Span2 = null;
        int n = -1;
        int n2;
        int mSpanCount;
        if (this.preferLastSpan(layoutState.mLayoutDirection)) {
            n2 = this.mSpanCount - 1;
            mSpanCount = -1;
        }
        else {
            mSpanCount = this.mSpanCount;
            n2 = 0;
            n = 1;
        }
        StaggeredGridLayoutManager$Span staggeredGridLayoutManager$Span4;
        if (layoutState.mLayoutDirection == 1) {
            final int startAfterPadding = this.mPrimaryOrientation.getStartAfterPadding();
            final int n3 = Integer.MAX_VALUE;
            int n4 = n2;
            StaggeredGridLayoutManager$Span staggeredGridLayoutManager$Span3 = staggeredGridLayoutManager$Span2;
            int n5 = n3;
            while (true) {
                staggeredGridLayoutManager$Span4 = staggeredGridLayoutManager$Span3;
                if (n4 == mSpanCount) {
                    break;
                }
                final StaggeredGridLayoutManager$Span staggeredGridLayoutManager$Span5 = this.mSpans[n4];
                final int endLine = staggeredGridLayoutManager$Span5.getEndLine(startAfterPadding);
                if (endLine < n5) {
                    staggeredGridLayoutManager$Span3 = staggeredGridLayoutManager$Span5;
                    n5 = endLine;
                }
                n4 += n;
            }
        }
        else {
            final int endAfterPadding = this.mPrimaryOrientation.getEndAfterPadding();
            final int n6 = Integer.MIN_VALUE;
            int n7 = n2;
            StaggeredGridLayoutManager$Span staggeredGridLayoutManager$Span6 = staggeredGridLayoutManager$Span;
            int n8 = n6;
            while (true) {
                staggeredGridLayoutManager$Span4 = staggeredGridLayoutManager$Span6;
                if (n7 == mSpanCount) {
                    break;
                }
                final StaggeredGridLayoutManager$Span staggeredGridLayoutManager$Span7 = this.mSpans[n7];
                final int startLine = staggeredGridLayoutManager$Span7.getStartLine(endAfterPadding);
                if (startLine > n8) {
                    staggeredGridLayoutManager$Span6 = staggeredGridLayoutManager$Span7;
                    n8 = startLine;
                }
                n7 += n;
            }
        }
        return staggeredGridLayoutManager$Span4;
    }
    
    private void handleUpdate(int n, final int n2, final int n3) {
        int n4;
        if (this.mShouldReverseLayout) {
            n4 = this.getLastChildPosition();
        }
        else {
            n4 = this.getFirstChildPosition();
        }
        int n5;
        int n6;
        if (n3 == 8) {
            if (n < n2) {
                n5 = n2 + 1;
                n6 = n;
            }
            else {
                n5 = n + 1;
                n6 = n2;
            }
        }
        else {
            n5 = n + n2;
            n6 = n;
        }
        this.mLazySpanLookup.invalidateAfter(n6);
        switch (n3) {
            case 1: {
                this.mLazySpanLookup.offsetForAddition(n, n2);
                break;
            }
            case 2: {
                this.mLazySpanLookup.offsetForRemoval(n, n2);
                break;
            }
            case 8: {
                this.mLazySpanLookup.offsetForRemoval(n, 1);
                this.mLazySpanLookup.offsetForAddition(n2, 1);
                break;
            }
        }
        if (n5 > n4) {
            if (this.mShouldReverseLayout) {
                n = this.getFirstChildPosition();
            }
            else {
                n = this.getLastChildPosition();
            }
            if (n6 <= n) {
                this.requestLayout();
            }
        }
    }
    
    private void measureChildWithDecorationsAndMargin(final View view, int updateSpecWithExtra, int updateSpecWithExtra2, final boolean b) {
        this.calculateItemDecorationsForChild(view, this.mTmpRect);
        final StaggeredGridLayoutManager$LayoutParams staggeredGridLayoutManager$LayoutParams = (StaggeredGridLayoutManager$LayoutParams)view.getLayoutParams();
        updateSpecWithExtra = this.updateSpecWithExtra(updateSpecWithExtra, staggeredGridLayoutManager$LayoutParams.leftMargin + this.mTmpRect.left, staggeredGridLayoutManager$LayoutParams.rightMargin + this.mTmpRect.right);
        updateSpecWithExtra2 = this.updateSpecWithExtra(updateSpecWithExtra2, staggeredGridLayoutManager$LayoutParams.topMargin + this.mTmpRect.top, staggeredGridLayoutManager$LayoutParams.bottomMargin + this.mTmpRect.bottom);
        boolean b2;
        if (b) {
            b2 = this.shouldReMeasureChild(view, updateSpecWithExtra, updateSpecWithExtra2, staggeredGridLayoutManager$LayoutParams);
        }
        else {
            b2 = this.shouldMeasureChild(view, updateSpecWithExtra, updateSpecWithExtra2, staggeredGridLayoutManager$LayoutParams);
        }
        if (b2) {
            view.measure(updateSpecWithExtra, updateSpecWithExtra2);
        }
    }
    
    private void measureChildWithDecorationsAndMargin(final View view, final StaggeredGridLayoutManager$LayoutParams staggeredGridLayoutManager$LayoutParams, final boolean b) {
        if (staggeredGridLayoutManager$LayoutParams.mFullSpan) {
            if (this.mOrientation == 1) {
                this.measureChildWithDecorationsAndMargin(view, this.mFullSizeSpec, RecyclerView$LayoutManager.getChildMeasureSpec(this.getHeight(), this.getHeightMode(), 0, staggeredGridLayoutManager$LayoutParams.height, true), b);
                return;
            }
            this.measureChildWithDecorationsAndMargin(view, RecyclerView$LayoutManager.getChildMeasureSpec(this.getWidth(), this.getWidthMode(), 0, staggeredGridLayoutManager$LayoutParams.width, true), this.mFullSizeSpec, b);
        }
        else {
            if (this.mOrientation == 1) {
                this.measureChildWithDecorationsAndMargin(view, RecyclerView$LayoutManager.getChildMeasureSpec(this.mSizePerSpan, this.getWidthMode(), 0, staggeredGridLayoutManager$LayoutParams.width, false), RecyclerView$LayoutManager.getChildMeasureSpec(this.getHeight(), this.getHeightMode(), 0, staggeredGridLayoutManager$LayoutParams.height, true), b);
                return;
            }
            this.measureChildWithDecorationsAndMargin(view, RecyclerView$LayoutManager.getChildMeasureSpec(this.getWidth(), this.getWidthMode(), 0, staggeredGridLayoutManager$LayoutParams.width, true), RecyclerView$LayoutManager.getChildMeasureSpec(this.mSizePerSpan, this.getHeightMode(), 0, staggeredGridLayoutManager$LayoutParams.height, false), b);
        }
    }
    
    private void onLayoutChildren(final RecyclerView$Recycler recyclerView$Recycler, final RecyclerView$State recyclerView$State, final boolean b) {
        final StaggeredGridLayoutManager$AnchorInfo mAnchorInfo = this.mAnchorInfo;
        if ((this.mPendingSavedState == null && this.mPendingScrollPosition == -1) || recyclerView$State.getItemCount() != 0) {
            boolean b2;
            if (!mAnchorInfo.mValid || this.mPendingScrollPosition != -1 || this.mPendingSavedState != null) {
                b2 = true;
            }
            else {
                b2 = false;
            }
            if (b2) {
                mAnchorInfo.reset();
                if (this.mPendingSavedState != null) {
                    this.applyPendingSavedState(mAnchorInfo);
                }
                else {
                    this.resolveShouldLayoutReverse();
                    mAnchorInfo.mLayoutFromEnd = this.mShouldReverseLayout;
                }
                this.updateAnchorInfoForLayout(recyclerView$State, mAnchorInfo);
                mAnchorInfo.mValid = true;
            }
            if (this.mPendingSavedState == null && this.mPendingScrollPosition == -1 && (mAnchorInfo.mLayoutFromEnd != this.mLastLayoutFromEnd || this.isLayoutRTL() != this.mLastLayoutRTL)) {
                this.mLazySpanLookup.clear();
                mAnchorInfo.mInvalidateOffsets = true;
            }
            if (this.getChildCount() > 0 && (this.mPendingSavedState == null || this.mPendingSavedState.mSpanOffsetsSize < 1)) {
                if (mAnchorInfo.mInvalidateOffsets) {
                    for (int i = 0; i < this.mSpanCount; ++i) {
                        this.mSpans[i].clear();
                        if (mAnchorInfo.mOffset != Integer.MIN_VALUE) {
                            this.mSpans[i].setLine(mAnchorInfo.mOffset);
                        }
                    }
                }
                else if (b2 || this.mAnchorInfo.mSpanReferenceLines == null) {
                    for (int j = 0; j < this.mSpanCount; ++j) {
                        this.mSpans[j].cacheReferenceLineAndClear(this.mShouldReverseLayout, mAnchorInfo.mOffset);
                    }
                    this.mAnchorInfo.saveSpanReferenceLines(this.mSpans);
                }
                else {
                    for (int k = 0; k < this.mSpanCount; ++k) {
                        final StaggeredGridLayoutManager$Span staggeredGridLayoutManager$Span = this.mSpans[k];
                        staggeredGridLayoutManager$Span.clear();
                        staggeredGridLayoutManager$Span.setLine(this.mAnchorInfo.mSpanReferenceLines[k]);
                    }
                }
            }
            this.detachAndScrapAttachedViews(recyclerView$Recycler);
            this.mLayoutState.mRecycle = false;
            this.mLaidOutInvalidFullSpan = false;
            this.updateMeasureSpecs(this.mSecondaryOrientation.getTotalSpace());
            this.updateLayoutState(mAnchorInfo.mPosition, recyclerView$State);
            if (mAnchorInfo.mLayoutFromEnd) {
                this.setLayoutStateDirection(-1);
                this.fill(recyclerView$Recycler, this.mLayoutState, recyclerView$State);
                this.setLayoutStateDirection(1);
                this.mLayoutState.mCurrentPosition = mAnchorInfo.mPosition + this.mLayoutState.mItemDirection;
                this.fill(recyclerView$Recycler, this.mLayoutState, recyclerView$State);
            }
            else {
                this.setLayoutStateDirection(1);
                this.fill(recyclerView$Recycler, this.mLayoutState, recyclerView$State);
                this.setLayoutStateDirection(-1);
                this.mLayoutState.mCurrentPosition = mAnchorInfo.mPosition + this.mLayoutState.mItemDirection;
                this.fill(recyclerView$Recycler, this.mLayoutState, recyclerView$State);
            }
            this.repositionToWrapContentIfNecessary();
            if (this.getChildCount() > 0) {
                if (this.mShouldReverseLayout) {
                    this.fixEndGap(recyclerView$Recycler, recyclerView$State, true);
                    this.fixStartGap(recyclerView$Recycler, recyclerView$State, false);
                }
                else {
                    this.fixStartGap(recyclerView$Recycler, recyclerView$State, true);
                    this.fixEndGap(recyclerView$Recycler, recyclerView$State, false);
                }
            }
            while (true) {
                Label_0705: {
                    if (!b || recyclerView$State.isPreLayout()) {
                        break Label_0705;
                    }
                    int n;
                    if (this.mGapStrategy != 0 && this.getChildCount() > 0 && (this.mLaidOutInvalidFullSpan || this.hasGapsToFix() != null)) {
                        n = 1;
                    }
                    else {
                        n = 0;
                    }
                    if (n == 0) {
                        break Label_0705;
                    }
                    this.removeCallbacks(this.mCheckForGapsRunnable);
                    if (!this.checkForGaps()) {
                        break Label_0705;
                    }
                    final int n2 = 1;
                    if (recyclerView$State.isPreLayout()) {
                        this.mAnchorInfo.reset();
                    }
                    this.mLastLayoutFromEnd = mAnchorInfo.mLayoutFromEnd;
                    this.mLastLayoutRTL = this.isLayoutRTL();
                    if (n2 != 0) {
                        this.mAnchorInfo.reset();
                        this.onLayoutChildren(recyclerView$Recycler, recyclerView$State, false);
                        return;
                    }
                    return;
                }
                final int n2 = 0;
                continue;
            }
        }
        this.removeAndRecycleAllViews(recyclerView$Recycler);
        mAnchorInfo.reset();
    }
    
    private boolean preferLastSpan(final int n) {
        if (this.mOrientation == 0) {
            if (n == -1 == this.mShouldReverseLayout) {
                return false;
            }
        }
        else if (n == -1 == this.mShouldReverseLayout != this.isLayoutRTL()) {
            return false;
        }
        return true;
    }
    
    private void prependViewToAllSpans(final View view) {
        for (int i = this.mSpanCount - 1; i >= 0; --i) {
            this.mSpans[i].prependToSpan(view);
        }
    }
    
    private void recycle(final RecyclerView$Recycler recyclerView$Recycler, final LayoutState layoutState) {
        if (!layoutState.mRecycle || layoutState.mInfinite) {
            return;
        }
        if (layoutState.mAvailable == 0) {
            if (layoutState.mLayoutDirection == -1) {
                this.recycleFromEnd(recyclerView$Recycler, layoutState.mEndLine);
                return;
            }
            this.recycleFromStart(recyclerView$Recycler, layoutState.mStartLine);
        }
        else {
            if (layoutState.mLayoutDirection == -1) {
                final int n = layoutState.mStartLine - this.getMaxStart(layoutState.mStartLine);
                int mEndLine;
                if (n < 0) {
                    mEndLine = layoutState.mEndLine;
                }
                else {
                    mEndLine = layoutState.mEndLine - Math.min(n, layoutState.mAvailable);
                }
                this.recycleFromEnd(recyclerView$Recycler, mEndLine);
                return;
            }
            final int n2 = this.getMinEnd(layoutState.mEndLine) - layoutState.mEndLine;
            int mStartLine;
            if (n2 < 0) {
                mStartLine = layoutState.mStartLine;
            }
            else {
                mStartLine = Math.min(n2, layoutState.mAvailable) + layoutState.mStartLine;
            }
            this.recycleFromStart(recyclerView$Recycler, mStartLine);
        }
    }
    
    private void recycleFromEnd(final RecyclerView$Recycler recyclerView$Recycler, final int n) {
    Label_0091:
        for (int i = this.getChildCount() - 1; i >= 0; --i) {
            final View child = this.getChildAt(i);
            if (this.mPrimaryOrientation.getDecoratedStart(child) < n || this.mPrimaryOrientation.getTransformedStartWithDecoration(child) < n) {
                break;
            }
            final StaggeredGridLayoutManager$LayoutParams staggeredGridLayoutManager$LayoutParams = (StaggeredGridLayoutManager$LayoutParams)child.getLayoutParams();
            if (staggeredGridLayoutManager$LayoutParams.mFullSpan) {
                for (int j = 0; j < this.mSpanCount; ++j) {
                    if (this.mSpans[j].mViews.size() == 1) {
                        break Label_0091;
                    }
                }
                for (int k = 0; k < this.mSpanCount; ++k) {
                    this.mSpans[k].popEnd();
                }
            }
            else {
                if (staggeredGridLayoutManager$LayoutParams.mSpan.mViews.size() == 1) {
                    break;
                }
                staggeredGridLayoutManager$LayoutParams.mSpan.popEnd();
            }
            this.removeAndRecycleView(child, recyclerView$Recycler);
        }
    }
    
    private void recycleFromStart(final RecyclerView$Recycler recyclerView$Recycler, final int n) {
    Label_0084:
        while (this.getChildCount() > 0) {
            final View child = this.getChildAt(0);
            if (this.mPrimaryOrientation.getDecoratedEnd(child) > n || this.mPrimaryOrientation.getTransformedEndWithDecoration(child) > n) {
                break;
            }
            final StaggeredGridLayoutManager$LayoutParams staggeredGridLayoutManager$LayoutParams = (StaggeredGridLayoutManager$LayoutParams)child.getLayoutParams();
            if (staggeredGridLayoutManager$LayoutParams.mFullSpan) {
                for (int i = 0; i < this.mSpanCount; ++i) {
                    if (this.mSpans[i].mViews.size() == 1) {
                        break Label_0084;
                    }
                }
                for (int j = 0; j < this.mSpanCount; ++j) {
                    this.mSpans[j].popStart();
                }
            }
            else {
                if (staggeredGridLayoutManager$LayoutParams.mSpan.mViews.size() == 1) {
                    break;
                }
                staggeredGridLayoutManager$LayoutParams.mSpan.popStart();
            }
            this.removeAndRecycleView(child, recyclerView$Recycler);
        }
    }
    
    private void repositionToWrapContentIfNecessary() {
        if (this.mSecondaryOrientation.getMode() != 1073741824) {
            float max = 0.0f;
            final int childCount = this.getChildCount();
            for (int i = 0; i < childCount; ++i) {
                final View child = this.getChildAt(i);
                float n = this.mSecondaryOrientation.getDecoratedMeasurement(child);
                if (n >= max) {
                    if (((StaggeredGridLayoutManager$LayoutParams)child.getLayoutParams()).isFullSpan()) {
                        n = 1.0f * n / this.mSpanCount;
                    }
                    max = Math.max(max, n);
                }
            }
            final int mSizePerSpan = this.mSizePerSpan;
            int n2 = Math.round(this.mSpanCount * max);
            if (this.mSecondaryOrientation.getMode() == Integer.MIN_VALUE) {
                n2 = Math.min(n2, this.mSecondaryOrientation.getTotalSpace());
            }
            this.updateMeasureSpecs(n2);
            if (this.mSizePerSpan != mSizePerSpan) {
                for (int j = 0; j < childCount; ++j) {
                    final View child2 = this.getChildAt(j);
                    final StaggeredGridLayoutManager$LayoutParams staggeredGridLayoutManager$LayoutParams = (StaggeredGridLayoutManager$LayoutParams)child2.getLayoutParams();
                    if (!staggeredGridLayoutManager$LayoutParams.mFullSpan) {
                        if (this.isLayoutRTL() && this.mOrientation == 1) {
                            child2.offsetLeftAndRight(-(this.mSpanCount - 1 - staggeredGridLayoutManager$LayoutParams.mSpan.mIndex) * this.mSizePerSpan - -(this.mSpanCount - 1 - staggeredGridLayoutManager$LayoutParams.mSpan.mIndex) * mSizePerSpan);
                        }
                        else {
                            final int n3 = staggeredGridLayoutManager$LayoutParams.mSpan.mIndex * this.mSizePerSpan;
                            final int n4 = staggeredGridLayoutManager$LayoutParams.mSpan.mIndex * mSizePerSpan;
                            if (this.mOrientation == 1) {
                                child2.offsetLeftAndRight(n3 - n4);
                            }
                            else {
                                child2.offsetTopAndBottom(n3 - n4);
                            }
                        }
                    }
                }
            }
        }
    }
    
    private void resolveShouldLayoutReverse() {
        boolean mShouldReverseLayout = true;
        if (this.mOrientation == 1 || !this.isLayoutRTL()) {
            this.mShouldReverseLayout = this.mReverseLayout;
            return;
        }
        if (this.mReverseLayout) {
            mShouldReverseLayout = false;
        }
        this.mShouldReverseLayout = mShouldReverseLayout;
    }
    
    private void setLayoutStateDirection(int n) {
        final int n2 = 1;
        this.mLayoutState.mLayoutDirection = n;
        final LayoutState mLayoutState = this.mLayoutState;
        if (this.mShouldReverseLayout == (n == -1)) {
            n = n2;
        }
        else {
            n = -1;
        }
        mLayoutState.mItemDirection = n;
    }
    
    private void updateAllRemainingSpans(final int n, final int n2) {
        for (int i = 0; i < this.mSpanCount; ++i) {
            if (!this.mSpans[i].mViews.isEmpty()) {
                this.updateRemainingSpans(this.mSpans[i], n, n2);
            }
        }
    }
    
    private boolean updateAnchorFromChildren(final RecyclerView$State recyclerView$State, final StaggeredGridLayoutManager$AnchorInfo staggeredGridLayoutManager$AnchorInfo) {
        int mPosition;
        if (this.mLastLayoutFromEnd) {
            mPosition = this.findLastReferenceChildPosition(recyclerView$State.getItemCount());
        }
        else {
            mPosition = this.findFirstReferenceChildPosition(recyclerView$State.getItemCount());
        }
        staggeredGridLayoutManager$AnchorInfo.mPosition = mPosition;
        staggeredGridLayoutManager$AnchorInfo.mOffset = Integer.MIN_VALUE;
        return true;
    }
    
    private void updateLayoutState(int totalSpace, final RecyclerView$State recyclerView$State) {
        final boolean b = false;
        this.mLayoutState.mAvailable = 0;
        this.mLayoutState.mCurrentPosition = totalSpace;
        while (true) {
            Label_0212: {
                if (!this.isSmoothScrolling()) {
                    break Label_0212;
                }
                final int targetScrollPosition = recyclerView$State.getTargetScrollPosition();
                if (targetScrollPosition == -1) {
                    break Label_0212;
                }
                int totalSpace2;
                if (this.mShouldReverseLayout == targetScrollPosition < totalSpace) {
                    totalSpace = this.mPrimaryOrientation.getTotalSpace();
                    totalSpace2 = 0;
                }
                else {
                    totalSpace2 = this.mPrimaryOrientation.getTotalSpace();
                    totalSpace = 0;
                }
                if (this.getClipToPadding()) {
                    this.mLayoutState.mStartLine = this.mPrimaryOrientation.getStartAfterPadding() - totalSpace2;
                    this.mLayoutState.mEndLine = totalSpace + this.mPrimaryOrientation.getEndAfterPadding();
                }
                else {
                    this.mLayoutState.mEndLine = totalSpace + this.mPrimaryOrientation.getEnd();
                    this.mLayoutState.mStartLine = -totalSpace2;
                }
                this.mLayoutState.mStopInFocusable = false;
                this.mLayoutState.mRecycle = true;
                final LayoutState mLayoutState = this.mLayoutState;
                boolean mInfinite = b;
                if (this.mPrimaryOrientation.getMode() == 0) {
                    mInfinite = b;
                    if (this.mPrimaryOrientation.getEnd() == 0) {
                        mInfinite = true;
                    }
                }
                mLayoutState.mInfinite = mInfinite;
                return;
            }
            totalSpace = 0;
            int totalSpace2 = 0;
            continue;
        }
    }
    
    private void updateRemainingSpans(final StaggeredGridLayoutManager$Span staggeredGridLayoutManager$Span, final int n, final int n2) {
        final int deletedSize = staggeredGridLayoutManager$Span.getDeletedSize();
        if (n == -1) {
            if (deletedSize + staggeredGridLayoutManager$Span.getStartLine() <= n2) {
                this.mRemainingSpans.set(staggeredGridLayoutManager$Span.mIndex, false);
            }
        }
        else if (staggeredGridLayoutManager$Span.getEndLine() - deletedSize >= n2) {
            this.mRemainingSpans.set(staggeredGridLayoutManager$Span.mIndex, false);
        }
    }
    
    private int updateSpecWithExtra(final int n, final int n2, final int n3) {
        if (n2 != 0 || n3 != 0) {
            final int mode = View$MeasureSpec.getMode(n);
            if (mode == Integer.MIN_VALUE || mode == 1073741824) {
                return View$MeasureSpec.makeMeasureSpec(Math.max(0, View$MeasureSpec.getSize(n) - n2 - n3), mode);
            }
        }
        return n;
    }
    
    boolean areAllEndsEqual() {
        final boolean b = true;
        final int endLine = this.mSpans[0].getEndLine(Integer.MIN_VALUE);
        int n = 1;
        boolean b2;
        while (true) {
            b2 = b;
            if (n >= this.mSpanCount) {
                break;
            }
            if (this.mSpans[n].getEndLine(Integer.MIN_VALUE) != endLine) {
                b2 = false;
                break;
            }
            ++n;
        }
        return b2;
    }
    
    boolean areAllStartsEqual() {
        final boolean b = true;
        final int startLine = this.mSpans[0].getStartLine(Integer.MIN_VALUE);
        int n = 1;
        boolean b2;
        while (true) {
            b2 = b;
            if (n >= this.mSpanCount) {
                break;
            }
            if (this.mSpans[n].getStartLine(Integer.MIN_VALUE) != startLine) {
                b2 = false;
                break;
            }
            ++n;
        }
        return b2;
    }
    
    @Override
    public void assertNotInLayoutOrScroll(final String s) {
        if (this.mPendingSavedState == null) {
            super.assertNotInLayoutOrScroll(s);
        }
    }
    
    @Override
    public boolean canScrollHorizontally() {
        return this.mOrientation == 0;
    }
    
    @Override
    public boolean canScrollVertically() {
        return this.mOrientation == 1;
    }
    
    boolean checkForGaps() {
        if (this.getChildCount() == 0 || this.mGapStrategy == 0 || !this.isAttachedToWindow()) {
            return false;
        }
        int n;
        int n2;
        if (this.mShouldReverseLayout) {
            n = this.getLastChildPosition();
            n2 = this.getFirstChildPosition();
        }
        else {
            n = this.getFirstChildPosition();
            n2 = this.getLastChildPosition();
        }
        if (n == 0 && this.hasGapsToFix() != null) {
            this.mLazySpanLookup.clear();
            this.requestSimpleAnimationsInNextLayout();
            this.requestLayout();
            return true;
        }
        if (!this.mLaidOutInvalidFullSpan) {
            return false;
        }
        int n3;
        if (this.mShouldReverseLayout) {
            n3 = -1;
        }
        else {
            n3 = 1;
        }
        final StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem firstFullSpanItemInRange = this.mLazySpanLookup.getFirstFullSpanItemInRange(n, n2 + 1, n3, true);
        if (firstFullSpanItemInRange == null) {
            this.mLaidOutInvalidFullSpan = false;
            this.mLazySpanLookup.forceInvalidateAfter(n2 + 1);
            return false;
        }
        final StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem firstFullSpanItemInRange2 = this.mLazySpanLookup.getFirstFullSpanItemInRange(n, firstFullSpanItemInRange.mPosition, n3 * -1, true);
        if (firstFullSpanItemInRange2 == null) {
            this.mLazySpanLookup.forceInvalidateAfter(firstFullSpanItemInRange.mPosition);
        }
        else {
            this.mLazySpanLookup.forceInvalidateAfter(firstFullSpanItemInRange2.mPosition + 1);
        }
        this.requestSimpleAnimationsInNextLayout();
        this.requestLayout();
        return true;
    }
    
    @Override
    public boolean checkLayoutParams(final RecyclerView$LayoutParams recyclerView$LayoutParams) {
        return recyclerView$LayoutParams instanceof StaggeredGridLayoutManager$LayoutParams;
    }
    
    @Override
    public void collectAdjacentPrefetchPositions(int i, int n, final RecyclerView$State recyclerView$State, final RecyclerView$LayoutManager$LayoutPrefetchRegistry recyclerView$LayoutManager$LayoutPrefetchRegistry) {
        final int n2 = 0;
        if (this.mOrientation != 0) {
            i = n;
        }
        if (this.getChildCount() != 0 && i != 0) {
            this.prepareLayoutStateForDelta(i, recyclerView$State);
            if (this.mPrefetchDistances == null || this.mPrefetchDistances.length < this.mSpanCount) {
                this.mPrefetchDistances = new int[this.mSpanCount];
            }
            int[] mPrefetchDistances;
            for (i = 0; i < this.mSpanCount; ++i) {
                mPrefetchDistances = this.mPrefetchDistances;
                if (this.mLayoutState.mItemDirection == -1) {
                    n = this.mLayoutState.mStartLine - this.mSpans[i].getStartLine(this.mLayoutState.mStartLine);
                }
                else {
                    n = this.mSpans[i].getEndLine(this.mLayoutState.mEndLine) - this.mLayoutState.mEndLine;
                }
                mPrefetchDistances[i] = n;
            }
            Arrays.sort(this.mPrefetchDistances, 0, this.mSpanCount);
            LayoutState mLayoutState;
            for (i = n2; i < this.mSpanCount && this.mLayoutState.hasMore(recyclerView$State); ++i) {
                recyclerView$LayoutManager$LayoutPrefetchRegistry.addPosition(this.mLayoutState.mCurrentPosition, this.mPrefetchDistances[i]);
                mLayoutState = this.mLayoutState;
                mLayoutState.mCurrentPosition += this.mLayoutState.mItemDirection;
            }
        }
    }
    
    @Override
    public int computeHorizontalScrollExtent(final RecyclerView$State recyclerView$State) {
        return this.computeScrollExtent(recyclerView$State);
    }
    
    @Override
    public int computeHorizontalScrollOffset(final RecyclerView$State recyclerView$State) {
        return this.computeScrollOffset(recyclerView$State);
    }
    
    @Override
    public int computeHorizontalScrollRange(final RecyclerView$State recyclerView$State) {
        return this.computeScrollRange(recyclerView$State);
    }
    
    @Override
    public PointF computeScrollVectorForPosition(int calculateScrollDirectionForPosition) {
        calculateScrollDirectionForPosition = this.calculateScrollDirectionForPosition(calculateScrollDirectionForPosition);
        final PointF pointF = new PointF();
        if (calculateScrollDirectionForPosition == 0) {
            return null;
        }
        if (this.mOrientation == 0) {
            pointF.x = calculateScrollDirectionForPosition;
            pointF.y = 0.0f;
            return pointF;
        }
        pointF.x = 0.0f;
        pointF.y = calculateScrollDirectionForPosition;
        return pointF;
    }
    
    @Override
    public int computeVerticalScrollExtent(final RecyclerView$State recyclerView$State) {
        return this.computeScrollExtent(recyclerView$State);
    }
    
    @Override
    public int computeVerticalScrollOffset(final RecyclerView$State recyclerView$State) {
        return this.computeScrollOffset(recyclerView$State);
    }
    
    @Override
    public int computeVerticalScrollRange(final RecyclerView$State recyclerView$State) {
        return this.computeScrollRange(recyclerView$State);
    }
    
    View findFirstVisibleItemClosestToEnd(final boolean b) {
        final int startAfterPadding = this.mPrimaryOrientation.getStartAfterPadding();
        final int endAfterPadding = this.mPrimaryOrientation.getEndAfterPadding();
        int i = this.getChildCount() - 1;
        View view = null;
        while (i >= 0) {
            final View child = this.getChildAt(i);
            final int decoratedStart = this.mPrimaryOrientation.getDecoratedStart(child);
            final int decoratedEnd = this.mPrimaryOrientation.getDecoratedEnd(child);
            View view2 = view;
            if (decoratedEnd > startAfterPadding) {
                if (decoratedStart >= endAfterPadding) {
                    view2 = view;
                }
                else {
                    if (decoratedEnd <= endAfterPadding || !b) {
                        return child;
                    }
                    if ((view2 = view) == null) {
                        view2 = child;
                    }
                }
            }
            --i;
            view = view2;
        }
        return view;
    }
    
    View findFirstVisibleItemClosestToStart(final boolean b) {
        final int startAfterPadding = this.mPrimaryOrientation.getStartAfterPadding();
        final int endAfterPadding = this.mPrimaryOrientation.getEndAfterPadding();
        final int childCount = this.getChildCount();
        int i = 0;
        View view = null;
        while (i < childCount) {
            final View child = this.getChildAt(i);
            final int decoratedStart = this.mPrimaryOrientation.getDecoratedStart(child);
            View view2 = view;
            if (this.mPrimaryOrientation.getDecoratedEnd(child) > startAfterPadding) {
                if (decoratedStart >= endAfterPadding) {
                    view2 = view;
                }
                else {
                    if (decoratedStart >= startAfterPadding || !b) {
                        return child;
                    }
                    if ((view2 = view) == null) {
                        view2 = child;
                    }
                }
            }
            ++i;
            view = view2;
        }
        return view;
    }
    
    int findFirstVisibleItemPositionInt() {
        View view;
        if (this.mShouldReverseLayout) {
            view = this.findFirstVisibleItemClosestToEnd(true);
        }
        else {
            view = this.findFirstVisibleItemClosestToStart(true);
        }
        if (view == null) {
            return -1;
        }
        return this.getPosition(view);
    }
    
    @Override
    public RecyclerView$LayoutParams generateDefaultLayoutParams() {
        if (this.mOrientation == 0) {
            return new StaggeredGridLayoutManager$LayoutParams(-2, -1);
        }
        return new StaggeredGridLayoutManager$LayoutParams(-1, -2);
    }
    
    @Override
    public RecyclerView$LayoutParams generateLayoutParams(final Context context, final AttributeSet set) {
        return new StaggeredGridLayoutManager$LayoutParams(context, set);
    }
    
    @Override
    public RecyclerView$LayoutParams generateLayoutParams(final ViewGroup$LayoutParams viewGroup$LayoutParams) {
        if (viewGroup$LayoutParams instanceof ViewGroup$MarginLayoutParams) {
            return new StaggeredGridLayoutManager$LayoutParams((ViewGroup$MarginLayoutParams)viewGroup$LayoutParams);
        }
        return new StaggeredGridLayoutManager$LayoutParams(viewGroup$LayoutParams);
    }
    
    @Override
    public int getColumnCountForAccessibility(final RecyclerView$Recycler recyclerView$Recycler, final RecyclerView$State recyclerView$State) {
        if (this.mOrientation == 1) {
            return this.mSpanCount;
        }
        return super.getColumnCountForAccessibility(recyclerView$Recycler, recyclerView$State);
    }
    
    int getFirstChildPosition() {
        if (this.getChildCount() == 0) {
            return 0;
        }
        return this.getPosition(this.getChildAt(0));
    }
    
    int getLastChildPosition() {
        final int childCount = this.getChildCount();
        if (childCount == 0) {
            return 0;
        }
        return this.getPosition(this.getChildAt(childCount - 1));
    }
    
    @Override
    public int getRowCountForAccessibility(final RecyclerView$Recycler recyclerView$Recycler, final RecyclerView$State recyclerView$State) {
        if (this.mOrientation == 0) {
            return this.mSpanCount;
        }
        return super.getRowCountForAccessibility(recyclerView$Recycler, recyclerView$State);
    }
    
    View hasGapsToFix() {
        int n = this.getChildCount() - 1;
        final BitSet set = new BitSet(this.mSpanCount);
        set.set(0, this.mSpanCount, true);
        int n2;
        if (this.mOrientation == 1 && this.isLayoutRTL()) {
            n2 = 1;
        }
        else {
            n2 = -1;
        }
        int n3;
        if (this.mShouldReverseLayout) {
            n3 = -1;
        }
        else {
            n3 = n + 1;
            n = 0;
        }
        int n4;
        if (n < n3) {
            n4 = 1;
        }
        else {
            n4 = -1;
        }
    Label_0164:
        for (int i = n; i != n3; i += n4) {
            final View child = this.getChildAt(i);
            final StaggeredGridLayoutManager$LayoutParams staggeredGridLayoutManager$LayoutParams = (StaggeredGridLayoutManager$LayoutParams)child.getLayoutParams();
            if (set.get(staggeredGridLayoutManager$LayoutParams.mSpan.mIndex)) {
                if (this.checkSpanForGap(staggeredGridLayoutManager$LayoutParams.mSpan)) {
                    return child;
                }
                set.clear(staggeredGridLayoutManager$LayoutParams.mSpan.mIndex);
            }
            if (!staggeredGridLayoutManager$LayoutParams.mFullSpan && i + n4 != n3) {
                final View child2 = this.getChildAt(i + n4);
                while (true) {
                    Label_0345: {
                        int n5;
                        if (this.mShouldReverseLayout) {
                            final int decoratedEnd = this.mPrimaryOrientation.getDecoratedEnd(child);
                            final int decoratedEnd2 = this.mPrimaryOrientation.getDecoratedEnd(child2);
                            if (decoratedEnd < decoratedEnd2) {
                                return child;
                            }
                            if (decoratedEnd != decoratedEnd2) {
                                break Label_0345;
                            }
                            n5 = 1;
                        }
                        else {
                            final int decoratedStart = this.mPrimaryOrientation.getDecoratedStart(child);
                            final int decoratedStart2 = this.mPrimaryOrientation.getDecoratedStart(child2);
                            if (decoratedStart > decoratedStart2) {
                                return child;
                            }
                            if (decoratedStart != decoratedStart2) {
                                break Label_0345;
                            }
                            n5 = 1;
                        }
                        if (n5 == 0) {
                            continue Label_0164;
                        }
                        int n6;
                        if (staggeredGridLayoutManager$LayoutParams.mSpan.mIndex - ((StaggeredGridLayoutManager$LayoutParams)child2.getLayoutParams()).mSpan.mIndex < 0) {
                            n6 = 1;
                        }
                        else {
                            n6 = 0;
                        }
                        int n7;
                        if (n2 < 0) {
                            n7 = 1;
                        }
                        else {
                            n7 = 0;
                        }
                        if (n6 != n7) {
                            return child;
                        }
                        continue Label_0164;
                    }
                    int n5 = 0;
                    continue;
                }
            }
        }
        return null;
    }
    
    public void invalidateSpanAssignments() {
        this.mLazySpanLookup.clear();
        this.requestLayout();
    }
    
    boolean isLayoutRTL() {
        return this.getLayoutDirection() == 1;
    }
    
    @Override
    public void offsetChildrenHorizontal(final int n) {
        super.offsetChildrenHorizontal(n);
        for (int i = 0; i < this.mSpanCount; ++i) {
            this.mSpans[i].onOffset(n);
        }
    }
    
    @Override
    public void offsetChildrenVertical(final int n) {
        super.offsetChildrenVertical(n);
        for (int i = 0; i < this.mSpanCount; ++i) {
            this.mSpans[i].onOffset(n);
        }
    }
    
    @Override
    public void onDetachedFromWindow(final RecyclerView recyclerView, final RecyclerView$Recycler recyclerView$Recycler) {
        this.removeCallbacks(this.mCheckForGapsRunnable);
        for (int i = 0; i < this.mSpanCount; ++i) {
            this.mSpans[i].clear();
        }
        recyclerView.requestLayout();
    }
    
    @Override
    public View onFocusSearchFailed(View containingItemView, int n, final RecyclerView$Recycler recyclerView$Recycler, final RecyclerView$State recyclerView$State) {
        int i = 0;
        if (this.getChildCount() == 0) {
            return null;
        }
        containingItemView = this.findContainingItemView(containingItemView);
        if (containingItemView == null) {
            return null;
        }
        this.resolveShouldLayoutReverse();
        final int convertFocusDirectionToLayoutDirection = this.convertFocusDirectionToLayoutDirection(n);
        if (convertFocusDirectionToLayoutDirection == Integer.MIN_VALUE) {
            return null;
        }
        final StaggeredGridLayoutManager$LayoutParams staggeredGridLayoutManager$LayoutParams = (StaggeredGridLayoutManager$LayoutParams)containingItemView.getLayoutParams();
        final boolean mFullSpan = staggeredGridLayoutManager$LayoutParams.mFullSpan;
        final StaggeredGridLayoutManager$Span mSpan = staggeredGridLayoutManager$LayoutParams.mSpan;
        if (convertFocusDirectionToLayoutDirection == 1) {
            n = this.getLastChildPosition();
        }
        else {
            n = this.getFirstChildPosition();
        }
        this.updateLayoutState(n, recyclerView$State);
        this.setLayoutStateDirection(convertFocusDirectionToLayoutDirection);
        this.mLayoutState.mCurrentPosition = this.mLayoutState.mItemDirection + n;
        this.mLayoutState.mAvailable = (int)(0.33333334f * this.mPrimaryOrientation.getTotalSpace());
        this.mLayoutState.mStopInFocusable = true;
        this.mLayoutState.mRecycle = false;
        this.fill(recyclerView$Recycler, this.mLayoutState, recyclerView$State);
        this.mLastLayoutFromEnd = this.mShouldReverseLayout;
        if (!mFullSpan) {
            final View focusableViewAfter = mSpan.getFocusableViewAfter(n, convertFocusDirectionToLayoutDirection);
            if (focusableViewAfter != null && focusableViewAfter != containingItemView) {
                return focusableViewAfter;
            }
        }
        if (this.preferLastSpan(convertFocusDirectionToLayoutDirection)) {
            for (int j = this.mSpanCount - 1; j >= 0; --j) {
                final View focusableViewAfter2 = this.mSpans[j].getFocusableViewAfter(n, convertFocusDirectionToLayoutDirection);
                if (focusableViewAfter2 != null && focusableViewAfter2 != containingItemView) {
                    return focusableViewAfter2;
                }
            }
        }
        else {
            while (i < this.mSpanCount) {
                final View focusableViewAfter3 = this.mSpans[i].getFocusableViewAfter(n, convertFocusDirectionToLayoutDirection);
                if (focusableViewAfter3 != null && focusableViewAfter3 != containingItemView) {
                    return focusableViewAfter3;
                }
                ++i;
            }
        }
        return null;
    }
    
    @Override
    public void onInitializeAccessibilityEvent(final AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        if (this.getChildCount() > 0) {
            final AccessibilityRecordCompat record = AccessibilityEventCompat.asRecord(accessibilityEvent);
            final View firstVisibleItemClosestToStart = this.findFirstVisibleItemClosestToStart(false);
            final View firstVisibleItemClosestToEnd = this.findFirstVisibleItemClosestToEnd(false);
            if (firstVisibleItemClosestToStart != null && firstVisibleItemClosestToEnd != null) {
                final int position = this.getPosition(firstVisibleItemClosestToStart);
                final int position2 = this.getPosition(firstVisibleItemClosestToEnd);
                if (position < position2) {
                    record.setFromIndex(position);
                    record.setToIndex(position2);
                    return;
                }
                record.setFromIndex(position2);
                record.setToIndex(position);
            }
        }
    }
    
    @Override
    public void onInitializeAccessibilityNodeInfoForItem(final RecyclerView$Recycler recyclerView$Recycler, final RecyclerView$State recyclerView$State, final View view, final AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        final ViewGroup$LayoutParams layoutParams = view.getLayoutParams();
        if (!(layoutParams instanceof StaggeredGridLayoutManager$LayoutParams)) {
            super.onInitializeAccessibilityNodeInfoForItem(view, accessibilityNodeInfoCompat);
            return;
        }
        final StaggeredGridLayoutManager$LayoutParams staggeredGridLayoutManager$LayoutParams = (StaggeredGridLayoutManager$LayoutParams)layoutParams;
        if (this.mOrientation == 0) {
            final int spanIndex = staggeredGridLayoutManager$LayoutParams.getSpanIndex();
            int mSpanCount;
            if (staggeredGridLayoutManager$LayoutParams.mFullSpan) {
                mSpanCount = this.mSpanCount;
            }
            else {
                mSpanCount = 1;
            }
            accessibilityNodeInfoCompat.setCollectionItemInfo(AccessibilityNodeInfoCompat$CollectionItemInfoCompat.obtain(spanIndex, mSpanCount, -1, -1, staggeredGridLayoutManager$LayoutParams.mFullSpan, false));
            return;
        }
        final int spanIndex2 = staggeredGridLayoutManager$LayoutParams.getSpanIndex();
        int mSpanCount2;
        if (staggeredGridLayoutManager$LayoutParams.mFullSpan) {
            mSpanCount2 = this.mSpanCount;
        }
        else {
            mSpanCount2 = 1;
        }
        accessibilityNodeInfoCompat.setCollectionItemInfo(AccessibilityNodeInfoCompat$CollectionItemInfoCompat.obtain(-1, -1, spanIndex2, mSpanCount2, staggeredGridLayoutManager$LayoutParams.mFullSpan, false));
    }
    
    @Override
    public void onItemsAdded(final RecyclerView recyclerView, final int n, final int n2) {
        this.handleUpdate(n, n2, 1);
    }
    
    @Override
    public void onItemsChanged(final RecyclerView recyclerView) {
        this.mLazySpanLookup.clear();
        this.requestLayout();
    }
    
    @Override
    public void onItemsMoved(final RecyclerView recyclerView, final int n, final int n2, final int n3) {
        this.handleUpdate(n, n2, 8);
    }
    
    @Override
    public void onItemsRemoved(final RecyclerView recyclerView, final int n, final int n2) {
        this.handleUpdate(n, n2, 2);
    }
    
    @Override
    public void onItemsUpdated(final RecyclerView recyclerView, final int n, final int n2, final Object o) {
        this.handleUpdate(n, n2, 4);
    }
    
    @Override
    public void onLayoutChildren(final RecyclerView$Recycler recyclerView$Recycler, final RecyclerView$State recyclerView$State) {
        this.onLayoutChildren(recyclerView$Recycler, recyclerView$State, true);
    }
    
    @Override
    public void onLayoutCompleted(final RecyclerView$State recyclerView$State) {
        super.onLayoutCompleted(recyclerView$State);
        this.mPendingScrollPosition = -1;
        this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
        this.mPendingSavedState = null;
        this.mAnchorInfo.reset();
    }
    
    @Override
    public void onRestoreInstanceState(final Parcelable parcelable) {
        if (parcelable instanceof StaggeredGridLayoutManager$SavedState) {
            this.mPendingSavedState = (StaggeredGridLayoutManager$SavedState)parcelable;
            this.requestLayout();
        }
    }
    
    @Override
    public Parcelable onSaveInstanceState() {
        if (this.mPendingSavedState != null) {
            return (Parcelable)new StaggeredGridLayoutManager$SavedState(this.mPendingSavedState);
        }
        final StaggeredGridLayoutManager$SavedState staggeredGridLayoutManager$SavedState = new StaggeredGridLayoutManager$SavedState();
        staggeredGridLayoutManager$SavedState.mReverseLayout = this.mReverseLayout;
        staggeredGridLayoutManager$SavedState.mAnchorLayoutFromEnd = this.mLastLayoutFromEnd;
        staggeredGridLayoutManager$SavedState.mLastLayoutRTL = this.mLastLayoutRTL;
        if (this.mLazySpanLookup != null && this.mLazySpanLookup.mData != null) {
            staggeredGridLayoutManager$SavedState.mSpanLookup = this.mLazySpanLookup.mData;
            staggeredGridLayoutManager$SavedState.mSpanLookupSize = staggeredGridLayoutManager$SavedState.mSpanLookup.length;
            staggeredGridLayoutManager$SavedState.mFullSpanItems = this.mLazySpanLookup.mFullSpanItems;
        }
        else {
            staggeredGridLayoutManager$SavedState.mSpanLookupSize = 0;
        }
        if (this.getChildCount() > 0) {
            int mAnchorPosition;
            if (this.mLastLayoutFromEnd) {
                mAnchorPosition = this.getLastChildPosition();
            }
            else {
                mAnchorPosition = this.getFirstChildPosition();
            }
            staggeredGridLayoutManager$SavedState.mAnchorPosition = mAnchorPosition;
            staggeredGridLayoutManager$SavedState.mVisibleAnchorPosition = this.findFirstVisibleItemPositionInt();
            staggeredGridLayoutManager$SavedState.mSpanOffsetsSize = this.mSpanCount;
            staggeredGridLayoutManager$SavedState.mSpanOffsets = new int[this.mSpanCount];
            for (int i = 0; i < this.mSpanCount; ++i) {
                int n;
                if (this.mLastLayoutFromEnd) {
                    final int endLine = this.mSpans[i].getEndLine(Integer.MIN_VALUE);
                    if ((n = endLine) != Integer.MIN_VALUE) {
                        n = endLine - this.mPrimaryOrientation.getEndAfterPadding();
                    }
                }
                else {
                    final int startLine = this.mSpans[i].getStartLine(Integer.MIN_VALUE);
                    if ((n = startLine) != Integer.MIN_VALUE) {
                        n = startLine - this.mPrimaryOrientation.getStartAfterPadding();
                    }
                }
                staggeredGridLayoutManager$SavedState.mSpanOffsets[i] = n;
            }
        }
        else {
            staggeredGridLayoutManager$SavedState.mAnchorPosition = -1;
            staggeredGridLayoutManager$SavedState.mVisibleAnchorPosition = -1;
            staggeredGridLayoutManager$SavedState.mSpanOffsetsSize = 0;
        }
        return (Parcelable)staggeredGridLayoutManager$SavedState;
    }
    
    @Override
    public void onScrollStateChanged(final int n) {
        if (n == 0) {
            this.checkForGaps();
        }
    }
    
    void prepareLayoutStateForDelta(final int n, final RecyclerView$State recyclerView$State) {
        int n2;
        int layoutStateDirection;
        if (n > 0) {
            n2 = this.getLastChildPosition();
            layoutStateDirection = 1;
        }
        else {
            layoutStateDirection = -1;
            n2 = this.getFirstChildPosition();
        }
        this.mLayoutState.mRecycle = true;
        this.updateLayoutState(n2, recyclerView$State);
        this.setLayoutStateDirection(layoutStateDirection);
        this.mLayoutState.mCurrentPosition = this.mLayoutState.mItemDirection + n2;
        this.mLayoutState.mAvailable = Math.abs(n);
    }
    
    int scrollBy(int n, final RecyclerView$Recycler recyclerView$Recycler, final RecyclerView$State recyclerView$State) {
        if (this.getChildCount() == 0 || n == 0) {
            return 0;
        }
        this.prepareLayoutStateForDelta(n, recyclerView$State);
        final int fill = this.fill(recyclerView$Recycler, this.mLayoutState, recyclerView$State);
        if (this.mLayoutState.mAvailable >= fill) {
            if (n < 0) {
                n = -fill;
            }
            else {
                n = fill;
            }
        }
        this.mPrimaryOrientation.offsetChildren(-n);
        this.mLastLayoutFromEnd = this.mShouldReverseLayout;
        this.mLayoutState.mAvailable = 0;
        this.recycle(recyclerView$Recycler, this.mLayoutState);
        return n;
    }
    
    @Override
    public int scrollHorizontallyBy(final int n, final RecyclerView$Recycler recyclerView$Recycler, final RecyclerView$State recyclerView$State) {
        return this.scrollBy(n, recyclerView$Recycler, recyclerView$State);
    }
    
    @Override
    public void scrollToPosition(final int mPendingScrollPosition) {
        if (this.mPendingSavedState != null && this.mPendingSavedState.mAnchorPosition != mPendingScrollPosition) {
            this.mPendingSavedState.invalidateAnchorPositionInfo();
        }
        this.mPendingScrollPosition = mPendingScrollPosition;
        this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
        this.requestLayout();
    }
    
    @Override
    public int scrollVerticallyBy(final int n, final RecyclerView$Recycler recyclerView$Recycler, final RecyclerView$State recyclerView$State) {
        return this.scrollBy(n, recyclerView$Recycler, recyclerView$State);
    }
    
    @Override
    public void setMeasuredDimension(final Rect rect, int chooseSize, int chooseSize2) {
        final int n = this.getPaddingRight() + this.getPaddingLeft();
        final int n2 = this.getPaddingTop() + this.getPaddingBottom();
        if (this.mOrientation == 1) {
            final int chooseSize3 = RecyclerView$LayoutManager.chooseSize(chooseSize2, n2 + rect.height(), this.getMinimumHeight());
            chooseSize2 = RecyclerView$LayoutManager.chooseSize(chooseSize, n + this.mSizePerSpan * this.mSpanCount, this.getMinimumWidth());
            chooseSize = chooseSize3;
        }
        else {
            final int chooseSize4 = RecyclerView$LayoutManager.chooseSize(chooseSize, n + rect.width(), this.getMinimumWidth());
            chooseSize = RecyclerView$LayoutManager.chooseSize(chooseSize2, n2 + this.mSizePerSpan * this.mSpanCount, this.getMinimumHeight());
            chooseSize2 = chooseSize4;
        }
        this.setMeasuredDimension(chooseSize2, chooseSize);
    }
    
    public void setOrientation(final int mOrientation) {
        if (mOrientation != 0 && mOrientation != 1) {
            throw new IllegalArgumentException("invalid orientation.");
        }
        this.assertNotInLayoutOrScroll(null);
        if (mOrientation == this.mOrientation) {
            return;
        }
        this.mOrientation = mOrientation;
        final OrientationHelper mPrimaryOrientation = this.mPrimaryOrientation;
        this.mPrimaryOrientation = this.mSecondaryOrientation;
        this.mSecondaryOrientation = mPrimaryOrientation;
        this.requestLayout();
    }
    
    public void setReverseLayout(final boolean b) {
        this.assertNotInLayoutOrScroll(null);
        if (this.mPendingSavedState != null && this.mPendingSavedState.mReverseLayout != b) {
            this.mPendingSavedState.mReverseLayout = b;
        }
        this.mReverseLayout = b;
        this.requestLayout();
    }
    
    public void setSpanCount(int i) {
        this.assertNotInLayoutOrScroll(null);
        if (i != this.mSpanCount) {
            this.invalidateSpanAssignments();
            this.mSpanCount = i;
            this.mRemainingSpans = new BitSet(this.mSpanCount);
            this.mSpans = new StaggeredGridLayoutManager$Span[this.mSpanCount];
            for (i = 0; i < this.mSpanCount; ++i) {
                this.mSpans[i] = new StaggeredGridLayoutManager$Span(this, i);
            }
            this.requestLayout();
        }
    }
    
    @Override
    public void smoothScrollToPosition(final RecyclerView recyclerView, final RecyclerView$State recyclerView$State, final int targetPosition) {
        final LinearSmoothScroller linearSmoothScroller = new LinearSmoothScroller(recyclerView.getContext());
        linearSmoothScroller.setTargetPosition(targetPosition);
        this.startSmoothScroll(linearSmoothScroller);
    }
    
    @Override
    public boolean supportsPredictiveItemAnimations() {
        return this.mPendingSavedState == null;
    }
    
    boolean updateAnchorFromPendingData(final RecyclerView$State recyclerView$State, final StaggeredGridLayoutManager$AnchorInfo staggeredGridLayoutManager$AnchorInfo) {
        boolean mLayoutFromEnd = false;
        if (recyclerView$State.isPreLayout() || this.mPendingScrollPosition == -1) {
            return false;
        }
        if (this.mPendingScrollPosition < 0 || this.mPendingScrollPosition >= recyclerView$State.getItemCount()) {
            this.mPendingScrollPosition = -1;
            this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
            return false;
        }
        if (this.mPendingSavedState != null && this.mPendingSavedState.mAnchorPosition != -1 && this.mPendingSavedState.mSpanOffsetsSize >= 1) {
            staggeredGridLayoutManager$AnchorInfo.mOffset = Integer.MIN_VALUE;
            staggeredGridLayoutManager$AnchorInfo.mPosition = this.mPendingScrollPosition;
            return true;
        }
        final View viewByPosition = this.findViewByPosition(this.mPendingScrollPosition);
        if (viewByPosition == null) {
            staggeredGridLayoutManager$AnchorInfo.mPosition = this.mPendingScrollPosition;
            if (this.mPendingScrollPositionOffset == Integer.MIN_VALUE) {
                if (this.calculateScrollDirectionForPosition(staggeredGridLayoutManager$AnchorInfo.mPosition) == 1) {
                    mLayoutFromEnd = true;
                }
                staggeredGridLayoutManager$AnchorInfo.mLayoutFromEnd = mLayoutFromEnd;
                staggeredGridLayoutManager$AnchorInfo.assignCoordinateFromPadding();
            }
            else {
                staggeredGridLayoutManager$AnchorInfo.assignCoordinateFromPadding(this.mPendingScrollPositionOffset);
            }
            return staggeredGridLayoutManager$AnchorInfo.mInvalidateOffsets = true;
        }
        int mPosition;
        if (this.mShouldReverseLayout) {
            mPosition = this.getLastChildPosition();
        }
        else {
            mPosition = this.getFirstChildPosition();
        }
        staggeredGridLayoutManager$AnchorInfo.mPosition = mPosition;
        if (this.mPendingScrollPositionOffset != Integer.MIN_VALUE) {
            if (staggeredGridLayoutManager$AnchorInfo.mLayoutFromEnd) {
                staggeredGridLayoutManager$AnchorInfo.mOffset = this.mPrimaryOrientation.getEndAfterPadding() - this.mPendingScrollPositionOffset - this.mPrimaryOrientation.getDecoratedEnd(viewByPosition);
                return true;
            }
            staggeredGridLayoutManager$AnchorInfo.mOffset = this.mPrimaryOrientation.getStartAfterPadding() + this.mPendingScrollPositionOffset - this.mPrimaryOrientation.getDecoratedStart(viewByPosition);
            return true;
        }
        else {
            if (this.mPrimaryOrientation.getDecoratedMeasurement(viewByPosition) > this.mPrimaryOrientation.getTotalSpace()) {
                int mOffset;
                if (staggeredGridLayoutManager$AnchorInfo.mLayoutFromEnd) {
                    mOffset = this.mPrimaryOrientation.getEndAfterPadding();
                }
                else {
                    mOffset = this.mPrimaryOrientation.getStartAfterPadding();
                }
                staggeredGridLayoutManager$AnchorInfo.mOffset = mOffset;
                return true;
            }
            final int n = this.mPrimaryOrientation.getDecoratedStart(viewByPosition) - this.mPrimaryOrientation.getStartAfterPadding();
            if (n < 0) {
                staggeredGridLayoutManager$AnchorInfo.mOffset = -n;
                return true;
            }
            final int mOffset2 = this.mPrimaryOrientation.getEndAfterPadding() - this.mPrimaryOrientation.getDecoratedEnd(viewByPosition);
            if (mOffset2 < 0) {
                staggeredGridLayoutManager$AnchorInfo.mOffset = mOffset2;
                return true;
            }
            staggeredGridLayoutManager$AnchorInfo.mOffset = Integer.MIN_VALUE;
            return true;
        }
    }
    
    void updateAnchorInfoForLayout(final RecyclerView$State recyclerView$State, final StaggeredGridLayoutManager$AnchorInfo staggeredGridLayoutManager$AnchorInfo) {
        if (!this.updateAnchorFromPendingData(recyclerView$State, staggeredGridLayoutManager$AnchorInfo) && !this.updateAnchorFromChildren(recyclerView$State, staggeredGridLayoutManager$AnchorInfo)) {
            staggeredGridLayoutManager$AnchorInfo.assignCoordinateFromPadding();
            staggeredGridLayoutManager$AnchorInfo.mPosition = 0;
        }
    }
    
    void updateMeasureSpecs(final int n) {
        this.mSizePerSpan = n / this.mSpanCount;
        this.mFullSizeSpec = View$MeasureSpec.makeMeasureSpec(n, this.mSecondaryOrientation.getMode());
    }
}
