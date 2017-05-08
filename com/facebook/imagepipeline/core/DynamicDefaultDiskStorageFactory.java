// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.core;

import com.facebook.cache.disk.DynamicDefaultDiskStorage;
import com.facebook.cache.disk.DiskStorage;
import com.facebook.cache.disk.DiskCacheConfig;

public class DynamicDefaultDiskStorageFactory implements DiskStorageFactory
{
    @Override
    public DiskStorage get(final DiskCacheConfig diskCacheConfig) {
        return new DynamicDefaultDiskStorage(diskCacheConfig.getVersion(), diskCacheConfig.getBaseDirectoryPathSupplier(), diskCacheConfig.getBaseDirectoryName(), diskCacheConfig.getCacheErrorLogger());
    }
}
