// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.cache;

import com.facebook.cache.common.CacheKey;
import com.facebook.imagepipeline.request.ImageRequest;

public interface CacheKeyFactory
{
    CacheKey getBitmapCacheKey(final ImageRequest p0, final Object p1);
    
    CacheKey getEncodedCacheKey(final ImageRequest p0, final Object p1);
    
    CacheKey getPostprocessedBitmapCacheKey(final ImageRequest p0, final Object p1);
}
