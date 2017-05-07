// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.event.service;

import org.json.JSONException;
import org.json.JSONObject;

public class AuthorizationCanceled extends BaseServiceEvent
{
    public static final String TYPE = "nrdp-service-authorize-didcancelauthorize";
    protected Service service;
    
    public AuthorizationCanceled(final Service service) {
        super("nrdp-service-authorize-didcancelauthorize");
        if (service == null) {
            throw new IllegalArgumentException("Service is null!");
        }
        this.service = service;
    }
    
    @Override
    public JSONObject getData() throws JSONException {
        final JSONObject jsonObject = new JSONObject();
        jsonObject.put("service", (Object)this.service.getName());
        jsonObject.put("nrdp-service-authorize-didcancelauthorize", (Object)this.getName());
        return jsonObject;
    }
}
