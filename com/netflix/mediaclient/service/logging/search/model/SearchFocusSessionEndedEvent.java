// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.search.model;

import org.json.JSONArray;
import org.json.JSONObject;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.service.logging.client.model.EventType;
import com.netflix.mediaclient.service.logging.client.model.DeviceUniqueId;
import com.netflix.mediaclient.service.logging.client.model.SessionEndedEvent;

public final class SearchFocusSessionEndedEvent extends SessionEndedEvent
{
    private static final String APP_SESSION_NAME = "focus";
    private static final String CATEGORY = "uiView";
    private static final String NAME = "focus.ended";
    
    public SearchFocusSessionEndedEvent(final long n) {
        super("focus", new DeviceUniqueId(), n);
        this.setupAttributes();
    }
    
    private void setupAttributes() {
        this.sessionName = "focus";
        this.type = EventType.sessionEnded;
        this.modalView = IClientLogging$ModalView.search;
        this.category = "uiView";
        this.name = "focus.ended";
    }
    
    @Override
    protected JSONObject getData() {
        JSONObject data;
        if ((data = super.getData()) == null) {
            data = new JSONObject();
        }
        data.put("path", (Object)new JSONArray());
        return data;
    }
}
