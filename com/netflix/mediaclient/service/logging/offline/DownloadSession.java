// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.offline;

import com.netflix.mediaclient.service.logging.offline.model.DownloadSessionStartedEvent;
import com.netflix.mediaclient.service.logging.offline.model.DownloadSessionEndedEvent;
import com.netflix.mediaclient.service.logging.client.model.UIError;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.servicemgr.UserActionLogging$CommandName;
import com.netflix.mediaclient.service.logging.uiaction.BaseUIActionSession;

public final class DownloadSession extends BaseUIActionSession
{
    public static final String NAME = "Download";
    
    public DownloadSession(final UserActionLogging$CommandName userActionLogging$CommandName, final IClientLogging$ModalView clientLogging$ModalView) {
        super(userActionLogging$CommandName, clientLogging$ModalView);
    }
    
    public DownloadSessionEndedEvent createEndedEvent(final IClientLogging$CompletionReason clientLogging$CompletionReason, final UIError uiError, final IClientLogging$ModalView clientLogging$ModalView) {
        final DownloadSessionEndedEvent downloadSessionEndedEvent = new DownloadSessionEndedEvent(this.mId, System.currentTimeMillis() - this.mStarted, this.mView, this.mAction, clientLogging$CompletionReason, uiError);
        downloadSessionEndedEvent.setCategory(this.getCategory());
        downloadSessionEndedEvent.setSessionId(this.mId);
        return downloadSessionEndedEvent;
    }
    
    public DownloadSessionStartedEvent createStartedEvent(final String s) {
        final DownloadSessionStartedEvent downloadSessionStartedEvent = new DownloadSessionStartedEvent(s);
        downloadSessionStartedEvent.setCategory(this.getCategory());
        downloadSessionStartedEvent.setSessionId(this.mId);
        return downloadSessionStartedEvent;
    }
    
    @Override
    public String getName() {
        return "Download";
    }
}
