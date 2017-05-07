// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.browse.cache;

import java.util.Set;

public interface BrowseCache
{
    void flush();
    
    Object get(final String p0);
    
    Set<?> getKeySet();
    
    Object put(final String p0, final Object p1);
    
    Object remove(final String p0);
}
