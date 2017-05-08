// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.core;

import com.facebook.cache.disk.DiskStorage;
import com.facebook.cache.disk.DiskCacheConfig;

public interface DiskStorageFactory
{
    DiskStorage get(final DiskCacheConfig p0);
}
