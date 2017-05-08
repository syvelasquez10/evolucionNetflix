// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.job;

import android.app.job.JobInfo$Builder;
import android.content.ComponentName;
import java.util.Iterator;
import java.util.List;
import android.app.job.JobInfo;
import com.netflix.mediaclient.Log;
import android.app.job.JobScheduler;
import android.content.Context;
import android.annotation.TargetApi;

@TargetApi(21)
public class NetflixJobSchedulerImpl implements NetflixJobScheduler
{
    private static final String TAG = "nf_job_scheduler";
    private final Context mContext;
    
    public NetflixJobSchedulerImpl(final Context mContext) {
        this.mContext = mContext;
    }
    
    private void cancelJobIfExists(final JobScheduler jobScheduler, final int n) {
        final JobInfo pendingJobByJobId = this.getPendingJobByJobId(jobScheduler, n);
        if (pendingJobByJobId != null) {
            if (Log.isLoggable()) {
                Log.d("nf_job_scheduler", "cancelJobIfExists cancelling..");
            }
            jobScheduler.cancel(pendingJobByJobId.getId());
        }
    }
    
    private JobInfo getPendingJobByJobId(final JobScheduler jobScheduler, final int n) {
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
        if (Log.isLoggable()) {
            Log.d("nf_job_scheduler", "cancelJob jobId=" + netflixJob$NetflixJobId);
        }
        this.cancelJobIfExists((JobScheduler)this.mContext.getSystemService("jobscheduler"), netflixJob$NetflixJobId.getIntValue());
    }
    
    @Override
    public boolean isJobScheduled(final NetflixJob$NetflixJobId netflixJob$NetflixJobId) {
        if (Log.isLoggable()) {
            Log.d("nf_job_scheduler", "isJobScheduled netflixJobId=" + netflixJob$NetflixJobId);
        }
        final JobInfo pendingJobByJobId = this.getPendingJobByJobId((JobScheduler)this.mContext.getSystemService("jobscheduler"), netflixJob$NetflixJobId.getIntValue());
        if (Log.isLoggable()) {
            Log.d("nf_job_scheduler", "isJobScheduled: Job Info = " + pendingJobByJobId);
        }
        return pendingJobByJobId != null;
    }
    
    @Override
    public void onJobFinished(final NetflixJob$NetflixJobId netflixJob$NetflixJobId, final boolean b) {
        NetflixJobService.sendJobFinishBroadcast(this.mContext, netflixJob$NetflixJobId, b);
    }
    
    @Override
    public void scheduleJob(final NetflixJob netflixJob) {
        if (Log.isLoggable()) {
            Log.d("nf_job_scheduler", "scheduleJob jobId=" + netflixJob.getNetflixJobId());
        }
        final JobScheduler jobScheduler = (JobScheduler)this.mContext.getSystemService("jobscheduler");
        this.cancelJobIfExists(jobScheduler, netflixJob.getNetflixJobId().getIntValue());
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
