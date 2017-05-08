// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat$CollectionItemInfoCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import java.util.Arrays;
import android.view.View$MeasureSpec;
import android.view.ViewGroup$MarginLayoutParams;
import android.view.ViewGroup$LayoutParams;
import android.util.Log;
import android.util.AttributeSet;
import android.content.Context;
import android.view.View;
import android.util.SparseIntArray;
import android.graphics.Rect;

public class GridLayoutManager extends LinearLayoutManager
{
    private static final boolean DEBUG = false;
    public static final int DEFAULT_SPAN_COUNT = -1;
    private static final String TAG = "GridLayoutManager";
    int[] mCachedBorders;
    final Rect mDecorInsets;
    boolean mPendingSpanCountChange;
    final SparseIntArray mPreLayoutSpanIndexCache;
    final SparseIntArray mPreLayoutSpanSizeCache;
    View[] mSet;
    int mSpanCount;
    GridLayoutManager$SpanSizeLookup mSpanSizeLookup;
    
    public GridLayoutManager(final Context context, final int spanCount) {
        super(context);
        this.mPendingSpanCountChange = false;
        this.mSpanCount = -1;
        this.mPreLayoutSpanSizeCache = new SparseIntArray();
        this.mPreLayoutSpanIndexCache = new SparseIntArray();
        this.mSpanSizeLookup = new GridLayoutManager$DefaultSpanSizeLookup();
        this.mDecorInsets = new Rect();
        this.setSpanCount(spanCount);
    }
    
    public GridLayoutManager(final Context context, final int spanCount, final int n, final boolean b) {
        super(context, n, b);
        this.mPendingSpanCountChange = false;
        this.mSpanCount = -1;
        this.mPreLayoutSpanSizeCache = new SparseIntArray();
        this.mPreLayoutSpanIndexCache = new SparseIntArray();
        this.mSpanSizeLookup = new GridLayoutManager$DefaultSpanSizeLookup();
        this.mDecorInsets = new Rect();
        this.setSpanCount(spanCount);
    }
    
    public GridLayoutManager(final Context context, final AttributeSet set, final int n, final int n2) {
        super(context, set, n, n2);
        this.mPendingSpanCountChange = false;
        this.mSpanCount = -1;
        this.mPreLayoutSpanSizeCache = new SparseIntArray();
        this.mPreLayoutSpanIndexCache = new SparseIntArray();
        this.mSpanSizeLookup = new GridLayoutManager$DefaultSpanSizeLookup();
        this.mDecorInsets = new Rect();
        this.setSpanCount(RecyclerView$LayoutManager.getProperties(context, set, n, n2).spanCount);
    }
    
    private void assignSpans(final RecyclerView$Recycler recyclerView$Recycler, final RecyclerView$State recyclerView$State, int i, int n, final boolean b) {
        int n3;
        if (b) {
            n = 1;
            final int n2 = 0;
            n3 = i;
            i = n2;
        }
        else {
            n3 = -1;
            --i;
            n = -1;
        }
        int mSpanIndex = 0;
        while (i != n3) {
            final View view = this.mSet[i];
            final GridLayoutManager$LayoutParams gridLayoutManager$LayoutParams = (GridLayoutManager$LayoutParams)view.getLayoutParams();
            gridLayoutManager$LayoutParams.mSpanSize = this.getSpanSize(recyclerView$Recycler, recyclerView$State, this.getPosition(view));
            gridLayoutManager$LayoutParams.mSpanIndex = mSpanIndex;
            mSpanIndex += gridLayoutManager$LayoutParams.mSpanSize;
            i += n;
        }
    }
    
    private void cachePreLayoutSpanMapping() {
        for (int childCount = this.getChildCount(), i = 0; i < childCount; ++i) {
            final GridLayoutManager$LayoutParams gridLayoutManager$LayoutParams = (GridLayoutManager$LayoutParams)this.getChildAt(i).getLayoutParams();
            final int viewLayoutPosition = gridLayoutManager$LayoutParams.getViewLayoutPosition();
            this.mPreLayoutSpanSizeCache.put(viewLayoutPosition, gridLayoutManager$LayoutParams.getSpanSize());
            this.mPreLayoutSpanIndexCache.put(viewLayoutPosition, gridLayoutManager$LayoutParams.getSpanIndex());
        }
    }
    
    private void calculateItemBorders(final int n) {
        this.mCachedBorders = calculateItemBorders(this.mCachedBorders, this.mSpanCount, n);
    }
    
    static int[] calculateItemBorders(final int[] array, final int n, int n2) {
        final int n3 = 0;
        int[] array2 = null;
        Label_0035: {
            if (array != null && array.length == n + 1) {
                array2 = array;
                if (array[array.length - 1] == n2) {
                    break Label_0035;
                }
            }
            array2 = new int[n + 1];
        }
        array2[0] = 0;
        final int n4 = n2 / n;
        final int n5 = n2 % n;
        int i = 1;
        int n6 = 0;
        n2 = n3;
        while (i <= n) {
            n2 += n5;
            int n7;
            if (n2 > 0 && n - n2 < n5) {
                n7 = n4 + 1;
                n2 -= n;
            }
            else {
                n7 = n4;
            }
            n6 += n7;
            array2[i] = n6;
            ++i;
        }
        return array2;
    }
    
    private void clearPreLayoutSpanMappingCache() {
        this.mPreLayoutSpanSizeCache.clear();
        this.mPreLayoutSpanIndexCache.clear();
    }
    
    private void ensureAnchorIsInCorrectSpan(final RecyclerView$Recycler recyclerView$Recycler, final RecyclerView$State recyclerView$State, final LinearLayoutManager$AnchorInfo linearLayoutManager$AnchorInfo, int n) {
        int n2 = 1;
        if (n != 1) {
            n2 = 0;
        }
        n = this.getSpanIndex(recyclerView$Recycler, recyclerView$State, linearLayoutManager$AnchorInfo.mPosition);
        if (n2 != 0) {
            while (n > 0 && linearLayoutManager$AnchorInfo.mPosition > 0) {
                --linearLayoutManager$AnchorInfo.mPosition;
                n = this.getSpanIndex(recyclerView$Recycler, recyclerView$State, linearLayoutManager$AnchorInfo.mPosition);
            }
        }
        else {
            int itemCount;
            int i;
            int spanIndex;
            for (itemCount = recyclerView$State.getItemCount(), i = linearLayoutManager$AnchorInfo.mPosition; i < itemCount - 1; ++i, n = spanIndex) {
                spanIndex = this.getSpanIndex(recyclerView$Recycler, recyclerView$State, i + 1);
                if (spanIndex <= n) {
                    break;
                }
            }
            linearLayoutManager$AnchorInfo.mPosition = i;
        }
    }
    
    private void ensureViewSet() {
        if (this.mSet == null || this.mSet.length != this.mSpanCount) {
            this.mSet = new View[this.mSpanCount];
        }
    }
    
    private int getSpanGroupIndex(final RecyclerView$Recycler recyclerView$Recycler, final RecyclerView$State recyclerView$State, final int n) {
        if (!recyclerView$State.isPreLayout()) {
            return this.mSpanSizeLookup.getSpanGroupIndex(n, this.mSpanCount);
        }
        final int convertPreLayoutPositionToPostLayout = recyclerView$Recycler.convertPreLayoutPositionToPostLayout(n);
        if (convertPreLayoutPositionToPostLayout == -1) {
            Log.w("GridLayoutManager", "Cannot find span size for pre layout position. " + n);
            return 0;
        }
        return this.mSpanSizeLookup.getSpanGroupIndex(convertPreLayoutPositionToPostLayout, this.mSpanCount);
    }
    
    private int getSpanIndex(final RecyclerView$Recycler recyclerView$Recycler, final RecyclerView$State recyclerView$State, final int n) {
        int n2;
        if (!recyclerView$State.isPreLayout()) {
            n2 = this.mSpanSizeLookup.getCachedSpanIndex(n, this.mSpanCount);
        }
        else if ((n2 = this.mPreLayoutSpanIndexCache.get(n, -1)) == -1) {
            final int convertPreLayoutPositionToPostLayout = recyclerView$Recycler.convertPreLayoutPositionToPostLayout(n);
            if (convertPreLayoutPositionToPostLayout == -1) {
                Log.w("GridLayoutManager", "Cannot find span size for pre layout position. It is not cached, not in the adapter. Pos:" + n);
                return 0;
            }
            return this.mSpanSizeLookup.getCachedSpanIndex(convertPreLayoutPositionToPostLayout, this.mSpanCount);
        }
        return n2;
    }
    
    private int getSpanSize(final RecyclerView$Recycler recyclerView$Recycler, final RecyclerView$State recyclerView$State, final int n) {
        int n2;
        if (!recyclerView$State.isPreLayout()) {
            n2 = this.mSpanSizeLookup.getSpanSize(n);
        }
        else if ((n2 = this.mPreLayoutSpanSizeCache.get(n, -1)) == -1) {
            final int convertPreLayoutPositionToPostLayout = recyclerView$Recycler.convertPreLayoutPositionToPostLayout(n);
            if (convertPreLayoutPositionToPostLayout == -1) {
                Log.w("GridLayoutManager", "Cannot find span size for pre layout position. It is not cached, not in the adapter. Pos:" + n);
                return 1;
            }
            return this.mSpanSizeLookup.getSpanSize(convertPreLayoutPositionToPostLayout);
        }
        return n2;
    }
    
    private void guessMeasurement(final float n, final int n2) {
        this.calculateItemBorders(Math.max(Math.round(this.mSpanCount * n), n2));
    }
    
    private void measureChild(final View view, int n, final boolean b) {
        final GridLayoutManager$LayoutParams gridLayoutManager$LayoutParams = (GridLayoutManager$LayoutParams)view.getLayoutParams();
        final Rect mDecorInsets = gridLayoutManager$LayoutParams.mDecorInsets;
        final int n2 = mDecorInsets.top + mDecorInsets.bottom + gridLayoutManager$LayoutParams.topMargin + gridLayoutManager$LayoutParams.bottomMargin;
        final int n3 = gridLayoutManager$LayoutParams.rightMargin + (mDecorInsets.right + mDecorInsets.left + gridLayoutManager$LayoutParams.leftMargin);
        final int spaceForSpanRange = this.getSpaceForSpanRange(gridLayoutManager$LayoutParams.mSpanIndex, gridLayoutManager$LayoutParams.mSpanSize);
        int n4;
        if (this.mOrientation == 1) {
            n4 = RecyclerView$LayoutManager.getChildMeasureSpec(spaceForSpanRange, n, n3, gridLayoutManager$LayoutParams.width, false);
            n = RecyclerView$LayoutManager.getChildMeasureSpec(this.mOrientationHelper.getTotalSpace(), this.getHeightMode(), n2, gridLayoutManager$LayoutParams.height, true);
        }
        else {
            n = RecyclerView$LayoutManager.getChildMeasureSpec(spaceForSpanRange, n, n2, gridLayoutManager$LayoutParams.height, false);
            n4 = RecyclerView$LayoutManager.getChildMeasureSpec(this.mOrientationHelper.getTotalSpace(), this.getWidthMode(), n3, gridLayoutManager$LayoutParams.width, true);
        }
        this.measureChildWithDecorationsAndMargin(view, n4, n, b);
    }
    
    private void measureChildWithDecorationsAndMargin(final View view, final int n, final int n2, final boolean b) {
        final RecyclerView$LayoutParams recyclerView$LayoutParams = (RecyclerView$LayoutParams)view.getLayoutParams();
        boolean b2;
        if (b) {
            b2 = this.shouldReMeasureChild(view, n, n2, recyclerView$LayoutParams);
        }
        else {
            b2 = this.shouldMeasureChild(view, n, n2, recyclerView$LayoutParams);
        }
        if (b2) {
            view.measure(n, n2);
        }
    }
    
    private void updateMeasurements() {
        int n;
        if (this.getOrientation() == 1) {
            n = this.getWidth() - this.getPaddingRight() - this.getPaddingLeft();
        }
        else {
            n = this.getHeight() - this.getPaddingBottom() - this.getPaddingTop();
        }
        this.calculateItemBorders(n);
    }
    
    @Override
    public boolean checkLayoutParams(final RecyclerView$LayoutParams recyclerView$LayoutParams) {
        return recyclerView$LayoutParams instanceof GridLayoutManager$LayoutParams;
    }
    
    @Override
    View findReferenceChild(final RecyclerView$Recycler recyclerView$Recycler, final RecyclerView$State recyclerView$State, int i, final int n, final int n2) {
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
    Label_0093_Outer:
        while (i != n) {
            final View child = this.getChildAt(i);
            final int position = this.getPosition(child);
            while (true) {
                Label_0216: {
                    if (position < 0 || position >= n2) {
                        break Label_0216;
                    }
                    View view4;
                    View view5;
                    if (this.getSpanIndex(recyclerView$Recycler, recyclerView$State, position) != 0) {
                        final View view3 = view;
                        view4 = view2;
                        view5 = view3;
                    }
                    else if (((RecyclerView$LayoutParams)child.getLayoutParams()).isItemRemoved()) {
                        if (view2 != null) {
                            break Label_0216;
                        }
                        view5 = view;
                        view4 = child;
                    }
                    else {
                        if (this.mOrientationHelper.getDecoratedStart(child) < endAfterPadding) {
                            final View view6 = child;
                            if (this.mOrientationHelper.getDecoratedEnd(child) >= startAfterPadding) {
                                return view6;
                            }
                        }
                        if (view != null) {
                            break Label_0216;
                        }
                        view4 = view2;
                        view5 = child;
                    }
                    i += n3;
                    final View view7 = view4;
                    view = view5;
                    view2 = view7;
                    continue Label_0093_Outer;
                }
                final View view8 = view2;
                View view5 = view;
                View view4 = view8;
                continue;
            }
        }
        if (view == null) {
            view = view2;
        }
        return view;
    }
    
    @Override
    public RecyclerView$LayoutParams generateDefaultLayoutParams() {
        if (this.mOrientation == 0) {
            return new GridLayoutManager$LayoutParams(-2, -1);
        }
        return new GridLayoutManager$LayoutParams(-1, -2);
    }
    
    @Override
    public RecyclerView$LayoutParams generateLayoutParams(final Context context, final AttributeSet set) {
        return new GridLayoutManager$LayoutParams(context, set);
    }
    
    @Override
    public RecyclerView$LayoutParams generateLayoutParams(final ViewGroup$LayoutParams viewGroup$LayoutParams) {
        if (viewGroup$LayoutParams instanceof ViewGroup$MarginLayoutParams) {
            return new GridLayoutManager$LayoutParams((ViewGroup$MarginLayoutParams)viewGroup$LayoutParams);
        }
        return new GridLayoutManager$LayoutParams(viewGroup$LayoutParams);
    }
    
    @Override
    public int getColumnCountForAccessibility(final RecyclerView$Recycler recyclerView$Recycler, final RecyclerView$State recyclerView$State) {
        if (this.mOrientation == 1) {
            return this.mSpanCount;
        }
        if (recyclerView$State.getItemCount() < 1) {
            return 0;
        }
        return this.getSpanGroupIndex(recyclerView$Recycler, recyclerView$State, recyclerView$State.getItemCount() - 1) + 1;
    }
    
    @Override
    public int getRowCountForAccessibility(final RecyclerView$Recycler recyclerView$Recycler, final RecyclerView$State recyclerView$State) {
        if (this.mOrientation == 0) {
            return this.mSpanCount;
        }
        if (recyclerView$State.getItemCount() < 1) {
            return 0;
        }
        return this.getSpanGroupIndex(recyclerView$Recycler, recyclerView$State, recyclerView$State.getItemCount() - 1) + 1;
    }
    
    int getSpaceForSpanRange(final int n, final int n2) {
        if (this.mOrientation == 1 && this.isLayoutRTL()) {
            return this.mCachedBorders[this.mSpanCount - n] - this.mCachedBorders[this.mSpanCount - n - n2];
        }
        return this.mCachedBorders[n + n2] - this.mCachedBorders[n];
    }
    
    public int getSpanCount() {
        return this.mSpanCount;
    }
    
    public GridLayoutManager$SpanSizeLookup getSpanSizeLookup() {
        return this.mSpanSizeLookup;
    }
    
    @Override
    void layoutChunk(final RecyclerView$Recycler recyclerView$Recycler, final RecyclerView$State recyclerView$State, final LinearLayoutManager$LayoutState linearLayoutManager$LayoutState, final LinearLayoutManager$LayoutChunkResult linearLayoutManager$LayoutChunkResult) {
        final int modeInOther = this.mOrientationHelper.getModeInOther();
        boolean b;
        if (modeInOther != 1073741824) {
            b = true;
        }
        else {
            b = false;
        }
        int n;
        if (this.getChildCount() > 0) {
            n = this.mCachedBorders[this.mSpanCount];
        }
        else {
            n = 0;
        }
        if (b) {
            this.updateMeasurements();
        }
        final boolean b2 = linearLayoutManager$LayoutState.mItemDirection == 1;
        final int n2 = 0;
        final int n3 = 0;
        int mSpanCount = this.mSpanCount;
        int n4 = n2;
        int n5 = n3;
        if (!b2) {
            mSpanCount = this.getSpanIndex(recyclerView$Recycler, recyclerView$State, linearLayoutManager$LayoutState.mCurrentPosition) + this.getSpanSize(recyclerView$Recycler, recyclerView$State, linearLayoutManager$LayoutState.mCurrentPosition);
            n5 = n3;
            n4 = n2;
        }
        while (n4 < this.mSpanCount && linearLayoutManager$LayoutState.hasMore(recyclerView$State) && mSpanCount > 0) {
            final int mCurrentPosition = linearLayoutManager$LayoutState.mCurrentPosition;
            final int spanSize = this.getSpanSize(recyclerView$Recycler, recyclerView$State, mCurrentPosition);
            if (spanSize > this.mSpanCount) {
                throw new IllegalArgumentException("Item at position " + mCurrentPosition + " requires " + spanSize + " spans but GridLayoutManager has only " + this.mSpanCount + " spans.");
            }
            mSpanCount -= spanSize;
            if (mSpanCount < 0) {
                break;
            }
            final View next = linearLayoutManager$LayoutState.next(recyclerView$Recycler);
            if (next == null) {
                break;
            }
            n5 += spanSize;
            this.mSet[n4] = next;
            ++n4;
        }
        if (n4 == 0) {
            linearLayoutManager$LayoutChunkResult.mFinished = true;
            return;
        }
        this.assignSpans(recyclerView$Recycler, recyclerView$State, n4, n5, b2);
        int i = 0;
        float n6 = 0.0f;
        int n7 = 0;
        while (i < n4) {
            final View view = this.mSet[i];
            if (linearLayoutManager$LayoutState.mScrapList == null) {
                if (b2) {
                    this.addView(view);
                }
                else {
                    this.addView(view, 0);
                }
            }
            else if (b2) {
                this.addDisappearingView(view);
            }
            else {
                this.addDisappearingView(view, 0);
            }
            this.calculateItemDecorationsForChild(view, this.mDecorInsets);
            this.measureChild(view, modeInOther, false);
            final int decoratedMeasurement = this.mOrientationHelper.getDecoratedMeasurement(view);
            int n8 = n7;
            if (decoratedMeasurement > n7) {
                n8 = decoratedMeasurement;
            }
            final float n9 = this.mOrientationHelper.getDecoratedMeasurementInOther(view) * 1.0f / ((GridLayoutManager$LayoutParams)view.getLayoutParams()).mSpanSize;
            if (n9 > n6) {
                n6 = n9;
            }
            ++i;
            n7 = n8;
        }
        int mConsumed = n7;
        if (b) {
            this.guessMeasurement(n6, n);
            int n10 = 0;
            int n11 = 0;
            while (true) {
                mConsumed = n10;
                if (n11 >= n4) {
                    break;
                }
                final View view2 = this.mSet[n11];
                this.measureChild(view2, 1073741824, true);
                final int decoratedMeasurement2 = this.mOrientationHelper.getDecoratedMeasurement(view2);
                if (decoratedMeasurement2 > n10) {
                    n10 = decoratedMeasurement2;
                }
                ++n11;
            }
        }
        for (int j = 0; j < n4; ++j) {
            final View view3 = this.mSet[j];
            if (this.mOrientationHelper.getDecoratedMeasurement(view3) != mConsumed) {
                final GridLayoutManager$LayoutParams gridLayoutManager$LayoutParams = (GridLayoutManager$LayoutParams)view3.getLayoutParams();
                final Rect mDecorInsets = gridLayoutManager$LayoutParams.mDecorInsets;
                final int n12 = mDecorInsets.top + mDecorInsets.bottom + gridLayoutManager$LayoutParams.topMargin + gridLayoutManager$LayoutParams.bottomMargin;
                final int n13 = mDecorInsets.right + mDecorInsets.left + gridLayoutManager$LayoutParams.leftMargin + gridLayoutManager$LayoutParams.rightMargin;
                final int spaceForSpanRange = this.getSpaceForSpanRange(gridLayoutManager$LayoutParams.mSpanIndex, gridLayoutManager$LayoutParams.mSpanSize);
                int n14;
                int n15;
                if (this.mOrientation == 1) {
                    n14 = RecyclerView$LayoutManager.getChildMeasureSpec(spaceForSpanRange, 1073741824, n13, gridLayoutManager$LayoutParams.width, false);
                    n15 = View$MeasureSpec.makeMeasureSpec(mConsumed - n12, 1073741824);
                }
                else {
                    n14 = View$MeasureSpec.makeMeasureSpec(mConsumed - n13, 1073741824);
                    n15 = RecyclerView$LayoutManager.getChildMeasureSpec(spaceForSpanRange, 1073741824, n12, gridLayoutManager$LayoutParams.height, false);
                }
                this.measureChildWithDecorationsAndMargin(view3, n14, n15, true);
            }
        }
        linearLayoutManager$LayoutChunkResult.mConsumed = mConsumed;
        int mOffset = 0;
        int mOffset2;
        int mOffset3;
        int n16;
        if (this.mOrientation == 1) {
            if (linearLayoutManager$LayoutState.mLayoutDirection == -1) {
                mOffset = linearLayoutManager$LayoutState.mOffset;
                mOffset2 = mOffset - mConsumed;
                mOffset3 = 0;
                n16 = 0;
            }
            else {
                mOffset2 = linearLayoutManager$LayoutState.mOffset;
                mOffset = mOffset2 + mConsumed;
                mOffset3 = 0;
                n16 = 0;
            }
        }
        else if (linearLayoutManager$LayoutState.mLayoutDirection == -1) {
            n16 = (mOffset3 = linearLayoutManager$LayoutState.mOffset) - mConsumed;
            mOffset2 = 0;
        }
        else {
            final int mOffset4 = linearLayoutManager$LayoutState.mOffset;
            mOffset3 = mConsumed + mOffset4;
            mOffset2 = 0;
            n16 = mOffset4;
        }
        final int n17 = n16;
        final int n18 = 0;
        int n19 = mOffset;
        int n20 = mOffset2;
        int n21 = n17;
        for (int k = n18; k < n4; ++k) {
            final View view4 = this.mSet[k];
            final GridLayoutManager$LayoutParams gridLayoutManager$LayoutParams2 = (GridLayoutManager$LayoutParams)view4.getLayoutParams();
            if (this.mOrientation == 1) {
                if (this.isLayoutRTL()) {
                    mOffset3 = this.getPaddingLeft() + this.mCachedBorders[this.mSpanCount - gridLayoutManager$LayoutParams2.mSpanIndex];
                    n21 = mOffset3 - this.mOrientationHelper.getDecoratedMeasurementInOther(view4);
                }
                else {
                    n21 = this.getPaddingLeft() + this.mCachedBorders[gridLayoutManager$LayoutParams2.mSpanIndex];
                    mOffset3 = n21 + this.mOrientationHelper.getDecoratedMeasurementInOther(view4);
                }
            }
            else {
                n20 = this.getPaddingTop() + this.mCachedBorders[gridLayoutManager$LayoutParams2.mSpanIndex];
                n19 = n20 + this.mOrientationHelper.getDecoratedMeasurementInOther(view4);
            }
            this.layoutDecoratedWithMargins(view4, n21, n20, mOffset3, n19);
            if (gridLayoutManager$LayoutParams2.isItemRemoved() || gridLayoutManager$LayoutParams2.isItemChanged()) {
                linearLayoutManager$LayoutChunkResult.mIgnoreConsumed = true;
            }
            linearLayoutManager$LayoutChunkResult.mFocusable |= view4.isFocusable();
        }
        Arrays.fill(this.mSet, null);
    }
    
    @Override
    void onAnchorReady(final RecyclerView$Recycler recyclerView$Recycler, final RecyclerView$State recyclerView$State, final LinearLayoutManager$AnchorInfo linearLayoutManager$AnchorInfo, final int n) {
        super.onAnchorReady(recyclerView$Recycler, recyclerView$State, linearLayoutManager$AnchorInfo, n);
        this.updateMeasurements();
        if (recyclerView$State.getItemCount() > 0 && !recyclerView$State.isPreLayout()) {
            this.ensureAnchorIsInCorrectSpan(recyclerView$Recycler, recyclerView$State, linearLayoutManager$AnchorInfo, n);
        }
        this.ensureViewSet();
    }
    
    @Override
    public View onFocusSearchFailed(View view, int n, final RecyclerView$Recycler recyclerView$Recycler, final RecyclerView$State recyclerView$State) {
        final View containingItemView = this.findContainingItemView(view);
        View view2;
        if (containingItemView == null) {
            view2 = null;
        }
        else {
            final GridLayoutManager$LayoutParams gridLayoutManager$LayoutParams = (GridLayoutManager$LayoutParams)containingItemView.getLayoutParams();
            final int mSpanIndex = gridLayoutManager$LayoutParams.mSpanIndex;
            final int n2 = gridLayoutManager$LayoutParams.mSpanIndex + gridLayoutManager$LayoutParams.mSpanSize;
            if (super.onFocusSearchFailed(view, n, recyclerView$Recycler, recyclerView$State) == null) {
                return null;
            }
            if (this.convertFocusDirectionToLayoutDirection(n) == 1 != this.mShouldReverseLayout) {
                n = 1;
            }
            else {
                n = 0;
            }
            int n3;
            int childCount;
            if (n != 0) {
                n = this.getChildCount() - 1;
                n3 = -1;
                childCount = -1;
            }
            else {
                childCount = this.getChildCount();
                n = 0;
                n3 = 1;
            }
            final boolean b = this.mOrientation == 1 && this.isLayoutRTL();
            view = null;
            final int n4 = -1;
            int n5 = 0;
            int i;
            View child;
            int n6;
            int mSpanIndex2;
            GridLayoutManager$LayoutParams gridLayoutManager$LayoutParams2;
            int mSpanIndex3;
            int n7;
            boolean b2;
            int n8;
            int n9;
            boolean b3;
            int n10;
            int n11;
            for (i = n, n = n4; i != childCount; i += n3, n11 = mSpanIndex2, n5 = n, n = n11) {
                child = this.getChildAt(i);
                if (child == containingItemView) {
                    break;
                }
                if (!child.isFocusable()) {
                    n6 = n5;
                    mSpanIndex2 = n;
                    n = n6;
                }
                else {
                    gridLayoutManager$LayoutParams2 = (GridLayoutManager$LayoutParams)child.getLayoutParams();
                    mSpanIndex3 = gridLayoutManager$LayoutParams2.mSpanIndex;
                    n7 = gridLayoutManager$LayoutParams2.mSpanIndex + gridLayoutManager$LayoutParams2.mSpanSize;
                    if (mSpanIndex3 == mSpanIndex) {
                        view2 = child;
                        if (n7 == n2) {
                            return view2;
                        }
                    }
                    b2 = false;
                    if (view == null) {
                        n8 = 1;
                    }
                    else {
                        n9 = Math.min(n7, n2) - Math.max(mSpanIndex3, mSpanIndex);
                        if (n9 > n5) {
                            n8 = 1;
                        }
                        else {
                            n8 = (b2 ? 1 : 0);
                            if (n9 == n5) {
                                b3 = (mSpanIndex3 > n);
                                n8 = (b2 ? 1 : 0);
                                if (b == b3) {
                                    n8 = 1;
                                }
                            }
                        }
                    }
                    if (n8 != 0) {
                        mSpanIndex2 = gridLayoutManager$LayoutParams2.mSpanIndex;
                        n = Math.min(n7, n2) - Math.max(mSpanIndex3, mSpanIndex);
                        view = child;
                    }
                    else {
                        n10 = n;
                        n = n5;
                        mSpanIndex2 = n10;
                    }
                }
            }
            return view;
        }
        return view2;
    }
    
    @Override
    public void onInitializeAccessibilityNodeInfoForItem(final RecyclerView$Recycler recyclerView$Recycler, final RecyclerView$State recyclerView$State, final View view, final AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        final ViewGroup$LayoutParams layoutParams = view.getLayoutParams();
        if (!(layoutParams instanceof GridLayoutManager$LayoutParams)) {
            super.onInitializeAccessibilityNodeInfoForItem(view, accessibilityNodeInfoCompat);
            return;
        }
        final GridLayoutManager$LayoutParams gridLayoutManager$LayoutParams = (GridLayoutManager$LayoutParams)layoutParams;
        final int spanGroupIndex = this.getSpanGroupIndex(recyclerView$Recycler, recyclerView$State, gridLayoutManager$LayoutParams.getViewLayoutPosition());
        if (this.mOrientation == 0) {
            accessibilityNodeInfoCompat.setCollectionItemInfo(AccessibilityNodeInfoCompat$CollectionItemInfoCompat.obtain(gridLayoutManager$LayoutParams.getSpanIndex(), gridLayoutManager$LayoutParams.getSpanSize(), spanGroupIndex, 1, this.mSpanCount > 1 && gridLayoutManager$LayoutParams.getSpanSize() == this.mSpanCount, false));
            return;
        }
        accessibilityNodeInfoCompat.setCollectionItemInfo(AccessibilityNodeInfoCompat$CollectionItemInfoCompat.obtain(spanGroupIndex, 1, gridLayoutManager$LayoutParams.getSpanIndex(), gridLayoutManager$LayoutParams.getSpanSize(), this.mSpanCount > 1 && gridLayoutManager$LayoutParams.getSpanSize() == this.mSpanCount, false));
    }
    
    @Override
    public void onItemsAdded(final RecyclerView recyclerView, final int n, final int n2) {
        this.mSpanSizeLookup.invalidateSpanIndexCache();
    }
    
    @Override
    public void onItemsChanged(final RecyclerView recyclerView) {
        this.mSpanSizeLookup.invalidateSpanIndexCache();
    }
    
    @Override
    public void onItemsMoved(final RecyclerView recyclerView, final int n, final int n2, final int n3) {
        this.mSpanSizeLookup.invalidateSpanIndexCache();
    }
    
    @Override
    public void onItemsRemoved(final RecyclerView recyclerView, final int n, final int n2) {
        this.mSpanSizeLookup.invalidateSpanIndexCache();
    }
    
    @Override
    public void onItemsUpdated(final RecyclerView recyclerView, final int n, final int n2, final Object o) {
        this.mSpanSizeLookup.invalidateSpanIndexCache();
    }
    
    @Override
    public void onLayoutChildren(final RecyclerView$Recycler recyclerView$Recycler, final RecyclerView$State recyclerView$State) {
        if (recyclerView$State.isPreLayout()) {
            this.cachePreLayoutSpanMapping();
        }
        super.onLayoutChildren(recyclerView$Recycler, recyclerView$State);
        this.clearPreLayoutSpanMappingCache();
    }
    
    @Override
    public void onLayoutCompleted(final RecyclerView$State recyclerView$State) {
        super.onLayoutCompleted(recyclerView$State);
        this.mPendingSpanCountChange = false;
    }
    
    @Override
    public int scrollHorizontallyBy(final int n, final RecyclerView$Recycler recyclerView$Recycler, final RecyclerView$State recyclerView$State) {
        this.updateMeasurements();
        this.ensureViewSet();
        return super.scrollHorizontallyBy(n, recyclerView$Recycler, recyclerView$State);
    }
    
    @Override
    public int scrollVerticallyBy(final int n, final RecyclerView$Recycler recyclerView$Recycler, final RecyclerView$State recyclerView$State) {
        this.updateMeasurements();
        this.ensureViewSet();
        return super.scrollVerticallyBy(n, recyclerView$Recycler, recyclerView$State);
    }
    
    @Override
    public void setMeasuredDimension(final Rect rect, int chooseSize, int chooseSize2) {
        if (this.mCachedBorders == null) {
            super.setMeasuredDimension(rect, chooseSize, chooseSize2);
        }
        final int n = this.getPaddingRight() + this.getPaddingLeft();
        final int n2 = this.getPaddingTop() + this.getPaddingBottom();
        if (this.mOrientation == 1) {
            final int chooseSize3 = RecyclerView$LayoutManager.chooseSize(chooseSize2, n2 + rect.height(), this.getMinimumHeight());
            chooseSize2 = RecyclerView$LayoutManager.chooseSize(chooseSize, n + this.mCachedBorders[this.mCachedBorders.length - 1], this.getMinimumWidth());
            chooseSize = chooseSize3;
        }
        else {
            final int chooseSize4 = RecyclerView$LayoutManager.chooseSize(chooseSize, n + rect.width(), this.getMinimumWidth());
            chooseSize = RecyclerView$LayoutManager.chooseSize(chooseSize2, n2 + this.mCachedBorders[this.mCachedBorders.length - 1], this.getMinimumHeight());
            chooseSize2 = chooseSize4;
        }
        this.setMeasuredDimension(chooseSize2, chooseSize);
    }
    
    public void setSpanCount(final int mSpanCount) {
        if (mSpanCount == this.mSpanCount) {
            return;
        }
        this.mPendingSpanCountChange = true;
        if (mSpanCount < 1) {
            throw new IllegalArgumentException("Span count should be at least 1. Provided " + mSpanCount);
        }
        this.mSpanCount = mSpanCount;
        this.mSpanSizeLookup.invalidateSpanIndexCache();
        this.requestLayout();
    }
    
    public void setSpanSizeLookup(final GridLayoutManager$SpanSizeLookup mSpanSizeLookup) {
        this.mSpanSizeLookup = mSpanSizeLookup;
    }
    
    @Override
    public void setStackFromEnd(final boolean b) {
        if (b) {
            throw new UnsupportedOperationException("GridLayoutManager does not support stack from end. Consider using reverse layout");
        }
        super.setStackFromEnd(false);
    }
    
    @Override
    public boolean supportsPredictiveItemAnimations() {
        return this.mPendingSavedState == null && !this.mPendingSpanCountChange;
    }
}
