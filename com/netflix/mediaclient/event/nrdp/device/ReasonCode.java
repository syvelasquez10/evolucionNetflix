// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.event.nrdp.device;

import org.json.JSONObject;

public class ReasonCode extends BaseDeviceEvent
{
    public static final String TYPE = "nrdp-webapi-nccpreasoncode";
    
    public ReasonCode(final JSONObject json) {
        super("nrdp-webapi-nccpreasoncode");
        this.json = json;
        try {
            json.put("type", (Object)this.getType());
        }
        catch (Exception ex) {}
    }
}
