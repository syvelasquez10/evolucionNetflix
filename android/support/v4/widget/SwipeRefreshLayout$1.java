// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.widget;

import android.annotation.SuppressLint;
import android.view.animation.Animation;
import android.view.animation.Animation$AnimationListener;

class SwipeRefreshLayout$1 implements Animation$AnimationListener
{
    final /* synthetic */ SwipeRefreshLayout this$0;
    
    SwipeRefreshLayout$1(final SwipeRefreshLayout this$0) {
        this.this$0 = this$0;
    }
    
    @SuppressLint({ "NewApi" })
    public void onAnimationEnd(final Animation animation) {
        if (this.this$0.mRefreshing) {
            this.this$0.mProgress.setAlpha(255);
            this.this$0.mProgress.start();
            if (this.this$0.mNotify && this.this$0.mListener != null) {
                this.this$0.mListener.onRefresh();
            }
            this.this$0.mCurrentTargetOffsetTop = this.this$0.mCircleView.getTop();
            return;
        }
        this.this$0.reset();
    }
    
    public void onAnimationRepeat(final Animation animation) {
    }
    
    public void onAnimationStart(final Animation animation) {
    }
}
