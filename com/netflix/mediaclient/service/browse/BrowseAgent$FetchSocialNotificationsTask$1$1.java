// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.browse;

import com.netflix.mediaclient.android.app.Status;
import com.netflix.model.leafs.social.SocialNotificationsList;

class BrowseAgent$FetchSocialNotificationsTask$1$1 implements Runnable
{
    final /* synthetic */ BrowseAgent$FetchSocialNotificationsTask$1 this$2;
    final /* synthetic */ SocialNotificationsList val$notifications;
    final /* synthetic */ Status val$res;
    
    BrowseAgent$FetchSocialNotificationsTask$1$1(final BrowseAgent$FetchSocialNotificationsTask$1 this$2, final SocialNotificationsList val$notifications, final Status val$res) {
        this.this$2 = this$2;
        this.val$notifications = val$notifications;
        this.val$res = val$res;
    }
    
    @Override
    public void run() {
        if (this.this$2.this$1.mCallback != null) {
            this.this$2.this$1.mCallback.onSocialNotificationsListFetched(this.val$notifications, this.val$res);
        }
    }
}
