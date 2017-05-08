// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

import android.support.v4.view.ViewCompat;
import android.view.View;
import android.support.v4.widget.ViewDragHelper$Callback;

class BottomSheetBehavior$2 extends ViewDragHelper$Callback
{
    final /* synthetic */ BottomSheetBehavior this$0;
    
    BottomSheetBehavior$2(final BottomSheetBehavior this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public int clampViewPositionHorizontal(final View view, final int n, final int n2) {
        return view.getLeft();
    }
    
    @Override
    public int clampViewPositionVertical(final View view, final int n, int n2) {
        final int mMinOffset = this.this$0.mMinOffset;
        if (this.this$0.mHideable) {
            n2 = this.this$0.mParentHeight;
        }
        else {
            n2 = this.this$0.mMaxOffset;
        }
        return MathUtils.constrain(n, mMinOffset, n2);
    }
    
    @Override
    public int getViewVerticalDragRange(final View view) {
        if (this.this$0.mHideable) {
            return this.this$0.mParentHeight - this.this$0.mMinOffset;
        }
        return this.this$0.mMaxOffset - this.this$0.mMinOffset;
    }
    
    @Override
    public void onViewDragStateChanged(final int n) {
        if (n == 1) {
            this.this$0.setStateInternal(1);
        }
    }
    
    @Override
    public void onViewPositionChanged(final View view, final int n, final int n2, final int n3, final int n4) {
        this.this$0.dispatchOnSlide(n2);
    }
    
    @Override
    public void onViewReleased(final View view, final float n, final float n2) {
        int stateInternal = 3;
        int n3;
        if (n2 < 0.0f) {
            n3 = this.this$0.mMinOffset;
        }
        else if (this.this$0.mHideable && this.this$0.shouldHide(view, n2)) {
            n3 = this.this$0.mParentHeight;
            stateInternal = 5;
        }
        else if (n2 == 0.0f) {
            final int top = view.getTop();
            if (Math.abs(top - this.this$0.mMinOffset) < Math.abs(top - this.this$0.mMaxOffset)) {
                n3 = this.this$0.mMinOffset;
            }
            else {
                n3 = this.this$0.mMaxOffset;
                stateInternal = 4;
            }
        }
        else {
            n3 = this.this$0.mMaxOffset;
            stateInternal = 4;
        }
        if (this.this$0.mViewDragHelper.settleCapturedViewAt(view.getLeft(), n3)) {
            this.this$0.setStateInternal(2);
            ViewCompat.postOnAnimation(view, new BottomSheetBehavior$SettleRunnable(this.this$0, view, stateInternal));
            return;
        }
        this.this$0.setStateInternal(stateInternal);
    }
    
    @Override
    public boolean tryCaptureView(final View view, final int n) {
        if (this.this$0.mState != 1 && !this.this$0.mTouchingScrollingChild) {
            if (this.this$0.mState == 3 && this.this$0.mActivePointerId == n) {
                final View view2 = this.this$0.mNestedScrollingChildRef.get();
                if (view2 != null && ViewCompat.canScrollVertically(view2, -1)) {
                    return false;
                }
            }
            return this.this$0.mViewRef != null && this.this$0.mViewRef.get() == view;
        }
        return false;
    }
}
