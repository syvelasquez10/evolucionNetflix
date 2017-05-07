// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.ui.Asset;
import android.app.Notification;
import android.text.TextUtils;
import android.app.Notification$Builder;
import android.support.v4.app.NotificationCompat$Builder;
import com.netflix.mediaclient.util.AndroidUtils;
import android.content.IntentFilter;
import android.widget.RemoteViews;
import com.netflix.mediaclient.util.gfx.ImageLoader;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import com.netflix.mediaclient.util.StringUtils;
import android.content.Context;
import android.content.Intent;
import android.app.PendingIntent;
import java.util.concurrent.atomic.AtomicBoolean;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import android.app.NotificationManager;
import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.gfx.ImageLoader$ImageLoaderListener;

class PlayerSuspendNotification$2 implements ImageLoader$ImageLoaderListener
{
    final /* synthetic */ PlayerSuspendNotification this$0;
    
    PlayerSuspendNotification$2(final PlayerSuspendNotification this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onErrorResponse(final String s) {
        Log.e(PlayerSuspendNotification.TAG, "failed to downlod " + s);
    }
    
    @Override
    public void onResponse(Bitmap copy, final String s) {
        if (copy != null && !copy.isRecycled()) {
            if (Log.isLoggable(PlayerSuspendNotification.TAG, 3)) {
                Log.d(PlayerSuspendNotification.TAG, "downloaded image from " + s);
            }
            copy = copy.copy(copy.getConfig(), copy.isMutable());
            this.this$0.show(copy);
            return;
        }
        Log.e(PlayerSuspendNotification.TAG, "bitmap is not valid " + copy);
    }
}
