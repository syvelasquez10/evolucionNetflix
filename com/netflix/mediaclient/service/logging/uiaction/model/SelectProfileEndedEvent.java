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
import com.netflix.mediaclient.servicemgr.UserActionLogging$RememberProfile;

public final class SelectProfileEndedEvent extends BaseUIActionSessionEndedEvent
{
    public static final String PROFILE_ID = "profileId";
    public static final String REMEMBER_PROFILE = "rememberProfile";
    public static final String UIA_SESSION_NAME = "selectProfile";
    private String mProfileId;
    private UserActionLogging$RememberProfile mRememberProfile;
    
    public SelectProfileEndedEvent(final DeviceUniqueId deviceUniqueId, final long n, final IClientLogging$ModalView clientLogging$ModalView, final UserActionLogging$CommandName userActionLogging$CommandName, final IClientLogging$CompletionReason clientLogging$CompletionReason, final UIError uiError, final String mProfileId, final UserActionLogging$RememberProfile mRememberProfile) {
        super("selectProfile", deviceUniqueId, n, clientLogging$ModalView, userActionLogging$CommandName, clientLogging$CompletionReason, uiError);
        this.mProfileId = mProfileId;
        this.mRememberProfile = mRememberProfile;
    }
    
    public SelectProfileEndedEvent(final JSONObject jsonObject) {
        super(jsonObject);
        this.mProfileId = JsonUtils.getString(jsonObject, "profileId", null);
        final String string = JsonUtils.getString(jsonObject, "rememberProfile", null);
        if (string != null) {
            this.mRememberProfile = Enum.valueOf(UserActionLogging$RememberProfile.class, string);
        }
    }
    
    @Override
    protected JSONObject getData() {
        final JSONObject data = super.getData();
        if (this.mProfileId != null) {
            data.put("profileId", (Object)this.mProfileId);
        }
        if (this.mRememberProfile != null) {
            data.put("rememberProfile", (Object)this.mRememberProfile.name());
        }
        return data;
    }
    
    @Override
    public boolean isMemberEvent() {
        return true;
    }
}
