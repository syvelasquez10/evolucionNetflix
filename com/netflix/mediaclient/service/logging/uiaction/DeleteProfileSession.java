// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.uiaction;

import com.netflix.mediaclient.service.logging.uiaction.model.DeleteProfileEndedEvent;
import com.netflix.mediaclient.service.logging.client.model.UIError;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.servicemgr.UserActionLogging$CommandName;

public final class DeleteProfileSession extends BaseUIActionSession
{
    public static final String NAME = "deleteProfile";
    private String mProfileId;
    
    public DeleteProfileSession(final String mProfileId, final UserActionLogging$CommandName userActionLogging$CommandName, final IClientLogging$ModalView clientLogging$ModalView) {
        super(userActionLogging$CommandName, clientLogging$ModalView);
        if (mProfileId == null) {
            throw new IllegalArgumentException("Profile ID is null!");
        }
        this.mProfileId = mProfileId;
    }
    
    public DeleteProfileEndedEvent createEndedEvent(final IClientLogging$CompletionReason clientLogging$CompletionReason, final UIError uiError, final IClientLogging$ModalView clientLogging$ModalView) {
        final DeleteProfileEndedEvent deleteProfileEndedEvent = new DeleteProfileEndedEvent(this.mId, System.currentTimeMillis() - this.mStarted, this.mView, this.mAction, clientLogging$CompletionReason, uiError, this.mProfileId);
        deleteProfileEndedEvent.setCategory(this.getCategory());
        deleteProfileEndedEvent.setSessionId(this.mId);
        return deleteProfileEndedEvent;
    }
    
    @Override
    public String getName() {
        return "deleteProfile";
    }
}
