// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.cache.disk;

import com.facebook.cache.common.WriterCallback;
import com.facebook.binaryresource.BinaryResource;
import com.facebook.cache.common.CacheKey;
import com.facebook.common.disk.DiskTrimmable;

public interface FileCache extends DiskTrimmable
{
    void clearAll();
    
    BinaryResource getResource(final CacheKey p0);
    
    boolean hasKey(final CacheKey p0);
    
    boolean hasKeySync(final CacheKey p0);
    
    BinaryResource insert(final CacheKey p0, final WriterCallback p1);
}
