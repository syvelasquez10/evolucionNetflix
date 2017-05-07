// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.android;

import com.netflix.mediaclient.service.logging.android.model.RecommendSheetEndedEvent;
import com.netflix.mediaclient.service.logging.client.model.UIError;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.servicemgr.UserActionLogging$CommandName;

public class RecommendSheetSession extends AndroidUIActionSession
{
    public static final String NAME = "recommendSheet";
    
    public RecommendSheetSession(final UserActionLogging$CommandName userActionLogging$CommandName, final IClientLogging$ModalView clientLogging$ModalView) {
        super(userActionLogging$CommandName, clientLogging$ModalView);
    }
    
    public RecommendSheetEndedEvent createEndedEvent(final IClientLogging$CompletionReason clientLogging$CompletionReason, final UIError uiError, final IClientLogging$ModalView clientLogging$ModalView) {
        final RecommendSheetEndedEvent recommendSheetEndedEvent = new RecommendSheetEndedEvent(this.mId, System.currentTimeMillis() - this.mStarted, this.mView, this.mAction, clientLogging$CompletionReason, uiError);
        recommendSheetEndedEvent.setCategory(this.getCategory());
        recommendSheetEndedEvent.setSessionId(this.mId);
        return recommendSheetEndedEvent;
    }
    
    @Override
    public String getName() {
        return "recommendSheet";
    }
}
