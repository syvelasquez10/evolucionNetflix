// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.job;

import android.content.Context;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;

class ServiceManagerHelper
{
    private static final String TAG = "nf_job_svcmgr_helper";
    private final ManagerStatusListener mManagerStatusListener;
    private ServiceManager mServiceManager;
    private final ServiceManagerHelper$ServiceManagerHelperListener mServiceManagerHelperListener;
    private ServiceManagerHelper$ServiceManagerState mState;
    
    public ServiceManagerHelper(final Context context, final ServiceManagerHelper$ServiceManagerHelperListener mServiceManagerHelperListener) {
        this.mState = ServiceManagerHelper$ServiceManagerState.WaitingForResult;
        this.mManagerStatusListener = new ServiceManagerHelper$1(this);
        this.mState = ServiceManagerHelper$ServiceManagerState.WaitingForResult;
        this.mServiceManager = new ServiceManager(context, this.mManagerStatusListener);
        this.mServiceManagerHelperListener = mServiceManagerHelperListener;
    }
    
    public void destroy() {
        if (this.mServiceManager != null) {
            this.mServiceManager.release();
            this.mServiceManager = null;
        }
    }
    
    public boolean isServiceManagerFailed() {
        return this.mState == ServiceManagerHelper$ServiceManagerState.ServiceManagerFailed;
    }
    
    public boolean isServiceManagerReady() {
        return this.mState == ServiceManagerHelper$ServiceManagerState.ServiceManagerReady;
    }
    
    public void startJob(final NetflixJob$NetflixJobId netflixJob$NetflixJobId) {
        if (this.mServiceManager != null) {
            this.mServiceManager.startJob(netflixJob$NetflixJobId);
        }
    }
    
    public void stopJob(final NetflixJob$NetflixJobId netflixJob$NetflixJobId) {
        if (this.mServiceManager != null) {
            this.mServiceManager.stopJob(netflixJob$NetflixJobId);
        }
    }
}
