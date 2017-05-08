// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.iko;

import com.netflix.mediaclient.service.logging.iko.model.IkoMomentEndedEvent;
import com.netflix.mediaclient.service.logging.client.model.UIError;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.servicemgr.UserActionLogging$CommandName;
import com.netflix.mediaclient.service.logging.uiaction.BaseUIActionSession;

public class IkoMomentSession extends BaseUIActionSession
{
    protected static final String CATEGORY = "iko";
    public static final String NAME = "ikoMoment";
    
    public IkoMomentSession(final UserActionLogging$CommandName userActionLogging$CommandName, final IClientLogging$ModalView clientLogging$ModalView) {
        super(userActionLogging$CommandName, clientLogging$ModalView);
    }
    
    public IkoMomentEndedEvent createEndedEvent(final IClientLogging$CompletionReason clientLogging$CompletionReason, final UIError uiError, final String s, final String s2, final int n, final String s3) {
        final IkoMomentEndedEvent ikoMomentEndedEvent = new IkoMomentEndedEvent("ikoMoment", this.mId, System.currentTimeMillis() - this.mStarted, this.mView, this.mAction, clientLogging$CompletionReason, uiError, s, s2, n, s3);
        ikoMomentEndedEvent.setCategory(this.getCategory());
        ikoMomentEndedEvent.setSessionId(this.mId);
        return ikoMomentEndedEvent;
    }
    
    @Override
    public String getCategory() {
        return "iko";
    }
    
    @Override
    public String getName() {
        return "ikoMoment";
    }
}
