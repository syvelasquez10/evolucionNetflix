// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.iko.kong.postplay;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

class KongBattleResultScreen$1 extends AnimatorListenerAdapter
{
    final /* synthetic */ KongBattleResultScreen this$0;
    
    KongBattleResultScreen$1(final KongBattleResultScreen this$0) {
        this.this$0 = this$0;
    }
    
    public void onAnimationEnd(final Animator animator) {
        if (this.this$0.handler != null) {
            this.this$0.handler.postDelayed((Runnable)new KongBattleResultScreen$1$1(this), 2000L);
        }
    }
}
