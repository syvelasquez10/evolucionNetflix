// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import com.netflix.mediaclient.util.AndroidUtils;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

class PostPlayItemView$1 extends AnimatorListenerAdapter
{
    final /* synthetic */ PostPlayItemView this$0;
    
    PostPlayItemView$1(final PostPlayItemView this$0) {
        this.this$0 = this$0;
    }
    
    public void onAnimationEnd(final Animator animator) {
        if (!AndroidUtils.isActivityFinishedOrDestroyed(this.this$0.getContext())) {
            this.this$0.stopTimer();
        }
    }
}
