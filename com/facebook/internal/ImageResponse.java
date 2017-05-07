// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.internal;

import android.graphics.Bitmap;

public class ImageResponse
{
    private Bitmap bitmap;
    private Exception error;
    private boolean isCachedRedirect;
    private ImageRequest request;
    
    ImageResponse(final ImageRequest request, final Exception error, final boolean isCachedRedirect, final Bitmap bitmap) {
        this.request = request;
        this.error = error;
        this.bitmap = bitmap;
        this.isCachedRedirect = isCachedRedirect;
    }
    
    public Bitmap getBitmap() {
        return this.bitmap;
    }
    
    public Exception getError() {
        return this.error;
    }
    
    public ImageRequest getRequest() {
        return this.request;
    }
    
    public boolean isCachedRedirect() {
        return this.isCachedRedirect;
    }
}
