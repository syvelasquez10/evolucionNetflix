// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.resfetcher.volley;

import com.android.volley.NetworkResponse;
import com.android.volley.RetryPolicy;
import com.android.volley.DefaultRetryPolicy;
import android.graphics.Bitmap$Config;
import android.graphics.Bitmap;
import com.android.volley.Response;
import com.android.volley.Request;

public class ImageRequest extends com.android.volley.toolbox.ImageRequest
{
    private static final float IMAGE_BACKOFF_MULT = 2.0f;
    private static final int IMAGE_MAX_RETRIES = 2;
    private static final boolean LOG_VERBOSE = false;
    private static final String TAG = "nf_ImageRequest";
    private final long mMinimumCacheTtl;
    private final Priority mPriority;
    
    public ImageRequest(final String s, final Response.Listener<Bitmap> listener, final int n, final int n2, final Bitmap$Config bitmap$Config, final Response.ErrorListener errorListener, final Priority mPriority, final int n3, final long mMinimumCacheTtl) {
        super(s, listener, n, n2, bitmap$Config, errorListener);
        if (mPriority != null) {
            this.mPriority = mPriority;
        }
        else {
            this.mPriority = Priority.LOW;
        }
        if (n3 > 0) {
            this.setRetryPolicy(new DefaultRetryPolicy(n3, 2, 2.0f));
        }
        this.mMinimumCacheTtl = mMinimumCacheTtl;
    }
    
    @Override
    public String getCacheKey() {
        return ImageLoader.getCacheKey(this.getUrl());
    }
    
    @Override
    public Priority getPriority() {
        return this.mPriority;
    }
    
    @Override
    protected Response<Bitmap> parseNetworkResponse(final NetworkResponse networkResponse) {
        final Response<Bitmap> networkResponse2 = super.parseNetworkResponse(networkResponse);
        if (networkResponse2 != null && networkResponse2.error == null && networkResponse2.cacheEntry != null && this.mMinimumCacheTtl > 0L) {
            final long n = System.currentTimeMillis() + this.mMinimumCacheTtl;
            if (n > networkResponse2.cacheEntry.ttl) {
                networkResponse2.cacheEntry.ttl = n;
                networkResponse2.cacheEntry.softTtl = n;
                return networkResponse2;
            }
        }
        return networkResponse2;
    }
}
