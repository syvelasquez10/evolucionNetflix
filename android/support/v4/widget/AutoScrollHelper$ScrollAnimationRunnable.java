// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.widget;

import android.support.v4.view.ViewCompat;

class AutoScrollHelper$ScrollAnimationRunnable implements Runnable
{
    final /* synthetic */ AutoScrollHelper this$0;
    
    AutoScrollHelper$ScrollAnimationRunnable(final AutoScrollHelper this$0) {
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
        final AutoScrollHelper$ClampedScroller mScroller = this.this$0.mScroller;
        if (mScroller.isFinished() || !this.this$0.shouldAnimate()) {
            this.this$0.mAnimating = false;
            return;
        }
        if (this.this$0.mNeedsCancel) {
            this.this$0.mNeedsCancel = false;
            this.this$0.cancelTargetTouch();
        }
        mScroller.computeScrollDelta();
        this.this$0.scrollTargetBy(mScroller.getDeltaX(), mScroller.getDeltaY());
        ViewCompat.postOnAnimation(this.this$0.mTarget, this);
    }
}
