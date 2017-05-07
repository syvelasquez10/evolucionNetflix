// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.search.model;

import org.json.JSONException;
import com.netflix.mediaclient.util.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import com.netflix.mediaclient.servicemgr.IClientLogging;
import com.netflix.mediaclient.service.logging.client.model.EventType;
import com.netflix.mediaclient.service.logging.client.model.SessionStartedEvent;

public class SearchFocusSessionStartedEvent extends SessionStartedEvent
{
    private static final String APP_SESSION_NAME = "focus";
    private static final String CATEGORY = "uiView";
    private static final String NAME = "focus.started";
    private String query;
    
    public SearchFocusSessionStartedEvent(final String query) {
        super("focus");
        this.query = query;
        this.setupAttributes();
    }
    
    private void setupAttributes() {
        this.sessionName = "focus";
        this.type = EventType.sessionStarted;
        this.modalView = IClientLogging.ModalView.search;
        this.category = "uiView";
        this.name = "focus.started";
    }
    
    @Override
    protected JSONObject getData() throws JSONException {
        JSONObject data;
        if ((data = super.getData()) == null) {
            data = new JSONObject();
        }
        final JSONArray jsonArray = new JSONArray();
        if (StringUtils.isNotEmpty(this.query)) {
            jsonArray.put((Object)this.query);
        }
        data.put("path", (Object)jsonArray);
        return data;
    }
}
