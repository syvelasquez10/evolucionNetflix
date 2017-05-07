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
import com.netflix.mediaclient.servicemgr.UserActionLogging$Streams;

public final class UpgradeStreamsEndedEvent extends BaseUIActionSessionEndedEvent
{
    private static final String CURRENT_STREAMS = "currentStreams";
    private static final String END_STREAMS = "endStreams";
    private static final String UIA_NAME = "upgradeStreams";
    private UserActionLogging$Streams mCurrentStreams;
    private UserActionLogging$Streams mEndStreams;
    
    public UpgradeStreamsEndedEvent(final DeviceUniqueId deviceUniqueId, final long n, final IClientLogging$ModalView clientLogging$ModalView, final UserActionLogging$CommandName userActionLogging$CommandName, final IClientLogging$CompletionReason clientLogging$CompletionReason, final UIError uiError, final UserActionLogging$Streams mCurrentStreams, final UserActionLogging$Streams mEndStreams) {
        super("upgradeStreams", deviceUniqueId, n, clientLogging$ModalView, userActionLogging$CommandName, clientLogging$CompletionReason, uiError);
        this.mCurrentStreams = mCurrentStreams;
        this.mEndStreams = mEndStreams;
    }
    
    public UpgradeStreamsEndedEvent(final JSONObject jsonObject) {
        super(jsonObject);
        this.mCurrentStreams = UserActionLogging$Streams.valueOf(JsonUtils.getString(jsonObject, "currentStreams", null));
        this.mEndStreams = UserActionLogging$Streams.valueOf(JsonUtils.getString(jsonObject, "endStreams", null));
    }
    
    @Override
    protected JSONObject getData() {
        final JSONObject data = super.getData();
        if (this.mCurrentStreams != null) {
            data.put("currentStreams", (Object)this.mCurrentStreams.getValue());
        }
        if (this.mEndStreams != null) {
            data.put("endStreams", (Object)this.mEndStreams.getValue());
        }
        return data;
    }
    
    @Override
    public boolean isMemberEvent() {
        return true;
    }
}
