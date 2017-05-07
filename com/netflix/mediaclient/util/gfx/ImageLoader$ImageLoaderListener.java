// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util.gfx;

import android.graphics.Bitmap;

public interface ImageLoader$ImageLoaderListener
{
    void onErrorResponse(final String p0);
    
    void onResponse(final Bitmap p0, final String p1);
}
