// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.social.model;

import org.json.JSONException;
import com.netflix.mediaclient.util.log.ConsolidatedLoggingUtils;
import org.json.JSONObject;
import com.netflix.mediaclient.servicemgr.IClientLogging;

public final class RecommendMessageAddedEvent extends BaseSocialDiscreteEvent
{
    protected static final String NAME = "recommendMessageAdded ";
    private int mTrackId;
    private IClientLogging.ModalView mView;
    
    public RecommendMessageAddedEvent(final IClientLogging.ModalView mView, final int mTrackId) {
        super("recommendMessageAdded ");
        this.mView = mView;
        this.mTrackId = mTrackId;
    }
    
    @Override
    protected JSONObject getData() throws JSONException {
        JSONObject data;
        if ((data = super.getData()) == null) {
            data = new JSONObject();
        }
        data.put("trackId", this.mTrackId);
        if (this.mView != null) {
            data.put("view", (Object)this.mView.name());
        }
        data.put("originatingRequestGuid", (Object)ConsolidatedLoggingUtils.createGUID());
        return data;
    }
}
