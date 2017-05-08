// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.cache.disk;

public class DiskStorageCache$Params
{
    public final long mCacheSizeLimitMinimum;
    public final long mDefaultCacheSizeLimit;
    public final long mLowDiskSpaceCacheSizeLimit;
    
    public DiskStorageCache$Params(final long mCacheSizeLimitMinimum, final long mLowDiskSpaceCacheSizeLimit, final long mDefaultCacheSizeLimit) {
        this.mCacheSizeLimitMinimum = mCacheSizeLimitMinimum;
        this.mLowDiskSpaceCacheSizeLimit = mLowDiskSpaceCacheSizeLimit;
        this.mDefaultCacheSizeLimit = mDefaultCacheSizeLimit;
    }
}
