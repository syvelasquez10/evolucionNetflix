// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.client;

import com.netflix.mediaclient.service.logging.client.model.SessionKey;
import com.netflix.mediaclient.service.logging.client.model.DeviceUniqueId;

public abstract class BaseLoggingSession implements LoggingSession
{
    protected DeviceUniqueId mId;
    protected SessionKey mKey;
    protected long mStarted;
    
    public BaseLoggingSession() {
        this.mStarted = System.currentTimeMillis();
        this.mId = new DeviceUniqueId();
        this.mKey = new SessionKey(this.mId, this.getCategory(), this.getName());
    }
    
    public abstract String getCategory();
    
    public DeviceUniqueId getId() {
        return this.mId;
    }
    
    @Override
    public SessionKey getKey() {
        return this.mKey;
    }
    
    public long getStarted() {
        return this.mStarted;
    }
    
    public void setId(final long n) {
        this.mId = new DeviceUniqueId(n);
        this.mKey = new SessionKey(this.mId, this.getCategory(), this.getName());
    }
    
    public void setStarted(final long mStarted) {
        this.mStarted = mStarted;
    }
}
