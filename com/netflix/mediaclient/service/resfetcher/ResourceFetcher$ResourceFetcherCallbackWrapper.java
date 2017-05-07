// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.resfetcher;

import com.netflix.mediaclient.service.resfetcher.volley.PrefetchResourceRequest;
import com.netflix.mediaclient.service.webclient.WebClient;
import com.android.volley.Request;
import com.android.volley.Response$ErrorListener;
import com.netflix.mediaclient.service.resfetcher.volley.FileDownloadRequest;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.service.webclient.WebClientInitParameters;
import com.netflix.mediaclient.service.webclient.NetflixWebClientInitParameters;
import com.android.volley.Network;
import com.android.volley.Cache;
import com.android.volley.toolbox.BasicNetwork;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.util.gfx.BitmapLruCache;
import com.netflix.mediaclient.service.configuration.ConfigurationAgent;
import com.netflix.mediaclient.NetflixApplication;
import com.netflix.mediaclient.service.resfetcher.volley.ImageLoader$ImageCache;
import com.netflix.mediaclient.servicemgr.ApplicationPerformanceMetricsLogging;
import com.netflix.mediaclient.service.ServiceAgent$ConfigurationAgentInterface;
import android.content.Context;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.HttpStack;
import com.netflix.mediaclient.Log;
import com.android.volley.toolbox.DiskBasedCache;
import com.netflix.mediaclient.service.NetflixService;
import com.netflix.mediaclient.service.webclient.volley.FalkorVolleyWebClient;
import com.android.volley.RequestQueue;
import com.netflix.mediaclient.service.resfetcher.volley.ImageLoader;
import java.io.File;
import com.netflix.mediaclient.service.ServiceAgent;
import com.netflix.mediaclient.util.log.ApmLogUtils;
import com.netflix.mediaclient.android.app.Status;

class ResourceFetcher$ResourceFetcherCallbackWrapper implements ResourceFetcherCallback
{
    private final ResourceFetcherCallback mCallback;
    final /* synthetic */ ResourceFetcher this$0;
    
    private ResourceFetcher$ResourceFetcherCallbackWrapper(final ResourceFetcher this$0, final ResourceFetcherCallback mCallback) {
        this.this$0 = this$0;
        if (mCallback == null) {
            throw new IllegalArgumentException("Callback can not be null");
        }
        this.mCallback = mCallback;
    }
    
    @Override
    public void onResourceFetched(final String s, final String s2, final Status status) {
        ApmLogUtils.reportAssetRequestResult(s, status.getStatusCode(), this.this$0.getService().getClientLogging().getApplicationPerformanceMetricsLogging());
        this.mCallback.onResourceFetched(s, s2, status);
    }
    
    @Override
    public void onResourcePrefetched(final String s, final int n, final Status status) {
        ApmLogUtils.reportAssetRequestResult(s, status.getStatusCode(), this.this$0.getService().getClientLogging().getApplicationPerformanceMetricsLogging());
        this.mCallback.onResourcePrefetched(s, n, status);
    }
}
