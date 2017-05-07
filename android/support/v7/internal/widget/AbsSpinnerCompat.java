// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.internal.widget;

import android.os.Parcelable;
import android.support.v4.view.ViewCompat;
import android.view.ViewGroup;
import android.view.View$MeasureSpec;
import android.widget.Adapter;
import android.view.ViewGroup$LayoutParams;
import android.view.View;
import android.util.AttributeSet;
import android.content.Context;
import android.graphics.Rect;
import android.database.DataSetObserver;
import android.widget.SpinnerAdapter;

abstract class AbsSpinnerCompat extends AdapterViewCompat<SpinnerAdapter>
{
    SpinnerAdapter mAdapter;
    private DataSetObserver mDataSetObserver;
    int mHeightMeasureSpec;
    final AbsSpinnerCompat$RecycleBin mRecycler;
    int mSelectionBottomPadding;
    int mSelectionLeftPadding;
    int mSelectionRightPadding;
    int mSelectionTopPadding;
    final Rect mSpinnerPadding;
    int mWidthMeasureSpec;
    
    AbsSpinnerCompat(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.mSelectionLeftPadding = 0;
        this.mSelectionTopPadding = 0;
        this.mSelectionRightPadding = 0;
        this.mSelectionBottomPadding = 0;
        this.mSpinnerPadding = new Rect();
        this.mRecycler = new AbsSpinnerCompat$RecycleBin(this);
        this.initAbsSpinner();
    }
    
    private void initAbsSpinner() {
        this.setFocusable(true);
        this.setWillNotDraw(false);
    }
    
    protected ViewGroup$LayoutParams generateDefaultLayoutParams() {
        return new ViewGroup$LayoutParams(-1, -2);
    }
    
    @Override
    public SpinnerAdapter getAdapter() {
        return this.mAdapter;
    }
    
    int getChildHeight(final View view) {
        return view.getMeasuredHeight();
    }
    
    int getChildWidth(final View view) {
        return view.getMeasuredWidth();
    }
    
    @Override
    public View getSelectedView() {
        if (this.mItemCount > 0 && this.mSelectedPosition >= 0) {
            return this.getChildAt(this.mSelectedPosition - this.mFirstPosition);
        }
        return null;
    }
    
    protected void onMeasure(final int mWidthMeasureSpec, final int mHeightMeasureSpec) {
        final int mode = View$MeasureSpec.getMode(mWidthMeasureSpec);
        int left = this.getPaddingLeft();
        final int paddingTop = this.getPaddingTop();
        final int paddingRight = this.getPaddingRight();
        final int paddingBottom = this.getPaddingBottom();
        final Rect mSpinnerPadding = this.mSpinnerPadding;
        if (left <= this.mSelectionLeftPadding) {
            left = this.mSelectionLeftPadding;
        }
        mSpinnerPadding.left = left;
        final Rect mSpinnerPadding2 = this.mSpinnerPadding;
        int mSelectionTopPadding;
        if (paddingTop > this.mSelectionTopPadding) {
            mSelectionTopPadding = paddingTop;
        }
        else {
            mSelectionTopPadding = this.mSelectionTopPadding;
        }
        mSpinnerPadding2.top = mSelectionTopPadding;
        final Rect mSpinnerPadding3 = this.mSpinnerPadding;
        int mSelectionRightPadding;
        if (paddingRight > this.mSelectionRightPadding) {
            mSelectionRightPadding = paddingRight;
        }
        else {
            mSelectionRightPadding = this.mSelectionRightPadding;
        }
        mSpinnerPadding3.right = mSelectionRightPadding;
        final Rect mSpinnerPadding4 = this.mSpinnerPadding;
        int mSelectionBottomPadding;
        if (paddingBottom > this.mSelectionBottomPadding) {
            mSelectionBottomPadding = paddingBottom;
        }
        else {
            mSelectionBottomPadding = this.mSelectionBottomPadding;
        }
        mSpinnerPadding4.bottom = mSelectionBottomPadding;
        if (this.mDataChanged) {
            this.handleDataChanged();
        }
        final int selectedItemPosition = this.getSelectedItemPosition();
        while (true) {
            Label_0435: {
                if (selectedItemPosition < 0 || this.mAdapter == null || selectedItemPosition >= this.mAdapter.getCount()) {
                    break Label_0435;
                }
                View view;
                if ((view = this.mRecycler.get(selectedItemPosition)) == null) {
                    view = this.mAdapter.getView(selectedItemPosition, (View)null, (ViewGroup)this);
                }
                if (view == null) {
                    break Label_0435;
                }
                this.mRecycler.put(selectedItemPosition, view);
                if (view.getLayoutParams() == null) {
                    this.mBlockLayoutRequests = true;
                    view.setLayoutParams(this.generateDefaultLayoutParams());
                    this.mBlockLayoutRequests = false;
                }
                this.measureChild(view, mWidthMeasureSpec, mHeightMeasureSpec);
                int n = this.getChildHeight(view) + this.mSpinnerPadding.top + this.mSpinnerPadding.bottom;
                final int n2 = this.getChildWidth(view) + this.mSpinnerPadding.left + this.mSpinnerPadding.right;
                final int n3 = 0;
                int n4 = n2;
                if (n3 != 0) {
                    final int n5 = this.mSpinnerPadding.top + this.mSpinnerPadding.bottom;
                    n4 = n2;
                    n = n5;
                    if (mode == 0) {
                        n4 = this.mSpinnerPadding.left + this.mSpinnerPadding.right;
                        n = n5;
                    }
                }
                this.setMeasuredDimension(ViewCompat.resolveSizeAndState(Math.max(n4, this.getSuggestedMinimumWidth()), mWidthMeasureSpec, 0), ViewCompat.resolveSizeAndState(Math.max(n, this.getSuggestedMinimumHeight()), mHeightMeasureSpec, 0));
                this.mHeightMeasureSpec = mHeightMeasureSpec;
                this.mWidthMeasureSpec = mWidthMeasureSpec;
                return;
            }
            final int n3 = 1;
            final int n2 = 0;
            int n = 0;
            continue;
        }
    }
    
    public void onRestoreInstanceState(final Parcelable parcelable) {
        final AbsSpinnerCompat$SavedState absSpinnerCompat$SavedState = (AbsSpinnerCompat$SavedState)parcelable;
        super.onRestoreInstanceState(absSpinnerCompat$SavedState.getSuperState());
        if (absSpinnerCompat$SavedState.selectedId >= 0L) {
            this.mDataChanged = true;
            this.mNeedSync = true;
            this.mSyncRowId = absSpinnerCompat$SavedState.selectedId;
            this.mSyncPosition = absSpinnerCompat$SavedState.position;
            this.mSyncMode = 0;
            this.requestLayout();
        }
    }
    
    public Parcelable onSaveInstanceState() {
        final AbsSpinnerCompat$SavedState absSpinnerCompat$SavedState = new AbsSpinnerCompat$SavedState(super.onSaveInstanceState());
        absSpinnerCompat$SavedState.selectedId = this.getSelectedItemId();
        if (absSpinnerCompat$SavedState.selectedId >= 0L) {
            absSpinnerCompat$SavedState.position = this.getSelectedItemPosition();
            return (Parcelable)absSpinnerCompat$SavedState;
        }
        absSpinnerCompat$SavedState.position = -1;
        return (Parcelable)absSpinnerCompat$SavedState;
    }
    
    void recycleAllViews() {
        final int childCount = this.getChildCount();
        final AbsSpinnerCompat$RecycleBin mRecycler = this.mRecycler;
        final int mFirstPosition = this.mFirstPosition;
        for (int i = 0; i < childCount; ++i) {
            mRecycler.put(mFirstPosition + i, this.getChildAt(i));
        }
    }
    
    public void requestLayout() {
        if (!this.mBlockLayoutRequests) {
            super.requestLayout();
        }
    }
    
    void resetList() {
        this.mDataChanged = false;
        this.mNeedSync = false;
        this.removeAllViewsInLayout();
        this.mOldSelectedPosition = -1;
        this.mOldSelectedRowId = Long.MIN_VALUE;
        this.setSelectedPositionInt(-1);
        this.setNextSelectedPositionInt(-1);
        this.invalidate();
    }
    
    public void setAdapter(final SpinnerAdapter mAdapter) {
        int n = -1;
        if (this.mAdapter != null) {
            this.mAdapter.unregisterDataSetObserver(this.mDataSetObserver);
            this.resetList();
        }
        this.mAdapter = mAdapter;
        this.mOldSelectedPosition = -1;
        this.mOldSelectedRowId = Long.MIN_VALUE;
        if (this.mAdapter != null) {
            this.mOldItemCount = this.mItemCount;
            this.mItemCount = this.mAdapter.getCount();
            this.checkFocus();
            this.mDataSetObserver = new AdapterViewCompat$AdapterDataSetObserver(this);
            this.mAdapter.registerDataSetObserver(this.mDataSetObserver);
            if (this.mItemCount > 0) {
                n = 0;
            }
            this.setSelectedPositionInt(n);
            this.setNextSelectedPositionInt(n);
            if (this.mItemCount == 0) {
                this.checkSelectionChanged();
            }
        }
        else {
            this.checkFocus();
            this.resetList();
            this.checkSelectionChanged();
        }
        this.requestLayout();
    }
    
    public void setSelection(final int nextSelectedPositionInt) {
        this.setNextSelectedPositionInt(nextSelectedPositionInt);
        this.requestLayout();
        this.invalidate();
    }
}
