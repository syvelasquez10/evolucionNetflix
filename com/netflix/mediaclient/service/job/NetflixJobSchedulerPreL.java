// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.job;

import java.util.concurrent.TimeUnit;
import java.util.Iterator;
import java.util.Map;
import android.app.PendingIntent;
import android.content.Intent;
import android.app.AlarmManager;
import com.google.gson.JsonSyntaxException;
import com.netflix.mediaclient.Log;
import com.google.gson.Gson;
import com.netflix.mediaclient.util.PreferenceUtils;
import android.content.Context;

public class NetflixJobSchedulerPreL implements NetflixJobScheduler
{
    private static final String NETFLIX_JOB_ID = "NetflixJobId=";
    private static final String TAG = "nf_job_scheduler_prel";
    private static NetflixJobSchedulerPreL$PendingJobRegistry sPendingJobRegistry;
    private final Context mContext;
    
    public NetflixJobSchedulerPreL(final Context mContext) {
        buildPendingJobsFromPrefIfRequired(this.mContext = mContext);
    }
    
    private static void buildPendingJobsFromPrefIfRequired(final Context context) {
        if (NetflixJobSchedulerPreL.sPendingJobRegistry == null) {
            synchronized (NetflixJobSchedulerPreL$PendingJobRegistry.class) {
                if (NetflixJobSchedulerPreL.sPendingJobRegistry != null) {
                    return;
                }
                final String stringPref = PreferenceUtils.getStringPref(context, "pending_jobs", "");
                final Gson gson = new Gson();
                while (true) {
                    try {
                        NetflixJobSchedulerPreL.sPendingJobRegistry = gson.fromJson(stringPref, NetflixJobSchedulerPreL$PendingJobRegistry.class);
                        if (NetflixJobSchedulerPreL.sPendingJobRegistry == null) {
                            Log.i("nf_job_scheduler_prel", "buildPendingJobsFromPrefIfRequired sPendingJobRegistry was null");
                            NetflixJobSchedulerPreL.sPendingJobRegistry = new NetflixJobSchedulerPreL$PendingJobRegistry();
                        }
                    }
                    catch (JsonSyntaxException ex) {
                        if (Log.isLoggable()) {
                            Log.i("nf_job_scheduler_prel", "buildPendingJobsFromPrefIfRequired JsonSyntaxException ", ex);
                        }
                        continue;
                    }
                    break;
                }
            }
        }
    }
    
    private static void cancelAlarm(final Context context, final NetflixJob$NetflixJobId netflixJob$NetflixJobId) {
        final AlarmManager alarmManager = (AlarmManager)context.getSystemService("alarm");
        if (alarmManager != null) {
            final Intent intent = new Intent(context, (Class)NetflixAlarmReceiver.class);
            intent.setAction("NetflixJobId=" + netflixJob$NetflixJobId);
            alarmManager.cancel(PendingIntent.getBroadcast(context, 0, intent, 0));
        }
        else if (Log.isLoggable()) {
            Log.e("nf_job_scheduler_prel", "cancelAlarm alarmManager is null");
        }
    }
    
    public static void cancelAllJobs(final Context context) {
        if (Log.isLoggable()) {
            Log.i("nf_job_scheduler_prel", "cancelAllJobs");
        }
        buildPendingJobsFromPrefIfRequired(context);
        synchronized (NetflixJobSchedulerPreL.sPendingJobRegistry.mNetflixJobs) {
            final Iterator<Map.Entry<Integer, NetflixJobSchedulerPreL$NetflixJobExecInfo>> iterator = NetflixJobSchedulerPreL.sPendingJobRegistry.mNetflixJobs.entrySet().iterator();
            while (iterator.hasNext()) {
                cancelAlarm(context, NetflixJob$NetflixJobId.getJobIdByValue(iterator.next().getKey()));
            }
        }
        NetflixJobSchedulerPreL.sPendingJobRegistry.mNetflixJobs.clear();
        // monitorexit(map)
        final Context context2;
        PreferenceUtils.putStringPref(context2, "pending_jobs", "");
        dumpPref(context2);
    }
    
    private static void checkAllAndStartJobs(final Context context) {
        synchronized (NetflixJobSchedulerPreL.sPendingJobRegistry.mNetflixJobs) {
            final Iterator<Map.Entry<Integer, NetflixJobSchedulerPreL$NetflixJobExecInfo>> iterator = NetflixJobSchedulerPreL.sPendingJobRegistry.mNetflixJobs.entrySet().iterator();
            while (iterator.hasNext()) {
                checkAndStartJob(context, iterator.next().getValue());
            }
        }
    }
    // monitorexit(map)
    
    private static void checkAndStartJob(final Context context, final NetflixJobSchedulerPreL$NetflixJobExecInfo netflixJobSchedulerPreL$NetflixJobExecInfo) {
        if (netflixJobSchedulerPreL$NetflixJobExecInfo == null) {
            if (Log.isLoggable()) {
                Log.e("nf_job_scheduler_prel", "checkAndStartJob netflixJobExecInfo is null");
            }
        }
        else {
            final NetflixJob mNetflixJob = netflixJobSchedulerPreL$NetflixJobExecInfo.mNetflixJob;
            if (mNetflixJob == null) {
                if (Log.isLoggable()) {
                    Log.e("nf_job_scheduler_prel", "checkAndStartJob job is null");
                }
            }
            else {
                long n;
                if (mNetflixJob.isRepeating()) {
                    n = netflixJobSchedulerPreL$NetflixJobExecInfo.mLastExecutionTime + mNetflixJob.getRepeatingPeriodInMs() - System.currentTimeMillis();
                }
                else {
                    n = 0L;
                }
                if (mNetflixJob.getMinimumDelay() > 0L) {
                    n = netflixJobSchedulerPreL$NetflixJobExecInfo.mRequestIssueTime + mNetflixJob.getMinimumDelay() - System.currentTimeMillis();
                }
                if (n > 0L) {
                    if (Log.isLoggable()) {
                        Log.i("nf_job_scheduler_prel", "checkAndStartJob not starting repeating job. Will start after " + TimeUnit.MILLISECONDS.toSeconds(n) + " seconds");
                    }
                }
                else {
                    if (mNetflixJob.canExecute(context)) {
                        context.startService(NetflixJobServicePreL.getServiceStartIntentForJobId(context, mNetflixJob));
                        return;
                    }
                    if (Log.isLoggable()) {
                        Log.i("nf_job_scheduler_prel", "checkAndStartJob not starting job=" + mNetflixJob.getNetflixJobId());
                    }
                }
            }
        }
    }
    
    private static void dumpPref(final Context context) {
        if (Log.isLoggable()) {
            Log.i("nf_job_scheduler_prel", "pendingJobsPref=" + PreferenceUtils.getStringPref(context, "pending_jobs", ""));
        }
    }
    
    private static NetflixJobSchedulerPreL$NetflixJobExecInfo getJobExecInfoFromMap(final NetflixJob$NetflixJobId netflixJob$NetflixJobId) {
        synchronized (NetflixJobSchedulerPreL.sPendingJobRegistry.mNetflixJobs) {
            return NetflixJobSchedulerPreL.sPendingJobRegistry.mNetflixJobs.get(netflixJob$NetflixJobId.getIntValue());
        }
    }
    
    public static void onAlarmReceived(final Context context, final Intent intent) {
        if (Log.isLoggable()) {
            Log.i("nf_job_scheduler_prel", "onAlarmReceived");
        }
        Label_0101: {
            if (intent.getAction() == null || !intent.getAction().startsWith("NetflixJobId=")) {
                break Label_0101;
            }
            buildPendingJobsFromPrefIfRequired(context);
            try {
                checkAndStartJob(context, getJobExecInfoFromMap(NetflixJob$NetflixJobId.getJobIdByValue(Integer.parseInt(intent.getAction().substring("NetflixJobId=".length())))));
                return;
            }
            catch (NumberFormatException ex) {
                if (Log.isLoggable()) {
                    Log.e("nf_job_scheduler_prel", "onAlarmReceived can't parse action=" + intent.getAction());
                }
                return;
            }
        }
        if (Log.isLoggable()) {
            Log.e("nf_job_scheduler_prel", "onAlarmReceived unknown action" + intent.getAction());
        }
    }
    
    public static void onJobExecutionStarted(final Context context, final NetflixJob$NetflixJobId netflixJob$NetflixJobId) {
        buildPendingJobsFromPrefIfRequired(context);
        final NetflixJobSchedulerPreL$NetflixJobExecInfo jobExecInfoFromMap = getJobExecInfoFromMap(netflixJob$NetflixJobId);
        if (jobExecInfoFromMap != null) {
            jobExecInfoFromMap.mLastExecutionTime = System.currentTimeMillis();
            if (!jobExecInfoFromMap.mNetflixJob.isRepeating()) {
                removeJobExecInfoFromMap(jobExecInfoFromMap.mNetflixJob.getNetflixJobId());
            }
            savePendingJobsToPref(context);
        }
    }
    
    public static void onNetworkConnectivityChanged(final Context context) {
        if (Log.isLoggable()) {
            Log.i("nf_job_scheduler_prel", "onNetworkConnectivityChanged");
        }
        buildPendingJobsFromPrefIfRequired(context);
        checkAllAndStartJobs(context);
    }
    
    private static void removeJobExecInfoFromMap(final NetflixJob$NetflixJobId netflixJob$NetflixJobId) {
        synchronized (NetflixJobSchedulerPreL.sPendingJobRegistry.mNetflixJobs) {
            NetflixJobSchedulerPreL.sPendingJobRegistry.mNetflixJobs.remove(netflixJob$NetflixJobId.getIntValue());
        }
    }
    
    private static void savePendingJobsToPref(final Context context) {
        PreferenceUtils.putStringPref(context, "pending_jobs", new Gson().toJson(NetflixJobSchedulerPreL.sPendingJobRegistry));
        dumpPref(context);
    }
    
    private static void setJobExecInfoToMap(final NetflixJobSchedulerPreL$NetflixJobExecInfo netflixJobSchedulerPreL$NetflixJobExecInfo) {
        synchronized (NetflixJobSchedulerPreL.sPendingJobRegistry.mNetflixJobs) {
            NetflixJobSchedulerPreL.sPendingJobRegistry.mNetflixJobs.put(netflixJobSchedulerPreL$NetflixJobExecInfo.mNetflixJob.getNetflixJobId().getIntValue(), netflixJobSchedulerPreL$NetflixJobExecInfo);
        }
    }
    
    private void setOneOffAlarm(final long n, final PendingIntent pendingIntent) {
        final AlarmManager alarmManager = (AlarmManager)this.mContext.getSystemService("alarm");
        if (alarmManager != null) {
            alarmManager.set(0, System.currentTimeMillis() + n, pendingIntent);
        }
        else if (Log.isLoggable()) {
            Log.e("nf_job_scheduler_prel", "setOneOffAlarm alarmManager is null");
        }
    }
    
    private void setRepeatingAlarm(final long n, final PendingIntent pendingIntent) {
        final AlarmManager alarmManager = (AlarmManager)this.mContext.getSystemService("alarm");
        if (alarmManager != null) {
            alarmManager.setInexactRepeating(0, n, n, pendingIntent);
        }
        else if (Log.isLoggable()) {
            Log.e("nf_job_scheduler_prel", "setRepeatingAlarm alarmManager is null");
        }
    }
    
    @Override
    public void cancelJob(final NetflixJob$NetflixJobId netflixJob$NetflixJobId) {
        if (Log.isLoggable()) {
            Log.i("nf_job_scheduler_prel", "cancelJob " + netflixJob$NetflixJobId);
        }
        if (getJobExecInfoFromMap(netflixJob$NetflixJobId) != null) {
            removeJobExecInfoFromMap(netflixJob$NetflixJobId);
            cancelAlarm(this.mContext, netflixJob$NetflixJobId);
            savePendingJobsToPref(this.mContext);
        }
    }
    
    @Override
    public boolean isJobScheduled(final NetflixJob$NetflixJobId netflixJob$NetflixJobId) {
        if (Log.isLoggable()) {
            Log.i("nf_job_scheduler_prel", "isJobScheduled netflixJobId=" + netflixJob$NetflixJobId);
        }
        dumpPref(this.mContext);
        return getJobExecInfoFromMap(netflixJob$NetflixJobId) != null;
    }
    
    @Override
    public void onJobFinished(final NetflixJob$NetflixJobId netflixJob$NetflixJobId, final boolean b) {
        if (Log.isLoggable()) {
            Log.i("nf_job_scheduler_prel", "onJobFinished");
        }
    }
    
    @Override
    public void scheduleJob(final NetflixJob mNetflixJob) {
        final NetflixJob$NetflixJobId netflixJobId = mNetflixJob.getNetflixJobId();
        if (Log.isLoggable()) {
            Log.i("nf_job_scheduler_prel", "scheduleJob netflixJobId=" + netflixJobId);
        }
        if (getJobExecInfoFromMap(netflixJobId) != null) {
            if (Log.isLoggable()) {
                Log.e("nf_job_scheduler_prel", "Trying to schedule a job while it already exists.. cancelling");
            }
            this.cancelJob(mNetflixJob.getNetflixJobId());
        }
        final NetflixJobSchedulerPreL$NetflixJobExecInfo jobExecInfoToMap = new NetflixJobSchedulerPreL$NetflixJobExecInfo(this);
        jobExecInfoToMap.mLastExecutionTime = 0L;
        jobExecInfoToMap.mRequestIssueTime = System.currentTimeMillis();
        jobExecInfoToMap.mNetflixJob = mNetflixJob;
        setJobExecInfoToMap(jobExecInfoToMap);
        savePendingJobsToPref(this.mContext);
        final Intent intent = new Intent(this.mContext, (Class)NetflixAlarmReceiver.class);
        intent.setAction("NetflixJobId=" + netflixJobId.getIntValue());
        final PendingIntent broadcast = PendingIntent.getBroadcast(this.mContext, 0, intent, 268435456);
        if (broadcast != null) {
            if (mNetflixJob.isRepeating()) {
                if (Log.isLoggable()) {
                    Log.i("nf_job_scheduler_prel", "setting alarm job=" + mNetflixJob.getNetflixJobId());
                }
                this.setRepeatingAlarm(mNetflixJob.getRepeatingPeriodInMs(), broadcast);
            }
            else {
                if (mNetflixJob.getMinimumDelay() > 0L) {
                    this.setOneOffAlarm(mNetflixJob.getMinimumDelay(), broadcast);
                    return;
                }
                if (Log.isLoggable()) {
                    Log.i("nf_job_scheduler_prel", "non-repeating job, no minimum delay. no alarm set. job=" + mNetflixJob.getNetflixJobId());
                }
            }
        }
        else if (Log.isLoggable()) {
            Log.e("nf_job_scheduler_prel", "no alarm set");
        }
    }
}
