// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.search;

import com.netflix.mediaclient.service.logging.search.model.SearchFocusSessionStartedEvent;
import com.netflix.mediaclient.service.logging.search.model.SearchFocusSessionEndedEvent;
import com.netflix.mediaclient.service.logging.apm.BaseApmSession;

public class SearchFocusSession extends BaseApmSession
{
    public static final String CATEGORY = "uiView";
    public static final String NAME = "focus";
    
    public SearchFocusSession(final long id) {
        this.setId(id);
    }
    
    public SearchFocusSessionEndedEvent createEndedEvent() {
        final SearchFocusSessionEndedEvent searchFocusSessionEndedEvent = new SearchFocusSessionEndedEvent(System.currentTimeMillis() - this.mStarted);
        searchFocusSessionEndedEvent.setCategory(this.getCategory());
        searchFocusSessionEndedEvent.setSessionId(this.mId);
        return searchFocusSessionEndedEvent;
    }
    
    public SearchFocusSessionStartedEvent createStartEvent(final String s) {
        final SearchFocusSessionStartedEvent searchFocusSessionStartedEvent = new SearchFocusSessionStartedEvent(s);
        searchFocusSessionStartedEvent.setCategory(this.getCategory());
        searchFocusSessionStartedEvent.setSessionId(this.mId);
        return searchFocusSessionStartedEvent;
    }
    
    @Override
    public String getCategory() {
        return "uiView";
    }
    
    @Override
    public String getName() {
        return "focus";
    }
}
