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

public final class DeleteProfileEndedEvent extends BaseUIActionSessionEndedEvent
{
    public static final String PROFILE_ID = "profileId";
    public static final String UIA_SESSION_NAME = "deleteProfile";
    private String mProfileId;
    
    public DeleteProfileEndedEvent(final DeviceUniqueId deviceUniqueId, final long n, final IClientLogging$ModalView clientLogging$ModalView, final UserActionLogging$CommandName userActionLogging$CommandName, final IClientLogging$CompletionReason clientLogging$CompletionReason, final UIError uiError, final String mProfileId) {
        super("deleteProfile", deviceUniqueId, n, clientLogging$ModalView, userActionLogging$CommandName, clientLogging$CompletionReason, uiError);
        this.mProfileId = mProfileId;
    }
    
    public DeleteProfileEndedEvent(final JSONObject jsonObject) {
        super(jsonObject);
        this.mProfileId = JsonUtils.getString(jsonObject, "profileId", (String)null);
    }
    
    @Override
    protected JSONObject getData() {
        final JSONObject data = super.getData();
        if (this.mProfileId != null) {
            data.put("profileId", (Object)this.mProfileId);
        }
        return data;
    }
    
    @Override
    public boolean isMemberEvent() {
        return true;
    }
}
