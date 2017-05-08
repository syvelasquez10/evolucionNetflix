// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.cache;

import com.facebook.imagepipeline.request.Postprocessor;
import com.facebook.cache.common.SimpleCacheKey;
import android.net.Uri;
import com.facebook.cache.common.CacheKey;
import com.facebook.imagepipeline.request.ImageRequest;

public class DefaultCacheKeyFactory implements CacheKeyFactory
{
    private static DefaultCacheKeyFactory sInstance;
    
    static {
        DefaultCacheKeyFactory.sInstance = null;
    }
    
    public static DefaultCacheKeyFactory getInstance() {
        synchronized (DefaultCacheKeyFactory.class) {
            if (DefaultCacheKeyFactory.sInstance == null) {
                DefaultCacheKeyFactory.sInstance = new DefaultCacheKeyFactory();
            }
            return DefaultCacheKeyFactory.sInstance;
        }
    }
    
    @Override
    public CacheKey getBitmapCacheKey(final ImageRequest imageRequest, final Object o) {
        return new BitmapMemoryCacheKey(this.getCacheKeySourceUri(imageRequest.getSourceUri()).toString(), imageRequest.getResizeOptions(), imageRequest.getAutoRotateEnabled(), imageRequest.getImageDecodeOptions(), null, null, o);
    }
    
    protected Uri getCacheKeySourceUri(final Uri uri) {
        return uri;
    }
    
    @Override
    public CacheKey getEncodedCacheKey(final ImageRequest imageRequest, final Object o) {
        return new SimpleCacheKey(this.getCacheKeySourceUri(imageRequest.getSourceUri()).toString());
    }
    
    @Override
    public CacheKey getPostprocessedBitmapCacheKey(final ImageRequest imageRequest, final Object o) {
        String name = null;
        final Postprocessor postprocessor = imageRequest.getPostprocessor();
        CacheKey postprocessorCacheKey;
        if (postprocessor != null) {
            postprocessorCacheKey = postprocessor.getPostprocessorCacheKey();
            name = postprocessor.getClass().getName();
        }
        else {
            postprocessorCacheKey = null;
        }
        return new BitmapMemoryCacheKey(this.getCacheKeySourceUri(imageRequest.getSourceUri()).toString(), imageRequest.getResizeOptions(), imageRequest.getAutoRotateEnabled(), imageRequest.getImageDecodeOptions(), postprocessorCacheKey, name, o);
    }
}
