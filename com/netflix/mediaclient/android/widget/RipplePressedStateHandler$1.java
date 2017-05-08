// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.widget;

import android.animation.Animator;
import com.netflix.mediaclient.util.OnAnimationEndListener;

class RipplePressedStateHandler$1 extends OnAnimationEndListener
{
    final /* synthetic */ RipplePressedStateHandler this$0;
    
    RipplePressedStateHandler$1(final RipplePressedStateHandler this$0) {
        this.this$0 = this$0;
    }
    
    public void onAnimationEnd(final Animator animator) {
        this.this$0.notifyParentOfAnimationComplete();
    }
}
