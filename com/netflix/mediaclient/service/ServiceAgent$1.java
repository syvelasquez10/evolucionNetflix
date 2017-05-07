// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service;

import com.netflix.mediaclient.Log;

class ServiceAgent$1 implements Runnable
{
    final /* synthetic */ ServiceAgent this$0;
    
    ServiceAgent$1(final ServiceAgent this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        Log.d("nf_service_ServiceAgent", "Initing " + this.this$0.getClass().getSimpleName());
        this.this$0.doInit();
    }
}
