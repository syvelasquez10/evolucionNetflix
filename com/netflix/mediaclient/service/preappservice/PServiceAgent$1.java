// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.preappservice;

import com.netflix.mediaclient.Log;

class PServiceAgent$1 implements Runnable
{
    final /* synthetic */ PServiceAgent this$0;
    
    PServiceAgent$1(final PServiceAgent this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        Log.d("nf_preapp_serviceagent", "Initing " + this.this$0.getClass().getSimpleName());
        this.this$0.doInit();
    }
}
