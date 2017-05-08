// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.cache.disk;

import com.facebook.binaryresource.BinaryResource;
import java.util.Collection;

public interface DiskStorage
{
    void clearAll();
    
    boolean contains(final String p0, final Object p1);
    
    Collection<DiskStorage$Entry> getEntries();
    
    BinaryResource getResource(final String p0, final Object p1);
    
    DiskStorage$Inserter insert(final String p0, final Object p1);
    
    void purgeUnexpectedResources();
    
    long remove(final DiskStorage$Entry p0);
}
