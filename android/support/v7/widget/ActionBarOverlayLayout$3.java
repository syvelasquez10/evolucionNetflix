// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.view.View;
import android.support.v4.view.ViewCompat;

class ActionBarOverlayLayout$3 implements Runnable
{
    final /* synthetic */ ActionBarOverlayLayout this$0;
    
    ActionBarOverlayLayout$3(final ActionBarOverlayLayout this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        this.this$0.haltActionBarHideOffsetAnimations();
        this.this$0.mCurrentActionBarTopAnimator = ViewCompat.animate((View)this.this$0.mActionBarTop).translationY(-this.this$0.mActionBarTop.getHeight()).setListener(this.this$0.mTopAnimatorListener);
    }
}
