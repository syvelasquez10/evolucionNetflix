// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.job;

import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import com.netflix.mediaclient.service.configuration.AndroidJobSchedulerConfig;
import java.util.Iterator;
import com.netflix.mediaclient.Log;
import java.util.HashMap;
import java.util.ArrayList;
import android.os.Handler;
import java.util.List;
import android.app.job.JobParameters;
import java.util.Map;
import android.annotation.TargetApi;
import android.app.job.JobService;
import android.content.Intent;
import android.content.Context;
import android.content.BroadcastReceiver;

class NetflixJobService$2 extends BroadcastReceiver
{
    final /* synthetic */ NetflixJobService this$0;
    
    NetflixJobService$2(final NetflixJobService this$0) {
        this.this$0 = this$0;
    }
    
    public void onReceive(final Context context, final Intent intent) {
        this.this$0.onJobFinishBroadcast(intent);
    }
}
