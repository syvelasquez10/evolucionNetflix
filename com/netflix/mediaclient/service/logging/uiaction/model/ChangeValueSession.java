// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.uiaction.model;

import com.netflix.mediaclient.service.logging.client.model.UIError;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.servicemgr.UserActionLogging$CommandName;
import com.netflix.mediaclient.service.logging.uiaction.BaseUIActionSession;

public class ChangeValueSession extends BaseUIActionSession
{
    public static final String NAME = "command";
    
    public ChangeValueSession(final UserActionLogging$CommandName userActionLogging$CommandName, final IClientLogging$ModalView clientLogging$ModalView) {
        super(userActionLogging$CommandName, clientLogging$ModalView);
    }
    
    public ChangeValueEndedEvent createEndedEvent(final IClientLogging$CompletionReason clientLogging$CompletionReason, final UIError uiError, final String s) {
        final ChangeValueEndedEvent changeValueEndedEvent = new ChangeValueEndedEvent(this.mId, System.currentTimeMillis() - this.mStarted, this.mView, this.mAction, clientLogging$CompletionReason, uiError, s);
        changeValueEndedEvent.setCategory(this.getCategory());
        changeValueEndedEvent.setSessionId(this.mId);
        return changeValueEndedEvent;
    }
    
    @Override
    public String getName() {
        return "command";
    }
}
