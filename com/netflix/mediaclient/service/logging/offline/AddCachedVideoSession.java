// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.offline;

import com.netflix.mediaclient.service.logging.offline.model.AddCachedVideoSessionEndedEvent;
import com.netflix.mediaclient.service.logging.client.model.UIError;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.servicemgr.UserActionLogging$CommandName;
import com.netflix.mediaclient.service.logging.uiaction.BaseUIActionSession;

public final class AddCachedVideoSession extends BaseUIActionSession
{
    public static final String NAME = "AddCachedVideo";
    private String mOxId;
    
    public AddCachedVideoSession(final String mOxId, final UserActionLogging$CommandName userActionLogging$CommandName) {
        super(userActionLogging$CommandName, null);
        this.mOxId = mOxId;
    }
    
    public AddCachedVideoSessionEndedEvent createEndedEvent(final IClientLogging$CompletionReason clientLogging$CompletionReason, final UIError uiError, final IClientLogging$ModalView clientLogging$ModalView) {
        final AddCachedVideoSessionEndedEvent addCachedVideoSessionEndedEvent = new AddCachedVideoSessionEndedEvent(this.mId, System.currentTimeMillis() - this.mStarted, this.mOxId, clientLogging$ModalView, this.mAction, clientLogging$CompletionReason, uiError);
        addCachedVideoSessionEndedEvent.setCategory(this.getCategory());
        addCachedVideoSessionEndedEvent.setSessionId(this.mId);
        return addCachedVideoSessionEndedEvent;
    }
    
    @Override
    public String getName() {
        return "AddCachedVideo";
    }
}
