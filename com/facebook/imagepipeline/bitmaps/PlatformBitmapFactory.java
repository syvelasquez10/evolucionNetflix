// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.bitmaps;

import android.graphics.Bitmap;
import com.facebook.common.references.CloseableReference;
import android.graphics.Bitmap$Config;

public abstract class PlatformBitmapFactory
{
    public abstract CloseableReference<Bitmap> createBitmap(final int p0, final int p1, final Bitmap$Config p2);
}
