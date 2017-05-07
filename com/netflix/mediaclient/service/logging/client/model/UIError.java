// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.client.model;

import com.netflix.mediaclient.util.JsonUtils;
import org.json.JSONObject;
import java.util.List;
import com.google.gson.annotations.Since;
import com.google.gson.annotations.SerializedName;

public class UIError extends Error
{
    public static final String DISPLAYED_MESSAGE = "displayedMessage";
    public static final String UI_ACTION = "uiAction";
    @SerializedName("displayedMessage")
    @Since(1.0)
    private String displayedMessage;
    @SerializedName("uiAction")
    @Since(1.0)
    private ActionOnUIError uiAction;
    
    public UIError() {
    }
    
    public UIError(final Error error, final ActionOnUIError uiAction, final String displayedMessage) {
        super(error.getRootCause(), error.getDeepError());
        this.displayedMessage = displayedMessage;
        this.uiAction = uiAction;
    }
    
    public UIError(final RootCause rootCause, final ActionOnUIError uiAction, final String displayedMessage) {
        this.displayedMessage = displayedMessage;
        this.uiAction = uiAction;
        this.rootCause = rootCause;
    }
    
    public UIError(final RootCause rootCause, final ActionOnUIError uiAction, final String displayedMessage, final List<DeepErrorElement> list) {
        super(rootCause, list);
        this.displayedMessage = displayedMessage;
        this.uiAction = uiAction;
    }
    
    public UIError(final JSONObject jsonObject) {
        super(jsonObject);
        this.displayedMessage = JsonUtils.getString(jsonObject, "displayedMessage", null);
        final String string = JsonUtils.getString(jsonObject, "uiAction", null);
        if (string != null) {
            this.uiAction = ActionOnUIError.valueOf(string);
        }
    }
    
    public static UIError createInstance(final String s) {
        if (s == null) {
            return null;
        }
        return new UIError(new JSONObject(s));
    }
    
    public static UIError createInstance(final JSONObject jsonObject) {
        if (jsonObject == null) {
            return null;
        }
        return new UIError(jsonObject);
    }
    
    public String getDisplayedMessage() {
        return this.displayedMessage;
    }
    
    public ActionOnUIError getUiAction() {
        return this.uiAction;
    }
    
    @Override
    public JSONObject toJSONObject() {
        final JSONObject jsonObject = super.toJSONObject();
        if (this.uiAction != null) {
            jsonObject.put("uiAction", (Object)this.uiAction.name());
        }
        if (this.displayedMessage != null) {
            jsonObject.put("displayedMessage", (Object)this.displayedMessage);
        }
        return jsonObject;
    }
    
    @Override
    public String toString() {
        return "UIError [uiAction=" + this.uiAction + ", displayedMessage=" + this.displayedMessage + "]";
    }
}
