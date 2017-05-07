// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.uiaction.model;

import org.json.JSONException;
import com.netflix.mediaclient.util.JsonUtils;
import org.json.JSONObject;
import com.netflix.mediaclient.service.logging.client.model.UIError;
import com.netflix.mediaclient.servicemgr.IClientLogging;
import com.netflix.mediaclient.service.logging.client.model.DeviceUniqueId;
import com.netflix.mediaclient.servicemgr.UserActionLogging;

public final class SelectProfileEndedEvent extends BaseUIActionSessionEndedEvent
{
    public static final String PROFILE_ID = "profileId";
    public static final String REMEMBER_PROFILE = "rememberProfile";
    public static final String UIA_SESSION_NAME = "selectProfile";
    private String mProfileId;
    private UserActionLogging.RememberProfile mRememberProfile;
    
    public SelectProfileEndedEvent(final DeviceUniqueId deviceUniqueId, final long n, final IClientLogging.ModalView modalView, final UserActionLogging.CommandName commandName, final IClientLogging.CompletionReason completionReason, final UIError uiError, final String mProfileId, final UserActionLogging.RememberProfile mRememberProfile) {
        super("selectProfile", deviceUniqueId, n, modalView, commandName, completionReason, uiError);
        this.mProfileId = mProfileId;
        this.mRememberProfile = mRememberProfile;
        if (this.mProfileId == null) {
            throw new IllegalArgumentException("Profile ID can not be null!");
        }
    }
    
    public SelectProfileEndedEvent(final JSONObject jsonObject) throws JSONException {
        super(jsonObject);
        this.mProfileId = JsonUtils.getString(jsonObject, "profileId", null);
        final String string = JsonUtils.getString(jsonObject, "rememberProfile", null);
        if (string != null) {
            this.mRememberProfile = Enum.valueOf(UserActionLogging.RememberProfile.class, string);
        }
    }
    
    @Override
    protected JSONObject getData() throws JSONException {
        final JSONObject data = super.getData();
        data.put("profileId", (Object)this.mProfileId);
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
