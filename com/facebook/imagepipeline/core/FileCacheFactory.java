// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.core;

import com.facebook.cache.disk.FileCache;
import com.facebook.cache.disk.DiskCacheConfig;

public interface FileCacheFactory
{
    FileCache get(final DiskCacheConfig p0);
}
