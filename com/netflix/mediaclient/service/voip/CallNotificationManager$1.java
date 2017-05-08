// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.voip;

import com.netflix.mediaclient.service.NetflixService;
import android.app.Notification;

class CallNotificationManager$1 implements Runnable
{
    final /* synthetic */ CallNotificationManager this$0;
    final /* synthetic */ Notification val$notification;
    final /* synthetic */ NetflixService val$service;
    
    CallNotificationManager$1(final CallNotificationManager this$0, final NetflixService val$service, final Notification val$notification) {
        this.this$0 = this$0;
        this.val$service = val$service;
        this.val$notification = val$notification;
    }
    
    @Override
    public void run() {
        this.val$service.requestForegroundForNotification(20, this.val$notification);
    }
}
