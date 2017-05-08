// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.resfetcher.volley;

import com.android.volley.Cache$Entry;
import com.netflix.mediaclient.Log;
import com.android.volley.Response;
import com.android.volley.NetworkResponse;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.android.volley.RetryPolicy;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response$ErrorListener;
import com.android.volley.Request$Priority;
import com.netflix.mediaclient.service.resfetcher.ResourceFetcherCallback;
import com.android.volley.Request;

public class RawFileDownloadRequest extends Request<byte[]>
{
    private static final float IMAGE_BACKOFF_MULT = 2.0f;
    private static final int IMAGE_MAX_RETRIES = 2;
    private static final String TAG = "nf_service_rawfiledownloadrequest";
    private ResourceFetcherCallback mCallback;
    private Request$Priority mPriority;
    
    public RawFileDownloadRequest(final String s, final ResourceFetcherCallback mCallback, final Response$ErrorListener response$ErrorListener, final int n, final Request$Priority mPriority) {
        super(0, s, response$ErrorListener);
        this.mCallback = mCallback;
        this.mPriority = mPriority;
        this.setShouldCache(false);
        this.setRetryPolicy(new DefaultRetryPolicy(n, 2, 2.0f));
    }
    
    @Override
    protected void deliverResponse(final byte[] array) {
        if (this.mCallback != null) {
            this.mCallback.onResourceRawFetched(this.getUrl(), array, CommonStatus.OK);
        }
    }
    
    @Override
    public Request$Priority getPriority() {
        return this.mPriority;
    }
    
    @Override
    protected Response<byte[]> parseNetworkResponse(final NetworkResponse networkResponse) {
        final String url = this.getUrl();
        if (Log.isLoggable()) {
            Log.d("nf_service_rawfiledownloadrequest", "Received response from url: " + url);
        }
        return Response.success(networkResponse.data, null);
    }
}
