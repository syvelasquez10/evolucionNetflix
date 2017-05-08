// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.signup;

import com.netflix.mediaclient.service.logging.perf.Sessions;
import com.netflix.mediaclient.service.logging.perf.PerformanceProfiler;
import com.netflix.mediaclient.Log;

class OnRampActivity$3 implements Runnable
{
    final /* synthetic */ OnRampActivity this$0;
    
    OnRampActivity$3(final OnRampActivity this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        Log.d("OnRampActivity", "Handling error during Onramp");
        this.this$0.clearCookies();
        PerformanceProfiler.getInstance().endSession(Sessions.ONRAMP_TTR, PerformanceProfiler.createFailedMap());
        this.this$0.finish();
    }
}
