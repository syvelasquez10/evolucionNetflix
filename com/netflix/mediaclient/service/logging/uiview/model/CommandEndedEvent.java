// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.uiview.model;

import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.service.logging.client.model.DeviceUniqueId;
import org.json.JSONObject;
import com.netflix.mediaclient.servicemgr.UIViewLogging$UIViewCommandName;

public class CommandEndedEvent extends BaseUIViewSessionEndedEvent
{
    private static final Integer DEF_VALUE_CONFIDENCE;
    private static final String DEF_VALUE_IS_HOT_BOTTON = "false";
    public static final String KEY_CONFIDENCE = "confidence";
    public static final String KEY_INPUT_METHOD = "inputMethod";
    public static final String KEY_INPUT_VALUE = "inputValue";
    public static final String KEY_IS_HOT_BUTTON = "isHotKey";
    public static final String KEY_MODEL = "model";
    public static final String KEY_NAME = "name";
    public static final String UIVIEW_SESSION_NAME = "command";
    private UIViewLogging$UIViewCommandName mCommandName;
    private CommandEndedEvent$InputMethod mInputMethod;
    private CommandEndedEvent$InputValue mInputValue;
    private JSONObject mModel;
    private String mUrl;
    
    static {
        DEF_VALUE_CONFIDENCE = 1;
    }
    
    public CommandEndedEvent(final DeviceUniqueId deviceUniqueId, final long n, final UIViewLogging$UIViewCommandName mCommandName, final String mUrl) {
        super("command", deviceUniqueId, n);
        this.mCommandName = mCommandName;
        this.mUrl = mUrl;
    }
    
    @Override
    protected JSONObject getData() {
        JSONObject data;
        if ((data = super.getData()) == null) {
            data = new JSONObject();
        }
        if (this.mCommandName != null) {
            data.put("name", (Object)this.mCommandName.name());
            if (StringUtils.isNotEmpty(this.mUrl)) {
                data.put("inputMethod", (Object)CommandEndedEvent$InputMethod.url.name());
                data.put("inputValue", (Object)this.mUrl);
            }
            else {
                if (this.mInputMethod == null) {
                    this.mInputMethod = CommandEndedEvent$InputMethod.gesture;
                }
                data.put("inputMethod", (Object)this.mInputMethod.name());
                if (this.mInputValue == null) {
                    this.mInputValue = CommandEndedEvent$InputValue.touch;
                }
                data.put("inputValue", (Object)this.mInputValue.name());
            }
            data.put("isHotKey", (Object)"false");
            data.put("confidence", (Object)CommandEndedEvent.DEF_VALUE_CONFIDENCE);
            if (this.mModel != null) {
                data.put("model", (Object)this.mModel);
            }
        }
        return data;
    }
    
    public void setInputMethod(final CommandEndedEvent$InputMethod mInputMethod) {
        this.mInputMethod = mInputMethod;
    }
    
    public void setInputValue(final CommandEndedEvent$InputValue mInputValue) {
        this.mInputValue = mInputValue;
    }
    
    public void setModel(final JSONObject mModel) {
        this.mModel = mModel;
    }
}
