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

public class AddProfileEndedEvent extends BaseUIActionSessionEndedEvent
{
    public static final String PROFILE = "profile";
    public static final String UIA_SESSION_NAME = "addProfile";
    private UserActionLogging.Profile mProfile;
    
    public AddProfileEndedEvent(final DeviceUniqueId deviceUniqueId, final long n, final IClientLogging.ModalView modalView, final UserActionLogging.CommandName commandName, final IClientLogging.CompletionReason completionReason, final UIError uiError, final UserActionLogging.Profile mProfile) {
        super("addProfile", deviceUniqueId, n, modalView, commandName, completionReason, uiError);
        if (mProfile == null) {
            throw new IllegalArgumentException("Profile can not be null!");
        }
        this.mProfile = mProfile;
    }
    
    public AddProfileEndedEvent(JSONObject jsonObject) throws JSONException {
        super(jsonObject);
        jsonObject = JsonUtils.getJSONObject(jsonObject, "profile", null);
        if (jsonObject == null) {
            throw new JSONException("Profile object missing!");
        }
        this.mProfile = new UserActionLogging.Profile(jsonObject);
    }
    
    @Override
    protected JSONObject getData() throws JSONException {
        final JSONObject data = super.getData();
        data.put("profile", (Object)this.mProfile.toJson());
        return data;
    }
    
    @Override
    public boolean isMemberEvent() {
        return true;
    }
}
