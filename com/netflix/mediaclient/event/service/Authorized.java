// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.event.service;

import org.json.JSONObject;

public abstract class Authorized extends BaseServiceEvent
{
    private String accessToken;
    private Service service;
    
    public Authorized(final String s, final Service service, final String accessToken) {
        super(s);
        if (service == null) {
            throw new IllegalArgumentException("Service is null!");
        }
        if (accessToken == null) {
            throw new IllegalArgumentException("Access token is null!");
        }
        this.service = service;
        this.accessToken = accessToken;
    }
    
    @Override
    public JSONObject getData() {
        final JSONObject jsonObject = new JSONObject();
        final JSONObject jsonObject2 = new JSONObject();
        jsonObject2.put("service", (Object)this.service.getName());
        jsonObject2.put("access_token", (Object)this.accessToken);
        jsonObject2.put("perm_ver", this.getVersion());
        jsonObject.put("type", (Object)this.getName());
        jsonObject.put("parameters", (Object)jsonObject2);
        return jsonObject;
    }
    
    public abstract int getVersion();
}
