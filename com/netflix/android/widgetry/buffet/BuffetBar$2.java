// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.android.widgetry.buffet;

import android.view.View;
import android.view.View$OnClickListener;

class BuffetBar$2 implements View$OnClickListener
{
    final /* synthetic */ BuffetBar this$0;
    final /* synthetic */ View$OnClickListener val$listener;
    
    BuffetBar$2(final BuffetBar this$0, final View$OnClickListener val$listener) {
        this.this$0 = this$0;
        this.val$listener = val$listener;
    }
    
    public void onClick(final View view) {
        this.val$listener.onClick(view);
        this.this$0.dispatchDismiss(1);
    }
}
