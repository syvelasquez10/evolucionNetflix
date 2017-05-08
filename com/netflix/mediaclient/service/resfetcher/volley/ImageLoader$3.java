// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.resfetcher.volley;

import android.text.TextUtils;
import com.netflix.mediaclient.service.logging.perf.InteractiveTracker$InteractiveListener;
import com.netflix.mediaclient.android.widget.AdvancedImageView$ImageLoaderInfo;
import com.netflix.mediaclient.util.gfx.ImageLoader$ImageLoaderListener;
import com.netflix.mediaclient.util.gfx.ImageLoader$StaticImgConfig;
import com.netflix.mediaclient.android.widget.AdvancedImageView;
import android.graphics.drawable.Drawable;
import com.netflix.mediaclient.util.StringUtils;
import com.android.volley.Request;
import com.android.volley.Response$Listener;
import com.netflix.mediaclient.StatusCode;
import java.util.Map;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.UriUtil;
import android.graphics.Bitmap$Config;
import com.android.volley.Request$Priority;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import android.graphics.Bitmap;
import android.widget.ImageView;
import android.os.Looper;
import com.netflix.mediaclient.service.logging.perf.InteractiveTracker$TTRTracker;
import com.android.volley.RequestQueue;
import android.os.Handler;
import java.util.HashMap;
import com.netflix.mediaclient.servicemgr.ApplicationPerformanceMetricsLogging;
import com.netflix.mediaclient.service.logging.perf.Sessions;
import com.netflix.mediaclient.service.logging.perf.PerformanceProfiler;
import com.netflix.mediaclient.util.log.ApmLogUtils;
import com.android.volley.VolleyError;
import com.android.volley.Response$ErrorListener;

class ImageLoader$3 implements Response$ErrorListener
{
    final /* synthetic */ ImageLoader this$0;
    final /* synthetic */ String val$cacheKey;
    final /* synthetic */ String val$requestUrl;
    final /* synthetic */ String val$sessionId;
    
    ImageLoader$3(final ImageLoader this$0, final String val$requestUrl, final String val$sessionId, final String val$cacheKey) {
        this.this$0 = this$0;
        this.val$requestUrl = val$requestUrl;
        this.val$sessionId = val$sessionId;
        this.val$cacheKey = val$cacheKey;
    }
    
    @Override
    public void onErrorResponse(final VolleyError volleyError) {
        ApmLogUtils.reportAssetRequestFailure(this.val$requestUrl, volleyError, this.this$0.mApmLogger);
        PerformanceProfiler.getInstance().endSession(Sessions.IMAGE_FETCH, PerformanceProfiler.createFailedMap(), this.val$sessionId);
        this.this$0.onGetImageError(this.val$cacheKey, volleyError);
    }
}
