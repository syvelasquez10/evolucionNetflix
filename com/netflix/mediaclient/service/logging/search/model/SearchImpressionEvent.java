// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.search.model;

import com.netflix.mediaclient.service.logging.client.model.EventType;
import org.json.JSONException;
import org.json.JSONArray;
import org.json.JSONObject;
import com.netflix.mediaclient.servicemgr.IClientLogging;
import com.netflix.mediaclient.service.logging.client.model.DiscreteEvent;

public class SearchImpressionEvent extends DiscreteEvent
{
    public static final String CATEGORY = "uiEvent";
    private String[] childIds;
    private int from;
    private IClientLogging.ModalView modalView;
    private int to;
    private int trackId;
    private IClientLogging.ModalView view;
    
    public SearchImpressionEvent(final int trackId, final int from, final int to, final String[] childIds, final IClientLogging.ModalView modalView, final IClientLogging.ModalView view) {
        this.modalView = modalView;
        this.childIds = childIds;
        this.trackId = trackId;
        this.from = from;
        this.view = view;
        this.to = to;
    }
    
    private String idsToString() {
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.childIds.length; ++i) {
            sb.append('\'').append(this.childIds[i]).append('\'');
            if (i == this.childIds.length - 1) {
                break;
            }
            sb.append(',');
        }
        return sb.toString();
    }
    
    @Override
    public String getCategory() {
        return "uiEvent";
    }
    
    @Override
    protected JSONObject getData() throws JSONException {
        JSONObject data;
        if ((data = super.getData()) == null) {
            data = new JSONObject();
        }
        String s;
        if (this.childIds != null && this.childIds.length > 0) {
            s = String.format("['search', 'lists', '%d',{'from': %d, 'to': %d,'ids':[%s]}]", this.trackId, this.from, this.to, this.idsToString());
        }
        else {
            s = String.format("['search', 'lists', '%d',{'from': %d, 'to': %d}]", this.trackId, this.from, this.to);
        }
        data.put("path", (Object)new JSONArray(s));
        if (this.view != null) {
            data.put("view", (Object)this.view.name());
        }
        return data;
    }
    
    @Override
    public String getName() {
        return "impression";
    }
    
    @Override
    public EventType getType() {
        return EventType.event;
    }
    
    public IClientLogging.ModalView getView() {
        return this.modalView;
    }
}
