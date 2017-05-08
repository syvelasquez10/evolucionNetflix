// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.memory;

import com.facebook.common.internal.Preconditions;

public class PoolFactory
{
    private BitmapPool mBitmapPool;
    private final PoolConfig mConfig;
    private FlexByteArrayPool mFlexByteArrayPool;
    private NativeMemoryChunkPool mNativeMemoryChunkPool;
    private PooledByteBufferFactory mPooledByteBufferFactory;
    private PooledByteStreams mPooledByteStreams;
    private ByteArrayPool mSmallByteArrayPool;
    
    public PoolFactory(final PoolConfig poolConfig) {
        this.mConfig = Preconditions.checkNotNull(poolConfig);
    }
    
    public BitmapPool getBitmapPool() {
        if (this.mBitmapPool == null) {
            this.mBitmapPool = new BitmapPool(this.mConfig.getMemoryTrimmableRegistry(), this.mConfig.getBitmapPoolParams(), this.mConfig.getBitmapPoolStatsTracker());
        }
        return this.mBitmapPool;
    }
    
    public FlexByteArrayPool getFlexByteArrayPool() {
        if (this.mFlexByteArrayPool == null) {
            this.mFlexByteArrayPool = new FlexByteArrayPool(this.mConfig.getMemoryTrimmableRegistry(), this.mConfig.getFlexByteArrayPoolParams());
        }
        return this.mFlexByteArrayPool;
    }
    
    public int getFlexByteArrayPoolMaxNumThreads() {
        return this.mConfig.getFlexByteArrayPoolParams().maxNumThreads;
    }
    
    public NativeMemoryChunkPool getNativeMemoryChunkPool() {
        if (this.mNativeMemoryChunkPool == null) {
            this.mNativeMemoryChunkPool = new NativeMemoryChunkPool(this.mConfig.getMemoryTrimmableRegistry(), this.mConfig.getNativeMemoryChunkPoolParams(), this.mConfig.getNativeMemoryChunkPoolStatsTracker());
        }
        return this.mNativeMemoryChunkPool;
    }
    
    public PooledByteBufferFactory getPooledByteBufferFactory() {
        if (this.mPooledByteBufferFactory == null) {
            this.mPooledByteBufferFactory = new NativePooledByteBufferFactory(this.getNativeMemoryChunkPool(), this.getPooledByteStreams());
        }
        return this.mPooledByteBufferFactory;
    }
    
    public PooledByteStreams getPooledByteStreams() {
        if (this.mPooledByteStreams == null) {
            this.mPooledByteStreams = new PooledByteStreams(this.getSmallByteArrayPool());
        }
        return this.mPooledByteStreams;
    }
    
    public ByteArrayPool getSmallByteArrayPool() {
        if (this.mSmallByteArrayPool == null) {
            this.mSmallByteArrayPool = new GenericByteArrayPool(this.mConfig.getMemoryTrimmableRegistry(), this.mConfig.getSmallByteArrayPoolParams(), this.mConfig.getSmallByteArrayPoolStatsTracker());
        }
        return this.mSmallByteArrayPool;
    }
}
