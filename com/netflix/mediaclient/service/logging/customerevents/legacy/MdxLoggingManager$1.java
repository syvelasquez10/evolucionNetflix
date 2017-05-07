// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.customerevents.legacy;

import com.netflix.mediaclient.Log;

class MdxLoggingManager$1 implements Runnable
{
    final /* synthetic */ MdxLoggingManager this$0;
    final /* synthetic */ MdxCustomerEvent val$cevent;
    
    MdxLoggingManager$1(final MdxLoggingManager this$0, final MdxCustomerEvent val$cevent) {
        this.this$0 = this$0;
        this.val$cevent = val$cevent;
    }
    
    @Override
    public void run() {
        try {
            this.val$cevent.execute();
        }
        catch (Throwable t) {
            Log.e("nf_mdxMdxLoggingManager", "sendEvent fails", t);
        }
    }
}
