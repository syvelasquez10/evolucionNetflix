// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.android;

import com.netflix.mediaclient.service.logging.android.model.ShareSheetEndedEvent;
import com.netflix.mediaclient.service.logging.client.model.UIError;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.servicemgr.UserActionLogging$CommandName;

public class ShareSheetSession extends AndroidUIActionSession
{
    public static final String NAME = "shareSheet";
    private String mUrl;
    
    public ShareSheetSession(final String mUrl, final UserActionLogging$CommandName userActionLogging$CommandName, final IClientLogging$ModalView clientLogging$ModalView) {
        super(userActionLogging$CommandName, clientLogging$ModalView);
        this.mUrl = mUrl;
    }
    
    public ShareSheetEndedEvent createEndedEvent(final IClientLogging$CompletionReason clientLogging$CompletionReason, final UIError uiError, final IClientLogging$ModalView clientLogging$ModalView) {
        final ShareSheetEndedEvent shareSheetEndedEvent = new ShareSheetEndedEvent(this.mUrl, this.mId, System.currentTimeMillis() - this.mStarted, this.mView, this.mAction, clientLogging$CompletionReason, uiError);
        shareSheetEndedEvent.setCategory(this.getCategory());
        shareSheetEndedEvent.setSessionId(this.mId);
        return shareSheetEndedEvent;
    }
    
    @Override
    public String getName() {
        return "shareSheet";
    }
}
