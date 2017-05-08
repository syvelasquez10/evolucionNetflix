// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

import android.view.ViewGroup$MarginLayoutParams;
import android.widget.LinearLayout$LayoutParams;
import android.view.ViewGroup$LayoutParams;
import android.support.v4.view.WindowInsetsCompat;
import android.widget.LinearLayout;
import android.os.Parcelable;
import android.support.v4.view.MotionEventCompat;
import android.view.ViewConfiguration;
import android.view.MotionEvent;
import android.view.animation.Interpolator;
import java.util.List;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.content.Context;
import android.support.v4.widget.ScrollerCompat;
import android.view.View;
import java.lang.ref.WeakReference;

public class AppBarLayout$Behavior extends ViewOffsetBehavior<AppBarLayout>
{
    private int mActivePointerId;
    private ValueAnimatorCompat mAnimator;
    private Runnable mFlingRunnable;
    private boolean mIsBeingDragged;
    private int mLastMotionY;
    private WeakReference<View> mLastNestedScrollingChildRef;
    private int mOffsetDelta;
    private int mOffsetToChildIndexOnLayout;
    private boolean mOffsetToChildIndexOnLayoutIsMinHeight;
    private float mOffsetToChildIndexOnLayoutPerc;
    private ScrollerCompat mScroller;
    private boolean mSkipNestedPreScroll;
    private int mTouchSlop;
    
    public AppBarLayout$Behavior() {
        this.mOffsetToChildIndexOnLayout = -1;
        this.mActivePointerId = -1;
        this.mTouchSlop = -1;
    }
    
    public AppBarLayout$Behavior(final Context context, final AttributeSet set) {
        super(context, set);
        this.mOffsetToChildIndexOnLayout = -1;
        this.mActivePointerId = -1;
        this.mTouchSlop = -1;
    }
    
    private void animateOffsetTo(final CoordinatorLayout coordinatorLayout, final AppBarLayout appBarLayout, final int n) {
        if (this.mAnimator == null) {
            (this.mAnimator = ViewUtils.createAnimator()).setInterpolator(AnimationUtils.DECELERATE_INTERPOLATOR);
            this.mAnimator.setUpdateListener(new AppBarLayout$Behavior$1(this, coordinatorLayout, appBarLayout));
        }
        else {
            this.mAnimator.cancel();
        }
        this.mAnimator.setIntValues(this.getTopBottomOffsetForScrollingSibling(), n);
        this.mAnimator.start();
    }
    
    private boolean canDragAppBarLayout() {
        if (this.mLastNestedScrollingChildRef != null) {
            final View view = this.mLastNestedScrollingChildRef.get();
            return view != null && view.isShown() && !ViewCompat.canScrollVertically(view, -1);
        }
        return false;
    }
    
    private void dispatchOffsetUpdates(final AppBarLayout appBarLayout) {
        final List access$200 = appBarLayout.mListeners;
        for (int size = access$200.size(), i = 0; i < size; ++i) {
            final AppBarLayout$OnOffsetChangedListener appBarLayout$OnOffsetChangedListener = access$200.get(i);
            if (appBarLayout$OnOffsetChangedListener != null) {
                appBarLayout$OnOffsetChangedListener.onOffsetChanged(appBarLayout, this.getTopAndBottomOffset());
            }
        }
    }
    
    private boolean fling(final CoordinatorLayout coordinatorLayout, final AppBarLayout appBarLayout, final int n, final int n2, final float n3) {
        if (this.mFlingRunnable != null) {
            appBarLayout.removeCallbacks(this.mFlingRunnable);
        }
        if (this.mScroller == null) {
            this.mScroller = ScrollerCompat.create(appBarLayout.getContext());
        }
        this.mScroller.fling(0, this.getTopBottomOffsetForScrollingSibling(), 0, Math.round(n3), 0, 0, n, n2);
        if (this.mScroller.computeScrollOffset()) {
            ViewCompat.postOnAnimation((View)appBarLayout, this.mFlingRunnable = new AppBarLayout$Behavior$FlingRunnable(this, coordinatorLayout, appBarLayout));
            return true;
        }
        this.mFlingRunnable = null;
        return false;
    }
    
    private int interpolateOffset(final AppBarLayout appBarLayout, final int n) {
        final int abs = Math.abs(n);
        final int childCount = appBarLayout.getChildCount();
        int n2 = 0;
        while (true) {
            Label_0212: {
                int n3;
                while (true) {
                    n3 = n;
                    if (n2 >= childCount) {
                        break;
                    }
                    final View child = appBarLayout.getChildAt(n2);
                    final AppBarLayout$LayoutParams appBarLayout$LayoutParams = (AppBarLayout$LayoutParams)child.getLayoutParams();
                    final Interpolator scrollInterpolator = appBarLayout$LayoutParams.getScrollInterpolator();
                    if (abs >= child.getTop() && abs <= child.getBottom()) {
                        n3 = n;
                        if (scrollInterpolator == null) {
                            break;
                        }
                        final int scrollFlags = appBarLayout$LayoutParams.getScrollFlags();
                        if ((scrollFlags & 0x1) == 0x0) {
                            break Label_0212;
                        }
                        int n5;
                        final int n4 = n5 = appBarLayout$LayoutParams.bottomMargin + (child.getHeight() + appBarLayout$LayoutParams.topMargin) + 0;
                        if ((scrollFlags & 0x2) != 0x0) {
                            n5 = n4 - ViewCompat.getMinimumHeight(child);
                        }
                        n3 = n;
                        if (n5 > 0) {
                            n3 = Integer.signum(n) * (Math.round(scrollInterpolator.getInterpolation((abs - child.getTop()) / n5) * n5) + child.getTop());
                            break;
                        }
                        break;
                    }
                    else {
                        ++n2;
                    }
                }
                return n3;
            }
            int n5 = 0;
            continue;
        }
    }
    
    private int scroll(final CoordinatorLayout coordinatorLayout, final AppBarLayout appBarLayout, final int n, final int n2, final int n3) {
        return this.setAppBarTopBottomOffset(coordinatorLayout, appBarLayout, this.getTopBottomOffsetForScrollingSibling() - n, n2, n3);
    }
    
    final int getTopBottomOffsetForScrollingSibling() {
        return this.getTopAndBottomOffset() + this.mOffsetDelta;
    }
    
    @Override
    public boolean onInterceptTouchEvent(final CoordinatorLayout coordinatorLayout, final AppBarLayout appBarLayout, final MotionEvent motionEvent) {
        if (this.mTouchSlop < 0) {
            this.mTouchSlop = ViewConfiguration.get(coordinatorLayout.getContext()).getScaledTouchSlop();
        }
        if (motionEvent.getAction() == 2 && this.mIsBeingDragged) {
            return true;
        }
        switch (MotionEventCompat.getActionMasked(motionEvent)) {
            case 2: {
                final int mActivePointerId = this.mActivePointerId;
                if (mActivePointerId == -1) {
                    break;
                }
                final int pointerIndex = MotionEventCompat.findPointerIndex(motionEvent, mActivePointerId);
                if (pointerIndex == -1) {
                    break;
                }
                final int mLastMotionY = (int)MotionEventCompat.getY(motionEvent, pointerIndex);
                if (Math.abs(mLastMotionY - this.mLastMotionY) > this.mTouchSlop) {
                    this.mIsBeingDragged = true;
                    this.mLastMotionY = mLastMotionY;
                    break;
                }
                break;
            }
            case 0: {
                this.mIsBeingDragged = false;
                final int n = (int)motionEvent.getX();
                final int mLastMotionY2 = (int)motionEvent.getY();
                if (coordinatorLayout.isPointInChildBounds((View)appBarLayout, n, mLastMotionY2) && this.canDragAppBarLayout()) {
                    this.mLastMotionY = mLastMotionY2;
                    this.mActivePointerId = MotionEventCompat.getPointerId(motionEvent, 0);
                    break;
                }
                break;
            }
            case 1:
            case 3: {
                this.mIsBeingDragged = false;
                this.mActivePointerId = -1;
                break;
            }
        }
        return this.mIsBeingDragged;
    }
    
    @Override
    public boolean onLayoutChild(final CoordinatorLayout coordinatorLayout, final AppBarLayout appBarLayout, int topAndBottomOffset) {
        final boolean onLayoutChild = super.onLayoutChild(coordinatorLayout, appBarLayout, topAndBottomOffset);
        final int pendingAction = appBarLayout.getPendingAction();
        if (pendingAction != 0) {
            if ((pendingAction & 0x4) != 0x0) {
                topAndBottomOffset = 1;
            }
            else {
                topAndBottomOffset = 0;
            }
            if ((pendingAction & 0x2) != 0x0) {
                final int n = -appBarLayout.getUpNestedPreScrollRange();
                if (topAndBottomOffset != 0) {
                    this.animateOffsetTo(coordinatorLayout, appBarLayout, n);
                }
                else {
                    this.setAppBarTopBottomOffset(coordinatorLayout, appBarLayout, n);
                }
            }
            else if ((pendingAction & 0x1) != 0x0) {
                if (topAndBottomOffset != 0) {
                    this.animateOffsetTo(coordinatorLayout, appBarLayout, 0);
                }
                else {
                    this.setAppBarTopBottomOffset(coordinatorLayout, appBarLayout, 0);
                }
            }
            appBarLayout.resetPendingAction();
        }
        else if (this.mOffsetToChildIndexOnLayout >= 0) {
            final View child = appBarLayout.getChildAt(this.mOffsetToChildIndexOnLayout);
            topAndBottomOffset = -child.getBottom();
            if (this.mOffsetToChildIndexOnLayoutIsMinHeight) {
                topAndBottomOffset += ViewCompat.getMinimumHeight(child);
            }
            else {
                topAndBottomOffset += Math.round(child.getHeight() * this.mOffsetToChildIndexOnLayoutPerc);
            }
            this.setTopAndBottomOffset(topAndBottomOffset);
            this.mOffsetToChildIndexOnLayout = -1;
        }
        this.dispatchOffsetUpdates(appBarLayout);
        return onLayoutChild;
    }
    
    @Override
    public boolean onNestedFling(final CoordinatorLayout coordinatorLayout, final AppBarLayout appBarLayout, final View view, final float n, final float n2, final boolean b) {
        final boolean b2 = false;
        boolean fling;
        if (!b) {
            fling = this.fling(coordinatorLayout, appBarLayout, -appBarLayout.getTotalScrollRange(), 0, -n2);
        }
        else {
            int n3;
            if (n2 < 0.0f) {
                n3 = -appBarLayout.getTotalScrollRange() + appBarLayout.getDownNestedPreScrollRange();
                fling = b2;
                if (this.getTopBottomOffsetForScrollingSibling() > n3) {
                    return fling;
                }
            }
            else if (this.getTopBottomOffsetForScrollingSibling() < (n3 = -appBarLayout.getUpNestedPreScrollRange())) {
                return false;
            }
            fling = b2;
            if (this.getTopBottomOffsetForScrollingSibling() != n3) {
                this.animateOffsetTo(coordinatorLayout, appBarLayout, n3);
                return true;
            }
        }
        return fling;
    }
    
    @Override
    public void onNestedPreScroll(final CoordinatorLayout coordinatorLayout, final AppBarLayout appBarLayout, final View view, int n, final int n2, final int[] array) {
        if (n2 != 0 && !this.mSkipNestedPreScroll) {
            int n3;
            if (n2 < 0) {
                n = -appBarLayout.getTotalScrollRange();
                n3 = n + appBarLayout.getDownNestedPreScrollRange();
            }
            else {
                n = -appBarLayout.getUpNestedPreScrollRange();
                n3 = 0;
            }
            array[1] = this.scroll(coordinatorLayout, appBarLayout, n2, n, n3);
        }
    }
    
    @Override
    public void onNestedScroll(final CoordinatorLayout coordinatorLayout, final AppBarLayout appBarLayout, final View view, final int n, final int n2, final int n3, final int n4) {
        if (n4 < 0) {
            this.scroll(coordinatorLayout, appBarLayout, n4, -appBarLayout.getDownNestedScrollRange(), 0);
            this.mSkipNestedPreScroll = true;
            return;
        }
        this.mSkipNestedPreScroll = false;
    }
    
    @Override
    public void onRestoreInstanceState(final CoordinatorLayout coordinatorLayout, final AppBarLayout appBarLayout, final Parcelable parcelable) {
        if (parcelable instanceof AppBarLayout$Behavior$SavedState) {
            final AppBarLayout$Behavior$SavedState appBarLayout$Behavior$SavedState = (AppBarLayout$Behavior$SavedState)parcelable;
            super.onRestoreInstanceState(coordinatorLayout, appBarLayout, appBarLayout$Behavior$SavedState.getSuperState());
            this.mOffsetToChildIndexOnLayout = appBarLayout$Behavior$SavedState.firstVisibleChildIndex;
            this.mOffsetToChildIndexOnLayoutPerc = appBarLayout$Behavior$SavedState.firstVisibileChildPercentageShown;
            this.mOffsetToChildIndexOnLayoutIsMinHeight = appBarLayout$Behavior$SavedState.firstVisibileChildAtMinimumHeight;
            return;
        }
        super.onRestoreInstanceState(coordinatorLayout, appBarLayout, parcelable);
        this.mOffsetToChildIndexOnLayout = -1;
    }
    
    @Override
    public Parcelable onSaveInstanceState(final CoordinatorLayout coordinatorLayout, final AppBarLayout appBarLayout) {
        boolean firstVisibileChildAtMinimumHeight = false;
        final Parcelable onSaveInstanceState = super.onSaveInstanceState(coordinatorLayout, appBarLayout);
        final int topAndBottomOffset = this.getTopAndBottomOffset();
        for (int childCount = appBarLayout.getChildCount(), i = 0; i < childCount; ++i) {
            final View child = appBarLayout.getChildAt(i);
            final int n = child.getBottom() + topAndBottomOffset;
            if (child.getTop() + topAndBottomOffset <= 0 && n >= 0) {
                final AppBarLayout$Behavior$SavedState appBarLayout$Behavior$SavedState = new AppBarLayout$Behavior$SavedState(onSaveInstanceState);
                appBarLayout$Behavior$SavedState.firstVisibleChildIndex = i;
                if (n == ViewCompat.getMinimumHeight(child)) {
                    firstVisibileChildAtMinimumHeight = true;
                }
                appBarLayout$Behavior$SavedState.firstVisibileChildAtMinimumHeight = firstVisibileChildAtMinimumHeight;
                appBarLayout$Behavior$SavedState.firstVisibileChildPercentageShown = n / child.getHeight();
                return (Parcelable)appBarLayout$Behavior$SavedState;
            }
        }
        return onSaveInstanceState;
    }
    
    @Override
    public boolean onStartNestedScroll(final CoordinatorLayout coordinatorLayout, final AppBarLayout appBarLayout, final View view, final View view2, final int n) {
        final boolean b = (n & 0x2) != 0x0 && appBarLayout.hasScrollableChildren() && coordinatorLayout.getHeight() - view.getHeight() <= appBarLayout.getHeight();
        if (b && this.mAnimator != null) {
            this.mAnimator.cancel();
        }
        this.mLastNestedScrollingChildRef = null;
        return b;
    }
    
    @Override
    public void onStopNestedScroll(final CoordinatorLayout coordinatorLayout, final AppBarLayout appBarLayout, final View view) {
        this.mSkipNestedPreScroll = false;
        this.mLastNestedScrollingChildRef = new WeakReference<View>(view);
    }
    
    @Override
    public boolean onTouchEvent(final CoordinatorLayout coordinatorLayout, final AppBarLayout appBarLayout, final MotionEvent motionEvent) {
        final boolean b = false;
        if (this.mTouchSlop < 0) {
            this.mTouchSlop = ViewConfiguration.get(coordinatorLayout.getContext()).getScaledTouchSlop();
        }
        final int n = (int)motionEvent.getX();
        final int mLastMotionY = (int)motionEvent.getY();
        switch (MotionEventCompat.getActionMasked(motionEvent)) {
            case 0: {
                boolean b2 = b;
                if (!coordinatorLayout.isPointInChildBounds((View)appBarLayout, n, mLastMotionY)) {
                    return b2;
                }
                b2 = b;
                if (this.canDragAppBarLayout()) {
                    this.mLastMotionY = mLastMotionY;
                    this.mActivePointerId = MotionEventCompat.getPointerId(motionEvent, 0);
                    break;
                }
                return b2;
            }
            case 2: {
                final int pointerIndex = MotionEventCompat.findPointerIndex(motionEvent, this.mActivePointerId);
                final boolean b2 = b;
                if (pointerIndex == -1) {
                    return b2;
                }
                final int mLastMotionY2 = (int)MotionEventCompat.getY(motionEvent, pointerIndex);
                int n3;
                final int n2 = n3 = this.mLastMotionY - mLastMotionY2;
                if (!this.mIsBeingDragged) {
                    n3 = n2;
                    if (Math.abs(n2) > this.mTouchSlop) {
                        this.mIsBeingDragged = true;
                        if (n2 > 0) {
                            n3 = n2 - this.mTouchSlop;
                        }
                        else {
                            n3 = n2 + this.mTouchSlop;
                        }
                    }
                }
                if (this.mIsBeingDragged) {
                    this.mLastMotionY = mLastMotionY2;
                    this.scroll(coordinatorLayout, appBarLayout, n3, -appBarLayout.getDownNestedScrollRange(), 0);
                    break;
                }
                break;
            }
            case 1:
            case 3: {
                this.mIsBeingDragged = false;
                this.mActivePointerId = -1;
                break;
            }
        }
        return true;
    }
    
    final int setAppBarTopBottomOffset(final CoordinatorLayout coordinatorLayout, final AppBarLayout appBarLayout, final int n) {
        return this.setAppBarTopBottomOffset(coordinatorLayout, appBarLayout, n, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
    
    final int setAppBarTopBottomOffset(final CoordinatorLayout coordinatorLayout, final AppBarLayout appBarLayout, int interpolateOffset, int constrain, final int n) {
        final int topBottomOffsetForScrollingSibling = this.getTopBottomOffsetForScrollingSibling();
        int n3;
        final int n2 = n3 = 0;
        if (constrain != 0) {
            n3 = n2;
            if (topBottomOffsetForScrollingSibling >= constrain) {
                n3 = n2;
                if (topBottomOffsetForScrollingSibling <= n) {
                    constrain = MathUtils.constrain(interpolateOffset, constrain, n);
                    n3 = n2;
                    if (topBottomOffsetForScrollingSibling != constrain) {
                        if (appBarLayout.hasChildWithInterpolator()) {
                            interpolateOffset = this.interpolateOffset(appBarLayout, constrain);
                        }
                        else {
                            interpolateOffset = constrain;
                        }
                        final boolean setTopAndBottomOffset = this.setTopAndBottomOffset(interpolateOffset);
                        this.mOffsetDelta = constrain - interpolateOffset;
                        if (!setTopAndBottomOffset && appBarLayout.hasChildWithInterpolator()) {
                            coordinatorLayout.dispatchDependentViewsChanged((View)appBarLayout);
                        }
                        this.dispatchOffsetUpdates(appBarLayout);
                        n3 = topBottomOffsetForScrollingSibling - constrain;
                    }
                }
            }
        }
        return n3;
    }
}
