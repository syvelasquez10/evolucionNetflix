// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.event.nrdp.device;

import org.json.JSONException;
import org.json.JSONObject;

public class NetworkConnectionLost extends BaseDeviceEvent
{
    public static final String TYPE = "networkconnectionlost";
    
    public NetworkConnectionLost() {
        super("networkconnectionlost");
    }
    
    @Override
    public JSONObject getData() throws JSONException {
        final JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", (Object)this.getType());
        return jsonObject;
    }
}
