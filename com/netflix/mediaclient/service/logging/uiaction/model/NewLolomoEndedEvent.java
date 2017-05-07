// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.uiaction.model;

import com.netflix.mediaclient.util.StringUtils;
import org.json.JSONObject;
import com.netflix.mediaclient.service.logging.client.model.UIError;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import com.netflix.mediaclient.servicemgr.UserActionLogging$CommandName;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.service.logging.client.model.DeviceUniqueId;

public final class NewLolomoEndedEvent extends BaseUIActionSessionEndedEvent
{
    public static final String MERCURY_EVENT_GUID = "mercuryEventGUID";
    public static final String MERCURY_MESSAGE_GUID = "mercuryMessageGUID";
    public static final String RENO_CAUSE = "cause";
    public static final String RENO_CREATION_TIME = "creationTS";
    public static final String RENO_MESSAGE_GUID = "messageGUID";
    private static final String UIA_NAME = "NewLolomo";
    private String mercuryEventGuid;
    private String mercuryMessageGuid;
    private String renoCause;
    private long renoCreationTimestamp;
    private String renoMessageGuid;
    
    public NewLolomoEndedEvent(final DeviceUniqueId deviceUniqueId, final long n, final IClientLogging$ModalView clientLogging$ModalView, final UserActionLogging$CommandName userActionLogging$CommandName, final IClientLogging$CompletionReason clientLogging$CompletionReason, final UIError uiError, final String renoCause, final String renoMessageGuid, final long renoCreationTimestamp, final String mercuryMessageGuid, final String mercuryEventGuid) {
        super("NewLolomo", deviceUniqueId, n, clientLogging$ModalView, userActionLogging$CommandName, clientLogging$CompletionReason, uiError);
        this.renoCause = renoCause;
        this.renoMessageGuid = renoMessageGuid;
        this.renoCreationTimestamp = renoCreationTimestamp;
        this.mercuryMessageGuid = mercuryMessageGuid;
        this.mercuryEventGuid = mercuryEventGuid;
    }
    
    @Override
    protected JSONObject getData() {
        final JSONObject data = super.getData();
        if (StringUtils.isNotEmpty(this.renoCause)) {
            data.put("cause", (Object)this.renoCause);
        }
        if (StringUtils.isNotEmpty(this.renoMessageGuid)) {
            data.put("messageGUID", (Object)this.renoMessageGuid);
        }
        data.put("creationTS", this.renoCreationTimestamp);
        if (StringUtils.isNotEmpty(this.mercuryMessageGuid)) {
            data.put("mercuryMessageGUID", (Object)this.mercuryMessageGuid);
        }
        if (StringUtils.isNotEmpty(this.mercuryEventGuid)) {
            data.put("mercuryEventGUID", (Object)this.mercuryEventGuid);
        }
        return data;
    }
    
    @Override
    public boolean isMemberEvent() {
        return true;
    }
}
