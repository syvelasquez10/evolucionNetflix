// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.bitmaps;

import com.facebook.common.references.ResourceReleaser;
import com.facebook.imagepipeline.nativecode.Bitmaps;
import com.facebook.imageutils.BitmapUtil;
import android.graphics.Bitmap;
import com.facebook.common.references.CloseableReference;
import android.graphics.Bitmap$Config;
import com.facebook.imagepipeline.memory.BitmapPool;
import android.annotation.TargetApi;

@TargetApi(21)
public class ArtBitmapFactory extends PlatformBitmapFactory
{
    private final BitmapPool mBitmapPool;
    
    public ArtBitmapFactory(final BitmapPool mBitmapPool) {
        this.mBitmapPool = mBitmapPool;
    }
    
    @Override
    public CloseableReference<Bitmap> createBitmap(final int n, final int n2, final Bitmap$Config bitmap$Config) {
        final Bitmap bitmap = this.mBitmapPool.get(BitmapUtil.getSizeInByteForBitmap(n, n2, bitmap$Config));
        Bitmaps.reconfigureBitmap(bitmap, n, n2, bitmap$Config);
        return CloseableReference.of(bitmap, this.mBitmapPool);
    }
}
