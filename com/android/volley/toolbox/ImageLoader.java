// 
// Decompiled by Procyon v0.5.30
// 

package com.android.volley.toolbox;

import java.util.LinkedList;
import com.android.volley.Request;
import com.android.volley.Response$ErrorListener;
import com.android.volley.Response$Listener;
import android.graphics.Bitmap$Config;
import android.widget.ImageView;
import com.android.volley.VolleyError;
import android.graphics.Bitmap;
import android.os.Looper;
import com.android.volley.RequestQueue;
import android.os.Handler;
import java.util.HashMap;

public class ImageLoader
{
    private int mBatchResponseDelayMs;
    private final HashMap<String, ImageLoader$BatchedImageRequest> mBatchedResponses;
    private final ImageLoader$ImageCache mCache;
    private final Handler mHandler;
    private final HashMap<String, ImageLoader$BatchedImageRequest> mInFlightRequests;
    private final RequestQueue mRequestQueue;
    private Runnable mRunnable;
    
    public ImageLoader(final RequestQueue mRequestQueue, final ImageLoader$ImageCache mCache) {
        this.mBatchResponseDelayMs = 100;
        this.mInFlightRequests = new HashMap<String, ImageLoader$BatchedImageRequest>();
        this.mBatchedResponses = new HashMap<String, ImageLoader$BatchedImageRequest>();
        this.mHandler = new Handler(Looper.getMainLooper());
        this.mRequestQueue = mRequestQueue;
        this.mCache = mCache;
    }
    
    private void batchResponse(final String s, final ImageLoader$BatchedImageRequest imageLoader$BatchedImageRequest) {
        this.mBatchedResponses.put(s, imageLoader$BatchedImageRequest);
        if (this.mRunnable == null) {
            this.mRunnable = new ImageLoader$4(this);
            this.mHandler.postDelayed(this.mRunnable, (long)this.mBatchResponseDelayMs);
        }
    }
    
    private static String getCacheKey(final String s, final int n, final int n2) {
        return new StringBuilder(s.length() + 12).append("#W").append(n).append("#H").append(n2).append(s).toString();
    }
    
    public static ImageLoader$ImageListener getImageListener(final ImageView imageView, final int n, final int n2) {
        return new ImageLoader$1(n2, imageView, n);
    }
    
    private void onGetImageError(final String s, final VolleyError error) {
        final ImageLoader$BatchedImageRequest imageLoader$BatchedImageRequest = this.mInFlightRequests.remove(s);
        imageLoader$BatchedImageRequest.setError(error);
        if (imageLoader$BatchedImageRequest != null) {
            this.batchResponse(s, imageLoader$BatchedImageRequest);
        }
    }
    
    private void onGetImageSuccess(final String s, final Bitmap bitmap) {
        this.mCache.putBitmap(s, bitmap);
        final ImageLoader$BatchedImageRequest imageLoader$BatchedImageRequest = this.mInFlightRequests.remove(s);
        if (imageLoader$BatchedImageRequest != null) {
            imageLoader$BatchedImageRequest.mResponseBitmap = bitmap;
            this.batchResponse(s, imageLoader$BatchedImageRequest);
        }
    }
    
    private void throwIfNotOnMainThread() {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            throw new IllegalStateException("ImageLoader must be invoked from the main thread.");
        }
    }
    
    public ImageLoader$ImageContainer get(final String s, final ImageLoader$ImageListener imageLoader$ImageListener) {
        return this.get(s, imageLoader$ImageListener, 0, 0);
    }
    
    public ImageLoader$ImageContainer get(final String s, final ImageLoader$ImageListener imageLoader$ImageListener, final int n, final int n2) {
        this.throwIfNotOnMainThread();
        final String cacheKey = getCacheKey(s, n, n2);
        final Bitmap bitmap = this.mCache.getBitmap(cacheKey);
        if (bitmap != null) {
            final ImageLoader$ImageContainer imageLoader$ImageContainer = new ImageLoader$ImageContainer(this, bitmap, s, null, null);
            imageLoader$ImageListener.onResponse(imageLoader$ImageContainer, true);
            return imageLoader$ImageContainer;
        }
        final ImageLoader$ImageContainer imageLoader$ImageContainer2 = new ImageLoader$ImageContainer(this, null, s, cacheKey, imageLoader$ImageListener);
        imageLoader$ImageListener.onResponse(imageLoader$ImageContainer2, true);
        final ImageLoader$BatchedImageRequest imageLoader$BatchedImageRequest = this.mInFlightRequests.get(cacheKey);
        if (imageLoader$BatchedImageRequest != null) {
            imageLoader$BatchedImageRequest.addContainer(imageLoader$ImageContainer2);
            return imageLoader$ImageContainer2;
        }
        final ImageRequest imageRequest = new ImageRequest(s, new ImageLoader$2(this, cacheKey), n, n2, Bitmap$Config.RGB_565, new ImageLoader$3(this, cacheKey));
        this.mRequestQueue.add(imageRequest);
        this.mInFlightRequests.put(cacheKey, new ImageLoader$BatchedImageRequest(imageRequest, imageLoader$ImageContainer2));
        return imageLoader$ImageContainer2;
    }
    
    public boolean isCached(String cacheKey, final int n, final int n2) {
        this.throwIfNotOnMainThread();
        cacheKey = getCacheKey(cacheKey, n, n2);
        return this.mCache.getBitmap(cacheKey) != null;
    }
    
    public void setBatchedResponseDelay(final int mBatchResponseDelayMs) {
        this.mBatchResponseDelayMs = mBatchResponseDelayMs;
    }
}
