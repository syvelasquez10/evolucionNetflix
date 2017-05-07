// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.uiaction.model;

import org.json.JSONException;
import org.json.JSONObject;
import com.netflix.mediaclient.service.logging.client.model.UIError;
import com.netflix.mediaclient.servicemgr.UserActionLogging;
import com.netflix.mediaclient.servicemgr.IClientLogging;
import com.netflix.mediaclient.service.logging.client.model.DeviceUniqueId;

public class SayThanksEndedEvent extends BaseUIActionSessionEndedEvent
{
    private static final String UIA_NAME = "sayThanks";
    
    public SayThanksEndedEvent(final DeviceUniqueId deviceUniqueId, final long n, final IClientLogging.ModalView modalView, final UserActionLogging.CommandName commandName, final IClientLogging.CompletionReason completionReason, final UIError uiError) {
        super("sayThanks", deviceUniqueId, n, modalView, commandName, completionReason, uiError);
    }
    
    public SayThanksEndedEvent(final JSONObject jsonObject) throws JSONException {
        super(jsonObject);
    }
    
    @Override
    public boolean isMemberEvent() {
        return true;
    }
}
