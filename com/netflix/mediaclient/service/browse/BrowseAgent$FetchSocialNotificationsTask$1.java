// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.browse;

import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.service.webclient.model.leafs.social.SocialNotificationsList;

class BrowseAgent$FetchSocialNotificationsTask$1 extends SimpleBrowseAgentCallback
{
    final /* synthetic */ BrowseAgent$FetchSocialNotificationsTask this$1;
    
    BrowseAgent$FetchSocialNotificationsTask$1(final BrowseAgent$FetchSocialNotificationsTask this$1) {
        this.this$1 = this$1;
    }
    
    @Override
    public void onSocialNotificationsListFetched(final SocialNotificationsList list, final Status status) {
        this.this$1.updateCacheIfNecessary(list, status);
        this.this$1.this$0.getMainHandler().post((Runnable)new BrowseAgent$FetchSocialNotificationsTask$1$1(this, list, status));
    }
}
