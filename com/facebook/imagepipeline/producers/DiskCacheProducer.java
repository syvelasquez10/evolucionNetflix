// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.producers;

import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequest$ImageType;
import java.util.concurrent.atomic.AtomicBoolean;
import bolts.Continuation;
import com.facebook.cache.common.CacheKey;
import com.facebook.imagepipeline.request.ImageRequest$RequestLevel;
import java.util.concurrent.CancellationException;
import com.facebook.common.internal.ImmutableMap;
import java.util.Map;
import bolts.Task;
import com.facebook.imagepipeline.cache.BufferedDiskCache;
import com.facebook.imagepipeline.cache.CacheKeyFactory;
import com.facebook.imagepipeline.image.EncodedImage;

public class DiskCacheProducer implements Producer<EncodedImage>
{
    private final CacheKeyFactory mCacheKeyFactory;
    private final boolean mChooseCacheByImageSize;
    private final BufferedDiskCache mDefaultBufferedDiskCache;
    private final int mForceSmallCacheThresholdBytes;
    private final Producer<EncodedImage> mInputProducer;
    private final BufferedDiskCache mSmallImageBufferedDiskCache;
    
    public DiskCacheProducer(final BufferedDiskCache mDefaultBufferedDiskCache, final BufferedDiskCache mSmallImageBufferedDiskCache, final CacheKeyFactory mCacheKeyFactory, final Producer<EncodedImage> mInputProducer, final int mForceSmallCacheThresholdBytes) {
        this.mDefaultBufferedDiskCache = mDefaultBufferedDiskCache;
        this.mSmallImageBufferedDiskCache = mSmallImageBufferedDiskCache;
        this.mCacheKeyFactory = mCacheKeyFactory;
        this.mInputProducer = mInputProducer;
        this.mForceSmallCacheThresholdBytes = mForceSmallCacheThresholdBytes;
        this.mChooseCacheByImageSize = (mForceSmallCacheThresholdBytes > 0);
    }
    
    static Map<String, String> getExtraMap(final ProducerListener producerListener, final String s, final boolean b) {
        if (!producerListener.requiresExtraMap(s)) {
            return null;
        }
        return ImmutableMap.of("cached_value_found", String.valueOf(b));
    }
    
    private static boolean isTaskCancelled(final Task<?> task) {
        return task.isCancelled() || (task.isFaulted() && task.getError() instanceof CancellationException);
    }
    
    private void maybeStartInputProducer(final Consumer<EncodedImage> consumer, final Consumer<EncodedImage> consumer2, final ProducerContext producerContext) {
        if (producerContext.getLowestPermittedRequestLevel().getValue() >= ImageRequest$RequestLevel.DISK_CACHE.getValue()) {
            consumer.onNewResult(null, true);
            return;
        }
        this.mInputProducer.produceResults(consumer2, producerContext);
    }
    
    private Continuation<EncodedImage, Void> onFinishDiskReads(final Consumer<EncodedImage> consumer, final BufferedDiskCache bufferedDiskCache, final CacheKey cacheKey, final ProducerContext producerContext) {
        return new DiskCacheProducer$2(this, producerContext.getListener(), producerContext.getId(), consumer, bufferedDiskCache, cacheKey, producerContext);
    }
    
    private void subscribeTaskForRequestCancellation(final AtomicBoolean atomicBoolean, final ProducerContext producerContext) {
        producerContext.addCallbacks(new DiskCacheProducer$3(this, atomicBoolean));
    }
    
    @Override
    public void produceResults(final Consumer<EncodedImage> consumer, final ProducerContext producerContext) {
        final ImageRequest imageRequest = producerContext.getImageRequest();
        if (!imageRequest.isDiskCacheEnabled()) {
            this.maybeStartInputProducer(consumer, consumer, producerContext);
            return;
        }
        producerContext.getListener().onProducerStart(producerContext.getId(), "DiskCacheProducer");
        final CacheKey encodedCacheKey = this.mCacheKeyFactory.getEncodedCacheKey(imageRequest, producerContext.getCallerContext());
        int n;
        if (imageRequest.getImageType() == ImageRequest$ImageType.SMALL) {
            n = 1;
        }
        else {
            n = 0;
        }
        BufferedDiskCache bufferedDiskCache;
        if (n != 0) {
            bufferedDiskCache = this.mSmallImageBufferedDiskCache;
        }
        else {
            bufferedDiskCache = this.mDefaultBufferedDiskCache;
        }
        final AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        Task<EncodedImage> task;
        if (this.mChooseCacheByImageSize) {
            final boolean containsSync = this.mSmallImageBufferedDiskCache.containsSync(encodedCacheKey);
            final boolean containsSync2 = this.mDefaultBufferedDiskCache.containsSync(encodedCacheKey);
            BufferedDiskCache bufferedDiskCache2;
            BufferedDiskCache bufferedDiskCache3;
            if (containsSync || !containsSync2) {
                bufferedDiskCache2 = this.mSmallImageBufferedDiskCache;
                bufferedDiskCache3 = this.mDefaultBufferedDiskCache;
            }
            else {
                bufferedDiskCache2 = this.mDefaultBufferedDiskCache;
                bufferedDiskCache3 = this.mSmallImageBufferedDiskCache;
            }
            task = bufferedDiskCache2.get(encodedCacheKey, atomicBoolean).continueWithTask((Continuation<EncodedImage, Task<EncodedImage>>)new DiskCacheProducer$1(this, bufferedDiskCache3, encodedCacheKey, atomicBoolean));
        }
        else {
            task = bufferedDiskCache.get(encodedCacheKey, atomicBoolean);
        }
        task.continueWith(this.onFinishDiskReads(consumer, bufferedDiskCache, encodedCacheKey, producerContext));
        this.subscribeTaskForRequestCancellation(atomicBoolean, producerContext);
    }
}
