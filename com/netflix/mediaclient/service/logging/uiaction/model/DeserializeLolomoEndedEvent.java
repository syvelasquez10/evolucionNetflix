// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.uiaction.model;

import org.json.JSONObject;
import com.netflix.mediaclient.service.logging.client.model.UIError;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import com.netflix.mediaclient.servicemgr.UserActionLogging$CommandName;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.service.logging.client.model.DeviceUniqueId;

public class DeserializeLolomoEndedEvent extends BaseUIActionSessionEndedEvent
{
    public static final String LOLOMO_FETCHED_TIME = "lolomoTtl";
    private final long lolomoFetchTime;
    
    public DeserializeLolomoEndedEvent(final String s, final DeviceUniqueId deviceUniqueId, final long n, final IClientLogging$ModalView clientLogging$ModalView, final UserActionLogging$CommandName userActionLogging$CommandName, final IClientLogging$CompletionReason clientLogging$CompletionReason, final UIError uiError, final long lolomoFetchTime) {
        super(s, deviceUniqueId, n, clientLogging$ModalView, userActionLogging$CommandName, clientLogging$CompletionReason, uiError);
        this.lolomoFetchTime = lolomoFetchTime;
    }
    
    @Override
    protected JSONObject getData() {
        final JSONObject data = super.getData();
        if (this.lolomoFetchTime > -1L) {
            data.put("lolomoTtl", this.lolomoFetchTime);
        }
        return data;
    }
    
    @Override
    public boolean isMemberEvent() {
        return true;
    }
}
