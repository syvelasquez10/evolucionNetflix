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
import android.util.AttributeSet;
import android.content.Context;
import android.view.ViewDebug$ExportedProperty;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.os.Parcelable;
import android.database.DataSetObserver;

class AdapterViewCompat$AdapterDataSetObserver extends DataSetObserver
{
    private Parcelable mInstanceState;
    final /* synthetic */ AdapterViewCompat this$0;
    
    AdapterViewCompat$AdapterDataSetObserver(final AdapterViewCompat this$0) {
        this.this$0 = this$0;
        this.mInstanceState = null;
    }
    
    public void clearSavedState() {
        this.mInstanceState = null;
    }
    
    public void onChanged() {
        this.this$0.mDataChanged = true;
        this.this$0.mOldItemCount = this.this$0.mItemCount;
        this.this$0.mItemCount = this.this$0.getAdapter().getCount();
        if (this.this$0.getAdapter().hasStableIds() && this.mInstanceState != null && this.this$0.mOldItemCount == 0 && this.this$0.mItemCount > 0) {
            AdapterViewCompat.access$000(this.this$0, this.mInstanceState);
            this.mInstanceState = null;
        }
        else {
            this.this$0.rememberSyncState();
        }
        this.this$0.checkFocus();
        this.this$0.requestLayout();
    }
    
    public void onInvalidated() {
        this.this$0.mDataChanged = true;
        if (this.this$0.getAdapter().hasStableIds()) {
            this.mInstanceState = AdapterViewCompat.access$100(this.this$0);
        }
        this.this$0.mOldItemCount = this.this$0.mItemCount;
        this.this$0.mItemCount = 0;
        this.this$0.mSelectedPosition = -1;
        this.this$0.mSelectedRowId = Long.MIN_VALUE;
        this.this$0.mNextSelectedPosition = -1;
        this.this$0.mNextSelectedRowId = Long.MIN_VALUE;
        this.this$0.mNeedSync = false;
        this.this$0.checkFocus();
        this.this$0.requestLayout();
    }
}
