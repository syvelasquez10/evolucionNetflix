// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.core;

import java.util.Collections;
import java.util.HashSet;
import com.facebook.imagepipeline.decoder.SimpleProgressiveJpegConfig;
import com.facebook.imagepipeline.memory.PoolConfig;
import com.facebook.imagepipeline.producers.HttpUrlConnectionNetworkFetcher;
import com.facebook.common.memory.NoOpMemoryTrimmableRegistry;
import com.facebook.imagepipeline.cache.NoOpImageCacheStatsTracker;
import com.facebook.imagepipeline.cache.DefaultEncodedMemoryCacheParamsSupplier;
import com.facebook.common.internal.Preconditions;
import com.facebook.imagepipeline.cache.DefaultCacheKeyFactory;
import com.facebook.imagepipeline.cache.DefaultBitmapMemoryCacheParamsSupplier;
import android.app.ActivityManager;
import com.facebook.imagepipeline.listener.RequestListener;
import java.util.Set;
import com.facebook.imagepipeline.decoder.ProgressiveJpegConfig;
import com.facebook.imagepipeline.memory.PoolFactory;
import com.facebook.imagepipeline.bitmaps.PlatformBitmapFactory;
import com.facebook.imagepipeline.producers.NetworkFetcher;
import com.facebook.common.memory.MemoryTrimmableRegistry;
import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.imagepipeline.decoder.ImageDecoder;
import com.facebook.imagepipeline.cache.ImageCacheStatsTracker;
import android.content.Context;
import com.facebook.imagepipeline.cache.CacheKeyFactory;
import com.facebook.imagepipeline.cache.MemoryCacheParams;
import com.facebook.common.internal.Supplier;
import android.graphics.Bitmap$Config;
import com.facebook.imagepipeline.animated.factory.AnimatedImageFactory;

public class ImagePipelineConfig
{
    private final AnimatedImageFactory mAnimatedImageFactory;
    private final Bitmap$Config mBitmapConfig;
    private final Supplier<MemoryCacheParams> mBitmapMemoryCacheParamsSupplier;
    private final CacheKeyFactory mCacheKeyFactory;
    private final Context mContext;
    private final boolean mDecodeMemoryFileEnabled;
    private final boolean mDownsampleEnabled;
    private final Supplier<MemoryCacheParams> mEncodedMemoryCacheParamsSupplier;
    private final ExecutorSupplier mExecutorSupplier;
    private final FileCacheFactory mFileCacheFactory;
    private final ImageCacheStatsTracker mImageCacheStatsTracker;
    private final ImageDecoder mImageDecoder;
    private final ImagePipelineExperiments mImagePipelineExperiments;
    private final Supplier<Boolean> mIsPrefetchEnabledSupplier;
    private final DiskCacheConfig mMainDiskCacheConfig;
    private final MemoryTrimmableRegistry mMemoryTrimmableRegistry;
    private final NetworkFetcher mNetworkFetcher;
    private final PlatformBitmapFactory mPlatformBitmapFactory;
    private final PoolFactory mPoolFactory;
    private final ProgressiveJpegConfig mProgressiveJpegConfig;
    private final Set<RequestListener> mRequestListeners;
    private final boolean mResizeAndRotateEnabledForNetwork;
    private final DiskCacheConfig mSmallImageDiskCacheConfig;
    
    private ImagePipelineConfig(final ImagePipelineConfig$Builder imagePipelineConfig$Builder) {
        this.mAnimatedImageFactory = imagePipelineConfig$Builder.mAnimatedImageFactory;
        Supplier access$100;
        if (imagePipelineConfig$Builder.mBitmapMemoryCacheParamsSupplier == null) {
            access$100 = new DefaultBitmapMemoryCacheParamsSupplier((ActivityManager)imagePipelineConfig$Builder.mContext.getSystemService("activity"));
        }
        else {
            access$100 = imagePipelineConfig$Builder.mBitmapMemoryCacheParamsSupplier;
        }
        this.mBitmapMemoryCacheParamsSupplier = (Supplier<MemoryCacheParams>)access$100;
        Bitmap$Config mBitmapConfig;
        if (imagePipelineConfig$Builder.mBitmapConfig == null) {
            mBitmapConfig = Bitmap$Config.ARGB_8888;
        }
        else {
            mBitmapConfig = imagePipelineConfig$Builder.mBitmapConfig;
        }
        this.mBitmapConfig = mBitmapConfig;
        CacheKeyFactory mCacheKeyFactory;
        if (imagePipelineConfig$Builder.mCacheKeyFactory == null) {
            mCacheKeyFactory = DefaultCacheKeyFactory.getInstance();
        }
        else {
            mCacheKeyFactory = imagePipelineConfig$Builder.mCacheKeyFactory;
        }
        this.mCacheKeyFactory = mCacheKeyFactory;
        this.mContext = Preconditions.checkNotNull(imagePipelineConfig$Builder.mContext);
        this.mDecodeMemoryFileEnabled = imagePipelineConfig$Builder.mDecodeMemoryFileEnabled;
        FileCacheFactory access$101;
        if (imagePipelineConfig$Builder.mFileCacheFactory == null) {
            access$101 = new DiskStorageCacheFactory(new DynamicDefaultDiskStorageFactory());
        }
        else {
            access$101 = imagePipelineConfig$Builder.mFileCacheFactory;
        }
        this.mFileCacheFactory = access$101;
        this.mDownsampleEnabled = imagePipelineConfig$Builder.mDownsampleEnabled;
        Supplier access$102;
        if (imagePipelineConfig$Builder.mEncodedMemoryCacheParamsSupplier == null) {
            access$102 = new DefaultEncodedMemoryCacheParamsSupplier();
        }
        else {
            access$102 = imagePipelineConfig$Builder.mEncodedMemoryCacheParamsSupplier;
        }
        this.mEncodedMemoryCacheParamsSupplier = (Supplier<MemoryCacheParams>)access$102;
        ImageCacheStatsTracker mImageCacheStatsTracker;
        if (imagePipelineConfig$Builder.mImageCacheStatsTracker == null) {
            mImageCacheStatsTracker = NoOpImageCacheStatsTracker.getInstance();
        }
        else {
            mImageCacheStatsTracker = imagePipelineConfig$Builder.mImageCacheStatsTracker;
        }
        this.mImageCacheStatsTracker = mImageCacheStatsTracker;
        this.mImageDecoder = imagePipelineConfig$Builder.mImageDecoder;
        Supplier access$103;
        if (imagePipelineConfig$Builder.mIsPrefetchEnabledSupplier == null) {
            access$103 = new ImagePipelineConfig$1(this);
        }
        else {
            access$103 = imagePipelineConfig$Builder.mIsPrefetchEnabledSupplier;
        }
        this.mIsPrefetchEnabledSupplier = (Supplier<Boolean>)access$103;
        DiskCacheConfig mMainDiskCacheConfig;
        if (imagePipelineConfig$Builder.mMainDiskCacheConfig == null) {
            mMainDiskCacheConfig = getDefaultMainDiskCacheConfig(imagePipelineConfig$Builder.mContext);
        }
        else {
            mMainDiskCacheConfig = imagePipelineConfig$Builder.mMainDiskCacheConfig;
        }
        this.mMainDiskCacheConfig = mMainDiskCacheConfig;
        MemoryTrimmableRegistry mMemoryTrimmableRegistry;
        if (imagePipelineConfig$Builder.mMemoryTrimmableRegistry == null) {
            mMemoryTrimmableRegistry = NoOpMemoryTrimmableRegistry.getInstance();
        }
        else {
            mMemoryTrimmableRegistry = imagePipelineConfig$Builder.mMemoryTrimmableRegistry;
        }
        this.mMemoryTrimmableRegistry = mMemoryTrimmableRegistry;
        NetworkFetcher access$104;
        if (imagePipelineConfig$Builder.mNetworkFetcher == null) {
            access$104 = new HttpUrlConnectionNetworkFetcher();
        }
        else {
            access$104 = imagePipelineConfig$Builder.mNetworkFetcher;
        }
        this.mNetworkFetcher = access$104;
        this.mPlatformBitmapFactory = imagePipelineConfig$Builder.mPlatformBitmapFactory;
        PoolFactory access$105;
        if (imagePipelineConfig$Builder.mPoolFactory == null) {
            access$105 = new PoolFactory(PoolConfig.newBuilder().build());
        }
        else {
            access$105 = imagePipelineConfig$Builder.mPoolFactory;
        }
        this.mPoolFactory = access$105;
        ProgressiveJpegConfig access$106;
        if (imagePipelineConfig$Builder.mProgressiveJpegConfig == null) {
            access$106 = new SimpleProgressiveJpegConfig();
        }
        else {
            access$106 = imagePipelineConfig$Builder.mProgressiveJpegConfig;
        }
        this.mProgressiveJpegConfig = access$106;
        Set<RequestListener> access$107;
        if (imagePipelineConfig$Builder.mRequestListeners == null) {
            access$107 = new HashSet<RequestListener>();
        }
        else {
            access$107 = imagePipelineConfig$Builder.mRequestListeners;
        }
        this.mRequestListeners = access$107;
        this.mResizeAndRotateEnabledForNetwork = imagePipelineConfig$Builder.mResizeAndRotateEnabledForNetwork;
        DiskCacheConfig mSmallImageDiskCacheConfig;
        if (imagePipelineConfig$Builder.mSmallImageDiskCacheConfig == null) {
            mSmallImageDiskCacheConfig = this.mMainDiskCacheConfig;
        }
        else {
            mSmallImageDiskCacheConfig = imagePipelineConfig$Builder.mSmallImageDiskCacheConfig;
        }
        this.mSmallImageDiskCacheConfig = mSmallImageDiskCacheConfig;
        final int flexByteArrayPoolMaxNumThreads = this.mPoolFactory.getFlexByteArrayPoolMaxNumThreads();
        ExecutorSupplier access$108;
        if (imagePipelineConfig$Builder.mExecutorSupplier == null) {
            access$108 = new DefaultExecutorSupplier(flexByteArrayPoolMaxNumThreads);
        }
        else {
            access$108 = imagePipelineConfig$Builder.mExecutorSupplier;
        }
        this.mExecutorSupplier = access$108;
        this.mImagePipelineExperiments = imagePipelineConfig$Builder.mExperimentsBuilder.build();
    }
    
    private static DiskCacheConfig getDefaultMainDiskCacheConfig(final Context context) {
        return DiskCacheConfig.newBuilder(context).build();
    }
    
    public static ImagePipelineConfig$Builder newBuilder(final Context context) {
        return new ImagePipelineConfig$Builder(context, null);
    }
    
    public Bitmap$Config getBitmapConfig() {
        return this.mBitmapConfig;
    }
    
    public Supplier<MemoryCacheParams> getBitmapMemoryCacheParamsSupplier() {
        return this.mBitmapMemoryCacheParamsSupplier;
    }
    
    public CacheKeyFactory getCacheKeyFactory() {
        return this.mCacheKeyFactory;
    }
    
    public Context getContext() {
        return this.mContext;
    }
    
    public Supplier<MemoryCacheParams> getEncodedMemoryCacheParamsSupplier() {
        return this.mEncodedMemoryCacheParamsSupplier;
    }
    
    public ExecutorSupplier getExecutorSupplier() {
        return this.mExecutorSupplier;
    }
    
    public ImagePipelineExperiments getExperiments() {
        return this.mImagePipelineExperiments;
    }
    
    public FileCacheFactory getFileCacheFactory() {
        return this.mFileCacheFactory;
    }
    
    public ImageCacheStatsTracker getImageCacheStatsTracker() {
        return this.mImageCacheStatsTracker;
    }
    
    public ImageDecoder getImageDecoder() {
        return this.mImageDecoder;
    }
    
    public Supplier<Boolean> getIsPrefetchEnabledSupplier() {
        return this.mIsPrefetchEnabledSupplier;
    }
    
    public DiskCacheConfig getMainDiskCacheConfig() {
        return this.mMainDiskCacheConfig;
    }
    
    public MemoryTrimmableRegistry getMemoryTrimmableRegistry() {
        return this.mMemoryTrimmableRegistry;
    }
    
    public NetworkFetcher getNetworkFetcher() {
        return this.mNetworkFetcher;
    }
    
    public PoolFactory getPoolFactory() {
        return this.mPoolFactory;
    }
    
    public ProgressiveJpegConfig getProgressiveJpegConfig() {
        return this.mProgressiveJpegConfig;
    }
    
    public Set<RequestListener> getRequestListeners() {
        return Collections.unmodifiableSet((Set<? extends RequestListener>)this.mRequestListeners);
    }
    
    public DiskCacheConfig getSmallImageDiskCacheConfig() {
        return this.mSmallImageDiskCacheConfig;
    }
    
    public boolean isDecodeMemoryFileEnabled() {
        return this.mDecodeMemoryFileEnabled;
    }
    
    public boolean isDownsampleEnabled() {
        return this.mDownsampleEnabled;
    }
    
    public boolean isResizeAndRotateEnabledForNetwork() {
        return this.mResizeAndRotateEnabledForNetwork;
    }
}
