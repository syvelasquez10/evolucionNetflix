// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.support.v4.widget.ViewDragHelper;
import android.support.v4.widget.ViewDragHelper$Callback;
import android.view.View;

public class SwipeDismissBehavior<V extends View> extends CoordinatorLayout$Behavior<V>
{
    private static final float DEFAULT_ALPHA_END_DISTANCE = 0.5f;
    private static final float DEFAULT_ALPHA_START_DISTANCE = 0.0f;
    private static final float DEFAULT_DRAG_DISMISS_THRESHOLD = 0.5f;
    public static final int STATE_DRAGGING = 1;
    public static final int STATE_IDLE = 0;
    public static final int STATE_SETTLING = 2;
    public static final int SWIPE_DIRECTION_ANY = 2;
    public static final int SWIPE_DIRECTION_END_TO_START = 1;
    public static final int SWIPE_DIRECTION_START_TO_END = 0;
    float mAlphaEndSwipeDistance;
    float mAlphaStartSwipeDistance;
    private final ViewDragHelper$Callback mDragCallback;
    float mDragDismissThreshold;
    private boolean mInterceptingEvents;
    SwipeDismissBehavior$OnDismissListener mListener;
    private float mSensitivity;
    private boolean mSensitivitySet;
    int mSwipeDirection;
    ViewDragHelper mViewDragHelper;
    
    public SwipeDismissBehavior() {
        this.mSensitivity = 0.0f;
        this.mSwipeDirection = 2;
        this.mDragDismissThreshold = 0.5f;
        this.mAlphaStartSwipeDistance = 0.0f;
        this.mAlphaEndSwipeDistance = 0.5f;
        this.mDragCallback = new SwipeDismissBehavior$1(this);
    }
    
    static float clamp(final float n, final float n2, final float n3) {
        return Math.min(Math.max(n, n2), n3);
    }
    
    static int clamp(final int n, final int n2, final int n3) {
        return Math.min(Math.max(n, n2), n3);
    }
    
    private void ensureViewDragHelper(final ViewGroup viewGroup) {
        if (this.mViewDragHelper == null) {
            ViewDragHelper mViewDragHelper;
            if (this.mSensitivitySet) {
                mViewDragHelper = ViewDragHelper.create(viewGroup, this.mSensitivity, this.mDragCallback);
            }
            else {
                mViewDragHelper = ViewDragHelper.create(viewGroup, this.mDragCallback);
            }
            this.mViewDragHelper = mViewDragHelper;
        }
    }
    
    static float fraction(final float n, final float n2, final float n3) {
        return (n3 - n) / (n2 - n);
    }
    
    public boolean canSwipeDismissView(final View view) {
        return true;
    }
    
    public int getDragState() {
        if (this.mViewDragHelper != null) {
            return this.mViewDragHelper.getViewDragState();
        }
        return 0;
    }
    
    @Override
    public boolean onInterceptTouchEvent(final CoordinatorLayout coordinatorLayout, final V v, final MotionEvent motionEvent) {
        boolean shouldInterceptTouchEvent = false;
        boolean b2;
        final boolean b = b2 = this.mInterceptingEvents;
        Label_0052: {
            switch (MotionEventCompat.getActionMasked(motionEvent)) {
                default: {
                    b2 = b;
                    break Label_0052;
                }
                case 1:
                case 3: {
                    this.mInterceptingEvents = false;
                    b2 = b;
                    break Label_0052;
                }
                case 0: {
                    this.mInterceptingEvents = coordinatorLayout.isPointInChildBounds(v, (int)motionEvent.getX(), (int)motionEvent.getY());
                    b2 = this.mInterceptingEvents;
                }
                case 2: {
                    if (b2) {
                        this.ensureViewDragHelper(coordinatorLayout);
                        shouldInterceptTouchEvent = this.mViewDragHelper.shouldInterceptTouchEvent(motionEvent);
                    }
                    return shouldInterceptTouchEvent;
                }
            }
        }
    }
    
    @Override
    public boolean onTouchEvent(final CoordinatorLayout coordinatorLayout, final V v, final MotionEvent motionEvent) {
        if (this.mViewDragHelper != null) {
            this.mViewDragHelper.processTouchEvent(motionEvent);
            return true;
        }
        return false;
    }
    
    public void setDragDismissDistance(final float n) {
        this.mDragDismissThreshold = clamp(0.0f, n, 1.0f);
    }
    
    public void setEndAlphaSwipeDistance(final float n) {
        this.mAlphaEndSwipeDistance = clamp(0.0f, n, 1.0f);
    }
    
    public void setListener(final SwipeDismissBehavior$OnDismissListener mListener) {
        this.mListener = mListener;
    }
    
    public void setSensitivity(final float mSensitivity) {
        this.mSensitivity = mSensitivity;
        this.mSensitivitySet = true;
    }
    
    public void setStartAlphaSwipeDistance(final float n) {
        this.mAlphaStartSwipeDistance = clamp(0.0f, n, 1.0f);
    }
    
    public void setSwipeDirection(final int mSwipeDirection) {
        this.mSwipeDirection = mSwipeDirection;
    }
}
