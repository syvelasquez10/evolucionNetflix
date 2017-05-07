// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.os.Bundle;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat$CollectionItemInfoCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat$CollectionInfoCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v4.view.accessibility.AccessibilityRecordCompat;
import android.support.v4.view.accessibility.AccessibilityEventCompat;
import android.view.accessibility.AccessibilityEvent;
import android.view.ViewGroup$MarginLayoutParams;
import android.view.View$MeasureSpec;
import android.util.SparseArray;
import android.support.v4.view.ViewConfigurationCompat;
import android.support.v4.view.AccessibilityDelegateCompat;
import android.support.v4.view.VelocityTrackerCompat;
import android.os.Parcelable;
import android.view.ViewParent;
import android.view.FocusFinder;
import android.graphics.Canvas;
import android.support.v4.util.SimpleArrayMap;
import android.support.v4.util.ArrayMap;
import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;
import android.view.ViewGroup$LayoutParams;
import android.support.v4.view.ViewCompat;
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
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.graphics.PointF;
import android.util.Log;
import android.view.View;

public abstract class RecyclerView$SmoothScroller
{
    private RecyclerView$LayoutManager mLayoutManager;
    private boolean mPendingInitialRun;
    private RecyclerView mRecyclerView;
    private final RecyclerView$SmoothScroller$Action mRecyclingAction;
    private boolean mRunning;
    private int mTargetPosition;
    private View mTargetView;
    
    public RecyclerView$SmoothScroller() {
        this.mTargetPosition = -1;
        this.mRecyclingAction = new RecyclerView$SmoothScroller$Action(0, 0);
    }
    
    private void onAnimation(final int n, final int n2) {
        if (!this.mRunning || this.mTargetPosition == -1) {
            this.stop();
        }
        this.mPendingInitialRun = false;
        if (this.mTargetView != null) {
            if (this.getChildPosition(this.mTargetView) == this.mTargetPosition) {
                this.onTargetFound(this.mTargetView, this.mRecyclerView.mState, this.mRecyclingAction);
                this.mRecyclingAction.runIfNecessary(this.mRecyclerView);
                this.stop();
            }
            else {
                Log.e("RecyclerView", "Passed over target position while smooth scrolling.");
                this.mTargetView = null;
            }
        }
        if (this.mRunning) {
            this.onSeekTargetStep(n, n2, this.mRecyclerView.mState, this.mRecyclingAction);
            this.mRecyclingAction.runIfNecessary(this.mRecyclerView);
        }
    }
    
    public View findViewByPosition(final int n) {
        return this.mRecyclerView.mLayout.findViewByPosition(n);
    }
    
    public int getChildCount() {
        return this.mRecyclerView.mLayout.getChildCount();
    }
    
    public int getChildPosition(final View view) {
        return this.mRecyclerView.getChildPosition(view);
    }
    
    public RecyclerView$LayoutManager getLayoutManager() {
        return this.mLayoutManager;
    }
    
    public int getTargetPosition() {
        return this.mTargetPosition;
    }
    
    public void instantScrollToPosition(final int n) {
        this.mRecyclerView.scrollToPosition(n);
    }
    
    public boolean isPendingInitialRun() {
        return this.mPendingInitialRun;
    }
    
    public boolean isRunning() {
        return this.mRunning;
    }
    
    protected void normalize(final PointF pointF) {
        final double sqrt = Math.sqrt(pointF.x * pointF.x + pointF.y * pointF.y);
        pointF.x /= (float)sqrt;
        pointF.y /= (float)sqrt;
    }
    
    protected void onChildAttachedToWindow(final View mTargetView) {
        if (this.getChildPosition(mTargetView) == this.getTargetPosition()) {
            this.mTargetView = mTargetView;
        }
    }
    
    protected abstract void onSeekTargetStep(final int p0, final int p1, final RecyclerView$State p2, final RecyclerView$SmoothScroller$Action p3);
    
    protected abstract void onStart();
    
    protected abstract void onStop();
    
    protected abstract void onTargetFound(final View p0, final RecyclerView$State p1, final RecyclerView$SmoothScroller$Action p2);
    
    public void setTargetPosition(final int mTargetPosition) {
        this.mTargetPosition = mTargetPosition;
    }
    
    void start(final RecyclerView mRecyclerView, final RecyclerView$LayoutManager mLayoutManager) {
        this.mRecyclerView = mRecyclerView;
        this.mLayoutManager = mLayoutManager;
        if (this.mTargetPosition == -1) {
            throw new IllegalArgumentException("Invalid target position");
        }
        this.mRecyclerView.mState.mTargetPosition = this.mTargetPosition;
        this.mRunning = true;
        this.mPendingInitialRun = true;
        this.mTargetView = this.findViewByPosition(this.getTargetPosition());
        this.onStart();
        this.mRecyclerView.mViewFlinger.postOnAnimation();
    }
    
    protected final void stop() {
        if (!this.mRunning) {
            return;
        }
        this.onStop();
        this.mRecyclerView.mState.mTargetPosition = -1;
        this.mTargetView = null;
        this.mTargetPosition = -1;
        this.mPendingInitialRun = false;
        this.mRunning = false;
        this.mLayoutManager.onSmoothScrollerStopped(this);
        this.mLayoutManager = null;
        this.mRecyclerView = null;
    }
}
