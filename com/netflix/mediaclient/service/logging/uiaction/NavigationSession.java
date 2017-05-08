// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.uiaction;

import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.logging.uiaction.model.NavigationEndedEvent;
import com.netflix.mediaclient.service.logging.client.model.UIError;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import com.netflix.mediaclient.servicemgr.UserActionLogging$CommandName;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;

public final class NavigationSession extends BaseUIActionSession
{
    public static final String NAME = "navigate";
    private static final String TAG = "nf_log";
    private IClientLogging$ModalView startedModalView;
    
    public NavigationSession(final UserActionLogging$CommandName userActionLogging$CommandName, final IClientLogging$ModalView clientLogging$ModalView) {
        super(userActionLogging$CommandName, clientLogging$ModalView);
    }
    
    public NavigationEndedEvent createEndedEvent(IClientLogging$ModalView startedModalView, final IClientLogging$CompletionReason clientLogging$CompletionReason, final UIError uiError) {
        if (this.startedModalView != null) {
            startedModalView = this.startedModalView;
        }
        if (startedModalView == this.mView) {
            if (Log.isLoggable()) {
                Log.d("nf_log", "We stayed in same view, do not report " + startedModalView);
            }
            return null;
        }
        final long n = System.currentTimeMillis() - this.mStarted;
        if (Log.isLoggable()) {
            Log.d("nf_log", "We started from " + startedModalView + " and ended up on " + this.mView + " for " + n + "ms");
        }
        final NavigationEndedEvent navigationEndedEvent = new NavigationEndedEvent(this.mId, n, this.mAction, clientLogging$CompletionReason, uiError, startedModalView, this.mView);
        navigationEndedEvent.setCategory(this.getCategory());
        navigationEndedEvent.setSessionId(this.mId);
        return navigationEndedEvent;
    }
    
    @Override
    public String getName() {
        return "navigate";
    }
    
    public IClientLogging$ModalView getStartingView() {
        return this.startedModalView;
    }
    
    public void setStartingView(final IClientLogging$ModalView startedModalView) {
        this.startedModalView = startedModalView;
    }
}
