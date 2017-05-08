// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.iko.model;

import org.json.JSONObject;
import com.netflix.mediaclient.service.logging.client.model.DeviceUniqueId;
import com.netflix.mediaclient.service.logging.client.model.UIError;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import com.netflix.mediaclient.service.logging.client.model.SessionEndedEvent;

public class IkoVideoLoadEndedEvent extends SessionEndedEvent
{
    protected static final String CATEGORY = "iko";
    private static final String DATA_FIELD_ERROR = "error";
    private static final String DATA_FIELD_REASON = "reason";
    private static final String DATA_FIELD_URL = "url";
    protected static final String NAME = "ikoVideoLoadEnded";
    protected IClientLogging$CompletionReason mCompletionReason;
    protected UIError mUIError;
    private String mVideoUrl;
    
    public IkoVideoLoadEndedEvent(final String s, final DeviceUniqueId deviceUniqueId, final long n, final IClientLogging$CompletionReason mCompletionReason, final UIError muiError, final String mVideoUrl) {
        super(s, deviceUniqueId, n);
        this.mVideoUrl = mVideoUrl;
        this.mUIError = muiError;
        this.mCompletionReason = mCompletionReason;
        this.category = "iko";
        this.name = "ikoVideoLoadEnded";
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
        if (this.mUIError != null) {
            data.put("error", (Object)this.mUIError.toJSONObject());
        }
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
