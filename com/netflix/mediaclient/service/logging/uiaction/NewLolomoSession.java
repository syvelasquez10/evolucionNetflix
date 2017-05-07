// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.uiaction;

import com.netflix.mediaclient.service.logging.uiaction.model.NewLolomoEndedEvent;
import com.netflix.mediaclient.service.logging.client.model.UIError;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.servicemgr.UserActionLogging$CommandName;

public class NewLolomoSession extends BaseUIActionSession
{
    public static final String NAME = "NewLolomo";
    
    public NewLolomoSession(final UserActionLogging$CommandName userActionLogging$CommandName, final IClientLogging$ModalView clientLogging$ModalView) {
        super(userActionLogging$CommandName, clientLogging$ModalView);
    }
    
    public NewLolomoEndedEvent createEndedEvent(final IClientLogging$CompletionReason clientLogging$CompletionReason, final UIError uiError, final IClientLogging$ModalView clientLogging$ModalView, final String s, final String s2, final long n, final String s3, final String s4) {
        final NewLolomoEndedEvent newLolomoEndedEvent = new NewLolomoEndedEvent(this.mId, System.currentTimeMillis() - this.mStarted, this.mView, this.mAction, clientLogging$CompletionReason, uiError, s, s2, n, s3, s4);
        newLolomoEndedEvent.setCategory(this.getCategory());
        newLolomoEndedEvent.setSessionId(this.mId);
        return newLolomoEndedEvent;
    }
    
    @Override
    public String getName() {
        return "NewLolomo";
    }
}
