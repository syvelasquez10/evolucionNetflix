// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.uiaction;

import com.netflix.mediaclient.service.logging.uiaction.model.SerializeLolomoEndedEvent;
import com.netflix.mediaclient.service.logging.client.model.UIError;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.servicemgr.UserActionLogging$CommandName;

public class SerializeLolomoSession extends BaseUIActionSession
{
    public static final String NAME = "deserializeLolomo";
    
    public SerializeLolomoSession(final UserActionLogging$CommandName userActionLogging$CommandName, final IClientLogging$ModalView clientLogging$ModalView) {
        super(userActionLogging$CommandName, clientLogging$ModalView);
    }
    
    public SerializeLolomoEndedEvent createEndedEvent(final IClientLogging$CompletionReason clientLogging$CompletionReason, final UIError uiError, final long n) {
        final SerializeLolomoEndedEvent serializeLolomoEndedEvent = new SerializeLolomoEndedEvent("deserializeLolomo", this.mId, System.currentTimeMillis() - this.mStarted, this.mView, this.mAction, clientLogging$CompletionReason, uiError, n);
        serializeLolomoEndedEvent.setCategory(this.getCategory());
        serializeLolomoEndedEvent.setSessionId(this.mId);
        return serializeLolomoEndedEvent;
    }
    
    @Override
    public String getName() {
        return "deserializeLolomo";
    }
}
