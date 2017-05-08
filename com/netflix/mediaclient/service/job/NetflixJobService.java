// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.job;

import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.content.Intent;
import android.content.Context;
import com.netflix.mediaclient.Log;
import java.util.Iterator;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import android.app.job.JobParameters;
import java.util.Map;
import android.content.BroadcastReceiver;
import android.annotation.TargetApi;
import android.app.job.JobService;

@TargetApi(21)
public class NetflixJobService extends JobService implements ServiceManagerHelper$ServiceManagerHelperListener
{
    private static final String JOB_COMPLETE_INTENT = "com.netflix.mediaclient.service.job.netflixjobservice.jobcomplete";
    private static final String NETFLIX_JOB_FINISHED_NEEDS_RESCHEDULE = "needsReschedule";
    private static final String NETFLIX_JOB_ID = "NetflixJobId=";
    private static final String TAG = "nf_job_service_l";
    private final BroadcastReceiver mJobFinishReceiver;
    private final Map<NetflixJob$NetflixJobId, JobParameters> mJobsUnderExecutionInfoMap;
    private final List<NetflixJob$NetflixJobId> mJobsWaitingForExecution;
    private ServiceManagerHelper mServiceManagerHelper;
    
    public NetflixJobService() {
        this.mJobsWaitingForExecution = new ArrayList<NetflixJob$NetflixJobId>();
        this.mJobsUnderExecutionInfoMap = new HashMap<NetflixJob$NetflixJobId, JobParameters>();
        this.mJobFinishReceiver = new NetflixJobService$1(this);
    }
    
    private void createServiceManagerHelperIfRequired() {
        if (this.mServiceManagerHelper == null) {
            this.mServiceManagerHelper = new ServiceManagerHelper(this.getApplicationContext(), this);
        }
    }
    
    private void executeJobs() {
        if (this.mServiceManagerHelper != null) {
            final Iterator<NetflixJob$NetflixJobId> iterator = this.mJobsWaitingForExecution.iterator();
            while (iterator.hasNext()) {
                this.mServiceManagerHelper.startJob(iterator.next());
            }
        }
        this.mJobsWaitingForExecution.clear();
    }
    
    private JobParameters getPendingJobParams(final int n) {
        return this.mJobsUnderExecutionInfoMap.get(NetflixJob$NetflixJobId.getJobIdByValue(n));
    }
    
    private void markAllPendingJobsFinished() {
        if (Log.isLoggable()) {
            Log.i("nf_job_service_l", "markAllPendingJobsFinished");
        }
        final Iterator<Map.Entry<NetflixJob$NetflixJobId, JobParameters>> iterator = this.mJobsUnderExecutionInfoMap.entrySet().iterator();
        while (iterator.hasNext()) {
            this.sendJobFinished(iterator.next().getKey().getIntValue(), false);
        }
    }
    
    private void releaseServiceManagerHelper() {
        if (this.mServiceManagerHelper != null) {
            this.mServiceManagerHelper.destroy();
            this.mServiceManagerHelper = null;
        }
    }
    
    private void removePendingJob(final int n) {
        this.mJobsUnderExecutionInfoMap.remove(NetflixJob$NetflixJobId.getJobIdByValue(n));
    }
    
    public static void sendJobFinishBroadcast(final Context context, final NetflixJob$NetflixJobId netflixJob$NetflixJobId, final boolean b) {
        final Intent intent = new Intent("com.netflix.mediaclient.service.job.netflixjobservice.jobcomplete");
        intent.putExtra("NetflixJobId=", netflixJob$NetflixJobId.getIntValue());
        intent.putExtra("needsReschedule", b);
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }
    
    private void sendJobFinished(final int n, final boolean b) {
        final JobParameters pendingJobParams = this.getPendingJobParams(n);
        if (pendingJobParams != null) {
            if (Log.isLoggable()) {
                Log.i("nf_job_service_l", "calling Android JobService jobFinished");
            }
            this.removePendingJob(n);
            this.jobFinished(pendingJobParams, b);
        }
    }
    
    public void onCreate() {
        if (Log.isLoggable()) {
            Log.i("nf_job_service_l", "onCreate");
        }
        super.onCreate();
        this.createServiceManagerHelperIfRequired();
        LocalBroadcastManager.getInstance((Context)this).registerReceiver(this.mJobFinishReceiver, new IntentFilter("com.netflix.mediaclient.service.job.netflixjobservice.jobcomplete"));
    }
    
    public void onDestroy() {
        if (Log.isLoggable()) {
            Log.i("nf_job_service_l", "onDestroy");
        }
        LocalBroadcastManager.getInstance((Context)this).unregisterReceiver(this.mJobFinishReceiver);
        this.releaseServiceManagerHelper();
        super.onDestroy();
    }
    
    public int onStartCommand(final Intent intent, final int n, final int n2) {
        if (Log.isLoggable()) {
            Log.i("nf_job_service_l", "onStartCommand");
        }
        return super.onStartCommand(intent, n, n2);
    }
    
    public boolean onStartJob(final JobParameters jobParameters) {
        if (Log.isLoggable()) {
            Log.i("nf_job_service_l", "onStartJob NetflixJobId=" + jobParameters.getJobId());
        }
        final NetflixJob$NetflixJobId jobIdByValue = NetflixJob$NetflixJobId.getJobIdByValue(jobParameters.getJobId());
        this.mJobsUnderExecutionInfoMap.put(jobIdByValue, jobParameters);
        this.createServiceManagerHelperIfRequired();
        if (this.mServiceManagerHelper.isServiceManagerFailed()) {
            if (Log.isLoggable()) {
                Log.e("nf_job_service_l", "onStartJob ServiceManager was failed. Can't execute jobId=" + jobIdByValue);
            }
            this.mJobsWaitingForExecution.clear();
            this.markAllPendingJobsFinished();
            this.releaseServiceManagerHelper();
            return false;
        }
        if (!this.mJobsWaitingForExecution.contains(jobIdByValue)) {
            this.mJobsWaitingForExecution.add(jobIdByValue);
        }
        if (this.mServiceManagerHelper.isServiceManagerReady()) {
            this.executeJobs();
        }
        else if (Log.isLoggable()) {
            Log.i("nf_job_service_l", "waiting for serviceManager to be ready");
        }
        return true;
    }
    
    public boolean onStopJob(final JobParameters jobParameters) {
        if (Log.isLoggable()) {
            Log.i("nf_job_service_l", "onStopJob NetflixJobId=" + jobParameters.getJobId());
        }
        if (this.mServiceManagerHelper != null) {
            this.mServiceManagerHelper.stopJob(NetflixJob$NetflixJobId.getJobIdByValue(jobParameters.getJobId()));
        }
        this.removePendingJob(jobParameters.getJobId());
        return false;
    }
    
    public void serviceManagerReady() {
        this.executeJobs();
    }
}
