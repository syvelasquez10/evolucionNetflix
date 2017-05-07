// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.uiaction;

import com.netflix.mediaclient.service.logging.uiaction.model.AcknowledgeSignupEndedEvent;
import com.netflix.mediaclient.service.logging.client.model.UIError;
import com.netflix.mediaclient.servicemgr.IClientLogging;
import com.netflix.mediaclient.servicemgr.UserActionLogging;

public final class AcknowledgeSignupSession extends BaseUIActionSession
{
    public static final String NAME = "acknowledgeSignup";
    
    public AcknowledgeSignupSession(final UserActionLogging.CommandName commandName, final IClientLogging.ModalView modalView) {
        super(commandName, modalView);
    }
    
    public AcknowledgeSignupEndedEvent createEndedEvent(final IClientLogging.CompletionReason completionReason, final UIError uiError, final IClientLogging.ModalView modalView) {
        final AcknowledgeSignupEndedEvent acknowledgeSignupEndedEvent = new AcknowledgeSignupEndedEvent(this.mId, System.currentTimeMillis() - this.mStarted, this.mView, this.mAction, completionReason, uiError);
        acknowledgeSignupEndedEvent.setCategory(this.getCategory());
        acknowledgeSignupEndedEvent.setSessionId(this.mId);
        return acknowledgeSignupEndedEvent;
    }
    
    @Override
    public String getName() {
        return "acknowledgeSignup";
    }
}
