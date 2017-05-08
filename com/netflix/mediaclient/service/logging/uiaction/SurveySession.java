// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.uiaction;

import com.netflix.mediaclient.service.logging.uiaction.model.SurveyEndedEvent;
import com.netflix.mediaclient.service.logging.client.model.UIError;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.servicemgr.UserActionLogging$CommandName;

public final class SurveySession extends BaseUIActionSession
{
    public static final String NAME = "survey";
    
    public SurveySession(final UserActionLogging$CommandName userActionLogging$CommandName, final IClientLogging$ModalView clientLogging$ModalView) {
        super(userActionLogging$CommandName, clientLogging$ModalView);
    }
    
    public SurveyEndedEvent createEndedEvent(final IClientLogging$CompletionReason clientLogging$CompletionReason, final UIError uiError, final String s) {
        final SurveyEndedEvent surveyEndedEvent = new SurveyEndedEvent(this.mId, System.currentTimeMillis() - this.mStarted, this.mView, this.mAction, clientLogging$CompletionReason, uiError, s);
        surveyEndedEvent.setCategory(this.getCategory());
        surveyEndedEvent.setSessionId(this.mId);
        return surveyEndedEvent;
    }
    
    @Override
    public String getName() {
        return "survey";
    }
}
