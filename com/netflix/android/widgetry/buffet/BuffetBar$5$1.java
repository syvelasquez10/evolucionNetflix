// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.android.widgetry.buffet;

class BuffetBar$5$1 implements Runnable
{
    final /* synthetic */ BuffetBar$5 this$1;
    
    BuffetBar$5$1(final BuffetBar$5 this$1) {
        this.this$1 = this$1;
    }
    
    @Override
    public void run() {
        this.this$1.this$0.onViewHidden(3);
    }
}
