// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.widget;

import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;
import android.os.SystemClock;
import android.util.DisplayMetrics;
import android.content.res.Resources;
import android.view.animation.AccelerateInterpolator;
import android.view.ViewConfiguration;
import android.view.View;
import android.view.animation.Interpolator;
import android.view.View$OnTouchListener;
import android.support.v4.view.ViewCompat;

class AutoScrollHelper$ScrollAnimationRunnable implements Runnable
{
    final /* synthetic */ AutoScrollHelper this$0;
    
    private AutoScrollHelper$ScrollAnimationRunnable(final AutoScrollHelper this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        if (!this.this$0.mAnimating) {
            return;
        }
        if (this.this$0.mNeedsReset) {
            this.this$0.mNeedsReset = false;
            this.this$0.mScroller.start();
        }
        final AutoScrollHelper$ClampedScroller access$300 = this.this$0.mScroller;
        if (access$300.isFinished() || !this.this$0.shouldAnimate()) {
            this.this$0.mAnimating = false;
            return;
        }
        if (this.this$0.mNeedsCancel) {
            this.this$0.mNeedsCancel = false;
            this.this$0.cancelTargetTouch();
        }
        access$300.computeScrollDelta();
        this.this$0.scrollTargetBy(access$300.getDeltaX(), access$300.getDeltaY());
        ViewCompat.postOnAnimation(this.this$0.mTarget, this);
    }
}
