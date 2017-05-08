// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.resfetcher.volley;

import com.netflix.mediaclient.android.widget.AdvancedImageView$ImageLoaderInfo;
import com.netflix.mediaclient.util.gfx.ImageLoader$ImageLoaderListener;
import com.netflix.mediaclient.util.gfx.ImageLoader$StaticImgConfig;
import com.netflix.mediaclient.android.widget.AdvancedImageView;
import android.graphics.drawable.Drawable;
import com.netflix.mediaclient.util.StringUtils;
import com.android.volley.Request;
import com.android.volley.Response$ErrorListener;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.UriUtil;
import android.graphics.Bitmap$Config;
import com.android.volley.Request$Priority;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import com.android.volley.VolleyError;
import android.widget.ImageView;
import android.os.Looper;
import com.netflix.mediaclient.service.logging.perf.InteractiveTracker$TTRTracker;
import com.android.volley.RequestQueue;
import android.os.Handler;
import java.util.HashMap;
import com.netflix.mediaclient.servicemgr.ApplicationPerformanceMetricsLogging;
import java.util.Map;
import com.netflix.mediaclient.service.logging.perf.Sessions;
import com.netflix.mediaclient.service.logging.perf.PerformanceProfiler;
import com.netflix.mediaclient.util.log.ApmLogUtils;
import com.netflix.mediaclient.StatusCode;
import android.graphics.Bitmap;
import com.android.volley.Response$Listener;

class ImageLoader$2 implements Response$Listener<Bitmap>
{
    final /* synthetic */ ImageLoader this$0;
    final /* synthetic */ String val$cacheKey;
    final /* synthetic */ String val$requestUrl;
    final /* synthetic */ String val$sessionId;
    
    ImageLoader$2(final ImageLoader this$0, final String val$requestUrl, final String val$sessionId, final String val$cacheKey) {
        this.this$0 = this$0;
        this.val$requestUrl = val$requestUrl;
        this.val$sessionId = val$sessionId;
        this.val$cacheKey = val$cacheKey;
    }
    
    @Override
    public void onResponse(final Bitmap bitmap) {
        ApmLogUtils.reportAssetRequestResult(this.val$requestUrl, StatusCode.OK, this.this$0.mApmLogger);
        PerformanceProfiler.getInstance().endSession(Sessions.IMAGE_FETCH, null, this.val$sessionId);
        this.this$0.onGetImageSuccess(this.val$cacheKey, bitmap);
    }
}
