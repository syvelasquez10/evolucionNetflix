// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.pushnotification;

import java.util.Map;

class PushNotificationAgent$1 implements Runnable
{
    final /* synthetic */ PushNotificationAgent this$0;
    final /* synthetic */ Map val$settings;
    
    PushNotificationAgent$1(final PushNotificationAgent this$0, final Map val$settings) {
        this.this$0 = this$0;
        this.val$settings = val$settings;
    }
    
    @Override
    public void run() {
        NotificationUserSettings.saveSettings(this.this$0.getContext(), this.val$settings);
    }
}
