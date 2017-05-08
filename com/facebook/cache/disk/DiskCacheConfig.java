// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.cache.disk;

import android.content.Context;
import com.facebook.common.disk.NoOpDiskTrimmableRegistry;
import com.facebook.cache.common.NoOpCacheEventListener;
import com.facebook.cache.common.NoOpCacheErrorLogger;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.disk.DiskTrimmableRegistry;
import com.facebook.cache.common.CacheEventListener;
import com.facebook.cache.common.CacheErrorLogger;
import java.io.File;
import com.facebook.common.internal.Supplier;

public class DiskCacheConfig
{
    private final String mBaseDirectoryName;
    private final Supplier<File> mBaseDirectoryPathSupplier;
    private final CacheErrorLogger mCacheErrorLogger;
    private final CacheEventListener mCacheEventListener;
    private final long mDefaultSizeLimit;
    private final DiskTrimmableRegistry mDiskTrimmableRegistry;
    private final EntryEvictionComparatorSupplier mEntryEvictionComparatorSupplier;
    private final long mLowDiskSpaceSizeLimit;
    private final long mMinimumSizeLimit;
    private final int mVersion;
    
    private DiskCacheConfig(final DiskCacheConfig$Builder diskCacheConfig$Builder) {
        this.mVersion = diskCacheConfig$Builder.mVersion;
        this.mBaseDirectoryName = Preconditions.checkNotNull(diskCacheConfig$Builder.mBaseDirectoryName);
        this.mBaseDirectoryPathSupplier = Preconditions.checkNotNull(diskCacheConfig$Builder.mBaseDirectoryPathSupplier);
        this.mDefaultSizeLimit = diskCacheConfig$Builder.mMaxCacheSize;
        this.mLowDiskSpaceSizeLimit = diskCacheConfig$Builder.mMaxCacheSizeOnLowDiskSpace;
        this.mMinimumSizeLimit = diskCacheConfig$Builder.mMaxCacheSizeOnVeryLowDiskSpace;
        this.mEntryEvictionComparatorSupplier = Preconditions.checkNotNull(diskCacheConfig$Builder.mEntryEvictionComparatorSupplier);
        CacheErrorLogger mCacheErrorLogger;
        if (diskCacheConfig$Builder.mCacheErrorLogger == null) {
            mCacheErrorLogger = NoOpCacheErrorLogger.getInstance();
        }
        else {
            mCacheErrorLogger = diskCacheConfig$Builder.mCacheErrorLogger;
        }
        this.mCacheErrorLogger = mCacheErrorLogger;
        CacheEventListener mCacheEventListener;
        if (diskCacheConfig$Builder.mCacheEventListener == null) {
            mCacheEventListener = NoOpCacheEventListener.getInstance();
        }
        else {
            mCacheEventListener = diskCacheConfig$Builder.mCacheEventListener;
        }
        this.mCacheEventListener = mCacheEventListener;
        DiskTrimmableRegistry mDiskTrimmableRegistry;
        if (diskCacheConfig$Builder.mDiskTrimmableRegistry == null) {
            mDiskTrimmableRegistry = NoOpDiskTrimmableRegistry.getInstance();
        }
        else {
            mDiskTrimmableRegistry = diskCacheConfig$Builder.mDiskTrimmableRegistry;
        }
        this.mDiskTrimmableRegistry = mDiskTrimmableRegistry;
    }
    
    public static DiskCacheConfig$Builder newBuilder(final Context context) {
        return new DiskCacheConfig$Builder(context, null);
    }
    
    public String getBaseDirectoryName() {
        return this.mBaseDirectoryName;
    }
    
    public Supplier<File> getBaseDirectoryPathSupplier() {
        return this.mBaseDirectoryPathSupplier;
    }
    
    public CacheErrorLogger getCacheErrorLogger() {
        return this.mCacheErrorLogger;
    }
    
    public CacheEventListener getCacheEventListener() {
        return this.mCacheEventListener;
    }
    
    public long getDefaultSizeLimit() {
        return this.mDefaultSizeLimit;
    }
    
    public DiskTrimmableRegistry getDiskTrimmableRegistry() {
        return this.mDiskTrimmableRegistry;
    }
    
    public EntryEvictionComparatorSupplier getEntryEvictionComparatorSupplier() {
        return this.mEntryEvictionComparatorSupplier;
    }
    
    public long getLowDiskSpaceSizeLimit() {
        return this.mLowDiskSpaceSizeLimit;
    }
    
    public long getMinimumSizeLimit() {
        return this.mMinimumSizeLimit;
    }
    
    public int getVersion() {
        return this.mVersion;
    }
}
