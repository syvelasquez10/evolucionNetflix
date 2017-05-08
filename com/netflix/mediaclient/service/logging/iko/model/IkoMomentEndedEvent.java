// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.iko.model;

import org.json.JSONObject;
import com.netflix.mediaclient.service.logging.client.model.UIError;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import com.netflix.mediaclient.servicemgr.UserActionLogging$CommandName;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.service.logging.client.model.DeviceUniqueId;
import com.netflix.mediaclient.service.logging.uiaction.model.BaseUIActionSessionEndedEvent;

public class IkoMomentEndedEvent extends BaseUIActionSessionEndedEvent
{
    public static final String EXPECTED_VIDEO_OFFSET = "expectedVideoOffset";
    public static final String IKO_MOMENT_STATE = "ikoMomentState";
    public static final String MOMENT_ID = "momentId";
    public static final String MOMENT_TYPE = "momentType";
    private int expectedVideoOffset;
    private String ikoMomentState;
    private String momentId;
    private String momentType;
    
    public IkoMomentEndedEvent(final String s, final DeviceUniqueId deviceUniqueId, final long n, final IClientLogging$ModalView clientLogging$ModalView, final UserActionLogging$CommandName userActionLogging$CommandName, final IClientLogging$CompletionReason clientLogging$CompletionReason, final UIError uiError, final String momentId, final String momentType, final int expectedVideoOffset, final String ikoMomentState) {
        super(s, deviceUniqueId, n, clientLogging$ModalView, userActionLogging$CommandName, clientLogging$CompletionReason, uiError);
        this.momentId = momentId;
        this.momentType = momentType;
        this.expectedVideoOffset = expectedVideoOffset;
        this.ikoMomentState = ikoMomentState;
    }
    
    @Override
    protected JSONObject getData() {
        final JSONObject data = super.getData();
        data.put("momentId", (Object)this.momentId);
        data.put("momentType", (Object)this.momentType);
        data.put("expectedVideoOffset", this.expectedVideoOffset);
        data.put("ikoMomentState", (Object)this.ikoMomentState);
        return data;
    }
    
    @Override
    public boolean isMemberEvent() {
        return true;
    }
}
