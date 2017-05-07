// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.uiaction;

import com.netflix.mediaclient.service.logging.uiaction.model.RemoveFromPlaylistEndedEvent;
import com.netflix.mediaclient.service.logging.client.model.UIError;
import com.netflix.mediaclient.servicemgr.IClientLogging;
import com.netflix.mediaclient.servicemgr.UserActionLogging;

public final class RemoveFromPlaylistSession extends BaseUIActionSession
{
    public static final String NAME = "removeFromPlaylist";
    
    public RemoveFromPlaylistSession(final UserActionLogging.CommandName commandName, final IClientLogging.ModalView modalView) {
        super(commandName, modalView);
    }
    
    public RemoveFromPlaylistEndedEvent createEndedEvent(final IClientLogging.CompletionReason completionReason, final UIError uiError) {
        final RemoveFromPlaylistEndedEvent removeFromPlaylistEndedEvent = new RemoveFromPlaylistEndedEvent(this.mId, System.currentTimeMillis() - this.mStarted, this.mView, this.mAction, completionReason, uiError);
        removeFromPlaylistEndedEvent.setCategory(this.getCategory());
        removeFromPlaylistEndedEvent.setSessionId(this.mId);
        return removeFromPlaylistEndedEvent;
    }
    
    @Override
    public String getName() {
        return "removeFromPlaylist";
    }
}
