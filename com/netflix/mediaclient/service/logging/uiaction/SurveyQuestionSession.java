// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.uiaction;

import com.netflix.mediaclient.service.logging.uiaction.model.SurveyQuestionEndedEvent;
import com.netflix.mediaclient.service.logging.client.model.UIError;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.servicemgr.UserActionLogging$CommandName;

public final class SurveyQuestionSession extends BaseUIActionSession
{
    public static final String NAME = "surveyQuestion";
    
    public SurveyQuestionSession(final UserActionLogging$CommandName userActionLogging$CommandName, final IClientLogging$ModalView clientLogging$ModalView) {
        super(userActionLogging$CommandName, clientLogging$ModalView);
    }
    
    public SurveyQuestionEndedEvent createEndedEvent(final IClientLogging$CompletionReason clientLogging$CompletionReason, final UIError uiError, final String s, final String s2) {
        final SurveyQuestionEndedEvent surveyQuestionEndedEvent = new SurveyQuestionEndedEvent(this.mId, System.currentTimeMillis() - this.mStarted, this.mView, this.mAction, clientLogging$CompletionReason, uiError, s, s2);
        surveyQuestionEndedEvent.setCategory(this.getCategory());
        surveyQuestionEndedEvent.setSessionId(this.mId);
        return surveyQuestionEndedEvent;
    }
    
    @Override
    public String getName() {
        return "surveyQuestion";
    }
}
