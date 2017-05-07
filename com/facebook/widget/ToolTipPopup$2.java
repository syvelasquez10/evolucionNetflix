// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.widget;

class ToolTipPopup$2 implements Runnable
{
    final /* synthetic */ ToolTipPopup this$0;
    
    ToolTipPopup$2(final ToolTipPopup this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        this.this$0.dismiss();
    }
}
