// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.uimanager;

import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.TouchEvent;
import com.facebook.react.uimanager.events.TouchEventType;
import com.facebook.infer.annotation.Assertions;
import com.facebook.common.logging.FLog;
import com.facebook.react.uimanager.events.EventDispatcher;
import android.view.MotionEvent;
import com.facebook.react.uimanager.events.TouchEventCoalescingKeyHelper;
import android.view.ViewGroup;

public class JSTouchDispatcher
{
    private boolean mChildIsHandlingNativeGesture;
    private final ViewGroup mRootViewGroup;
    private final float[] mTargetCoordinates;
    private int mTargetTag;
    private final TouchEventCoalescingKeyHelper mTouchEventCoalescingKeyHelper;
    
    public JSTouchDispatcher(final ViewGroup mRootViewGroup) {
        this.mTargetTag = -1;
        this.mTargetCoordinates = new float[2];
        this.mChildIsHandlingNativeGesture = false;
        this.mTouchEventCoalescingKeyHelper = new TouchEventCoalescingKeyHelper();
        this.mRootViewGroup = mRootViewGroup;
    }
    
    private void dispatchCancelEvent(final MotionEvent motionEvent, final EventDispatcher eventDispatcher) {
        if (this.mTargetTag == -1) {
            FLog.w("React", "Can't cancel already finished gesture. Is a child View trying to start a gesture from an UP/CANCEL event?");
            return;
        }
        Assertions.assertCondition(!this.mChildIsHandlingNativeGesture, "Expected to not have already sent a cancel for this gesture");
        Assertions.assertNotNull(eventDispatcher).dispatchEvent(TouchEvent.obtain(this.mTargetTag, TouchEventType.CANCEL, motionEvent, this.mTargetCoordinates[0], this.mTargetCoordinates[1], this.mTouchEventCoalescingKeyHelper));
    }
    
    public void handleTouchEvent(final MotionEvent motionEvent, final EventDispatcher eventDispatcher) {
        final int n = motionEvent.getAction() & 0xFF;
        if (n == 0) {
            if (this.mTargetTag != -1) {
                FLog.e("React", "Got DOWN touch before receiving UP or CANCEL from last gesture");
            }
            this.mChildIsHandlingNativeGesture = false;
            this.mTargetTag = TouchTargetHelper.findTargetTagAndCoordinatesForTouch(motionEvent.getX(), motionEvent.getY(), this.mRootViewGroup, this.mTargetCoordinates, null);
            eventDispatcher.dispatchEvent(TouchEvent.obtain(this.mTargetTag, TouchEventType.START, motionEvent, this.mTargetCoordinates[0], this.mTargetCoordinates[1], this.mTouchEventCoalescingKeyHelper));
        }
        else if (!this.mChildIsHandlingNativeGesture) {
            if (this.mTargetTag == -1) {
                FLog.e("React", "Unexpected state: received touch event but didn't get starting ACTION_DOWN for this gesture before");
                return;
            }
            if (n == 1) {
                eventDispatcher.dispatchEvent(TouchEvent.obtain(this.mTargetTag, TouchEventType.END, motionEvent, this.mTargetCoordinates[0], this.mTargetCoordinates[1], this.mTouchEventCoalescingKeyHelper));
                this.mTargetTag = -1;
                return;
            }
            if (n == 2) {
                eventDispatcher.dispatchEvent(TouchEvent.obtain(this.mTargetTag, TouchEventType.MOVE, motionEvent, this.mTargetCoordinates[0], this.mTargetCoordinates[1], this.mTouchEventCoalescingKeyHelper));
                return;
            }
            if (n == 5) {
                eventDispatcher.dispatchEvent(TouchEvent.obtain(this.mTargetTag, TouchEventType.START, motionEvent, this.mTargetCoordinates[0], this.mTargetCoordinates[1], this.mTouchEventCoalescingKeyHelper));
                return;
            }
            if (n == 6) {
                eventDispatcher.dispatchEvent(TouchEvent.obtain(this.mTargetTag, TouchEventType.END, motionEvent, this.mTargetCoordinates[0], this.mTargetCoordinates[1], this.mTouchEventCoalescingKeyHelper));
                return;
            }
            if (n == 3) {
                if (this.mTouchEventCoalescingKeyHelper.hasCoalescingKey(motionEvent.getDownTime())) {
                    this.dispatchCancelEvent(motionEvent, eventDispatcher);
                }
                else {
                    FLog.e("React", "Received an ACTION_CANCEL touch event for which we have no corresponding ACTION_DOWN");
                }
                this.mTargetTag = -1;
                return;
            }
            FLog.w("React", "Warning : touch event was ignored. Action=" + n + " Target=" + this.mTargetTag);
        }
    }
    
    public void onChildStartedNativeGesture(final MotionEvent motionEvent, final EventDispatcher eventDispatcher) {
        if (this.mChildIsHandlingNativeGesture) {
            return;
        }
        this.dispatchCancelEvent(motionEvent, eventDispatcher);
        this.mChildIsHandlingNativeGesture = true;
        this.mTargetTag = -1;
    }
}
