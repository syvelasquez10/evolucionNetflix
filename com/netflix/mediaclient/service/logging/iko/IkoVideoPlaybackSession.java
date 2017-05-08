// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.iko;

import com.netflix.mediaclient.service.logging.iko.model.IkoVideoPlaybackEndedEvent;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import com.netflix.mediaclient.service.logging.client.BaseLoggingSession;

public class IkoVideoPlaybackSession extends BaseLoggingSession
{
    protected static final String CATEGORY = "iko";
    protected static final String NAME = "ikoVideoPlayback";
    protected String mUrl;
    
    public IkoVideoPlaybackSession(final String mUrl) {
        this.mUrl = mUrl;
    }
    
    public IkoVideoPlaybackEndedEvent createEndedEvent(final IClientLogging$CompletionReason clientLogging$CompletionReason, final int n, final int n2) {
        return new IkoVideoPlaybackEndedEvent("ikoVideoPlayback", this.mId, System.currentTimeMillis() - this.mStarted, clientLogging$CompletionReason, this.mUrl, n, n2);
    }
    
    @Override
    public String getCategory() {
        return "iko";
    }
    
    @Override
    public String getName() {
        return "ikoVideoPlayback";
    }
}
