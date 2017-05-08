// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.perf;

import org.json.JSONObject;
import java.util.Map;
import com.netflix.mediaclient.service.logging.client.model.SessionStartedEvent;

final class PerfSession$1 extends SessionStartedEvent
{
    final /* synthetic */ Map val$params;
    
    PerfSession$1(final String s, final Map val$params) {
        this.val$params = val$params;
        super(s);
    }
    
    @Override
    protected JSONObject getCustomData() {
        if (this.val$params != null && !this.val$params.isEmpty()) {
            return new JSONObject(this.val$params);
        }
        return super.getCustomData();
    }
}
