// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.home;

import com.netflix.mediaclient.Log;
import android.view.ViewStub;
import com.netflix.mediaclient.ui.social.notifications.NotificationsFrag$NotificationsListStatusListener;

class StandardSlidingMenu$1 implements NotificationsFrag$NotificationsListStatusListener
{
    final /* synthetic */ StandardSlidingMenu this$0;
    final /* synthetic */ ViewStub val$notificationsStub;
    
    StandardSlidingMenu$1(final StandardSlidingMenu this$0, final ViewStub val$notificationsStub) {
        this.this$0 = this$0;
        this.val$notificationsStub = val$notificationsStub;
    }
    
    @Override
    public void onNotificationsListUpdated(final boolean b) {
        if (b) {
            Log.v("StandardSlidingMenu", "Showing notifications header");
            this.val$notificationsStub.setVisibility(0);
            return;
        }
        Log.v("StandardSlidingMenu", "Hiding notifications header");
        this.val$notificationsStub.setVisibility(8);
    }
}
