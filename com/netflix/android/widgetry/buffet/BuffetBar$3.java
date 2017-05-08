// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.android.widgetry.buffet;

import android.os.Handler;

class BuffetBar$3 implements BuffetManager$Callback
{
    final /* synthetic */ BuffetBar this$0;
    
    BuffetBar$3(final BuffetBar this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void dismiss(final int n) {
        BuffetBar.sHandler.sendMessage(BuffetBar.sHandler.obtainMessage(1, n, 0, (Object)this.this$0));
    }
    
    @Override
    public void show(final boolean b) {
        final Handler sHandler = BuffetBar.sHandler;
        final Handler sHandler2 = BuffetBar.sHandler;
        int n;
        if (b) {
            n = 0;
        }
        else {
            n = 2;
        }
        sHandler.sendMessage(sHandler2.obtainMessage(n, (Object)this.this$0));
    }
}
