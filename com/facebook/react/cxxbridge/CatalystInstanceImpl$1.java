// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.cxxbridge;

class CatalystInstanceImpl$1 implements Runnable
{
    final /* synthetic */ CatalystInstanceImpl this$0;
    
    CatalystInstanceImpl$1(final CatalystInstanceImpl this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        this.this$0.destroy();
    }
}
