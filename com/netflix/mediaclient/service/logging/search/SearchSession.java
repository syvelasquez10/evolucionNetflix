// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.search;

import com.netflix.mediaclient.service.logging.search.model.SearchSessionStartedEvent;
import com.netflix.mediaclient.service.logging.search.model.SearchSessionEndedEvent;
import com.netflix.mediaclient.service.logging.client.model.DeviceUniqueId;
import com.netflix.mediaclient.service.logging.apm.BaseApmSession;

public class SearchSession extends BaseApmSession
{
    public static final String CATEGORY = "search";
    public static final String NAME = "search";
    private DeviceUniqueId mId;
    
    public SearchSession(final long n) {
        this.mId = new DeviceUniqueId(n);
    }
    
    public SearchSessionEndedEvent createEndedEvent() {
        final SearchSessionEndedEvent searchSessionEndedEvent = new SearchSessionEndedEvent(System.currentTimeMillis() - this.mStarted);
        searchSessionEndedEvent.setCategory(this.getCategory());
        searchSessionEndedEvent.setSessionId(this.mId);
        return searchSessionEndedEvent;
    }
    
    public SearchSessionStartedEvent createStartEvent() {
        final SearchSessionStartedEvent searchSessionStartedEvent = new SearchSessionStartedEvent();
        searchSessionStartedEvent.setCategory(this.getCategory());
        searchSessionStartedEvent.setSessionId(this.mId);
        return searchSessionStartedEvent;
    }
    
    @Override
    public String getCategory() {
        return "search";
    }
    
    @Override
    public String getName() {
        return "search";
    }
}
