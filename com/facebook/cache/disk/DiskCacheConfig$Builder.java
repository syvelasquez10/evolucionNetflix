// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.cache.disk;

import com.facebook.common.internal.Preconditions;
import com.facebook.common.disk.DiskTrimmableRegistry;
import android.content.Context;
import com.facebook.cache.common.CacheEventListener;
import com.facebook.cache.common.CacheErrorLogger;
import java.io.File;
import com.facebook.common.internal.Supplier;

public class DiskCacheConfig$Builder
{
    private String mBaseDirectoryName;
    private Supplier<File> mBaseDirectoryPathSupplier;
    private CacheErrorLogger mCacheErrorLogger;
    private CacheEventListener mCacheEventListener;
    private final Context mContext;
    private DiskTrimmableRegistry mDiskTrimmableRegistry;
    private EntryEvictionComparatorSupplier mEntryEvictionComparatorSupplier;
    private long mMaxCacheSize;
    private long mMaxCacheSizeOnLowDiskSpace;
    private long mMaxCacheSizeOnVeryLowDiskSpace;
    private int mVersion;
    
    private DiskCacheConfig$Builder(final Context mContext) {
        this.mVersion = 1;
        this.mBaseDirectoryName = "image_cache";
        this.mMaxCacheSize = 41943040L;
        this.mMaxCacheSizeOnLowDiskSpace = 10485760L;
        this.mMaxCacheSizeOnVeryLowDiskSpace = 2097152L;
        this.mEntryEvictionComparatorSupplier = new DefaultEntryEvictionComparatorSupplier();
        this.mContext = mContext;
    }
    
    public DiskCacheConfig build() {
        Preconditions.checkState(this.mBaseDirectoryPathSupplier != null || this.mContext != null, "Either a non-null context or a base directory path or supplier must be provided.");
        if (this.mBaseDirectoryPathSupplier == null && this.mContext != null) {
            this.mBaseDirectoryPathSupplier = new DiskCacheConfig$Builder$1(this);
        }
        return new DiskCacheConfig(this, null);
    }
}
