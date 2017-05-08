// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.support.v4.view.ViewConfigurationCompat;
import android.os.SystemClock;
import android.support.v4.view.AccessibilityDelegateCompat;
import android.support.v4.view.VelocityTrackerCompat;
import android.support.v4.os.TraceCompat;
import android.view.ViewParent;
import android.view.FocusFinder;
import android.graphics.Canvas;
import android.os.Parcelable;
import android.util.SparseArray;
import android.support.v4.util.SimpleArrayMap;
import android.util.Log;
import android.support.v4.util.ArrayMap;
import android.support.v4.view.MotionEventCompat;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.support.v4.view.accessibility.AccessibilityEventCompat;
import android.view.accessibility.AccessibilityEvent;
import android.view.View$MeasureSpec;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import android.view.ViewGroup$LayoutParams;
import android.content.res.TypedArray;
import android.support.v7.recyclerview.R$styleable;
import android.view.View;
import android.support.v4.view.ViewCompat;
import android.view.ViewConfiguration;
import android.util.AttributeSet;
import android.content.Context;
import android.os.Build$VERSION;
import android.view.VelocityTracker;
import android.graphics.Rect;
import android.support.v4.view.NestedScrollingChildHelper;
import java.util.List;
import java.util.ArrayList;
import android.support.v4.widget.EdgeEffectCompat;
import android.view.accessibility.AccessibilityManager;
import android.view.animation.Interpolator;
import android.support.v4.view.ScrollingView;
import android.support.v4.view.NestedScrollingChild;
import android.view.ViewGroup;

class RecyclerView$5 implements AdapterHelper$Callback
{
    final /* synthetic */ RecyclerView this$0;
    
    RecyclerView$5(final RecyclerView this$0) {
        this.this$0 = this$0;
    }
    
    void dispatchUpdate(final AdapterHelper$UpdateOp adapterHelper$UpdateOp) {
        switch (adapterHelper$UpdateOp.cmd) {
            default: {}
            case 0: {
                this.this$0.mLayout.onItemsAdded(this.this$0, adapterHelper$UpdateOp.positionStart, adapterHelper$UpdateOp.itemCount);
            }
            case 1: {
                this.this$0.mLayout.onItemsRemoved(this.this$0, adapterHelper$UpdateOp.positionStart, adapterHelper$UpdateOp.itemCount);
            }
            case 2: {
                this.this$0.mLayout.onItemsUpdated(this.this$0, adapterHelper$UpdateOp.positionStart, adapterHelper$UpdateOp.itemCount, adapterHelper$UpdateOp.payload);
            }
            case 3: {
                this.this$0.mLayout.onItemsMoved(this.this$0, adapterHelper$UpdateOp.positionStart, adapterHelper$UpdateOp.itemCount, 1);
            }
        }
    }
    
    @Override
    public RecyclerView$ViewHolder findViewHolder(final int n) {
        final RecyclerView$ViewHolder viewHolderForPosition = this.this$0.findViewHolderForPosition(n, true);
        if (viewHolderForPosition != null && !this.this$0.mChildHelper.isHidden(viewHolderForPosition.itemView)) {
            return viewHolderForPosition;
        }
        return null;
    }
    
    @Override
    public void markViewHoldersUpdated(final int n, final int n2, final Object o) {
        this.this$0.viewRangeUpdate(n, n2, o);
        this.this$0.mItemsChanged = true;
    }
    
    @Override
    public void offsetPositionsForAdd(final int n, final int n2) {
        this.this$0.offsetPositionRecordsForInsert(n, n2);
        this.this$0.mItemsAddedOrRemoved = true;
    }
    
    @Override
    public void offsetPositionsForMove(final int n, final int n2) {
        this.this$0.offsetPositionRecordsForMove(n, n2);
        this.this$0.mItemsAddedOrRemoved = true;
    }
    
    @Override
    public void offsetPositionsForRemovingInvisible(final int n, final int n2) {
        this.this$0.offsetPositionRecordsForRemove(n, n2, true);
        this.this$0.mItemsAddedOrRemoved = true;
        RecyclerView$State.access$1212(this.this$0.mState, n2);
    }
    
    @Override
    public void offsetPositionsForRemovingLaidOutOrNewView(final int n, final int n2) {
        this.this$0.offsetPositionRecordsForRemove(n, n2, false);
        this.this$0.mItemsAddedOrRemoved = true;
    }
    
    @Override
    public void onDispatchFirstPass(final AdapterHelper$UpdateOp adapterHelper$UpdateOp) {
        this.dispatchUpdate(adapterHelper$UpdateOp);
    }
    
    @Override
    public void onDispatchSecondPass(final AdapterHelper$UpdateOp adapterHelper$UpdateOp) {
        this.dispatchUpdate(adapterHelper$UpdateOp);
    }
}
