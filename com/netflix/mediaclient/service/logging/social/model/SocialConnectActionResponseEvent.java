// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.social.model;

import org.json.JSONObject;
import com.netflix.mediaclient.servicemgr.SocialLogging$Source;
import com.netflix.mediaclient.service.logging.client.model.Error;
import com.netflix.mediaclient.servicemgr.SocialLogging$Channel;

public final class SocialConnectActionResponseEvent extends BaseSocialDiscreteEvent
{
    protected static final String NAME = "socialConnectActionResponse";
    private SocialLogging$Channel mChannel;
    private Error mError;
    private SocialLogging$Source mSource;
    private boolean mSuccess;
    
    public SocialConnectActionResponseEvent(final SocialLogging$Channel mChannel, final SocialLogging$Source mSource, final boolean mSuccess, final Error mError) {
        super("socialConnectActionResponse");
        this.mChannel = mChannel;
        this.mSource = mSource;
        this.mSuccess = mSuccess;
        this.mError = mError;
    }
    
    @Override
    protected JSONObject getData() {
        JSONObject data;
        if ((data = super.getData()) == null) {
            data = new JSONObject();
        }
        if (this.mChannel != null) {
            data.put("channel", (Object)this.mChannel.name());
        }
        if (this.mSource != null) {
            data.put("source", (Object)this.mSource.getValue());
        }
        if (this.mError != null) {
            data.put("error", (Object)this.mError.toJSONObject());
        }
        data.put("success", this.mSuccess);
        return data;
    }
}
