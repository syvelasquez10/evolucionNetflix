// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service;

import android.content.Context;
import com.netflix.mediaclient.service.pushnotification.MessageData;
import android.widget.Toast;
import com.netflix.mediaclient.util.log.UserActionLogUtils;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import com.netflix.mediaclient.android.app.Status;

class BrowseAccess$1SentThanksCallback extends BrowseAccess$BrowseAgentClientCallback
{
    private final NetflixService service;
    final /* synthetic */ BrowseAccess this$0;
    
    BrowseAccess$1SentThanksCallback(final BrowseAccess this$0, final NetflixService service) {
        this.this$0 = this$0;
        super(this$0, 0, 0);
        this.service = service;
    }
    
    @Override
    public void onSocialNotificationWasThanked(final Status status) {
        if (this.service != null) {
            final Context applicationContext = this.service.getApplicationContext();
            IClientLogging$CompletionReason clientLogging$CompletionReason;
            if (status.isSucces()) {
                clientLogging$CompletionReason = IClientLogging$CompletionReason.success;
            }
            else {
                clientLogging$CompletionReason = IClientLogging$CompletionReason.failed;
            }
            UserActionLogUtils.reportSayThanksActionEnded(applicationContext, clientLogging$CompletionReason, status.getError());
        }
        if (status.isSucces() && this.service != null) {
            Toast.makeText(this.service.getApplicationContext(), 2131493371, 1).show();
            if (this.service.getBrowse() != null) {
                this.service.getBrowse().refreshSocialNotifications(true, false, null);
            }
        }
    }
}
