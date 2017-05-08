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

public class SurveyQuestionEndedEvent extends BaseUIActionSessionEndedEvent
{
    public static final String QUESTION = "question";
    public static final String RESPONSE = "response";
    public static final String UIA_SESSION_NAME = "surveyQuestion";
    private String mQuestionId;
    private String mResponse;
    
    public SurveyQuestionEndedEvent(final DeviceUniqueId deviceUniqueId, final long n, final IClientLogging$ModalView clientLogging$ModalView, final UserActionLogging$CommandName userActionLogging$CommandName, final IClientLogging$CompletionReason clientLogging$CompletionReason, final UIError uiError, final String mQuestionId, final String mResponse) {
        super("surveyQuestion", deviceUniqueId, n, clientLogging$ModalView, userActionLogging$CommandName, clientLogging$CompletionReason, uiError);
        this.mQuestionId = mQuestionId;
        this.mResponse = mResponse;
    }
    
    public SurveyQuestionEndedEvent(final JSONObject jsonObject) {
        super(jsonObject);
        this.mQuestionId = JsonUtils.getString(jsonObject, "question", "");
        this.mResponse = JsonUtils.getString(jsonObject, "response", "");
    }
    
    @Override
    protected JSONObject getData() {
        final JSONObject data = super.getData();
        data.put("question", (Object)this.mQuestionId);
        data.put("response", (Object)this.mResponse);
        return data;
    }
    
    @Override
    public boolean isMemberEvent() {
        return true;
    }
}
