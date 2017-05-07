// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.internal.widget;

import android.view.View$OnClickListener;
import android.view.ViewDebug$CapturedViewProperty;
import android.os.SystemClock;
import android.util.SparseArray;
import android.view.accessibility.AccessibilityEvent;
import android.view.ViewGroup$LayoutParams;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.content.Context;
import android.view.ViewDebug$ExportedProperty;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;

public abstract class AdapterViewCompat<T extends Adapter> extends ViewGroup
{
    boolean mBlockLayoutRequests;
    boolean mDataChanged;
    private boolean mDesiredFocusableInTouchModeState;
    private boolean mDesiredFocusableState;
    private View mEmptyView;
    @ViewDebug$ExportedProperty(category = "scrolling")
    int mFirstPosition;
    boolean mInLayout;
    @ViewDebug$ExportedProperty(category = "list")
    int mItemCount;
    private int mLayoutHeight;
    boolean mNeedSync;
    @ViewDebug$ExportedProperty(category = "list")
    int mNextSelectedPosition;
    long mNextSelectedRowId;
    int mOldItemCount;
    int mOldSelectedPosition;
    long mOldSelectedRowId;
    AdapterViewCompat$OnItemClickListener mOnItemClickListener;
    AdapterViewCompat$OnItemLongClickListener mOnItemLongClickListener;
    AdapterViewCompat$OnItemSelectedListener mOnItemSelectedListener;
    @ViewDebug$ExportedProperty(category = "list")
    int mSelectedPosition;
    long mSelectedRowId;
    private AdapterViewCompat$SelectionNotifier mSelectionNotifier;
    int mSpecificTop;
    long mSyncHeight;
    int mSyncMode;
    int mSyncPosition;
    long mSyncRowId;
    
    AdapterViewCompat(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.mFirstPosition = 0;
        this.mSyncRowId = Long.MIN_VALUE;
        this.mNeedSync = false;
        this.mInLayout = false;
        this.mNextSelectedPosition = -1;
        this.mNextSelectedRowId = Long.MIN_VALUE;
        this.mSelectedPosition = -1;
        this.mSelectedRowId = Long.MIN_VALUE;
        this.mOldSelectedPosition = -1;
        this.mOldSelectedRowId = Long.MIN_VALUE;
        this.mBlockLayoutRequests = false;
    }
    
    private void fireOnSelected() {
        if (this.mOnItemSelectedListener == null) {
            return;
        }
        final int selectedItemPosition = this.getSelectedItemPosition();
        if (selectedItemPosition >= 0) {
            this.mOnItemSelectedListener.onItemSelected(this, this.getSelectedView(), selectedItemPosition, this.getAdapter().getItemId(selectedItemPosition));
            return;
        }
        this.mOnItemSelectedListener.onNothingSelected(this);
    }
    
    private void updateEmptyStatus(boolean b) {
        if (this.isInFilterMode()) {
            b = false;
        }
        if (b) {
            if (this.mEmptyView != null) {
                this.mEmptyView.setVisibility(0);
                this.setVisibility(8);
            }
            else {
                this.setVisibility(0);
            }
            if (this.mDataChanged) {
                this.onLayout(false, this.getLeft(), this.getTop(), this.getRight(), this.getBottom());
            }
            return;
        }
        if (this.mEmptyView != null) {
            this.mEmptyView.setVisibility(8);
        }
        this.setVisibility(0);
    }
    
    public void addView(final View view) {
        throw new UnsupportedOperationException("addView(View) is not supported in AdapterView");
    }
    
    public void addView(final View view, final int n) {
        throw new UnsupportedOperationException("addView(View, int) is not supported in AdapterView");
    }
    
    public void addView(final View view, final int n, final ViewGroup$LayoutParams viewGroup$LayoutParams) {
        throw new UnsupportedOperationException("addView(View, int, LayoutParams) is not supported in AdapterView");
    }
    
    public void addView(final View view, final ViewGroup$LayoutParams viewGroup$LayoutParams) {
        throw new UnsupportedOperationException("addView(View, LayoutParams) is not supported in AdapterView");
    }
    
    protected boolean canAnimate() {
        return super.canAnimate() && this.mItemCount > 0;
    }
    
    void checkFocus() {
        final boolean b = false;
        final Adapter adapter = this.getAdapter();
        boolean b2;
        if (adapter == null || adapter.getCount() == 0) {
            b2 = true;
        }
        else {
            b2 = false;
        }
        boolean b3;
        if (!b2 || this.isInFilterMode()) {
            b3 = true;
        }
        else {
            b3 = false;
        }
        super.setFocusableInTouchMode(b3 && this.mDesiredFocusableInTouchModeState);
        super.setFocusable(b3 && this.mDesiredFocusableState);
        if (this.mEmptyView != null) {
            boolean b4 = false;
            Label_0100: {
                if (adapter != null) {
                    b4 = b;
                    if (!adapter.isEmpty()) {
                        break Label_0100;
                    }
                }
                b4 = true;
            }
            this.updateEmptyStatus(b4);
        }
    }
    
    void checkSelectionChanged() {
        if (this.mSelectedPosition != this.mOldSelectedPosition || this.mSelectedRowId != this.mOldSelectedRowId) {
            this.selectionChanged();
            this.mOldSelectedPosition = this.mSelectedPosition;
            this.mOldSelectedRowId = this.mSelectedRowId;
        }
    }
    
    public boolean dispatchPopulateAccessibilityEvent(final AccessibilityEvent accessibilityEvent) {
        final View selectedView = this.getSelectedView();
        return selectedView != null && selectedView.getVisibility() == 0 && selectedView.dispatchPopulateAccessibilityEvent(accessibilityEvent);
    }
    
    protected void dispatchRestoreInstanceState(final SparseArray<Parcelable> sparseArray) {
        this.dispatchThawSelfOnly((SparseArray)sparseArray);
    }
    
    protected void dispatchSaveInstanceState(final SparseArray<Parcelable> sparseArray) {
        this.dispatchFreezeSelfOnly((SparseArray)sparseArray);
    }
    
    int findSyncPosition() {
        final int mItemCount = this.mItemCount;
        int n;
        if (mItemCount == 0) {
            n = -1;
        }
        else {
            final long mSyncRowId = this.mSyncRowId;
            final int mSyncPosition = this.mSyncPosition;
            if (mSyncRowId == Long.MIN_VALUE) {
                return -1;
            }
            final int min = Math.min(mItemCount - 1, Math.max(0, mSyncPosition));
            final long uptimeMillis = SystemClock.uptimeMillis();
            int n2 = 0;
            final Adapter adapter = this.getAdapter();
            if (adapter == null) {
                return -1;
            }
            int n3 = min;
            final int n4 = min;
            int n5 = min;
            int n6 = n4;
            while (SystemClock.uptimeMillis() <= uptimeMillis + 100L) {
                n = n6;
                if (adapter.getItemId(n6) == mSyncRowId) {
                    return n;
                }
                boolean b;
                if (n5 == mItemCount - 1) {
                    b = true;
                }
                else {
                    b = false;
                }
                boolean b2;
                if (n3 == 0) {
                    b2 = true;
                }
                else {
                    b2 = false;
                }
                if (b && b2) {
                    break;
                }
                if (b2 || (n2 != 0 && !b)) {
                    ++n5;
                    n2 = 0;
                    n6 = n5;
                }
                else {
                    if (!b && (n2 != 0 || b2)) {
                        continue;
                    }
                    --n3;
                    n2 = 1;
                    n6 = n3;
                }
            }
            return -1;
        }
        return n;
    }
    
    public abstract T getAdapter();
    
    @ViewDebug$CapturedViewProperty
    public int getCount() {
        return this.mItemCount;
    }
    
    public View getEmptyView() {
        return this.mEmptyView;
    }
    
    public int getFirstVisiblePosition() {
        return this.mFirstPosition;
    }
    
    public long getItemIdAtPosition(final int n) {
        final Adapter adapter = this.getAdapter();
        if (adapter == null || n < 0) {
            return Long.MIN_VALUE;
        }
        return adapter.getItemId(n);
    }
    
    public int getLastVisiblePosition() {
        return this.mFirstPosition + this.getChildCount() - 1;
    }
    
    public final AdapterViewCompat$OnItemClickListener getOnItemClickListener() {
        return this.mOnItemClickListener;
    }
    
    public final AdapterViewCompat$OnItemLongClickListener getOnItemLongClickListener() {
        return this.mOnItemLongClickListener;
    }
    
    public final AdapterViewCompat$OnItemSelectedListener getOnItemSelectedListener() {
        return this.mOnItemSelectedListener;
    }
    
    public Object getSelectedItem() {
        final Adapter adapter = this.getAdapter();
        final int selectedItemPosition = this.getSelectedItemPosition();
        if (adapter != null && adapter.getCount() > 0 && selectedItemPosition >= 0) {
            return adapter.getItem(selectedItemPosition);
        }
        return null;
    }
    
    @ViewDebug$CapturedViewProperty
    public long getSelectedItemId() {
        return this.mNextSelectedRowId;
    }
    
    @ViewDebug$CapturedViewProperty
    public int getSelectedItemPosition() {
        return this.mNextSelectedPosition;
    }
    
    public abstract View getSelectedView();
    
    void handleDataChanged() {
        final int mItemCount = this.mItemCount;
        int n = 0;
        Label_0112: {
            if (mItemCount > 0) {
                while (true) {
                    Label_0156: {
                        if (!this.mNeedSync) {
                            break Label_0156;
                        }
                        this.mNeedSync = false;
                        final int syncPosition = this.findSyncPosition();
                        if (syncPosition < 0 || this.lookForSelectablePosition(syncPosition, true) != syncPosition) {
                            break Label_0156;
                        }
                        this.setNextSelectedPositionInt(syncPosition);
                        n = 1;
                        if (n == 0) {
                            int selectedItemPosition;
                            if ((selectedItemPosition = this.getSelectedItemPosition()) >= mItemCount) {
                                selectedItemPosition = mItemCount - 1;
                            }
                            int n2;
                            if ((n2 = selectedItemPosition) < 0) {
                                n2 = 0;
                            }
                            int nextSelectedPositionInt = this.lookForSelectablePosition(n2, true);
                            if (nextSelectedPositionInt < 0) {
                                nextSelectedPositionInt = this.lookForSelectablePosition(n2, false);
                            }
                            if (nextSelectedPositionInt >= 0) {
                                this.setNextSelectedPositionInt(nextSelectedPositionInt);
                                this.checkSelectionChanged();
                                n = 1;
                            }
                        }
                        break Label_0112;
                    }
                    n = 0;
                    continue;
                }
            }
            n = 0;
        }
        if (n == 0) {
            this.mSelectedPosition = -1;
            this.mSelectedRowId = Long.MIN_VALUE;
            this.mNextSelectedPosition = -1;
            this.mNextSelectedRowId = Long.MIN_VALUE;
            this.mNeedSync = false;
            this.checkSelectionChanged();
        }
    }
    
    boolean isInFilterMode() {
        return false;
    }
    
    int lookForSelectablePosition(final int n, final boolean b) {
        return n;
    }
    
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.removeCallbacks((Runnable)this.mSelectionNotifier);
    }
    
    protected void onLayout(final boolean b, final int n, final int n2, final int n3, final int n4) {
        this.mLayoutHeight = this.getHeight();
    }
    
    public boolean performItemClick(final View view, final int n, final long n2) {
        boolean b = false;
        if (this.mOnItemClickListener != null) {
            this.playSoundEffect(0);
            if (view != null) {
                view.sendAccessibilityEvent(1);
            }
            this.mOnItemClickListener.onItemClick(this, view, n, n2);
            b = true;
        }
        return b;
    }
    
    void rememberSyncState() {
        if (this.getChildCount() > 0) {
            this.mNeedSync = true;
            this.mSyncHeight = this.mLayoutHeight;
            if (this.mSelectedPosition < 0) {
                final View child = this.getChildAt(0);
                final Adapter adapter = this.getAdapter();
                if (this.mFirstPosition >= 0 && this.mFirstPosition < adapter.getCount()) {
                    this.mSyncRowId = adapter.getItemId(this.mFirstPosition);
                }
                else {
                    this.mSyncRowId = -1L;
                }
                this.mSyncPosition = this.mFirstPosition;
                if (child != null) {
                    this.mSpecificTop = child.getTop();
                }
                this.mSyncMode = 1;
                return;
            }
            final View child2 = this.getChildAt(this.mSelectedPosition - this.mFirstPosition);
            this.mSyncRowId = this.mNextSelectedRowId;
            this.mSyncPosition = this.mNextSelectedPosition;
            if (child2 != null) {
                this.mSpecificTop = child2.getTop();
            }
            this.mSyncMode = 0;
        }
    }
    
    public void removeAllViews() {
        throw new UnsupportedOperationException("removeAllViews() is not supported in AdapterView");
    }
    
    public void removeView(final View view) {
        throw new UnsupportedOperationException("removeView(View) is not supported in AdapterView");
    }
    
    public void removeViewAt(final int n) {
        throw new UnsupportedOperationException("removeViewAt(int) is not supported in AdapterView");
    }
    
    void selectionChanged() {
        if (this.mOnItemSelectedListener != null) {
            if (this.mInLayout || this.mBlockLayoutRequests) {
                if (this.mSelectionNotifier == null) {
                    this.mSelectionNotifier = new AdapterViewCompat$SelectionNotifier(this, null);
                }
                this.post((Runnable)this.mSelectionNotifier);
            }
            else {
                this.fireOnSelected();
            }
        }
        if (this.mSelectedPosition != -1 && this.isShown() && !this.isInTouchMode()) {
            this.sendAccessibilityEvent(4);
        }
    }
    
    public abstract void setAdapter(final T p0);
    
    public void setEmptyView(final View mEmptyView) {
        this.mEmptyView = mEmptyView;
        final Adapter adapter = this.getAdapter();
        this.updateEmptyStatus(adapter == null || adapter.isEmpty());
    }
    
    public void setFocusable(final boolean mDesiredFocusableState) {
        final boolean b = true;
        final Adapter adapter = this.getAdapter();
        int n;
        if (adapter == null || adapter.getCount() == 0) {
            n = 1;
        }
        else {
            n = 0;
        }
        if (!(this.mDesiredFocusableState = mDesiredFocusableState)) {
            this.mDesiredFocusableInTouchModeState = false;
        }
        while (true) {
            Label_0069: {
                if (!mDesiredFocusableState) {
                    break Label_0069;
                }
                boolean focusable = b;
                if (n != 0) {
                    if (!this.isInFilterMode()) {
                        break Label_0069;
                    }
                    focusable = b;
                }
                super.setFocusable(focusable);
                return;
            }
            boolean focusable = false;
            continue;
        }
    }
    
    public void setFocusableInTouchMode(final boolean mDesiredFocusableInTouchModeState) {
        final boolean b = true;
        final Adapter adapter = this.getAdapter();
        int n;
        if (adapter == null || adapter.getCount() == 0) {
            n = 1;
        }
        else {
            n = 0;
        }
        this.mDesiredFocusableInTouchModeState = mDesiredFocusableInTouchModeState;
        if (mDesiredFocusableInTouchModeState) {
            this.mDesiredFocusableState = true;
        }
        while (true) {
            Label_0069: {
                if (!mDesiredFocusableInTouchModeState) {
                    break Label_0069;
                }
                boolean focusableInTouchMode = b;
                if (n != 0) {
                    if (!this.isInFilterMode()) {
                        break Label_0069;
                    }
                    focusableInTouchMode = b;
                }
                super.setFocusableInTouchMode(focusableInTouchMode);
                return;
            }
            boolean focusableInTouchMode = false;
            continue;
        }
    }
    
    void setNextSelectedPositionInt(final int n) {
        this.mNextSelectedPosition = n;
        this.mNextSelectedRowId = this.getItemIdAtPosition(n);
        if (this.mNeedSync && this.mSyncMode == 0 && n >= 0) {
            this.mSyncPosition = n;
            this.mSyncRowId = this.mNextSelectedRowId;
        }
    }
    
    public void setOnClickListener(final View$OnClickListener view$OnClickListener) {
        throw new RuntimeException("Don't call setOnClickListener for an AdapterView. You probably want setOnItemClickListener instead");
    }
    
    public void setOnItemClickListener(final AdapterViewCompat$OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }
    
    public void setOnItemLongClickListener(final AdapterViewCompat$OnItemLongClickListener mOnItemLongClickListener) {
        if (!this.isLongClickable()) {
            this.setLongClickable(true);
        }
        this.mOnItemLongClickListener = mOnItemLongClickListener;
    }
    
    public void setOnItemSelectedListener(final AdapterViewCompat$OnItemSelectedListener mOnItemSelectedListener) {
        this.mOnItemSelectedListener = mOnItemSelectedListener;
    }
    
    void setSelectedPositionInt(final int mSelectedPosition) {
        this.mSelectedPosition = mSelectedPosition;
        this.mSelectedRowId = this.getItemIdAtPosition(mSelectedPosition);
    }
    
    public abstract void setSelection(final int p0);
}
