// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.view.animation.Transformation;
import android.view.animation.Animation;

class SwitchCompat$ThumbAnimation extends Animation
{
    final float mDiff;
    final float mEndPosition;
    final float mStartPosition;
    final /* synthetic */ SwitchCompat this$0;
    
    SwitchCompat$ThumbAnimation(final SwitchCompat this$0, final float mStartPosition, final float mEndPosition) {
        this.this$0 = this$0;
        this.mStartPosition = mStartPosition;
        this.mEndPosition = mEndPosition;
        this.mDiff = mEndPosition - mStartPosition;
    }
    
    protected void applyTransformation(final float n, final Transformation transformation) {
        this.this$0.setThumbPosition(this.mStartPosition + this.mDiff * n);
    }
}
