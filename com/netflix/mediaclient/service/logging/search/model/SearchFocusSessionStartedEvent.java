// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.search.model;

import com.netflix.mediaclient.service.logging.client.model.EventType;
import com.netflix.mediaclient.servicemgr.IClientLogging;
import org.json.JSONException;
import org.json.JSONObject;
import com.netflix.mediaclient.service.logging.client.model.SessionStartedEvent;

public class SearchFocusSessionStartedEvent extends SessionStartedEvent
{
    private static final String APP_SESSION_NAME = "focus";
    
    public SearchFocusSessionStartedEvent() {
        super("focus");
    }
    
    @Override
    public String getCategory() {
        return "search";
    }
    
    @Override
    protected JSONObject getData() throws JSONException {
        JSONObject data;
        if ((data = super.getData()) == null) {
            data = new JSONObject();
        }
        data.put("path", (Object)"['']");
        return data;
    }
    
    @Override
    public IClientLogging.ModalView getModalView() {
        return IClientLogging.ModalView.search;
    }
    
    @Override
    public String getName() {
        return "focus.started";
    }
    
    @Override
    public String getSessionName() {
        return "focus";
    }
    
    @Override
    public EventType getType() {
        return EventType.sessionStarted;
    }
}
