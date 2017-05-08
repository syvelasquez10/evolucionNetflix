// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

import android.view.View;

class BaseTransientBottomBar$5 implements BaseTransientBottomBar$OnAttachStateChangeListener
{
    final /* synthetic */ BaseTransientBottomBar this$0;
    
    BaseTransientBottomBar$5(final BaseTransientBottomBar this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onViewAttachedToWindow(final View view) {
    }
    
    @Override
    public void onViewDetachedFromWindow(final View view) {
        if (this.this$0.isShownOrQueued()) {
            BaseTransientBottomBar.sHandler.post((Runnable)new BaseTransientBottomBar$5$1(this));
        }
    }
}
