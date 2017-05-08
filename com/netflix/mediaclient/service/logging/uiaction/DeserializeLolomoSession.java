// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.uiaction;

import com.netflix.mediaclient.service.logging.uiaction.model.DeserializeLolomoEndedEvent;
import com.netflix.mediaclient.service.logging.client.model.UIError;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.servicemgr.UserActionLogging$CommandName;

public class DeserializeLolomoSession extends BaseUIActionSession
{
    public static final String NAME = "deserializeLolomo";
    
    public DeserializeLolomoSession(final UserActionLogging$CommandName userActionLogging$CommandName, final IClientLogging$ModalView clientLogging$ModalView) {
        super(userActionLogging$CommandName, clientLogging$ModalView);
    }
    
    public DeserializeLolomoEndedEvent createEndedEvent(final IClientLogging$CompletionReason clientLogging$CompletionReason, final UIError uiError, final long n) {
        final DeserializeLolomoEndedEvent deserializeLolomoEndedEvent = new DeserializeLolomoEndedEvent("deserializeLolomo", this.mId, System.currentTimeMillis() - this.mStarted, this.mView, this.mAction, clientLogging$CompletionReason, uiError, n);
        deserializeLolomoEndedEvent.setCategory(this.getCategory());
        deserializeLolomoEndedEvent.setSessionId(this.mId);
        return deserializeLolomoEndedEvent;
    }
    
    @Override
    public String getName() {
        return "deserializeLolomo";
    }
}
