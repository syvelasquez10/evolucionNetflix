// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.uiaction;

import com.netflix.mediaclient.service.logging.uiaction.model.AddProfileEndedEvent;
import com.netflix.mediaclient.service.logging.client.model.UIError;
import com.netflix.mediaclient.servicemgr.IClientLogging;
import com.netflix.mediaclient.servicemgr.UserActionLogging;

public final class AddProfileSession extends BaseUIActionSession
{
    public static final String NAME = "addProfile";
    
    public AddProfileSession(final UserActionLogging.CommandName commandName, final IClientLogging.ModalView modalView) {
        super(commandName, modalView);
    }
    
    public AddProfileEndedEvent createEndedEvent(final IClientLogging.CompletionReason completionReason, final UIError uiError, final IClientLogging.ModalView modalView, final UserActionLogging.Profile profile) {
        final AddProfileEndedEvent addProfileEndedEvent = new AddProfileEndedEvent(this.mId, System.currentTimeMillis() - this.mStarted, this.mView, this.mAction, completionReason, uiError, profile);
        addProfileEndedEvent.setCategory(this.getCategory());
        addProfileEndedEvent.setSessionId(this.mId);
        return addProfileEndedEvent;
    }
    
    @Override
    public String getName() {
        return "addProfile";
    }
}
