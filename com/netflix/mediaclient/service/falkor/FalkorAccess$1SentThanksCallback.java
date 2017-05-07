// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.falkor;

import com.netflix.mediaclient.service.pushnotification.MessageData;
import android.widget.Toast;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.service.NetflixService;

class FalkorAccess$1SentThanksCallback extends FalkorAccess$BrowseAgentClientCallback
{
    private final NetflixService service;
    final /* synthetic */ FalkorAccess this$0;
    
    FalkorAccess$1SentThanksCallback(final FalkorAccess this$0, final NetflixService service) {
        this.this$0 = this$0;
        super(this$0, 0, 0);
        this.service = service;
    }
    
    @Override
    public void onSocialNotificationWasThanked(final Status status) {
        if (status.isSucces() && this.service != null) {
            Toast.makeText(this.service.getApplicationContext(), 2131493375, 1).show();
            if (this.service.getBrowse() != null) {
                this.service.getBrowse().refreshSocialNotifications(true, false, null);
            }
        }
    }
}
