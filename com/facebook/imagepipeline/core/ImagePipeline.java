// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.core;

import com.facebook.imagepipeline.datasource.ProducerToDataSourceAdapter;
import com.facebook.imagepipeline.common.Priority;
import com.facebook.datasource.DataSources;
import com.facebook.imagepipeline.datasource.CloseableProducerToDataSourceAdapter;
import com.facebook.imagepipeline.producers.ProducerListener;
import com.facebook.imagepipeline.producers.SettableProducerContext;
import com.facebook.common.util.UriUtil;
import com.facebook.datasource.DataSource;
import com.facebook.imagepipeline.request.ImageRequest$RequestLevel;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.common.references.CloseableReference;
import com.facebook.imagepipeline.producers.Producer;
import com.android.internal.util.Predicate;
import android.net.Uri;
import com.facebook.imagepipeline.listener.ForwardingRequestListener;
import java.util.Set;
import com.facebook.imagepipeline.producers.ThreadHandoffProducerQueue;
import com.facebook.imagepipeline.listener.RequestListener;
import com.facebook.imagepipeline.cache.BufferedDiskCache;
import com.facebook.common.internal.Supplier;
import java.util.concurrent.atomic.AtomicLong;
import com.facebook.imagepipeline.memory.PooledByteBuffer;
import com.facebook.imagepipeline.cache.CacheKeyFactory;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.cache.common.CacheKey;
import com.facebook.imagepipeline.cache.MemoryCache;
import java.util.concurrent.CancellationException;

public class ImagePipeline
{
    private static final CancellationException PREFETCH_EXCEPTION;
    private final MemoryCache<CacheKey, CloseableImage> mBitmapMemoryCache;
    private final CacheKeyFactory mCacheKeyFactory;
    private final MemoryCache<CacheKey, PooledByteBuffer> mEncodedMemoryCache;
    private AtomicLong mIdCounter;
    private final Supplier<Boolean> mIsPrefetchEnabledSupplier;
    private final BufferedDiskCache mMainBufferedDiskCache;
    private final ProducerSequenceFactory mProducerSequenceFactory;
    private final RequestListener mRequestListener;
    private final BufferedDiskCache mSmallImageBufferedDiskCache;
    private final ThreadHandoffProducerQueue mThreadHandoffProducerQueue;
    
    static {
        PREFETCH_EXCEPTION = new CancellationException("Prefetching is not enabled");
    }
    
    public ImagePipeline(final ProducerSequenceFactory mProducerSequenceFactory, final Set<RequestListener> set, final Supplier<Boolean> mIsPrefetchEnabledSupplier, final MemoryCache<CacheKey, CloseableImage> mBitmapMemoryCache, final MemoryCache<CacheKey, PooledByteBuffer> mEncodedMemoryCache, final BufferedDiskCache mMainBufferedDiskCache, final BufferedDiskCache mSmallImageBufferedDiskCache, final CacheKeyFactory mCacheKeyFactory, final ThreadHandoffProducerQueue mThreadHandoffProducerQueue) {
        this.mIdCounter = new AtomicLong();
        this.mProducerSequenceFactory = mProducerSequenceFactory;
        this.mRequestListener = new ForwardingRequestListener(set);
        this.mIsPrefetchEnabledSupplier = mIsPrefetchEnabledSupplier;
        this.mBitmapMemoryCache = mBitmapMemoryCache;
        this.mEncodedMemoryCache = mEncodedMemoryCache;
        this.mMainBufferedDiskCache = mMainBufferedDiskCache;
        this.mSmallImageBufferedDiskCache = mSmallImageBufferedDiskCache;
        this.mCacheKeyFactory = mCacheKeyFactory;
        this.mThreadHandoffProducerQueue = mThreadHandoffProducerQueue;
    }
    
    private String generateUniqueFutureId() {
        return String.valueOf(this.mIdCounter.getAndIncrement());
    }
    
    private Predicate<CacheKey> predicateForUri(final Uri uri) {
        return (Predicate<CacheKey>)new ImagePipeline$6(this, uri);
    }
    
    private <T> DataSource<CloseableReference<T>> submitFetchRequest(final Producer<CloseableReference<T>> producer, final ImageRequest imageRequest, ImageRequest$RequestLevel max, final Object o) {
        while (true) {
            boolean b = false;
            while (true) {
                try {
                    max = ImageRequest$RequestLevel.getMax(imageRequest.getLowestPermittedRequestLevel(), max);
                    final String generateUniqueFutureId = this.generateUniqueFutureId();
                    final RequestListener mRequestListener = this.mRequestListener;
                    if (!imageRequest.getProgressiveRenderingEnabled()) {
                        if (UriUtil.isNetworkUri(imageRequest.getSourceUri())) {
                            return CloseableProducerToDataSourceAdapter.create(producer, new SettableProducerContext(imageRequest, generateUniqueFutureId, mRequestListener, o, max, false, b, imageRequest.getPriority()), this.mRequestListener);
                        }
                    }
                }
                catch (Exception ex) {
                    return DataSources.immediateFailedDataSource(ex);
                }
                b = true;
                continue;
            }
        }
    }
    
    private DataSource<Void> submitPrefetchRequest(final Producer<Void> producer, final ImageRequest imageRequest, ImageRequest$RequestLevel max, final Object o, final Priority priority) {
        try {
            max = ImageRequest$RequestLevel.getMax(imageRequest.getLowestPermittedRequestLevel(), max);
            return ProducerToDataSourceAdapter.create(producer, new SettableProducerContext(imageRequest, this.generateUniqueFutureId(), this.mRequestListener, o, max, true, false, priority), this.mRequestListener);
        }
        catch (Exception ex) {
            return DataSources.immediateFailedDataSource(ex);
        }
    }
    
    public void clearCaches() {
        this.clearMemoryCaches();
        this.clearDiskCaches();
    }
    
    public void clearDiskCaches() {
        this.mMainBufferedDiskCache.clearAll();
        this.mSmallImageBufferedDiskCache.clearAll();
    }
    
    public void clearMemoryCaches() {
        final ImagePipeline$3 imagePipeline$3 = new ImagePipeline$3(this);
        this.mBitmapMemoryCache.removeAll((com.android.internal.util.Predicate<CacheKey>)imagePipeline$3);
        this.mEncodedMemoryCache.removeAll((com.android.internal.util.Predicate<CacheKey>)imagePipeline$3);
    }
    
    public DataSource<CloseableReference<CloseableImage>> fetchDecodedImage(final ImageRequest imageRequest, final Object o) {
        try {
            return this.submitFetchRequest(this.mProducerSequenceFactory.getDecodedImageProducerSequence(imageRequest), imageRequest, ImageRequest$RequestLevel.FULL_FETCH, o);
        }
        catch (Exception ex) {
            return DataSources.immediateFailedDataSource(ex);
        }
    }
    
    public DataSource<CloseableReference<CloseableImage>> fetchImageFromBitmapCache(final ImageRequest imageRequest, final Object o) {
        try {
            return this.submitFetchRequest(this.mProducerSequenceFactory.getDecodedImageProducerSequence(imageRequest), imageRequest, ImageRequest$RequestLevel.BITMAP_MEMORY_CACHE, o);
        }
        catch (Exception ex) {
            return DataSources.immediateFailedDataSource(ex);
        }
    }
    
    public boolean isInBitmapMemoryCache(final Uri uri) {
        return uri != null && this.mBitmapMemoryCache.contains(this.predicateForUri(uri));
    }
    
    public boolean isInDiskCacheSync(final Uri uri) {
        return this.isInDiskCacheSync(ImageRequest.fromUri(uri));
    }
    
    public boolean isInDiskCacheSync(final ImageRequest imageRequest) {
        return this.mMainBufferedDiskCache.diskCheckSync(this.mCacheKeyFactory.getEncodedCacheKey(imageRequest, null));
    }
    
    public DataSource<Void> prefetchToDiskCache(final ImageRequest imageRequest, final Object o) {
        return this.prefetchToDiskCache(imageRequest, o, Priority.LOW);
    }
    
    public DataSource<Void> prefetchToDiskCache(final ImageRequest imageRequest, final Object o, final Priority priority) {
        if (!this.mIsPrefetchEnabledSupplier.get()) {
            return DataSources.immediateFailedDataSource(ImagePipeline.PREFETCH_EXCEPTION);
        }
        try {
            return this.submitPrefetchRequest(this.mProducerSequenceFactory.getEncodedImagePrefetchProducerSequence(imageRequest), imageRequest, ImageRequest$RequestLevel.FULL_FETCH, o, priority);
        }
        catch (Exception ex) {
            return DataSources.immediateFailedDataSource(ex);
        }
    }
}
