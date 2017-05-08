// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.offline.model;

import org.json.JSONObject;
import com.netflix.mediaclient.service.logging.client.model.UIError;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import com.netflix.mediaclient.servicemgr.UserActionLogging$CommandName;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.service.logging.client.model.DeviceUniqueId;
import com.netflix.mediaclient.service.logging.uiaction.model.BaseUIActionSessionEndedEvent;

public final class DownloadSessionEndedEvent extends BaseUIActionSessionEndedEvent
{
    private static final String TAG = "nf_log_DownloadSessionEndedEvent";
    private static final String UIA_NAME = "Download";
    
    public DownloadSessionEndedEvent(final DeviceUniqueId deviceUniqueId, final long n, final IClientLogging$ModalView clientLogging$ModalView, final UserActionLogging$CommandName userActionLogging$CommandName, final IClientLogging$CompletionReason clientLogging$CompletionReason, final UIError uiError) {
        super("Download", deviceUniqueId, n, clientLogging$ModalView, userActionLogging$CommandName, clientLogging$CompletionReason, uiError);
    }
    
    public DownloadSessionEndedEvent(final JSONObject jsonObject) {
        super(jsonObject);
    }
    
    @Override
    public boolean isMemberEvent() {
        return true;
    }
}
