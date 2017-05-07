// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.uiaction;

import com.netflix.mediaclient.service.logging.uiaction.model.StartPlayEndedEvent;
import com.netflix.mediaclient.media.PlayerType;
import com.netflix.mediaclient.service.logging.client.model.UIError;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.servicemgr.UserActionLogging$CommandName;

public final class StartPlaySession extends BaseUIActionSession
{
    public static final String NAME = "startPlay";
    
    public StartPlaySession(final UserActionLogging$CommandName userActionLogging$CommandName, final IClientLogging$ModalView clientLogging$ModalView) {
        super(userActionLogging$CommandName, clientLogging$ModalView);
    }
    
    public StartPlayEndedEvent createEndedEvent(final IClientLogging$CompletionReason clientLogging$CompletionReason, final UIError uiError, final Integer n, final PlayerType playerType) {
        final StartPlayEndedEvent startPlayEndedEvent = new StartPlayEndedEvent(this.mId, System.currentTimeMillis() - this.mStarted, this.mView, this.mAction, clientLogging$CompletionReason, uiError, n, playerType);
        startPlayEndedEvent.setCategory(this.getCategory());
        startPlayEndedEvent.setSessionId(this.mId);
        return startPlayEndedEvent;
    }
    
    @Override
    public String getName() {
        return "startPlay";
    }
}
