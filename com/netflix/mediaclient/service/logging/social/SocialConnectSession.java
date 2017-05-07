// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.social;

import com.netflix.mediaclient.service.logging.social.model.SocialConnectSessionEnded;
import com.netflix.mediaclient.servicemgr.SocialLogging$Channel;

public class SocialConnectSession extends BaseSocialSession
{
    public static final String NAME = "socialConnect";
    private SocialLogging$Channel mChannel;
    
    public SocialConnectSession(final SocialLogging$Channel mChannel) {
        this.mChannel = mChannel;
    }
    
    public SocialConnectSessionEnded createEndedEvent() {
        final SocialConnectSessionEnded socialConnectSessionEnded = new SocialConnectSessionEnded(System.currentTimeMillis() - this.mStarted, this.mChannel);
        socialConnectSessionEnded.setCategory(this.getCategory());
        socialConnectSessionEnded.setSessionId(this.mId);
        return socialConnectSessionEnded;
    }
    
    @Override
    public String getName() {
        return "socialConnect";
    }
}
