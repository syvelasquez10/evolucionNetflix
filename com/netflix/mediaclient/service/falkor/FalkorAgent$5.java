// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.falkor;

import com.netflix.mediaclient.service.pushnotification.MessageData;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.service.browse.SimpleBrowseAgentCallback;

class FalkorAgent$5 extends SimpleBrowseAgentCallback
{
    final /* synthetic */ FalkorAgent this$0;
    
    FalkorAgent$5(final FalkorAgent this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onIrisNotificationsMarkedAsRead(final Status status) {
        if (status.isSucces()) {
            this.this$0.refreshIrisNotifications(false, false, null);
            return;
        }
        this.this$0.refreshIrisNotifications(true, false, null);
    }
}
