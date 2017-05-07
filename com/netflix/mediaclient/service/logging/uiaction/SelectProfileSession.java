// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.uiaction;

import com.netflix.mediaclient.service.logging.uiaction.model.SelectProfileEndedEvent;
import com.netflix.mediaclient.service.logging.client.model.UIError;
import com.netflix.mediaclient.servicemgr.IClientLogging;
import com.netflix.mediaclient.servicemgr.UserActionLogging;

public final class SelectProfileSession extends BaseUIActionSession
{
    public static final String NAME = "selectProfile";
    private String mProfileId;
    private UserActionLogging.RememberProfile mRememberProfile;
    
    public SelectProfileSession(final String mProfileId, final UserActionLogging.RememberProfile mRememberProfile, final UserActionLogging.CommandName commandName, final IClientLogging.ModalView modalView) {
        super(commandName, modalView);
        if (mProfileId == null) {
            throw new IllegalArgumentException("Profile ID is null!");
        }
        this.mProfileId = mProfileId;
        this.mRememberProfile = mRememberProfile;
    }
    
    public SelectProfileEndedEvent createEndedEvent(final IClientLogging.CompletionReason completionReason, final UIError uiError, final IClientLogging.ModalView modalView) {
        final SelectProfileEndedEvent selectProfileEndedEvent = new SelectProfileEndedEvent(this.mId, System.currentTimeMillis() - this.mStarted, this.mView, this.mAction, completionReason, uiError, this.mProfileId, this.mRememberProfile);
        selectProfileEndedEvent.setCategory(this.getCategory());
        selectProfileEndedEvent.setSessionId(this.mId);
        return selectProfileEndedEvent;
    }
    
    @Override
    public String getName() {
        return "selectProfile";
    }
}
