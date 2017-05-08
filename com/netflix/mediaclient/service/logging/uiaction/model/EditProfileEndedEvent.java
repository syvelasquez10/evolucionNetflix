// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.uiaction.model;

import com.netflix.mediaclient.util.JsonUtils;
import org.json.JSONObject;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.logging.client.model.UIError;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import com.netflix.mediaclient.servicemgr.UserActionLogging$CommandName;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.service.logging.client.model.DeviceUniqueId;
import com.netflix.mediaclient.servicemgr.UserActionLogging$Profile;

public final class EditProfileEndedEvent extends BaseUIActionSessionEndedEvent
{
    public static final String PROFILE = "profile";
    protected static final String TAG = "profile";
    public static final String UIA_SESSION_NAME = "editProfile";
    private UserActionLogging$Profile mProfile;
    
    public EditProfileEndedEvent(final DeviceUniqueId deviceUniqueId, final long n, final IClientLogging$ModalView clientLogging$ModalView, final UserActionLogging$CommandName userActionLogging$CommandName, final IClientLogging$CompletionReason clientLogging$CompletionReason, final UIError uiError, final UserActionLogging$Profile mProfile) {
        super("editProfile", deviceUniqueId, n, clientLogging$ModalView, userActionLogging$CommandName, clientLogging$CompletionReason, uiError);
        if (mProfile == null) {
            Log.w("profile", "EditProfileEndedEvent: Profile object missing!");
            return;
        }
        this.mProfile = mProfile;
    }
    
    public EditProfileEndedEvent(JSONObject jsonObject) {
        super(jsonObject);
        jsonObject = JsonUtils.getJSONObject(jsonObject, "profile", (JSONObject)null);
        if (jsonObject == null) {
            Log.w("profile", "EditProfileEndedEvent: Profile object missing!");
            return;
        }
        this.mProfile = new UserActionLogging$Profile(jsonObject);
    }
    
    @Override
    protected JSONObject getData() {
        final JSONObject data = super.getData();
        if (this.mProfile != null) {
            data.put("profile", (Object)this.mProfile.toJson());
        }
        return data;
    }
    
    @Override
    public boolean isMemberEvent() {
        return true;
    }
}
