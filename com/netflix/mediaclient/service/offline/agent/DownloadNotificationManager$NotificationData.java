// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.offline.agent;

import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import android.graphics.Bitmap;

class DownloadNotificationManager$NotificationData
{
    Bitmap mBoxShot;
    CharSequence mSecondaryTitle;
    CharSequence mTitle;
    VideoType mVideoType;
    final /* synthetic */ DownloadNotificationManager this$0;
    
    DownloadNotificationManager$NotificationData(final DownloadNotificationManager this$0) {
        this.this$0 = this$0;
        this.mTitle = "";
        this.mSecondaryTitle = "";
        this.mBoxShot = null;
        this.mVideoType = null;
    }
}
