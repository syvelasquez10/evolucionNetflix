// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.falkor;

import com.netflix.mediaclient.service.pushnotification.MessageData;

class FalkorAgent$9 implements Runnable
{
    final /* synthetic */ FalkorAgent this$0;
    
    FalkorAgent$9(final FalkorAgent this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        if (this.this$0.getService() != null) {
            this.this$0.refreshIrisNotifications(true, true, null);
        }
    }
}
