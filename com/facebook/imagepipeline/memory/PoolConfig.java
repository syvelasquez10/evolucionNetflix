// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.memory;

import com.facebook.common.memory.NoOpMemoryTrimmableRegistry;
import com.facebook.common.memory.MemoryTrimmableRegistry;

public class PoolConfig
{
    private final PoolParams mBitmapPoolParams;
    private final PoolStatsTracker mBitmapPoolStatsTracker;
    private final PoolParams mFlexByteArrayPoolParams;
    private final MemoryTrimmableRegistry mMemoryTrimmableRegistry;
    private final PoolParams mNativeMemoryChunkPoolParams;
    private final PoolStatsTracker mNativeMemoryChunkPoolStatsTracker;
    private final PoolParams mSmallByteArrayPoolParams;
    private final PoolStatsTracker mSmallByteArrayPoolStatsTracker;
    
    private PoolConfig(final PoolConfig$Builder poolConfig$Builder) {
        PoolParams mBitmapPoolParams;
        if (poolConfig$Builder.mBitmapPoolParams == null) {
            mBitmapPoolParams = DefaultBitmapPoolParams.get();
        }
        else {
            mBitmapPoolParams = poolConfig$Builder.mBitmapPoolParams;
        }
        this.mBitmapPoolParams = mBitmapPoolParams;
        PoolStatsTracker mBitmapPoolStatsTracker;
        if (poolConfig$Builder.mBitmapPoolStatsTracker == null) {
            mBitmapPoolStatsTracker = NoOpPoolStatsTracker.getInstance();
        }
        else {
            mBitmapPoolStatsTracker = poolConfig$Builder.mBitmapPoolStatsTracker;
        }
        this.mBitmapPoolStatsTracker = mBitmapPoolStatsTracker;
        PoolParams mFlexByteArrayPoolParams;
        if (poolConfig$Builder.mFlexByteArrayPoolParams == null) {
            mFlexByteArrayPoolParams = DefaultFlexByteArrayPoolParams.get();
        }
        else {
            mFlexByteArrayPoolParams = poolConfig$Builder.mFlexByteArrayPoolParams;
        }
        this.mFlexByteArrayPoolParams = mFlexByteArrayPoolParams;
        MemoryTrimmableRegistry mMemoryTrimmableRegistry;
        if (poolConfig$Builder.mMemoryTrimmableRegistry == null) {
            mMemoryTrimmableRegistry = NoOpMemoryTrimmableRegistry.getInstance();
        }
        else {
            mMemoryTrimmableRegistry = poolConfig$Builder.mMemoryTrimmableRegistry;
        }
        this.mMemoryTrimmableRegistry = mMemoryTrimmableRegistry;
        PoolParams mNativeMemoryChunkPoolParams;
        if (poolConfig$Builder.mNativeMemoryChunkPoolParams == null) {
            mNativeMemoryChunkPoolParams = DefaultNativeMemoryChunkPoolParams.get();
        }
        else {
            mNativeMemoryChunkPoolParams = poolConfig$Builder.mNativeMemoryChunkPoolParams;
        }
        this.mNativeMemoryChunkPoolParams = mNativeMemoryChunkPoolParams;
        PoolStatsTracker mNativeMemoryChunkPoolStatsTracker;
        if (poolConfig$Builder.mNativeMemoryChunkPoolStatsTracker == null) {
            mNativeMemoryChunkPoolStatsTracker = NoOpPoolStatsTracker.getInstance();
        }
        else {
            mNativeMemoryChunkPoolStatsTracker = poolConfig$Builder.mNativeMemoryChunkPoolStatsTracker;
        }
        this.mNativeMemoryChunkPoolStatsTracker = mNativeMemoryChunkPoolStatsTracker;
        PoolParams mSmallByteArrayPoolParams;
        if (poolConfig$Builder.mSmallByteArrayPoolParams == null) {
            mSmallByteArrayPoolParams = DefaultByteArrayPoolParams.get();
        }
        else {
            mSmallByteArrayPoolParams = poolConfig$Builder.mSmallByteArrayPoolParams;
        }
        this.mSmallByteArrayPoolParams = mSmallByteArrayPoolParams;
        PoolStatsTracker mSmallByteArrayPoolStatsTracker;
        if (poolConfig$Builder.mSmallByteArrayPoolStatsTracker == null) {
            mSmallByteArrayPoolStatsTracker = NoOpPoolStatsTracker.getInstance();
        }
        else {
            mSmallByteArrayPoolStatsTracker = poolConfig$Builder.mSmallByteArrayPoolStatsTracker;
        }
        this.mSmallByteArrayPoolStatsTracker = mSmallByteArrayPoolStatsTracker;
    }
    
    public static PoolConfig$Builder newBuilder() {
        return new PoolConfig$Builder(null);
    }
    
    public PoolParams getBitmapPoolParams() {
        return this.mBitmapPoolParams;
    }
    
    public PoolStatsTracker getBitmapPoolStatsTracker() {
        return this.mBitmapPoolStatsTracker;
    }
    
    public PoolParams getFlexByteArrayPoolParams() {
        return this.mFlexByteArrayPoolParams;
    }
    
    public MemoryTrimmableRegistry getMemoryTrimmableRegistry() {
        return this.mMemoryTrimmableRegistry;
    }
    
    public PoolParams getNativeMemoryChunkPoolParams() {
        return this.mNativeMemoryChunkPoolParams;
    }
    
    public PoolStatsTracker getNativeMemoryChunkPoolStatsTracker() {
        return this.mNativeMemoryChunkPoolStatsTracker;
    }
    
    public PoolParams getSmallByteArrayPoolParams() {
        return this.mSmallByteArrayPoolParams;
    }
    
    public PoolStatsTracker getSmallByteArrayPoolStatsTracker() {
        return this.mSmallByteArrayPoolStatsTracker;
    }
}
