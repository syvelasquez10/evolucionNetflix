// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.preapp;

import com.netflix.mediaclient.Log;

class PreAppAgent$3 implements Runnable
{
    final /* synthetic */ PreAppAgent this$0;
    
    PreAppAgent$3(final PreAppAgent this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        Log.i("nf_preappagent", "inform prefetch done via runnable");
        if (this.this$0.getContext() != null) {
            PreAppAgent.informMemberUpdated(this.this$0.getContext());
        }
    }
}
