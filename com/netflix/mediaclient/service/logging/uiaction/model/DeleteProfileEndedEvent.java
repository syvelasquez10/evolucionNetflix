// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.uiaction.model;

import org.json.JSONException;
import com.netflix.mediaclient.util.JsonUtils;
import org.json.JSONObject;
import com.netflix.mediaclient.service.logging.client.model.UIError;
import com.netflix.mediaclient.servicemgr.UserActionLogging;
import com.netflix.mediaclient.servicemgr.IClientLogging;
import com.netflix.mediaclient.service.logging.client.model.DeviceUniqueId;

public final class DeleteProfileEndedEvent extends BaseUIActionSessionEndedEvent
{
    public static final String PROFILE_ID = "profileId";
    public static final String UIA_SESSION_NAME = "deleteProfile";
    private String mProfileId;
    
    public DeleteProfileEndedEvent(final DeviceUniqueId deviceUniqueId, final long n, final IClientLogging.ModalView modalView, final UserActionLogging.CommandName commandName, final IClientLogging.CompletionReason completionReason, final UIError uiError, final String mProfileId) {
        super("deleteProfile", deviceUniqueId, n, modalView, commandName, completionReason, uiError);
        this.mProfileId = mProfileId;
        if (this.mProfileId == null) {
            throw new IllegalArgumentException("Profile ID can not be null!");
        }
    }
    
    public DeleteProfileEndedEvent(final JSONObject jsonObject) throws JSONException {
        super(jsonObject);
        this.mProfileId = JsonUtils.getString(jsonObject, "profileId", null);
    }
    
    @Override
    protected JSONObject getData() throws JSONException {
        final JSONObject data = super.getData();
        data.put("profileId", (Object)this.mProfileId);
        return data;
    }
    
    @Override
    public boolean isMemberEvent() {
        return true;
    }
}
