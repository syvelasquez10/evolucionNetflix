// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.social.model;

import org.json.JSONObject;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;

public final class RecommendPanelSearchedEvent extends BaseSocialDiscreteEvent
{
    protected static final String NAME = "recommendPanelSearched";
    private String mOriginatingRequestGuid;
    private int mTrackId;
    private IClientLogging$ModalView mView;
    
    public RecommendPanelSearchedEvent(final IClientLogging$ModalView mView, final String mOriginatingRequestGuid, final int mTrackId) {
        super("recommendPanelSearched");
        this.mView = mView;
        this.mOriginatingRequestGuid = mOriginatingRequestGuid;
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
        if (this.mOriginatingRequestGuid != null) {
            data.put("originatingRequestGuid", (Object)this.mOriginatingRequestGuid);
        }
        return data;
    }
}
