// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.drawee.gestures;

import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.content.Context;

public class GestureDetector
{
    long mActionDownTime;
    float mActionDownX;
    float mActionDownY;
    GestureDetector$ClickListener mClickListener;
    boolean mIsCapturingGesture;
    boolean mIsClickCandidate;
    final float mSingleTapSlopPx;
    
    public GestureDetector(final Context context) {
        this.mSingleTapSlopPx = ViewConfiguration.get(context).getScaledTouchSlop();
        this.init();
    }
    
    public static GestureDetector newInstance(final Context context) {
        return new GestureDetector(context);
    }
    
    public void init() {
        this.mClickListener = null;
        this.reset();
    }
    
    public boolean isCapturingGesture() {
        return this.mIsCapturingGesture;
    }
    
    public boolean onTouchEvent(final MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case 0: {
                this.mIsCapturingGesture = true;
                this.mIsClickCandidate = true;
                this.mActionDownTime = motionEvent.getEventTime();
                this.mActionDownX = motionEvent.getX();
                this.mActionDownY = motionEvent.getY();
                return true;
            }
            case 2: {
                if (Math.abs(motionEvent.getX() - this.mActionDownX) > this.mSingleTapSlopPx || Math.abs(motionEvent.getY() - this.mActionDownY) > this.mSingleTapSlopPx) {
                    this.mIsClickCandidate = false;
                    return true;
                }
                break;
            }
            case 3: {
                this.mIsCapturingGesture = false;
                this.mIsClickCandidate = false;
                return true;
            }
            case 1: {
                this.mIsCapturingGesture = false;
                if (Math.abs(motionEvent.getX() - this.mActionDownX) > this.mSingleTapSlopPx || Math.abs(motionEvent.getY() - this.mActionDownY) > this.mSingleTapSlopPx) {
                    this.mIsClickCandidate = false;
                }
                if (this.mIsClickCandidate && motionEvent.getEventTime() - this.mActionDownTime <= ViewConfiguration.getLongPressTimeout() && this.mClickListener != null) {
                    this.mClickListener.onClick();
                }
                this.mIsClickCandidate = false;
                return true;
            }
        }
        return true;
    }
    
    public void reset() {
        this.mIsCapturingGesture = false;
        this.mIsClickCandidate = false;
    }
    
    public void setClickListener(final GestureDetector$ClickListener mClickListener) {
        this.mClickListener = mClickListener;
    }
}
