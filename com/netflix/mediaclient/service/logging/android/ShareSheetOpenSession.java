// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.android;

import com.netflix.mediaclient.service.logging.android.model.ShareSheetOpenEndedEvent;
import com.netflix.mediaclient.service.logging.client.model.UIError;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.servicemgr.UserActionLogging$CommandName;

public class ShareSheetOpenSession extends AndroidUIActionSession
{
    public static final String NAME = "shareOpenSheet";
    private String mUrl;
    
    public ShareSheetOpenSession(final String mUrl, final UserActionLogging$CommandName userActionLogging$CommandName, final IClientLogging$ModalView clientLogging$ModalView) {
        super(userActionLogging$CommandName, clientLogging$ModalView);
        this.mUrl = mUrl;
    }
    
    public ShareSheetOpenEndedEvent createEndedEvent(final IClientLogging$CompletionReason clientLogging$CompletionReason, final UIError uiError, final IClientLogging$ModalView clientLogging$ModalView) {
        final ShareSheetOpenEndedEvent shareSheetOpenEndedEvent = new ShareSheetOpenEndedEvent(this.mUrl, this.mId, System.currentTimeMillis() - this.mStarted, this.mView, this.mAction, clientLogging$CompletionReason, uiError);
        shareSheetOpenEndedEvent.setCategory(this.getCategory());
        shareSheetOpenEndedEvent.setSessionId(this.mId);
        return shareSheetOpenEndedEvent;
    }
    
    @Override
    public String getName() {
        return "shareOpenSheet";
    }
}
