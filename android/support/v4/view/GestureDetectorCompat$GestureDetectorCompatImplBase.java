// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view;

import android.content.Context;
import android.view.ViewConfiguration;
import android.view.VelocityTracker;
import android.view.GestureDetector$OnGestureListener;
import android.os.Handler;
import android.view.GestureDetector$OnDoubleTapListener;
import android.view.MotionEvent;

class GestureDetectorCompat$GestureDetectorCompatImplBase implements GestureDetectorCompat$GestureDetectorCompatImpl
{
    private static final int DOUBLE_TAP_TIMEOUT;
    private static final int LONGPRESS_TIMEOUT;
    private static final int LONG_PRESS = 2;
    private static final int SHOW_PRESS = 1;
    private static final int TAP = 3;
    private static final int TAP_TIMEOUT;
    private boolean mAlwaysInBiggerTapRegion;
    private boolean mAlwaysInTapRegion;
    private MotionEvent mCurrentDownEvent;
    private boolean mDeferConfirmSingleTap;
    private GestureDetector$OnDoubleTapListener mDoubleTapListener;
    private int mDoubleTapSlopSquare;
    private float mDownFocusX;
    private float mDownFocusY;
    private final Handler mHandler;
    private boolean mInLongPress;
    private boolean mIsDoubleTapping;
    private boolean mIsLongpressEnabled;
    private float mLastFocusX;
    private float mLastFocusY;
    private final GestureDetector$OnGestureListener mListener;
    private int mMaximumFlingVelocity;
    private int mMinimumFlingVelocity;
    private MotionEvent mPreviousUpEvent;
    private boolean mStillDown;
    private int mTouchSlopSquare;
    private VelocityTracker mVelocityTracker;
    
    static {
        LONGPRESS_TIMEOUT = ViewConfiguration.getLongPressTimeout();
        TAP_TIMEOUT = ViewConfiguration.getTapTimeout();
        DOUBLE_TAP_TIMEOUT = ViewConfiguration.getDoubleTapTimeout();
    }
    
    public GestureDetectorCompat$GestureDetectorCompatImplBase(final Context context, final GestureDetector$OnGestureListener mListener, final Handler handler) {
        if (handler != null) {
            this.mHandler = new GestureDetectorCompat$GestureDetectorCompatImplBase$GestureHandler(this, handler);
        }
        else {
            this.mHandler = new GestureDetectorCompat$GestureDetectorCompatImplBase$GestureHandler(this);
        }
        this.mListener = mListener;
        if (mListener instanceof GestureDetector$OnDoubleTapListener) {
            this.setOnDoubleTapListener((GestureDetector$OnDoubleTapListener)mListener);
        }
        this.init(context);
    }
    
    private void cancel() {
        this.mHandler.removeMessages(1);
        this.mHandler.removeMessages(2);
        this.mHandler.removeMessages(3);
        this.mVelocityTracker.recycle();
        this.mVelocityTracker = null;
        this.mIsDoubleTapping = false;
        this.mStillDown = false;
        this.mAlwaysInTapRegion = false;
        this.mAlwaysInBiggerTapRegion = false;
        this.mDeferConfirmSingleTap = false;
        if (this.mInLongPress) {
            this.mInLongPress = false;
        }
    }
    
    private void cancelTaps() {
        this.mHandler.removeMessages(1);
        this.mHandler.removeMessages(2);
        this.mHandler.removeMessages(3);
        this.mIsDoubleTapping = false;
        this.mAlwaysInTapRegion = false;
        this.mAlwaysInBiggerTapRegion = false;
        this.mDeferConfirmSingleTap = false;
        if (this.mInLongPress) {
            this.mInLongPress = false;
        }
    }
    
    private void dispatchLongPress() {
        this.mHandler.removeMessages(3);
        this.mDeferConfirmSingleTap = false;
        this.mInLongPress = true;
        this.mListener.onLongPress(this.mCurrentDownEvent);
    }
    
    private void init(final Context context) {
        if (context == null) {
            throw new IllegalArgumentException("Context must not be null");
        }
        if (this.mListener == null) {
            throw new IllegalArgumentException("OnGestureListener must not be null");
        }
        this.mIsLongpressEnabled = true;
        final ViewConfiguration value = ViewConfiguration.get(context);
        final int scaledTouchSlop = value.getScaledTouchSlop();
        final int scaledDoubleTapSlop = value.getScaledDoubleTapSlop();
        this.mMinimumFlingVelocity = value.getScaledMinimumFlingVelocity();
        this.mMaximumFlingVelocity = value.getScaledMaximumFlingVelocity();
        this.mTouchSlopSquare = scaledTouchSlop * scaledTouchSlop;
        this.mDoubleTapSlopSquare = scaledDoubleTapSlop * scaledDoubleTapSlop;
    }
    
    private boolean isConsideredDoubleTap(final MotionEvent motionEvent, final MotionEvent motionEvent2, final MotionEvent motionEvent3) {
        if (this.mAlwaysInBiggerTapRegion && motionEvent3.getEventTime() - motionEvent2.getEventTime() <= GestureDetectorCompat$GestureDetectorCompatImplBase.DOUBLE_TAP_TIMEOUT) {
            final int n = (int)motionEvent.getX() - (int)motionEvent3.getX();
            final int n2 = (int)motionEvent.getY() - (int)motionEvent3.getY();
            if (n * n + n2 * n2 < this.mDoubleTapSlopSquare) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public boolean isLongpressEnabled() {
        return this.mIsLongpressEnabled;
    }
    
    @Override
    public boolean onTouchEvent(final MotionEvent motionEvent) {
        final int action = motionEvent.getAction();
        if (this.mVelocityTracker == null) {
            this.mVelocityTracker = VelocityTracker.obtain();
        }
        this.mVelocityTracker.addMovement(motionEvent);
        boolean b;
        if ((action & 0xFF) == 0x6) {
            b = true;
        }
        else {
            b = false;
        }
        int actionIndex;
        if (b) {
            actionIndex = MotionEventCompat.getActionIndex(motionEvent);
        }
        else {
            actionIndex = -1;
        }
        final int pointerCount = MotionEventCompat.getPointerCount(motionEvent);
        int i = 0;
        float n = 0.0f;
        float n2 = 0.0f;
        while (i < pointerCount) {
            if (actionIndex != i) {
                n2 += MotionEventCompat.getX(motionEvent, i);
                n += MotionEventCompat.getY(motionEvent, i);
            }
            ++i;
        }
        int n3;
        if (b) {
            n3 = pointerCount - 1;
        }
        else {
            n3 = pointerCount;
        }
        final float n4 = n2 / n3;
        final float n5 = n / n3;
        switch (action & 0xFF) {
            case 5: {
                this.mLastFocusX = n4;
                this.mDownFocusX = n4;
                this.mLastFocusY = n5;
                this.mDownFocusY = n5;
                this.cancelTaps();
                return false;
            }
            case 6: {
                this.mLastFocusX = n4;
                this.mDownFocusX = n4;
                this.mLastFocusY = n5;
                this.mDownFocusY = n5;
                this.mVelocityTracker.computeCurrentVelocity(1000, (float)this.mMaximumFlingVelocity);
                final int actionIndex2 = MotionEventCompat.getActionIndex(motionEvent);
                final int pointerId = MotionEventCompat.getPointerId(motionEvent, actionIndex2);
                final float xVelocity = VelocityTrackerCompat.getXVelocity(this.mVelocityTracker, pointerId);
                final float yVelocity = VelocityTrackerCompat.getYVelocity(this.mVelocityTracker, pointerId);
                for (int j = 0; j < pointerCount; ++j) {
                    if (j != actionIndex2) {
                        final int pointerId2 = MotionEventCompat.getPointerId(motionEvent, j);
                        if (VelocityTrackerCompat.getYVelocity(this.mVelocityTracker, pointerId2) * yVelocity + VelocityTrackerCompat.getXVelocity(this.mVelocityTracker, pointerId2) * xVelocity < 0.0f) {
                            this.mVelocityTracker.clear();
                            return false;
                        }
                    }
                }
                break;
            }
            case 0: {
                while (true) {
                    Label_0625: {
                        if (this.mDoubleTapListener == null) {
                            break Label_0625;
                        }
                        final boolean hasMessages = this.mHandler.hasMessages(3);
                        if (hasMessages) {
                            this.mHandler.removeMessages(3);
                        }
                        if (this.mCurrentDownEvent == null || this.mPreviousUpEvent == null || !hasMessages || !this.isConsideredDoubleTap(this.mCurrentDownEvent, this.mPreviousUpEvent, motionEvent)) {
                            this.mHandler.sendEmptyMessageDelayed(3, (long)GestureDetectorCompat$GestureDetectorCompatImplBase.DOUBLE_TAP_TIMEOUT);
                            break Label_0625;
                        }
                        this.mIsDoubleTapping = true;
                        final boolean b2 = this.mDoubleTapListener.onDoubleTap(this.mCurrentDownEvent) | false | this.mDoubleTapListener.onDoubleTapEvent(motionEvent);
                        this.mLastFocusX = n4;
                        this.mDownFocusX = n4;
                        this.mLastFocusY = n5;
                        this.mDownFocusY = n5;
                        if (this.mCurrentDownEvent != null) {
                            this.mCurrentDownEvent.recycle();
                        }
                        this.mCurrentDownEvent = MotionEvent.obtain(motionEvent);
                        this.mAlwaysInTapRegion = true;
                        this.mAlwaysInBiggerTapRegion = true;
                        this.mStillDown = true;
                        this.mInLongPress = false;
                        this.mDeferConfirmSingleTap = false;
                        if (this.mIsLongpressEnabled) {
                            this.mHandler.removeMessages(2);
                            this.mHandler.sendEmptyMessageAtTime(2, this.mCurrentDownEvent.getDownTime() + GestureDetectorCompat$GestureDetectorCompatImplBase.TAP_TIMEOUT + GestureDetectorCompat$GestureDetectorCompatImplBase.LONGPRESS_TIMEOUT);
                        }
                        this.mHandler.sendEmptyMessageAtTime(1, this.mCurrentDownEvent.getDownTime() + GestureDetectorCompat$GestureDetectorCompatImplBase.TAP_TIMEOUT);
                        return b2 | this.mListener.onDown(motionEvent);
                    }
                    final boolean b2 = false;
                    continue;
                }
            }
            case 2: {
                if (this.mInLongPress) {
                    break;
                }
                final float n6 = this.mLastFocusX - n4;
                final float n7 = this.mLastFocusY - n5;
                if (this.mIsDoubleTapping) {
                    return false | this.mDoubleTapListener.onDoubleTapEvent(motionEvent);
                }
                if (this.mAlwaysInTapRegion) {
                    final int n8 = (int)(n4 - this.mDownFocusX);
                    final int n9 = (int)(n5 - this.mDownFocusY);
                    final int n10 = n8 * n8 + n9 * n9;
                    boolean onScroll;
                    if (n10 > this.mTouchSlopSquare) {
                        onScroll = this.mListener.onScroll(this.mCurrentDownEvent, motionEvent, n6, n7);
                        this.mLastFocusX = n4;
                        this.mLastFocusY = n5;
                        this.mAlwaysInTapRegion = false;
                        this.mHandler.removeMessages(3);
                        this.mHandler.removeMessages(1);
                        this.mHandler.removeMessages(2);
                    }
                    else {
                        onScroll = false;
                    }
                    if (n10 > this.mTouchSlopSquare) {
                        this.mAlwaysInBiggerTapRegion = false;
                    }
                    return onScroll;
                }
                if (Math.abs(n6) >= 1.0f || Math.abs(n7) >= 1.0f) {
                    final boolean onScroll2 = this.mListener.onScroll(this.mCurrentDownEvent, motionEvent, n6, n7);
                    this.mLastFocusX = n4;
                    this.mLastFocusY = n5;
                    return onScroll2;
                }
                break;
            }
            case 1: {
                this.mStillDown = false;
                final MotionEvent obtain = MotionEvent.obtain(motionEvent);
                boolean onSingleTapUp;
                if (this.mIsDoubleTapping) {
                    onSingleTapUp = (this.mDoubleTapListener.onDoubleTapEvent(motionEvent) | false);
                }
                else if (this.mInLongPress) {
                    this.mHandler.removeMessages(3);
                    this.mInLongPress = false;
                    onSingleTapUp = false;
                }
                else if (this.mAlwaysInTapRegion) {
                    final boolean b3 = onSingleTapUp = this.mListener.onSingleTapUp(motionEvent);
                    if (this.mDeferConfirmSingleTap) {
                        onSingleTapUp = b3;
                        if (this.mDoubleTapListener != null) {
                            this.mDoubleTapListener.onSingleTapConfirmed(motionEvent);
                            onSingleTapUp = b3;
                        }
                    }
                }
                else {
                    final VelocityTracker mVelocityTracker = this.mVelocityTracker;
                    final int pointerId3 = MotionEventCompat.getPointerId(motionEvent, 0);
                    mVelocityTracker.computeCurrentVelocity(1000, (float)this.mMaximumFlingVelocity);
                    final float yVelocity2 = VelocityTrackerCompat.getYVelocity(mVelocityTracker, pointerId3);
                    final float xVelocity2 = VelocityTrackerCompat.getXVelocity(mVelocityTracker, pointerId3);
                    onSingleTapUp = ((Math.abs(yVelocity2) > this.mMinimumFlingVelocity || Math.abs(xVelocity2) > this.mMinimumFlingVelocity) && this.mListener.onFling(this.mCurrentDownEvent, motionEvent, xVelocity2, yVelocity2));
                }
                if (this.mPreviousUpEvent != null) {
                    this.mPreviousUpEvent.recycle();
                }
                this.mPreviousUpEvent = obtain;
                if (this.mVelocityTracker != null) {
                    this.mVelocityTracker.recycle();
                    this.mVelocityTracker = null;
                }
                this.mIsDoubleTapping = false;
                this.mDeferConfirmSingleTap = false;
                this.mHandler.removeMessages(1);
                this.mHandler.removeMessages(2);
                return onSingleTapUp;
            }
            case 3: {
                this.cancel();
                return false;
            }
        }
        return false;
    }
    
    @Override
    public void setIsLongpressEnabled(final boolean mIsLongpressEnabled) {
        this.mIsLongpressEnabled = mIsLongpressEnabled;
    }
    
    @Override
    public void setOnDoubleTapListener(final GestureDetector$OnDoubleTapListener mDoubleTapListener) {
        this.mDoubleTapListener = mDoubleTapListener;
    }
}
