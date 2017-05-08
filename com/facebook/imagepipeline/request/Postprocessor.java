// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.request;

import com.facebook.common.references.CloseableReference;
import com.facebook.imagepipeline.bitmaps.PlatformBitmapFactory;
import android.graphics.Bitmap;
import com.facebook.cache.common.CacheKey;

public interface Postprocessor
{
    String getName();
    
    CacheKey getPostprocessorCacheKey();
    
    CloseableReference<Bitmap> process(final Bitmap p0, final PlatformBitmapFactory p1);
}
