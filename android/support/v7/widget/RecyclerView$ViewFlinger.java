// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.os.SystemClock;
import android.support.v4.view.AccessibilityDelegateCompat;
import android.support.v4.view.VelocityTrackerCompat;
import android.view.View$MeasureSpec;
import android.view.FocusFinder;
import android.view.ViewParent;
import android.graphics.Canvas;
import android.os.Parcelable;
import android.util.SparseArray;
import android.support.v4.view.MotionEventCompat;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.support.v4.view.accessibility.AccessibilityEventCompat;
import android.view.accessibility.AccessibilityEvent;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import android.view.ViewGroup$LayoutParams;
import android.content.res.TypedArray;
import android.support.v7.recyclerview.R$styleable;
import android.view.ViewConfiguration;
import android.util.AttributeSet;
import android.content.Context;
import android.os.Build$VERSION;
import android.view.VelocityTracker;
import android.graphics.RectF;
import android.graphics.Rect;
import android.support.v4.view.NestedScrollingChildHelper;
import java.util.List;
import java.util.ArrayList;
import android.support.v4.widget.EdgeEffectCompat;
import android.view.accessibility.AccessibilityManager;
import android.support.v4.view.ScrollingView;
import android.support.v4.view.NestedScrollingChild;
import android.view.ViewGroup;
import android.graphics.PointF;
import android.util.Log;
import android.support.v4.os.TraceCompat;
import android.view.View;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ScrollerCompat;
import android.view.animation.Interpolator;

class RecyclerView$ViewFlinger implements Runnable
{
    private boolean mEatRunOnAnimationRequest;
    private Interpolator mInterpolator;
    private int mLastFlingX;
    private int mLastFlingY;
    private boolean mReSchedulePostAnimationCallback;
    private ScrollerCompat mScroller;
    final /* synthetic */ RecyclerView this$0;
    
    public RecyclerView$ViewFlinger(final RecyclerView this$0) {
        this.this$0 = this$0;
        this.mInterpolator = RecyclerView.sQuinticInterpolator;
        this.mEatRunOnAnimationRequest = false;
        this.mReSchedulePostAnimationCallback = false;
        this.mScroller = ScrollerCompat.create(this$0.getContext(), RecyclerView.sQuinticInterpolator);
    }
    
    private int computeScrollDuration(int n, int n2, int n3, int n4) {
        final int abs = Math.abs(n);
        final int abs2 = Math.abs(n2);
        boolean b;
        if (abs > abs2) {
            b = true;
        }
        else {
            b = false;
        }
        n3 = (int)Math.sqrt(n3 * n3 + n4 * n4);
        n2 = (int)Math.sqrt(n * n + n2 * n2);
        if (b) {
            n = this.this$0.getWidth();
        }
        else {
            n = this.this$0.getHeight();
        }
        n4 = n / 2;
        final float min = Math.min(1.0f, n2 * 1.0f / n);
        final float n5 = n4;
        final float n6 = n4;
        final float distanceInfluenceForSnapDuration = this.distanceInfluenceForSnapDuration(min);
        if (n3 > 0) {
            n = Math.round(1000.0f * Math.abs((distanceInfluenceForSnapDuration * n6 + n5) / n3)) * 4;
        }
        else {
            if (b) {
                n2 = abs;
            }
            else {
                n2 = abs2;
            }
            n = (int)((n2 / n + 1.0f) * 300.0f);
        }
        return Math.min(n, 2000);
    }
    
    private void disableRunOnAnimationRequests() {
        this.mReSchedulePostAnimationCallback = false;
        this.mEatRunOnAnimationRequest = true;
    }
    
    private float distanceInfluenceForSnapDuration(final float n) {
        return (float)Math.sin((float)((n - 0.5f) * 0.4712389167638204));
    }
    
    private void enableRunOnAnimationRequests() {
        this.mEatRunOnAnimationRequest = false;
        if (this.mReSchedulePostAnimationCallback) {
            this.postOnAnimation();
        }
    }
    
    public void fling(final int n, final int n2) {
        this.this$0.setScrollState(2);
        this.mLastFlingY = 0;
        this.mLastFlingX = 0;
        this.mScroller.fling(0, 0, n, n2, Integer.MIN_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE);
        this.postOnAnimation();
    }
    
    void postOnAnimation() {
        if (this.mEatRunOnAnimationRequest) {
            this.mReSchedulePostAnimationCallback = true;
            return;
        }
        this.this$0.removeCallbacks((Runnable)this);
        ViewCompat.postOnAnimation((View)this.this$0, this);
    }
    
    @Override
    public void run() {
        if (this.this$0.mLayout == null) {
            this.stop();
            return;
        }
        this.disableRunOnAnimationRequests();
        this.this$0.consumePendingUpdateOperations();
        final ScrollerCompat mScroller = this.mScroller;
        final RecyclerView$SmoothScroller mSmoothScroller = this.this$0.mLayout.mSmoothScroller;
        Label_0618: {
            if (mScroller.computeScrollOffset()) {
                final int currX = mScroller.getCurrX();
                final int currY = mScroller.getCurrY();
                final int n = currX - this.mLastFlingX;
                final int n2 = currY - this.mLastFlingY;
                int n3 = 0;
                int scrollHorizontallyBy = 0;
                int n4 = 0;
                int scrollVerticallyBy = 0;
                this.mLastFlingX = currX;
                this.mLastFlingY = currY;
                int n5 = 0;
                int n6 = 0;
                int n7 = 0;
                int n8 = 0;
                while (true) {
                    Label_0723: {
                        if (this.this$0.mAdapter == null) {
                            break Label_0723;
                        }
                        this.this$0.eatRequestLayout();
                        this.this$0.onEnterLayoutOrScroll();
                        TraceCompat.beginSection("RV Scroll");
                        if (n != 0) {
                            scrollHorizontallyBy = this.this$0.mLayout.scrollHorizontallyBy(n, this.this$0.mRecycler, this.this$0.mState);
                            n6 = n - scrollHorizontallyBy;
                        }
                        if (n2 != 0) {
                            scrollVerticallyBy = this.this$0.mLayout.scrollVerticallyBy(n2, this.this$0.mRecycler, this.this$0.mState);
                            n8 = n2 - scrollVerticallyBy;
                        }
                        TraceCompat.endSection();
                        this.this$0.repositionShadowingViews();
                        this.this$0.onExitLayoutOrScroll();
                        this.this$0.resumeRequestLayout(false);
                        n7 = n8;
                        n4 = scrollVerticallyBy;
                        n5 = n6;
                        n3 = scrollHorizontallyBy;
                        if (mSmoothScroller == null) {
                            break Label_0723;
                        }
                        n7 = n8;
                        n4 = scrollVerticallyBy;
                        n5 = n6;
                        n3 = scrollHorizontallyBy;
                        if (mSmoothScroller.isPendingInitialRun()) {
                            break Label_0723;
                        }
                        n7 = n8;
                        n4 = scrollVerticallyBy;
                        n5 = n6;
                        n3 = scrollHorizontallyBy;
                        if (!mSmoothScroller.isRunning()) {
                            break Label_0723;
                        }
                        final int itemCount = this.this$0.mState.getItemCount();
                        int n9;
                        int n10;
                        if (itemCount == 0) {
                            mSmoothScroller.stop();
                            n9 = scrollVerticallyBy;
                            n10 = n6;
                        }
                        else {
                            if (mSmoothScroller.getTargetPosition() < itemCount) {
                                mSmoothScroller.onAnimation(n - n6, n2 - n8);
                                n3 = scrollHorizontallyBy;
                                n5 = n6;
                                n4 = scrollVerticallyBy;
                                n7 = n8;
                                break Label_0723;
                            }
                            mSmoothScroller.setTargetPosition(itemCount - 1);
                            mSmoothScroller.onAnimation(n - n6, n2 - n8);
                            n9 = scrollVerticallyBy;
                            n10 = n6;
                        }
                        if (!this.this$0.mItemDecorations.isEmpty()) {
                            this.this$0.invalidate();
                        }
                        if (this.this$0.getOverScrollMode() != 2) {
                            this.this$0.considerReleasingGlowsOnScroll(n, n2);
                        }
                        if (n10 != 0 || n8 != 0) {
                            final int n11 = (int)mScroller.getCurrVelocity();
                            int n13;
                            if (n10 != currX) {
                                int n12;
                                if (n10 < 0) {
                                    n12 = -n11;
                                }
                                else if (n10 > 0) {
                                    n12 = n11;
                                }
                                else {
                                    n12 = 0;
                                }
                                n13 = n12;
                            }
                            else {
                                n13 = 0;
                            }
                            int n14;
                            if (n8 != currY) {
                                if (n8 < 0) {
                                    n14 = -n11;
                                }
                                else {
                                    n14 = n11;
                                    if (n8 <= 0) {
                                        n14 = 0;
                                    }
                                }
                            }
                            else {
                                n14 = 0;
                            }
                            if (this.this$0.getOverScrollMode() != 2) {
                                this.this$0.absorbGlows(n13, n14);
                            }
                            if ((n13 != 0 || n10 == currX || mScroller.getFinalX() == 0) && (n14 != 0 || n8 == currY || mScroller.getFinalY() == 0)) {
                                mScroller.abortAnimation();
                            }
                        }
                        if (scrollHorizontallyBy != 0 || n9 != 0) {
                            this.this$0.dispatchOnScrolled(scrollHorizontallyBy, n9);
                        }
                        if (!RecyclerView.access$500(this.this$0)) {
                            this.this$0.invalidate();
                        }
                        boolean b;
                        if (n2 != 0 && this.this$0.mLayout.canScrollVertically() && n9 == n2) {
                            b = true;
                        }
                        else {
                            b = false;
                        }
                        boolean b2;
                        if (n != 0 && this.this$0.mLayout.canScrollHorizontally() && scrollHorizontallyBy == n) {
                            b2 = true;
                        }
                        else {
                            b2 = false;
                        }
                        boolean b3;
                        if ((n == 0 && n2 == 0) || b2 || b) {
                            b3 = true;
                        }
                        else {
                            b3 = false;
                        }
                        if (mScroller.isFinished() || !b3) {
                            this.this$0.setScrollState(0);
                            break Label_0618;
                        }
                        this.postOnAnimation();
                        break Label_0618;
                    }
                    int n10 = n5;
                    n8 = n7;
                    int n9 = n4;
                    scrollHorizontallyBy = n3;
                    continue;
                }
            }
        }
        if (mSmoothScroller != null) {
            if (mSmoothScroller.isPendingInitialRun()) {
                mSmoothScroller.onAnimation(0, 0);
            }
            if (!this.mReSchedulePostAnimationCallback) {
                mSmoothScroller.stop();
            }
        }
        this.enableRunOnAnimationRequests();
    }
    
    public void smoothScrollBy(final int n, final int n2) {
        this.smoothScrollBy(n, n2, 0, 0);
    }
    
    public void smoothScrollBy(final int n, final int n2, final int n3) {
        this.smoothScrollBy(n, n2, n3, RecyclerView.sQuinticInterpolator);
    }
    
    public void smoothScrollBy(final int n, final int n2, final int n3, final int n4) {
        this.smoothScrollBy(n, n2, this.computeScrollDuration(n, n2, n3, n4));
    }
    
    public void smoothScrollBy(final int n, final int n2, final int n3, final Interpolator mInterpolator) {
        if (this.mInterpolator != mInterpolator) {
            this.mInterpolator = mInterpolator;
            this.mScroller = ScrollerCompat.create(this.this$0.getContext(), mInterpolator);
        }
        this.this$0.setScrollState(2);
        this.mLastFlingY = 0;
        this.mLastFlingX = 0;
        this.mScroller.startScroll(0, 0, n, n2, n3);
        this.postOnAnimation();
    }
    
    public void stop() {
        this.this$0.removeCallbacks((Runnable)this);
        this.mScroller.abortAnimation();
    }
}
