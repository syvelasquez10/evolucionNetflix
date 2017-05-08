// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

import android.support.v4.view.ViewCompat;
import android.view.View;

class SwipeDismissBehavior$SettleRunnable implements Runnable
{
    private final boolean mDismiss;
    private final View mView;
    final /* synthetic */ SwipeDismissBehavior this$0;
    
    SwipeDismissBehavior$SettleRunnable(final SwipeDismissBehavior this$0, final View mView, final boolean mDismiss) {
        this.this$0 = this$0;
        this.mView = mView;
        this.mDismiss = mDismiss;
    }
    
    @Override
    public void run() {
        if (this.this$0.mViewDragHelper != null && this.this$0.mViewDragHelper.continueSettling(true)) {
            ViewCompat.postOnAnimation(this.mView, this);
        }
        else if (this.mDismiss && this.this$0.mListener != null) {
            this.this$0.mListener.onDismiss(this.mView);
        }
    }
}
