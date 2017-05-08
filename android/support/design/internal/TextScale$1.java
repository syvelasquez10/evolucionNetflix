// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.internal;

import android.animation.ValueAnimator;
import android.widget.TextView;
import android.animation.ValueAnimator$AnimatorUpdateListener;

class TextScale$1 implements ValueAnimator$AnimatorUpdateListener
{
    final /* synthetic */ TextScale this$0;
    final /* synthetic */ TextView val$view;
    
    TextScale$1(final TextScale this$0, final TextView val$view) {
        this.this$0 = this$0;
        this.val$view = val$view;
    }
    
    public void onAnimationUpdate(final ValueAnimator valueAnimator) {
        final float floatValue = (float)valueAnimator.getAnimatedValue();
        this.val$view.setScaleX(floatValue);
        this.val$view.setScaleY(floatValue);
    }
}
