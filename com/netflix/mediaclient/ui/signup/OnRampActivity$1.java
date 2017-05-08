// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.signup;

import android.widget.Toast;
import com.netflix.mediaclient.android.widget.AlertDialogFactory$TwoButtonAlertDialogDescriptor;
import com.netflix.mediaclient.android.widget.AlertDialogFactory;
import com.netflix.mediaclient.android.widget.AlertDialogFactory$AlertDialogDescriptor;
import java.util.Map;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.servicemgr.CustomerServiceLogging$EntryPoint;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import android.content.Context;
import com.netflix.mediaclient.service.configuration.PersistentConfig;
import android.app.Activity;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import java.util.concurrent.TimeUnit;
import android.annotation.SuppressLint;
import com.netflix.mediaclient.service.logging.perf.Sessions;
import com.netflix.mediaclient.service.logging.perf.PerformanceProfiler;
import com.netflix.mediaclient.Log;

class OnRampActivity$1 implements Runnable
{
    final /* synthetic */ OnRampActivity this$0;
    
    OnRampActivity$1(final OnRampActivity this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        Log.d("OnRampActivity", "Timeout triggered");
        if (!this.this$0.onLoadedBeenCalled && !this.this$0.isFinishing()) {
            PerformanceProfiler.getInstance().endSession(Sessions.ONRAMP_TTR, PerformanceProfiler.createFailedMap());
            this.this$0.finish();
        }
    }
}
