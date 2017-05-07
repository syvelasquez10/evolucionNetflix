// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.uiaction;

import com.netflix.mediaclient.service.logging.uiaction.model.RegisterEndedEvent;
import com.netflix.mediaclient.service.logging.client.model.UIError;
import com.netflix.mediaclient.servicemgr.IClientLogging;
import com.netflix.mediaclient.servicemgr.UserActionLogging;

public final class RegisterSession extends BaseUIActionSession
{
    public static final String NAME = "register";
    
    public RegisterSession(final UserActionLogging.CommandName commandName, final IClientLogging.ModalView modalView) {
        super(commandName, modalView);
    }
    
    public RegisterEndedEvent createEndedEvent(final IClientLogging.CompletionReason completionReason, final UIError uiError) {
        final RegisterEndedEvent registerEndedEvent = new RegisterEndedEvent(this.mId, System.currentTimeMillis() - this.mStarted, this.mView, this.mAction, completionReason, uiError);
        registerEndedEvent.setCategory(this.getCategory());
        registerEndedEvent.setSessionId(this.mId);
        return registerEndedEvent;
    }
    
    @Override
    public String getName() {
        return "register";
    }
}
