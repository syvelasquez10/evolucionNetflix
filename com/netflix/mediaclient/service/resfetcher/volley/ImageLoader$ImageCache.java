// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.resfetcher.volley;

import android.graphics.Bitmap;

public interface ImageLoader$ImageCache
{
    Bitmap getBitmap(final String p0);
    
    void putBitmap(final String p0, final Bitmap p1);
}
