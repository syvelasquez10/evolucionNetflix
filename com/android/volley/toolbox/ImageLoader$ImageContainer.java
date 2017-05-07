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
import android.os.Looper;
import com.android.volley.RequestQueue;
import android.os.Handler;
import java.util.HashMap;
import android.graphics.Bitmap;

public class ImageLoader$ImageContainer
{
    private Bitmap mBitmap;
    private final String mCacheKey;
    private final ImageLoader$ImageListener mListener;
    private final String mRequestUrl;
    final /* synthetic */ ImageLoader this$0;
    
    public ImageLoader$ImageContainer(final ImageLoader this$0, final Bitmap mBitmap, final String mRequestUrl, final String mCacheKey, final ImageLoader$ImageListener mListener) {
        this.this$0 = this$0;
        this.mBitmap = mBitmap;
        this.mRequestUrl = mRequestUrl;
        this.mCacheKey = mCacheKey;
        this.mListener = mListener;
    }
    
    public void cancelRequest() {
        if (this.mListener != null) {
            final ImageLoader$BatchedImageRequest imageLoader$BatchedImageRequest = this.this$0.mInFlightRequests.get(this.mCacheKey);
            if (imageLoader$BatchedImageRequest != null) {
                if (imageLoader$BatchedImageRequest.removeContainerAndCancelIfNecessary(this)) {
                    this.this$0.mInFlightRequests.remove(this.mCacheKey);
                }
            }
            else {
                final ImageLoader$BatchedImageRequest imageLoader$BatchedImageRequest2 = this.this$0.mBatchedResponses.get(this.mCacheKey);
                if (imageLoader$BatchedImageRequest2 != null) {
                    imageLoader$BatchedImageRequest2.removeContainerAndCancelIfNecessary(this);
                    if (imageLoader$BatchedImageRequest2.mContainers.size() == 0) {
                        this.this$0.mBatchedResponses.remove(this.mCacheKey);
                    }
                }
            }
        }
    }
    
    public Bitmap getBitmap() {
        return this.mBitmap;
    }
    
    public String getRequestUrl() {
        return this.mRequestUrl;
    }
}
