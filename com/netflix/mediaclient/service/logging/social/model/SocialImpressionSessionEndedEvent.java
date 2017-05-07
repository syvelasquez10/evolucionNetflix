// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.social.model;

import org.json.JSONException;
import com.netflix.mediaclient.util.log.ConsolidatedLoggingUtils;
import org.json.JSONObject;
import com.netflix.mediaclient.service.logging.client.model.DeviceUniqueId;
import com.netflix.mediaclient.servicemgr.IClientLogging;
import com.netflix.mediaclient.service.logging.client.model.Error;
import com.netflix.mediaclient.service.logging.client.model.SessionEndedEvent;

public final class SocialImpressionSessionEndedEvent extends SessionEndedEvent
{
    protected static final String ERROR = "error";
    protected static final String GUID = "originatingRequestGuid";
    private static final String SESSION_NAME = "impression";
    protected static final String STORY_ID = "storyId";
    protected static final String SUCCESS = "success";
    protected static final String TRACK_ID = "trackId";
    protected static final String VIEW = "view";
    private Error mError;
    private String mStoryId;
    private boolean mSuccess;
    private int mTrackId;
    private IClientLogging.ModalView mView;
    
    public SocialImpressionSessionEndedEvent(final DeviceUniqueId deviceUniqueId, final long n, final IClientLogging.ModalView mView, final String mStoryId, final int mTrackId, final boolean mSuccess, final Error mError) {
        super("impression", deviceUniqueId, n);
        this.mView = mView;
        this.mError = mError;
        this.mSuccess = mSuccess;
        this.mStoryId = mStoryId;
        this.mTrackId = mTrackId;
    }
    
    @Override
    protected JSONObject getData() throws JSONException {
        JSONObject data;
        if ((data = super.getData()) == null) {
            data = new JSONObject();
        }
        if (this.mView != null) {
            data.put("view", (Object)this.mView);
        }
        if (this.mStoryId != null) {
            data.put("storyId", (Object)this.mStoryId);
        }
        if (this.mError != null) {
            data.put("error", (Object)this.mError.toJSONObject());
        }
        data.put("originatingRequestGuid", (Object)ConsolidatedLoggingUtils.createGUID());
        data.put("success", this.mSuccess);
        data.put("trackId", this.mTrackId);
        return data;
    }
}
