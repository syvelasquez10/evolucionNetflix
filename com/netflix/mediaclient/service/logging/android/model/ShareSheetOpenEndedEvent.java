// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.android.model;

import org.json.JSONObject;
import com.netflix.mediaclient.service.logging.client.model.UIError;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import com.netflix.mediaclient.servicemgr.UserActionLogging$CommandName;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.service.logging.client.model.DeviceUniqueId;
import com.netflix.mediaclient.service.logging.uiaction.model.BaseUIActionSessionEndedEvent;

public class ShareSheetOpenEndedEvent extends BaseUIActionSessionEndedEvent
{
    private static final String UIA_NAME = "shareOpenSheet";
    private static final String URL_FIELD = "url";
    private String mUrl;
    
    public ShareSheetOpenEndedEvent(final String mUrl, final DeviceUniqueId deviceUniqueId, final long n, final IClientLogging$ModalView clientLogging$ModalView, final UserActionLogging$CommandName userActionLogging$CommandName, final IClientLogging$CompletionReason clientLogging$CompletionReason, final UIError uiError) {
        super("shareOpenSheet", deviceUniqueId, n, clientLogging$ModalView, userActionLogging$CommandName, clientLogging$CompletionReason, uiError);
        this.mUrl = mUrl;
    }
    
    public ShareSheetOpenEndedEvent(final JSONObject jsonObject) {
        super(jsonObject);
        this.mUrl = jsonObject.getString("url");
    }
    
    @Override
    protected JSONObject getData() {
        final JSONObject data = super.getData();
        data.put("url", (Object)this.mUrl);
        return data;
    }
    
    @Override
    public boolean isMemberEvent() {
        return true;
    }
}
