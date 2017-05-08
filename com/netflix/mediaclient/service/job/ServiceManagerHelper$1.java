// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.job;

import android.content.Context;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;

class ServiceManagerHelper$1 implements ManagerStatusListener
{
    final /* synthetic */ ServiceManagerHelper this$0;
    
    ServiceManagerHelper$1(final ServiceManagerHelper this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onManagerReady(final ServiceManager serviceManager, final Status status) {
        if (Log.isLoggable()) {
            Log.i("nf_job_svcmgr_helper", "onManagerReady result=" + status);
        }
        if (this.this$0.mServiceManager == null) {
            Log.i("nf_job_svcmgr_helper", "got a callback even after the mServiceManager release");
            return;
        }
        if (status.isSucces()) {
            this.this$0.mState = ServiceManagerHelper$ServiceManagerState.ServiceManagerReady;
            this.this$0.mServiceManagerHelperListener.serviceManagerReady();
            return;
        }
        this.this$0.mState = ServiceManagerHelper$ServiceManagerState.ServiceManagerFailed;
        this.this$0.mServiceManagerHelperListener.serviceManagerFailed();
    }
    
    @Override
    public void onManagerUnavailable(final ServiceManager serviceManager, final Status status) {
        if (Log.isLoggable()) {
            Log.i("nf_job_svcmgr_helper", "onManagerUnavailable result=" + status);
        }
        this.this$0.mState = ServiceManagerHelper$ServiceManagerState.ServiceManagerFailed;
    }
}
