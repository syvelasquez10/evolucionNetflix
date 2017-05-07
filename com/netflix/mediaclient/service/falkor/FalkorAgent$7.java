// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.falkor;

import com.netflix.mediaclient.service.pushnotification.MessageData;
import com.netflix.mediaclient.util.SocialUtils;

class FalkorAgent$7 implements Runnable
{
    final /* synthetic */ FalkorAgent this$0;
    
    FalkorAgent$7(final FalkorAgent this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        if (this.this$0.getService() != null && SocialUtils.isNotificationsFeatureSupported(this.this$0.getService().getCurrentProfile(), this.this$0.getContext())) {
            this.this$0.refreshSocialNotifications(true, true, null);
        }
    }
}
