// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

import android.view.View;

class BaseTransientBottomBar$6 implements BaseTransientBottomBar$OnLayoutChangeListener
{
    final /* synthetic */ BaseTransientBottomBar this$0;
    
    BaseTransientBottomBar$6(final BaseTransientBottomBar this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onLayoutChange(final View view, final int n, final int n2, final int n3, final int n4) {
        this.this$0.mView.setOnLayoutChangeListener(null);
        if (this.this$0.shouldAnimate()) {
            this.this$0.animateViewIn();
            return;
        }
        this.this$0.onViewShown();
    }
}
