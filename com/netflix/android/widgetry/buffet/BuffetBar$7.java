// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.android.widgetry.buffet;

import android.view.View;
import android.support.v4.view.ViewPropertyAnimatorListenerAdapter;

class BuffetBar$7 extends ViewPropertyAnimatorListenerAdapter
{
    final /* synthetic */ BuffetBar this$0;
    
    BuffetBar$7(final BuffetBar this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onAnimationEnd(final View view) {
        this.this$0.onViewShown();
    }
    
    @Override
    public void onAnimationStart(final View view) {
        this.this$0.mView.animateChildrenIn(70, 180);
    }
}
