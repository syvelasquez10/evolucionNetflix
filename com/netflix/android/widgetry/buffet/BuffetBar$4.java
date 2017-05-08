// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.android.widgetry.buffet;

import android.view.View;
import android.support.design.widget.SwipeDismissBehavior$OnDismissListener;

class BuffetBar$4 implements SwipeDismissBehavior$OnDismissListener
{
    final /* synthetic */ BuffetBar this$0;
    
    BuffetBar$4(final BuffetBar this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onDismiss(final View view) {
        view.setVisibility(8);
        this.this$0.dispatchDismiss(0);
    }
    
    @Override
    public void onDragStateChanged(final int n) {
        switch (n) {
            default: {}
            case 1:
            case 2: {
                BuffetManager.getInstance().cancelTimeout(this.this$0.mManagerCallback);
            }
            case 0: {
                BuffetManager.getInstance().restoreTimeout(this.this$0.mManagerCallback);
            }
        }
    }
}
