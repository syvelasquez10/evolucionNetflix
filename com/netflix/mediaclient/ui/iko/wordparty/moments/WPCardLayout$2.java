// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.iko.wordparty.moments;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

class WPCardLayout$2 extends AnimatorListenerAdapter
{
    final /* synthetic */ WPCardLayout this$0;
    
    WPCardLayout$2(final WPCardLayout this$0) {
        this.this$0 = this$0;
    }
    
    public void onAnimationCancel(final Animator animator) {
        this.onAnimationEnd(animator);
    }
    
    public void onAnimationEnd(final Animator animator) {
        this.this$0.imageView.updateDrawableBitmap();
        this.this$0.revealCard();
    }
}
