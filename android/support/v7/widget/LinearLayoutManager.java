// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.os.Parcelable;
import android.support.v4.view.accessibility.AccessibilityRecordCompat;
import android.support.v4.view.accessibility.AccessibilityEventCompat;
import android.view.accessibility.AccessibilityEvent;
import android.graphics.PointF;
import android.util.Log;
import java.util.List;
import android.view.View;
import android.content.Context;

public class LinearLayoutManager extends RecyclerView$LayoutManager
{
    private static final boolean DEBUG = false;
    public static final int HORIZONTAL = 0;
    public static final int INVALID_OFFSET = Integer.MIN_VALUE;
    private static final float MAX_SCROLL_FACTOR = 0.33f;
    private static final String TAG = "LinearLayoutManager";
    public static final int VERTICAL = 1;
    final LinearLayoutManager$AnchorInfo mAnchorInfo;
    private boolean mLastStackFromEnd;
    private LinearLayoutManager$LayoutState mLayoutState;
    int mOrientation;
    OrientationHelper mOrientationHelper;
    LinearLayoutManager$SavedState mPendingSavedState;
    int mPendingScrollPosition;
    int mPendingScrollPositionOffset;
    private boolean mRecycleChildrenOnDetach;
    private boolean mReverseLayout;
    boolean mShouldReverseLayout;
    private boolean mSmoothScrollbarEnabled;
    private boolean mStackFromEnd;
    
    public LinearLayoutManager(final Context context) {
        this(context, 1, false);
    }
    
    public LinearLayoutManager(final Context context, final int orientation, final boolean reverseLayout) {
        this.mReverseLayout = false;
        this.mShouldReverseLayout = false;
        this.mStackFromEnd = false;
        this.mSmoothScrollbarEnabled = true;
        this.mPendingScrollPosition = -1;
        this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
        this.mPendingSavedState = null;
        this.mAnchorInfo = new LinearLayoutManager$AnchorInfo(this);
        this.setOrientation(orientation);
        this.setReverseLayout(reverseLayout);
    }
    
    private int computeScrollExtent(final RecyclerView$State recyclerView$State) {
        if (this.getChildCount() == 0) {
            return 0;
        }
        this.ensureLayoutState();
        return ScrollbarHelper.computeScrollExtent(recyclerView$State, this.mOrientationHelper, this.getChildClosestToStart(), this.getChildClosestToEnd(), this, this.mSmoothScrollbarEnabled);
    }
    
    private int computeScrollOffset(final RecyclerView$State recyclerView$State) {
        if (this.getChildCount() == 0) {
            return 0;
        }
        this.ensureLayoutState();
        return ScrollbarHelper.computeScrollOffset(recyclerView$State, this.mOrientationHelper, this.getChildClosestToStart(), this.getChildClosestToEnd(), this, this.mSmoothScrollbarEnabled, this.mShouldReverseLayout);
    }
    
    private int computeScrollRange(final RecyclerView$State recyclerView$State) {
        if (this.getChildCount() == 0) {
            return 0;
        }
        this.ensureLayoutState();
        return ScrollbarHelper.computeScrollRange(recyclerView$State, this.mOrientationHelper, this.getChildClosestToStart(), this.getChildClosestToEnd(), this, this.mSmoothScrollbarEnabled);
    }
    
    private int convertFocusDirectionToLayoutDirection(int n) {
        final int n2 = -1;
        final int n3 = 1;
        final int n4 = Integer.MIN_VALUE;
        int n5 = n2;
        switch (n) {
            default: {
                n5 = Integer.MIN_VALUE;
                return n5;
            }
            case 1: {
                return n5;
            }
            case 2: {
                return 1;
            }
            case 33: {
                n5 = n2;
                if (this.mOrientation != 1) {
                    return Integer.MIN_VALUE;
                }
                return n5;
            }
            case 130: {
                n = n4;
                if (this.mOrientation == 1) {
                    n = 1;
                }
                return n;
            }
            case 17: {
                n5 = n2;
                if (this.mOrientation != 0) {
                    return Integer.MIN_VALUE;
                }
                return n5;
            }
            case 66: {
                if (this.mOrientation == 0) {
                    n = n3;
                }
                else {
                    n = Integer.MIN_VALUE;
                }
                return n;
            }
        }
    }
    
    private View findFirstReferenceChild(final int n) {
        return this.findReferenceChild(0, this.getChildCount(), n);
    }
    
    private View findLastReferenceChild(final int n) {
        return this.findReferenceChild(this.getChildCount() - 1, -1, n);
    }
    
    private View findReferenceChild(int i, final int n, final int n2) {
        View view = null;
        this.ensureLayoutState();
        final int startAfterPadding = this.mOrientationHelper.getStartAfterPadding();
        final int endAfterPadding = this.mOrientationHelper.getEndAfterPadding();
        int n3;
        if (n > i) {
            n3 = 1;
        }
        else {
            n3 = -1;
        }
        View view2 = null;
    Label_0094_Outer:
        while (i != n) {
            final View child = this.getChildAt(i);
            final int position = this.getPosition(child);
            while (true) {
                Label_0187: {
                    if (position < 0 || position >= n2) {
                        break Label_0187;
                    }
                    View view3;
                    View view4;
                    if (((RecyclerView$LayoutParams)child.getLayoutParams()).isItemRemoved()) {
                        if (view2 != null) {
                            break Label_0187;
                        }
                        view3 = view;
                        view4 = child;
                    }
                    else {
                        if (this.mOrientationHelper.getDecoratedStart(child) < endAfterPadding) {
                            final View view5 = child;
                            if (this.mOrientationHelper.getDecoratedEnd(child) >= startAfterPadding) {
                                return view5;
                            }
                        }
                        if (view != null) {
                            break Label_0187;
                        }
                        view4 = view2;
                        view3 = child;
                    }
                    i += n3;
                    final View view6 = view4;
                    view = view3;
                    view2 = view6;
                    continue Label_0094_Outer;
                }
                final View view7 = view2;
                View view3 = view;
                View view4 = view7;
                continue;
            }
        }
        if (view == null) {
            view = view2;
        }
        return view;
    }
    
    private View findReferenceChildClosestToEnd(final RecyclerView$State recyclerView$State) {
        if (this.mShouldReverseLayout) {
            return this.findFirstReferenceChild(recyclerView$State.getItemCount());
        }
        return this.findLastReferenceChild(recyclerView$State.getItemCount());
    }
    
    private View findReferenceChildClosestToStart(final RecyclerView$State recyclerView$State) {
        if (this.mShouldReverseLayout) {
            return this.findLastReferenceChild(recyclerView$State.getItemCount());
        }
        return this.findFirstReferenceChild(recyclerView$State.getItemCount());
    }
    
    private int fixLayoutEndGap(int n, final RecyclerView$Recycler recyclerView$Recycler, final RecyclerView$State recyclerView$State, final boolean b) {
        final int n2 = this.mOrientationHelper.getEndAfterPadding() - n;
        if (n2 > 0) {
            int n4;
            final int n3 = n4 = -this.scrollBy(-n2, recyclerView$Recycler, recyclerView$State);
            if (b) {
                n = this.mOrientationHelper.getEndAfterPadding() - (n + n3);
                n4 = n3;
                if (n > 0) {
                    this.mOrientationHelper.offsetChildren(n);
                    n4 = n3 + n;
                }
            }
            return n4;
        }
        return 0;
    }
    
    private int fixLayoutStartGap(int n, final RecyclerView$Recycler recyclerView$Recycler, final RecyclerView$State recyclerView$State, final boolean b) {
        final int n2 = n - this.mOrientationHelper.getStartAfterPadding();
        if (n2 > 0) {
            int n4;
            final int n3 = n4 = -this.scrollBy(n2, recyclerView$Recycler, recyclerView$State);
            if (b) {
                n = n + n3 - this.mOrientationHelper.getStartAfterPadding();
                n4 = n3;
                if (n > 0) {
                    this.mOrientationHelper.offsetChildren(-n);
                    n4 = n3 - n;
                }
            }
            return n4;
        }
        return 0;
    }
    
    private View getChildClosestToEnd() {
        int n;
        if (this.mShouldReverseLayout) {
            n = 0;
        }
        else {
            n = this.getChildCount() - 1;
        }
        return this.getChildAt(n);
    }
    
    private View getChildClosestToStart() {
        int n;
        if (this.mShouldReverseLayout) {
            n = this.getChildCount() - 1;
        }
        else {
            n = 0;
        }
        return this.getChildAt(n);
    }
    
    private void layoutForPredictiveAnimations(final RecyclerView$Recycler recyclerView$Recycler, final RecyclerView$State recyclerView$State, int n, int mCurrentPosition) {
        if (!recyclerView$State.willRunPredictiveAnimations() || this.getChildCount() == 0 || recyclerView$State.isPreLayout() || !this.supportsPredictiveItemAnimations()) {
            return;
        }
        int mExtra = 0;
        int mExtra2 = 0;
        final List<RecyclerView$ViewHolder> scrapList = recyclerView$Recycler.getScrapList();
        final int size = scrapList.size();
        final int position = this.getPosition(this.getChildAt(0));
        for (int i = 0; i < size; ++i) {
            final RecyclerView$ViewHolder recyclerView$ViewHolder = scrapList.get(i);
            int n2;
            if (recyclerView$ViewHolder.getPosition() < position != this.mShouldReverseLayout) {
                n2 = -1;
            }
            else {
                n2 = 1;
            }
            if (n2 == -1) {
                mExtra += this.mOrientationHelper.getDecoratedMeasurement(recyclerView$ViewHolder.itemView);
            }
            else {
                mExtra2 += this.mOrientationHelper.getDecoratedMeasurement(recyclerView$ViewHolder.itemView);
            }
        }
        this.mLayoutState.mScrapList = scrapList;
        if (mExtra > 0) {
            this.updateLayoutStateToFillStart(this.getPosition(this.getChildClosestToStart()), n);
            this.mLayoutState.mExtra = mExtra;
            this.mLayoutState.mAvailable = 0;
            final LinearLayoutManager$LayoutState mLayoutState = this.mLayoutState;
            final int mCurrentPosition2 = mLayoutState.mCurrentPosition;
            if (this.mShouldReverseLayout) {
                n = 1;
            }
            else {
                n = -1;
            }
            mLayoutState.mCurrentPosition = n + mCurrentPosition2;
            this.fill(recyclerView$Recycler, this.mLayoutState, recyclerView$State, false);
        }
        if (mExtra2 > 0) {
            this.updateLayoutStateToFillEnd(this.getPosition(this.getChildClosestToEnd()), mCurrentPosition);
            this.mLayoutState.mExtra = mExtra2;
            this.mLayoutState.mAvailable = 0;
            final LinearLayoutManager$LayoutState mLayoutState2 = this.mLayoutState;
            mCurrentPosition = mLayoutState2.mCurrentPosition;
            if (this.mShouldReverseLayout) {
                n = -1;
            }
            else {
                n = 1;
            }
            mLayoutState2.mCurrentPosition = n + mCurrentPosition;
            this.fill(recyclerView$Recycler, this.mLayoutState, recyclerView$State, false);
        }
        this.mLayoutState.mScrapList = null;
    }
    
    private void logChildren() {
        Log.d("LinearLayoutManager", "internal representation of views on the screen");
        for (int i = 0; i < this.getChildCount(); ++i) {
            final View child = this.getChildAt(i);
            Log.d("LinearLayoutManager", "item " + this.getPosition(child) + ", coord:" + this.mOrientationHelper.getDecoratedStart(child));
        }
        Log.d("LinearLayoutManager", "==============");
    }
    
    private void recycleByLayoutState(final RecyclerView$Recycler recyclerView$Recycler, final LinearLayoutManager$LayoutState linearLayoutManager$LayoutState) {
        if (!linearLayoutManager$LayoutState.mRecycle) {
            return;
        }
        if (linearLayoutManager$LayoutState.mLayoutDirection == -1) {
            this.recycleViewsFromEnd(recyclerView$Recycler, linearLayoutManager$LayoutState.mScrollingOffset);
            return;
        }
        this.recycleViewsFromStart(recyclerView$Recycler, linearLayoutManager$LayoutState.mScrollingOffset);
    }
    
    private void recycleChildren(final RecyclerView$Recycler recyclerView$Recycler, final int n, int i) {
        if (n != i) {
            int j;
            if (i > (j = n)) {
                for (--i; i >= n; --i) {
                    this.removeAndRecycleViewAt(i, recyclerView$Recycler);
                }
            }
            else {
                while (j > i) {
                    this.removeAndRecycleViewAt(j, recyclerView$Recycler);
                    --j;
                }
            }
        }
    }
    
    private void recycleViewsFromEnd(final RecyclerView$Recycler recyclerView$Recycler, int i) {
        final int childCount = this.getChildCount();
        if (i >= 0) {
            final int n = this.mOrientationHelper.getEnd() - i;
            if (this.mShouldReverseLayout) {
                for (i = 0; i < childCount; ++i) {
                    if (this.mOrientationHelper.getDecoratedStart(this.getChildAt(i)) < n) {
                        this.recycleChildren(recyclerView$Recycler, 0, i);
                        return;
                    }
                }
            }
            else {
                for (i = childCount - 1; i >= 0; --i) {
                    if (this.mOrientationHelper.getDecoratedStart(this.getChildAt(i)) < n) {
                        this.recycleChildren(recyclerView$Recycler, childCount - 1, i);
                        return;
                    }
                }
            }
        }
    }
    
    private void recycleViewsFromStart(final RecyclerView$Recycler recyclerView$Recycler, final int n) {
        if (n >= 0) {
            final int childCount = this.getChildCount();
            if (this.mShouldReverseLayout) {
                for (int i = childCount - 1; i >= 0; --i) {
                    if (this.mOrientationHelper.getDecoratedEnd(this.getChildAt(i)) > n) {
                        this.recycleChildren(recyclerView$Recycler, childCount - 1, i);
                        return;
                    }
                }
            }
            else {
                for (int j = 0; j < childCount; ++j) {
                    if (this.mOrientationHelper.getDecoratedEnd(this.getChildAt(j)) > n) {
                        this.recycleChildren(recyclerView$Recycler, 0, j);
                        return;
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
    
    private boolean updateAnchorFromChildren(final RecyclerView$State recyclerView$State, final LinearLayoutManager$AnchorInfo linearLayoutManager$AnchorInfo) {
        boolean b = false;
        if (this.getChildCount() != 0) {
            final View focusedChild = this.getFocusedChild();
            if (focusedChild != null && linearLayoutManager$AnchorInfo.assignFromViewIfValid(focusedChild, recyclerView$State)) {
                return true;
            }
            if (this.mLastStackFromEnd == this.mStackFromEnd) {
                View view;
                if (linearLayoutManager$AnchorInfo.mLayoutFromEnd) {
                    view = this.findReferenceChildClosestToEnd(recyclerView$State);
                }
                else {
                    view = this.findReferenceChildClosestToStart(recyclerView$State);
                }
                if (view != null) {
                    linearLayoutManager$AnchorInfo.assignFromView(view);
                    if (!recyclerView$State.isPreLayout() && this.supportsPredictiveItemAnimations()) {
                        if (this.mOrientationHelper.getDecoratedStart(view) >= this.mOrientationHelper.getEndAfterPadding() || this.mOrientationHelper.getDecoratedEnd(view) < this.mOrientationHelper.getStartAfterPadding()) {
                            b = true;
                        }
                        if (b) {
                            int mCoordinate;
                            if (linearLayoutManager$AnchorInfo.mLayoutFromEnd) {
                                mCoordinate = this.mOrientationHelper.getEndAfterPadding();
                            }
                            else {
                                mCoordinate = this.mOrientationHelper.getStartAfterPadding();
                            }
                            linearLayoutManager$AnchorInfo.mCoordinate = mCoordinate;
                        }
                    }
                    return true;
                }
            }
        }
        return false;
    }
    
    private boolean updateAnchorFromPendingData(final RecyclerView$State recyclerView$State, final LinearLayoutManager$AnchorInfo linearLayoutManager$AnchorInfo) {
        boolean mLayoutFromEnd = false;
        if (recyclerView$State.isPreLayout() || this.mPendingScrollPosition == -1) {
            return false;
        }
        if (this.mPendingScrollPosition < 0 || this.mPendingScrollPosition >= recyclerView$State.getItemCount()) {
            this.mPendingScrollPosition = -1;
            this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
            return false;
        }
        linearLayoutManager$AnchorInfo.mPosition = this.mPendingScrollPosition;
        if (this.mPendingSavedState != null && this.mPendingSavedState.hasValidAnchor()) {
            linearLayoutManager$AnchorInfo.mLayoutFromEnd = this.mPendingSavedState.mAnchorLayoutFromEnd;
            if (linearLayoutManager$AnchorInfo.mLayoutFromEnd) {
                linearLayoutManager$AnchorInfo.mCoordinate = this.mOrientationHelper.getEndAfterPadding() - this.mPendingSavedState.mAnchorOffset;
                return true;
            }
            linearLayoutManager$AnchorInfo.mCoordinate = this.mOrientationHelper.getStartAfterPadding() + this.mPendingSavedState.mAnchorOffset;
            return true;
        }
        else if (this.mPendingScrollPositionOffset == Integer.MIN_VALUE) {
            final View viewByPosition = this.findViewByPosition(this.mPendingScrollPosition);
            if (viewByPosition == null) {
                if (this.getChildCount() > 0) {
                    if (this.mPendingScrollPosition < this.getPosition(this.getChildAt(0)) == this.mShouldReverseLayout) {
                        mLayoutFromEnd = true;
                    }
                    linearLayoutManager$AnchorInfo.mLayoutFromEnd = mLayoutFromEnd;
                }
                linearLayoutManager$AnchorInfo.assignCoordinateFromPadding();
                return true;
            }
            if (this.mOrientationHelper.getDecoratedMeasurement(viewByPosition) > this.mOrientationHelper.getTotalSpace()) {
                linearLayoutManager$AnchorInfo.assignCoordinateFromPadding();
                return true;
            }
            if (this.mOrientationHelper.getDecoratedStart(viewByPosition) - this.mOrientationHelper.getStartAfterPadding() < 0) {
                linearLayoutManager$AnchorInfo.mCoordinate = this.mOrientationHelper.getStartAfterPadding();
                linearLayoutManager$AnchorInfo.mLayoutFromEnd = false;
                return true;
            }
            if (this.mOrientationHelper.getEndAfterPadding() - this.mOrientationHelper.getDecoratedEnd(viewByPosition) < 0) {
                linearLayoutManager$AnchorInfo.mCoordinate = this.mOrientationHelper.getEndAfterPadding();
                return linearLayoutManager$AnchorInfo.mLayoutFromEnd = true;
            }
            int decoratedStart;
            if (linearLayoutManager$AnchorInfo.mLayoutFromEnd) {
                decoratedStart = this.mOrientationHelper.getDecoratedEnd(viewByPosition) + this.mOrientationHelper.getTotalSpaceChange();
            }
            else {
                decoratedStart = this.mOrientationHelper.getDecoratedStart(viewByPosition);
            }
            linearLayoutManager$AnchorInfo.mCoordinate = decoratedStart;
            return true;
        }
        else {
            linearLayoutManager$AnchorInfo.mLayoutFromEnd = this.mShouldReverseLayout;
            if (this.mShouldReverseLayout) {
                linearLayoutManager$AnchorInfo.mCoordinate = this.mOrientationHelper.getEndAfterPadding() - this.mPendingScrollPositionOffset;
                return true;
            }
            linearLayoutManager$AnchorInfo.mCoordinate = this.mOrientationHelper.getStartAfterPadding() + this.mPendingScrollPositionOffset;
            return true;
        }
    }
    
    private void updateAnchorInfoForLayout(final RecyclerView$State recyclerView$State, final LinearLayoutManager$AnchorInfo linearLayoutManager$AnchorInfo) {
        if (!this.updateAnchorFromPendingData(recyclerView$State, linearLayoutManager$AnchorInfo) && !this.updateAnchorFromChildren(recyclerView$State, linearLayoutManager$AnchorInfo)) {
            linearLayoutManager$AnchorInfo.assignCoordinateFromPadding();
            int mPosition;
            if (this.mStackFromEnd) {
                mPosition = recyclerView$State.getItemCount() - 1;
            }
            else {
                mPosition = 0;
            }
            linearLayoutManager$AnchorInfo.mPosition = mPosition;
        }
    }
    
    private void updateLayoutState(int n, final int mAvailable, final boolean b, final RecyclerView$State recyclerView$State) {
        final int n2 = -1;
        final int n3 = 1;
        this.mLayoutState.mExtra = this.getExtraLayoutSpace(recyclerView$State);
        this.mLayoutState.mLayoutDirection = n;
        if (n == 1) {
            final LinearLayoutManager$LayoutState mLayoutState = this.mLayoutState;
            mLayoutState.mExtra += this.mOrientationHelper.getEndPadding();
            final View childClosestToEnd = this.getChildClosestToEnd();
            final LinearLayoutManager$LayoutState mLayoutState2 = this.mLayoutState;
            if (this.mShouldReverseLayout) {
                n = n2;
            }
            else {
                n = 1;
            }
            mLayoutState2.mItemDirection = n;
            this.mLayoutState.mCurrentPosition = this.getPosition(childClosestToEnd) + this.mLayoutState.mItemDirection;
            this.mLayoutState.mOffset = this.mOrientationHelper.getDecoratedEnd(childClosestToEnd);
            n = this.mOrientationHelper.getDecoratedEnd(childClosestToEnd) - this.mOrientationHelper.getEndAfterPadding();
        }
        else {
            final View childClosestToStart = this.getChildClosestToStart();
            final LinearLayoutManager$LayoutState mLayoutState3 = this.mLayoutState;
            mLayoutState3.mExtra += this.mOrientationHelper.getStartAfterPadding();
            final LinearLayoutManager$LayoutState mLayoutState4 = this.mLayoutState;
            if (this.mShouldReverseLayout) {
                n = n3;
            }
            else {
                n = -1;
            }
            mLayoutState4.mItemDirection = n;
            this.mLayoutState.mCurrentPosition = this.getPosition(childClosestToStart) + this.mLayoutState.mItemDirection;
            this.mLayoutState.mOffset = this.mOrientationHelper.getDecoratedStart(childClosestToStart);
            n = -this.mOrientationHelper.getDecoratedStart(childClosestToStart) + this.mOrientationHelper.getStartAfterPadding();
        }
        this.mLayoutState.mAvailable = mAvailable;
        if (b) {
            final LinearLayoutManager$LayoutState mLayoutState5 = this.mLayoutState;
            mLayoutState5.mAvailable -= n;
        }
        this.mLayoutState.mScrollingOffset = n;
    }
    
    private void updateLayoutStateToFillEnd(final int mCurrentPosition, final int mOffset) {
        this.mLayoutState.mAvailable = this.mOrientationHelper.getEndAfterPadding() - mOffset;
        final LinearLayoutManager$LayoutState mLayoutState = this.mLayoutState;
        int mItemDirection;
        if (this.mShouldReverseLayout) {
            mItemDirection = -1;
        }
        else {
            mItemDirection = 1;
        }
        mLayoutState.mItemDirection = mItemDirection;
        this.mLayoutState.mCurrentPosition = mCurrentPosition;
        this.mLayoutState.mLayoutDirection = 1;
        this.mLayoutState.mOffset = mOffset;
        this.mLayoutState.mScrollingOffset = Integer.MIN_VALUE;
    }
    
    private void updateLayoutStateToFillEnd(final LinearLayoutManager$AnchorInfo linearLayoutManager$AnchorInfo) {
        this.updateLayoutStateToFillEnd(linearLayoutManager$AnchorInfo.mPosition, linearLayoutManager$AnchorInfo.mCoordinate);
    }
    
    private void updateLayoutStateToFillStart(int n, final int mOffset) {
        this.mLayoutState.mAvailable = mOffset - this.mOrientationHelper.getStartAfterPadding();
        this.mLayoutState.mCurrentPosition = n;
        final LinearLayoutManager$LayoutState mLayoutState = this.mLayoutState;
        if (this.mShouldReverseLayout) {
            n = 1;
        }
        else {
            n = -1;
        }
        mLayoutState.mItemDirection = n;
        this.mLayoutState.mLayoutDirection = -1;
        this.mLayoutState.mOffset = mOffset;
        this.mLayoutState.mScrollingOffset = Integer.MIN_VALUE;
    }
    
    private void updateLayoutStateToFillStart(final LinearLayoutManager$AnchorInfo linearLayoutManager$AnchorInfo) {
        this.updateLayoutStateToFillStart(linearLayoutManager$AnchorInfo.mPosition, linearLayoutManager$AnchorInfo.mCoordinate);
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
    
    public PointF computeScrollVectorForPosition(int n) {
        final int n2 = 1;
        boolean b = false;
        if (this.getChildCount() == 0) {
            return null;
        }
        if (n < this.getPosition(this.getChildAt(0))) {
            b = true;
        }
        n = n2;
        if (b != this.mShouldReverseLayout) {
            n = -1;
        }
        if (this.mOrientation == 0) {
            return new PointF((float)n, 0.0f);
        }
        return new PointF(0.0f, (float)n);
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
    
    void ensureLayoutState() {
        if (this.mLayoutState == null) {
            this.mLayoutState = new LinearLayoutManager$LayoutState();
        }
        if (this.mOrientationHelper == null) {
            this.mOrientationHelper = OrientationHelper.createOrientationHelper(this, this.mOrientation);
        }
    }
    
    int fill(final RecyclerView$Recycler recyclerView$Recycler, final LinearLayoutManager$LayoutState linearLayoutManager$LayoutState, final RecyclerView$State recyclerView$State, final boolean b) {
        final int mAvailable = linearLayoutManager$LayoutState.mAvailable;
        if (linearLayoutManager$LayoutState.mScrollingOffset != Integer.MIN_VALUE) {
            if (linearLayoutManager$LayoutState.mAvailable < 0) {
                linearLayoutManager$LayoutState.mScrollingOffset += linearLayoutManager$LayoutState.mAvailable;
            }
            this.recycleByLayoutState(recyclerView$Recycler, linearLayoutManager$LayoutState);
        }
        int n = linearLayoutManager$LayoutState.mAvailable + linearLayoutManager$LayoutState.mExtra;
        final LinearLayoutManager$LayoutChunkResult linearLayoutManager$LayoutChunkResult = new LinearLayoutManager$LayoutChunkResult();
        while (n > 0 && linearLayoutManager$LayoutState.hasMore(recyclerView$State)) {
            linearLayoutManager$LayoutChunkResult.resetInternal();
            this.layoutChunk(recyclerView$Recycler, recyclerView$State, linearLayoutManager$LayoutState, linearLayoutManager$LayoutChunkResult);
            if (linearLayoutManager$LayoutChunkResult.mFinished) {
                break;
            }
            linearLayoutManager$LayoutState.mOffset += linearLayoutManager$LayoutChunkResult.mConsumed * linearLayoutManager$LayoutState.mLayoutDirection;
            int n2 = 0;
            Label_0176: {
                if (linearLayoutManager$LayoutChunkResult.mIgnoreConsumed && this.mLayoutState.mScrapList == null) {
                    n2 = n;
                    if (recyclerView$State.isPreLayout()) {
                        break Label_0176;
                    }
                }
                linearLayoutManager$LayoutState.mAvailable -= linearLayoutManager$LayoutChunkResult.mConsumed;
                n2 = n - linearLayoutManager$LayoutChunkResult.mConsumed;
            }
            if (linearLayoutManager$LayoutState.mScrollingOffset != Integer.MIN_VALUE) {
                linearLayoutManager$LayoutState.mScrollingOffset += linearLayoutManager$LayoutChunkResult.mConsumed;
                if (linearLayoutManager$LayoutState.mAvailable < 0) {
                    linearLayoutManager$LayoutState.mScrollingOffset += linearLayoutManager$LayoutState.mAvailable;
                }
                this.recycleByLayoutState(recyclerView$Recycler, linearLayoutManager$LayoutState);
            }
            n = n2;
            if (!b) {
                continue;
            }
            n = n2;
            if (linearLayoutManager$LayoutChunkResult.mFocusable) {
                break;
            }
        }
        return mAvailable - linearLayoutManager$LayoutState.mAvailable;
    }
    
    public int findFirstCompletelyVisibleItemPosition() {
        final View oneVisibleChild = this.findOneVisibleChild(0, this.getChildCount(), true);
        if (oneVisibleChild == null) {
            return -1;
        }
        return this.getPosition(oneVisibleChild);
    }
    
    public int findFirstVisibleItemPosition() {
        final View oneVisibleChild = this.findOneVisibleChild(0, this.getChildCount(), false);
        if (oneVisibleChild == null) {
            return -1;
        }
        return this.getPosition(oneVisibleChild);
    }
    
    public int findLastCompletelyVisibleItemPosition() {
        final View oneVisibleChild = this.findOneVisibleChild(this.getChildCount() - 1, -1, true);
        if (oneVisibleChild == null) {
            return -1;
        }
        return this.getPosition(oneVisibleChild);
    }
    
    public int findLastVisibleItemPosition() {
        final View oneVisibleChild = this.findOneVisibleChild(this.getChildCount() - 1, -1, false);
        if (oneVisibleChild == null) {
            return -1;
        }
        return this.getPosition(oneVisibleChild);
    }
    
    View findOneVisibleChild(int i, final int n, final boolean b) {
        this.ensureLayoutState();
        final int startAfterPadding = this.mOrientationHelper.getStartAfterPadding();
        final int endAfterPadding = this.mOrientationHelper.getEndAfterPadding();
        int n2;
        if (n > i) {
            n2 = 1;
        }
        else {
            n2 = -1;
        }
        while (i != n) {
            final View child = this.getChildAt(i);
            final int decoratedStart = this.mOrientationHelper.getDecoratedStart(child);
            final int decoratedEnd = this.mOrientationHelper.getDecoratedEnd(child);
            if (decoratedStart < endAfterPadding && decoratedEnd > startAfterPadding) {
                if (!b) {
                    return child;
                }
                if (decoratedStart >= startAfterPadding && decoratedEnd <= endAfterPadding) {
                    return child;
                }
            }
            i += n2;
        }
        return null;
    }
    
    @Override
    public View findViewByPosition(int n) {
        final int childCount = this.getChildCount();
        if (childCount != 0) {
            n -= this.getPosition(this.getChildAt(0));
            if (n >= 0 && n < childCount) {
                return this.getChildAt(n);
            }
        }
        return null;
    }
    
    @Override
    public RecyclerView$LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView$LayoutParams(-2, -2);
    }
    
    protected int getExtraLayoutSpace(final RecyclerView$State recyclerView$State) {
        if (recyclerView$State.hasTargetScrollPosition()) {
            return this.mOrientationHelper.getTotalSpace();
        }
        return 0;
    }
    
    public int getOrientation() {
        return this.mOrientation;
    }
    
    public boolean getRecycleChildrenOnDetach() {
        return this.mRecycleChildrenOnDetach;
    }
    
    public boolean getReverseLayout() {
        return this.mReverseLayout;
    }
    
    public boolean getStackFromEnd() {
        return this.mStackFromEnd;
    }
    
    protected boolean isLayoutRTL() {
        return this.getLayoutDirection() == 1;
    }
    
    public boolean isSmoothScrollbarEnabled() {
        return this.mSmoothScrollbarEnabled;
    }
    
    void layoutChunk(final RecyclerView$Recycler recyclerView$Recycler, final RecyclerView$State recyclerView$State, final LinearLayoutManager$LayoutState linearLayoutManager$LayoutState, final LinearLayoutManager$LayoutChunkResult linearLayoutManager$LayoutChunkResult) {
        final View next = linearLayoutManager$LayoutState.next(recyclerView$Recycler);
        if (next == null) {
            linearLayoutManager$LayoutChunkResult.mFinished = true;
            return;
        }
        final RecyclerView$LayoutParams recyclerView$LayoutParams = (RecyclerView$LayoutParams)next.getLayoutParams();
        if (linearLayoutManager$LayoutState.mScrapList == null) {
            if (this.mShouldReverseLayout == (linearLayoutManager$LayoutState.mLayoutDirection == -1)) {
                this.addView(next);
            }
            else {
                this.addView(next, 0);
            }
        }
        else if (this.mShouldReverseLayout == (linearLayoutManager$LayoutState.mLayoutDirection == -1)) {
            this.addDisappearingView(next);
        }
        else {
            this.addDisappearingView(next, 0);
        }
        this.measureChildWithMargins(next, 0, 0);
        linearLayoutManager$LayoutChunkResult.mConsumed = this.mOrientationHelper.getDecoratedMeasurement(next);
        int mOffset;
        int n;
        int mOffset2;
        int n2;
        if (this.mOrientation == 1) {
            if (this.isLayoutRTL()) {
                mOffset = this.getWidth() - this.getPaddingRight();
                n = mOffset - this.mOrientationHelper.getDecoratedMeasurementInOther(next);
            }
            else {
                n = this.getPaddingLeft();
                mOffset = this.mOrientationHelper.getDecoratedMeasurementInOther(next) + n;
            }
            if (linearLayoutManager$LayoutState.mLayoutDirection == -1) {
                mOffset2 = linearLayoutManager$LayoutState.mOffset;
                n2 = linearLayoutManager$LayoutState.mOffset - linearLayoutManager$LayoutChunkResult.mConsumed;
            }
            else {
                n2 = linearLayoutManager$LayoutState.mOffset;
                mOffset2 = linearLayoutManager$LayoutState.mOffset + linearLayoutManager$LayoutChunkResult.mConsumed;
            }
        }
        else {
            n2 = this.getPaddingTop();
            mOffset2 = this.mOrientationHelper.getDecoratedMeasurementInOther(next) + n2;
            if (linearLayoutManager$LayoutState.mLayoutDirection == -1) {
                mOffset = linearLayoutManager$LayoutState.mOffset;
                n = linearLayoutManager$LayoutState.mOffset - linearLayoutManager$LayoutChunkResult.mConsumed;
            }
            else {
                n = linearLayoutManager$LayoutState.mOffset;
                mOffset = linearLayoutManager$LayoutState.mOffset + linearLayoutManager$LayoutChunkResult.mConsumed;
            }
        }
        this.layoutDecorated(next, n + recyclerView$LayoutParams.leftMargin, n2 + recyclerView$LayoutParams.topMargin, mOffset - recyclerView$LayoutParams.rightMargin, mOffset2 - recyclerView$LayoutParams.bottomMargin);
        if (recyclerView$LayoutParams.isItemRemoved() || recyclerView$LayoutParams.isItemChanged()) {
            linearLayoutManager$LayoutChunkResult.mIgnoreConsumed = true;
        }
        linearLayoutManager$LayoutChunkResult.mFocusable = next.isFocusable();
    }
    
    void onAnchorReady(final RecyclerView$State recyclerView$State, final LinearLayoutManager$AnchorInfo linearLayoutManager$AnchorInfo) {
    }
    
    @Override
    public void onDetachedFromWindow(final RecyclerView recyclerView, final RecyclerView$Recycler recyclerView$Recycler) {
        super.onDetachedFromWindow(recyclerView, recyclerView$Recycler);
        if (this.mRecycleChildrenOnDetach) {
            this.removeAndRecycleAllViews(recyclerView$Recycler);
            recyclerView$Recycler.clear();
        }
    }
    
    @Override
    public View onFocusSearchFailed(View view, int convertFocusDirectionToLayoutDirection, final RecyclerView$Recycler recyclerView$Recycler, final RecyclerView$State recyclerView$State) {
        this.resolveShouldLayoutReverse();
        if (this.getChildCount() != 0) {
            convertFocusDirectionToLayoutDirection = this.convertFocusDirectionToLayoutDirection(convertFocusDirectionToLayoutDirection);
            if (convertFocusDirectionToLayoutDirection != Integer.MIN_VALUE) {
                this.ensureLayoutState();
                if (convertFocusDirectionToLayoutDirection == -1) {
                    view = this.findReferenceChildClosestToStart(recyclerView$State);
                }
                else {
                    view = this.findReferenceChildClosestToEnd(recyclerView$State);
                }
                if (view != null) {
                    this.ensureLayoutState();
                    this.updateLayoutState(convertFocusDirectionToLayoutDirection, (int)(0.33f * this.mOrientationHelper.getTotalSpace()), false, recyclerView$State);
                    this.mLayoutState.mScrollingOffset = Integer.MIN_VALUE;
                    this.mLayoutState.mRecycle = false;
                    this.fill(recyclerView$Recycler, this.mLayoutState, recyclerView$State, true);
                    View view2;
                    if (convertFocusDirectionToLayoutDirection == -1) {
                        view2 = this.getChildClosestToStart();
                    }
                    else {
                        view2 = this.getChildClosestToEnd();
                    }
                    if (view2 != view && view2.isFocusable()) {
                        return view2;
                    }
                }
            }
        }
        return null;
    }
    
    @Override
    public void onInitializeAccessibilityEvent(final AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        if (this.getChildCount() > 0) {
            final AccessibilityRecordCompat record = AccessibilityEventCompat.asRecord(accessibilityEvent);
            record.setFromIndex(this.findFirstVisibleItemPosition());
            record.setToIndex(this.findLastVisibleItemPosition());
        }
    }
    
    @Override
    public void onLayoutChildren(final RecyclerView$Recycler recyclerView$Recycler, final RecyclerView$State recyclerView$State) {
        if (this.mPendingSavedState != null && this.mPendingSavedState.hasValidAnchor()) {
            this.mPendingScrollPosition = this.mPendingSavedState.mAnchorPosition;
        }
        this.ensureLayoutState();
        this.mLayoutState.mRecycle = false;
        this.resolveShouldLayoutReverse();
        this.mAnchorInfo.reset();
        this.mAnchorInfo.mLayoutFromEnd = (this.mShouldReverseLayout ^ this.mStackFromEnd);
        this.updateAnchorInfoForLayout(recyclerView$State, this.mAnchorInfo);
        int extraLayoutSpace = this.getExtraLayoutSpace(recyclerView$State);
        int n;
        if (recyclerView$State.getTargetScrollPosition() < this.mAnchorInfo.mPosition == this.mShouldReverseLayout) {
            n = extraLayoutSpace;
            extraLayoutSpace = 0;
        }
        else {
            n = 0;
        }
        final int n2 = extraLayoutSpace + this.mOrientationHelper.getStartAfterPadding();
        int mExtra;
        final int n3 = mExtra = n + this.mOrientationHelper.getEndPadding();
        int mExtra2 = n2;
        if (recyclerView$State.isPreLayout()) {
            mExtra = n3;
            mExtra2 = n2;
            if (this.mPendingScrollPosition != -1) {
                mExtra = n3;
                mExtra2 = n2;
                if (this.mPendingScrollPositionOffset != Integer.MIN_VALUE) {
                    final View viewByPosition = this.findViewByPosition(this.mPendingScrollPosition);
                    mExtra = n3;
                    mExtra2 = n2;
                    if (viewByPosition != null) {
                        int n4;
                        if (this.mShouldReverseLayout) {
                            n4 = this.mOrientationHelper.getEndAfterPadding() - this.mOrientationHelper.getDecoratedEnd(viewByPosition) - this.mPendingScrollPositionOffset;
                        }
                        else {
                            n4 = this.mPendingScrollPositionOffset - (this.mOrientationHelper.getDecoratedStart(viewByPosition) - this.mOrientationHelper.getStartAfterPadding());
                        }
                        if (n4 > 0) {
                            mExtra2 = n2 + n4;
                            mExtra = n3;
                        }
                        else {
                            mExtra = n3 - n4;
                            mExtra2 = n2;
                        }
                    }
                }
            }
        }
        this.onAnchorReady(recyclerView$State, this.mAnchorInfo);
        this.detachAndScrapAttachedViews(recyclerView$Recycler);
        this.mLayoutState.mIsPreLayout = recyclerView$State.isPreLayout();
        int mOffset2;
        int mOffset3;
        if (this.mAnchorInfo.mLayoutFromEnd) {
            this.updateLayoutStateToFillStart(this.mAnchorInfo);
            this.mLayoutState.mExtra = mExtra2;
            this.fill(recyclerView$Recycler, this.mLayoutState, recyclerView$State, false);
            final int mOffset = this.mLayoutState.mOffset;
            int mExtra3 = mExtra;
            if (this.mLayoutState.mAvailable > 0) {
                mExtra3 = mExtra + this.mLayoutState.mAvailable;
            }
            this.updateLayoutStateToFillEnd(this.mAnchorInfo);
            this.mLayoutState.mExtra = mExtra3;
            final LinearLayoutManager$LayoutState mLayoutState = this.mLayoutState;
            mLayoutState.mCurrentPosition += this.mLayoutState.mItemDirection;
            this.fill(recyclerView$Recycler, this.mLayoutState, recyclerView$State, false);
            mOffset2 = this.mLayoutState.mOffset;
            mOffset3 = mOffset;
        }
        else {
            this.updateLayoutStateToFillEnd(this.mAnchorInfo);
            this.mLayoutState.mExtra = mExtra;
            this.fill(recyclerView$Recycler, this.mLayoutState, recyclerView$State, false);
            final int mOffset4 = this.mLayoutState.mOffset;
            int mExtra4 = mExtra2;
            if (this.mLayoutState.mAvailable > 0) {
                mExtra4 = mExtra2 + this.mLayoutState.mAvailable;
            }
            this.updateLayoutStateToFillStart(this.mAnchorInfo);
            this.mLayoutState.mExtra = mExtra4;
            final LinearLayoutManager$LayoutState mLayoutState2 = this.mLayoutState;
            mLayoutState2.mCurrentPosition += this.mLayoutState.mItemDirection;
            this.fill(recyclerView$Recycler, this.mLayoutState, recyclerView$State, false);
            mOffset3 = this.mLayoutState.mOffset;
            mOffset2 = mOffset4;
        }
        int n5 = mOffset2;
        int n6 = mOffset3;
        if (this.getChildCount() > 0) {
            if (this.mShouldReverseLayout ^ this.mStackFromEnd) {
                final int fixLayoutEndGap = this.fixLayoutEndGap(mOffset2, recyclerView$Recycler, recyclerView$State, true);
                final int n7 = mOffset3 + fixLayoutEndGap;
                final int fixLayoutStartGap = this.fixLayoutStartGap(n7, recyclerView$Recycler, recyclerView$State, false);
                n6 = n7 + fixLayoutStartGap;
                n5 = mOffset2 + fixLayoutEndGap + fixLayoutStartGap;
            }
            else {
                final int fixLayoutStartGap2 = this.fixLayoutStartGap(mOffset3, recyclerView$Recycler, recyclerView$State, true);
                final int n8 = mOffset2 + fixLayoutStartGap2;
                final int fixLayoutEndGap2 = this.fixLayoutEndGap(n8, recyclerView$Recycler, recyclerView$State, false);
                n6 = mOffset3 + fixLayoutStartGap2 + fixLayoutEndGap2;
                n5 = n8 + fixLayoutEndGap2;
            }
        }
        this.layoutForPredictiveAnimations(recyclerView$Recycler, recyclerView$State, n6, n5);
        if (!recyclerView$State.isPreLayout()) {
            this.mPendingScrollPosition = -1;
            this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
            this.mOrientationHelper.onLayoutComplete();
        }
        this.mLastStackFromEnd = this.mStackFromEnd;
        this.mPendingSavedState = null;
    }
    
    @Override
    public void onRestoreInstanceState(final Parcelable parcelable) {
        if (parcelable instanceof LinearLayoutManager$SavedState) {
            this.mPendingSavedState = (LinearLayoutManager$SavedState)parcelable;
            this.requestLayout();
        }
    }
    
    @Override
    public Parcelable onSaveInstanceState() {
        if (this.mPendingSavedState != null) {
            return (Parcelable)new LinearLayoutManager$SavedState(this.mPendingSavedState);
        }
        final LinearLayoutManager$SavedState linearLayoutManager$SavedState = new LinearLayoutManager$SavedState();
        if (this.getChildCount() <= 0) {
            linearLayoutManager$SavedState.invalidateAnchor();
            return (Parcelable)linearLayoutManager$SavedState;
        }
        this.ensureLayoutState();
        final boolean mAnchorLayoutFromEnd = this.mLastStackFromEnd ^ this.mShouldReverseLayout;
        linearLayoutManager$SavedState.mAnchorLayoutFromEnd = mAnchorLayoutFromEnd;
        if (mAnchorLayoutFromEnd) {
            final View childClosestToEnd = this.getChildClosestToEnd();
            linearLayoutManager$SavedState.mAnchorOffset = this.mOrientationHelper.getEndAfterPadding() - this.mOrientationHelper.getDecoratedEnd(childClosestToEnd);
            linearLayoutManager$SavedState.mAnchorPosition = this.getPosition(childClosestToEnd);
            return (Parcelable)linearLayoutManager$SavedState;
        }
        final View childClosestToStart = this.getChildClosestToStart();
        linearLayoutManager$SavedState.mAnchorPosition = this.getPosition(childClosestToStart);
        linearLayoutManager$SavedState.mAnchorOffset = this.mOrientationHelper.getDecoratedStart(childClosestToStart) - this.mOrientationHelper.getStartAfterPadding();
        return (Parcelable)linearLayoutManager$SavedState;
    }
    
    int scrollBy(int n, final RecyclerView$Recycler recyclerView$Recycler, final RecyclerView$State recyclerView$State) {
        if (this.getChildCount() == 0 || n == 0) {
            return 0;
        }
        this.mLayoutState.mRecycle = true;
        this.ensureLayoutState();
        int n2;
        if (n > 0) {
            n2 = 1;
        }
        else {
            n2 = -1;
        }
        final int abs = Math.abs(n);
        this.updateLayoutState(n2, abs, true, recyclerView$State);
        final int n3 = this.mLayoutState.mScrollingOffset + this.fill(recyclerView$Recycler, this.mLayoutState, recyclerView$State, false);
        if (n3 < 0) {
            return 0;
        }
        if (abs > n3) {
            n = n2 * n3;
        }
        this.mOrientationHelper.offsetChildren(-n);
        return n;
    }
    
    @Override
    public int scrollHorizontallyBy(final int n, final RecyclerView$Recycler recyclerView$Recycler, final RecyclerView$State recyclerView$State) {
        if (this.mOrientation == 1) {
            return 0;
        }
        return this.scrollBy(n, recyclerView$Recycler, recyclerView$State);
    }
    
    @Override
    public void scrollToPosition(final int mPendingScrollPosition) {
        this.mPendingScrollPosition = mPendingScrollPosition;
        this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
        if (this.mPendingSavedState != null) {
            this.mPendingSavedState.invalidateAnchor();
        }
        this.requestLayout();
    }
    
    public void scrollToPositionWithOffset(final int mPendingScrollPosition, final int mPendingScrollPositionOffset) {
        this.mPendingScrollPosition = mPendingScrollPosition;
        this.mPendingScrollPositionOffset = mPendingScrollPositionOffset;
        if (this.mPendingSavedState != null) {
            this.mPendingSavedState.invalidateAnchor();
        }
        this.requestLayout();
    }
    
    @Override
    public int scrollVerticallyBy(final int n, final RecyclerView$Recycler recyclerView$Recycler, final RecyclerView$State recyclerView$State) {
        if (this.mOrientation == 0) {
            return 0;
        }
        return this.scrollBy(n, recyclerView$Recycler, recyclerView$State);
    }
    
    public void setOrientation(final int mOrientation) {
        if (mOrientation != 0 && mOrientation != 1) {
            throw new IllegalArgumentException("invalid orientation:" + mOrientation);
        }
        this.assertNotInLayoutOrScroll(null);
        if (mOrientation == this.mOrientation) {
            return;
        }
        this.mOrientation = mOrientation;
        this.mOrientationHelper = null;
        this.requestLayout();
    }
    
    public void setRecycleChildrenOnDetach(final boolean mRecycleChildrenOnDetach) {
        this.mRecycleChildrenOnDetach = mRecycleChildrenOnDetach;
    }
    
    public void setReverseLayout(final boolean mReverseLayout) {
        this.assertNotInLayoutOrScroll(null);
        if (mReverseLayout == this.mReverseLayout) {
            return;
        }
        this.mReverseLayout = mReverseLayout;
        this.requestLayout();
    }
    
    public void setSmoothScrollbarEnabled(final boolean mSmoothScrollbarEnabled) {
        this.mSmoothScrollbarEnabled = mSmoothScrollbarEnabled;
    }
    
    public void setStackFromEnd(final boolean mStackFromEnd) {
        this.assertNotInLayoutOrScroll(null);
        if (this.mStackFromEnd == mStackFromEnd) {
            return;
        }
        this.mStackFromEnd = mStackFromEnd;
        this.requestLayout();
    }
    
    @Override
    public void smoothScrollToPosition(final RecyclerView recyclerView, final RecyclerView$State recyclerView$State, final int targetPosition) {
        final LinearLayoutManager$1 linearLayoutManager$1 = new LinearLayoutManager$1(this, recyclerView.getContext());
        linearLayoutManager$1.setTargetPosition(targetPosition);
        this.startSmoothScroll(linearLayoutManager$1);
    }
    
    @Override
    public boolean supportsPredictiveItemAnimations() {
        return this.mPendingSavedState == null && this.mLastStackFromEnd == this.mStackFromEnd;
    }
    
    void validateChildOrder() {
        final boolean b = true;
        boolean b2 = true;
        Log.d("LinearLayoutManager", "validating child count " + this.getChildCount());
        if (this.getChildCount() >= 1) {
            final int position = this.getPosition(this.getChildAt(0));
            final int decoratedStart = this.mOrientationHelper.getDecoratedStart(this.getChildAt(0));
            if (this.mShouldReverseLayout) {
                for (int i = 1; i < this.getChildCount(); ++i) {
                    final View child = this.getChildAt(i);
                    final int position2 = this.getPosition(child);
                    final int decoratedStart2 = this.mOrientationHelper.getDecoratedStart(child);
                    if (position2 < position) {
                        this.logChildren();
                        final StringBuilder append = new StringBuilder().append("detected invalid position. loc invalid? ");
                        if (decoratedStart2 >= decoratedStart) {
                            b2 = false;
                        }
                        throw new RuntimeException(append.append(b2).toString());
                    }
                    if (decoratedStart2 > decoratedStart) {
                        this.logChildren();
                        throw new RuntimeException("detected invalid location");
                    }
                }
            }
            else {
                for (int j = 1; j < this.getChildCount(); ++j) {
                    final View child2 = this.getChildAt(j);
                    final int position3 = this.getPosition(child2);
                    final int decoratedStart3 = this.mOrientationHelper.getDecoratedStart(child2);
                    if (position3 < position) {
                        this.logChildren();
                        throw new RuntimeException("detected invalid position. loc invalid? " + (decoratedStart3 < decoratedStart && b));
                    }
                    if (decoratedStart3 < decoratedStart) {
                        this.logChildren();
                        throw new RuntimeException("detected invalid location");
                    }
                }
            }
        }
    }
}
