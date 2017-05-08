// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.offline.agent;

import com.netflix.mediaclient.android.app.BackgroundTask;
import com.netflix.mediaclient.Log;
import android.graphics.Bitmap;
import com.netflix.mediaclient.util.gfx.ImageLoader$ImageLoaderListener;

class DownloadNotificationManager$2 implements ImageLoader$ImageLoaderListener
{
    final /* synthetic */ DownloadNotificationManager this$0;
    final /* synthetic */ DownloadNotificationManager$NotificationData val$notificationData;
    
    DownloadNotificationManager$2(final DownloadNotificationManager this$0, final DownloadNotificationManager$NotificationData val$notificationData) {
        this.this$0 = this$0;
        this.val$notificationData = val$notificationData;
    }
    
    @Override
    public void onErrorResponse(final String s) {
        this.val$notificationData.mBoxShot = null;
    }
    
    @Override
    public void onResponse(final Bitmap bitmap, final String s) {
        boolean b = true;
        if (bitmap == null) {
            b = false;
        }
        Log.i("nf_downloadNotification", "fetchNotificationData onResponse gotImage=%b", b);
        if (bitmap != null) {
            new BackgroundTask().execute(new DownloadNotificationManager$2$1(this, bitmap));
        }
    }
}
