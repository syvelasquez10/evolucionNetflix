// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.support.v4.widget.ViewDragHelper;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.support.v4.widget.ViewDragHelper$Callback;

class SwipeDismissBehavior$1 extends ViewDragHelper$Callback
{
    private int mOriginalCapturedViewLeft;
    final /* synthetic */ SwipeDismissBehavior this$0;
    
    SwipeDismissBehavior$1(final SwipeDismissBehavior this$0) {
        this.this$0 = this$0;
    }
    
    private boolean shouldDismiss(final View view, final float n) {
        if (n != 0.0f) {
            final boolean b = ViewCompat.getLayoutDirection(view) == 1;
            if (this.this$0.mSwipeDirection != 2) {
                if (this.this$0.mSwipeDirection == 0) {
                    if (b) {
                        if (n >= 0.0f) {
                            return false;
                        }
                    }
                    else if (n <= 0.0f) {
                        return false;
                    }
                }
                else {
                    if (this.this$0.mSwipeDirection != 1) {
                        return false;
                    }
                    if (b) {
                        if (n <= 0.0f) {
                            return false;
                        }
                    }
                    else if (n >= 0.0f) {
                        return false;
                    }
                }
            }
        }
        else if (Math.abs(view.getLeft() - this.mOriginalCapturedViewLeft) < Math.round(view.getWidth() * this.this$0.mDragDismissThreshold)) {
            return false;
        }
        return true;
    }
    
    @Override
    public int clampViewPositionHorizontal(final View view, final int n, int n2) {
        if (ViewCompat.getLayoutDirection(view) == 1) {
            n2 = 1;
        }
        else {
            n2 = 0;
        }
        int n3;
        if (this.this$0.mSwipeDirection == 0) {
            if (n2 != 0) {
                n3 = this.mOriginalCapturedViewLeft - view.getWidth();
                n2 = this.mOriginalCapturedViewLeft;
            }
            else {
                n3 = this.mOriginalCapturedViewLeft;
                n2 = this.mOriginalCapturedViewLeft + view.getWidth();
            }
        }
        else if (this.this$0.mSwipeDirection == 1) {
            if (n2 != 0) {
                n3 = this.mOriginalCapturedViewLeft;
                n2 = this.mOriginalCapturedViewLeft + view.getWidth();
            }
            else {
                n3 = this.mOriginalCapturedViewLeft - view.getWidth();
                n2 = this.mOriginalCapturedViewLeft;
            }
        }
        else {
            n3 = this.mOriginalCapturedViewLeft - view.getWidth();
            n2 = this.mOriginalCapturedViewLeft + view.getWidth();
        }
        return clamp(n3, n, n2);
    }
    
    @Override
    public int clampViewPositionVertical(final View view, final int n, final int n2) {
        return view.getTop();
    }
    
    @Override
    public int getViewHorizontalDragRange(final View view) {
        return view.getWidth();
    }
    
    @Override
    public void onViewDragStateChanged(final int n) {
        if (this.this$0.mListener != null) {
            this.this$0.mListener.onDragStateChanged(n);
        }
    }
    
    @Override
    public void onViewPositionChanged(final View view, final int n, final int n2, final int n3, final int n4) {
        final float n5 = view.getWidth() * this.this$0.mAlphaStartSwipeDistance;
        final float n6 = view.getWidth() * this.this$0.mAlphaEndSwipeDistance;
        if (n <= n5) {
            ViewCompat.setAlpha(view, 1.0f);
            return;
        }
        if (n >= n6) {
            ViewCompat.setAlpha(view, 0.0f);
            return;
        }
        ViewCompat.setAlpha(view, clamp(0.0f, 1.0f - SwipeDismissBehavior.fraction(n5, n6, n), 1.0f));
    }
    
    @Override
    public void onViewReleased(final View view, final float n, final float n2) {
        final int width = view.getWidth();
        boolean b = false;
        int mOriginalCapturedViewLeft;
        if (this.shouldDismiss(view, n)) {
            if (view.getLeft() < this.mOriginalCapturedViewLeft) {
                mOriginalCapturedViewLeft = this.mOriginalCapturedViewLeft - width;
            }
            else {
                mOriginalCapturedViewLeft = this.mOriginalCapturedViewLeft + width;
            }
            b = true;
        }
        else {
            mOriginalCapturedViewLeft = this.mOriginalCapturedViewLeft;
        }
        if (this.this$0.mViewDragHelper.settleCapturedViewAt(mOriginalCapturedViewLeft, view.getTop())) {
            ViewCompat.postOnAnimation(view, new SwipeDismissBehavior$SettleRunnable(this.this$0, view, b));
        }
        else if (b && this.this$0.mListener != null) {
            this.this$0.mListener.onDismiss(view);
        }
    }
    
    @Override
    public boolean tryCaptureView(final View view, final int n) {
        this.mOriginalCapturedViewLeft = view.getLeft();
        return true;
    }
}
