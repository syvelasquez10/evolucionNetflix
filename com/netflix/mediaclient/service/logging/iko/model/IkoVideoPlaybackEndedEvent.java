// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.iko.model;

import org.json.JSONObject;
import com.netflix.mediaclient.service.logging.client.model.DeviceUniqueId;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import com.netflix.mediaclient.service.logging.client.model.SessionEndedEvent;

public class IkoVideoPlaybackEndedEvent extends SessionEndedEvent
{
    protected static final String CATEGORY = "iko";
    private static final String DATA_FIELD_ERROR_CODE = "errorCode";
    private static final String DATA_FIELD_ERROR_SUB_CODE = "errorSubCode";
    private static final String DATA_FIELD_REASON = "reason";
    private static final String DATA_FIELD_URL = "url";
    protected static final String NAME = "ikoVideoPlaybackEnded";
    protected IClientLogging$CompletionReason mCompletionReason;
    protected int mErrorCode;
    protected int mErrorSubCode;
    private String mVideoUrl;
    protected IClientLogging$ModalView mView;
    
    public IkoVideoPlaybackEndedEvent(final String s, final DeviceUniqueId deviceUniqueId, final long n, final IClientLogging$CompletionReason mCompletionReason, final String mVideoUrl, final int mErrorCode, final int mErrorSubCode) {
        super(s, deviceUniqueId, n);
        this.mVideoUrl = mVideoUrl;
        this.mErrorCode = mErrorCode;
        this.mErrorSubCode = mErrorSubCode;
        this.mCompletionReason = mCompletionReason;
        this.category = "iko";
        this.name = "ikoVideoPlaybackEnded";
    }
    
    @Override
    protected JSONObject getData() {
        JSONObject data;
        if ((data = super.getData()) == null) {
            data = new JSONObject();
        }
        if (this.mCompletionReason != null) {
            data.put("reason", (Object)this.mCompletionReason.name());
        }
        data.put("errorCode", this.mErrorCode);
        data.put("errorSubCode", this.mErrorSubCode);
        String mVideoUrl;
        if (this.mVideoUrl != null) {
            mVideoUrl = this.mVideoUrl;
        }
        else {
            mVideoUrl = "unknown";
        }
        data.put("url", (Object)mVideoUrl);
        return data;
    }
}
