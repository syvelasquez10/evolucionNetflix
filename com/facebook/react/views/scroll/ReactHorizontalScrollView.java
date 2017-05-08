// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.views.scroll;

import com.facebook.react.uimanager.ReactClippingViewGroupHelper;
import android.graphics.drawable.ColorDrawable;
import android.view.View$MeasureSpec;
import com.facebook.react.uimanager.MeasureSpecAssertions;
import com.facebook.react.uimanager.events.NativeGestureUtil;
import android.view.MotionEvent;
import android.view.View;
import android.graphics.Canvas;
import android.annotation.TargetApi;
import android.view.ViewGroup;
import com.facebook.infer.annotation.Assertions;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.Rect;
import com.facebook.react.uimanager.ReactClippingViewGroup;
import android.widget.HorizontalScrollView;

public class ReactHorizontalScrollView extends HorizontalScrollView implements ReactClippingViewGroup
{
    private boolean mActivelyScrolling;
    private Rect mClippingRect;
    private boolean mDragging;
    private Drawable mEndBackground;
    private int mEndFillColor;
    private FpsListener mFpsListener;
    private final OnScrollDispatchHelper mOnScrollDispatchHelper;
    private boolean mPagingEnabled;
    private Runnable mPostTouchRunnable;
    private boolean mRemoveClippedSubviews;
    private boolean mScrollEnabled;
    private String mScrollPerfTag;
    private boolean mSendMomentumEvents;
    
    public ReactHorizontalScrollView(final Context context, final FpsListener mFpsListener) {
        super(context);
        this.mOnScrollDispatchHelper = new OnScrollDispatchHelper();
        this.mPagingEnabled = false;
        this.mScrollEnabled = true;
        this.mFpsListener = null;
        this.mEndFillColor = 0;
        this.mFpsListener = mFpsListener;
    }
    
    private void disableFpsListener() {
        if (this.isScrollPerfLoggingEnabled()) {
            Assertions.assertNotNull(this.mFpsListener);
            Assertions.assertNotNull(this.mScrollPerfTag);
            this.mFpsListener.disable(this.mScrollPerfTag);
        }
    }
    
    private void enableFpsListener() {
        if (this.isScrollPerfLoggingEnabled()) {
            Assertions.assertNotNull(this.mFpsListener);
            Assertions.assertNotNull(this.mScrollPerfTag);
            this.mFpsListener.enable(this.mScrollPerfTag);
        }
    }
    
    @TargetApi(16)
    private void handlePostTouchScrolling() {
        if ((this.mSendMomentumEvents || this.mPagingEnabled || this.isScrollPerfLoggingEnabled()) && this.mPostTouchRunnable == null) {
            if (this.mSendMomentumEvents) {
                ReactScrollViewHelper.emitScrollMomentumBeginEvent((ViewGroup)this);
            }
            this.mActivelyScrolling = false;
            this.postOnAnimationDelayed(this.mPostTouchRunnable = new ReactHorizontalScrollView$1(this), 20L);
        }
    }
    
    private boolean isScrollPerfLoggingEnabled() {
        return this.mFpsListener != null && this.mScrollPerfTag != null && !this.mScrollPerfTag.isEmpty();
    }
    
    private void smoothScrollToPage(final int n) {
        final int width = this.getWidth();
        final int scrollX = this.getScrollX();
        int n3;
        final int n2 = n3 = scrollX / width;
        if (scrollX + n > n2 * width + width / 2) {
            n3 = n2 + 1;
        }
        this.smoothScrollTo(n3 * width, this.getScrollY());
    }
    
    public void draw(final Canvas canvas) {
        if (this.mEndFillColor != 0) {
            final View child = this.getChildAt(0);
            if (this.mEndBackground != null && child != null && child.getRight() < this.getWidth()) {
                this.mEndBackground.setBounds(child.getRight(), 0, this.getWidth(), this.getHeight());
                this.mEndBackground.draw(canvas);
            }
        }
        super.draw(canvas);
    }
    
    public void fling(final int n) {
        if (this.mPagingEnabled) {
            this.smoothScrollToPage(n);
        }
        else {
            super.fling(n);
        }
        this.handlePostTouchScrolling();
    }
    
    public void getClippingRect(final Rect rect) {
        rect.set((Rect)Assertions.assertNotNull(this.mClippingRect));
    }
    
    public boolean getRemoveClippedSubviews() {
        return this.mRemoveClippedSubviews;
    }
    
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.mRemoveClippedSubviews) {
            this.updateClippingRect();
        }
    }
    
    public boolean onInterceptTouchEvent(final MotionEvent motionEvent) {
        if (this.mScrollEnabled && super.onInterceptTouchEvent(motionEvent)) {
            NativeGestureUtil.notifyNativeGestureStarted((View)this, motionEvent);
            ReactScrollViewHelper.emitScrollBeginDragEvent((ViewGroup)this);
            this.mDragging = true;
            this.enableFpsListener();
            return true;
        }
        return false;
    }
    
    protected void onLayout(final boolean b, final int n, final int n2, final int n3, final int n4) {
        this.scrollTo(this.getScrollX(), this.getScrollY());
    }
    
    protected void onMeasure(final int n, final int n2) {
        MeasureSpecAssertions.assertExplicitMeasureSpec(n, n2);
        this.setMeasuredDimension(View$MeasureSpec.getSize(n), View$MeasureSpec.getSize(n2));
    }
    
    protected void onScrollChanged(final int n, final int n2, final int n3, final int n4) {
        super.onScrollChanged(n, n2, n3, n4);
        if (this.mOnScrollDispatchHelper.onScrollChanged(n, n2)) {
            if (this.mRemoveClippedSubviews) {
                this.updateClippingRect();
            }
            this.mActivelyScrolling = true;
            ReactScrollViewHelper.emitScrollEvent((ViewGroup)this);
        }
    }
    
    protected void onSizeChanged(final int n, final int n2, final int n3, final int n4) {
        super.onSizeChanged(n, n2, n3, n4);
        if (this.mRemoveClippedSubviews) {
            this.updateClippingRect();
        }
    }
    
    public boolean onTouchEvent(final MotionEvent motionEvent) {
        if (!this.mScrollEnabled) {
            return false;
        }
        if ((motionEvent.getAction() & 0xFF) == 0x1 && this.mDragging) {
            ReactScrollViewHelper.emitScrollEndDragEvent((ViewGroup)this);
            this.mDragging = false;
            this.handlePostTouchScrolling();
        }
        return super.onTouchEvent(motionEvent);
    }
    
    public void setEndFillColor(final int mEndFillColor) {
        if (mEndFillColor != this.mEndFillColor) {
            this.mEndFillColor = mEndFillColor;
            this.mEndBackground = (Drawable)new ColorDrawable(this.mEndFillColor);
        }
    }
    
    public void setPagingEnabled(final boolean mPagingEnabled) {
        this.mPagingEnabled = mPagingEnabled;
    }
    
    public void setRemoveClippedSubviews(final boolean mRemoveClippedSubviews) {
        if (mRemoveClippedSubviews && this.mClippingRect == null) {
            this.mClippingRect = new Rect();
        }
        this.mRemoveClippedSubviews = mRemoveClippedSubviews;
        this.updateClippingRect();
    }
    
    public void setScrollEnabled(final boolean mScrollEnabled) {
        this.mScrollEnabled = mScrollEnabled;
    }
    
    public void setScrollPerfTag(final String mScrollPerfTag) {
        this.mScrollPerfTag = mScrollPerfTag;
    }
    
    public void setSendMomentumEvents(final boolean mSendMomentumEvents) {
        this.mSendMomentumEvents = mSendMomentumEvents;
    }
    
    public void updateClippingRect() {
        if (this.mRemoveClippedSubviews) {
            Assertions.assertNotNull(this.mClippingRect);
            ReactClippingViewGroupHelper.calculateClippingRect((View)this, this.mClippingRect);
            final View child = this.getChildAt(0);
            if (child instanceof ReactClippingViewGroup) {
                ((ReactClippingViewGroup)child).updateClippingRect();
            }
        }
    }
}
