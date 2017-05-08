// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

import android.view.animation.Animation;
import android.view.animation.Animation$AnimationListener;

class Snackbar$8 implements Animation$AnimationListener
{
    final /* synthetic */ Snackbar this$0;
    
    Snackbar$8(final Snackbar this$0) {
        this.this$0 = this$0;
    }
    
    public void onAnimationEnd(final Animation animation) {
        this.this$0.onViewShown();
    }
    
    public void onAnimationRepeat(final Animation animation) {
    }
    
    public void onAnimationStart(final Animation animation) {
    }
}
