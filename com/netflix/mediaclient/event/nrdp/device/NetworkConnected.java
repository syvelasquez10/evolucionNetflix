// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.event.nrdp.device;

import org.json.JSONException;
import org.json.JSONObject;

public class NetworkConnected extends BaseDeviceEvent
{
    public static final String TYPE = "networkconnected";
    
    public NetworkConnected() {
        super("networkconnected");
    }
    
    @Override
    public JSONObject getData() throws JSONException {
        final JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", (Object)this.getType());
        return jsonObject;
    }
}
