// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.uiaction;

import com.netflix.mediaclient.service.logging.uiaction.model.RateTitleEndedEvent;
import com.netflix.mediaclient.service.logging.client.model.UIError;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.servicemgr.UserActionLogging$CommandName;

public final class RateTitleSession extends BaseUIActionSession
{
    public static final String NAME = "rateTitle";
    
    public RateTitleSession(final UserActionLogging$CommandName userActionLogging$CommandName, final IClientLogging$ModalView clientLogging$ModalView) {
        super(userActionLogging$CommandName, clientLogging$ModalView);
    }
    
    public RateTitleEndedEvent createEndedEvent(final IClientLogging$CompletionReason clientLogging$CompletionReason, final UIError uiError, final Integer n, final int n2) {
        final RateTitleEndedEvent rateTitleEndedEvent = new RateTitleEndedEvent(this.mId, System.currentTimeMillis() - this.mStarted, this.mView, this.mAction, clientLogging$CompletionReason, uiError, n, n2);
        rateTitleEndedEvent.setCategory(this.getCategory());
        rateTitleEndedEvent.setSessionId(this.mId);
        return rateTitleEndedEvent;
    }
    
    @Override
    public String getName() {
        return "rateTitle";
    }
}
