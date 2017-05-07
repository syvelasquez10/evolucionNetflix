// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.browse;

import com.netflix.mediaclient.android.app.Status;

class BrowseAgent$SendThanksToSocialNotificationTask$1 extends SimpleBrowseAgentCallback
{
    final /* synthetic */ BrowseAgent$SendThanksToSocialNotificationTask this$1;
    
    BrowseAgent$SendThanksToSocialNotificationTask$1(final BrowseAgent$SendThanksToSocialNotificationTask this$1) {
        this.this$1 = this$1;
    }
    
    @Override
    public void onSocialNotificationWasThanked(final Status status) {
        this.this$1.this$0.getMainHandler().post((Runnable)new BrowseAgent$SendThanksToSocialNotificationTask$1$1(this, status));
    }
}
