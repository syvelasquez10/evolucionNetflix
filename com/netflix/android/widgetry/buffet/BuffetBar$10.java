// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.android.widgetry.buffet;

import android.view.animation.Animation;
import android.view.animation.Animation$AnimationListener;

class BuffetBar$10 implements Animation$AnimationListener
{
    final /* synthetic */ BuffetBar this$0;
    final /* synthetic */ int val$event;
    
    BuffetBar$10(final BuffetBar this$0, final int val$event) {
        this.this$0 = this$0;
        this.val$event = val$event;
    }
    
    public void onAnimationEnd(final Animation animation) {
        this.this$0.onViewHidden(this.val$event);
    }
    
    public void onAnimationRepeat(final Animation animation) {
    }
    
    public void onAnimationStart(final Animation animation) {
    }
}
