// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.views.swiperefresh;

import com.facebook.react.uimanager.PixelUtil;
import android.view.View;
import com.facebook.react.uimanager.events.NativeGestureUtil;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.content.Context;
import com.facebook.react.bridge.ReactContext;
import android.support.v4.widget.SwipeRefreshLayout;

public class ReactSwipeRefreshLayout extends SwipeRefreshLayout
{
    private boolean mDidLayout;
    private boolean mIntercepted;
    private float mPrevTouchX;
    private float mProgressViewOffset;
    private boolean mRefreshing;
    private int mTouchSlop;
    
    public ReactSwipeRefreshLayout(final ReactContext reactContext) {
        super((Context)reactContext);
        this.mDidLayout = false;
        this.mRefreshing = false;
        this.mProgressViewOffset = 0.0f;
        this.mTouchSlop = ViewConfiguration.get((Context)reactContext).getScaledTouchSlop();
    }
    
    private boolean shouldInterceptTouchEvent(final MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case 0: {
                this.mPrevTouchX = motionEvent.getX();
                this.mIntercepted = false;
                break;
            }
            case 2: {
                final float abs = Math.abs(motionEvent.getX() - this.mPrevTouchX);
                if (this.mIntercepted || abs > this.mTouchSlop) {
                    this.mIntercepted = true;
                    return false;
                }
                break;
            }
        }
        return true;
    }
    
    @Override
    public boolean onInterceptTouchEvent(final MotionEvent motionEvent) {
        if (this.shouldInterceptTouchEvent(motionEvent) && super.onInterceptTouchEvent(motionEvent)) {
            NativeGestureUtil.notifyNativeGestureStarted((View)this, motionEvent);
            return true;
        }
        return false;
    }
    
    public void onLayout(final boolean b, final int n, final int n2, final int n3, final int n4) {
        super.onLayout(b, n, n2, n3, n4);
        if (!this.mDidLayout) {
            this.mDidLayout = true;
            this.setProgressViewOffset(this.mProgressViewOffset);
            this.setRefreshing(this.mRefreshing);
        }
    }
    
    @Override
    public void requestDisallowInterceptTouchEvent(final boolean b) {
        if (this.getParent() != null) {
            this.getParent().requestDisallowInterceptTouchEvent(b);
        }
    }
    
    public void setProgressViewOffset(final float mProgressViewOffset) {
        this.mProgressViewOffset = mProgressViewOffset;
        if (this.mDidLayout) {
            final int progressCircleDiameter = this.getProgressCircleDiameter();
            this.setProgressViewOffset(false, Math.round(PixelUtil.toPixelFromDIP(mProgressViewOffset)) - progressCircleDiameter, Math.round(PixelUtil.toPixelFromDIP(64.0f + mProgressViewOffset) - progressCircleDiameter));
        }
    }
    
    @Override
    public void setRefreshing(final boolean b) {
        this.mRefreshing = b;
        if (this.mDidLayout) {
            super.setRefreshing(b);
        }
    }
}
