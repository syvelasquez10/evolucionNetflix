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

@TargetApi(21)
public class NetflixJobService extends JobService implements ServiceManagerHelper$ServiceManagerHelperListener
{
    private static final String JOB_COMPLETE_INTENT = "com.netflix.mediaclient.service.job.netflixjobservice.jobcomplete";
    private static final String NETFLIX_JOB_FINISHED_NEEDS_RESCHEDULE = "needsReschedule";
    private static final String NETFLIX_JOB_ID = "NetflixJobId=";
    private static final String TAG = "nf_job_service_l";
    private final BroadcastReceiver mJobFinishReceiver;
    private final Map<NetflixJob$NetflixJobId, JobParameters> mJobsParamsInfoMap;
    private final List<NetflixJob$NetflixJobId> mJobsWaitingForServiceManager;
    private final Handler mMainHandler;
    private ServiceManagerHelper mServiceManagerHelper;
    
    public NetflixJobService() {
        this.mJobsWaitingForServiceManager = new ArrayList<NetflixJob$NetflixJobId>();
        this.mJobsParamsInfoMap = new HashMap<NetflixJob$NetflixJobId, JobParameters>();
        this.mMainHandler = new Handler();
        this.mJobFinishReceiver = new NetflixJobService$2(this);
    }
    
    private void callAndroidJobFinish(final JobParameters jobParameters) {
        if (isAndroidJobFinishDisabled(this.getApplicationContext())) {
            Log.i("nf_job_service_l", "not calling Android JobService jobFinish");
            return;
        }
        Log.i("nf_job_service_l", "calling Android JobService jobFinish");
        this.jobFinished(jobParameters, false);
    }
    
    private void createServiceManagerHelperIfRequired() {
        if (this.mServiceManagerHelper == null) {
            this.mServiceManagerHelper = new ServiceManagerHelper(this.getApplicationContext(), this);
        }
    }
    
    private void executeJobs() {
        if (this.mServiceManagerHelper != null) {
            final Iterator<NetflixJob$NetflixJobId> iterator = this.mJobsWaitingForServiceManager.iterator();
            while (iterator.hasNext()) {
                this.mServiceManagerHelper.startJob(iterator.next());
                iterator.remove();
            }
        }
    }
    
    private static boolean isAndroidJobFinishDisabled(final Context context) {
        return context == null || AndroidJobSchedulerConfig.isJobFinishDisabled(context);
    }
    
    private void markAllPendingJobsFinished() {
        if (Log.isLoggable()) {
            Log.i("nf_job_service_l", "markAllPendingJobsFinished");
        }
        final Iterator<Map.Entry<NetflixJob$NetflixJobId, JobParameters>> iterator = this.mJobsParamsInfoMap.entrySet().iterator();
        while (iterator.hasNext()) {
            final JobParameters jobParameters = iterator.next().getValue();
            Log.i("nf_job_service_l", "markAllPendingJobsFinished calling jobFinish");
            iterator.remove();
            this.callAndroidJobFinish(jobParameters);
        }
    }
    
    private void onJobFinishBroadcast(final Intent intent) {
        final NetflixJob$NetflixJobId jobIdByValue = NetflixJob$NetflixJobId.getJobIdByValue(intent.getIntExtra("NetflixJobId=", NetflixJob$NetflixJobId.UNKNOWN_JOB_ID.getIntValue()));
        if (Log.isLoggable()) {
            Log.i("nf_job_service_l", "mJobFinishReceiver onReceive jobId=" + jobIdByValue);
        }
        final JobParameters jobParameters = this.mJobsParamsInfoMap.get(jobIdByValue);
        if (jobParameters != null) {
            this.mJobsParamsInfoMap.remove(jobIdByValue);
            this.callAndroidJobFinish(jobParameters);
        }
    }
    
    private void releaseServiceManagerHelper() {
        if (this.mServiceManagerHelper != null) {
            this.mServiceManagerHelper.destroy();
            this.mServiceManagerHelper = null;
        }
    }
    
    public static void sendJobFinishBroadcast(final Context context, final NetflixJob$NetflixJobId netflixJob$NetflixJobId, final boolean b) {
        final Intent intent = new Intent("com.netflix.mediaclient.service.job.netflixjobservice.jobcomplete");
        intent.putExtra("NetflixJobId=", netflixJob$NetflixJobId.getIntValue());
        intent.putExtra("needsReschedule", b);
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
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
        this.createServiceManagerHelperIfRequired();
        if (this.mServiceManagerHelper.isServiceManagerFailed()) {
            if (Log.isLoggable()) {
                Log.e("nf_job_service_l", "onStartJob ServiceManager was failed. Can't execute jobId=" + jobIdByValue);
            }
            this.mJobsWaitingForServiceManager.clear();
            this.markAllPendingJobsFinished();
            this.releaseServiceManagerHelper();
            return false;
        }
        this.mJobsParamsInfoMap.put(jobIdByValue, jobParameters);
        if (!this.mJobsWaitingForServiceManager.contains(jobIdByValue)) {
            this.mJobsWaitingForServiceManager.add(jobIdByValue);
        }
        if (this.mServiceManagerHelper.isServiceManagerReady()) {
            this.mMainHandler.post((Runnable)new NetflixJobService$1(this));
        }
        else {
            Log.i("nf_job_service_l", "waiting for serviceManager to be ready");
        }
        return true;
    }
    
    public boolean onStopJob(final JobParameters jobParameters) {
        if (Log.isLoggable()) {
            Log.i("nf_job_service_l", "onStopJob NetflixJobId=" + jobParameters.getJobId());
        }
        final NetflixJob$NetflixJobId jobIdByValue = NetflixJob$NetflixJobId.getJobIdByValue(jobParameters.getJobId());
        this.mJobsParamsInfoMap.remove(jobIdByValue);
        if (this.mServiceManagerHelper != null) {
            this.mServiceManagerHelper.stopJob(jobIdByValue);
        }
        return false;
    }
    
    public void serviceManagerFailed() {
        if (this.mServiceManagerHelper != null) {
            this.markAllPendingJobsFinished();
        }
    }
    
    public void serviceManagerReady() {
        this.executeJobs();
    }
}
