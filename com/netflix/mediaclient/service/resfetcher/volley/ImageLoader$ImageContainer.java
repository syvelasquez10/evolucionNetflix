// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.resfetcher.volley;

import java.util.LinkedList;
import com.netflix.mediaclient.android.widget.AdvancedImageView$ImageLoaderInfo;
import com.netflix.mediaclient.util.gfx.ImageLoader$ImageLoaderListener;
import com.netflix.mediaclient.util.gfx.ImageLoader$StaticImgConfig;
import com.netflix.mediaclient.android.widget.AdvancedImageView;
import android.graphics.drawable.Drawable;
import com.netflix.mediaclient.util.StringUtils;
import com.android.volley.Request;
import com.android.volley.Response$ErrorListener;
import com.android.volley.Response$Listener;
import com.netflix.mediaclient.StatusCode;
import com.netflix.mediaclient.util.log.ApmLogUtils;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.UriUtil;
import android.graphics.Bitmap$Config;
import com.android.volley.Request$Priority;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import com.android.volley.VolleyError;
import android.widget.ImageView;
import android.os.Looper;
import com.netflix.mediaclient.service.ServiceAgent$ConfigurationAgentInterface;
import com.android.volley.RequestQueue;
import android.os.Handler;
import java.util.HashMap;
import com.netflix.mediaclient.servicemgr.ApplicationPerformanceMetricsLogging;
import com.netflix.mediaclient.util.api.Api19Util;
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
    
    @Override
    public String toString() {
        int bitmapByteCount = -1;
        int width;
        if (this.mBitmap == null) {
            width = -1;
        }
        else {
            width = this.mBitmap.getWidth();
        }
        int height;
        if (this.mBitmap == null) {
            height = -1;
        }
        else {
            height = this.mBitmap.getHeight();
        }
        if (this.mBitmap != null) {
            bitmapByteCount = Api19Util.getBitmapByteCount(this.mBitmap);
        }
        return "ImageContainer [mBitmap=" + this.mBitmap + ", x=" + width + ", y=" + height + ", byteCount=" + bitmapByteCount + ", mCacheKey=" + this.mCacheKey + ", mRequestUrl=" + this.mRequestUrl + "]";
    }
}
