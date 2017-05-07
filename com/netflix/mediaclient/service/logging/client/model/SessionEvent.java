// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.client.model;

import org.json.JSONException;
import com.netflix.mediaclient.util.JsonUtils;
import org.json.JSONObject;
import com.netflix.mediaclient.util.StringUtils;
import com.google.gson.annotations.Since;
import com.google.gson.annotations.SerializedName;

public abstract class SessionEvent extends Event
{
    public static final String CATEGORY_VALUE = "uiQOE";
    public static final String SESSION_ID = "sessionId";
    public static final String SESSION_NAME = "sessionName";
    @SerializedName("sessionId")
    @Since(1.0)
    protected DeviceUniqueId sessionId;
    @SerializedName("sessionName")
    @Since(1.0)
    protected transient String sessionName;
    
    public SessionEvent(final String sessionName) {
        if (StringUtils.isEmpty(sessionName)) {
            throw new IllegalArgumentException("Seasssion name can not be null!");
        }
        this.category = "uiQOE";
        this.sessionName = sessionName;
    }
    
    public SessionEvent(final JSONObject jsonObject) {
        super(jsonObject);
        this.sessionId = new DeviceUniqueId(JsonUtils.getLong(jsonObject, "sessionId", 0L));
        this.sessionName = JsonUtils.getString(jsonObject, "sessionName", null);
    }
    
    public DeviceUniqueId getSessionId() {
        return this.sessionId;
    }
    
    public String getSessionName() {
        return this.sessionName;
    }
    
    public void setSessionId(final DeviceUniqueId sessionId) {
        this.sessionId = sessionId;
    }
    
    @Override
    public JSONObject toJSONObject() {
        final JSONObject jsonObject = super.toJSONObject();
        if (this.sessionName != null) {
            jsonObject.put("sessionName", (Object)this.sessionName);
        }
        if (this.sessionId != null) {
            jsonObject.put("sessionId", this.sessionId.getValue());
        }
        return jsonObject;
    }
    
    @Override
    public String toString() {
        Object customData = null;
        try {
            customData = this.getCustomData();
            return "SessionEvent [sessionName=" + this.sessionName + ", sessionId=" + this.sessionId + ", type=" + this.type + ", category=" + this.category + ", name=" + this.name + ", activeSessions=" + this.activeSessions + ", time=" + this.time + ", sequence=" + this.sequence + ", uptime=" + this.uptime + ", modalView=" + this.modalView + ", dataContext=" + this.dataContext + ", kids=" + this.kids + ", getCustomData()=" + customData + "]";
        }
        catch (JSONException ex) {
            return "SessionEvent [sessionName=" + this.sessionName + ", sessionId=" + this.sessionId + ", type=" + this.type + ", category=" + this.category + ", name=" + this.name + ", activeSessions=" + this.activeSessions + ", time=" + this.time + ", sequence=" + this.sequence + ", uptime=" + this.uptime + ", modalView=" + this.modalView + ", dataContext=" + this.dataContext + ", kids=" + this.kids + ", getCustomData()=" + customData + "]";
        }
    }
}
