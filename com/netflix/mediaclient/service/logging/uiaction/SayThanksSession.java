// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.uiaction;

import com.netflix.mediaclient.service.logging.uiaction.model.SayThanksEndedEvent;
import com.netflix.mediaclient.service.logging.client.model.UIError;
import com.netflix.mediaclient.servicemgr.IClientLogging;
import com.netflix.mediaclient.servicemgr.UserActionLogging;

public class SayThanksSession extends BaseUIActionSession
{
    public static final String NAME = "sayThanks";
    
    public SayThanksSession(final UserActionLogging.CommandName commandName, final IClientLogging.ModalView modalView) {
        super(commandName, modalView);
    }
    
    public SayThanksEndedEvent createEndedEvent(final IClientLogging.CompletionReason completionReason, final UIError uiError, final IClientLogging.ModalView modalView) {
        final SayThanksEndedEvent sayThanksEndedEvent = new SayThanksEndedEvent(this.mId, System.currentTimeMillis() - this.mStarted, this.mView, this.mAction, completionReason, uiError);
        sayThanksEndedEvent.setCategory(this.getCategory());
        sayThanksEndedEvent.setSessionId(this.mId);
        return sayThanksEndedEvent;
    }
    
    @Override
    public String getName() {
        return "sayThanks";
    }
}
