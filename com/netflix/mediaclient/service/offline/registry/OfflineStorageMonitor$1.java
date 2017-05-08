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
import com.netflix.mediaclient.Log;
import java.io.File;
import android.os.Looper;
import java.util.concurrent.TimeUnit;
import android.os.Handler;
import android.content.BroadcastReceiver;
import android.content.Context;

class OfflineStorageMonitor$1 implements Runnable
{
    final /* synthetic */ OfflineStorageMonitor this$0;
    
    OfflineStorageMonitor$1(final OfflineStorageMonitor this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        this.this$0.sendStorageAddedOrRemoved();
    }
}
