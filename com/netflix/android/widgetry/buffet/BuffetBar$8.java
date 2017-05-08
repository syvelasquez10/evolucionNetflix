// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.android.widgetry.buffet;

import android.view.animation.Animation;
import android.view.animation.Animation$AnimationListener;

class BuffetBar$8 implements Animation$AnimationListener
{
    final /* synthetic */ BuffetBar this$0;
    
    BuffetBar$8(final BuffetBar this$0) {
        this.this$0 = this$0;
    }
    
    public void onAnimationEnd(final Animation animation) {
        this.this$0.onViewShown();
    }
    
    public void onAnimationRepeat(final Animation animation) {
    }
    
    public void onAnimationStart(final Animation animation) {
    }
}
