// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.search.model;

import org.json.JSONArray;
import org.json.JSONObject;
import com.netflix.mediaclient.service.logging.client.model.EventType;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.servicemgr.ISearchLogging$InputMode;
import com.netflix.mediaclient.service.logging.client.model.DiscreteEvent;

public class SearchEditEvent extends DiscreteEvent
{
    private static final String CATEGORY = "uiView";
    private static final String NAME = "edit";
    private ISearchLogging$InputMode inputMode;
    private String query;
    
    public SearchEditEvent(final String query, final ISearchLogging$InputMode inputMode) {
        this.query = query;
        this.inputMode = inputMode;
        this.setupAttributes();
    }
    
    private void setupAttributes() {
        this.modalView = IClientLogging$ModalView.search;
        this.type = EventType.edit;
        this.category = "uiView";
        this.name = "edit";
    }
    
    @Override
    protected JSONObject getData() {
        JSONObject data;
        if ((data = super.getData()) == null) {
            data = new JSONObject();
        }
        data.put("path", (Object)new JSONArray("['searchQuery']"));
        data.put("value", (Object)this.query);
        if (this.inputMode == null) {
            this.inputMode = ISearchLogging$InputMode.keyboard;
        }
        data.put("mode", (Object)this.inputMode.toString());
        return data;
    }
}
