// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.uiaction;

import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.logging.uiaction.model.NavigationEndedEvent;
import com.netflix.mediaclient.service.logging.client.model.UIError;
import com.netflix.mediaclient.servicemgr.IClientLogging;
import com.netflix.mediaclient.servicemgr.UserActionLogging;

public final class NavigationSession extends BaseUIActionSession
{
    public static final String NAME = "navigate";
    private static final String TAG = "nf_log";
    
    public NavigationSession(final UserActionLogging.CommandName commandName, final IClientLogging.ModalView modalView) {
        super(commandName, modalView);
    }
    
    public NavigationEndedEvent createEndedEvent(final IClientLogging.ModalView modalView, final IClientLogging.CompletionReason completionReason, final UIError uiError) {
        if (modalView == this.mView) {
            if (Log.isLoggable("nf_log", 3)) {
                Log.d("nf_log", "We stayed in same view, do not report " + modalView);
            }
            return null;
        }
        if (Log.isLoggable("nf_log", 3)) {
            Log.d("nf_log", "We started from " + modalView + " and ended up on " + this.mView);
        }
        final NavigationEndedEvent navigationEndedEvent = new NavigationEndedEvent(this.mId, System.currentTimeMillis() - this.mStarted, this.mAction, completionReason, uiError, this.mView);
        navigationEndedEvent.setCategory(this.getCategory());
        navigationEndedEvent.setSessionId(this.mId);
        return navigationEndedEvent;
    }
    
    @Override
    public String getName() {
        return "navigate";
    }
}
