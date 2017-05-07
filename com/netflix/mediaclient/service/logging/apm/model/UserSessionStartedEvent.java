// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.apm.model;

import org.json.JSONException;
import com.netflix.mediaclient.util.JsonUtils;
import org.json.JSONObject;
import com.netflix.mediaclient.servicemgr.ApplicationPerformanceMetricsLogging;
import com.netflix.mediaclient.service.logging.client.model.SessionStartedEvent;

public final class UserSessionStartedEvent extends SessionStartedEvent
{
    public static final String IDLE_TIME = "idleTime";
    public static final String TRIGGER = "trigger";
    private static final String USER_SESSION_NAME = "userSession";
    private long idleTime;
    private ApplicationPerformanceMetricsLogging.Trigger trigger;
    
    public UserSessionStartedEvent(final ApplicationPerformanceMetricsLogging.Trigger trigger, final long idleTime) {
        super("userSession");
        if (trigger == null) {
            throw new IllegalArgumentException("Trigger is null!");
        }
        this.trigger = trigger;
        this.idleTime = idleTime;
    }
    
    public UserSessionStartedEvent(JSONObject jsonObject) throws JSONException {
        super(jsonObject);
        jsonObject = JsonUtils.getJSONObject(jsonObject, "data", null);
        if (jsonObject != null) {
            final String string = JsonUtils.getString(jsonObject, "trigger", null);
            if (string != null) {
                this.trigger = Enum.valueOf(ApplicationPerformanceMetricsLogging.Trigger.class, string);
            }
            this.idleTime = JsonUtils.getLong(jsonObject, "idleTime", 0L);
        }
    }
    
    @Override
    protected JSONObject getData() throws JSONException {
        JSONObject data;
        if ((data = super.getData()) == null) {
            data = new JSONObject();
        }
        data.put("trigger", (Object)this.trigger.name());
        data.put("idleTime", this.idleTime);
        return data;
    }
    
    public long getIdleTime() {
        return this.idleTime;
    }
    
    public ApplicationPerformanceMetricsLogging.Trigger getTrigger() {
        return this.trigger;
    }
}
