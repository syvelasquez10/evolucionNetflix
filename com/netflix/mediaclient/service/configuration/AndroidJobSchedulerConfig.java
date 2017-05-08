// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.configuration;

import com.netflix.mediaclient.service.job.NetflixJobSchedulerImpl;
import com.netflix.mediaclient.util.PreferenceUtils;
import android.content.Context;

public class AndroidJobSchedulerConfig
{
    public static boolean isJobFinishDisabled(final Context context) {
        return PreferenceUtils.getBooleanPref(context, "disableJobFinishAndroidJobScheduler", false);
    }
    
    public static boolean isJobSchedulerDisabled(final Context context) {
        return PreferenceUtils.getBooleanPref(context, "disableAndroidJobScheduler", false);
    }
    
    public static void updateJobFinishDisabled(final Context context, final boolean b) {
        PreferenceUtils.putBooleanPref(context, "disableJobFinishAndroidJobScheduler", b);
    }
    
    public static void updateJobSchedulerDisabledAndDisableIfNeeded(final Context context, final boolean b) {
        PreferenceUtils.putBooleanPref(context, "disableAndroidJobScheduler", b);
        if (b) {
            NetflixJobSchedulerImpl.cancelAllJobs(context);
        }
    }
}
