// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.client.model;

import com.netflix.mediaclient.util.JsonUtils;
import org.json.JSONObject;

public abstract class SessionEndedEvent extends SessionEvent
{
    public static final String DURATION = "duration";
    private static final String EXT = ".ended";
    protected long duration;
    
    public SessionEndedEvent(final SessionStartedEvent sessionStartedEvent, final long duration) {
        super(sessionStartedEvent.getSessionName());
        this.type = EventType.sessionEnded;
        this.name = this.sessionName + ".ended";
        this.sessionId = sessionStartedEvent.getSessionId();
        this.duration = duration;
    }
    
    public SessionEndedEvent(final String s) {
        super(s);
        this.type = EventType.sessionEnded;
        this.name = s + ".ended";
        this.sessionId = new DeviceUniqueId();
        this.duration = 0L;
    }
    
    public SessionEndedEvent(final String s, final DeviceUniqueId sessionId, final long duration) {
        super(s);
        this.type = EventType.sessionEnded;
        this.name = s + ".ended";
        this.sessionId = sessionId;
        this.duration = duration;
    }
    
    public SessionEndedEvent(final JSONObject jsonObject) {
        super(jsonObject);
        this.duration = JsonUtils.getLong(jsonObject, "duration", 0L);
    }
    
    public long getDuration() {
        return this.duration;
    }
    
    @Override
    public JSONObject toJSONObject() {
        final JSONObject jsonObject = super.toJSONObject();
        jsonObject.put("duration", this.duration);
        return jsonObject;
    }
}
