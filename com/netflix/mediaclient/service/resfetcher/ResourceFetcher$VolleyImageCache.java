// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.resfetcher;

import com.netflix.mediaclient.service.resfetcher.volley.ImageLoader$ImageCache;
import com.netflix.mediaclient.util.gfx.BitmapLruCache;

class ResourceFetcher$VolleyImageCache extends BitmapLruCache implements ImageLoader$ImageCache
{
    public ResourceFetcher$VolleyImageCache(final int n) {
        super(n);
    }
}
