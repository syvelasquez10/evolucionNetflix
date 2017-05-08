// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.resfetcher.volley;

import android.text.TextUtils;
import com.netflix.mediaclient.android.widget.AdvancedImageView$ImageLoaderInfo;
import com.netflix.mediaclient.util.gfx.ImageLoader$ImageLoaderListener;
import com.netflix.mediaclient.util.gfx.ImageLoader$StaticImgConfig;
import com.netflix.mediaclient.android.widget.AdvancedImageView;
import android.graphics.drawable.Drawable;
import com.netflix.mediaclient.util.StringUtils;
import com.android.volley.Response$ErrorListener;
import com.android.volley.Response$Listener;
import com.netflix.mediaclient.StatusCode;
import com.netflix.mediaclient.util.log.ApmLogUtils;
import java.util.Map;
import com.netflix.mediaclient.service.logging.perf.Sessions;
import com.netflix.mediaclient.service.logging.perf.PerformanceProfiler;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.UriUtil;
import android.graphics.Bitmap$Config;
import com.android.volley.Request$Priority;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import android.widget.ImageView;
import android.os.Looper;
import com.netflix.mediaclient.service.logging.perf.InteractiveTracker$TTRTracker;
import com.android.volley.RequestQueue;
import android.os.Handler;
import java.util.HashMap;
import com.netflix.mediaclient.servicemgr.ApplicationPerformanceMetricsLogging;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import java.util.LinkedList;
import com.netflix.mediaclient.util.api.Api19Util;
import android.graphics.Bitmap;
import java.util.Iterator;

class ImageLoader$4 implements Runnable
{
    final /* synthetic */ ImageLoader this$0;
    
    ImageLoader$4(final ImageLoader this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        for (final ImageLoader$BatchedImageRequest imageLoader$BatchedImageRequest : this.this$0.mBatchedResponses.values()) {
            for (final ImageLoader$ImageContainer imageLoader$ImageContainer : imageLoader$BatchedImageRequest.mContainers) {
                if (imageLoader$ImageContainer.mListener != null) {
                    if (imageLoader$BatchedImageRequest.getError() == null) {
                        imageLoader$ImageContainer.mBitmap = imageLoader$BatchedImageRequest.mResponseBitmap;
                        imageLoader$ImageContainer.mListener.onResponse(imageLoader$ImageContainer, ImageLoader$Type.NETWORK);
                    }
                    else {
                        imageLoader$ImageContainer.mListener.onErrorResponse(imageLoader$BatchedImageRequest.getError());
                    }
                }
            }
        }
        this.this$0.mBatchedResponses.clear();
        this.this$0.mRunnable = null;
    }
}
