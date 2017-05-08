// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.perf;

import org.json.JSONObject;
import java.util.Map;
import com.netflix.mediaclient.service.logging.client.model.DiscreteEvent;

final class PerformanceProfiler$1 extends DiscreteEvent
{
    final /* synthetic */ Map val$params;
    
    PerformanceProfiler$1(final Map val$params) {
        this.val$params = val$params;
    }
    
    @Override
    protected JSONObject getCustomData() {
        if (this.val$params != null && !this.val$params.isEmpty()) {
            return new JSONObject(this.val$params);
        }
        return super.getCustomData();
    }
}
