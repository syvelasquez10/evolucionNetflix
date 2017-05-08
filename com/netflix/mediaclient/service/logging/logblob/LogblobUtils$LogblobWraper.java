// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.logblob;

import com.netflix.mediaclient.servicemgr.Logblob$Severity;
import org.json.JSONObject;
import com.netflix.mediaclient.servicemgr.Logblob;

class LogblobUtils$LogblobWraper implements Logblob
{
    private long mClientEpoch;
    private JSONObject mPayload;
    
    public LogblobUtils$LogblobWraper(final JSONObject jsonObject) {
        if (jsonObject == null) {
            throw new IllegalStateException("Payload can not be empty");
        }
        this.mPayload = jsonObject.getJSONObject("clientJson");
        this.mClientEpoch = jsonObject.getLong("clientEpoch");
    }
    
    @Override
    public long getClientEpoch() {
        return this.mClientEpoch;
    }
    
    @Override
    public Logblob$Severity getSeverity() {
        throw new IllegalAccessError("Method not implemented");
    }
    
    @Override
    public String getType() {
        throw new IllegalAccessError("Method not implemented");
    }
    
    @Override
    public boolean isMandatory() {
        throw new IllegalAccessError("Method not implemented");
    }
    
    @Override
    public boolean shouldSendNow() {
        throw new IllegalAccessError("Method not implemented");
    }
    
    @Override
    public JSONObject toJson() {
        return this.mPayload;
    }
    
    @Override
    public String toJsonString() {
        return this.mPayload.toString();
    }
}
