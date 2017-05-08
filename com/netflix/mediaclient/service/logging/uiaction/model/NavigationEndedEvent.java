// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.uiaction.model;

import com.netflix.mediaclient.util.JsonUtils;
import org.json.JSONObject;
import com.netflix.mediaclient.service.logging.client.model.UIError;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import com.netflix.mediaclient.servicemgr.UserActionLogging$CommandName;
import com.netflix.mediaclient.service.logging.client.model.DeviceUniqueId;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;

public class NavigationEndedEvent extends BaseUIActionSessionEndedEvent
{
    public static final String DESTINATION_VIEW = "destinationView";
    public static final String START_VIEW = "sourceModalView";
    public static final String UIA_SESSION_NAME = "navigate";
    private IClientLogging$ModalView mDestinationView;
    private IClientLogging$ModalView mStartView;
    
    public NavigationEndedEvent(final DeviceUniqueId deviceUniqueId, final long n, final UserActionLogging$CommandName userActionLogging$CommandName, final IClientLogging$CompletionReason clientLogging$CompletionReason, final UIError uiError, final IClientLogging$ModalView mStartView, final IClientLogging$ModalView mDestinationView) {
        super("navigate", deviceUniqueId, n, mDestinationView, userActionLogging$CommandName, clientLogging$CompletionReason, uiError);
        this.mStartView = mStartView;
        this.mDestinationView = mDestinationView;
    }
    
    public NavigationEndedEvent(final JSONObject jsonObject) {
        super(jsonObject);
        this.mStartView = IClientLogging$ModalView.valueOf(JsonUtils.getString(jsonObject, "sourceModalView", (String)null));
        this.mDestinationView = IClientLogging$ModalView.valueOf(JsonUtils.getString(jsonObject, "destinationView", (String)null));
    }
    
    @Override
    protected JSONObject getData() {
        final JSONObject data = super.getData();
        if (this.mDestinationView != null) {
            data.put("destinationView", (Object)this.mDestinationView.name());
        }
        if (this.mStartView != null) {
            data.put("sourceModalView", (Object)this.mStartView.name());
        }
        return data;
    }
    
    @Override
    public boolean isMemberEvent() {
        return true;
    }
}
