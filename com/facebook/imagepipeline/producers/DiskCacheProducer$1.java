// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.producers;

import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequest$ImageType;
import com.facebook.imagepipeline.request.ImageRequest$RequestLevel;
import java.util.concurrent.CancellationException;
import com.facebook.common.internal.ImmutableMap;
import java.util.Map;
import com.facebook.imagepipeline.cache.CacheKeyFactory;
import com.facebook.imagepipeline.cache.BufferedDiskCache;
import java.util.concurrent.atomic.AtomicBoolean;
import com.facebook.cache.common.CacheKey;
import bolts.Task;
import com.facebook.imagepipeline.image.EncodedImage;
import bolts.Continuation;

class DiskCacheProducer$1 implements Continuation<EncodedImage, Task<EncodedImage>>
{
    final /* synthetic */ DiskCacheProducer this$0;
    final /* synthetic */ CacheKey val$cacheKey;
    final /* synthetic */ AtomicBoolean val$isCancelled;
    final /* synthetic */ BufferedDiskCache val$secondCache;
    
    DiskCacheProducer$1(final DiskCacheProducer this$0, final BufferedDiskCache val$secondCache, final CacheKey val$cacheKey, final AtomicBoolean val$isCancelled) {
        this.this$0 = this$0;
        this.val$secondCache = val$secondCache;
        this.val$cacheKey = val$cacheKey;
        this.val$isCancelled = val$isCancelled;
    }
    
    @Override
    public Task<EncodedImage> then(final Task<EncodedImage> task) {
        if (isTaskCancelled(task) || (!task.isFaulted() && task.getResult() != null)) {
            return task;
        }
        return this.val$secondCache.get(this.val$cacheKey, this.val$isCancelled);
    }
}
