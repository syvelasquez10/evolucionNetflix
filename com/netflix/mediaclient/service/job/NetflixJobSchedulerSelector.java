// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.job;

import com.netflix.mediaclient.Log;
import android.os.Build$VERSION;
import android.content.Context;

public class NetflixJobSchedulerSelector
{
    private static final String TAG = "nf_job_scheduler_select";
    
    public static NetflixJobScheduler createNetflixJobScheduler(final Context context) {
        int n;
        if (Build$VERSION.SDK_INT < 21) {
            n = 1;
        }
        else {
            n = 0;
        }
        final boolean boolean1 = context.getResources().getBoolean(2131689475);
        final boolean boolean2 = context.getResources().getBoolean(2131689476);
        if (Log.isLoggable()) {
            Log.i("nf_job_scheduler_select", "alarmReceiverOn=" + boolean1 + " networkChangeReceiverOn=" + boolean2);
        }
        if (n != 0) {
            return new NetflixJobSchedulerPreL(context);
        }
        NetflixJobSchedulerPreL.cancelAllJobs(context);
        return new NetflixJobSchedulerImpl(context);
    }
}
