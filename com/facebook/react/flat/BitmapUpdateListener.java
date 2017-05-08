// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.flat;

import android.graphics.Bitmap;

interface BitmapUpdateListener
{
    void onBitmapReady(final Bitmap p0);
    
    void onImageLoadEvent(final int p0);
    
    void onSecondaryAttach(final Bitmap p0);
}
