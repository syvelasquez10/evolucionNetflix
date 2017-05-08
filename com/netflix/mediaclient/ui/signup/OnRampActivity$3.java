// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.signup;

import com.netflix.model.leafs.OnRampEligibility$Action;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;

class OnRampActivity$3 implements ManagerStatusListener
{
    final /* synthetic */ OnRampActivity this$0;
    
    OnRampActivity$3(final OnRampActivity this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onManagerReady(final ServiceManager serviceManager, final Status status) {
        if (Log.isLoggable()) {
            Log.d("OnRampActivity", "ServiceManager ready: " + status.getStatusCode());
        }
        serviceManager.createAutoLoginToken(3600000L, new OnRampActivity$3$1(this, serviceManager));
        if (serviceManager.getCurrentProfile() != null) {
            serviceManager.doOnRampEligibilityAction(OnRampEligibility$Action.RECORD, null);
        }
    }
    
    @Override
    public void onManagerUnavailable(final ServiceManager serviceManager, final Status status) {
        Log.e("OnRampActivity", "NetflixService is NOT available!");
        this.this$0.finish();
    }
}
