// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.bitmaps;

import com.facebook.common.references.ResourceReleaser;
import android.graphics.Bitmap;
import com.facebook.common.references.CloseableReference;
import android.graphics.Bitmap$Config;

public class GingerbreadBitmapFactory extends PlatformBitmapFactory
{
    @Override
    public CloseableReference<Bitmap> createBitmap(final int n, final int n2, final Bitmap$Config bitmap$Config) {
        return CloseableReference.of(Bitmap.createBitmap(n, n2, bitmap$Config), SimpleBitmapReleaser.getInstance());
    }
}
