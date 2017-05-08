// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.apm.model;

import com.netflix.mediaclient.util.JsonUtils;
import org.json.JSONObject;
import com.netflix.mediaclient.service.logging.client.model.SessionStartedEvent;

public class SharedContextSessionStartedEvent extends SessionStartedEvent
{
    private static final String ROLE = "role";
    private static final String ROLE_VALUE = "initiator";
    private static final String TRIGGER = "trigger";
    private static final String TRIGGER_VALUE = "signup";
    private static final String UUID = "uuid";
    private String mUuid;
    
    public SharedContextSessionStartedEvent(final String mUuid) {
        super("sharedContext");
        this.mUuid = mUuid;
    }
    
    public SharedContextSessionStartedEvent(JSONObject jsonObject) {
        super(jsonObject);
        jsonObject = JsonUtils.getJSONObject(jsonObject, "data", (JSONObject)null);
        if (jsonObject != null) {
            this.mUuid = JsonUtils.getString(jsonObject, "uuid", (String)null);
        }
    }
    
    @Override
    protected JSONObject getData() {
        JSONObject data;
        if ((data = super.getData()) == null) {
            data = new JSONObject();
        }
        data.put("uuid", (Object)this.mUuid);
        data.put("role", (Object)"initiator");
        data.put("trigger", (Object)"signup");
        return data;
    }
}
