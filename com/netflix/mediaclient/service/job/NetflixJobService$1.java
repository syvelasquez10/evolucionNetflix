// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.job;

import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import java.util.Iterator;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import android.app.job.JobParameters;
import java.util.Map;
import android.annotation.TargetApi;
import android.app.job.JobService;
import com.netflix.mediaclient.Log;
import android.content.Intent;
import android.content.Context;
import android.content.BroadcastReceiver;

class NetflixJobService$1 extends BroadcastReceiver
{
    final /* synthetic */ NetflixJobService this$0;
    
    NetflixJobService$1(final NetflixJobService this$0) {
        this.this$0 = this$0;
    }
    
    public void onReceive(final Context context, final Intent intent) {
        final int intExtra = intent.getIntExtra("NetflixJobId=", NetflixJob$NetflixJobId.UNKNOWN_JOB_ID.getIntValue());
        final boolean booleanExtra = intent.getBooleanExtra("needsReschedule", false);
        final NetflixJob$NetflixJobId jobIdByValue = NetflixJob$NetflixJobId.getJobIdByValue(intExtra);
        if (Log.isLoggable()) {
            Log.i("nf_job_service_l", "mJobFinishReceiver onReceive jobId=" + jobIdByValue);
        }
        this.this$0.sendJobFinished(intExtra, booleanExtra);
    }
}
