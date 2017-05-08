// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.offline;

import com.netflix.mediaclient.service.logging.offline.model.CachedPlaySessionStartedEvent;
import com.netflix.mediaclient.service.logging.offline.model.CachedPlaySessionEndedEvent;
import com.netflix.mediaclient.service.logging.client.model.UIError;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.servicemgr.UserActionLogging$CommandName;
import com.netflix.mediaclient.service.logging.uiaction.BaseUIActionSession;

public class CachedPlaySession extends BaseUIActionSession
{
    public static final String NAME = "CachedPlay";
    
    public CachedPlaySession(final UserActionLogging$CommandName userActionLogging$CommandName, final IClientLogging$ModalView clientLogging$ModalView) {
        super(userActionLogging$CommandName, clientLogging$ModalView);
    }
    
    public CachedPlaySessionEndedEvent createEndedEvent(final IClientLogging$CompletionReason clientLogging$CompletionReason, final UIError uiError, final IClientLogging$ModalView clientLogging$ModalView) {
        final CachedPlaySessionEndedEvent cachedPlaySessionEndedEvent = new CachedPlaySessionEndedEvent(this.mId, System.currentTimeMillis() - this.mStarted, this.mView, this.mAction, clientLogging$CompletionReason, uiError);
        cachedPlaySessionEndedEvent.setCategory(this.getCategory());
        cachedPlaySessionEndedEvent.setSessionId(this.mId);
        return cachedPlaySessionEndedEvent;
    }
    
    public CachedPlaySessionStartedEvent createStartedEvent(final String s, final String s2, final int n, final int n2, final int n3) {
        final CachedPlaySessionStartedEvent cachedPlaySessionStartedEvent = new CachedPlaySessionStartedEvent(s, s2, n, n2, n3);
        cachedPlaySessionStartedEvent.setCategory(this.getCategory());
        cachedPlaySessionStartedEvent.setSessionId(this.mId);
        return cachedPlaySessionStartedEvent;
    }
    
    @Override
    public String getName() {
        return "CachedPlay";
    }
}
