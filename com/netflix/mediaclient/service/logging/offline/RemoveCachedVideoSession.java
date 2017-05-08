// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.offline;

import com.netflix.mediaclient.service.logging.offline.model.RemoveCachedVideoSessionEndedEvent;
import com.netflix.mediaclient.service.logging.client.model.UIError;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.servicemgr.UserActionLogging$CommandName;
import com.netflix.mediaclient.service.logging.uiaction.BaseUIActionSession;

public final class RemoveCachedVideoSession extends BaseUIActionSession
{
    public static final String NAME = "RemoveCachedVideo";
    private String mOxId;
    
    public RemoveCachedVideoSession(final String mOxId, final UserActionLogging$CommandName userActionLogging$CommandName) {
        super(userActionLogging$CommandName, null);
        this.mOxId = mOxId;
    }
    
    public RemoveCachedVideoSessionEndedEvent createEndedEvent(final IClientLogging$CompletionReason clientLogging$CompletionReason, final UIError uiError, final IClientLogging$ModalView clientLogging$ModalView) {
        final RemoveCachedVideoSessionEndedEvent removeCachedVideoSessionEndedEvent = new RemoveCachedVideoSessionEndedEvent(this.mId, System.currentTimeMillis() - this.mStarted, this.mOxId, clientLogging$ModalView, this.mAction, clientLogging$CompletionReason, uiError);
        removeCachedVideoSessionEndedEvent.setCategory(this.getCategory());
        removeCachedVideoSessionEndedEvent.setSessionId(this.mId);
        return removeCachedVideoSessionEndedEvent;
    }
    
    @Override
    public String getName() {
        return "RemoveCachedVideo";
    }
}
