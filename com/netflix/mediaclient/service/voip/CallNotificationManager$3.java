// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.voip;

import com.netflix.mediaclient.service.NetflixService;

class CallNotificationManager$3 implements Runnable
{
    final /* synthetic */ CallNotificationManager this$0;
    final /* synthetic */ NetflixService val$service;
    
    CallNotificationManager$3(final CallNotificationManager this$0, final NetflixService val$service) {
        this.this$0 = this$0;
        this.val$service = val$service;
    }
    
    @Override
    public void run() {
        this.val$service.requestBackgroundForNotification(20, true);
    }
}
