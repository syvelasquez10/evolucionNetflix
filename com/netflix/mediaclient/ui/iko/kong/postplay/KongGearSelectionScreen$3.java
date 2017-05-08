// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.iko.kong.postplay;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

class KongGearSelectionScreen$3 extends AnimatorListenerAdapter
{
    final /* synthetic */ KongGearSelectionScreen this$0;
    
    KongGearSelectionScreen$3(final KongGearSelectionScreen this$0) {
        this.this$0 = this$0;
    }
    
    public void onAnimationStart(final Animator animator) {
        this.this$0.playGear1Sound();
    }
}
