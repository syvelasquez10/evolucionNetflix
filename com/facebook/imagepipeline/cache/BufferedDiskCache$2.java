// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.cache;

import com.facebook.common.internal.Preconditions;
import com.facebook.cache.common.WriterCallback;
import java.io.InputStream;
import com.facebook.binaryresource.BinaryResource;
import java.io.IOException;
import bolts.Task;
import java.util.concurrent.Executor;
import com.facebook.imagepipeline.memory.PooledByteStreams;
import com.facebook.imagepipeline.memory.PooledByteBufferFactory;
import com.facebook.cache.disk.FileCache;
import com.facebook.imagepipeline.memory.PooledByteBuffer;
import com.facebook.common.references.CloseableReference;
import com.facebook.common.logging.FLog;
import java.util.concurrent.CancellationException;
import com.facebook.cache.common.CacheKey;
import java.util.concurrent.atomic.AtomicBoolean;
import com.facebook.imagepipeline.image.EncodedImage;
import java.util.concurrent.Callable;

class BufferedDiskCache$2 implements Callable<EncodedImage>
{
    final /* synthetic */ BufferedDiskCache this$0;
    final /* synthetic */ AtomicBoolean val$isCancelled;
    final /* synthetic */ CacheKey val$key;
    
    BufferedDiskCache$2(final BufferedDiskCache this$0, final AtomicBoolean val$isCancelled, final CacheKey val$key) {
        this.this$0 = this$0;
        this.val$isCancelled = val$isCancelled;
        this.val$key = val$key;
    }
    
    @Override
    public EncodedImage call() {
        if (this.val$isCancelled.get()) {
            throw new CancellationException();
        }
        final EncodedImage value = this.this$0.mStagingArea.get(this.val$key);
        if (value == null) {
            FLog.v(BufferedDiskCache.TAG, "Did not find image for %s in staging area", this.val$key.toString());
            this.this$0.mImageCacheStatsTracker.onStagingAreaMiss();
            EncodedImage encodedImage2 = null;
            try {
                final CloseableReference<PooledByteBuffer> of = CloseableReference.of(this.this$0.readFromDiskCache(this.val$key));
                try {
                    final EncodedImage encodedImage = new EncodedImage(of);
                }
                finally {
                    CloseableReference.closeSafely(of);
                }
            }
            catch (Exception ex) {
                encodedImage2 = null;
            }
            return encodedImage2;
        }
        FLog.v(BufferedDiskCache.TAG, "Found image for %s in staging area", this.val$key.toString());
        this.this$0.mImageCacheStatsTracker.onStagingAreaHit();
        EncodedImage encodedImage2 = value;
        if (Thread.interrupted()) {
            FLog.v(BufferedDiskCache.TAG, "Host thread was interrupted, decreasing reference count");
            if (value != null) {
                value.close();
            }
            throw new InterruptedException();
        }
        return encodedImage2;
    }
}
