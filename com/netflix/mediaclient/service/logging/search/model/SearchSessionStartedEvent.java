// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.search.model;

import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.service.logging.client.model.EventType;
import com.netflix.mediaclient.service.logging.client.model.SessionStartedEvent;

public class SearchSessionStartedEvent extends SessionStartedEvent
{
    private static final String APP_SESSION_NAME = "search";
    private static final String CATEGORY = "search";
    private static final String NAME = "session.started";
    
    public SearchSessionStartedEvent() {
        super("search");
        this.setupAttributes();
    }
    
    private void setupAttributes() {
        this.type = EventType.sessionStarted;
        this.sessionName = "search";
        this.modalView = IClientLogging$ModalView.search;
        this.category = "search";
        this.name = "session.started";
    }
}
