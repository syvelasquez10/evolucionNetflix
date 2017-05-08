// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.signup;

import android.widget.Toast;
import com.netflix.mediaclient.android.widget.AlertDialogFactory$TwoButtonAlertDialogDescriptor;
import com.netflix.mediaclient.android.widget.AlertDialogFactory;
import com.netflix.mediaclient.android.widget.AlertDialogFactory$AlertDialogDescriptor;
import java.util.Map;
import com.netflix.mediaclient.service.logging.perf.Sessions;
import com.netflix.mediaclient.service.logging.perf.PerformanceProfiler;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.servicemgr.CustomerServiceLogging$EntryPoint;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.model.leafs.OnRampEligibility$Action;
import android.content.Context;
import com.netflix.mediaclient.service.configuration.PersistentConfig;
import android.app.Activity;
import java.util.concurrent.TimeUnit;
import android.annotation.SuppressLint;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.servicemgr.SimpleManagerCallback;

class OnRampActivity$3$1 extends SimpleManagerCallback
{
    final /* synthetic */ OnRampActivity$3 this$1;
    final /* synthetic */ ServiceManager val$svcManager;
    
    OnRampActivity$3$1(final OnRampActivity$3 this$1, final ServiceManager val$svcManager) {
        this.this$1 = this$1;
        this.val$svcManager = val$svcManager;
    }
    
    @Override
    public void onAutoLoginTokenCreated(final String s, final Status status) {
        if (status.isSucces()) {
            this.this$1.this$0.mBootUrl = "https://www.netflix.com/welcome/onramp?isProfilesOnRamp=true&nftoken=" + s;
            this.this$1.this$0.setViews(this.val$svcManager, false);
            return;
        }
        if (Log.isLoggable()) {
            Log.d("OnRampActivity", "Couldn't fetch token!");
        }
        this.this$1.this$0.finish();
    }
}
