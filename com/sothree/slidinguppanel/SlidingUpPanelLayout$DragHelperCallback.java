// 
// Decompiled by Procyon v0.5.30
// 

package com.sothree.slidinguppanel;

import android.view.View$MeasureSpec;
import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;
import android.view.ViewGroup$MarginLayoutParams;
import android.graphics.Canvas;
import android.support.v4.view.ViewCompat;
import android.view.ViewGroup$LayoutParams;
import android.content.res.TypedArray;
import android.view.ViewConfiguration;
import com.netflix.mediaclient.R$styleable;
import android.util.AttributeSet;
import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.widget.ViewDragHelper;
import android.graphics.Paint;
import android.view.ViewGroup;
import java.io.Serializable;
import android.util.Log;
import android.view.View;
import android.support.v4.widget.ViewDragHelper$Callback;

class SlidingUpPanelLayout$DragHelperCallback extends ViewDragHelper$Callback
{
    final /* synthetic */ SlidingUpPanelLayout this$0;
    
    private SlidingUpPanelLayout$DragHelperCallback(final SlidingUpPanelLayout this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public int clampViewPositionVertical(final View view, final int n, int paddingTop) {
        int access$1100;
        if (this.this$0.mIsSlidingUp) {
            access$1100 = this.this$0.getSlidingTop();
            paddingTop = this.this$0.mSlideRange + access$1100;
        }
        else {
            paddingTop = this.this$0.getPaddingTop();
            access$1100 = paddingTop - this.this$0.mSlideRange;
        }
        return Math.min(Math.max(n, access$1100), paddingTop);
    }
    
    @Override
    public int getViewVerticalDragRange(final View view) {
        return this.this$0.mSlideRange;
    }
    
    @Override
    public void onViewCaptured(final View view, final int n) {
        this.this$0.setAllChildrenVisible();
    }
    
    @Override
    public void onViewDragStateChanged(int n) {
        if (Log.isLoggable(SlidingUpPanelLayout.TAG, 2)) {
            final String access$200 = SlidingUpPanelLayout.TAG;
            final StringBuilder append = new StringBuilder().append("onViewDragStateChanged - ").append(n).append(", sliding top: ");
            Serializable value;
            if (this.this$0.mSlideableView == null) {
                value = "null";
            }
            else {
                value = this.this$0.mSlideableView.getTop();
            }
            Log.v(access$200, append.append(value).toString());
        }
        n = (int)(this.this$0.mAnchorPoint * this.this$0.mSlideRange);
        if (this.this$0.mDragHelper.getViewDragState() == 0) {
            if (this.this$0.mSlideOffset == 0.0f || (this.this$0.mSlideableView != null && this.this$0.mSlideableView.getTop() == 0)) {
                if (this.this$0.mSlideState != SlidingUpPanelLayout$SlideState.EXPANDED) {
                    this.this$0.updateObscuredViewVisibility();
                    this.this$0.dispatchOnPanelExpanded(this.this$0.mSlideableView);
                    this.this$0.mSlideState = SlidingUpPanelLayout$SlideState.EXPANDED;
                }
            }
            else if (this.this$0.mSlideOffset == n / this.this$0.mSlideRange) {
                if (this.this$0.mSlideState != SlidingUpPanelLayout$SlideState.ANCHORED) {
                    this.this$0.updateObscuredViewVisibility();
                    this.this$0.dispatchOnPanelAnchored(this.this$0.mSlideableView);
                    this.this$0.mSlideState = SlidingUpPanelLayout$SlideState.ANCHORED;
                }
            }
            else if (this.this$0.mSlideState != SlidingUpPanelLayout$SlideState.COLLAPSED) {
                this.this$0.dispatchOnPanelCollapsed(this.this$0.mSlideableView);
                this.this$0.mSlideState = SlidingUpPanelLayout$SlideState.COLLAPSED;
            }
        }
    }
    
    @Override
    public void onViewPositionChanged(final View view, final int n, final int n2, final int n3, final int n4) {
        this.this$0.onPanelDragged(n2);
        this.this$0.invalidate();
    }
    
    @Override
    public void onViewReleased(final View view, float n, final float n2) {
        int access$1100;
        if (this.this$0.mIsSlidingUp) {
            access$1100 = this.this$0.getSlidingTop();
        }
        else {
            access$1100 = this.this$0.getSlidingTop() - this.this$0.mSlideRange;
        }
        int n3 = 0;
        Label_0109: {
            if (this.this$0.mAnchorPoint != 0.0f) {
                if (this.this$0.mIsSlidingUp) {
                    n = (int)(this.this$0.mAnchorPoint * this.this$0.mSlideRange) / this.this$0.mSlideRange;
                }
                else {
                    n = (this.this$0.mPanelHeight - (this.this$0.mPanelHeight - (int)(this.this$0.mAnchorPoint * this.this$0.mSlideRange))) / this.this$0.mSlideRange;
                }
                if (n2 > 0.0f || (n2 == 0.0f && this.this$0.mSlideOffset >= (1.0f + n) / 2.0f)) {
                    n3 = access$1100 + this.this$0.mSlideRange;
                }
                else {
                    n3 = access$1100;
                    if (n2 == 0.0f) {
                        n3 = access$1100;
                        if (this.this$0.mSlideOffset < (1.0f + n) / 2.0f) {
                            n3 = access$1100;
                            if (this.this$0.mSlideOffset >= n / 2.0f) {
                                n3 = (int)(access$1100 + this.this$0.mSlideRange * this.this$0.mAnchorPoint);
                            }
                        }
                    }
                }
            }
            else {
                if (n2 <= 0.0f) {
                    n3 = access$1100;
                    if (n2 != 0.0f) {
                        break Label_0109;
                    }
                    n3 = access$1100;
                    if (this.this$0.mSlideOffset <= 0.5f) {
                        break Label_0109;
                    }
                }
                n3 = access$1100 + this.this$0.mSlideRange;
            }
        }
        this.this$0.mDragHelper.settleCapturedViewAt(view.getLeft(), n3);
        this.this$0.invalidate();
    }
    
    @Override
    public boolean tryCaptureView(final View view, final int n) {
        return !this.this$0.mIsUnableToDrag && ((SlidingUpPanelLayout$LayoutParams)view.getLayoutParams()).slideable;
    }
}
