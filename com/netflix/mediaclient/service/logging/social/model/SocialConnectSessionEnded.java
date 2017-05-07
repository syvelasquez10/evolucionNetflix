// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.social.model;

import org.json.JSONException;
import org.json.JSONObject;
import com.netflix.mediaclient.servicemgr.SocialLogging;
import com.netflix.mediaclient.service.logging.client.model.SessionEndedEvent;

public class SocialConnectSessionEnded extends SessionEndedEvent
{
    public static final String CHANNEL = "Channel";
    private static final String SESSION_NAME = "socialConnect";
    private SocialLogging.Channel mChannel;
    
    public SocialConnectSessionEnded(final long duration, final SocialLogging.Channel mChannel) {
        super("socialConnect");
        this.duration = duration;
        this.mChannel = mChannel;
    }
    
    @Override
    protected JSONObject getData() throws JSONException {
        JSONObject data;
        if ((data = super.getData()) == null) {
            data = new JSONObject();
        }
        if (this.mChannel != null) {
            data.put("Channel", (Object)this.mChannel.name());
        }
        return data;
    }
}
