// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

import android.os.Parcelable;
import android.view.View$MeasureSpec;
import android.os.Build$VERSION;
import java.util.List;
import android.view.animation.Interpolator;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.content.Context;
import android.view.View;
import java.lang.ref.WeakReference;

public class AppBarLayout$Behavior extends HeaderBehavior<AppBarLayout>
{
    private static final int INVALID_POSITION = -1;
    private static final int MAX_OFFSET_ANIMATION_DURATION = 600;
    private WeakReference<View> mLastNestedScrollingChildRef;
    private ValueAnimatorCompat mOffsetAnimator;
    private int mOffsetDelta;
    private int mOffsetToChildIndexOnLayout;
    private boolean mOffsetToChildIndexOnLayoutIsMinHeight;
    private float mOffsetToChildIndexOnLayoutPerc;
    private AppBarLayout$Behavior$DragCallback mOnDragCallback;
    private boolean mSkipNestedPreScroll;
    private boolean mWasNestedFlung;
    
    public AppBarLayout$Behavior() {
        this.mOffsetToChildIndexOnLayout = -1;
    }
    
    public AppBarLayout$Behavior(final Context context, final AttributeSet set) {
        super(context, set);
        this.mOffsetToChildIndexOnLayout = -1;
    }
    
    private void animateOffsetTo(final CoordinatorLayout coordinatorLayout, final AppBarLayout appBarLayout, final int n, float abs) {
        final int abs2 = Math.abs(this.getTopBottomOffsetForScrollingSibling() - n);
        abs = Math.abs(abs);
        int n2;
        if (abs > 0.0f) {
            n2 = Math.round(abs2 / abs * 1000.0f) * 3;
        }
        else {
            n2 = (int)((abs2 / appBarLayout.getHeight() + 1.0f) * 150.0f);
        }
        this.animateOffsetWithDuration(coordinatorLayout, appBarLayout, n, n2);
    }
    
    private void animateOffsetWithDuration(final CoordinatorLayout coordinatorLayout, final AppBarLayout appBarLayout, final int n, final int n2) {
        final int topBottomOffsetForScrollingSibling = this.getTopBottomOffsetForScrollingSibling();
        if (topBottomOffsetForScrollingSibling == n) {
            if (this.mOffsetAnimator != null && this.mOffsetAnimator.isRunning()) {
                this.mOffsetAnimator.cancel();
            }
            return;
        }
        if (this.mOffsetAnimator == null) {
            (this.mOffsetAnimator = ViewUtils.createAnimator()).setInterpolator(AnimationUtils.DECELERATE_INTERPOLATOR);
            this.mOffsetAnimator.addUpdateListener(new AppBarLayout$Behavior$1(this, coordinatorLayout, appBarLayout));
        }
        else {
            this.mOffsetAnimator.cancel();
        }
        this.mOffsetAnimator.setDuration(Math.min(n2, 600));
        this.mOffsetAnimator.setIntValues(topBottomOffsetForScrollingSibling, n);
        this.mOffsetAnimator.start();
    }
    
    private static boolean checkFlag(final int n, final int n2) {
        return (n & n2) == n2;
    }
    
    private static View getAppBarChildOnOffset(final AppBarLayout appBarLayout, int i) {
        final int abs = Math.abs(i);
        int childCount;
        View child;
        for (childCount = appBarLayout.getChildCount(), i = 0; i < childCount; ++i) {
            child = appBarLayout.getChildAt(i);
            if (abs >= child.getTop() && abs <= child.getBottom()) {
                return child;
            }
        }
        return null;
    }
    
    private int getChildIndexOnOffset(final AppBarLayout appBarLayout, final int n) {
        for (int i = 0; i < appBarLayout.getChildCount(); ++i) {
            final View child = appBarLayout.getChildAt(i);
            if (child.getTop() <= -n && child.getBottom() >= -n) {
                return i;
            }
        }
        return -1;
    }
    
    private int interpolateOffset(final AppBarLayout appBarLayout, final int n) {
        final int abs = Math.abs(n);
        final int childCount = appBarLayout.getChildCount();
        int n2 = 0;
        while (true) {
            Label_0233: {
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
                            break Label_0233;
                        }
                        int n5;
                        final int n4 = n5 = appBarLayout$LayoutParams.bottomMargin + (child.getHeight() + appBarLayout$LayoutParams.topMargin) + 0;
                        if ((scrollFlags & 0x2) != 0x0) {
                            n5 = n4 - ViewCompat.getMinimumHeight(child);
                        }
                        int n6 = n5;
                        if (ViewCompat.getFitsSystemWindows(child)) {
                            n6 = n5 - appBarLayout.getTopInset();
                        }
                        n3 = n;
                        if (n6 > 0) {
                            n3 = Integer.signum(n) * (Math.round(scrollInterpolator.getInterpolation((abs - child.getTop()) / n6) * n6) + child.getTop());
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
    
    private boolean shouldJumpElevationState(final CoordinatorLayout coordinatorLayout, final AppBarLayout appBarLayout) {
        final List<View> dependents = coordinatorLayout.getDependents((View)appBarLayout);
        for (int size = dependents.size(), i = 0; i < size; ++i) {
            final CoordinatorLayout$Behavior behavior = ((CoordinatorLayout$LayoutParams)dependents.get(i).getLayoutParams()).getBehavior();
            if (behavior instanceof AppBarLayout$ScrollingViewBehavior) {
                return ((AppBarLayout$ScrollingViewBehavior)behavior).getOverlayTop() != 0;
            }
        }
        return false;
    }
    
    private void snapToChildIfNeeded(final CoordinatorLayout coordinatorLayout, final AppBarLayout appBarLayout) {
        final int topBottomOffsetForScrollingSibling = this.getTopBottomOffsetForScrollingSibling();
        final int childIndexOnOffset = this.getChildIndexOnOffset(appBarLayout, topBottomOffsetForScrollingSibling);
        if (childIndexOnOffset >= 0) {
            final View child = appBarLayout.getChildAt(childIndexOnOffset);
            final int scrollFlags = ((AppBarLayout$LayoutParams)child.getLayoutParams()).getScrollFlags();
            if ((scrollFlags & 0x11) == 0x11) {
                final int n = -child.getTop();
                int n3;
                final int n2 = n3 = -child.getBottom();
                if (childIndexOnOffset == appBarLayout.getChildCount() - 1) {
                    n3 = n2 + appBarLayout.getTopInset();
                }
                int n4;
                if (checkFlag(scrollFlags, 2)) {
                    n3 += ViewCompat.getMinimumHeight(child);
                    n4 = n;
                }
                else if (checkFlag(scrollFlags, 5)) {
                    final int n5 = ViewCompat.getMinimumHeight(child) + n3;
                    if (topBottomOffsetForScrollingSibling >= (n4 = n5)) {
                        n3 = n5;
                        n4 = n;
                    }
                }
                else {
                    n4 = n;
                }
                if (topBottomOffsetForScrollingSibling >= (n3 + n4) / 2) {
                    n3 = n4;
                }
                this.animateOffsetTo(coordinatorLayout, appBarLayout, MathUtils.constrain(n3, -appBarLayout.getTotalScrollRange(), 0), 0.0f);
            }
        }
    }
    
    private void updateAppBarLayoutDrawableState(final CoordinatorLayout coordinatorLayout, final AppBarLayout appBarLayout, final int n, final int n2) {
        final boolean b = true;
        final boolean b2 = false;
        final View appBarChildOnOffset = getAppBarChildOnOffset(appBarLayout, n);
        if (appBarChildOnOffset != null) {
            final int scrollFlags = ((AppBarLayout$LayoutParams)appBarChildOnOffset.getLayoutParams()).getScrollFlags();
            boolean collapsedState = b2;
            if ((scrollFlags & 0x1) != 0x0) {
                final int minimumHeight = ViewCompat.getMinimumHeight(appBarChildOnOffset);
                if (n2 > 0 && (scrollFlags & 0xC) != 0x0) {
                    collapsedState = (-n >= appBarChildOnOffset.getBottom() - minimumHeight - appBarLayout.getTopInset());
                }
                else {
                    collapsedState = b2;
                    if ((scrollFlags & 0x2) != 0x0) {
                        collapsedState = (-n >= appBarChildOnOffset.getBottom() - minimumHeight - appBarLayout.getTopInset() && b);
                    }
                }
            }
            if (appBarLayout.setCollapsedState(collapsedState) && Build$VERSION.SDK_INT >= 11 && this.shouldJumpElevationState(coordinatorLayout, appBarLayout)) {
                appBarLayout.jumpDrawablesToCurrentState();
            }
        }
    }
    
    @Override
    boolean canDragView(final AppBarLayout appBarLayout) {
        if (this.mOnDragCallback != null) {
            return this.mOnDragCallback.canDrag(appBarLayout);
        }
        if (this.mLastNestedScrollingChildRef != null) {
            final View view = this.mLastNestedScrollingChildRef.get();
            return view != null && view.isShown() && !ViewCompat.canScrollVertically(view, -1);
        }
        return true;
    }
    
    @Override
    int getMaxDragOffset(final AppBarLayout appBarLayout) {
        return -appBarLayout.getDownNestedScrollRange();
    }
    
    @Override
    int getScrollRangeForDragFling(final AppBarLayout appBarLayout) {
        return appBarLayout.getTotalScrollRange();
    }
    
    @Override
    int getTopBottomOffsetForScrollingSibling() {
        return this.getTopAndBottomOffset() + this.mOffsetDelta;
    }
    
    boolean isOffsetAnimatorRunning() {
        return this.mOffsetAnimator != null && this.mOffsetAnimator.isRunning();
    }
    
    @Override
    void onFlingFinished(final CoordinatorLayout coordinatorLayout, final AppBarLayout appBarLayout) {
        this.snapToChildIfNeeded(coordinatorLayout, appBarLayout);
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
                    this.animateOffsetTo(coordinatorLayout, appBarLayout, n, 0.0f);
                }
                else {
                    this.setHeaderTopBottomOffset(coordinatorLayout, appBarLayout, n);
                }
            }
            else if ((pendingAction & 0x1) != 0x0) {
                if (topAndBottomOffset != 0) {
                    this.animateOffsetTo(coordinatorLayout, appBarLayout, 0, 0.0f);
                }
                else {
                    this.setHeaderTopBottomOffset(coordinatorLayout, appBarLayout, 0);
                }
            }
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
        }
        appBarLayout.resetPendingAction();
        this.mOffsetToChildIndexOnLayout = -1;
        this.setTopAndBottomOffset(MathUtils.constrain(this.getTopAndBottomOffset(), -appBarLayout.getTotalScrollRange(), 0));
        appBarLayout.dispatchOffsetUpdates(this.getTopAndBottomOffset());
        return onLayoutChild;
    }
    
    @Override
    public boolean onMeasureChild(final CoordinatorLayout coordinatorLayout, final AppBarLayout appBarLayout, final int n, final int n2, final int n3, final int n4) {
        if (((CoordinatorLayout$LayoutParams)appBarLayout.getLayoutParams()).height == -2) {
            coordinatorLayout.onMeasureChild((View)appBarLayout, n, n2, View$MeasureSpec.makeMeasureSpec(0, 0), n4);
            return true;
        }
        return super.onMeasureChild(coordinatorLayout, appBarLayout, n, n2, n3, n4);
    }
    
    @Override
    public boolean onNestedFling(final CoordinatorLayout coordinatorLayout, final AppBarLayout appBarLayout, final View view, final float n, final float n2, final boolean b) {
        final boolean b2 = false;
        boolean fling;
        if (!b) {
            fling = this.fling(coordinatorLayout, appBarLayout, -appBarLayout.getTotalScrollRange(), 0, -n2);
        }
        else if (n2 < 0.0f) {
            final int n3 = -appBarLayout.getTotalScrollRange() + appBarLayout.getDownNestedPreScrollRange();
            fling = b2;
            if (this.getTopBottomOffsetForScrollingSibling() < n3) {
                this.animateOffsetTo(coordinatorLayout, appBarLayout, n3, n2);
                fling = true;
            }
        }
        else {
            final int n4 = -appBarLayout.getUpNestedPreScrollRange();
            fling = b2;
            if (this.getTopBottomOffsetForScrollingSibling() > n4) {
                this.animateOffsetTo(coordinatorLayout, appBarLayout, n4, n2);
                fling = true;
            }
        }
        return this.mWasNestedFlung = fling;
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
            this.mOffsetToChildIndexOnLayoutPerc = appBarLayout$Behavior$SavedState.firstVisibleChildPercentageShown;
            this.mOffsetToChildIndexOnLayoutIsMinHeight = appBarLayout$Behavior$SavedState.firstVisibleChildAtMinimumHeight;
            return;
        }
        super.onRestoreInstanceState(coordinatorLayout, appBarLayout, parcelable);
        this.mOffsetToChildIndexOnLayout = -1;
    }
    
    @Override
    public Parcelable onSaveInstanceState(final CoordinatorLayout coordinatorLayout, final AppBarLayout appBarLayout) {
        boolean firstVisibleChildAtMinimumHeight = false;
        final Parcelable onSaveInstanceState = super.onSaveInstanceState(coordinatorLayout, appBarLayout);
        final int topAndBottomOffset = this.getTopAndBottomOffset();
        for (int childCount = appBarLayout.getChildCount(), i = 0; i < childCount; ++i) {
            final View child = appBarLayout.getChildAt(i);
            final int n = child.getBottom() + topAndBottomOffset;
            if (child.getTop() + topAndBottomOffset <= 0 && n >= 0) {
                final AppBarLayout$Behavior$SavedState appBarLayout$Behavior$SavedState = new AppBarLayout$Behavior$SavedState(onSaveInstanceState);
                appBarLayout$Behavior$SavedState.firstVisibleChildIndex = i;
                if (n == ViewCompat.getMinimumHeight(child)) {
                    firstVisibleChildAtMinimumHeight = true;
                }
                appBarLayout$Behavior$SavedState.firstVisibleChildAtMinimumHeight = firstVisibleChildAtMinimumHeight;
                appBarLayout$Behavior$SavedState.firstVisibleChildPercentageShown = n / child.getHeight();
                return (Parcelable)appBarLayout$Behavior$SavedState;
            }
        }
        return onSaveInstanceState;
    }
    
    @Override
    public boolean onStartNestedScroll(final CoordinatorLayout coordinatorLayout, final AppBarLayout appBarLayout, final View view, final View view2, final int n) {
        final boolean b = (n & 0x2) != 0x0 && appBarLayout.hasScrollableChildren() && coordinatorLayout.getHeight() - view.getHeight() <= appBarLayout.getHeight();
        if (b && this.mOffsetAnimator != null) {
            this.mOffsetAnimator.cancel();
        }
        this.mLastNestedScrollingChildRef = null;
        return b;
    }
    
    @Override
    public void onStopNestedScroll(final CoordinatorLayout coordinatorLayout, final AppBarLayout appBarLayout, final View view) {
        if (!this.mWasNestedFlung) {
            this.snapToChildIfNeeded(coordinatorLayout, appBarLayout);
        }
        this.mSkipNestedPreScroll = false;
        this.mWasNestedFlung = false;
        this.mLastNestedScrollingChildRef = new WeakReference<View>(view);
    }
    
    public void setDragCallback(final AppBarLayout$Behavior$DragCallback mOnDragCallback) {
        this.mOnDragCallback = mOnDragCallback;
    }
    
    @Override
    int setHeaderTopBottomOffset(final CoordinatorLayout coordinatorLayout, final AppBarLayout appBarLayout, int interpolateOffset, int constrain, final int n) {
        final int n2 = 0;
        final int topBottomOffsetForScrollingSibling = this.getTopBottomOffsetForScrollingSibling();
        if (constrain != 0 && topBottomOffsetForScrollingSibling >= constrain && topBottomOffsetForScrollingSibling <= n) {
            constrain = MathUtils.constrain(interpolateOffset, constrain, n);
            interpolateOffset = n2;
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
                appBarLayout.dispatchOffsetUpdates(this.getTopAndBottomOffset());
                if (constrain < topBottomOffsetForScrollingSibling) {
                    interpolateOffset = -1;
                }
                else {
                    interpolateOffset = 1;
                }
                this.updateAppBarLayoutDrawableState(coordinatorLayout, appBarLayout, constrain, interpolateOffset);
                interpolateOffset = topBottomOffsetForScrollingSibling - constrain;
            }
            return interpolateOffset;
        }
        return this.mOffsetDelta = 0;
    }
}
