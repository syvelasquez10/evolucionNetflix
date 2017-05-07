// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.widget;

import android.content.res.Resources;
import android.view.View$MeasureSpec;
import android.util.Log;
import android.widget.AbsListView;
import android.os.Build$VERSION;
import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;
import android.graphics.drawable.Drawable;
import android.view.animation.Interpolator;
import android.util.DisplayMetrics;
import android.content.res.TypedArray;
import android.support.v4.view.ViewCompat;
import android.view.ViewConfiguration;
import android.util.AttributeSet;
import android.content.Context;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Animation$AnimationListener;

class SwipeRefreshLayout$1 implements Animation$AnimationListener
{
    final /* synthetic */ SwipeRefreshLayout this$0;
    
    SwipeRefreshLayout$1(final SwipeRefreshLayout this$0) {
        this.this$0 = this$0;
    }
    
    public void onAnimationEnd(final Animation animation) {
        if (this.this$0.mRefreshing) {
            this.this$0.mProgress.setAlpha(255);
            this.this$0.mProgress.start();
            if (this.this$0.mNotify && this.this$0.mListener != null) {
                this.this$0.mListener.onRefresh();
            }
        }
        else {
            this.this$0.mProgress.stop();
            this.this$0.mCircleView.setVisibility(8);
            this.this$0.setColorViewAlpha(255);
            if (this.this$0.mScale) {
                this.this$0.setAnimationProgress(0.0f);
            }
            else {
                this.this$0.setTargetOffsetTopAndBottom(this.this$0.mOriginalOffsetTop - this.this$0.mCurrentTargetOffsetTop, true);
            }
        }
        this.this$0.mCurrentTargetOffsetTop = this.this$0.mCircleView.getTop();
    }
    
    public void onAnimationRepeat(final Animation animation) {
    }
    
    public void onAnimationStart(final Animation animation) {
    }
}
