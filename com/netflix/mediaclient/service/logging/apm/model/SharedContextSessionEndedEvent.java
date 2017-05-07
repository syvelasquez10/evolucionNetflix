// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.apm.model;

import com.netflix.mediaclient.util.JsonUtils;
import org.json.JSONObject;
import com.netflix.mediaclient.service.logging.client.model.DeviceUniqueId;
import com.netflix.mediaclient.service.logging.client.model.SessionEndedEvent;

public class SharedContextSessionEndedEvent extends SessionEndedEvent
{
    private static final String ROLE = "role";
    private static final String ROLE_VALUE = "initiator";
    private static final String UUID = "uuid";
    private String mUuid;
    
    public SharedContextSessionEndedEvent(final DeviceUniqueId deviceUniqueId, final long n, final String mUuid) {
        super("sharedContext", deviceUniqueId, n);
        this.mUuid = mUuid;
    }
    
    public SharedContextSessionEndedEvent(JSONObject jsonObject) {
        super(jsonObject);
        jsonObject = JsonUtils.getJSONObject(jsonObject, "data", null);
        if (jsonObject != null) {
            this.mUuid = JsonUtils.getString(jsonObject, "uuid", null);
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
        return data;
    }
}
