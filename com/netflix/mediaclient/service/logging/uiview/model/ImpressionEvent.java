// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.uiview.model;

import org.json.JSONObject;
import com.netflix.mediaclient.servicemgr.UIViewLogging$UIViewCommandName;
import com.netflix.mediaclient.service.logging.client.model.DiscreteEvent;

public class ImpressionEvent extends DiscreteEvent
{
    protected static final String CATEGORY = "uiView";
    protected static final String NAME = "impression";
    protected static final String TRACK_ID = "trackId";
    protected static final String VIEW = "view";
    private int mTrackId;
    private UIViewLogging$UIViewCommandName mView;
    
    public ImpressionEvent(final UIViewLogging$UIViewCommandName mView, final int mTrackId) {
        this.name = "impression";
        this.category = "uiView";
        this.mView = mView;
        this.mTrackId = mTrackId;
    }
    
    @Override
    protected JSONObject getData() {
        JSONObject data;
        if ((data = super.getData()) == null) {
            data = new JSONObject();
        }
        if (this.mView != null) {
            data.put("view", (Object)this.mView.name());
        }
        data.put("trackId", this.mTrackId);
        return data;
    }
}
