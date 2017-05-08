// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

import android.view.ViewParent;
import android.os.Parcelable;
import android.support.design.R$dimen;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;
import android.support.v4.view.VelocityTrackerCompat;
import android.view.ViewGroup$LayoutParams;
import android.view.ViewGroup;
import android.support.v4.view.NestedScrollingChild;
import android.util.TypedValue;
import android.content.res.TypedArray;
import android.view.ViewConfiguration;
import android.support.design.R$styleable;
import android.util.AttributeSet;
import android.content.Context;
import android.support.v4.widget.ViewDragHelper;
import android.view.VelocityTracker;
import java.lang.ref.WeakReference;
import android.support.v4.widget.ViewDragHelper$Callback;
import android.view.View;

public class BottomSheetBehavior<V extends View> extends CoordinatorLayout$Behavior<V>
{
    private static final float HIDE_FRICTION = 0.1f;
    private static final float HIDE_THRESHOLD = 0.5f;
    public static final int PEEK_HEIGHT_AUTO = -1;
    public static final int STATE_COLLAPSED = 4;
    public static final int STATE_DRAGGING = 1;
    public static final int STATE_EXPANDED = 3;
    public static final int STATE_HIDDEN = 5;
    public static final int STATE_SETTLING = 2;
    int mActivePointerId;
    private BottomSheetBehavior$BottomSheetCallback mCallback;
    private final ViewDragHelper$Callback mDragCallback;
    boolean mHideable;
    private boolean mIgnoreEvents;
    private int mInitialY;
    private int mLastNestedScrollDy;
    int mMaxOffset;
    private float mMaximumVelocity;
    int mMinOffset;
    private boolean mNestedScrolled;
    WeakReference<View> mNestedScrollingChildRef;
    int mParentHeight;
    private int mPeekHeight;
    private boolean mPeekHeightAuto;
    private int mPeekHeightMin;
    private boolean mSkipCollapsed;
    int mState;
    boolean mTouchingScrollingChild;
    private VelocityTracker mVelocityTracker;
    ViewDragHelper mViewDragHelper;
    WeakReference<V> mViewRef;
    
    public BottomSheetBehavior() {
        this.mState = 4;
        this.mDragCallback = new BottomSheetBehavior$2(this);
    }
    
    public BottomSheetBehavior(final Context context, final AttributeSet set) {
        super(context, set);
        this.mState = 4;
        this.mDragCallback = new BottomSheetBehavior$2(this);
        final TypedArray obtainStyledAttributes = context.obtainStyledAttributes(set, R$styleable.BottomSheetBehavior_Layout);
        final TypedValue peekValue = obtainStyledAttributes.peekValue(R$styleable.BottomSheetBehavior_Layout_behavior_peekHeight);
        if (peekValue != null && peekValue.data == -1) {
            this.setPeekHeight(peekValue.data);
        }
        else {
            this.setPeekHeight(obtainStyledAttributes.getDimensionPixelSize(R$styleable.BottomSheetBehavior_Layout_behavior_peekHeight, -1));
        }
        this.setHideable(obtainStyledAttributes.getBoolean(R$styleable.BottomSheetBehavior_Layout_behavior_hideable, false));
        this.setSkipCollapsed(obtainStyledAttributes.getBoolean(R$styleable.BottomSheetBehavior_Layout_behavior_skipCollapsed, false));
        obtainStyledAttributes.recycle();
        this.mMaximumVelocity = ViewConfiguration.get(context).getScaledMaximumFlingVelocity();
    }
    
    private View findScrollingChild(final View view) {
        if (view instanceof NestedScrollingChild) {
            return view;
        }
        if (view instanceof ViewGroup) {
            final ViewGroup viewGroup = (ViewGroup)view;
            for (int childCount = viewGroup.getChildCount(), i = 0; i < childCount; ++i) {
                final View scrollingChild = this.findScrollingChild(viewGroup.getChildAt(i));
                if (scrollingChild != null) {
                    return scrollingChild;
                }
            }
        }
        return null;
    }
    
    public static <V extends View> BottomSheetBehavior<V> from(final V v) {
        final ViewGroup$LayoutParams layoutParams = v.getLayoutParams();
        if (!(layoutParams instanceof CoordinatorLayout$LayoutParams)) {
            throw new IllegalArgumentException("The view is not a child of CoordinatorLayout");
        }
        final CoordinatorLayout$Behavior behavior = ((CoordinatorLayout$LayoutParams)layoutParams).getBehavior();
        if (!(behavior instanceof BottomSheetBehavior)) {
            throw new IllegalArgumentException("The view is not associated with BottomSheetBehavior");
        }
        return (BottomSheetBehavior<V>)behavior;
    }
    
    private float getYVelocity() {
        this.mVelocityTracker.computeCurrentVelocity(1000, this.mMaximumVelocity);
        return VelocityTrackerCompat.getYVelocity(this.mVelocityTracker, this.mActivePointerId);
    }
    
    private void reset() {
        this.mActivePointerId = -1;
        if (this.mVelocityTracker != null) {
            this.mVelocityTracker.recycle();
            this.mVelocityTracker = null;
        }
    }
    
    void dispatchOnSlide(final int n) {
        final View view = this.mViewRef.get();
        if (view != null && this.mCallback != null) {
            if (n <= this.mMaxOffset) {
                this.mCallback.onSlide(view, (this.mMaxOffset - n) / (this.mMaxOffset - this.mMinOffset));
                return;
            }
            this.mCallback.onSlide(view, (this.mMaxOffset - n) / (this.mParentHeight - this.mMaxOffset));
        }
    }
    
    public final int getPeekHeight() {
        if (this.mPeekHeightAuto) {
            return -1;
        }
        return this.mPeekHeight;
    }
    
    int getPeekHeightMin() {
        return this.mPeekHeightMin;
    }
    
    public boolean getSkipCollapsed() {
        return this.mSkipCollapsed;
    }
    
    public final int getState() {
        return this.mState;
    }
    
    public boolean isHideable() {
        return this.mHideable;
    }
    
    @Override
    public boolean onInterceptTouchEvent(final CoordinatorLayout coordinatorLayout, final V v, final MotionEvent motionEvent) {
        final boolean b = true;
        if (!v.isShown()) {
            this.mIgnoreEvents = true;
            return false;
        }
        final int actionMasked = MotionEventCompat.getActionMasked(motionEvent);
        if (actionMasked == 0) {
            this.reset();
        }
        if (this.mVelocityTracker == null) {
            this.mVelocityTracker = VelocityTracker.obtain();
        }
        this.mVelocityTracker.addMovement(motionEvent);
        switch (actionMasked) {
            case 1:
            case 3: {
                this.mTouchingScrollingChild = false;
                this.mActivePointerId = -1;
                if (this.mIgnoreEvents) {
                    return this.mIgnoreEvents = false;
                }
                break;
            }
            case 0: {
                final int n = (int)motionEvent.getX();
                this.mInitialY = (int)motionEvent.getY();
                final View view = this.mNestedScrollingChildRef.get();
                if (view != null && coordinatorLayout.isPointInChildBounds(view, n, this.mInitialY)) {
                    this.mActivePointerId = motionEvent.getPointerId(motionEvent.getActionIndex());
                    this.mTouchingScrollingChild = true;
                }
                this.mIgnoreEvents = (this.mActivePointerId == -1 && !coordinatorLayout.isPointInChildBounds(v, n, this.mInitialY));
                break;
            }
        }
        if (!this.mIgnoreEvents && this.mViewDragHelper.shouldInterceptTouchEvent(motionEvent)) {
            return true;
        }
        final View view2 = this.mNestedScrollingChildRef.get();
        return actionMasked == 2 && view2 != null && !this.mIgnoreEvents && this.mState != 1 && !coordinatorLayout.isPointInChildBounds(view2, (int)motionEvent.getX(), (int)motionEvent.getY()) && Math.abs(this.mInitialY - motionEvent.getY()) > this.mViewDragHelper.getTouchSlop() && b;
    }
    
    @Override
    public boolean onLayoutChild(final CoordinatorLayout coordinatorLayout, final V v, int n) {
        if (ViewCompat.getFitsSystemWindows((View)coordinatorLayout) && !ViewCompat.getFitsSystemWindows(v)) {
            ViewCompat.setFitsSystemWindows(v, true);
        }
        final int top = v.getTop();
        coordinatorLayout.onLayoutChild(v, n);
        this.mParentHeight = coordinatorLayout.getHeight();
        if (this.mPeekHeightAuto) {
            if (this.mPeekHeightMin == 0) {
                this.mPeekHeightMin = coordinatorLayout.getResources().getDimensionPixelSize(R$dimen.design_bottom_sheet_peek_height_min);
            }
            n = Math.max(this.mPeekHeightMin, this.mParentHeight - coordinatorLayout.getWidth() * 9 / 16);
        }
        else {
            n = this.mPeekHeight;
        }
        this.mMinOffset = Math.max(0, this.mParentHeight - v.getHeight());
        this.mMaxOffset = Math.max(this.mParentHeight - n, this.mMinOffset);
        if (this.mState == 3) {
            ViewCompat.offsetTopAndBottom(v, this.mMinOffset);
        }
        else if (this.mHideable && this.mState == 5) {
            ViewCompat.offsetTopAndBottom(v, this.mParentHeight);
        }
        else if (this.mState == 4) {
            ViewCompat.offsetTopAndBottom(v, this.mMaxOffset);
        }
        else if (this.mState == 1 || this.mState == 2) {
            ViewCompat.offsetTopAndBottom(v, top - v.getTop());
        }
        if (this.mViewDragHelper == null) {
            this.mViewDragHelper = ViewDragHelper.create(coordinatorLayout, this.mDragCallback);
        }
        this.mViewRef = new WeakReference<V>(v);
        this.mNestedScrollingChildRef = new WeakReference<View>(this.findScrollingChild(v));
        return true;
    }
    
    @Override
    public boolean onNestedPreFling(final CoordinatorLayout coordinatorLayout, final V v, final View view, final float n, final float n2) {
        return view == this.mNestedScrollingChildRef.get() && (this.mState != 3 || super.onNestedPreFling(coordinatorLayout, v, view, n, n2));
    }
    
    @Override
    public void onNestedPreScroll(final CoordinatorLayout coordinatorLayout, final V v, final View view, int top, final int mLastNestedScrollDy, final int[] array) {
        if (view != this.mNestedScrollingChildRef.get()) {
            return;
        }
        top = v.getTop();
        final int n = top - mLastNestedScrollDy;
        if (mLastNestedScrollDy > 0) {
            if (n < this.mMinOffset) {
                array[1] = top - this.mMinOffset;
                ViewCompat.offsetTopAndBottom(v, -array[1]);
                this.setStateInternal(3);
            }
            else {
                ViewCompat.offsetTopAndBottom(v, -(array[1] = mLastNestedScrollDy));
                this.setStateInternal(1);
            }
        }
        else if (mLastNestedScrollDy < 0 && !ViewCompat.canScrollVertically(view, -1)) {
            if (n <= this.mMaxOffset || this.mHideable) {
                ViewCompat.offsetTopAndBottom(v, -(array[1] = mLastNestedScrollDy));
                this.setStateInternal(1);
            }
            else {
                array[1] = top - this.mMaxOffset;
                ViewCompat.offsetTopAndBottom(v, -array[1]);
                this.setStateInternal(4);
            }
        }
        this.dispatchOnSlide(v.getTop());
        this.mLastNestedScrollDy = mLastNestedScrollDy;
        this.mNestedScrolled = true;
    }
    
    @Override
    public void onRestoreInstanceState(final CoordinatorLayout coordinatorLayout, final V v, final Parcelable parcelable) {
        final BottomSheetBehavior$SavedState bottomSheetBehavior$SavedState = (BottomSheetBehavior$SavedState)parcelable;
        super.onRestoreInstanceState(coordinatorLayout, v, bottomSheetBehavior$SavedState.getSuperState());
        if (bottomSheetBehavior$SavedState.state == 1 || bottomSheetBehavior$SavedState.state == 2) {
            this.mState = 4;
            return;
        }
        this.mState = bottomSheetBehavior$SavedState.state;
    }
    
    @Override
    public Parcelable onSaveInstanceState(final CoordinatorLayout coordinatorLayout, final V v) {
        return (Parcelable)new BottomSheetBehavior$SavedState(super.onSaveInstanceState(coordinatorLayout, v), this.mState);
    }
    
    @Override
    public boolean onStartNestedScroll(final CoordinatorLayout coordinatorLayout, final V v, final View view, final View view2, final int n) {
        boolean b = false;
        this.mLastNestedScrollDy = 0;
        this.mNestedScrolled = false;
        if ((n & 0x2) != 0x0) {
            b = true;
        }
        return b;
    }
    
    @Override
    public void onStopNestedScroll(final CoordinatorLayout coordinatorLayout, final V v, final View view) {
        int stateInternal = 3;
        if (v.getTop() == this.mMinOffset) {
            this.setStateInternal(3);
        }
        else if (view == this.mNestedScrollingChildRef.get() && this.mNestedScrolled) {
            int n;
            if (this.mLastNestedScrollDy > 0) {
                n = this.mMinOffset;
            }
            else if (this.mHideable && this.shouldHide(v, this.getYVelocity())) {
                n = this.mParentHeight;
                stateInternal = 5;
            }
            else if (this.mLastNestedScrollDy == 0) {
                final int top = v.getTop();
                if (Math.abs(top - this.mMinOffset) < Math.abs(top - this.mMaxOffset)) {
                    n = this.mMinOffset;
                }
                else {
                    n = this.mMaxOffset;
                    stateInternal = 4;
                }
            }
            else {
                n = this.mMaxOffset;
                stateInternal = 4;
            }
            if (this.mViewDragHelper.smoothSlideViewTo(v, v.getLeft(), n)) {
                this.setStateInternal(2);
                ViewCompat.postOnAnimation(v, new BottomSheetBehavior$SettleRunnable(this, v, stateInternal));
            }
            else {
                this.setStateInternal(stateInternal);
            }
            this.mNestedScrolled = false;
        }
    }
    
    @Override
    public boolean onTouchEvent(final CoordinatorLayout coordinatorLayout, final V v, final MotionEvent motionEvent) {
        final boolean b = true;
        boolean b2;
        if (!v.isShown()) {
            b2 = false;
        }
        else {
            final int actionMasked = MotionEventCompat.getActionMasked(motionEvent);
            if (this.mState == 1) {
                b2 = b;
                if (actionMasked == 0) {
                    return b2;
                }
            }
            this.mViewDragHelper.processTouchEvent(motionEvent);
            if (actionMasked == 0) {
                this.reset();
            }
            if (this.mVelocityTracker == null) {
                this.mVelocityTracker = VelocityTracker.obtain();
            }
            this.mVelocityTracker.addMovement(motionEvent);
            if (actionMasked == 2 && !this.mIgnoreEvents && Math.abs(this.mInitialY - motionEvent.getY()) > this.mViewDragHelper.getTouchSlop()) {
                this.mViewDragHelper.captureChildView(v, motionEvent.getPointerId(motionEvent.getActionIndex()));
            }
            b2 = b;
            if (this.mIgnoreEvents) {
                return false;
            }
        }
        return b2;
    }
    
    public void setBottomSheetCallback(final BottomSheetBehavior$BottomSheetCallback mCallback) {
        this.mCallback = mCallback;
    }
    
    public void setHideable(final boolean mHideable) {
        this.mHideable = mHideable;
    }
    
    public final void setPeekHeight(int n) {
        final int n2 = 1;
        while (true) {
            Label_0104: {
                if (n == -1) {
                    if (this.mPeekHeightAuto) {
                        break Label_0104;
                    }
                    this.mPeekHeightAuto = true;
                    n = n2;
                }
                else {
                    if (!this.mPeekHeightAuto && this.mPeekHeight == n) {
                        break Label_0104;
                    }
                    this.mPeekHeightAuto = false;
                    this.mPeekHeight = Math.max(0, n);
                    this.mMaxOffset = this.mParentHeight - n;
                    n = n2;
                }
                if (n != 0 && this.mState == 4 && this.mViewRef != null) {
                    final View view = this.mViewRef.get();
                    if (view != null) {
                        view.requestLayout();
                    }
                }
                return;
            }
            n = 0;
            continue;
        }
    }
    
    public void setSkipCollapsed(final boolean mSkipCollapsed) {
        this.mSkipCollapsed = mSkipCollapsed;
    }
    
    public final void setState(final int mState) {
        if (mState != this.mState) {
            if (this.mViewRef == null) {
                if (mState == 4 || mState == 3 || (this.mHideable && mState == 5)) {
                    this.mState = mState;
                }
            }
            else {
                final View view = this.mViewRef.get();
                if (view != null) {
                    final ViewParent parent = view.getParent();
                    if (parent != null && parent.isLayoutRequested() && ViewCompat.isAttachedToWindow(view)) {
                        view.post((Runnable)new BottomSheetBehavior$1(this, view, mState));
                        return;
                    }
                    this.startSettlingAnimation(view, mState);
                }
            }
        }
    }
    
    void setStateInternal(final int mState) {
        if (this.mState != mState) {
            this.mState = mState;
            final View view = this.mViewRef.get();
            if (view != null && this.mCallback != null) {
                this.mCallback.onStateChanged(view, mState);
            }
        }
    }
    
    boolean shouldHide(final View view, final float n) {
        if (!this.mSkipCollapsed) {
            if (view.getTop() < this.mMaxOffset) {
                return false;
            }
            if (Math.abs(view.getTop() + 0.1f * n - this.mMaxOffset) / this.mPeekHeight <= 0.5f) {
                return false;
            }
        }
        return true;
    }
    
    void startSettlingAnimation(final View view, final int n) {
        int n2;
        if (n == 4) {
            n2 = this.mMaxOffset;
        }
        else if (n == 3) {
            n2 = this.mMinOffset;
        }
        else {
            if (!this.mHideable || n != 5) {
                throw new IllegalArgumentException("Illegal state argument: " + n);
            }
            n2 = this.mParentHeight;
        }
        this.setStateInternal(2);
        if (this.mViewDragHelper.smoothSlideViewTo(view, view.getLeft(), n2)) {
            ViewCompat.postOnAnimation(view, new BottomSheetBehavior$SettleRunnable(this, view, n));
        }
    }
}
