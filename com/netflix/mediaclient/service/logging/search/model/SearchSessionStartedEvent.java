// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.search.model;

import com.netflix.mediaclient.service.logging.client.model.EventType;
import com.netflix.mediaclient.servicemgr.IClientLogging;
import com.netflix.mediaclient.service.logging.client.model.SessionStartedEvent;

public class SearchSessionStartedEvent extends SessionStartedEvent
{
    private static final String APP_SESSION_NAME = "search";
    
    public SearchSessionStartedEvent() {
        super("search");
    }
    
    @Override
    public String getCategory() {
        return "search";
    }
    
    @Override
    public IClientLogging.ModalView getModalView() {
        return IClientLogging.ModalView.search;
    }
    
    @Override
    public String getName() {
        return "session.started";
    }
    
    @Override
    public String getSessionName() {
        return "search";
    }
    
    @Override
    public EventType getType() {
        return EventType.sessionStarted;
    }
}
