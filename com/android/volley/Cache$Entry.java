// 
// Decompiled by Procyon v0.5.30
// 

package com.android.volley;

import java.util.Collections;
import java.util.Map;

public class Cache$Entry
{
    public byte[] data;
    public String etag;
    public Map<String, String> responseHeaders;
    public long serverDate;
    public long softTtl;
    public long ttl;
    
    public Cache$Entry() {
        this.responseHeaders = Collections.emptyMap();
    }
    
    public boolean isExpired() {
        return this.ttl < System.currentTimeMillis();
    }
    
    public boolean refreshNeeded() {
        return this.softTtl < System.currentTimeMillis();
    }
}
