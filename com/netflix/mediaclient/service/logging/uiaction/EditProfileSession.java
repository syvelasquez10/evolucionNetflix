// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.uiaction;

import com.netflix.mediaclient.service.logging.uiaction.model.EditProfileEndedEvent;
import com.netflix.mediaclient.service.logging.client.model.UIError;
import com.netflix.mediaclient.servicemgr.IClientLogging;
import com.netflix.mediaclient.servicemgr.UserActionLogging;

public final class EditProfileSession extends BaseUIActionSession
{
    public static final String NAME = "editProfile";
    
    public EditProfileSession(final UserActionLogging.CommandName commandName, final IClientLogging.ModalView modalView) {
        super(commandName, modalView);
    }
    
    public EditProfileEndedEvent createEndedEvent(final IClientLogging.CompletionReason completionReason, final UIError uiError, final IClientLogging.ModalView modalView, final UserActionLogging.Profile profile) {
        final EditProfileEndedEvent editProfileEndedEvent = new EditProfileEndedEvent(this.mId, System.currentTimeMillis() - this.mStarted, this.mView, this.mAction, completionReason, uiError, profile);
        editProfileEndedEvent.setCategory(this.getCategory());
        editProfileEndedEvent.setSessionId(this.mId);
        return editProfileEndedEvent;
    }
    
    @Override
    public String getName() {
        return "editProfile";
    }
}
