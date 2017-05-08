// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.offline.agent;

import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.interface_.offline.StopReason;
import com.netflix.mediaclient.servicemgr.interface_.offline.DownloadState;
import com.netflix.mediaclient.util.ConnectivityUtils;
import com.netflix.mediaclient.service.offline.utils.OfflineUtils;
import java.util.Iterator;
import com.netflix.mediaclient.util.PreferenceUtils;
import com.netflix.mediaclient.service.job.NetflixJob$NetflixJobId;
import com.netflix.mediaclient.Log;
import java.util.Random;
import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import java.util.concurrent.TimeUnit;
import java.util.HashMap;
import android.os.Looper;
import android.os.Handler;
import java.util.Map;
import com.netflix.mediaclient.service.offline.download.OfflinePlayable;
import java.util.List;
import com.netflix.mediaclient.service.job.NetflixJobScheduler;
import com.netflix.mediaclient.service.job.NetflixJob;
import android.content.Context;
import com.netflix.mediaclient.util.ConnectivityUtils$NetType;
import com.netflix.mediaclient.service.ServiceAgent$ConfigurationAgentInterface;
import com.netflix.mediaclient.servicemgr.IClientLogging;

class DownloadController$4 implements Runnable
{
    final /* synthetic */ DownloadController this$0;
    
    DownloadController$4(final DownloadController this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        this.this$0.mDownloadContinueReceiver.continueDownloadOnBackOff();
    }
}
