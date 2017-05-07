// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.uiaction;

import com.netflix.mediaclient.service.logging.uiaction.model.LoginEndedEvent;
import com.netflix.mediaclient.service.logging.client.model.UIError;
import com.netflix.mediaclient.servicemgr.IClientLogging;
import com.netflix.mediaclient.servicemgr.UserActionLogging;

public final class LoginSession extends BaseUIActionSession
{
    public static final String NAME = "logIn";
    
    public LoginSession(final UserActionLogging.CommandName commandName, final IClientLogging.ModalView modalView) {
        super(commandName, modalView);
    }
    
    public LoginEndedEvent createEndedEvent(final IClientLogging.CompletionReason completionReason, final UIError uiError) {
        final LoginEndedEvent loginEndedEvent = new LoginEndedEvent(this.mId, System.currentTimeMillis() - this.mStarted, this.mView, this.mAction, completionReason, uiError);
        loginEndedEvent.setCategory(this.getCategory());
        loginEndedEvent.setSessionId(this.mId);
        return loginEndedEvent;
    }
    
    @Override
    public String getName() {
        return "logIn";
    }
}
