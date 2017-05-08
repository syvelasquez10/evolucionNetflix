// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.job;

import android.app.job.JobInfo$Builder;
import android.content.ComponentName;
import java.util.Iterator;
import java.util.List;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import com.netflix.mediaclient.service.configuration.AndroidJobSchedulerConfig;
import com.netflix.mediaclient.Log;
import android.content.Context;
import android.annotation.TargetApi;

@TargetApi(21)
public class NetflixJobSchedulerImpl implements NetflixJobScheduler
{
    private static final String TAG = "nf_job_scheduler";
    private final Context mContext;
    
    public NetflixJobSchedulerImpl(final Context mContext) {
        Log.i("nf_job_scheduler", "NetflixJobSchedulerImpl");
        this.mContext = mContext;
        if (AndroidJobSchedulerConfig.isJobSchedulerDisabled(mContext)) {
            Log.i("nf_job_scheduler", "NetflixJobSchedulerImpl JobScheduler disabled.");
            cancelAllJobs(this.mContext);
        }
    }
    
    public static void cancelAllJobs(final Context context) {
        Log.i("nf_job_scheduler", "cancelAllJobs");
        if (context != null) {
            final JobScheduler jobScheduler = (JobScheduler)context.getSystemService("jobscheduler");
            final NetflixJob$NetflixJobId[] values = NetflixJob$NetflixJobId.values();
            for (int length = values.length, i = 0; i < length; ++i) {
                cancelJobIfExists(jobScheduler, values[i].getIntValue());
            }
        }
    }
    
    private static void cancelJobIfExists(final JobScheduler jobScheduler, final int n) {
        final JobInfo pendingJobByJobId = getPendingJobByJobId(jobScheduler, n);
        if (pendingJobByJobId != null) {
            Log.i("nf_job_scheduler", "cancelJobIfExists cancelling..");
            jobScheduler.cancel(pendingJobByJobId.getId());
        }
    }
    
    private static JobInfo getPendingJobByJobId(final JobScheduler jobScheduler, final int n) {
        if (jobScheduler == null) {
            return null;
        }
        final List allPendingJobs = jobScheduler.getAllPendingJobs();
        if (allPendingJobs == null) {
            return null;
        }
        for (final JobInfo jobInfo : allPendingJobs) {
            if (jobInfo.getId() == n) {
                return jobInfo;
            }
        }
        return null;
    }
    
    @Override
    public void cancelJob(final NetflixJob$NetflixJobId netflixJob$NetflixJobId) {
        if (AndroidJobSchedulerConfig.isJobSchedulerDisabled(this.mContext)) {
            Log.i("nf_job_scheduler", "cancelJob no-op");
            return;
        }
        if (Log.isLoggable()) {
            Log.i("nf_job_scheduler", "cancelJob jobId=" + netflixJob$NetflixJobId);
        }
        cancelJobIfExists((JobScheduler)this.mContext.getSystemService("jobscheduler"), netflixJob$NetflixJobId.getIntValue());
    }
    
    @Override
    public boolean isJobScheduled(final NetflixJob$NetflixJobId netflixJob$NetflixJobId) {
        if (AndroidJobSchedulerConfig.isJobSchedulerDisabled(this.mContext)) {
            Log.i("nf_job_scheduler", "isJobScheduled no-op");
            return false;
        }
        if (Log.isLoggable()) {
            Log.i("nf_job_scheduler", "isJobScheduled netflixJobId=" + netflixJob$NetflixJobId);
        }
        final JobInfo pendingJobByJobId = getPendingJobByJobId((JobScheduler)this.mContext.getSystemService("jobscheduler"), netflixJob$NetflixJobId.getIntValue());
        if (Log.isLoggable()) {
            Log.d("nf_job_scheduler", "isJobScheduled: Job Info = " + pendingJobByJobId);
        }
        return pendingJobByJobId != null;
    }
    
    @Override
    public void onJobFinished(final NetflixJob$NetflixJobId netflixJob$NetflixJobId, final boolean b) {
        if (AndroidJobSchedulerConfig.isJobFinishDisabled(this.mContext)) {
            Log.i("nf_job_scheduler", "onJobFinished no-op");
            return;
        }
        if (Log.isLoggable()) {
            Log.i("nf_job_scheduler", "onJobFinished " + netflixJob$NetflixJobId);
        }
        NetflixJobService.sendJobFinishBroadcast(this.mContext, netflixJob$NetflixJobId, b);
    }
    
    @Override
    public void scheduleJob(final NetflixJob netflixJob) {
        if (AndroidJobSchedulerConfig.isJobSchedulerDisabled(this.mContext)) {
            Log.i("nf_job_scheduler", "scheduleJob no-op");
            return;
        }
        if (Log.isLoggable()) {
            Log.i("nf_job_scheduler", "scheduleJob jobId=" + netflixJob.getNetflixJobId());
        }
        final JobScheduler jobScheduler = (JobScheduler)this.mContext.getSystemService("jobscheduler");
        cancelJobIfExists(jobScheduler, netflixJob.getNetflixJobId().getIntValue());
        final JobInfo$Builder jobInfo$Builder = new JobInfo$Builder(netflixJob.getNetflixJobId().getIntValue(), new ComponentName(this.mContext.getPackageName(), NetflixJobService.class.getName()));
        if (netflixJob.isRequiresUnmeteredConnection()) {
            jobInfo$Builder.setRequiredNetworkType(2);
        }
        else {
            jobInfo$Builder.setRequiredNetworkType(1);
        }
        if (netflixJob.isRepeating()) {
            jobInfo$Builder.setPeriodic(netflixJob.getRepeatingPeriodInMs());
        }
        else if (netflixJob.getMinimumDelay() > 0L) {
            jobInfo$Builder.setMinimumLatency(netflixJob.getMinimumDelay());
        }
        jobInfo$Builder.setRequiresCharging(netflixJob.getRequiresCharging());
        jobInfo$Builder.setRequiresDeviceIdle(netflixJob.getRequiresIdle());
        jobScheduler.schedule(jobInfo$Builder.build());
    }
}
