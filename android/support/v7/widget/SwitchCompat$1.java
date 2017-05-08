// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.view.animation.Animation;
import android.view.animation.Animation$AnimationListener;

class SwitchCompat$1 implements Animation$AnimationListener
{
    final /* synthetic */ SwitchCompat this$0;
    final /* synthetic */ boolean val$newCheckedState;
    
    SwitchCompat$1(final SwitchCompat this$0, final boolean val$newCheckedState) {
        this.this$0 = this$0;
        this.val$newCheckedState = val$newCheckedState;
    }
    
    public void onAnimationEnd(final Animation animation) {
        if (this.this$0.mPositionAnimator == animation) {
            final SwitchCompat this$0 = this.this$0;
            float thumbPosition;
            if (this.val$newCheckedState) {
                thumbPosition = 1.0f;
            }
            else {
                thumbPosition = 0.0f;
            }
            this$0.setThumbPosition(thumbPosition);
            this.this$0.mPositionAnimator = null;
        }
    }
    
    public void onAnimationRepeat(final Animation animation) {
    }
    
    public void onAnimationStart(final Animation animation) {
    }
}
