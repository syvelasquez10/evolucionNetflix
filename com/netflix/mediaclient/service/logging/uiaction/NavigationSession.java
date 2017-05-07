// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.uiaction;

import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.logging.uiaction.model.NavigationEndedEvent;
import com.netflix.mediaclient.service.logging.client.model.UIError;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.servicemgr.UserActionLogging$CommandName;

public final class NavigationSession extends BaseUIActionSession
{
    public static final String NAME = "navigate";
    private static final String TAG = "nf_log";
    
    public NavigationSession(final UserActionLogging$CommandName userActionLogging$CommandName, final IClientLogging$ModalView clientLogging$ModalView) {
        super(userActionLogging$CommandName, clientLogging$ModalView);
    }
    
    public NavigationEndedEvent createEndedEvent(final IClientLogging$ModalView clientLogging$ModalView, final IClientLogging$CompletionReason clientLogging$CompletionReason, final UIError uiError) {
        if (clientLogging$ModalView == this.mView) {
            if (Log.isLoggable("nf_log", 3)) {
                Log.d("nf_log", "We stayed in same view, do not report " + clientLogging$ModalView);
            }
            return null;
        }
        if (Log.isLoggable("nf_log", 3)) {
            Log.d("nf_log", "We started from " + clientLogging$ModalView + " and ended up on " + this.mView);
        }
        final NavigationEndedEvent navigationEndedEvent = new NavigationEndedEvent(this.mId, System.currentTimeMillis() - this.mStarted, this.mAction, clientLogging$CompletionReason, uiError, clientLogging$ModalView, this.mView);
        navigationEndedEvent.setCategory(this.getCategory());
        navigationEndedEvent.setSessionId(this.mId);
        return navigationEndedEvent;
    }
    
    @Override
    public String getName() {
        return "navigate";
    }
}
