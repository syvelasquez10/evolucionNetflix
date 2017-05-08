// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.apm.model;

import com.netflix.mediaclient.util.JsonUtils;
import org.json.JSONObject;
import com.netflix.mediaclient.service.logging.client.model.DeviceUniqueId;
import com.netflix.mediaclient.service.logging.client.model.SessionStartedEvent;
import com.netflix.mediaclient.servicemgr.ApplicationPerformanceMetricsLogging$EndReason;
import com.netflix.mediaclient.service.logging.client.model.SessionEndedEvent;

public final class UserSessionEndedEvent extends SessionEndedEvent
{
    public static final String END_REASON = "endReason";
    public static final String LAST_USER_ACTIVITY_TIME = "lastUserActivityTime";
    public static final String USER_SESSION_DURATION = "userSessionDuration";
    private static final String USER_SESSION_NAME = "userSession";
    private ApplicationPerformanceMetricsLogging$EndReason endReason;
    private long lastUserActivityTime;
    private long userSessionDuration;
    
    public UserSessionEndedEvent(final UserSessionStartedEvent userSessionStartedEvent, final long userSessionDuration, final ApplicationPerformanceMetricsLogging$EndReason endReason, final long lastUserActivityTime) {
        super(userSessionStartedEvent, userSessionDuration);
        this.userSessionDuration = userSessionDuration;
        this.lastUserActivityTime = lastUserActivityTime;
        if (endReason == null) {
            throw new IllegalArgumentException("End reason is null!");
        }
        this.endReason = endReason;
    }
    
    public UserSessionEndedEvent(final DeviceUniqueId deviceUniqueId, final long userSessionDuration, final ApplicationPerformanceMetricsLogging$EndReason endReason, final long lastUserActivityTime) {
        super("userSession", deviceUniqueId, userSessionDuration);
        this.userSessionDuration = userSessionDuration;
        this.lastUserActivityTime = lastUserActivityTime;
        if (endReason == null) {
            throw new IllegalArgumentException("End reason is null!");
        }
        this.endReason = endReason;
    }
    
    public UserSessionEndedEvent(JSONObject jsonObject) {
        super(jsonObject);
        jsonObject = JsonUtils.getJSONObject(jsonObject, "data", (JSONObject)null);
        if (jsonObject != null) {
            final String string = JsonUtils.getString(jsonObject, "endReason", (String)null);
            if (string != null) {
                this.endReason = Enum.valueOf(ApplicationPerformanceMetricsLogging$EndReason.class, string);
            }
            this.lastUserActivityTime = JsonUtils.getLong(jsonObject, "lastUserActivityTime", 0L);
            this.userSessionDuration = JsonUtils.getLong(jsonObject, "userSessionDuration", 0L);
        }
    }
    
    @Override
    protected JSONObject getData() {
        JSONObject data;
        if ((data = super.getData()) == null) {
            data = new JSONObject();
        }
        data.put("lastUserActivityTime", this.lastUserActivityTime);
        data.put("userSessionDuration", this.userSessionDuration);
        return data;
    }
    
    public ApplicationPerformanceMetricsLogging$EndReason getEndReason() {
        return this.endReason;
    }
    
    public long getLastUserActivityTime() {
        return this.lastUserActivityTime;
    }
    
    public long getUserSessionDuration() {
        return this.userSessionDuration;
    }
}
