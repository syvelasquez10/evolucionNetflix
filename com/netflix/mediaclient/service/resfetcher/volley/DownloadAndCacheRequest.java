// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.resfetcher.volley;

import com.android.volley.VolleyError;
import com.netflix.mediaclient.Log;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.Response;
import com.android.volley.NetworkResponse;
import com.android.volley.Request$Priority;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.service.resfetcher.VolleyCacheWrapper$CachedResourceMetaData;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.android.volley.RetryPolicy;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response$ErrorListener;
import com.netflix.mediaclient.service.resfetcher.VolleyCacheWrapper;
import com.netflix.mediaclient.service.resfetcher.ResourceFetcherCallback;
import com.android.volley.Request;

public class DownloadAndCacheRequest extends Request<Integer>
{
    private static final float RESOURCE_BACKOFF_MULT = 2.0f;
    private static final int RESOURCE_MAX_RETRIES = 2;
    public static final String TAG = "DownloadAndCacheRequest";
    private final ResourceFetcherCallback mCallback;
    private VolleyCacheWrapper mVolleyCacheWrapper;
    
    public DownloadAndCacheRequest(final String s, final ResourceFetcherCallback mCallback, final Response$ErrorListener response$ErrorListener, final int n, final VolleyCacheWrapper mVolleyCacheWrapper) {
        super(0, s, response$ErrorListener);
        this.mCallback = mCallback;
        this.mVolleyCacheWrapper = mVolleyCacheWrapper;
        this.setShouldCache(true);
        this.setRetryPolicy(new DefaultRetryPolicy(n, 2, 2.0f));
    }
    
    @Override
    protected void deliverResponse(final Integer n) {
        final VolleyCacheWrapper$CachedResourceMetaData entryMetaData = this.mVolleyCacheWrapper.getEntryMetaData(this.getCacheKey());
        if (entryMetaData != null && this.mCallback != null) {
            this.mCallback.onResourceCached(this.getUrl(), entryMetaData.getLocalPath(), entryMetaData.getByteOffset(), entryMetaData.getByteLength(), CommonStatus.OK);
        }
    }
    
    @Override
    public String getCacheKey() {
        return StringUtils.getPathFromUri(this.getUrl());
    }
    
    @Override
    public Request$Priority getPriority() {
        return Request$Priority.NORMAL;
    }
    
    @Override
    protected Response<Integer> parseNetworkResponse(final NetworkResponse networkResponse) {
        if (HttpHeaderParser.parseCacheHeaders(networkResponse) == null) {
            Log.e("DownloadAndCacheRequest", "Request to cache response is not allowed because the response has no-cache param in header");
            return Response.error(new VolleyError("Request to cache response is not allowed because the response has no-cache param in header"));
        }
        return Response.success(networkResponse.data.length, HttpHeaderParser.parseCacheHeaders(networkResponse));
    }
}
