// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.apm.model;

import com.netflix.mediaclient.util.JsonUtils;
import org.json.JSONObject;
import com.netflix.mediaclient.service.logging.client.model.SessionStartedEvent;

public final class AppSessionStartedEvent extends SessionStartedEvent
{
    private static final String APP_SESSION_NAME = "appSession";
    public static final String LAST_SHUTDOWN_GRACEFUL = "lastShutdownGraceful";
    private boolean lastShutdownGraceful;
    
    public AppSessionStartedEvent() {
        super("appSession");
        this.lastShutdownGraceful = true;
    }
    
    public AppSessionStartedEvent(JSONObject jsonObject) {
        super(jsonObject);
        this.lastShutdownGraceful = true;
        jsonObject = JsonUtils.getJSONObject(jsonObject, "data", (JSONObject)null);
        if (jsonObject != null) {
            this.lastShutdownGraceful = JsonUtils.getBoolean(jsonObject, "lastShutdownGraceful", true);
        }
    }
    
    @Override
    protected JSONObject getData() {
        JSONObject data;
        if ((data = super.getData()) == null) {
            data = new JSONObject();
        }
        data.put("lastShutdownGraceful", this.lastShutdownGraceful);
        return data;
    }
    
    public boolean isLastShutdownGraceful() {
        return this.lastShutdownGraceful;
    }
    
    public void setLastShutdownGraceful(final boolean lastShutdownGraceful) {
        this.lastShutdownGraceful = lastShutdownGraceful;
    }
}
