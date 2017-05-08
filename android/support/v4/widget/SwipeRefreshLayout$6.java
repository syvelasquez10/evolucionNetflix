// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.widget;

import android.view.animation.Transformation;
import android.view.animation.Animation;

class SwipeRefreshLayout$6 extends Animation
{
    final /* synthetic */ SwipeRefreshLayout this$0;
    
    SwipeRefreshLayout$6(final SwipeRefreshLayout this$0) {
        this.this$0 = this$0;
    }
    
    public void applyTransformation(final float n, final Transformation transformation) {
        int n2;
        if (!this.this$0.mUsingCustomStart) {
            n2 = (int)(this.this$0.mSpinnerFinalOffset - Math.abs(this.this$0.mOriginalOffsetTop));
        }
        else {
            n2 = (int)this.this$0.mSpinnerFinalOffset;
        }
        this.this$0.setTargetOffsetTopAndBottom((int)((n2 - this.this$0.mFrom) * n) + this.this$0.mFrom - this.this$0.mCircleView.getTop(), false);
        this.this$0.mProgress.setArrowScale(1.0f - n);
    }
}
