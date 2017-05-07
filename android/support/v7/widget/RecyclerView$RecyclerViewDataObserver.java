// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.util.SparseArray;
import android.support.v4.view.ViewConfigurationCompat;
import android.support.v4.view.AccessibilityDelegateCompat;
import android.support.v4.view.VelocityTrackerCompat;
import android.os.Parcelable;
import android.view.ViewParent;
import android.view.FocusFinder;
import android.graphics.Canvas;
import android.support.v4.util.SimpleArrayMap;
import android.util.Log;
import android.support.v4.util.ArrayMap;
import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;
import android.view.ViewGroup$LayoutParams;
import android.view.ViewConfiguration;
import android.util.AttributeSet;
import android.content.Context;
import android.os.Build$VERSION;
import android.view.VelocityTracker;
import android.graphics.Rect;
import java.util.ArrayList;
import java.util.List;
import android.support.v4.widget.EdgeEffectCompat;
import android.view.accessibility.AccessibilityManager;
import android.view.animation.Interpolator;
import android.view.ViewGroup;
import android.view.View;
import android.support.v4.view.ViewCompat;

class RecyclerView$RecyclerViewDataObserver extends RecyclerView$AdapterDataObserver
{
    final /* synthetic */ RecyclerView this$0;
    
    private RecyclerView$RecyclerViewDataObserver(final RecyclerView this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onChanged() {
        this.this$0.assertNotInLayoutOrScroll(null);
        if (this.this$0.mAdapter.hasStableIds()) {
            this.this$0.mState.mStructureChanged = true;
            this.this$0.mDataSetHasChangedAfterLayout = true;
        }
        else {
            this.this$0.mState.mStructureChanged = true;
            this.this$0.mDataSetHasChangedAfterLayout = true;
        }
        if (!this.this$0.mAdapterHelper.hasPendingUpdates()) {
            this.this$0.requestLayout();
        }
    }
    
    @Override
    public void onItemRangeChanged(final int n, final int n2) {
        this.this$0.assertNotInLayoutOrScroll(null);
        if (this.this$0.mAdapterHelper.onItemRangeChanged(n, n2)) {
            this.triggerUpdateProcessor();
        }
    }
    
    @Override
    public void onItemRangeInserted(final int n, final int n2) {
        this.this$0.assertNotInLayoutOrScroll(null);
        if (this.this$0.mAdapterHelper.onItemRangeInserted(n, n2)) {
            this.triggerUpdateProcessor();
        }
    }
    
    @Override
    public void onItemRangeMoved(final int n, final int n2, final int n3) {
        this.this$0.assertNotInLayoutOrScroll(null);
        if (this.this$0.mAdapterHelper.onItemRangeMoved(n, n2, n3)) {
            this.triggerUpdateProcessor();
        }
    }
    
    @Override
    public void onItemRangeRemoved(final int n, final int n2) {
        this.this$0.assertNotInLayoutOrScroll(null);
        if (this.this$0.mAdapterHelper.onItemRangeRemoved(n, n2)) {
            this.triggerUpdateProcessor();
        }
    }
    
    void triggerUpdateProcessor() {
        if (this.this$0.mPostUpdatesOnAnimation && this.this$0.mHasFixedSize && this.this$0.mIsAttached) {
            ViewCompat.postOnAnimation((View)this.this$0, this.this$0.mUpdateChildViewsRunnable);
            return;
        }
        this.this$0.mAdapterUpdateDuringMeasure = true;
        this.this$0.requestLayout();
    }
}
