// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.iko.kong.postplay;

import android.animation.Animator;
import com.netflix.mediaclient.util.OnAnimationEndListener;

class KongUnlockScreen$2 extends OnAnimationEndListener
{
    final /* synthetic */ KongUnlockScreen this$0;
    
    KongUnlockScreen$2(final KongUnlockScreen this$0) {
        this.this$0 = this$0;
    }
    
    public void onAnimationEnd(final Animator animator) {
        if (this.this$0.handler != null) {
            this.this$0.handler.postDelayed((Runnable)new KongUnlockScreen$2$1(this), 1000L);
        }
    }
}
