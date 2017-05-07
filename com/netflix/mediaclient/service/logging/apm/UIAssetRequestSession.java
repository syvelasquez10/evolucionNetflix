// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.apm;

import com.netflix.mediaclient.service.logging.apm.model.UIAssetRequestSessionEndedEvent;
import com.netflix.mediaclient.service.logging.client.model.Error;
import com.netflix.mediaclient.service.logging.client.model.HttpResponse;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;

public class UIAssetRequestSession extends BaseApmSession
{
    public static final String NAME = "uiAssetRequest";
    private IClientLogging$AssetType mAssetType;
    private String mUrl;
    
    public UIAssetRequestSession(final String mUrl, final IClientLogging$AssetType mAssetType) {
        this.mUrl = mUrl;
        this.mAssetType = mAssetType;
    }
    
    public UIAssetRequestSessionEndedEvent createEndedEvent(final IClientLogging$CompletionReason reason, final HttpResponse response, final Error error) {
        final UIAssetRequestSessionEndedEvent uiAssetRequestSessionEndedEvent = new UIAssetRequestSessionEndedEvent(System.currentTimeMillis() - this.mStarted);
        uiAssetRequestSessionEndedEvent.setCategory(this.getCategory());
        uiAssetRequestSessionEndedEvent.setSessionId(this.mId);
        uiAssetRequestSessionEndedEvent.setUrl(this.mUrl);
        uiAssetRequestSessionEndedEvent.setAssetType(this.mAssetType);
        uiAssetRequestSessionEndedEvent.setError(error);
        uiAssetRequestSessionEndedEvent.setReason(reason);
        uiAssetRequestSessionEndedEvent.setResponse(response);
        return uiAssetRequestSessionEndedEvent;
    }
    
    @Override
    public String getName() {
        return "uiAssetRequest";
    }
}
