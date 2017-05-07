// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.uiaction;

import com.netflix.mediaclient.service.logging.uiaction.model.UpgradeStreamsEndedEvent;
import com.netflix.mediaclient.service.logging.client.model.UIError;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.servicemgr.UserActionLogging$CommandName;
import com.netflix.mediaclient.servicemgr.UserActionLogging$Streams;

public final class UpgradeStreamsSession extends BaseUIActionSession
{
    public static final String NAME = "upgradeStreams";
    private UserActionLogging$Streams mCurrentStreams;
    
    public UpgradeStreamsSession(final UserActionLogging$CommandName userActionLogging$CommandName, final IClientLogging$ModalView clientLogging$ModalView, final UserActionLogging$Streams userActionLogging$Streams) {
        super(userActionLogging$CommandName, clientLogging$ModalView);
    }
    
    public UpgradeStreamsEndedEvent createEndedEvent(final IClientLogging$CompletionReason clientLogging$CompletionReason, final UIError uiError, final UserActionLogging$Streams userActionLogging$Streams) {
        final UpgradeStreamsEndedEvent upgradeStreamsEndedEvent = new UpgradeStreamsEndedEvent(this.mId, System.currentTimeMillis() - this.mStarted, this.mView, this.mAction, clientLogging$CompletionReason, uiError, this.mCurrentStreams, userActionLogging$Streams);
        upgradeStreamsEndedEvent.setCategory(this.getCategory());
        upgradeStreamsEndedEvent.setSessionId(this.mId);
        return upgradeStreamsEndedEvent;
    }
    
    @Override
    public String getName() {
        return "upgradeStreams";
    }
}
