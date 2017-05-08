// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.falkor.cache;

import java.util.Date;
import io.realm.RealmModel;

interface FalkorCache$FalkorCacheable extends RealmModel
{
    Date getExpiry();
    
    long getLastModifiedTime();
    
    String getPath();
    
    String getPayload();
    
    boolean getSentinel();
    
    boolean getVolatile();
    
    void setExpiry(final Date p0);
    
    void setLastModifiedTime(final long p0);
    
    void setPath(final String p0);
    
    void setPayload(final String p0);
    
    void setSentinel(final boolean p0);
    
    void setVolatile(final boolean p0);
}
