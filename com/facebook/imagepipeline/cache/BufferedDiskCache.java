// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.cache;

import com.facebook.common.internal.Preconditions;
import com.facebook.cache.common.WriterCallback;
import java.io.InputStream;
import com.facebook.binaryresource.BinaryResource;
import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicBoolean;
import bolts.Task;
import com.facebook.common.logging.FLog;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.memory.PooledByteBuffer;
import com.facebook.cache.common.CacheKey;
import java.util.concurrent.Executor;
import com.facebook.imagepipeline.memory.PooledByteStreams;
import com.facebook.imagepipeline.memory.PooledByteBufferFactory;
import com.facebook.cache.disk.FileCache;

public class BufferedDiskCache
{
    private static final Class<?> TAG;
    private final FileCache mFileCache;
    private final ImageCacheStatsTracker mImageCacheStatsTracker;
    private final PooledByteBufferFactory mPooledByteBufferFactory;
    private final PooledByteStreams mPooledByteStreams;
    private final Executor mReadExecutor;
    private final StagingArea mStagingArea;
    private final Executor mWriteExecutor;
    
    static {
        TAG = BufferedDiskCache.class;
    }
    
    public BufferedDiskCache(final FileCache mFileCache, final PooledByteBufferFactory mPooledByteBufferFactory, final PooledByteStreams mPooledByteStreams, final Executor mReadExecutor, final Executor mWriteExecutor, final ImageCacheStatsTracker mImageCacheStatsTracker) {
        this.mFileCache = mFileCache;
        this.mPooledByteBufferFactory = mPooledByteBufferFactory;
        this.mPooledByteStreams = mPooledByteStreams;
        this.mReadExecutor = mReadExecutor;
        this.mWriteExecutor = mWriteExecutor;
        this.mImageCacheStatsTracker = mImageCacheStatsTracker;
        this.mStagingArea = StagingArea.getInstance();
    }
    
    private boolean checkInStagingAreaAndFileCache(final CacheKey cacheKey) {
        final EncodedImage value = this.mStagingArea.get(cacheKey);
        if (value != null) {
            value.close();
            FLog.v(BufferedDiskCache.TAG, "Found image for %s in staging area", cacheKey.toString());
            this.mImageCacheStatsTracker.onStagingAreaHit();
            return true;
        }
        FLog.v(BufferedDiskCache.TAG, "Did not find image for %s in staging area", cacheKey.toString());
        this.mImageCacheStatsTracker.onStagingAreaMiss();
        try {
            return this.mFileCache.hasKey(cacheKey);
        }
        catch (Exception ex) {
            return false;
        }
    }
    
    private Task<EncodedImage> foundPinnedImage(final CacheKey cacheKey, final EncodedImage encodedImage) {
        FLog.v(BufferedDiskCache.TAG, "Found image for %s in staging area", cacheKey.toString());
        this.mImageCacheStatsTracker.onStagingAreaHit();
        return Task.forResult(encodedImage);
    }
    
    private Task<EncodedImage> getAsync(final CacheKey cacheKey, final AtomicBoolean atomicBoolean) {
        try {
            return Task.call((Callable<EncodedImage>)new BufferedDiskCache$2(this, atomicBoolean, cacheKey), this.mReadExecutor);
        }
        catch (Exception ex) {
            FLog.w(BufferedDiskCache.TAG, ex, "Failed to schedule disk-cache read for %s", cacheKey.toString());
            return Task.forError(ex);
        }
    }
    
    private PooledByteBuffer readFromDiskCache(final CacheKey cacheKey) {
        BinaryResource resource;
        InputStream openStream = null;
        try {
            FLog.v(BufferedDiskCache.TAG, "Disk cache read for %s", cacheKey.toString());
            resource = this.mFileCache.getResource(cacheKey);
            if (resource == null) {
                FLog.v(BufferedDiskCache.TAG, "Disk cache miss for %s", cacheKey.toString());
                this.mImageCacheStatsTracker.onDiskCacheMiss();
                return null;
            }
            FLog.v(BufferedDiskCache.TAG, "Found entry in disk cache for %s", cacheKey.toString());
            this.mImageCacheStatsTracker.onDiskCacheHit();
            openStream = resource.openStream();
            final BufferedDiskCache bufferedDiskCache = this;
            final PooledByteBufferFactory pooledByteBufferFactory = bufferedDiskCache.mPooledByteBufferFactory;
            final InputStream inputStream = openStream;
            final BinaryResource binaryResource = resource;
            final long n = binaryResource.size();
            final int n2 = (int)n;
            final PooledByteBuffer pooledByteBuffer = pooledByteBufferFactory.newByteBuffer(inputStream, n2);
            final InputStream inputStream2 = openStream;
            inputStream2.close();
            final Class<?> clazz = BufferedDiskCache.TAG;
            final String s = "Successful read from disk cache for %s";
            final CacheKey cacheKey2 = cacheKey;
            final String s2 = cacheKey2.toString();
            FLog.v(clazz, s, s2);
            return pooledByteBuffer;
        }
        catch (IOException openStream) {
            FLog.w(BufferedDiskCache.TAG, (Throwable)openStream, "Exception reading from cache for %s", cacheKey.toString());
            this.mImageCacheStatsTracker.onDiskCacheGetFail();
            throw openStream;
        }
        try {
            final BufferedDiskCache bufferedDiskCache = this;
            final PooledByteBufferFactory pooledByteBufferFactory = bufferedDiskCache.mPooledByteBufferFactory;
            final InputStream inputStream = openStream;
            final BinaryResource binaryResource = resource;
            final long n = binaryResource.size();
            final int n2 = (int)n;
            final PooledByteBuffer pooledByteBuffer = pooledByteBufferFactory.newByteBuffer(inputStream, n2);
            final InputStream inputStream2 = openStream;
            inputStream2.close();
            final Class<?> clazz = BufferedDiskCache.TAG;
            final String s = "Successful read from disk cache for %s";
            final CacheKey cacheKey2 = cacheKey;
            final String s2 = cacheKey2.toString();
            FLog.v(clazz, s, s2);
            return pooledByteBuffer;
        }
        finally {
            openStream.close();
        }
    }
    
    private void writeToDiskCache(final CacheKey cacheKey, final EncodedImage encodedImage) {
        FLog.v(BufferedDiskCache.TAG, "About to write to disk-cache for key %s", cacheKey.toString());
        try {
            this.mFileCache.insert(cacheKey, new BufferedDiskCache$6(this, encodedImage));
            FLog.v(BufferedDiskCache.TAG, "Successful disk-cache write for key %s", cacheKey.toString());
        }
        catch (IOException ex) {
            FLog.w(BufferedDiskCache.TAG, ex, "Failed to write to disk-cache for key %s", cacheKey.toString());
        }
    }
    
    public Task<Void> clearAll() {
        this.mStagingArea.clearAll();
        try {
            return Task.call((Callable<Void>)new BufferedDiskCache$5(this), this.mWriteExecutor);
        }
        catch (Exception ex) {
            FLog.w(BufferedDiskCache.TAG, ex, "Failed to schedule disk-cache clear", new Object[0]);
            return Task.forError(ex);
        }
    }
    
    public boolean containsSync(final CacheKey cacheKey) {
        return this.mStagingArea.containsKey(cacheKey) || this.mFileCache.hasKeySync(cacheKey);
    }
    
    public boolean diskCheckSync(final CacheKey cacheKey) {
        return this.containsSync(cacheKey) || this.checkInStagingAreaAndFileCache(cacheKey);
    }
    
    public Task<EncodedImage> get(final CacheKey cacheKey, final AtomicBoolean atomicBoolean) {
        final EncodedImage value = this.mStagingArea.get(cacheKey);
        if (value != null) {
            return this.foundPinnedImage(cacheKey, value);
        }
        return this.getAsync(cacheKey, atomicBoolean);
    }
    
    public void put(final CacheKey cacheKey, final EncodedImage encodedImage) {
        Preconditions.checkNotNull(cacheKey);
        Preconditions.checkArgument(EncodedImage.isValid(encodedImage));
        this.mStagingArea.put(cacheKey, encodedImage);
        final EncodedImage cloneOrNull = EncodedImage.cloneOrNull(encodedImage);
        try {
            this.mWriteExecutor.execute(new BufferedDiskCache$3(this, cacheKey, cloneOrNull));
        }
        catch (Exception ex) {
            FLog.w(BufferedDiskCache.TAG, ex, "Failed to schedule disk-cache write for %s", cacheKey.toString());
            this.mStagingArea.remove(cacheKey, encodedImage);
            EncodedImage.closeSafely(cloneOrNull);
        }
    }
}
