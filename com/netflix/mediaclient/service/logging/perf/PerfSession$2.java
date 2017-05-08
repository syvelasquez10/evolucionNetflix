// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.perf;

import org.json.JSONObject;
import com.netflix.mediaclient.service.logging.client.model.SessionStartedEvent;
import java.util.Map;
import com.netflix.mediaclient.service.logging.client.model.SessionEndedEvent;

class PerfSession$2 extends SessionEndedEvent
{
    final /* synthetic */ PerfSession this$0;
    final /* synthetic */ Map val$params;
    
    PerfSession$2(final PerfSession this$0, final SessionStartedEvent sessionStartedEvent, final long n, final Map val$params) {
        this.this$0 = this$0;
        this.val$params = val$params;
        super(sessionStartedEvent, n);
    }
    
    @Override
    protected JSONObject getCustomData() {
        if (this.val$params != null && !this.val$params.isEmpty()) {
            return new JSONObject(this.val$params);
        }
        return super.getCustomData();
    }
}
