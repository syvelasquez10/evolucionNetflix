// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.memory;

import com.facebook.common.references.ResourceReleaser;
import com.facebook.common.memory.MemoryTrimmable;

public interface Pool<V> extends MemoryTrimmable, ResourceReleaser<V>
{
    V get(final int p0);
    
    void release(final V p0);
}
