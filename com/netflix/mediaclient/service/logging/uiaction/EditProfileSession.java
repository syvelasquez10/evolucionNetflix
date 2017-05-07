// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.uiaction;

import com.netflix.mediaclient.service.logging.uiaction.model.EditProfileEndedEvent;
import com.netflix.mediaclient.servicemgr.UserActionLogging$Profile;
import com.netflix.mediaclient.service.logging.client.model.UIError;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.servicemgr.UserActionLogging$CommandName;

public final class EditProfileSession extends BaseUIActionSession
{
    public static final String NAME = "editProfile";
    
    public EditProfileSession(final UserActionLogging$CommandName userActionLogging$CommandName, final IClientLogging$ModalView clientLogging$ModalView) {
        super(userActionLogging$CommandName, clientLogging$ModalView);
    }
    
    public EditProfileEndedEvent createEndedEvent(final IClientLogging$CompletionReason clientLogging$CompletionReason, final UIError uiError, final IClientLogging$ModalView clientLogging$ModalView, final UserActionLogging$Profile userActionLogging$Profile) {
        final EditProfileEndedEvent editProfileEndedEvent = new EditProfileEndedEvent(this.mId, System.currentTimeMillis() - this.mStarted, this.mView, this.mAction, clientLogging$CompletionReason, uiError, userActionLogging$Profile);
        editProfileEndedEvent.setCategory(this.getCategory());
        editProfileEndedEvent.setSessionId(this.mId);
        return editProfileEndedEvent;
    }
    
    @Override
    public String getName() {
        return "editProfile";
    }
}
