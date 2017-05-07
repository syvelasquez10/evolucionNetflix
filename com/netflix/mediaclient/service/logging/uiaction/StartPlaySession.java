// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.uiaction;

import com.netflix.mediaclient.service.logging.uiaction.model.StartPlayEndedEvent;
import com.netflix.mediaclient.service.logging.client.model.UIError;
import com.netflix.mediaclient.servicemgr.IClientLogging;
import com.netflix.mediaclient.servicemgr.UserActionLogging;

public final class StartPlaySession extends BaseUIActionSession
{
    public static final String NAME = "startPlay";
    
    public StartPlaySession(final UserActionLogging.CommandName commandName, final IClientLogging.ModalView modalView) {
        super(commandName, modalView);
    }
    
    public StartPlayEndedEvent createEndedEvent(final IClientLogging.CompletionReason completionReason, final UIError uiError, final Integer n) {
        final StartPlayEndedEvent startPlayEndedEvent = new StartPlayEndedEvent(this.mId, System.currentTimeMillis() - this.mStarted, this.mView, this.mAction, completionReason, uiError, n);
        startPlayEndedEvent.setCategory(this.getCategory());
        startPlayEndedEvent.setSessionId(this.mId);
        return startPlayEndedEvent;
    }
    
    @Override
    public String getName() {
        return "startPlay";
    }
}
