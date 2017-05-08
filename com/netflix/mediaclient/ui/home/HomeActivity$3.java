// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.home;

import java.util.Map;
import com.netflix.mediaclient.service.logging.perf.Sessions;
import com.netflix.mediaclient.service.logging.perf.PerformanceProfiler;
import com.netflix.mediaclient.service.logging.perf.InteractiveTracker$InteractiveListener;

class HomeActivity$3 implements InteractiveTracker$InteractiveListener
{
    final /* synthetic */ HomeActivity this$0;
    
    HomeActivity$3(final HomeActivity this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onInteractive() {
        this.this$0.getNetflixApplication().setTTRComplete();
        PerformanceProfiler.getInstance().endSession(Sessions.TTR, null);
        PerformanceProfiler.getInstance().flushApmEvents(this.this$0.getApmSafely());
    }
}
