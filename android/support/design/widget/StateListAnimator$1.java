// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

import android.util.StateSet;
import android.view.View;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import android.view.animation.Animation;
import android.view.animation.Animation$AnimationListener;

class StateListAnimator$1 implements Animation$AnimationListener
{
    final /* synthetic */ StateListAnimator this$0;
    
    StateListAnimator$1(final StateListAnimator this$0) {
        this.this$0 = this$0;
    }
    
    public void onAnimationEnd(final Animation animation) {
        if (this.this$0.mRunningAnimation == animation) {
            this.this$0.mRunningAnimation = null;
        }
    }
    
    public void onAnimationRepeat(final Animation animation) {
    }
    
    public void onAnimationStart(final Animation animation) {
    }
}
