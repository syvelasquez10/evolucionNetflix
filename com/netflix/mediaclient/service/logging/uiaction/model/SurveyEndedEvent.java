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

public class SurveyEndedEvent extends BaseUIActionSessionEndedEvent
{
    public static final String TYPE = "surveyType";
    public static final String UIA_SESSION_NAME = "survey";
    private String mType;
    
    public SurveyEndedEvent(final DeviceUniqueId deviceUniqueId, final long n, final IClientLogging$ModalView clientLogging$ModalView, final UserActionLogging$CommandName userActionLogging$CommandName, final IClientLogging$CompletionReason clientLogging$CompletionReason, final UIError uiError, final String mType) {
        super("survey", deviceUniqueId, n, clientLogging$ModalView, userActionLogging$CommandName, clientLogging$CompletionReason, uiError);
        this.mType = mType;
    }
    
    public SurveyEndedEvent(final JSONObject jsonObject) {
        super(jsonObject);
        this.mType = JsonUtils.getString(jsonObject, "surveyType", "");
    }
    
    @Override
    protected JSONObject getData() {
        final JSONObject data = super.getData();
        data.put("surveyType", (Object)this.mType);
        return data;
    }
    
    @Override
    public boolean isMemberEvent() {
        return true;
    }
}
