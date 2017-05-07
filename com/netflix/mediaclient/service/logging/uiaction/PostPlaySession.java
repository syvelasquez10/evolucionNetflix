// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.uiaction;

import com.netflix.mediaclient.service.logging.uiaction.model.PostPlayEndedEvent;
import com.netflix.mediaclient.service.logging.client.model.UIError;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.servicemgr.UserActionLogging$CommandName;
import com.netflix.mediaclient.servicemgr.UserActionLogging$PostPlayExperience;

public class PostPlaySession extends BaseUIActionSession
{
    public static final String NAME = "postPlay";
    private boolean mAutoPlayCountdownEnabled;
    private int mLengthOfAutoPlayCountdown;
    private UserActionLogging$PostPlayExperience mPostPlayExperience;
    
    public PostPlaySession(final UserActionLogging$CommandName userActionLogging$CommandName, final IClientLogging$ModalView clientLogging$ModalView, final boolean mAutoPlayCountdownEnabled, final int mLengthOfAutoPlayCountdown, final UserActionLogging$PostPlayExperience mPostPlayExperience) {
        super(userActionLogging$CommandName, clientLogging$ModalView);
        this.mAutoPlayCountdownEnabled = mAutoPlayCountdownEnabled;
        this.mLengthOfAutoPlayCountdown = mLengthOfAutoPlayCountdown;
        this.mPostPlayExperience = mPostPlayExperience;
    }
    
    public PostPlayEndedEvent createEndedEvent(final IClientLogging$CompletionReason clientLogging$CompletionReason, final IClientLogging$ModalView clientLogging$ModalView, final UIError uiError, final boolean b, final boolean b2, final Integer n, final Integer n2, final int n3) {
        final PostPlayEndedEvent postPlayEndedEvent = new PostPlayEndedEvent(this.mId, System.currentTimeMillis() - this.mStarted, clientLogging$CompletionReason, clientLogging$ModalView, uiError, this.mAutoPlayCountdownEnabled, this.mLengthOfAutoPlayCountdown, this.mPostPlayExperience, b, b2, n, n2, n3);
        postPlayEndedEvent.setCategory(this.getCategory());
        postPlayEndedEvent.setSessionId(this.mId);
        return postPlayEndedEvent;
    }
    
    @Override
    public String getName() {
        return "postPlay";
    }
}
