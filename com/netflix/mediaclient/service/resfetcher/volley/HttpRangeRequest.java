// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.resfetcher.volley;

import java.util.Iterator;
import com.android.volley.VolleyError;
import com.android.volley.Cache$Entry;
import com.netflix.mediaclient.Log;
import com.android.volley.Response;
import com.android.volley.NetworkResponse;
import java.util.HashMap;
import java.util.Map;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.android.volley.RetryPolicy;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response$ErrorListener;
import com.netflix.mediaclient.service.resfetcher.ResourceFetcherCallback;
import com.android.volley.Request;

public class HttpRangeRequest extends Request<byte[]>
{
    public static final String HEADER_CONTENT_LENGTH = "Content-Length";
    public static final String HEADER_CONTENT_RANGE = "Content-Range";
    public static final String HEADER_CONTENT_TYPE = "Content-Type";
    private static final float IMAGE_BACKOFF_MULT = 2.0f;
    private static final int IMAGE_MAX_RETRIES = 2;
    private static final String TAG = "nf_service_httprangerequest";
    private ResourceFetcherCallback mCallback;
    private long mSize;
    private long mStartByte;
    
    public HttpRangeRequest(final String s, final long mStartByte, final long mSize, final ResourceFetcherCallback mCallback, final Response$ErrorListener response$ErrorListener, final int n) {
        super(0, s, response$ErrorListener);
        this.mCallback = mCallback;
        this.mSize = mSize;
        this.mStartByte = mStartByte;
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
    public Map<String, String> getHeaders() {
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("Range", "bytes=" + this.mStartByte + "-" + (this.mStartByte + this.mSize - 1L));
        return hashMap;
    }
    
    @Override
    protected Response<byte[]> parseNetworkResponse(final NetworkResponse networkResponse) {
        final String url = this.getUrl();
        if (Log.isLoggable()) {
            Log.d("nf_service_httprangerequest", "Received response from url: " + url);
            for (final String s : networkResponse.headers.keySet()) {
                Log.d("nf_service_httprangerequest", "Response header " + s + ": " + networkResponse.headers.get(s));
            }
        }
        if (networkResponse.statusCode == 206) {
            Log.d("nf_service_httprangerequest", "Received partial content as expected");
            return Response.success(networkResponse.data, null);
        }
        if (Log.isLoggable()) {
            Log.e("nf_service_httprangerequest", "Received invalid status " + networkResponse.statusCode);
        }
        return Response.error(new VolleyError("Received invalid status " + networkResponse.statusCode));
    }
}
