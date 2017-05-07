// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.internal.widget;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.view.View$BaseSavedState;
import android.util.SparseArray;
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
    final RecycleBin mRecycler;
    int mSelectionBottomPadding;
    int mSelectionLeftPadding;
    int mSelectionRightPadding;
    int mSelectionTopPadding;
    final Rect mSpinnerPadding;
    private Rect mTouchFrame;
    int mWidthMeasureSpec;
    
    AbsSpinnerCompat(final Context context) {
        super(context);
        this.mSelectionLeftPadding = 0;
        this.mSelectionTopPadding = 0;
        this.mSelectionRightPadding = 0;
        this.mSelectionBottomPadding = 0;
        this.mSpinnerPadding = new Rect();
        this.mRecycler = new RecycleBin();
        this.initAbsSpinner();
    }
    
    AbsSpinnerCompat(final Context context, final AttributeSet set) {
        this(context, set, 0);
    }
    
    AbsSpinnerCompat(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.mSelectionLeftPadding = 0;
        this.mSelectionTopPadding = 0;
        this.mSelectionRightPadding = 0;
        this.mSelectionBottomPadding = 0;
        this.mSpinnerPadding = new Rect();
        this.mRecycler = new RecycleBin();
        this.initAbsSpinner();
    }
    
    static /* synthetic */ void access$000(final AbsSpinnerCompat absSpinnerCompat, final View view, final boolean b) {
        absSpinnerCompat.removeDetachedView(view, b);
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
    public int getCount() {
        return this.mItemCount;
    }
    
    @Override
    public View getSelectedView() {
        if (this.mItemCount > 0 && this.mSelectedPosition >= 0) {
            return this.getChildAt(this.mSelectedPosition - this.mFirstPosition);
        }
        return null;
    }
    
    abstract void layout(final int p0, final boolean p1);
    
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
        final boolean b = false;
        final boolean b2 = false;
        final boolean b3 = true;
        final int selectedItemPosition = this.getSelectedItemPosition();
        boolean b4 = b3;
        int n = b ? 1 : 0;
        int n2 = b2 ? 1 : 0;
        if (selectedItemPosition >= 0) {
            b4 = b3;
            n = (b ? 1 : 0);
            n2 = (b2 ? 1 : 0);
            if (this.mAdapter != null) {
                b4 = b3;
                n = (b ? 1 : 0);
                n2 = (b2 ? 1 : 0);
                if (selectedItemPosition < this.mAdapter.getCount()) {
                    View view;
                    if ((view = this.mRecycler.get(selectedItemPosition)) == null) {
                        view = this.mAdapter.getView(selectedItemPosition, (View)null, (ViewGroup)this);
                    }
                    b4 = b3;
                    n = (b ? 1 : 0);
                    n2 = (b2 ? 1 : 0);
                    if (view != null) {
                        this.mRecycler.put(selectedItemPosition, view);
                        if (view.getLayoutParams() == null) {
                            this.mBlockLayoutRequests = true;
                            view.setLayoutParams(this.generateDefaultLayoutParams());
                            this.mBlockLayoutRequests = false;
                        }
                        this.measureChild(view, mWidthMeasureSpec, mHeightMeasureSpec);
                        n = this.getChildHeight(view) + this.mSpinnerPadding.top + this.mSpinnerPadding.bottom;
                        n2 = this.getChildWidth(view) + this.mSpinnerPadding.left + this.mSpinnerPadding.right;
                        b4 = false;
                    }
                }
            }
        }
        int n3 = n;
        int n4 = n2;
        if (b4) {
            n3 = this.mSpinnerPadding.top + this.mSpinnerPadding.bottom;
            n4 = n2;
            if (mode == 0) {
                n4 = this.mSpinnerPadding.left + this.mSpinnerPadding.right;
                n3 = n3;
            }
        }
        this.setMeasuredDimension(ViewCompat.resolveSizeAndState(Math.max(n4, this.getSuggestedMinimumWidth()), mWidthMeasureSpec, 0), ViewCompat.resolveSizeAndState(Math.max(n3, this.getSuggestedMinimumHeight()), mHeightMeasureSpec, 0));
        this.mHeightMeasureSpec = mHeightMeasureSpec;
        this.mWidthMeasureSpec = mWidthMeasureSpec;
    }
    
    public void onRestoreInstanceState(final Parcelable parcelable) {
        final SavedState savedState = (SavedState)parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        if (savedState.selectedId >= 0L) {
            this.mDataChanged = true;
            this.mNeedSync = true;
            this.mSyncRowId = savedState.selectedId;
            this.mSyncPosition = savedState.position;
            this.mSyncMode = 0;
            this.requestLayout();
        }
    }
    
    public Parcelable onSaveInstanceState() {
        final SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.selectedId = this.getSelectedItemId();
        if (savedState.selectedId >= 0L) {
            savedState.position = this.getSelectedItemPosition();
            return (Parcelable)savedState;
        }
        savedState.position = -1;
        return (Parcelable)savedState;
    }
    
    public int pointToPosition(final int n, final int n2) {
        Rect rect;
        if ((rect = this.mTouchFrame) == null) {
            this.mTouchFrame = new Rect();
            rect = this.mTouchFrame;
        }
        for (int i = this.getChildCount() - 1; i >= 0; --i) {
            final View child = this.getChildAt(i);
            if (child.getVisibility() == 0) {
                child.getHitRect(rect);
                if (rect.contains(n, n2)) {
                    return this.mFirstPosition + i;
                }
            }
        }
        return -1;
    }
    
    void recycleAllViews() {
        final int childCount = this.getChildCount();
        final RecycleBin mRecycler = this.mRecycler;
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
    
    @Override
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
            this.mDataSetObserver = new AdapterDataSetObserver(this);
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
    
    @Override
    public void setSelection(final int nextSelectedPositionInt) {
        this.setNextSelectedPositionInt(nextSelectedPositionInt);
        this.requestLayout();
        this.invalidate();
    }
    
    public void setSelection(final int n, final boolean b) {
        this.setSelectionInt(n, b && this.mFirstPosition <= n && n <= this.mFirstPosition + this.getChildCount() - 1);
    }
    
    void setSelectionInt(final int nextSelectedPositionInt, final boolean b) {
        if (nextSelectedPositionInt != this.mOldSelectedPosition) {
            this.mBlockLayoutRequests = true;
            final int mSelectedPosition = this.mSelectedPosition;
            this.setNextSelectedPositionInt(nextSelectedPositionInt);
            this.layout(nextSelectedPositionInt - mSelectedPosition, b);
            this.mBlockLayoutRequests = false;
        }
    }
    
    class RecycleBin
    {
        private final SparseArray<View> mScrapHeap;
        
        RecycleBin() {
            this.mScrapHeap = (SparseArray<View>)new SparseArray();
        }
        
        void clear() {
            final SparseArray<View> mScrapHeap = this.mScrapHeap;
            for (int size = mScrapHeap.size(), i = 0; i < size; ++i) {
                final View view = (View)mScrapHeap.valueAt(i);
                if (view != null) {
                    AbsSpinnerCompat.access$000(AbsSpinnerCompat.this, view, true);
                }
            }
            mScrapHeap.clear();
        }
        
        View get(final int n) {
            final View view = (View)this.mScrapHeap.get(n);
            if (view != null) {
                this.mScrapHeap.delete(n);
            }
            return view;
        }
        
        public void put(final int n, final View view) {
            this.mScrapHeap.put(n, (Object)view);
        }
    }
    
    static class SavedState extends View$BaseSavedState
    {
        public static final Parcelable$Creator<SavedState> CREATOR;
        int position;
        long selectedId;
        
        static {
            CREATOR = (Parcelable$Creator)new Parcelable$Creator<SavedState>() {
                public SavedState createFromParcel(final Parcel parcel) {
                    return new SavedState(parcel);
                }
                
                public SavedState[] newArray(final int n) {
                    return new SavedState[n];
                }
            };
        }
        
        SavedState(final Parcel parcel) {
            super(parcel);
            this.selectedId = parcel.readLong();
            this.position = parcel.readInt();
        }
        
        SavedState(final Parcelable parcelable) {
            super(parcelable);
        }
        
        public String toString() {
            return "AbsSpinner.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " selectedId=" + this.selectedId + " position=" + this.position + "}";
        }
        
        public void writeToParcel(final Parcel parcel, final int n) {
            super.writeToParcel(parcel, n);
            parcel.writeLong(this.selectedId);
            parcel.writeInt(this.position);
        }
    }
}
