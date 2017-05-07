// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.uiaction;

import com.netflix.mediaclient.service.logging.uiaction.model.RemoveFromPlaylistEndedEvent;
import com.netflix.mediaclient.service.logging.client.model.UIError;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.servicemgr.UserActionLogging$CommandName;

public final class RemoveFromPlaylistSession extends BaseUIActionSession
{
    public static final String NAME = "removeFromPlaylist";
    
    public RemoveFromPlaylistSession(final UserActionLogging$CommandName userActionLogging$CommandName, final IClientLogging$ModalView clientLogging$ModalView) {
        super(userActionLogging$CommandName, clientLogging$ModalView);
    }
    
    public RemoveFromPlaylistEndedEvent createEndedEvent(final IClientLogging$CompletionReason clientLogging$CompletionReason, final UIError uiError) {
        final RemoveFromPlaylistEndedEvent removeFromPlaylistEndedEvent = new RemoveFromPlaylistEndedEvent(this.mId, System.currentTimeMillis() - this.mStarted, this.mView, this.mAction, clientLogging$CompletionReason, uiError);
        removeFromPlaylistEndedEvent.setCategory(this.getCategory());
        removeFromPlaylistEndedEvent.setSessionId(this.mId);
        return removeFromPlaylistEndedEvent;
    }
    
    @Override
    public String getName() {
        return "removeFromPlaylist";
    }
}
