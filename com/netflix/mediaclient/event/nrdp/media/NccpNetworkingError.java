// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.event.nrdp.media;

import com.netflix.mediaclient.event.nrdp.BaseNccpEvent;
import org.json.JSONException;
import org.json.JSONObject;

public class NccpNetworkingError extends NccpError
{
    private String endpoint;
    private String stack;
    
    NccpNetworkingError(final String endpoint, final String stack, final String origin, final String result, final String transaction) {
        this.endpoint = endpoint;
        this.stack = stack;
        this.origin = origin;
        this.result = result;
        this.transaction = transaction;
    }
    
    public NccpNetworkingError(final JSONObject jsonObject) throws JSONException {
        super(jsonObject);
    }
    
    public String getEndpoint() {
        return this.endpoint;
    }
    
    public String getStack() {
        return this.stack;
    }
    
    @Override
    protected void populate(final JSONObject jsonObject) throws JSONException {
        this.endpoint = BaseNccpEvent.getString(jsonObject, "endpoint", null);
        this.stack = BaseNccpEvent.getString(jsonObject, "stack", null);
        this.origin = BaseNccpEvent.getString(jsonObject, "origin", null);
        this.result = BaseNccpEvent.getString(jsonObject, "result", null);
        this.transaction = BaseNccpEvent.getString(jsonObject, "transaction", null);
    }
    
    @Override
    public String toString() {
        return "NccpNetworkingError [endpoint=" + this.endpoint + ", stack=" + this.stack + ", origin=" + this.origin + ", result=" + this.result + ", transaction=" + this.transaction + "]";
    }
}
