// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.core;

import com.facebook.imagepipeline.producers.WebpTranscodeProducer;
import com.facebook.imagepipeline.producers.ThumbnailBranchProducer;
import com.facebook.imagepipeline.producers.ThumbnailProducer;
import com.facebook.imagepipeline.producers.ThrottlingProducer;
import com.facebook.imagepipeline.producers.ResizeAndRotateProducer;
import com.facebook.imagepipeline.producers.PostprocessorProducer;
import com.facebook.imagepipeline.producers.PostprocessedBitmapMemoryCacheProducer;
import com.facebook.imagepipeline.producers.NetworkFetchProducer;
import com.facebook.imagepipeline.producers.NetworkFetcher;
import com.facebook.imagepipeline.producers.LocalVideoThumbnailProducer;
import com.facebook.imagepipeline.producers.LocalResourceFetchProducer;
import com.facebook.imagepipeline.producers.LocalFileFetchProducer;
import com.facebook.imagepipeline.producers.LocalExifThumbnailProducer;
import com.facebook.imagepipeline.producers.LocalContentUriThumbnailFetchProducer;
import com.facebook.imagepipeline.producers.LocalContentUriFetchProducer;
import com.facebook.imagepipeline.producers.LocalAssetFetchProducer;
import com.facebook.imagepipeline.producers.EncodedMemoryCacheProducer;
import com.facebook.imagepipeline.producers.EncodedCacheKeyMultiplexProducer;
import com.facebook.imagepipeline.producers.DiskCacheProducer;
import com.facebook.imagepipeline.producers.DecodeProducer;
import com.facebook.imagepipeline.producers.DataFetchProducer;
import com.facebook.imagepipeline.producers.BitmapMemoryCacheProducer;
import com.facebook.imagepipeline.producers.BitmapMemoryCacheKeyMultiplexProducer;
import com.facebook.imagepipeline.producers.BitmapMemoryCacheGetProducer;
import com.facebook.common.references.CloseableReference;
import com.facebook.imagepipeline.producers.ThreadHandoffProducer;
import com.facebook.imagepipeline.producers.ThreadHandoffProducerQueue;
import com.facebook.imagepipeline.producers.SwallowResultProducer;
import com.facebook.imagepipeline.producers.BranchOnSeparateImagesProducer;
import com.facebook.imagepipeline.producers.AddImageTransformMetaDataProducer;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.producers.Producer;
import android.content.Context;
import android.content.res.Resources;
import com.facebook.imagepipeline.decoder.ProgressiveJpegConfig;
import com.facebook.imagepipeline.memory.PooledByteBufferFactory;
import com.facebook.imagepipeline.bitmaps.PlatformBitmapFactory;
import com.facebook.imagepipeline.decoder.ImageDecoder;
import com.facebook.imagepipeline.memory.PooledByteBuffer;
import com.facebook.imagepipeline.cache.BufferedDiskCache;
import android.content.ContentResolver;
import com.facebook.imagepipeline.cache.CacheKeyFactory;
import com.facebook.imagepipeline.memory.ByteArrayPool;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.cache.common.CacheKey;
import com.facebook.imagepipeline.cache.MemoryCache;
import android.content.res.AssetManager;

public class ProducerFactory
{
    private AssetManager mAssetManager;
    private final MemoryCache<CacheKey, CloseableImage> mBitmapMemoryCache;
    private final ByteArrayPool mByteArrayPool;
    private final CacheKeyFactory mCacheKeyFactory;
    private ContentResolver mContentResolver;
    private final boolean mDecodeFileDescriptorEnabled;
    private final BufferedDiskCache mDefaultBufferedDiskCache;
    private final boolean mDownsampleEnabled;
    private final MemoryCache<CacheKey, PooledByteBuffer> mEncodedMemoryCache;
    private final ExecutorSupplier mExecutorSupplier;
    private final int mForceSmallCacheThresholdBytes;
    private final ImageDecoder mImageDecoder;
    private final PlatformBitmapFactory mPlatformBitmapFactory;
    private final PooledByteBufferFactory mPooledByteBufferFactory;
    private final ProgressiveJpegConfig mProgressiveJpegConfig;
    private final boolean mResizeAndRotateEnabledForNetwork;
    private Resources mResources;
    private final BufferedDiskCache mSmallImageBufferedDiskCache;
    
    public ProducerFactory(final Context context, final ByteArrayPool mByteArrayPool, final ImageDecoder mImageDecoder, final ProgressiveJpegConfig mProgressiveJpegConfig, final boolean mDownsampleEnabled, final boolean mResizeAndRotateEnabledForNetwork, final ExecutorSupplier mExecutorSupplier, final PooledByteBufferFactory mPooledByteBufferFactory, final MemoryCache<CacheKey, CloseableImage> mBitmapMemoryCache, final MemoryCache<CacheKey, PooledByteBuffer> mEncodedMemoryCache, final BufferedDiskCache mDefaultBufferedDiskCache, final BufferedDiskCache mSmallImageBufferedDiskCache, final CacheKeyFactory mCacheKeyFactory, final PlatformBitmapFactory mPlatformBitmapFactory, final boolean mDecodeFileDescriptorEnabled, final int mForceSmallCacheThresholdBytes) {
        this.mForceSmallCacheThresholdBytes = mForceSmallCacheThresholdBytes;
        this.mContentResolver = context.getApplicationContext().getContentResolver();
        this.mResources = context.getApplicationContext().getResources();
        this.mAssetManager = context.getApplicationContext().getAssets();
        this.mByteArrayPool = mByteArrayPool;
        this.mImageDecoder = mImageDecoder;
        this.mProgressiveJpegConfig = mProgressiveJpegConfig;
        this.mDownsampleEnabled = mDownsampleEnabled;
        this.mResizeAndRotateEnabledForNetwork = mResizeAndRotateEnabledForNetwork;
        this.mExecutorSupplier = mExecutorSupplier;
        this.mPooledByteBufferFactory = mPooledByteBufferFactory;
        this.mBitmapMemoryCache = mBitmapMemoryCache;
        this.mEncodedMemoryCache = mEncodedMemoryCache;
        this.mDefaultBufferedDiskCache = mDefaultBufferedDiskCache;
        this.mSmallImageBufferedDiskCache = mSmallImageBufferedDiskCache;
        this.mCacheKeyFactory = mCacheKeyFactory;
        this.mPlatformBitmapFactory = mPlatformBitmapFactory;
        this.mDecodeFileDescriptorEnabled = mDecodeFileDescriptorEnabled;
    }
    
    public static AddImageTransformMetaDataProducer newAddImageTransformMetaDataProducer(final Producer<EncodedImage> producer) {
        return new AddImageTransformMetaDataProducer(producer);
    }
    
    public static BranchOnSeparateImagesProducer newBranchOnSeparateImagesProducer(final Producer<EncodedImage> producer, final Producer<EncodedImage> producer2) {
        return new BranchOnSeparateImagesProducer(producer, producer2);
    }
    
    public static <T> SwallowResultProducer<T> newSwallowResultProducer(final Producer<T> producer) {
        return new SwallowResultProducer<T>(producer);
    }
    
    public <T> ThreadHandoffProducer<T> newBackgroundThreadHandoffProducer(final Producer<T> producer, final ThreadHandoffProducerQueue threadHandoffProducerQueue) {
        return new ThreadHandoffProducer<T>(producer, threadHandoffProducerQueue);
    }
    
    public BitmapMemoryCacheGetProducer newBitmapMemoryCacheGetProducer(final Producer<CloseableReference<CloseableImage>> producer) {
        return new BitmapMemoryCacheGetProducer(this.mBitmapMemoryCache, this.mCacheKeyFactory, producer);
    }
    
    public BitmapMemoryCacheKeyMultiplexProducer newBitmapMemoryCacheKeyMultiplexProducer(final Producer<CloseableReference<CloseableImage>> producer) {
        return new BitmapMemoryCacheKeyMultiplexProducer(this.mCacheKeyFactory, producer);
    }
    
    public BitmapMemoryCacheProducer newBitmapMemoryCacheProducer(final Producer<CloseableReference<CloseableImage>> producer) {
        return new BitmapMemoryCacheProducer(this.mBitmapMemoryCache, this.mCacheKeyFactory, producer);
    }
    
    public DataFetchProducer newDataFetchProducer() {
        return new DataFetchProducer(this.mPooledByteBufferFactory, this.mDecodeFileDescriptorEnabled);
    }
    
    public DecodeProducer newDecodeProducer(final Producer<EncodedImage> producer) {
        return new DecodeProducer(this.mByteArrayPool, this.mExecutorSupplier.forDecode(), this.mImageDecoder, this.mProgressiveJpegConfig, this.mDownsampleEnabled, this.mResizeAndRotateEnabledForNetwork, producer);
    }
    
    public DiskCacheProducer newDiskCacheProducer(final Producer<EncodedImage> producer) {
        return new DiskCacheProducer(this.mDefaultBufferedDiskCache, this.mSmallImageBufferedDiskCache, this.mCacheKeyFactory, producer, this.mForceSmallCacheThresholdBytes);
    }
    
    public EncodedCacheKeyMultiplexProducer newEncodedCacheKeyMultiplexProducer(final Producer<EncodedImage> producer) {
        return new EncodedCacheKeyMultiplexProducer(this.mCacheKeyFactory, producer);
    }
    
    public EncodedMemoryCacheProducer newEncodedMemoryCacheProducer(final Producer<EncodedImage> producer) {
        return new EncodedMemoryCacheProducer(this.mEncodedMemoryCache, this.mCacheKeyFactory, producer);
    }
    
    public LocalAssetFetchProducer newLocalAssetFetchProducer() {
        return new LocalAssetFetchProducer(this.mExecutorSupplier.forLocalStorageRead(), this.mPooledByteBufferFactory, this.mAssetManager, this.mDecodeFileDescriptorEnabled);
    }
    
    public LocalContentUriFetchProducer newLocalContentUriFetchProducer() {
        return new LocalContentUriFetchProducer(this.mExecutorSupplier.forLocalStorageRead(), this.mPooledByteBufferFactory, this.mContentResolver, this.mDecodeFileDescriptorEnabled);
    }
    
    public LocalContentUriThumbnailFetchProducer newLocalContentUriThumbnailFetchProducer() {
        return new LocalContentUriThumbnailFetchProducer(this.mExecutorSupplier.forLocalStorageRead(), this.mPooledByteBufferFactory, this.mContentResolver, this.mDecodeFileDescriptorEnabled);
    }
    
    public LocalExifThumbnailProducer newLocalExifThumbnailProducer() {
        return new LocalExifThumbnailProducer(this.mExecutorSupplier.forLocalStorageRead(), this.mPooledByteBufferFactory, this.mContentResolver);
    }
    
    public LocalFileFetchProducer newLocalFileFetchProducer() {
        return new LocalFileFetchProducer(this.mExecutorSupplier.forLocalStorageRead(), this.mPooledByteBufferFactory, this.mDecodeFileDescriptorEnabled);
    }
    
    public LocalResourceFetchProducer newLocalResourceFetchProducer() {
        return new LocalResourceFetchProducer(this.mExecutorSupplier.forLocalStorageRead(), this.mPooledByteBufferFactory, this.mResources, this.mDecodeFileDescriptorEnabled);
    }
    
    public LocalVideoThumbnailProducer newLocalVideoThumbnailProducer() {
        return new LocalVideoThumbnailProducer(this.mExecutorSupplier.forLocalStorageRead());
    }
    
    public NetworkFetchProducer newNetworkFetchProducer(final NetworkFetcher networkFetcher) {
        return new NetworkFetchProducer(this.mPooledByteBufferFactory, this.mByteArrayPool, networkFetcher);
    }
    
    public PostprocessedBitmapMemoryCacheProducer newPostprocessorBitmapMemoryCacheProducer(final Producer<CloseableReference<CloseableImage>> producer) {
        return new PostprocessedBitmapMemoryCacheProducer(this.mBitmapMemoryCache, this.mCacheKeyFactory, producer);
    }
    
    public PostprocessorProducer newPostprocessorProducer(final Producer<CloseableReference<CloseableImage>> producer) {
        return new PostprocessorProducer(producer, this.mPlatformBitmapFactory, this.mExecutorSupplier.forBackgroundTasks());
    }
    
    public ResizeAndRotateProducer newResizeAndRotateProducer(final Producer<EncodedImage> producer) {
        return new ResizeAndRotateProducer(this.mExecutorSupplier.forBackgroundTasks(), this.mPooledByteBufferFactory, producer);
    }
    
    public <T> ThrottlingProducer<T> newThrottlingProducer(final int n, final Producer<T> producer) {
        return new ThrottlingProducer<T>(n, this.mExecutorSupplier.forLightweightBackgroundTasks(), producer);
    }
    
    public ThumbnailBranchProducer newThumbnailBranchProducer(final ThumbnailProducer<EncodedImage>[] array) {
        return new ThumbnailBranchProducer(array);
    }
    
    public WebpTranscodeProducer newWebpTranscodeProducer(final Producer<EncodedImage> producer) {
        return new WebpTranscodeProducer(this.mExecutorSupplier.forBackgroundTasks(), this.mPooledByteBufferFactory, producer);
    }
}
