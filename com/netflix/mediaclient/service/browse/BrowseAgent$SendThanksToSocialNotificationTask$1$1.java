// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.browse;

import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.service.webclient.model.leafs.social.SocialNotificationSummary;

class BrowseAgent$SendThanksToSocialNotificationTask$1$1 implements Runnable
{
    final /* synthetic */ BrowseAgent$SendThanksToSocialNotificationTask$1 this$2;
    final /* synthetic */ SocialNotificationSummary val$notification;
    final /* synthetic */ Status val$res;
    
    BrowseAgent$SendThanksToSocialNotificationTask$1$1(final BrowseAgent$SendThanksToSocialNotificationTask$1 this$2, final SocialNotificationSummary val$notification, final Status val$res) {
        this.this$2 = this$2;
        this.val$notification = val$notification;
        this.val$res = val$res;
    }
    
    @Override
    public void run() {
        if (this.this$2.this$1.mCallback != null) {
            this.this$2.this$1.mCallback.onSocialNotificationWasThanked(this.val$notification, this.val$res);
        }
    }
}
