// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.os.SystemClock;
import android.support.v4.view.AccessibilityDelegateCompat;
import android.support.v4.view.VelocityTrackerCompat;
import android.view.FocusFinder;
import android.view.ViewParent;
import android.graphics.Canvas;
import android.util.SparseArray;
import android.support.v4.os.TraceCompat;
import android.support.v4.view.MotionEventCompat;
import android.util.TypedValue;
import android.view.MotionEvent;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import android.view.ViewConfiguration;
import android.os.Build$VERSION;
import android.view.VelocityTracker;
import android.support.v4.view.NestedScrollingChildHelper;
import java.util.List;
import android.support.v4.widget.EdgeEffectCompat;
import android.view.accessibility.AccessibilityManager;
import android.view.animation.Interpolator;
import android.support.v4.view.ScrollingView;
import android.support.v4.view.NestedScrollingChild;
import android.view.ViewGroup;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat$CollectionItemInfoCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat$CollectionInfoCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v4.view.accessibility.AccessibilityRecordCompat;
import android.support.v4.view.accessibility.AccessibilityEventCompat;
import android.view.accessibility.AccessibilityEvent;
import java.util.ArrayList;
import android.graphics.RectF;
import android.graphics.Matrix;
import android.support.v4.view.ViewCompat;
import android.view.ViewGroup$MarginLayoutParams;
import android.graphics.Rect;
import android.view.ViewGroup$LayoutParams;
import android.content.res.TypedArray;
import android.support.v7.recyclerview.R$styleable;
import android.util.AttributeSet;
import android.content.Context;
import android.view.View$MeasureSpec;
import android.view.View;

public abstract class RecyclerView$LayoutManager
{
    boolean mAutoMeasure;
    ChildHelper mChildHelper;
    private int mHeight;
    private int mHeightMode;
    boolean mIsAttachedToWindow;
    private boolean mMeasurementCacheEnabled;
    RecyclerView mRecyclerView;
    boolean mRequestedSimpleAnimations;
    RecyclerView$SmoothScroller mSmoothScroller;
    private int mWidth;
    private int mWidthMode;
    
    public RecyclerView$LayoutManager() {
        this.mRequestedSimpleAnimations = false;
        this.mIsAttachedToWindow = false;
        this.mAutoMeasure = false;
        this.mMeasurementCacheEnabled = true;
    }
    
    private void addViewInt(final View view, final int n, final boolean b) {
        final RecyclerView$ViewHolder childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
        if (b || childViewHolderInt.isRemoved()) {
            this.mRecyclerView.mViewInfoStore.addToDisappearedInLayout(childViewHolderInt);
        }
        else {
            this.mRecyclerView.mViewInfoStore.removeFromDisappearedInLayout(childViewHolderInt);
        }
        final RecyclerView$LayoutParams recyclerView$LayoutParams = (RecyclerView$LayoutParams)view.getLayoutParams();
        if (childViewHolderInt.wasReturnedFromScrap() || childViewHolderInt.isScrap()) {
            if (childViewHolderInt.isScrap()) {
                childViewHolderInt.unScrap();
            }
            else {
                childViewHolderInt.clearReturnedFromScrapFlag();
            }
            this.mChildHelper.attachViewToParent(view, n, view.getLayoutParams(), false);
        }
        else if (view.getParent() == this.mRecyclerView) {
            final int indexOfChild = this.mChildHelper.indexOfChild(view);
            int childCount;
            if ((childCount = n) == -1) {
                childCount = this.mChildHelper.getChildCount();
            }
            if (indexOfChild == -1) {
                throw new IllegalStateException("Added View has RecyclerView as parent but view is not a real child. Unfiltered index:" + this.mRecyclerView.indexOfChild(view));
            }
            if (indexOfChild != childCount) {
                this.mRecyclerView.mLayout.moveView(indexOfChild, childCount);
            }
        }
        else {
            this.mChildHelper.addView(view, n, false);
            recyclerView$LayoutParams.mInsetsDirty = true;
            if (this.mSmoothScroller != null && this.mSmoothScroller.isRunning()) {
                this.mSmoothScroller.onChildAttachedToWindow(view);
            }
        }
        if (recyclerView$LayoutParams.mPendingInvalidate) {
            childViewHolderInt.itemView.invalidate();
            recyclerView$LayoutParams.mPendingInvalidate = false;
        }
    }
    
    public static int chooseSize(int n, final int n2, final int n3) {
        final int mode = View$MeasureSpec.getMode(n);
        final int n4 = n = View$MeasureSpec.getSize(n);
        switch (mode) {
            default: {
                n = Math.max(n2, n3);
                return n;
            }
            case 1073741824: {
                return n;
            }
            case Integer.MIN_VALUE: {
                return Math.min(n4, Math.max(n2, n3));
            }
        }
    }
    
    private void detachViewInternal(final int n, final View view) {
        this.mChildHelper.detachViewFromParent(n);
    }
    
    public static int getChildMeasureSpec(int n, int n2, int n3, final int n4, final boolean b) {
        final int n5 = 0;
        final int n6 = 0;
        final int max = Math.max(0, n - n3);
        if (n4 >= 0) {
            n = 1073741824;
            n3 = n4;
        }
        else {
            if (b) {
                if (n4 == -1) {
                    switch (n2) {
                        default: {
                            n2 = 0;
                            n = n5;
                            break;
                        }
                        case Integer.MIN_VALUE:
                        case 1073741824: {
                            n = max;
                            break;
                        }
                        case 0: {
                            n2 = 0;
                            n = n5;
                            break;
                        }
                    }
                    n3 = n;
                    n = n2;
                    return View$MeasureSpec.makeMeasureSpec(n3, n);
                }
                if (n4 == -2) {
                    n3 = 0;
                    n = n6;
                    return View$MeasureSpec.makeMeasureSpec(n3, n);
                }
            }
            else {
                if (n4 == -1) {
                    n = n2;
                    n3 = max;
                    return View$MeasureSpec.makeMeasureSpec(n3, n);
                }
                if (n4 == -2) {
                    if (n2 != Integer.MIN_VALUE) {
                        n = n6;
                        n3 = max;
                        if (n2 != 1073741824) {
                            return View$MeasureSpec.makeMeasureSpec(n3, n);
                        }
                    }
                    n = Integer.MIN_VALUE;
                    n3 = max;
                    return View$MeasureSpec.makeMeasureSpec(n3, n);
                }
            }
            n3 = 0;
            n = n6;
        }
        return View$MeasureSpec.makeMeasureSpec(n3, n);
    }
    
    @Deprecated
    public static int getChildMeasureSpec(int n, int n2, final int n3, final boolean b) {
        final int n4 = 1073741824;
        final int max = Math.max(0, n - n2);
        if (b) {
            if (n3 >= 0) {
                n = n3;
                n2 = n4;
            }
            else {
                n2 = 0;
                n = 0;
            }
        }
        else {
            n2 = n4;
            if ((n = n3) < 0) {
                if (n3 == -1) {
                    n = max;
                    n2 = n4;
                }
                else if (n3 == -2) {
                    n2 = Integer.MIN_VALUE;
                    n = max;
                }
                else {
                    n2 = 0;
                    n = 0;
                }
            }
        }
        return View$MeasureSpec.makeMeasureSpec(n, n2);
    }
    
    public static RecyclerView$LayoutManager$Properties getProperties(final Context context, final AttributeSet set, final int n, final int n2) {
        final RecyclerView$LayoutManager$Properties recyclerView$LayoutManager$Properties = new RecyclerView$LayoutManager$Properties();
        final TypedArray obtainStyledAttributes = context.obtainStyledAttributes(set, R$styleable.RecyclerView, n, n2);
        recyclerView$LayoutManager$Properties.orientation = obtainStyledAttributes.getInt(R$styleable.RecyclerView_android_orientation, 1);
        recyclerView$LayoutManager$Properties.spanCount = obtainStyledAttributes.getInt(R$styleable.RecyclerView_spanCount, 1);
        recyclerView$LayoutManager$Properties.reverseLayout = obtainStyledAttributes.getBoolean(R$styleable.RecyclerView_reverseLayout, false);
        recyclerView$LayoutManager$Properties.stackFromEnd = obtainStyledAttributes.getBoolean(R$styleable.RecyclerView_stackFromEnd, false);
        obtainStyledAttributes.recycle();
        return recyclerView$LayoutManager$Properties;
    }
    
    private static boolean isMeasurementUpToDate(final int n, int size, final int n2) {
        final boolean b = true;
        final int mode = View$MeasureSpec.getMode(size);
        size = View$MeasureSpec.getSize(size);
        boolean b2;
        if (n2 > 0 && n != n2) {
            b2 = false;
        }
        else {
            b2 = b;
            switch (mode) {
                case 0: {
                    break;
                }
                default: {
                    return false;
                }
                case Integer.MIN_VALUE: {
                    b2 = b;
                    if (size < n) {
                        return false;
                    }
                    break;
                }
                case 1073741824: {
                    b2 = b;
                    if (size != n) {
                        return false;
                    }
                    break;
                }
            }
        }
        return b2;
    }
    
    private void onSmoothScrollerStopped(final RecyclerView$SmoothScroller recyclerView$SmoothScroller) {
        if (this.mSmoothScroller == recyclerView$SmoothScroller) {
            this.mSmoothScroller = null;
        }
    }
    
    private void scrapOrRecycleView(final RecyclerView$Recycler recyclerView$Recycler, final int n, final View view) {
        final RecyclerView$ViewHolder childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
        if (childViewHolderInt.shouldIgnore()) {
            return;
        }
        if (childViewHolderInt.isInvalid() && !childViewHolderInt.isRemoved() && !this.mRecyclerView.mAdapter.hasStableIds()) {
            this.removeViewAt(n);
            recyclerView$Recycler.recycleViewHolderInternal(childViewHolderInt);
            return;
        }
        this.detachViewAt(n);
        recyclerView$Recycler.scrapView(view);
        this.mRecyclerView.mViewInfoStore.onViewDetached(childViewHolderInt);
    }
    
    public void addDisappearingView(final View view) {
        this.addDisappearingView(view, -1);
    }
    
    public void addDisappearingView(final View view, final int n) {
        this.addViewInt(view, n, true);
    }
    
    public void addView(final View view) {
        this.addView(view, -1);
    }
    
    public void addView(final View view, final int n) {
        this.addViewInt(view, n, false);
    }
    
    public void assertInLayoutOrScroll(final String s) {
        if (this.mRecyclerView != null) {
            this.mRecyclerView.assertInLayoutOrScroll(s);
        }
    }
    
    public void assertNotInLayoutOrScroll(final String s) {
        if (this.mRecyclerView != null) {
            this.mRecyclerView.assertNotInLayoutOrScroll(s);
        }
    }
    
    public void attachView(final View view) {
        this.attachView(view, -1);
    }
    
    public void attachView(final View view, final int n) {
        this.attachView(view, n, (RecyclerView$LayoutParams)view.getLayoutParams());
    }
    
    public void attachView(final View view, final int n, final RecyclerView$LayoutParams recyclerView$LayoutParams) {
        final RecyclerView$ViewHolder childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
        if (childViewHolderInt.isRemoved()) {
            this.mRecyclerView.mViewInfoStore.addToDisappearedInLayout(childViewHolderInt);
        }
        else {
            this.mRecyclerView.mViewInfoStore.removeFromDisappearedInLayout(childViewHolderInt);
        }
        this.mChildHelper.attachViewToParent(view, n, (ViewGroup$LayoutParams)recyclerView$LayoutParams, childViewHolderInt.isRemoved());
    }
    
    public void calculateItemDecorationsForChild(final View view, final Rect rect) {
        if (this.mRecyclerView == null) {
            rect.set(0, 0, 0, 0);
            return;
        }
        rect.set(this.mRecyclerView.getItemDecorInsetsForChild(view));
    }
    
    public boolean canScrollHorizontally() {
        return false;
    }
    
    public boolean canScrollVertically() {
        return false;
    }
    
    public boolean checkLayoutParams(final RecyclerView$LayoutParams recyclerView$LayoutParams) {
        return recyclerView$LayoutParams != null;
    }
    
    public int computeHorizontalScrollExtent(final RecyclerView$State recyclerView$State) {
        return 0;
    }
    
    public int computeHorizontalScrollOffset(final RecyclerView$State recyclerView$State) {
        return 0;
    }
    
    public int computeHorizontalScrollRange(final RecyclerView$State recyclerView$State) {
        return 0;
    }
    
    public int computeVerticalScrollExtent(final RecyclerView$State recyclerView$State) {
        return 0;
    }
    
    public int computeVerticalScrollOffset(final RecyclerView$State recyclerView$State) {
        return 0;
    }
    
    public int computeVerticalScrollRange(final RecyclerView$State recyclerView$State) {
        return 0;
    }
    
    public void detachAndScrapAttachedViews(final RecyclerView$Recycler recyclerView$Recycler) {
        for (int i = this.getChildCount() - 1; i >= 0; --i) {
            this.scrapOrRecycleView(recyclerView$Recycler, i, this.getChildAt(i));
        }
    }
    
    public void detachAndScrapView(final View view, final RecyclerView$Recycler recyclerView$Recycler) {
        this.scrapOrRecycleView(recyclerView$Recycler, this.mChildHelper.indexOfChild(view), view);
    }
    
    public void detachAndScrapViewAt(final int n, final RecyclerView$Recycler recyclerView$Recycler) {
        this.scrapOrRecycleView(recyclerView$Recycler, n, this.getChildAt(n));
    }
    
    public void detachView(final View view) {
        final int indexOfChild = this.mChildHelper.indexOfChild(view);
        if (indexOfChild >= 0) {
            this.detachViewInternal(indexOfChild, view);
        }
    }
    
    public void detachViewAt(final int n) {
        this.detachViewInternal(n, this.getChildAt(n));
    }
    
    void dispatchAttachedToWindow(final RecyclerView recyclerView) {
        this.mIsAttachedToWindow = true;
        this.onAttachedToWindow(recyclerView);
    }
    
    void dispatchDetachedFromWindow(final RecyclerView recyclerView, final RecyclerView$Recycler recyclerView$Recycler) {
        this.mIsAttachedToWindow = false;
        this.onDetachedFromWindow(recyclerView, recyclerView$Recycler);
    }
    
    public void endAnimation(final View view) {
        if (this.mRecyclerView.mItemAnimator != null) {
            this.mRecyclerView.mItemAnimator.endAnimation(RecyclerView.getChildViewHolderInt(view));
        }
    }
    
    public View findContainingItemView(View containingItemView) {
        if (this.mRecyclerView != null) {
            containingItemView = this.mRecyclerView.findContainingItemView(containingItemView);
            if (containingItemView != null && !this.mChildHelper.isHidden(containingItemView)) {
                return containingItemView;
            }
        }
        return null;
    }
    
    public View findViewByPosition(final int n) {
        for (int childCount = this.getChildCount(), i = 0; i < childCount; ++i) {
            final View child = this.getChildAt(i);
            final RecyclerView$ViewHolder childViewHolderInt = RecyclerView.getChildViewHolderInt(child);
            if (childViewHolderInt != null && childViewHolderInt.getLayoutPosition() == n && !childViewHolderInt.shouldIgnore() && (this.mRecyclerView.mState.isPreLayout() || !childViewHolderInt.isRemoved())) {
                return child;
            }
        }
        return null;
    }
    
    public abstract RecyclerView$LayoutParams generateDefaultLayoutParams();
    
    public RecyclerView$LayoutParams generateLayoutParams(final Context context, final AttributeSet set) {
        return new RecyclerView$LayoutParams(context, set);
    }
    
    public RecyclerView$LayoutParams generateLayoutParams(final ViewGroup$LayoutParams viewGroup$LayoutParams) {
        if (viewGroup$LayoutParams instanceof RecyclerView$LayoutParams) {
            return new RecyclerView$LayoutParams((RecyclerView$LayoutParams)viewGroup$LayoutParams);
        }
        if (viewGroup$LayoutParams instanceof ViewGroup$MarginLayoutParams) {
            return new RecyclerView$LayoutParams((ViewGroup$MarginLayoutParams)viewGroup$LayoutParams);
        }
        return new RecyclerView$LayoutParams(viewGroup$LayoutParams);
    }
    
    public int getBaseline() {
        return -1;
    }
    
    public int getBottomDecorationHeight(final View view) {
        return ((RecyclerView$LayoutParams)view.getLayoutParams()).mDecorInsets.bottom;
    }
    
    public View getChildAt(final int n) {
        if (this.mChildHelper != null) {
            return this.mChildHelper.getChildAt(n);
        }
        return null;
    }
    
    public int getChildCount() {
        if (this.mChildHelper != null) {
            return this.mChildHelper.getChildCount();
        }
        return 0;
    }
    
    public boolean getClipToPadding() {
        return this.mRecyclerView != null && this.mRecyclerView.mClipToPadding;
    }
    
    public int getColumnCountForAccessibility(final RecyclerView$Recycler recyclerView$Recycler, final RecyclerView$State recyclerView$State) {
        if (this.mRecyclerView != null && this.mRecyclerView.mAdapter != null && this.canScrollHorizontally()) {
            return this.mRecyclerView.mAdapter.getItemCount();
        }
        return 1;
    }
    
    public int getDecoratedBottom(final View view) {
        return view.getBottom() + this.getBottomDecorationHeight(view);
    }
    
    public void getDecoratedBoundsWithMargins(final View view, final Rect rect) {
        final RecyclerView$LayoutParams recyclerView$LayoutParams = (RecyclerView$LayoutParams)view.getLayoutParams();
        final Rect mDecorInsets = recyclerView$LayoutParams.mDecorInsets;
        rect.set(view.getLeft() - mDecorInsets.left - recyclerView$LayoutParams.leftMargin, view.getTop() - mDecorInsets.top - recyclerView$LayoutParams.topMargin, view.getRight() + mDecorInsets.right + recyclerView$LayoutParams.rightMargin, recyclerView$LayoutParams.bottomMargin + (mDecorInsets.bottom + view.getBottom()));
    }
    
    public int getDecoratedLeft(final View view) {
        return view.getLeft() - this.getLeftDecorationWidth(view);
    }
    
    public int getDecoratedMeasuredHeight(final View view) {
        final Rect mDecorInsets = ((RecyclerView$LayoutParams)view.getLayoutParams()).mDecorInsets;
        return mDecorInsets.bottom + (view.getMeasuredHeight() + mDecorInsets.top);
    }
    
    public int getDecoratedMeasuredWidth(final View view) {
        final Rect mDecorInsets = ((RecyclerView$LayoutParams)view.getLayoutParams()).mDecorInsets;
        return mDecorInsets.right + (view.getMeasuredWidth() + mDecorInsets.left);
    }
    
    public int getDecoratedRight(final View view) {
        return view.getRight() + this.getRightDecorationWidth(view);
    }
    
    public int getDecoratedTop(final View view) {
        return view.getTop() - this.getTopDecorationHeight(view);
    }
    
    public View getFocusedChild() {
        if (this.mRecyclerView != null) {
            final View focusedChild = this.mRecyclerView.getFocusedChild();
            if (focusedChild != null && !this.mChildHelper.isHidden(focusedChild)) {
                return focusedChild;
            }
        }
        return null;
    }
    
    public int getHeight() {
        return this.mHeight;
    }
    
    public int getHeightMode() {
        return this.mHeightMode;
    }
    
    public int getItemCount() {
        RecyclerView$Adapter adapter;
        if (this.mRecyclerView != null) {
            adapter = this.mRecyclerView.getAdapter();
        }
        else {
            adapter = null;
        }
        if (adapter != null) {
            return adapter.getItemCount();
        }
        return 0;
    }
    
    public int getItemViewType(final View view) {
        return RecyclerView.getChildViewHolderInt(view).getItemViewType();
    }
    
    public int getLayoutDirection() {
        return ViewCompat.getLayoutDirection((View)this.mRecyclerView);
    }
    
    public int getLeftDecorationWidth(final View view) {
        return ((RecyclerView$LayoutParams)view.getLayoutParams()).mDecorInsets.left;
    }
    
    public int getMinimumHeight() {
        return ViewCompat.getMinimumHeight((View)this.mRecyclerView);
    }
    
    public int getMinimumWidth() {
        return ViewCompat.getMinimumWidth((View)this.mRecyclerView);
    }
    
    public int getPaddingBottom() {
        if (this.mRecyclerView != null) {
            return this.mRecyclerView.getPaddingBottom();
        }
        return 0;
    }
    
    public int getPaddingEnd() {
        if (this.mRecyclerView != null) {
            return ViewCompat.getPaddingEnd((View)this.mRecyclerView);
        }
        return 0;
    }
    
    public int getPaddingLeft() {
        if (this.mRecyclerView != null) {
            return this.mRecyclerView.getPaddingLeft();
        }
        return 0;
    }
    
    public int getPaddingRight() {
        if (this.mRecyclerView != null) {
            return this.mRecyclerView.getPaddingRight();
        }
        return 0;
    }
    
    public int getPaddingStart() {
        if (this.mRecyclerView != null) {
            return ViewCompat.getPaddingStart((View)this.mRecyclerView);
        }
        return 0;
    }
    
    public int getPaddingTop() {
        if (this.mRecyclerView != null) {
            return this.mRecyclerView.getPaddingTop();
        }
        return 0;
    }
    
    public int getPosition(final View view) {
        return ((RecyclerView$LayoutParams)view.getLayoutParams()).getViewLayoutPosition();
    }
    
    public int getRightDecorationWidth(final View view) {
        return ((RecyclerView$LayoutParams)view.getLayoutParams()).mDecorInsets.right;
    }
    
    public int getRowCountForAccessibility(final RecyclerView$Recycler recyclerView$Recycler, final RecyclerView$State recyclerView$State) {
        if (this.mRecyclerView != null && this.mRecyclerView.mAdapter != null && this.canScrollVertically()) {
            return this.mRecyclerView.mAdapter.getItemCount();
        }
        return 1;
    }
    
    public int getSelectionModeForAccessibility(final RecyclerView$Recycler recyclerView$Recycler, final RecyclerView$State recyclerView$State) {
        return 0;
    }
    
    public int getTopDecorationHeight(final View view) {
        return ((RecyclerView$LayoutParams)view.getLayoutParams()).mDecorInsets.top;
    }
    
    public void getTransformedBoundingBox(final View view, final boolean b, final Rect rect) {
        if (b) {
            final Rect mDecorInsets = ((RecyclerView$LayoutParams)view.getLayoutParams()).mDecorInsets;
            rect.set(-mDecorInsets.left, -mDecorInsets.top, view.getWidth() + mDecorInsets.right, mDecorInsets.bottom + view.getHeight());
        }
        else {
            rect.set(0, 0, view.getWidth(), view.getHeight());
        }
        if (this.mRecyclerView != null) {
            final Matrix matrix = ViewCompat.getMatrix(view);
            if (matrix != null && !matrix.isIdentity()) {
                final RectF mTempRectF = this.mRecyclerView.mTempRectF;
                mTempRectF.set(rect);
                matrix.mapRect(mTempRectF);
                rect.set((int)Math.floor(mTempRectF.left), (int)Math.floor(mTempRectF.top), (int)Math.ceil(mTempRectF.right), (int)Math.ceil(mTempRectF.bottom));
            }
        }
        rect.offset(view.getLeft(), view.getTop());
    }
    
    public int getWidth() {
        return this.mWidth;
    }
    
    public int getWidthMode() {
        return this.mWidthMode;
    }
    
    boolean hasFlexibleChildInBothOrientations() {
        final boolean b = false;
        final int childCount = this.getChildCount();
        int n = 0;
        boolean b2;
        while (true) {
            b2 = b;
            if (n >= childCount) {
                break;
            }
            final ViewGroup$LayoutParams layoutParams = this.getChildAt(n).getLayoutParams();
            if (layoutParams.width < 0 && layoutParams.height < 0) {
                b2 = true;
                break;
            }
            ++n;
        }
        return b2;
    }
    
    public boolean hasFocus() {
        return this.mRecyclerView != null && this.mRecyclerView.hasFocus();
    }
    
    public void ignoreView(final View view) {
        if (view.getParent() != this.mRecyclerView || this.mRecyclerView.indexOfChild(view) == -1) {
            throw new IllegalArgumentException("View should be fully attached to be ignored");
        }
        final RecyclerView$ViewHolder childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
        childViewHolderInt.addFlags(128);
        this.mRecyclerView.mViewInfoStore.removeViewHolder(childViewHolderInt);
    }
    
    public boolean isAttachedToWindow() {
        return this.mIsAttachedToWindow;
    }
    
    public boolean isAutoMeasureEnabled() {
        return this.mAutoMeasure;
    }
    
    public boolean isFocused() {
        return this.mRecyclerView != null && this.mRecyclerView.isFocused();
    }
    
    public boolean isLayoutHierarchical(final RecyclerView$Recycler recyclerView$Recycler, final RecyclerView$State recyclerView$State) {
        return false;
    }
    
    public boolean isMeasurementCacheEnabled() {
        return this.mMeasurementCacheEnabled;
    }
    
    public boolean isSmoothScrolling() {
        return this.mSmoothScroller != null && this.mSmoothScroller.isRunning();
    }
    
    public void layoutDecorated(final View view, final int n, final int n2, final int n3, final int n4) {
        final Rect mDecorInsets = ((RecyclerView$LayoutParams)view.getLayoutParams()).mDecorInsets;
        view.layout(mDecorInsets.left + n, mDecorInsets.top + n2, n3 - mDecorInsets.right, n4 - mDecorInsets.bottom);
    }
    
    public void layoutDecoratedWithMargins(final View view, final int n, final int n2, final int n3, final int n4) {
        final RecyclerView$LayoutParams recyclerView$LayoutParams = (RecyclerView$LayoutParams)view.getLayoutParams();
        final Rect mDecorInsets = recyclerView$LayoutParams.mDecorInsets;
        view.layout(mDecorInsets.left + n + recyclerView$LayoutParams.leftMargin, mDecorInsets.top + n2 + recyclerView$LayoutParams.topMargin, n3 - mDecorInsets.right - recyclerView$LayoutParams.rightMargin, n4 - mDecorInsets.bottom - recyclerView$LayoutParams.bottomMargin);
    }
    
    public void measureChild(final View view, int childMeasureSpec, int childMeasureSpec2) {
        final RecyclerView$LayoutParams recyclerView$LayoutParams = (RecyclerView$LayoutParams)view.getLayoutParams();
        final Rect itemDecorInsetsForChild = this.mRecyclerView.getItemDecorInsetsForChild(view);
        final int left = itemDecorInsetsForChild.left;
        final int right = itemDecorInsetsForChild.right;
        final int top = itemDecorInsetsForChild.top;
        final int bottom = itemDecorInsetsForChild.bottom;
        childMeasureSpec = getChildMeasureSpec(this.getWidth(), this.getWidthMode(), left + right + childMeasureSpec + (this.getPaddingLeft() + this.getPaddingRight()), recyclerView$LayoutParams.width, this.canScrollHorizontally());
        childMeasureSpec2 = getChildMeasureSpec(this.getHeight(), this.getHeightMode(), bottom + top + childMeasureSpec2 + (this.getPaddingTop() + this.getPaddingBottom()), recyclerView$LayoutParams.height, this.canScrollVertically());
        if (this.shouldMeasureChild(view, childMeasureSpec, childMeasureSpec2, recyclerView$LayoutParams)) {
            view.measure(childMeasureSpec, childMeasureSpec2);
        }
    }
    
    public void measureChildWithMargins(final View view, int childMeasureSpec, int childMeasureSpec2) {
        final RecyclerView$LayoutParams recyclerView$LayoutParams = (RecyclerView$LayoutParams)view.getLayoutParams();
        final Rect itemDecorInsetsForChild = this.mRecyclerView.getItemDecorInsetsForChild(view);
        final int left = itemDecorInsetsForChild.left;
        final int right = itemDecorInsetsForChild.right;
        final int top = itemDecorInsetsForChild.top;
        final int bottom = itemDecorInsetsForChild.bottom;
        childMeasureSpec = getChildMeasureSpec(this.getWidth(), this.getWidthMode(), left + right + childMeasureSpec + (this.getPaddingLeft() + this.getPaddingRight() + recyclerView$LayoutParams.leftMargin + recyclerView$LayoutParams.rightMargin), recyclerView$LayoutParams.width, this.canScrollHorizontally());
        childMeasureSpec2 = getChildMeasureSpec(this.getHeight(), this.getHeightMode(), bottom + top + childMeasureSpec2 + (this.getPaddingTop() + this.getPaddingBottom() + recyclerView$LayoutParams.topMargin + recyclerView$LayoutParams.bottomMargin), recyclerView$LayoutParams.height, this.canScrollVertically());
        if (this.shouldMeasureChild(view, childMeasureSpec, childMeasureSpec2, recyclerView$LayoutParams)) {
            view.measure(childMeasureSpec, childMeasureSpec2);
        }
    }
    
    public void moveView(final int n, final int n2) {
        final View child = this.getChildAt(n);
        if (child == null) {
            throw new IllegalArgumentException("Cannot move a child from non-existing index:" + n);
        }
        this.detachViewAt(n);
        this.attachView(child, n2);
    }
    
    public void offsetChildrenHorizontal(final int n) {
        if (this.mRecyclerView != null) {
            this.mRecyclerView.offsetChildrenHorizontal(n);
        }
    }
    
    public void offsetChildrenVertical(final int n) {
        if (this.mRecyclerView != null) {
            this.mRecyclerView.offsetChildrenVertical(n);
        }
    }
    
    public void onAdapterChanged(final RecyclerView$Adapter recyclerView$Adapter, final RecyclerView$Adapter recyclerView$Adapter2) {
    }
    
    public boolean onAddFocusables(final RecyclerView recyclerView, final ArrayList<View> list, final int n, final int n2) {
        return false;
    }
    
    public void onAttachedToWindow(final RecyclerView recyclerView) {
    }
    
    @Deprecated
    public void onDetachedFromWindow(final RecyclerView recyclerView) {
    }
    
    public void onDetachedFromWindow(final RecyclerView recyclerView, final RecyclerView$Recycler recyclerView$Recycler) {
        this.onDetachedFromWindow(recyclerView);
    }
    
    public View onFocusSearchFailed(final View view, final int n, final RecyclerView$Recycler recyclerView$Recycler, final RecyclerView$State recyclerView$State) {
        return null;
    }
    
    public void onInitializeAccessibilityEvent(final RecyclerView$Recycler recyclerView$Recycler, final RecyclerView$State recyclerView$State, final AccessibilityEvent accessibilityEvent) {
        final boolean b = true;
        final AccessibilityRecordCompat record = AccessibilityEventCompat.asRecord(accessibilityEvent);
        if (this.mRecyclerView != null && record != null) {
            boolean scrollable = b;
            if (!ViewCompat.canScrollVertically((View)this.mRecyclerView, 1)) {
                scrollable = b;
                if (!ViewCompat.canScrollVertically((View)this.mRecyclerView, -1)) {
                    scrollable = b;
                    if (!ViewCompat.canScrollHorizontally((View)this.mRecyclerView, -1)) {
                        scrollable = (ViewCompat.canScrollHorizontally((View)this.mRecyclerView, 1) && b);
                    }
                }
            }
            record.setScrollable(scrollable);
            if (this.mRecyclerView.mAdapter != null) {
                record.setItemCount(this.mRecyclerView.mAdapter.getItemCount());
            }
        }
    }
    
    public void onInitializeAccessibilityEvent(final AccessibilityEvent accessibilityEvent) {
        this.onInitializeAccessibilityEvent(this.mRecyclerView.mRecycler, this.mRecyclerView.mState, accessibilityEvent);
    }
    
    void onInitializeAccessibilityNodeInfo(final AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        this.onInitializeAccessibilityNodeInfo(this.mRecyclerView.mRecycler, this.mRecyclerView.mState, accessibilityNodeInfoCompat);
    }
    
    public void onInitializeAccessibilityNodeInfo(final RecyclerView$Recycler recyclerView$Recycler, final RecyclerView$State recyclerView$State, final AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        if (ViewCompat.canScrollVertically((View)this.mRecyclerView, -1) || ViewCompat.canScrollHorizontally((View)this.mRecyclerView, -1)) {
            accessibilityNodeInfoCompat.addAction(8192);
            accessibilityNodeInfoCompat.setScrollable(true);
        }
        if (ViewCompat.canScrollVertically((View)this.mRecyclerView, 1) || ViewCompat.canScrollHorizontally((View)this.mRecyclerView, 1)) {
            accessibilityNodeInfoCompat.addAction(4096);
            accessibilityNodeInfoCompat.setScrollable(true);
        }
        accessibilityNodeInfoCompat.setCollectionInfo(AccessibilityNodeInfoCompat$CollectionInfoCompat.obtain(this.getRowCountForAccessibility(recyclerView$Recycler, recyclerView$State), this.getColumnCountForAccessibility(recyclerView$Recycler, recyclerView$State), this.isLayoutHierarchical(recyclerView$Recycler, recyclerView$State), this.getSelectionModeForAccessibility(recyclerView$Recycler, recyclerView$State)));
    }
    
    public void onInitializeAccessibilityNodeInfoForItem(final RecyclerView$Recycler recyclerView$Recycler, final RecyclerView$State recyclerView$State, final View view, final AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        int position;
        if (this.canScrollVertically()) {
            position = this.getPosition(view);
        }
        else {
            position = 0;
        }
        int position2;
        if (this.canScrollHorizontally()) {
            position2 = this.getPosition(view);
        }
        else {
            position2 = 0;
        }
        accessibilityNodeInfoCompat.setCollectionItemInfo(AccessibilityNodeInfoCompat$CollectionItemInfoCompat.obtain(position, 1, position2, 1, false, false));
    }
    
    void onInitializeAccessibilityNodeInfoForItem(final View view, final AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        final RecyclerView$ViewHolder childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
        if (childViewHolderInt != null && !childViewHolderInt.isRemoved() && !this.mChildHelper.isHidden(childViewHolderInt.itemView)) {
            this.onInitializeAccessibilityNodeInfoForItem(this.mRecyclerView.mRecycler, this.mRecyclerView.mState, view, accessibilityNodeInfoCompat);
        }
    }
    
    public View onInterceptFocusSearch(final View view, final int n) {
        return null;
    }
    
    public void onItemsAdded(final RecyclerView recyclerView, final int n, final int n2) {
    }
    
    public void onItemsChanged(final RecyclerView recyclerView) {
    }
    
    public void onItemsMoved(final RecyclerView recyclerView, final int n, final int n2, final int n3) {
    }
    
    public void onItemsRemoved(final RecyclerView recyclerView, final int n, final int n2) {
    }
    
    public void onItemsUpdated(final RecyclerView recyclerView, final int n, final int n2) {
    }
    
    public void onItemsUpdated(final RecyclerView recyclerView, final int n, final int n2, final Object o) {
        this.onItemsUpdated(recyclerView, n, n2);
    }
    
    public void onLayoutChildren(final RecyclerView$Recycler recyclerView$Recycler, final RecyclerView$State recyclerView$State) {
        Log.e("RecyclerView", "You must override onLayoutChildren(Recycler recycler, State state) ");
    }
    
    public void onLayoutCompleted(final RecyclerView$State recyclerView$State) {
    }
    
    public void onMeasure(final RecyclerView$Recycler recyclerView$Recycler, final RecyclerView$State recyclerView$State, final int n, final int n2) {
        this.mRecyclerView.defaultOnMeasure(n, n2);
    }
    
    public boolean onRequestChildFocus(final RecyclerView recyclerView, final RecyclerView$State recyclerView$State, final View view, final View view2) {
        return this.onRequestChildFocus(recyclerView, view, view2);
    }
    
    @Deprecated
    public boolean onRequestChildFocus(final RecyclerView recyclerView, final View view, final View view2) {
        return this.isSmoothScrolling() || recyclerView.isComputingLayout();
    }
    
    public void onRestoreInstanceState(final Parcelable parcelable) {
    }
    
    public Parcelable onSaveInstanceState() {
        return null;
    }
    
    public void onScrollStateChanged(final int n) {
    }
    
    boolean performAccessibilityAction(final int n, final Bundle bundle) {
        return this.performAccessibilityAction(this.mRecyclerView.mRecycler, this.mRecyclerView.mState, n, bundle);
    }
    
    public boolean performAccessibilityAction(final RecyclerView$Recycler recyclerView$Recycler, final RecyclerView$State recyclerView$State, int n, final Bundle bundle) {
        if (this.mRecyclerView != null) {
            while (true) {
                Label_0202: {
                    int n2 = 0;
                    switch (n) {
                        default: {
                            n = 0;
                            n2 = 0;
                            break;
                        }
                        case 8192: {
                            if (ViewCompat.canScrollVertically((View)this.mRecyclerView, -1)) {
                                n = -(this.getHeight() - this.getPaddingTop() - this.getPaddingBottom());
                            }
                            else {
                                n = 0;
                            }
                            n2 = n;
                            if (ViewCompat.canScrollHorizontally((View)this.mRecyclerView, -1)) {
                                final int n3 = -(this.getWidth() - this.getPaddingLeft() - this.getPaddingRight());
                                n2 = n;
                                n = n3;
                                break;
                            }
                            break Label_0202;
                        }
                        case 4096: {
                            if (ViewCompat.canScrollVertically((View)this.mRecyclerView, 1)) {
                                n = this.getHeight() - this.getPaddingTop() - this.getPaddingBottom();
                            }
                            else {
                                n = 0;
                            }
                            n2 = n;
                            if (ViewCompat.canScrollHorizontally((View)this.mRecyclerView, 1)) {
                                final int width = this.getWidth();
                                final int paddingLeft = this.getPaddingLeft();
                                final int paddingRight = this.getPaddingRight();
                                n2 = n;
                                n = width - paddingLeft - paddingRight;
                                break;
                            }
                            break Label_0202;
                        }
                    }
                    if (n2 != 0 || n != 0) {
                        this.mRecyclerView.scrollBy(n, n2);
                        return true;
                    }
                    return false;
                }
                n = 0;
                continue;
            }
        }
        return false;
    }
    
    public boolean performAccessibilityActionForItem(final RecyclerView$Recycler recyclerView$Recycler, final RecyclerView$State recyclerView$State, final View view, final int n, final Bundle bundle) {
        return false;
    }
    
    boolean performAccessibilityActionForItem(final View view, final int n, final Bundle bundle) {
        return this.performAccessibilityActionForItem(this.mRecyclerView.mRecycler, this.mRecyclerView.mState, view, n, bundle);
    }
    
    public void postOnAnimation(final Runnable runnable) {
        if (this.mRecyclerView != null) {
            ViewCompat.postOnAnimation((View)this.mRecyclerView, runnable);
        }
    }
    
    public void removeAllViews() {
        for (int i = this.getChildCount() - 1; i >= 0; --i) {
            this.mChildHelper.removeViewAt(i);
        }
    }
    
    public void removeAndRecycleAllViews(final RecyclerView$Recycler recyclerView$Recycler) {
        for (int i = this.getChildCount() - 1; i >= 0; --i) {
            if (!RecyclerView.getChildViewHolderInt(this.getChildAt(i)).shouldIgnore()) {
                this.removeAndRecycleViewAt(i, recyclerView$Recycler);
            }
        }
    }
    
    void removeAndRecycleScrapInt(final RecyclerView$Recycler recyclerView$Recycler) {
        final int scrapCount = recyclerView$Recycler.getScrapCount();
        for (int i = scrapCount - 1; i >= 0; --i) {
            final View scrapView = recyclerView$Recycler.getScrapViewAt(i);
            final RecyclerView$ViewHolder childViewHolderInt = RecyclerView.getChildViewHolderInt(scrapView);
            if (!childViewHolderInt.shouldIgnore()) {
                childViewHolderInt.setIsRecyclable(false);
                if (childViewHolderInt.isTmpDetached()) {
                    this.mRecyclerView.removeDetachedView(scrapView, false);
                }
                if (this.mRecyclerView.mItemAnimator != null) {
                    this.mRecyclerView.mItemAnimator.endAnimation(childViewHolderInt);
                }
                childViewHolderInt.setIsRecyclable(true);
                recyclerView$Recycler.quickRecycleScrapView(scrapView);
            }
        }
        recyclerView$Recycler.clearScrap();
        if (scrapCount > 0) {
            this.mRecyclerView.invalidate();
        }
    }
    
    public void removeAndRecycleView(final View view, final RecyclerView$Recycler recyclerView$Recycler) {
        this.removeView(view);
        recyclerView$Recycler.recycleView(view);
    }
    
    public void removeAndRecycleViewAt(final int n, final RecyclerView$Recycler recyclerView$Recycler) {
        final View child = this.getChildAt(n);
        this.removeViewAt(n);
        recyclerView$Recycler.recycleView(child);
    }
    
    public boolean removeCallbacks(final Runnable runnable) {
        return this.mRecyclerView != null && this.mRecyclerView.removeCallbacks(runnable);
    }
    
    public void removeDetachedView(final View view) {
        this.mRecyclerView.removeDetachedView(view, false);
    }
    
    public void removeView(final View view) {
        this.mChildHelper.removeView(view);
    }
    
    public void removeViewAt(final int n) {
        if (this.getChildAt(n) != null) {
            this.mChildHelper.removeViewAt(n);
        }
    }
    
    public boolean requestChildRectangleOnScreen(final RecyclerView recyclerView, final View view, final Rect rect, final boolean b) {
        final int paddingLeft = this.getPaddingLeft();
        final int paddingTop = this.getPaddingTop();
        final int n = this.getWidth() - this.getPaddingRight();
        final int height = this.getHeight();
        final int paddingBottom = this.getPaddingBottom();
        final int n2 = view.getLeft() + rect.left - view.getScrollX();
        final int n3 = view.getTop() + rect.top - view.getScrollY();
        final int n4 = n2 + rect.width();
        final int height2 = rect.height();
        int n5 = Math.min(0, n2 - paddingLeft);
        int n6 = Math.min(0, n3 - paddingTop);
        final int max = Math.max(0, n4 - n);
        final int max2 = Math.max(0, n3 + height2 - (height - paddingBottom));
        if (this.getLayoutDirection() == 1) {
            if (max != 0) {
                n5 = max;
            }
            else {
                n5 = Math.max(n5, n4 - n);
            }
        }
        else if (n5 == 0) {
            n5 = Math.min(n2 - paddingLeft, max);
        }
        if (n6 == 0) {
            n6 = Math.min(n3 - paddingTop, max2);
        }
        if (n5 != 0 || n6 != 0) {
            if (b) {
                recyclerView.scrollBy(n5, n6);
            }
            else {
                recyclerView.smoothScrollBy(n5, n6);
            }
            return true;
        }
        return false;
    }
    
    public void requestLayout() {
        if (this.mRecyclerView != null) {
            this.mRecyclerView.requestLayout();
        }
    }
    
    public void requestSimpleAnimationsInNextLayout() {
        this.mRequestedSimpleAnimations = true;
    }
    
    public int scrollHorizontallyBy(final int n, final RecyclerView$Recycler recyclerView$Recycler, final RecyclerView$State recyclerView$State) {
        return 0;
    }
    
    public void scrollToPosition(final int n) {
    }
    
    public int scrollVerticallyBy(final int n, final RecyclerView$Recycler recyclerView$Recycler, final RecyclerView$State recyclerView$State) {
        return 0;
    }
    
    public void setAutoMeasureEnabled(final boolean mAutoMeasure) {
        this.mAutoMeasure = mAutoMeasure;
    }
    
    void setExactMeasureSpecsFrom(final RecyclerView recyclerView) {
        this.setMeasureSpecs(View$MeasureSpec.makeMeasureSpec(recyclerView.getWidth(), 1073741824), View$MeasureSpec.makeMeasureSpec(recyclerView.getHeight(), 1073741824));
    }
    
    void setMeasureSpecs(final int n, final int n2) {
        this.mWidth = View$MeasureSpec.getSize(n);
        this.mWidthMode = View$MeasureSpec.getMode(n);
        if (this.mWidthMode == 0 && !RecyclerView.ALLOW_SIZE_IN_UNSPECIFIED_SPEC) {
            this.mWidth = 0;
        }
        this.mHeight = View$MeasureSpec.getSize(n2);
        this.mHeightMode = View$MeasureSpec.getMode(n2);
        if (this.mHeightMode == 0 && !RecyclerView.ALLOW_SIZE_IN_UNSPECIFIED_SPEC) {
            this.mHeight = 0;
        }
    }
    
    public void setMeasuredDimension(final int n, final int n2) {
        RecyclerView.access$1000(this.mRecyclerView, n, n2);
    }
    
    public void setMeasuredDimension(final Rect rect, final int n, final int n2) {
        this.setMeasuredDimension(chooseSize(n, rect.width() + this.getPaddingLeft() + this.getPaddingRight(), this.getMinimumWidth()), chooseSize(n2, rect.height() + this.getPaddingTop() + this.getPaddingBottom(), this.getMinimumHeight()));
    }
    
    void setMeasuredDimensionFromChildren(final int n, final int n2) {
        int top = Integer.MAX_VALUE;
        int bottom = Integer.MIN_VALUE;
        final int childCount = this.getChildCount();
        if (childCount == 0) {
            this.mRecyclerView.defaultOnMeasure(n, n2);
            return;
        }
        int i = 0;
        int right = Integer.MIN_VALUE;
        int left = Integer.MAX_VALUE;
        while (i < childCount) {
            final View child = this.getChildAt(i);
            final RecyclerView$LayoutParams recyclerView$LayoutParams = (RecyclerView$LayoutParams)child.getLayoutParams();
            final Rect mTempRect = this.mRecyclerView.mTempRect;
            this.getDecoratedBoundsWithMargins(child, mTempRect);
            if (mTempRect.left < left) {
                left = mTempRect.left;
            }
            if (mTempRect.right > right) {
                right = mTempRect.right;
            }
            if (mTempRect.top < top) {
                top = mTempRect.top;
            }
            if (mTempRect.bottom > bottom) {
                bottom = mTempRect.bottom;
            }
            ++i;
        }
        this.mRecyclerView.mTempRect.set(left, top, right, bottom);
        this.setMeasuredDimension(this.mRecyclerView.mTempRect, n, n2);
    }
    
    public void setMeasurementCacheEnabled(final boolean mMeasurementCacheEnabled) {
        this.mMeasurementCacheEnabled = mMeasurementCacheEnabled;
    }
    
    void setRecyclerView(final RecyclerView mRecyclerView) {
        if (mRecyclerView == null) {
            this.mRecyclerView = null;
            this.mChildHelper = null;
            this.mWidth = 0;
            this.mHeight = 0;
        }
        else {
            this.mRecyclerView = mRecyclerView;
            this.mChildHelper = mRecyclerView.mChildHelper;
            this.mWidth = mRecyclerView.getWidth();
            this.mHeight = mRecyclerView.getHeight();
        }
        this.mWidthMode = 1073741824;
        this.mHeightMode = 1073741824;
    }
    
    boolean shouldMeasureChild(final View view, final int n, final int n2, final RecyclerView$LayoutParams recyclerView$LayoutParams) {
        return view.isLayoutRequested() || !this.mMeasurementCacheEnabled || !isMeasurementUpToDate(view.getWidth(), n, recyclerView$LayoutParams.width) || !isMeasurementUpToDate(view.getHeight(), n2, recyclerView$LayoutParams.height);
    }
    
    boolean shouldMeasureTwice() {
        return false;
    }
    
    boolean shouldReMeasureChild(final View view, final int n, final int n2, final RecyclerView$LayoutParams recyclerView$LayoutParams) {
        return !this.mMeasurementCacheEnabled || !isMeasurementUpToDate(view.getMeasuredWidth(), n, recyclerView$LayoutParams.width) || !isMeasurementUpToDate(view.getMeasuredHeight(), n2, recyclerView$LayoutParams.height);
    }
    
    public void smoothScrollToPosition(final RecyclerView recyclerView, final RecyclerView$State recyclerView$State, final int n) {
        Log.e("RecyclerView", "You must override smoothScrollToPosition to support smooth scrolling");
    }
    
    public void startSmoothScroll(final RecyclerView$SmoothScroller mSmoothScroller) {
        if (this.mSmoothScroller != null && mSmoothScroller != this.mSmoothScroller && this.mSmoothScroller.isRunning()) {
            this.mSmoothScroller.stop();
        }
        (this.mSmoothScroller = mSmoothScroller).start(this.mRecyclerView, this);
    }
    
    public void stopIgnoringView(final View view) {
        final RecyclerView$ViewHolder childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
        childViewHolderInt.stopIgnoring();
        childViewHolderInt.resetInternal();
        childViewHolderInt.addFlags(4);
    }
    
    void stopSmoothScroller() {
        if (this.mSmoothScroller != null) {
            this.mSmoothScroller.stop();
        }
    }
    
    public boolean supportsPredictiveItemAnimations() {
        return false;
    }
}
