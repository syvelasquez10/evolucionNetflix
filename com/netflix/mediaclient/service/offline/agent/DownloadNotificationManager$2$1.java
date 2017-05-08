// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.offline.agent;

import java.util.List;
import com.netflix.mediaclient.util.UserVisibleErrorCodeGenerator;
import com.netflix.mediaclient.servicemgr.interface_.offline.StopReason;
import com.netflix.mediaclient.android.app.Status;
import android.text.format.Formatter;
import com.netflix.mediaclient.util.ThreadUtils;
import com.netflix.mediaclient.ui.player.PlayerActivity;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.ui.offline.OfflineActivity;
import android.support.v4.app.NotificationCompat$Style;
import android.support.v4.app.NotificationCompat$BigTextStyle;
import android.support.v4.app.NotificationCompat$Action;
import android.app.Notification$Style;
import android.app.Notification$BigTextStyle;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.servicemgr.interface_.offline.OfflinePlayableViewData;
import com.netflix.mediaclient.servicemgr.interface_.Playable;
import com.netflix.mediaclient.servicemgr.interface_.offline.realm.RealmVideoDetails;
import com.netflix.mediaclient.util.gfx.ImageLoader$ImageLoaderListener;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import com.netflix.mediaclient.util.l10n.LocalizationUtils;
import com.netflix.mediaclient.util.l10n.BidiMarker;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import com.netflix.mediaclient.servicemgr.interface_.offline.realm.RealmUtils;
import com.netflix.mediaclient.service.NetflixService;
import android.app.PendingIntent;
import android.content.Intent;
import android.app.NotificationManager;
import android.support.v4.content.ContextCompat;
import android.os.Build$VERSION;
import com.netflix.mediaclient.Log;
import android.app.Notification;
import android.app.Notification$Builder;
import android.os.Handler;
import android.util.LruCache;
import com.netflix.mediaclient.servicemgr.INetflixService;
import android.support.v4.app.NotificationCompat$Builder;
import com.netflix.mediaclient.util.gfx.ImageLoader;
import android.content.Context;
import com.netflix.mediaclient.servicemgr.interface_.offline.SimpleOfflineAgentListener;
import com.netflix.mediaclient.util.AndroidUtils;
import android.graphics.Bitmap;

class DownloadNotificationManager$2$1 implements Runnable
{
    final /* synthetic */ DownloadNotificationManager$2 this$1;
    final /* synthetic */ Bitmap val$bitmap;
    
    DownloadNotificationManager$2$1(final DownloadNotificationManager$2 this$1, final Bitmap val$bitmap) {
        this.this$1 = this$1;
        this.val$bitmap = val$bitmap;
    }
    
    @Override
    public void run() {
        this.this$1.val$notificationData.mBoxShot = AndroidUtils.createScaledBitmapWithAspectRatio(this.val$bitmap, this.this$1.this$0.mLargeIconWidth, this.this$1.this$0.mLargeIconHeight, true);
    }
}
