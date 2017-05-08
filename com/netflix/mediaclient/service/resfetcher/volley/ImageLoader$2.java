// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.resfetcher.volley;

import android.graphics.Bitmap;
import com.android.volley.VolleyError;
import com.netflix.mediaclient.util.gfx.ImageLoader$ImageLoaderListener;

class ImageLoader$2 implements ImageLoader$ImageListener
{
    final /* synthetic */ ImageLoader this$0;
    final /* synthetic */ ImageLoader$ImageLoaderListener val$listener;
    
    ImageLoader$2(final ImageLoader this$0, final ImageLoader$ImageLoaderListener val$listener) {
        this.this$0 = this$0;
        this.val$listener = val$listener;
    }
    
    @Override
    public void onErrorResponse(final VolleyError volleyError) {
        final ImageLoader$ImageLoaderListener val$listener = this.val$listener;
        String message;
        if (volleyError == null) {
            message = null;
        }
        else {
            message = volleyError.getMessage();
        }
        val$listener.onErrorResponse(message);
    }
    
    @Override
    public void onResponse(final ImageLoader$ImageContainer imageLoader$ImageContainer, final boolean b) {
        if (imageLoader$ImageContainer == null) {
            this.val$listener.onResponse(null, null);
            return;
        }
        this.val$listener.onResponse(imageLoader$ImageContainer.getBitmap(), imageLoader$ImageContainer.getRequestUrl());
    }
}
