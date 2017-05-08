// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.uiaction.model;

import com.netflix.mediaclient.util.JsonUtils;
import org.json.JSONObject;
import com.netflix.mediaclient.service.logging.client.model.DeviceUniqueId;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.service.logging.client.model.UIError;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import com.netflix.mediaclient.servicemgr.UserActionLogging$CommandName;
import com.netflix.mediaclient.service.logging.client.model.SessionEndedEvent;

public abstract class BaseUIActionSessionEndedEvent extends SessionEndedEvent
{
    public static final String COMMAND_NAME = "commandName";
    public static final String ERROR = "error";
    public static final String MODAL_VIEW = "modalView";
    public static final String REASON = "reason";
    public static final String STARTED = "started";
    protected UserActionLogging$CommandName mAction;
    protected IClientLogging$CompletionReason mCompletionReason;
    protected UIError mUIError;
    protected IClientLogging$ModalView mView;
    
    public BaseUIActionSessionEndedEvent(final String s, final DeviceUniqueId deviceUniqueId, final long n, final IClientLogging$ModalView mView, final UserActionLogging$CommandName mAction, final IClientLogging$CompletionReason mCompletionReason, final UIError muiError) {
        super(s, deviceUniqueId, n);
        this.mView = mView;
        this.mAction = mAction;
        this.mCompletionReason = mCompletionReason;
        this.mUIError = muiError;
    }
    
    public BaseUIActionSessionEndedEvent(JSONObject jsonObject) {
        super(jsonObject);
        jsonObject = JsonUtils.getJSONObject(jsonObject, "data", (JSONObject)null);
        if (jsonObject != null) {
            this.mCompletionReason = IClientLogging$CompletionReason.valueOf(JsonUtils.getString(jsonObject, "reason", (String)null));
            jsonObject = JsonUtils.getJSONObject(jsonObject, "started", (JSONObject)null);
            if (jsonObject != null) {
                this.mView = IClientLogging$ModalView.valueOf(JsonUtils.getString(jsonObject, "modalView", (String)null));
                this.mAction = UserActionLogging$CommandName.valueOf(JsonUtils.getString(jsonObject, "commandName", (String)null));
                jsonObject = JsonUtils.getJSONObject(jsonObject, "error", (JSONObject)null);
                if (jsonObject != null) {
                    this.mUIError = new UIError(jsonObject);
                }
            }
        }
    }
    
    public UserActionLogging$CommandName getAction() {
        return this.mAction;
    }
    
    public IClientLogging$CompletionReason getCompletionReason() {
        return this.mCompletionReason;
    }
    
    @Override
    protected JSONObject getData() {
        JSONObject data;
        if ((data = super.getData()) == null) {
            data = new JSONObject();
        }
        final JSONObject jsonObject = new JSONObject();
        data.put("started", (Object)jsonObject);
        if (this.mAction != null) {
            jsonObject.put("commandName", (Object)this.mAction.name());
        }
        if (this.mView != null) {
            jsonObject.put("modalView", (Object)this.mView.name());
        }
        if (this.mCompletionReason != null) {
            data.put("reason", (Object)this.mCompletionReason.name());
        }
        if (this.mUIError != null) {
            data.put("error", (Object)this.mUIError.toJSONObject());
        }
        return data;
    }
    
    public UIError getUIError() {
        return this.mUIError;
    }
    
    public IClientLogging$ModalView getView() {
        return this.mView;
    }
    
    public abstract boolean isMemberEvent();
}
