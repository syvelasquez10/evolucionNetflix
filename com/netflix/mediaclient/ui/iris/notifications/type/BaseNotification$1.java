// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.iris.notifications.type;

import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import android.graphics.Bitmap;
import com.netflix.mediaclient.Log;
import com.netflix.model.leafs.social.IrisNotificationSummary;
import com.netflix.mediaclient.service.pushnotification.MessageData;
import com.netflix.model.leafs.social.IrisNotificationsListSummary;
import com.netflix.mediaclient.util.gfx.ImageLoader;
import android.content.Context;
import com.netflix.mediaclient.util.gfx.ImageLoader$ImageLoaderListener;

class BaseNotification$1 implements ImageLoader$ImageLoaderListener
{
    final /* synthetic */ BaseNotification this$0;
    final /* synthetic */ Context val$context;
    final /* synthetic */ ImageLoader val$imageLoader;
    final /* synthetic */ IrisNotificationsListSummary val$listSummary;
    final /* synthetic */ MessageData val$msg;
    final /* synthetic */ IrisNotificationSummary val$notificationSummary;
    
    BaseNotification$1(final BaseNotification this$0, final ImageLoader val$imageLoader, final IrisNotificationSummary val$notificationSummary, final MessageData val$msg, final IrisNotificationsListSummary val$listSummary, final Context val$context) {
        this.this$0 = this$0;
        this.val$imageLoader = val$imageLoader;
        this.val$notificationSummary = val$notificationSummary;
        this.val$msg = val$msg;
        this.val$listSummary = val$listSummary;
        this.val$context = val$context;
    }
    
    @Override
    public void onErrorResponse(final String s) {
        if (Log.isLoggable()) {
            Log.e(BaseNotification.TAG, "Failed to download: " + s);
        }
    }
    
    @Override
    public void onResponse(final Bitmap bitmap, final String s) {
        if (bitmap != null) {
            this.val$imageLoader.getImg(this.val$notificationSummary.getTVCardUrl(), IClientLogging$AssetType.boxArt, 0, 0, new BaseNotification$1onBoxArtFetched(this.this$0, bitmap, this.val$context, this.val$notificationSummary, this.val$listSummary, this.val$msg));
        }
    }
}
