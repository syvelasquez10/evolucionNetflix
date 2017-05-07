// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.uiaction.model;

import com.netflix.mediaclient.util.JsonUtils;
import org.json.JSONObject;
import com.netflix.mediaclient.service.logging.client.model.UIError;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import com.netflix.mediaclient.servicemgr.UserActionLogging$CommandName;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.service.logging.client.model.DeviceUniqueId;

public final class SearchEndedEvent extends BaseUIActionSessionEndedEvent
{
    public static final String TERM = "term";
    private static final String UIA_NAME = "search";
    private String mTerm;
    
    public SearchEndedEvent(final DeviceUniqueId deviceUniqueId, final long n, final IClientLogging$ModalView clientLogging$ModalView, final UserActionLogging$CommandName userActionLogging$CommandName, final IClientLogging$CompletionReason clientLogging$CompletionReason, final UIError uiError, final String mTerm) {
        super("search", deviceUniqueId, n, clientLogging$ModalView, userActionLogging$CommandName, clientLogging$CompletionReason, uiError);
        this.mTerm = mTerm;
    }
    
    public SearchEndedEvent(final JSONObject jsonObject) {
        super(jsonObject);
        this.mTerm = JsonUtils.getString(jsonObject, "term", null);
    }
    
    @Override
    protected JSONObject getData() {
        final JSONObject data = super.getData();
        if (this.mTerm != null) {
            data.put("term", (Object)this.mTerm);
        }
        return data;
    }
    
    @Override
    public boolean isMemberEvent() {
        return true;
    }
}
