// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.core;

import com.facebook.cache.disk.FileCache;
import com.facebook.cache.disk.DiskStorageCache$Params;
import com.facebook.cache.disk.DiskStorageCache;
import com.facebook.cache.disk.DiskStorage;
import com.facebook.cache.disk.DiskCacheConfig;

public class DiskStorageCacheFactory implements FileCacheFactory
{
    private DiskStorageFactory mDiskStorageFactory;
    
    public DiskStorageCacheFactory(final DiskStorageFactory mDiskStorageFactory) {
        this.mDiskStorageFactory = mDiskStorageFactory;
    }
    
    public static DiskStorageCache buildDiskStorageCache(final DiskCacheConfig diskCacheConfig, final DiskStorage diskStorage) {
        return new DiskStorageCache(diskStorage, diskCacheConfig.getEntryEvictionComparatorSupplier(), new DiskStorageCache$Params(diskCacheConfig.getMinimumSizeLimit(), diskCacheConfig.getLowDiskSpaceSizeLimit(), diskCacheConfig.getDefaultSizeLimit()), diskCacheConfig.getCacheEventListener(), diskCacheConfig.getCacheErrorLogger(), diskCacheConfig.getDiskTrimmableRegistry());
    }
    
    @Override
    public FileCache get(final DiskCacheConfig diskCacheConfig) {
        return buildDiskStorageCache(diskCacheConfig, this.mDiskStorageFactory.get(diskCacheConfig));
    }
}
