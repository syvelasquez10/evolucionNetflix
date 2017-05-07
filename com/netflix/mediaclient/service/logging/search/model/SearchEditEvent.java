// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.search.model;

import com.netflix.mediaclient.service.logging.client.model.EventType;
import com.netflix.mediaclient.servicemgr.IClientLogging;
import org.json.JSONException;
import org.json.JSONArray;
import org.json.JSONObject;
import com.netflix.mediaclient.service.logging.client.model.DiscreteEvent;

public class SearchEditEvent extends DiscreteEvent
{
    String query;
    
    public SearchEditEvent(final String query) {
        this.query = query;
    }
    
    @Override
    public String getCategory() {
        return "uiView";
    }
    
    @Override
    protected JSONObject getData() throws JSONException {
        JSONObject data;
        if ((data = super.getData()) == null) {
            data = new JSONObject();
        }
        data.put("path", (Object)new JSONArray("['searchQuery']"));
        data.put("value", (Object)this.query);
        return data;
    }
    
    @Override
    public IClientLogging.ModalView getModalView() {
        return IClientLogging.ModalView.search;
    }
    
    @Override
    public String getName() {
        return "edit";
    }
    
    @Override
    public EventType getType() {
        return EventType.edit;
    }
}
