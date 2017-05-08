// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.iko;

import com.netflix.mediaclient.service.logging.iko.model.IkoVideoLoadEndedEvent;
import com.netflix.mediaclient.service.logging.client.model.UIError;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import com.netflix.mediaclient.service.logging.client.BaseLoggingSession;

public class IkoVideoLoadSession extends BaseLoggingSession
{
    protected static final String CATEGORY = "iko";
    protected static final String NAME = "ikoVideoLoad";
    protected String mUrl;
    
    public IkoVideoLoadSession(final String mUrl) {
        this.mUrl = mUrl;
    }
    
    public IkoVideoLoadEndedEvent createEndedEvent(final IClientLogging$CompletionReason clientLogging$CompletionReason, final UIError uiError) {
        return new IkoVideoLoadEndedEvent("ikoVideoLoad", this.mId, System.currentTimeMillis() - this.mStarted, clientLogging$CompletionReason, uiError, this.mUrl);
    }
    
    @Override
    public String getCategory() {
        return "iko";
    }
    
    @Override
    public String getName() {
        return "ikoVideoLoad";
    }
}
