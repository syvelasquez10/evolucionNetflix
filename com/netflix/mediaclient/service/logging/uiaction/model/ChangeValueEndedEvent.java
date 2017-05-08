// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.uiaction.model;

import org.json.JSONObject;
import com.netflix.mediaclient.service.logging.client.model.UIError;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import com.netflix.mediaclient.servicemgr.UserActionLogging$CommandName;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.service.logging.client.model.DeviceUniqueId;
import com.netflix.mediaclient.servicemgr.UIViewLogging$UIViewCommandName;

public class ChangeValueEndedEvent extends BaseUIActionSessionEndedEvent
{
    public static final String NEW_VALUE = "newValue";
    public static final String PARAM_NAME = "name";
    public static final String UIA_SESSION_ENDED;
    public static final String UIA_SESSION_NAME = "command";
    private String mNewValue;
    
    static {
        UIA_SESSION_ENDED = UIViewLogging$UIViewCommandName.changeValue + ".ended";
    }
    
    public ChangeValueEndedEvent(final DeviceUniqueId deviceUniqueId, final long n, final IClientLogging$ModalView clientLogging$ModalView, final UserActionLogging$CommandName userActionLogging$CommandName, final IClientLogging$CompletionReason clientLogging$CompletionReason, final UIError uiError, final String mNewValue) {
        super("command", deviceUniqueId, n, clientLogging$ModalView, userActionLogging$CommandName, clientLogging$CompletionReason, uiError);
        this.mNewValue = mNewValue;
    }
    
    @Override
    protected JSONObject getData() {
        final JSONObject data = super.getData();
        data.put("newValue", (Object)this.mNewValue);
        data.put("name", (Object)ChangeValueEndedEvent.UIA_SESSION_ENDED);
        return data;
    }
    
    @Override
    public boolean isMemberEvent() {
        return true;
    }
}
