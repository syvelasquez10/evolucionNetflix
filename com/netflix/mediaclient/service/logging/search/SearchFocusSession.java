// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.search;

import com.netflix.mediaclient.service.logging.search.model.SearchFocusSessionStartedEvent;
import com.netflix.mediaclient.service.logging.search.model.SearchFocusSessionEndedEvent;
import com.netflix.mediaclient.service.logging.client.model.DeviceUniqueId;
import com.netflix.mediaclient.service.logging.apm.BaseApmSession;

public class SearchFocusSession extends BaseApmSession
{
    public static final String CATEGORY = "search";
    public static final String NAME = "focus";
    private DeviceUniqueId mId;
    
    public SearchFocusSession(final long n) {
        this.mId = new DeviceUniqueId(n);
    }
    
    public SearchFocusSessionEndedEvent createEndedEvent() {
        final SearchFocusSessionEndedEvent searchFocusSessionEndedEvent = new SearchFocusSessionEndedEvent(System.currentTimeMillis() - this.mStarted);
        searchFocusSessionEndedEvent.setCategory(this.getCategory());
        searchFocusSessionEndedEvent.setSessionId(this.mId);
        return searchFocusSessionEndedEvent;
    }
    
    public SearchFocusSessionStartedEvent createStartEvent() {
        final SearchFocusSessionStartedEvent searchFocusSessionStartedEvent = new SearchFocusSessionStartedEvent();
        searchFocusSessionStartedEvent.setCategory(this.getCategory());
        searchFocusSessionStartedEvent.setSessionId(this.mId);
        return searchFocusSessionStartedEvent;
    }
    
    @Override
    public String getCategory() {
        return "search";
    }
    
    @Override
    public String getName() {
        return "focus";
    }
}
