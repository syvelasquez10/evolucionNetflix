// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.graphics.PointF;
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
import android.view.ViewGroup;
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
        ViewCompat.postOnAnimation((View)this.this$0, this);
    }
    
    @Override
    public void run() {
        this.disableRunOnAnimationRequests();
        this.this$0.consumePendingUpdateOperations();
        final ScrollerCompat mScroller = this.mScroller;
        final RecyclerView$SmoothScroller mSmoothScroller = this.this$0.mLayout.mSmoothScroller;
        if (mScroller.computeScrollOffset()) {
            final int currX = mScroller.getCurrX();
            final int currY = mScroller.getCurrY();
            final int n = currX - this.mLastFlingX;
            final int n2 = currY - this.mLastFlingY;
            int n3 = 0;
            final int n4 = 0;
            int scrollVerticallyBy = 0;
            final int n5 = 0;
            this.mLastFlingX = currX;
            this.mLastFlingY = currY;
            int n6 = 0;
            int n7 = 0;
            int n8 = 0;
            final int n9 = 0;
            if (this.this$0.mAdapter != null) {
                this.this$0.eatRequestLayout();
                this.this$0.mRunningLayoutOrScroll = true;
                int scrollHorizontallyBy = n4;
                if (n != 0) {
                    scrollHorizontallyBy = this.this$0.mLayout.scrollHorizontallyBy(n, this.this$0.mRecycler, this.this$0.mState);
                    n7 = n - scrollHorizontallyBy;
                }
                n8 = n9;
                scrollVerticallyBy = n5;
                if (n2 != 0) {
                    scrollVerticallyBy = this.this$0.mLayout.scrollVerticallyBy(n2, this.this$0.mRecycler, this.this$0.mState);
                    n8 = n2 - scrollVerticallyBy;
                }
                if (this.this$0.supportsChangeAnimations()) {
                    for (int childCount = this.this$0.mChildHelper.getChildCount(), i = 0; i < childCount; ++i) {
                        final View child = this.this$0.mChildHelper.getChildAt(i);
                        final RecyclerView$ViewHolder childViewHolder = this.this$0.getChildViewHolder(child);
                        if (childViewHolder != null && childViewHolder.mShadowingHolder != null) {
                            View itemView;
                            if (childViewHolder.mShadowingHolder != null) {
                                itemView = childViewHolder.mShadowingHolder.itemView;
                            }
                            else {
                                itemView = null;
                            }
                            if (itemView != null) {
                                final int left = child.getLeft();
                                final int top = child.getTop();
                                if (left != itemView.getLeft() || top != itemView.getTop()) {
                                    itemView.layout(left, top, itemView.getWidth() + left, itemView.getHeight() + top);
                                }
                            }
                        }
                    }
                }
                if (mSmoothScroller != null && !mSmoothScroller.isPendingInitialRun() && mSmoothScroller.isRunning()) {
                    final int itemCount = this.this$0.mState.getItemCount();
                    if (itemCount == 0) {
                        mSmoothScroller.stop();
                    }
                    else if (mSmoothScroller.getTargetPosition() >= itemCount) {
                        mSmoothScroller.setTargetPosition(itemCount - 1);
                        mSmoothScroller.onAnimation(n - n7, n2 - n8);
                    }
                    else {
                        mSmoothScroller.onAnimation(n - n7, n2 - n8);
                    }
                }
                this.this$0.mRunningLayoutOrScroll = false;
                this.this$0.resumeRequestLayout(false);
                n3 = scrollHorizontallyBy;
                n6 = n7;
            }
            boolean b;
            if (n == n3 && n2 == scrollVerticallyBy) {
                b = true;
            }
            else {
                b = false;
            }
            if (!this.this$0.mItemDecorations.isEmpty()) {
                this.this$0.invalidate();
            }
            if (ViewCompat.getOverScrollMode((View)this.this$0) != 2) {
                this.this$0.considerReleasingGlowsOnScroll(n, n2);
            }
            if (n6 != 0 || n8 != 0) {
                final int n10 = (int)mScroller.getCurrVelocity();
                int n12;
                if (n6 != currX) {
                    int n11;
                    if (n6 < 0) {
                        n11 = -n10;
                    }
                    else if (n6 > 0) {
                        n11 = n10;
                    }
                    else {
                        n11 = 0;
                    }
                    n12 = n11;
                }
                else {
                    n12 = 0;
                }
                int n13;
                if (n8 != currY) {
                    if (n8 < 0) {
                        n13 = -n10;
                    }
                    else {
                        n13 = n10;
                        if (n8 <= 0) {
                            n13 = 0;
                        }
                    }
                }
                else {
                    n13 = 0;
                }
                if (ViewCompat.getOverScrollMode((View)this.this$0) != 2) {
                    this.this$0.absorbGlows(n12, n13);
                }
                if ((n12 != 0 || n6 == currX || mScroller.getFinalX() == 0) && (n13 != 0 || n8 == currY || mScroller.getFinalY() == 0)) {
                    mScroller.abortAnimation();
                }
            }
            if (n3 != 0 || scrollVerticallyBy != 0) {
                RecyclerView.access$2800(this.this$0, 0, 0, 0, 0);
                if (this.this$0.mScrollListener != null) {
                    this.this$0.mScrollListener.onScrolled(this.this$0, n3, scrollVerticallyBy);
                }
            }
            if (!RecyclerView.access$3000(this.this$0)) {
                this.this$0.invalidate();
            }
            if (mScroller.isFinished() || !b) {
                this.this$0.setScrollState(0);
            }
            else {
                this.postOnAnimation();
            }
        }
        if (mSmoothScroller != null && mSmoothScroller.isPendingInitialRun()) {
            mSmoothScroller.onAnimation(0, 0);
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
