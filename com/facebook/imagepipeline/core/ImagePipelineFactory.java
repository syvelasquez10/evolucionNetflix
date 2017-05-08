// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.core;

import com.facebook.imagepipeline.cache.EncodedMemoryCacheFactory;
import com.facebook.imagepipeline.cache.EncodedCountingMemoryCacheFactory;
import com.facebook.imagepipeline.cache.BitmapMemoryCacheFactory;
import com.facebook.imagepipeline.cache.BitmapCountingMemoryCacheFactory;
import com.facebook.imagepipeline.animated.factory.AnimatedFactoryProvider;
import com.facebook.imagepipeline.animated.factory.AnimatedImageFactory;
import com.facebook.imagepipeline.platform.KitKatPurgeableDecoder;
import com.facebook.imagepipeline.platform.GingerbreadPurgeableDecoder;
import com.facebook.imagepipeline.platform.ArtDecoder;
import android.support.v4.util.Pools$SynchronizedPool;
import com.facebook.imagepipeline.bitmaps.GingerbreadBitmapFactory;
import com.facebook.imagepipeline.bitmaps.HoneycombBitmapFactory;
import com.facebook.imagepipeline.bitmaps.EmptyJpegGenerator;
import com.facebook.imagepipeline.bitmaps.ArtBitmapFactory;
import android.os.Build$VERSION;
import com.facebook.imagepipeline.memory.PoolFactory;
import com.facebook.common.internal.Preconditions;
import com.facebook.imagepipeline.producers.ThreadHandoffProducerQueue;
import com.facebook.imagepipeline.platform.PlatformDecoder;
import com.facebook.imagepipeline.bitmaps.PlatformBitmapFactory;
import com.facebook.cache.disk.FileCache;
import com.facebook.imagepipeline.cache.BufferedDiskCache;
import com.facebook.imagepipeline.decoder.ImageDecoder;
import com.facebook.imagepipeline.memory.PooledByteBuffer;
import com.facebook.imagepipeline.cache.MemoryCache;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.cache.common.CacheKey;
import com.facebook.imagepipeline.cache.CountingMemoryCache;
import com.facebook.imagepipeline.animated.factory.AnimatedFactory;

public class ImagePipelineFactory
{
    private static ImagePipelineFactory sInstance;
    private AnimatedFactory mAnimatedFactory;
    private CountingMemoryCache<CacheKey, CloseableImage> mBitmapCountingMemoryCache;
    private MemoryCache<CacheKey, CloseableImage> mBitmapMemoryCache;
    private final ImagePipelineConfig mConfig;
    private CountingMemoryCache<CacheKey, PooledByteBuffer> mEncodedCountingMemoryCache;
    private MemoryCache<CacheKey, PooledByteBuffer> mEncodedMemoryCache;
    private ImageDecoder mImageDecoder;
    private ImagePipeline mImagePipeline;
    private BufferedDiskCache mMainBufferedDiskCache;
    private FileCache mMainFileCache;
    private PlatformBitmapFactory mPlatformBitmapFactory;
    private PlatformDecoder mPlatformDecoder;
    private ProducerFactory mProducerFactory;
    private ProducerSequenceFactory mProducerSequenceFactory;
    private BufferedDiskCache mSmallImageBufferedDiskCache;
    private FileCache mSmallImageFileCache;
    private final ThreadHandoffProducerQueue mThreadHandoffProducerQueue;
    
    static {
        ImagePipelineFactory.sInstance = null;
    }
    
    public ImagePipelineFactory(final ImagePipelineConfig imagePipelineConfig) {
        this.mConfig = Preconditions.checkNotNull(imagePipelineConfig);
        this.mThreadHandoffProducerQueue = new ThreadHandoffProducerQueue(imagePipelineConfig.getExecutorSupplier().forLightweightBackgroundTasks());
    }
    
    public static PlatformBitmapFactory buildPlatformBitmapFactory(final PoolFactory poolFactory, final PlatformDecoder platformDecoder) {
        if (Build$VERSION.SDK_INT >= 21) {
            return new ArtBitmapFactory(poolFactory.getBitmapPool());
        }
        if (Build$VERSION.SDK_INT >= 11) {
            return new HoneycombBitmapFactory(new EmptyJpegGenerator(poolFactory.getPooledByteBufferFactory()), platformDecoder);
        }
        return new GingerbreadBitmapFactory();
    }
    
    public static PlatformDecoder buildPlatformDecoder(final PoolFactory poolFactory, final boolean b, final boolean b2) {
        if (Build$VERSION.SDK_INT >= 21) {
            final int flexByteArrayPoolMaxNumThreads = poolFactory.getFlexByteArrayPoolMaxNumThreads();
            return new ArtDecoder(poolFactory.getBitmapPool(), flexByteArrayPoolMaxNumThreads, new Pools$SynchronizedPool(flexByteArrayPoolMaxNumThreads));
        }
        if (b && Build$VERSION.SDK_INT < 19) {
            return new GingerbreadPurgeableDecoder(b2);
        }
        return new KitKatPurgeableDecoder(poolFactory.getFlexByteArrayPool());
    }
    
    private ImageDecoder getImageDecoder() {
        if (this.mImageDecoder == null) {
            if (this.mConfig.getImageDecoder() != null) {
                this.mImageDecoder = this.mConfig.getImageDecoder();
            }
            else {
                AnimatedImageFactory animatedImageFactory;
                if (this.getAnimatedFactory() != null) {
                    animatedImageFactory = this.getAnimatedFactory().getAnimatedImageFactory();
                }
                else {
                    animatedImageFactory = null;
                }
                this.mImageDecoder = new ImageDecoder(animatedImageFactory, this.getPlatformDecoder(), this.mConfig.getBitmapConfig());
            }
        }
        return this.mImageDecoder;
    }
    
    public static ImagePipelineFactory getInstance() {
        return Preconditions.checkNotNull(ImagePipelineFactory.sInstance, "ImagePipelineFactory was not initialized!");
    }
    
    private BufferedDiskCache getMainBufferedDiskCache() {
        if (this.mMainBufferedDiskCache == null) {
            this.mMainBufferedDiskCache = new BufferedDiskCache(this.getMainFileCache(), this.mConfig.getPoolFactory().getPooledByteBufferFactory(), this.mConfig.getPoolFactory().getPooledByteStreams(), this.mConfig.getExecutorSupplier().forLocalStorageRead(), this.mConfig.getExecutorSupplier().forLocalStorageWrite(), this.mConfig.getImageCacheStatsTracker());
        }
        return this.mMainBufferedDiskCache;
    }
    
    private ProducerFactory getProducerFactory() {
        if (this.mProducerFactory == null) {
            this.mProducerFactory = new ProducerFactory(this.mConfig.getContext(), this.mConfig.getPoolFactory().getSmallByteArrayPool(), this.getImageDecoder(), this.mConfig.getProgressiveJpegConfig(), this.mConfig.isDownsampleEnabled(), this.mConfig.isResizeAndRotateEnabledForNetwork(), this.mConfig.getExecutorSupplier(), this.mConfig.getPoolFactory().getPooledByteBufferFactory(), this.getBitmapMemoryCache(), this.getEncodedMemoryCache(), this.getMainBufferedDiskCache(), this.getSmallImageBufferedDiskCache(), this.mConfig.getCacheKeyFactory(), this.getPlatformBitmapFactory(), this.mConfig.getExperiments().isDecodeFileDescriptorEnabled(), this.mConfig.getExperiments().getForceSmallCacheThresholdBytes());
        }
        return this.mProducerFactory;
    }
    
    private ProducerSequenceFactory getProducerSequenceFactory() {
        if (this.mProducerSequenceFactory == null) {
            this.mProducerSequenceFactory = new ProducerSequenceFactory(this.getProducerFactory(), this.mConfig.getNetworkFetcher(), this.mConfig.isResizeAndRotateEnabledForNetwork(), this.mConfig.isDownsampleEnabled(), this.mConfig.getExperiments().isWebpSupportEnabled(), this.mThreadHandoffProducerQueue);
        }
        return this.mProducerSequenceFactory;
    }
    
    private BufferedDiskCache getSmallImageBufferedDiskCache() {
        if (this.mSmallImageBufferedDiskCache == null) {
            this.mSmallImageBufferedDiskCache = new BufferedDiskCache(this.getSmallImageFileCache(), this.mConfig.getPoolFactory().getPooledByteBufferFactory(), this.mConfig.getPoolFactory().getPooledByteStreams(), this.mConfig.getExecutorSupplier().forLocalStorageRead(), this.mConfig.getExecutorSupplier().forLocalStorageWrite(), this.mConfig.getImageCacheStatsTracker());
        }
        return this.mSmallImageBufferedDiskCache;
    }
    
    public static void initialize(final ImagePipelineConfig imagePipelineConfig) {
        ImagePipelineFactory.sInstance = new ImagePipelineFactory(imagePipelineConfig);
    }
    
    public AnimatedFactory getAnimatedFactory() {
        if (this.mAnimatedFactory == null) {
            this.mAnimatedFactory = AnimatedFactoryProvider.getAnimatedFactory(this.getPlatformBitmapFactory(), this.mConfig.getExecutorSupplier());
        }
        return this.mAnimatedFactory;
    }
    
    public CountingMemoryCache<CacheKey, CloseableImage> getBitmapCountingMemoryCache() {
        if (this.mBitmapCountingMemoryCache == null) {
            this.mBitmapCountingMemoryCache = BitmapCountingMemoryCacheFactory.get(this.mConfig.getBitmapMemoryCacheParamsSupplier(), this.mConfig.getMemoryTrimmableRegistry());
        }
        return this.mBitmapCountingMemoryCache;
    }
    
    public MemoryCache<CacheKey, CloseableImage> getBitmapMemoryCache() {
        if (this.mBitmapMemoryCache == null) {
            this.mBitmapMemoryCache = BitmapMemoryCacheFactory.get(this.getBitmapCountingMemoryCache(), this.mConfig.getImageCacheStatsTracker());
        }
        return this.mBitmapMemoryCache;
    }
    
    public CountingMemoryCache<CacheKey, PooledByteBuffer> getEncodedCountingMemoryCache() {
        if (this.mEncodedCountingMemoryCache == null) {
            this.mEncodedCountingMemoryCache = EncodedCountingMemoryCacheFactory.get(this.mConfig.getEncodedMemoryCacheParamsSupplier(), this.mConfig.getMemoryTrimmableRegistry());
        }
        return this.mEncodedCountingMemoryCache;
    }
    
    public MemoryCache<CacheKey, PooledByteBuffer> getEncodedMemoryCache() {
        if (this.mEncodedMemoryCache == null) {
            this.mEncodedMemoryCache = EncodedMemoryCacheFactory.get(this.getEncodedCountingMemoryCache(), this.mConfig.getImageCacheStatsTracker());
        }
        return this.mEncodedMemoryCache;
    }
    
    public ImagePipeline getImagePipeline() {
        if (this.mImagePipeline == null) {
            this.mImagePipeline = new ImagePipeline(this.getProducerSequenceFactory(), this.mConfig.getRequestListeners(), this.mConfig.getIsPrefetchEnabledSupplier(), this.getBitmapMemoryCache(), this.getEncodedMemoryCache(), this.getMainBufferedDiskCache(), this.getSmallImageBufferedDiskCache(), this.mConfig.getCacheKeyFactory(), this.mThreadHandoffProducerQueue);
        }
        return this.mImagePipeline;
    }
    
    public FileCache getMainFileCache() {
        if (this.mMainFileCache == null) {
            this.mMainFileCache = this.mConfig.getFileCacheFactory().get(this.mConfig.getMainDiskCacheConfig());
        }
        return this.mMainFileCache;
    }
    
    public PlatformBitmapFactory getPlatformBitmapFactory() {
        if (this.mPlatformBitmapFactory == null) {
            this.mPlatformBitmapFactory = buildPlatformBitmapFactory(this.mConfig.getPoolFactory(), this.getPlatformDecoder());
        }
        return this.mPlatformBitmapFactory;
    }
    
    public PlatformDecoder getPlatformDecoder() {
        if (this.mPlatformDecoder == null) {
            this.mPlatformDecoder = buildPlatformDecoder(this.mConfig.getPoolFactory(), this.mConfig.isDecodeMemoryFileEnabled(), this.mConfig.getExperiments().isWebpSupportEnabled());
        }
        return this.mPlatformDecoder;
    }
    
    public FileCache getSmallImageFileCache() {
        if (this.mSmallImageFileCache == null) {
            this.mSmallImageFileCache = this.mConfig.getFileCacheFactory().get(this.mConfig.getSmallImageDiskCacheConfig());
        }
        return this.mSmallImageFileCache;
    }
}
