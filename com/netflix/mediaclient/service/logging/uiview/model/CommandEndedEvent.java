// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.uiview.model;

import org.json.JSONObject;
import com.netflix.mediaclient.service.logging.client.model.DeviceUniqueId;
import com.netflix.mediaclient.servicemgr.UIViewLogging$UIViewCommandName;

public class CommandEndedEvent extends BaseUIViewSessionEndedEvent
{
    private static final Integer DEF_VALUE_CONFIDENCE;
    private static final String DEF_VALUE_IS_HOT_BOTTON = "false";
    public static final String KEY_CONFIDENCE = "confidence";
    public static final String KEY_INPUT_METHOD = "inputMethod";
    public static final String KEY_INPUT_VALUE = "inputValue";
    public static final String KEY_IS_HOT_BUTTON = "isHotKey";
    public static final String KEY_NAME = "name";
    public static final String UIVIEW_SESSION_NAME = "command";
    private UIViewLogging$UIViewCommandName mCommandName;
    
    static {
        DEF_VALUE_CONFIDENCE = 1;
    }
    
    public CommandEndedEvent(final DeviceUniqueId deviceUniqueId, final long n, final UIViewLogging$UIViewCommandName mCommandName) {
        super("command", deviceUniqueId, n);
        this.mCommandName = mCommandName;
    }
    
    @Override
    protected JSONObject getData() {
        JSONObject data;
        if ((data = super.getData()) == null) {
            data = new JSONObject();
        }
        if (this.mCommandName != null) {
            data.put("name", (Object)this.mCommandName.name());
            data.put("inputMethod", (Object)CommandEndedEvent$InputMethod.gesture.name());
            data.put("inputValue", (Object)CommandEndedEvent$InputValue.touch.name());
            data.put("isHotKey", (Object)"false");
            data.put("confidence", (Object)CommandEndedEvent.DEF_VALUE_CONFIDENCE);
        }
        return data;
    }
}
