// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.request;

import com.facebook.imagepipeline.nativecode.Bitmaps;
import com.facebook.common.references.CloseableReference;
import com.facebook.imagepipeline.bitmaps.PlatformBitmapFactory;
import android.graphics.Bitmap;
import com.facebook.cache.common.CacheKey;

public abstract class BasePostprocessor implements Postprocessor
{
    @Override
    public String getName() {
        return "Unknown postprocessor";
    }
    
    @Override
    public CacheKey getPostprocessorCacheKey() {
        return null;
    }
    
    @Override
    public CloseableReference<Bitmap> process(final Bitmap bitmap, PlatformBitmapFactory bitmap2) {
        bitmap2 = (PlatformBitmapFactory)bitmap2.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
        try {
            this.process(((CloseableReference<Bitmap>)bitmap2).get(), bitmap);
            return CloseableReference.cloneOrNull((CloseableReference<Bitmap>)bitmap2);
        }
        finally {
            CloseableReference.closeSafely((CloseableReference<?>)bitmap2);
        }
    }
    
    public void process(final Bitmap bitmap) {
    }
    
    public void process(final Bitmap bitmap, final Bitmap bitmap2) {
        Bitmaps.copyBitmap(bitmap, bitmap2);
        this.process(bitmap);
    }
}
