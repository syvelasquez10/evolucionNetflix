// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.social.notifications.types;

import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import android.graphics.Bitmap;
import com.netflix.mediaclient.Log;
import com.netflix.model.leafs.social.SocialNotificationSummary;
import com.netflix.mediaclient.service.pushnotification.MessageData;
import com.netflix.model.leafs.social.SocialNotificationsListSummary;
import com.netflix.mediaclient.util.gfx.ImageLoader;
import android.content.Context;
import com.netflix.mediaclient.util.gfx.ImageLoader$ImageLoaderListener;

class SocialNotification$1 implements ImageLoader$ImageLoaderListener
{
    final /* synthetic */ SocialNotification this$0;
    final /* synthetic */ Context val$context;
    final /* synthetic */ ImageLoader val$imageLoader;
    final /* synthetic */ SocialNotificationsListSummary val$listSummary;
    final /* synthetic */ MessageData val$msg;
    final /* synthetic */ SocialNotificationSummary val$notificationSummary;
    
    SocialNotification$1(final SocialNotification this$0, final ImageLoader val$imageLoader, final SocialNotificationSummary val$notificationSummary, final MessageData val$msg, final SocialNotificationsListSummary val$listSummary, final Context val$context) {
        this.this$0 = this$0;
        this.val$imageLoader = val$imageLoader;
        this.val$notificationSummary = val$notificationSummary;
        this.val$msg = val$msg;
        this.val$listSummary = val$listSummary;
        this.val$context = val$context;
    }
    
    @Override
    public void onErrorResponse(final String s) {
        if (Log.isLoggable(SocialNotification.TAG, 6)) {
            Log.e(SocialNotification.TAG, "Failed to download: " + s);
        }
    }
    
    @Override
    public void onResponse(final Bitmap bitmap, final String s) {
        if (bitmap != null) {
            this.val$imageLoader.getImg(this.val$notificationSummary.getVideo().getHorzDispUrl(), IClientLogging$AssetType.boxArt, 0, 0, new SocialNotification$1onBoxArtFetched(this.this$0, bitmap, this.val$context, this.val$notificationSummary, this.val$listSummary, this.val$msg));
        }
    }
}
