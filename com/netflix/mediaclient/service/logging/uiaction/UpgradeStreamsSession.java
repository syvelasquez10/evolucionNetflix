// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.uiaction;

import com.netflix.mediaclient.service.logging.uiaction.model.UpgradeStreamsEndedEvent;
import com.netflix.mediaclient.service.logging.client.model.UIError;
import com.netflix.mediaclient.servicemgr.IClientLogging;
import com.netflix.mediaclient.servicemgr.UserActionLogging;

public final class UpgradeStreamsSession extends BaseUIActionSession
{
    public static final String NAME = "upgradeStreams";
    private UserActionLogging.Streams mCurrentStreams;
    
    public UpgradeStreamsSession(final UserActionLogging.CommandName commandName, final IClientLogging.ModalView modalView, final UserActionLogging.Streams streams) {
        super(commandName, modalView);
    }
    
    public UpgradeStreamsEndedEvent createEndedEvent(final IClientLogging.CompletionReason completionReason, final UIError uiError, final UserActionLogging.Streams streams) {
        final UpgradeStreamsEndedEvent upgradeStreamsEndedEvent = new UpgradeStreamsEndedEvent(this.mId, System.currentTimeMillis() - this.mStarted, this.mView, this.mAction, completionReason, uiError, this.mCurrentStreams, streams);
        upgradeStreamsEndedEvent.setCategory(this.getCategory());
        upgradeStreamsEndedEvent.setSessionId(this.mId);
        return upgradeStreamsEndedEvent;
    }
    
    @Override
    public String getName() {
        return "upgradeStreams";
    }
}
