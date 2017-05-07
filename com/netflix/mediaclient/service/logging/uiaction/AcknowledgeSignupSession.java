// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.uiaction;

import com.netflix.mediaclient.service.logging.uiaction.model.AcknowledgeSignupEndedEvent;
import com.netflix.mediaclient.service.logging.client.model.UIError;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.servicemgr.UserActionLogging$CommandName;

public final class AcknowledgeSignupSession extends BaseUIActionSession
{
    public static final String NAME = "acknowledgeSignup";
    
    public AcknowledgeSignupSession(final UserActionLogging$CommandName userActionLogging$CommandName, final IClientLogging$ModalView clientLogging$ModalView) {
        super(userActionLogging$CommandName, clientLogging$ModalView);
    }
    
    public AcknowledgeSignupEndedEvent createEndedEvent(final IClientLogging$CompletionReason clientLogging$CompletionReason, final UIError uiError, final IClientLogging$ModalView clientLogging$ModalView) {
        final AcknowledgeSignupEndedEvent acknowledgeSignupEndedEvent = new AcknowledgeSignupEndedEvent(this.mId, System.currentTimeMillis() - this.mStarted, this.mView, this.mAction, clientLogging$CompletionReason, uiError);
        acknowledgeSignupEndedEvent.setCategory(this.getCategory());
        acknowledgeSignupEndedEvent.setSessionId(this.mId);
        return acknowledgeSignupEndedEvent;
    }
    
    @Override
    public String getName() {
        return "acknowledgeSignup";
    }
}
