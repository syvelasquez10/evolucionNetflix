// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

import android.support.v4.view.ViewCompat;
import android.view.View;

class BottomSheetBehavior$SettleRunnable implements Runnable
{
    private final int mTargetState;
    private final View mView;
    final /* synthetic */ BottomSheetBehavior this$0;
    
    BottomSheetBehavior$SettleRunnable(final BottomSheetBehavior this$0, final View mView, final int mTargetState) {
        this.this$0 = this$0;
        this.mView = mView;
        this.mTargetState = mTargetState;
    }
    
    @Override
    public void run() {
        if (this.this$0.mViewDragHelper != null && this.this$0.mViewDragHelper.continueSettling(true)) {
            ViewCompat.postOnAnimation(this.mView, this);
            return;
        }
        this.this$0.setStateInternal(this.mTargetState);
    }
}
