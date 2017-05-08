// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.image;

import android.graphics.Bitmap;

public abstract class CloseableBitmap extends CloseableImage
{
    public abstract Bitmap getUnderlyingBitmap();
}
