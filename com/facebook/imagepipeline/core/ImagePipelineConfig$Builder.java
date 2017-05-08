// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.core;

import com.facebook.common.internal.Preconditions;
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

public class ImagePipelineConfig$Builder
{
    private AnimatedImageFactory mAnimatedImageFactory;
    private Bitmap$Config mBitmapConfig;
    private Supplier<MemoryCacheParams> mBitmapMemoryCacheParamsSupplier;
    private CacheKeyFactory mCacheKeyFactory;
    private final Context mContext;
    private boolean mDecodeMemoryFileEnabled;
    private boolean mDownsampleEnabled;
    private Supplier<MemoryCacheParams> mEncodedMemoryCacheParamsSupplier;
    private ExecutorSupplier mExecutorSupplier;
    private final ImagePipelineExperiments$Builder mExperimentsBuilder;
    private FileCacheFactory mFileCacheFactory;
    private ImageCacheStatsTracker mImageCacheStatsTracker;
    private ImageDecoder mImageDecoder;
    private Supplier<Boolean> mIsPrefetchEnabledSupplier;
    private DiskCacheConfig mMainDiskCacheConfig;
    private MemoryTrimmableRegistry mMemoryTrimmableRegistry;
    private NetworkFetcher mNetworkFetcher;
    private PlatformBitmapFactory mPlatformBitmapFactory;
    private PoolFactory mPoolFactory;
    private ProgressiveJpegConfig mProgressiveJpegConfig;
    private Set<RequestListener> mRequestListeners;
    private boolean mResizeAndRotateEnabledForNetwork;
    private DiskCacheConfig mSmallImageDiskCacheConfig;
    
    private ImagePipelineConfig$Builder(final Context context) {
        this.mDownsampleEnabled = false;
        this.mResizeAndRotateEnabledForNetwork = true;
        this.mExperimentsBuilder = new ImagePipelineExperiments$Builder(this);
        this.mContext = Preconditions.checkNotNull(context);
    }
    
    public ImagePipelineConfig build() {
        return new ImagePipelineConfig(this, null);
    }
    
    public boolean isDownsampleEnabled() {
        return this.mDownsampleEnabled;
    }
    
    public ImagePipelineConfig$Builder setDownsampleEnabled(final boolean mDownsampleEnabled) {
        this.mDownsampleEnabled = mDownsampleEnabled;
        return this;
    }
    
    public ImagePipelineConfig$Builder setNetworkFetcher(final NetworkFetcher mNetworkFetcher) {
        this.mNetworkFetcher = mNetworkFetcher;
        return this;
    }
    
    public ImagePipelineConfig$Builder setRequestListeners(final Set<RequestListener> mRequestListeners) {
        this.mRequestListeners = mRequestListeners;
        return this;
    }
}
