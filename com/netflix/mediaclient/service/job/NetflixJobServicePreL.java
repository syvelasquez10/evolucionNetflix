// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.job;

import android.os.IBinder;
import com.netflix.mediaclient.Log;
import android.content.Intent;
import android.content.Context;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;
import android.app.Service;

public class NetflixJobServicePreL extends Service implements ServiceManagerHelper$ServiceManagerHelperListener
{
    private static final String NETFLIX_JOB = "NetflixJobId";
    private static final String TAG = "nf_job_service_prel";
    private final List<NetflixJob$NetflixJobId> mJobsWaitingForServiceManager;
    private ServiceManagerHelper mServiceManagerHelper;
    
    public NetflixJobServicePreL() {
        this.mJobsWaitingForServiceManager = new ArrayList<NetflixJob$NetflixJobId>();
    }
    
    private void createServiceManagerHelperIfRequired() {
        if (this.mServiceManagerHelper == null) {
            this.mServiceManagerHelper = new ServiceManagerHelper(this.getApplicationContext(), this);
        }
    }
    
    private void executeJobs() {
        if (this.mServiceManagerHelper != null) {
            for (final NetflixJob$NetflixJobId netflixJob$NetflixJobId : this.mJobsWaitingForServiceManager) {
                this.mServiceManagerHelper.startJob(netflixJob$NetflixJobId);
                NetflixJobSchedulerPreL.onJobExecutionStarted(this.getApplicationContext(), netflixJob$NetflixJobId);
            }
        }
        this.mJobsWaitingForServiceManager.clear();
    }
    
    public static Intent getServiceStartIntentForJobId(final Context context, final NetflixJob netflixJob) {
        if (Log.isLoggable()) {
            Log.i("nf_job_service_prel", "getServiceStartIntentForJobId " + netflixJob.getNetflixJobId());
        }
        final Intent intent = new Intent(context, (Class)NetflixJobServicePreL.class);
        intent.putExtra("NetflixJobId", netflixJob.getNetflixJobId().getIntValue());
        return intent;
    }
    
    private void releaseServiceManagerHelper() {
        if (this.mServiceManagerHelper != null) {
            this.mServiceManagerHelper.destroy();
            this.mJobsWaitingForServiceManager.clear();
        }
    }
    
    public IBinder onBind(final Intent intent) {
        return null;
    }
    
    public void onCreate() {
        if (Log.isLoggable()) {
            Log.i("nf_job_service_prel", "onCreate");
        }
        super.onCreate();
        this.createServiceManagerHelperIfRequired();
    }
    
    public void onDestroy() {
        if (Log.isLoggable()) {
            Log.i("nf_job_service_prel", "onDestroy");
        }
        this.releaseServiceManagerHelper();
        super.onDestroy();
    }
    
    public int onStartCommand(final Intent intent, final int n, final int n2) {
        final NetflixJob$NetflixJobId jobIdByValue = NetflixJob$NetflixJobId.getJobIdByValue(intent.getIntExtra("NetflixJobId", NetflixJob$NetflixJobId.UNKNOWN_JOB_ID.getIntValue()));
        if (Log.isLoggable()) {
            Log.i("nf_job_service_prel", "onStartCommand jobId=" + jobIdByValue);
        }
        this.createServiceManagerHelperIfRequired();
        if (this.mServiceManagerHelper.isServiceManagerFailed()) {
            if (Log.isLoggable()) {
                Log.e("nf_job_service_prel", "onStartCommand serviceManager has failed jobId=" + jobIdByValue);
            }
            this.releaseServiceManagerHelper();
            this.mJobsWaitingForServiceManager.clear();
        }
        else {
            if (!this.mJobsWaitingForServiceManager.contains(jobIdByValue)) {
                this.mJobsWaitingForServiceManager.add(jobIdByValue);
            }
            if (this.mServiceManagerHelper.isServiceManagerReady()) {
                this.executeJobs();
                return 2;
            }
            if (Log.isLoggable()) {
                Log.i("nf_job_service_prel", "onStartCommand waiting for serviceManager to be ready");
                return 2;
            }
        }
        return 2;
    }
    
    public void serviceManagerReady() {
        this.executeJobs();
    }
}
