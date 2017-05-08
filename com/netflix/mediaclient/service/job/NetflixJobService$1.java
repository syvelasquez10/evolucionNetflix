// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.job;

import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import com.netflix.mediaclient.service.configuration.AndroidJobSchedulerConfig;
import android.content.Context;
import java.util.Iterator;
import com.netflix.mediaclient.Log;
import android.content.Intent;
import java.util.HashMap;
import java.util.ArrayList;
import android.os.Handler;
import java.util.List;
import android.app.job.JobParameters;
import java.util.Map;
import android.content.BroadcastReceiver;
import android.annotation.TargetApi;
import android.app.job.JobService;

class NetflixJobService$1 implements Runnable
{
    final /* synthetic */ NetflixJobService this$0;
    
    NetflixJobService$1(final NetflixJobService this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        this.this$0.executeJobs();
    }
}
