// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.offline.registry;

import android.os.StatFs;
import com.netflix.mediaclient.util.AndroidUtils;
import android.os.storage.StorageManager;
import android.os.Environment;
import java.util.ArrayList;
import java.util.List;
import android.content.IntentFilter;
import java.io.IOException;
import java.io.File;
import android.os.Looper;
import java.util.concurrent.TimeUnit;
import android.os.Handler;
import com.netflix.mediaclient.Log;
import android.content.Intent;
import android.content.Context;
import android.content.BroadcastReceiver;

class OfflineStorageMonitor$2 extends BroadcastReceiver
{
    final /* synthetic */ OfflineStorageMonitor this$0;
    
    OfflineStorageMonitor$2(final OfflineStorageMonitor this$0) {
        this.this$0 = this$0;
    }
    
    public void onReceive(final Context context, final Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            Log.i("offlineStorageMonitor", "mExtStorageMountReceiver action=%s mData=%s", action, intent.getData());
            Log.d("offlineStorageMonitor", "intent=", intent);
            this.this$0.mWorkHandler.removeCallbacks(this.this$0.mStorageInfoUpdater);
            if (!"android.intent.action.MEDIA_EJECT".equals(action) && !"android.intent.action.MEDIA_UNMOUNTED".equals(action)) {
                this.this$0.mWorkHandler.postDelayed(this.this$0.mStorageInfoUpdater, OfflineStorageMonitor.STORAGE_MOUNT_DELAY_MS);
                return;
            }
            this.this$0.mWorkHandler.postDelayed(this.this$0.mStorageInfoUpdater, OfflineStorageMonitor.STORAGE_UN_MOUNT_DELAY_MS);
        }
    }
}
