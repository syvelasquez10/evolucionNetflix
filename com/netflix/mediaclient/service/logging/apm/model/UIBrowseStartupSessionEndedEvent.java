// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.apm.model;

import java.util.Map;
import java.util.HashMap;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.ui.experience.BrowseExperience;
import com.netflix.mediaclient.util.JsonUtils;
import org.json.JSONObject;
import com.netflix.mediaclient.service.logging.client.model.DeviceUniqueId;
import com.netflix.mediaclient.service.logging.client.model.UIError;
import com.netflix.mediaclient.service.logging.client.model.SessionEndedEvent;

public final class UIBrowseStartupSessionEndedEvent extends SessionEndedEvent
{
    public static final String ERROR = "error";
    public static final String SUCCESS = "success";
    private static final String TAG = "UIBrowseStartupSessionEndedEvent";
    public static final String TIME_TO_BROWSE_INTERACTIVE = "timeToBrowseInteractive";
    private static final String UI_BROWSE_STARTUP_SESSION_NAME = "uiBrowseStartup";
    private UIError error;
    private boolean success;
    private long timeToBrowseInteractive;
    
    public UIBrowseStartupSessionEndedEvent(final long n, final long timeToBrowseInteractive) {
        super("uiBrowseStartup", new DeviceUniqueId(), n);
        this.success = true;
        this.timeToBrowseInteractive = timeToBrowseInteractive;
    }
    
    public UIBrowseStartupSessionEndedEvent(JSONObject jsonObject) {
        super(jsonObject);
        this.success = true;
        jsonObject = JsonUtils.getJSONObject(jsonObject, "data", null);
        if (jsonObject != null) {
            this.success = JsonUtils.getBoolean(jsonObject, "success", true);
            this.error = UIError.createInstance(JsonUtils.getJSONObject(jsonObject, "error", null));
            this.timeToBrowseInteractive = JsonUtils.getLong(jsonObject, "timeToBrowseInteractive", 0L);
        }
    }
    
    @Override
    protected JSONObject getCustomData() {
        final String value = String.valueOf(BrowseExperience.get());
        if (Log.isLoggable()) {
            Log.v("UIBrowseStartupSessionEndedEvent", "getCustomData() - adding current browse experience: " + value);
        }
        final HashMap<String, String> hashMap = new HashMap<String, String>(1);
        hashMap.put("browseExperience", value);
        return new JSONObject((Map)hashMap);
    }
    
    @Override
    protected JSONObject getData() {
        JSONObject data;
        if ((data = super.getData()) == null) {
            data = new JSONObject();
        }
        data.put("success", this.success);
        data.put("timeToBrowseInteractive", this.timeToBrowseInteractive);
        if (this.error != null) {
            data.put("error", (Object)this.error.toJSONObject());
        }
        return data;
    }
    
    public UIError getError() {
        return this.error;
    }
    
    public long getTimeToBrowseInteractive() {
        return this.timeToBrowseInteractive;
    }
    
    public boolean isSuccess() {
        return this.success;
    }
    
    public void setError(final UIError error) {
        this.error = error;
    }
    
    public void setSuccess(final boolean success) {
        this.success = success;
    }
    
    public void setTimeToBrowseInteractive(final long timeToBrowseInteractive) {
        this.timeToBrowseInteractive = timeToBrowseInteractive;
    }
}
