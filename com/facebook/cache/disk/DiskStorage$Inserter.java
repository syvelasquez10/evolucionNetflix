// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.cache.disk;

import com.facebook.cache.common.WriterCallback;
import com.facebook.binaryresource.BinaryResource;

public interface DiskStorage$Inserter
{
    boolean cleanUp();
    
    BinaryResource commit(final Object p0);
    
    void writeData(final WriterCallback p0, final Object p1);
}
