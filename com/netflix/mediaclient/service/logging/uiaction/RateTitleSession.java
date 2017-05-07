// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.uiaction;

import com.netflix.mediaclient.service.logging.uiaction.model.RateTitleEndedEvent;
import com.netflix.mediaclient.service.logging.client.model.UIError;
import com.netflix.mediaclient.servicemgr.IClientLogging;
import com.netflix.mediaclient.servicemgr.UserActionLogging;

public final class RateTitleSession extends BaseUIActionSession
{
    public static final String NAME = "rateTitle";
    
    public RateTitleSession(final UserActionLogging.CommandName commandName, final IClientLogging.ModalView modalView) {
        super(commandName, modalView);
    }
    
    public RateTitleEndedEvent createEndedEvent(final IClientLogging.CompletionReason completionReason, final UIError uiError, final Integer n, final int n2) {
        final RateTitleEndedEvent rateTitleEndedEvent = new RateTitleEndedEvent(this.mId, System.currentTimeMillis() - this.mStarted, this.mView, this.mAction, completionReason, uiError, n, n2);
        rateTitleEndedEvent.setCategory(this.getCategory());
        rateTitleEndedEvent.setSessionId(this.mId);
        return rateTitleEndedEvent;
    }
    
    @Override
    public String getName() {
        return "rateTitle";
    }
}
