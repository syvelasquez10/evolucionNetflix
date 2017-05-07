// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.uiaction;

import com.netflix.mediaclient.service.logging.uiaction.model.AddProfileEndedEvent;
import com.netflix.mediaclient.servicemgr.UserActionLogging$Profile;
import com.netflix.mediaclient.service.logging.client.model.UIError;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.servicemgr.UserActionLogging$CommandName;

public final class AddProfileSession extends BaseUIActionSession
{
    public static final String NAME = "addProfile";
    
    public AddProfileSession(final UserActionLogging$CommandName userActionLogging$CommandName, final IClientLogging$ModalView clientLogging$ModalView) {
        super(userActionLogging$CommandName, clientLogging$ModalView);
    }
    
    public AddProfileEndedEvent createEndedEvent(final IClientLogging$CompletionReason clientLogging$CompletionReason, final UIError uiError, final IClientLogging$ModalView clientLogging$ModalView, final UserActionLogging$Profile userActionLogging$Profile) {
        final AddProfileEndedEvent addProfileEndedEvent = new AddProfileEndedEvent(this.mId, System.currentTimeMillis() - this.mStarted, this.mView, this.mAction, clientLogging$CompletionReason, uiError, userActionLogging$Profile);
        addProfileEndedEvent.setCategory(this.getCategory());
        addProfileEndedEvent.setSessionId(this.mId);
        return addProfileEndedEvent;
    }
    
    @Override
    public String getName() {
        return "addProfile";
    }
}
