// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.widget;

import android.animation.Animator;
import com.netflix.mediaclient.util.OnAnimationEndListener;

class ScalePressedStateHandler$EndAnimationCompleteListener extends OnAnimationEndListener
{
    final /* synthetic */ ScalePressedStateHandler this$0;
    
    private ScalePressedStateHandler$EndAnimationCompleteListener(final ScalePressedStateHandler this$0) {
        this.this$0 = this$0;
    }
    
    public void onAnimationEnd(final Animator animator) {
        this.this$0.notifyParentOfAnimationComplete();
    }
}
