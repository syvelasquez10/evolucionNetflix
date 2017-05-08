// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.signup;

import com.netflix.mediaclient.service.logging.perf.Sessions;
import com.netflix.mediaclient.service.logging.perf.PerformanceProfiler;
import com.netflix.mediaclient.Log;

class OnRampActivity$2 implements Runnable
{
    final /* synthetic */ OnRampActivity this$0;
    
    OnRampActivity$2(final OnRampActivity this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        Log.d("OnRampActivity", "Timeout triggered, switching to LoginActivity");
        if (!this.this$0.isWebViewLoaded() && !this.this$0.isFinishing()) {
            PerformanceProfiler.getInstance().endSession(Sessions.ONRAMP_TTR, PerformanceProfiler.createFailedMap());
            this.this$0.finish();
        }
    }
}
