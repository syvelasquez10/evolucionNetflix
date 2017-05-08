// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.iko;

import com.netflix.mediaclient.service.logging.iko.model.IkoModeEndedEvent;
import com.netflix.mediaclient.service.logging.client.model.UIError;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.servicemgr.UserActionLogging$CommandName;
import com.netflix.mediaclient.service.logging.uiaction.BaseUIActionSession;

public class IkoModeSession extends BaseUIActionSession
{
    protected static final String CATEGORY = "iko";
    public static final String NAME = "ikoMode";
    
    public IkoModeSession(final UserActionLogging$CommandName userActionLogging$CommandName, final IClientLogging$ModalView clientLogging$ModalView) {
        super(userActionLogging$CommandName, clientLogging$ModalView);
    }
    
    public IkoModeEndedEvent createEndedEvent(final IClientLogging$CompletionReason clientLogging$CompletionReason, final UIError uiError) {
        final IkoModeEndedEvent ikoModeEndedEvent = new IkoModeEndedEvent("ikoMode", this.mId, System.currentTimeMillis() - this.mStarted, this.mView, this.mAction, clientLogging$CompletionReason, uiError);
        ikoModeEndedEvent.setCategory(this.getCategory());
        ikoModeEndedEvent.setSessionId(this.mId);
        return ikoModeEndedEvent;
    }
    
    @Override
    public String getCategory() {
        return "iko";
    }
    
    @Override
    public String getName() {
        return "ikoMode";
    }
}
