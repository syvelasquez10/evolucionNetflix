// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.widget;

import android.os.Parcelable;
import android.view.View$MeasureSpec;
import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;
import android.view.ViewGroup$MarginLayoutParams;
import android.graphics.Bitmap;
import android.util.Log;
import android.graphics.Canvas;
import android.view.ViewGroup$LayoutParams;
import android.graphics.ColorFilter;
import android.graphics.PorterDuffColorFilter;
import android.graphics.PorterDuff$Mode;
import android.graphics.Paint;
import android.support.v4.view.AccessibilityDelegateCompat;
import android.support.v4.view.ViewCompat;
import android.view.ViewConfiguration;
import android.util.AttributeSet;
import android.content.Context;
import android.os.Build$VERSION;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import java.util.ArrayList;
import android.view.ViewGroup;
import android.view.View;

class SlidingPaneLayout$DragHelperCallback extends ViewDragHelper$Callback
{
    final /* synthetic */ SlidingPaneLayout this$0;
    
    private SlidingPaneLayout$DragHelperCallback(final SlidingPaneLayout this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public int clampViewPositionHorizontal(final View view, final int n, int n2) {
        final SlidingPaneLayout$LayoutParams slidingPaneLayout$LayoutParams = (SlidingPaneLayout$LayoutParams)this.this$0.mSlideableView.getLayoutParams();
        if (this.this$0.isLayoutRtlSupport()) {
            n2 = this.this$0.getWidth();
            n2 -= slidingPaneLayout$LayoutParams.rightMargin + this.this$0.getPaddingRight() + this.this$0.mSlideableView.getWidth();
            return Math.max(Math.min(n, n2), n2 - this.this$0.mSlideRange);
        }
        n2 = this.this$0.getPaddingLeft();
        n2 += slidingPaneLayout$LayoutParams.leftMargin;
        return Math.min(Math.max(n, n2), this.this$0.mSlideRange + n2);
    }
    
    @Override
    public int clampViewPositionVertical(final View view, final int n, final int n2) {
        return view.getTop();
    }
    
    @Override
    public int getViewHorizontalDragRange(final View view) {
        return this.this$0.mSlideRange;
    }
    
    @Override
    public void onEdgeDragStarted(final int n, final int n2) {
        this.this$0.mDragHelper.captureChildView(this.this$0.mSlideableView, n2);
    }
    
    @Override
    public void onViewCaptured(final View view, final int n) {
        this.this$0.setAllChildrenVisible();
    }
    
    @Override
    public void onViewDragStateChanged(final int n) {
        if (this.this$0.mDragHelper.getViewDragState() == 0) {
            if (this.this$0.mSlideOffset != 0.0f) {
                this.this$0.dispatchOnPanelOpened(this.this$0.mSlideableView);
                this.this$0.mPreservedOpenState = true;
                return;
            }
            this.this$0.updateObscuredViewsVisibility(this.this$0.mSlideableView);
            this.this$0.dispatchOnPanelClosed(this.this$0.mSlideableView);
            this.this$0.mPreservedOpenState = false;
        }
    }
    
    @Override
    public void onViewPositionChanged(final View view, final int n, final int n2, final int n3, final int n4) {
        this.this$0.onPanelDragged(n);
        this.this$0.invalidate();
    }
    
    @Override
    public void onViewReleased(final View view, final float n, final float n2) {
        final SlidingPaneLayout$LayoutParams slidingPaneLayout$LayoutParams = (SlidingPaneLayout$LayoutParams)view.getLayoutParams();
        int n5 = 0;
        Label_0110: {
            if (this.this$0.isLayoutRtlSupport()) {
                final int n3 = slidingPaneLayout$LayoutParams.rightMargin + this.this$0.getPaddingRight();
                int n4 = 0;
                Label_0083: {
                    if (n >= 0.0f) {
                        n4 = n3;
                        if (n != 0.0f) {
                            break Label_0083;
                        }
                        n4 = n3;
                        if (this.this$0.mSlideOffset <= 0.5f) {
                            break Label_0083;
                        }
                    }
                    n4 = n3 + this.this$0.mSlideRange;
                }
                n5 = this.this$0.getWidth() - n4 - this.this$0.mSlideableView.getWidth();
            }
            else {
                final int n6 = slidingPaneLayout$LayoutParams.leftMargin + this.this$0.getPaddingLeft();
                if (n <= 0.0f) {
                    n5 = n6;
                    if (n != 0.0f) {
                        break Label_0110;
                    }
                    n5 = n6;
                    if (this.this$0.mSlideOffset <= 0.5f) {
                        break Label_0110;
                    }
                }
                n5 = n6 + this.this$0.mSlideRange;
            }
        }
        this.this$0.mDragHelper.settleCapturedViewAt(n5, view.getTop());
        this.this$0.invalidate();
    }
    
    @Override
    public boolean tryCaptureView(final View view, final int n) {
        return !this.this$0.mIsUnableToDrag && ((SlidingPaneLayout$LayoutParams)view.getLayoutParams()).slideable;
    }
}
