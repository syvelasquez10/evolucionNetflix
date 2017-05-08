// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.offline.model;

import org.json.JSONObject;
import com.netflix.mediaclient.util.LogUtils;
import org.json.JSONException;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.service.logging.client.model.UIError;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import com.netflix.mediaclient.servicemgr.UserActionLogging$CommandName;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.service.logging.client.model.DeviceUniqueId;
import com.netflix.mediaclient.service.logging.uiaction.model.BaseUIActionSessionEndedEvent;

public final class AddCachedVideoSessionEndedEvent extends BaseUIActionSessionEndedEvent
{
    public static final String OXID = "oxid";
    private static final String UIA_NAME = "AddCachedVideo";
    private String oxid;
    
    public AddCachedVideoSessionEndedEvent(final DeviceUniqueId deviceUniqueId, final long n, final String oxid, final IClientLogging$ModalView clientLogging$ModalView, final UserActionLogging$CommandName userActionLogging$CommandName, final IClientLogging$CompletionReason clientLogging$CompletionReason, final UIError uiError) {
        super("AddCachedVideo", deviceUniqueId, n, clientLogging$ModalView, userActionLogging$CommandName, clientLogging$CompletionReason, uiError);
        if (StringUtils.isEmpty(oxid)) {
            LogUtils.reportErrorSafely("AddCachedVideoSessionEndedEvent: oxid is missing!", (Throwable)new JSONException("AddCachedVideoSessionEndedEvent: oxid is missing!"));
            return;
        }
        this.oxid = oxid;
    }
    
    public AddCachedVideoSessionEndedEvent(final JSONObject jsonObject) {
        super(jsonObject);
        if (jsonObject != null) {
            this.oxid = jsonObject.optString("oxid", (String)null);
            if (this.oxid == null) {
                LogUtils.reportErrorSafely("AddCachedVideoSessionEndedEvent: oxid is missing!", (Throwable)new JSONException("AddCachedVideoSessionEndedEvent: oxid is missing!"));
            }
        }
    }
    
    @Override
    protected JSONObject getData() {
        final JSONObject data = super.getData();
        if (StringUtils.isNotEmpty(this.oxid)) {
            data.put("oxid", (Object)this.oxid);
        }
        return data;
    }
    
    @Override
    public boolean isMemberEvent() {
        return true;
    }
}
