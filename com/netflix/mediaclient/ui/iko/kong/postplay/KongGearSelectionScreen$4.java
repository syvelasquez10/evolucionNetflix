// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.iko.kong.postplay;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

class KongGearSelectionScreen$4 extends AnimatorListenerAdapter
{
    final /* synthetic */ KongGearSelectionScreen this$0;
    
    KongGearSelectionScreen$4(final KongGearSelectionScreen this$0) {
        this.this$0 = this$0;
    }
    
    public void onAnimationEnd(final Animator animator) {
    }
    
    public void onAnimationStart(final Animator animator) {
        this.this$0.playGear2Sound();
    }
}
