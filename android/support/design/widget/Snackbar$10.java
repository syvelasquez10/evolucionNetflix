// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

import android.view.animation.Animation;
import android.view.animation.Animation$AnimationListener;

class Snackbar$10 implements Animation$AnimationListener
{
    final /* synthetic */ Snackbar this$0;
    final /* synthetic */ int val$event;
    
    Snackbar$10(final Snackbar this$0, final int val$event) {
        this.this$0 = this$0;
        this.val$event = val$event;
    }
    
    public void onAnimationEnd(final Animation animation) {
        this.this$0.onViewHidden(this.val$event);
    }
    
    public void onAnimationRepeat(final Animation animation) {
    }
    
    public void onAnimationStart(final Animation animation) {
    }
}
