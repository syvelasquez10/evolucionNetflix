// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.android.widgetry.buffet;

import android.view.View;
import android.support.v4.view.ViewPropertyAnimatorListenerAdapter;

class BuffetBar$9 extends ViewPropertyAnimatorListenerAdapter
{
    final /* synthetic */ BuffetBar this$0;
    final /* synthetic */ int val$event;
    
    BuffetBar$9(final BuffetBar this$0, final int val$event) {
        this.this$0 = this$0;
        this.val$event = val$event;
    }
    
    @Override
    public void onAnimationEnd(final View view) {
        this.this$0.onViewHidden(this.val$event);
    }
    
    @Override
    public void onAnimationStart(final View view) {
        this.this$0.mView.animateChildrenOut(0, 180);
    }
}
