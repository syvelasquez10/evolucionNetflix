// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.resfetcher.volley;

import com.netflix.mediaclient.android.widget.AdvancedImageView;
import com.netflix.mediaclient.util.gfx.ImageLoader$ImageLoaderListener;
import android.graphics.drawable.Drawable;
import com.netflix.mediaclient.util.StringUtils;
import com.android.volley.Request;
import com.android.volley.Response$ErrorListener;
import android.graphics.Bitmap$Config;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.UriUtil;
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
import com.netflix.mediaclient.util.log.ApmLogUtils;
import com.netflix.mediaclient.StatusCode;
import android.graphics.Bitmap;
import com.android.volley.Response$Listener;

class ImageLoader$2 implements Response$Listener<Bitmap>
{
    final /* synthetic */ ImageLoader this$0;
    final /* synthetic */ String val$cacheKey;
    final /* synthetic */ String val$requestUrl;
    
    ImageLoader$2(final ImageLoader this$0, final String val$requestUrl, final String val$cacheKey) {
        this.this$0 = this$0;
        this.val$requestUrl = val$requestUrl;
        this.val$cacheKey = val$cacheKey;
    }
    
    @Override
    public void onResponse(final Bitmap bitmap) {
        ApmLogUtils.reportAssetRequestResult(this.val$requestUrl, StatusCode.OK, this.this$0.mApmLogger);
        this.this$0.onGetImageSuccess(this.val$cacheKey, bitmap);
    }
}
