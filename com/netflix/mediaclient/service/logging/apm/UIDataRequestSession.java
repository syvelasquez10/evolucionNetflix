// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.apm;

import com.netflix.mediaclient.service.logging.apm.model.UIDataRequestSessionEndedEvent;
import com.netflix.mediaclient.service.logging.client.model.Error;
import com.netflix.mediaclient.service.logging.client.model.HttpResponse;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;

public final class UIDataRequestSession extends BaseApmSession
{
    public static final String NAME = "uiDataRequest";
    private String mRequestId;
    private String mUrl;
    
    public UIDataRequestSession(final String mUrl, final String mRequestId) {
        this.mUrl = mUrl;
        this.mRequestId = mRequestId;
    }
    
    public UIDataRequestSessionEndedEvent createEndedEvent(final IClientLogging$CompletionReason reason, final HttpResponse response, final Error error) {
        final UIDataRequestSessionEndedEvent uiDataRequestSessionEndedEvent = new UIDataRequestSessionEndedEvent(System.currentTimeMillis() - this.mStarted);
        uiDataRequestSessionEndedEvent.setCategory(this.getCategory());
        uiDataRequestSessionEndedEvent.setSessionId(this.mId);
        uiDataRequestSessionEndedEvent.setUrl(this.mUrl);
        uiDataRequestSessionEndedEvent.setRequestId(this.mRequestId);
        uiDataRequestSessionEndedEvent.setError(error);
        uiDataRequestSessionEndedEvent.setReason(reason);
        uiDataRequestSessionEndedEvent.setRequestId(this.mRequestId);
        uiDataRequestSessionEndedEvent.setResponse(response);
        return uiDataRequestSessionEndedEvent;
    }
    
    @Override
    public String getName() {
        return "uiDataRequest";
    }
}
