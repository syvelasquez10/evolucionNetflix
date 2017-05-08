// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.partner.playbilling;

import org.json.JSONObject;

class PlayBilling$8 implements Runnable
{
    final /* synthetic */ PlayBilling this$0;
    final /* synthetic */ PlayBillingCallback val$callback;
    final /* synthetic */ JSONObject val$data;
    
    PlayBilling$8(final PlayBilling this$0, final PlayBillingCallback val$callback, final JSONObject val$data) {
        this.this$0 = this$0;
        this.val$callback = val$callback;
        this.val$data = val$data;
    }
    
    @Override
    public void run() {
        this.val$callback.onResult(this.val$data);
    }
}
