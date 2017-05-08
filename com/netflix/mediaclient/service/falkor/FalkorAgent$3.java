// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.falkor;

import java.util.Map;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.StatusCode;
import com.netflix.mediaclient.service.logging.perf.Sessions;
import com.netflix.mediaclient.service.logging.perf.PerformanceProfiler;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.service.browse.BrowseAgentCallback;
import com.netflix.mediaclient.service.browse.SimpleBrowseAgentCallback;

class FalkorAgent$3 extends SimpleBrowseAgentCallback
{
    final /* synthetic */ FalkorAgent this$0;
    final /* synthetic */ BrowseAgentCallback val$cb;
    
    FalkorAgent$3(final FalkorAgent this$0, final BrowseAgentCallback val$cb) {
        this.this$0 = this$0;
        this.val$cb = val$cb;
    }
    
    @Override
    public void onLoLoMoPrefetched(final Status status) {
        final PerformanceProfiler instance = PerformanceProfiler.getInstance();
        final Sessions lolomo_PREFETCH = Sessions.LOLOMO_PREFETCH;
        Map<String, String> failedMap;
        if (StatusCode.OK.equals(status.getStatusCode())) {
            failedMap = null;
        }
        else {
            failedMap = PerformanceProfiler.createFailedMap();
        }
        instance.endSession(lolomo_PREFETCH, failedMap);
        this.val$cb.onLoLoMoPrefetched(status);
        boolean wasAllDataLocalToCache = false;
        if (status instanceof FalkorAgentStatus) {
            wasAllDataLocalToCache = ((FalkorAgentStatus)status).wasAllDataLocalToCache();
        }
        else {
            Log.w("FalkorAgent", "status is not FalkorAgentStatus");
        }
        if (!wasAllDataLocalToCache) {
            Log.d("FalkorAgent", "nf_preapp notifying of  prefetch done");
            this.this$0.getPreAppAgent().informPrefetched(this.this$0.getContext());
            if (!StatusCode.OK.equals(status.getStatusCode())) {
                Log.w("FalkorAgent", "nf_preapp prefetch failed");
            }
        }
    }
}
