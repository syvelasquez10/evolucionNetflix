// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.social.model;

import org.json.JSONException;
import com.netflix.mediaclient.util.log.ConsolidatedLoggingUtils;
import org.json.JSONObject;
import com.netflix.mediaclient.servicemgr.IClientLogging;

public final class RecommendPanelSearchedEvent extends BaseSocialDiscreteEvent
{
    protected static final String NAME = "recommendPanelSearched";
    private int mTrackId;
    private IClientLogging.ModalView mView;
    
    public RecommendPanelSearchedEvent(final IClientLogging.ModalView mView, final int mTrackId) {
        super("recommendPanelSearched");
        this.mView = mView;
        this.mTrackId = mTrackId;
    }
    
    @Override
    protected JSONObject getData() throws JSONException {
        JSONObject data;
        if ((data = super.getData()) == null) {
            data = new JSONObject();
        }
        if (this.mView != null) {
            data.put("view", (Object)this.mView.name());
        }
        data.put("trackId", this.mTrackId);
        data.put("originatingRequestGuid", (Object)ConsolidatedLoggingUtils.createGUID());
        return data;
    }
}
