// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.search;

import com.netflix.mediaclient.service.logging.search.model.SearchSessionStartedEvent;
import com.netflix.mediaclient.service.logging.search.model.SearchSessionEndedEvent;
import com.netflix.mediaclient.service.logging.apm.BaseApmSession;

public class SearchSession extends BaseApmSession
{
    private static final String CATEGORY = "search";
    public static final String NAME = "search";
    
    public SearchSession(final long id) {
        this.setId(id);
    }
    
    public SearchSessionEndedEvent createEndedEvent() {
        final SearchSessionEndedEvent searchSessionEndedEvent = new SearchSessionEndedEvent(System.currentTimeMillis() - this.mStarted);
        searchSessionEndedEvent.setSessionId(this.mId);
        return searchSessionEndedEvent;
    }
    
    public SearchSessionStartedEvent createStartEvent() {
        final SearchSessionStartedEvent searchSessionStartedEvent = new SearchSessionStartedEvent();
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
