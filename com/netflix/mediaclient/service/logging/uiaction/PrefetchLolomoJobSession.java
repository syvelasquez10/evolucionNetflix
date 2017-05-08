// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.uiaction;

import com.netflix.mediaclient.service.logging.uiaction.model.PrefetchLolomoJobEndedEvent;
import com.netflix.mediaclient.service.logging.client.model.UIError;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.servicemgr.UserActionLogging$CommandName;

public class PrefetchLolomoJobSession extends BaseUIActionSession
{
    public static final String NAME = "prefetchLolomoJob";
    
    public PrefetchLolomoJobSession(final UserActionLogging$CommandName userActionLogging$CommandName, final IClientLogging$ModalView clientLogging$ModalView) {
        super(userActionLogging$CommandName, clientLogging$ModalView);
    }
    
    public PrefetchLolomoJobEndedEvent createEndedEvent(final IClientLogging$CompletionReason clientLogging$CompletionReason, final UIError uiError) {
        final PrefetchLolomoJobEndedEvent prefetchLolomoJobEndedEvent = new PrefetchLolomoJobEndedEvent("prefetchLolomoJob", this.mId, System.currentTimeMillis() - this.mStarted, this.mView, this.mAction, clientLogging$CompletionReason, uiError);
        prefetchLolomoJobEndedEvent.setCategory(this.getCategory());
        prefetchLolomoJobEndedEvent.setSessionId(this.mId);
        return prefetchLolomoJobEndedEvent;
    }
    
    @Override
    public String getName() {
        return "prefetchLolomoJob";
    }
}
