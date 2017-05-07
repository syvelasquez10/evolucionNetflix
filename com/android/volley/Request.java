// 
// Decompiled by Procyon v0.5.30
// 

package com.android.volley;

import java.util.Collections;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import java.util.Iterator;
import java.net.URLEncoder;
import java.util.Map;
import java.net.MalformedURLException;
import java.net.URL;
import android.net.Uri;
import android.text.TextUtils;

public abstract class Request<T> implements Comparable<Request<T>>
{
    private static final String DEFAULT_PARAMS_ENCODING = "UTF-8";
    private static final long SLOW_REQUEST_THRESHOLD_MS = 3000L;
    private Cache$Entry mCacheEntry;
    private boolean mCanceled;
    private int mDefaultTrafficStatsTag;
    private final Response$ErrorListener mErrorListener;
    private final VolleyLog$MarkerLog mEventLog;
    private final int mMethod;
    private long mRequestBirthTime;
    private RequestQueue mRequestQueue;
    private boolean mResponseDelivered;
    private RetryPolicy mRetryPolicy;
    private Integer mSequence;
    private boolean mShouldCache;
    private Object mTag;
    private String mUrl;
    
    public Request(int hashCode, final String mUrl, final Response$ErrorListener mErrorListener) {
        VolleyLog$MarkerLog mEventLog;
        if (VolleyLog$MarkerLog.ENABLED) {
            mEventLog = new VolleyLog$MarkerLog();
        }
        else {
            mEventLog = null;
        }
        this.mEventLog = mEventLog;
        this.mShouldCache = true;
        this.mCanceled = false;
        this.mResponseDelivered = false;
        this.mRequestBirthTime = 0L;
        this.mCacheEntry = null;
        this.mMethod = hashCode;
        this.mUrl = mUrl;
        this.mErrorListener = mErrorListener;
        this.setRetryPolicy(new DefaultRetryPolicy());
        if (TextUtils.isEmpty((CharSequence)mUrl)) {
            hashCode = 0;
        }
        else {
            hashCode = Uri.parse(mUrl).getHost().hashCode();
        }
        this.mDefaultTrafficStatsTag = hashCode;
    }
    
    public Request(final String s, final Response$ErrorListener response$ErrorListener) {
        this(-1, s, response$ErrorListener);
    }
    
    public static String buildNewUrlString(final String s, final String s2) {
        try {
            final URL url = new URL(s);
            final StringBuilder sb = new StringBuilder();
            sb.append(url.getProtocol()).append("://").append(s2).append(url.getPath()).append("?").append(url.getQuery());
            VolleyLog.e("Redirect: newUrl %s", sb.toString());
            return sb.toString();
        }
        catch (MalformedURLException ex) {
            VolleyLog.e(ex, "failed in build redirected url redirectedHost %s, oldUrl %s", s2, s);
            return s;
        }
    }
    
    private byte[] encodeParameters(final Map<String, String> map, final String s) {
        StringBuilder sb;
        while (true) {
            sb = new StringBuilder();
            while (true) {
                Map.Entry<String, String> entry = null;
                Label_0137: {
                    try {
                        final Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
                        while (iterator.hasNext()) {
                            entry = iterator.next();
                            if (entry.getValue() == null) {
                                break Label_0137;
                            }
                            sb.append(URLEncoder.encode(entry.getKey(), s));
                            sb.append('=');
                            sb.append(URLEncoder.encode(entry.getValue(), s));
                            sb.append('&');
                        }
                        break;
                    }
                    catch (Exception ex) {
                        throw new RuntimeException("Encoding not supported: " + s, ex);
                    }
                }
                VolleyLog.d("valueNull for key: %s, params %s", entry.getKey(), map.toString());
                continue;
            }
        }
        return sb.toString().getBytes(s);
    }
    
    public void addMarker(final String s) {
        if (VolleyLog$MarkerLog.ENABLED) {
            this.mEventLog.add(s, Thread.currentThread().getId());
        }
        else if (this.mRequestBirthTime == 0L) {
            this.mRequestBirthTime = SystemClock.elapsedRealtime();
        }
    }
    
    public void cancel() {
        this.mCanceled = true;
    }
    
    public void changeHostUrl(final String s) {
        this.mUrl = buildNewUrlString(this.mUrl, s);
        this.mDefaultTrafficStatsTag = s.hashCode();
    }
    
    public void changeToRedirectedUrl(final String mUrl) {
        this.mUrl = mUrl;
    }
    
    @Override
    public int compareTo(final Request<T> request) {
        final Request$Priority priority = this.getPriority();
        final Request$Priority priority2 = request.getPriority();
        if (priority == priority2) {
            return this.mSequence - request.mSequence;
        }
        return priority2.ordinal() - priority.ordinal();
    }
    
    public void deliverError(final VolleyError volleyError) {
        if (this.mErrorListener != null) {
            this.mErrorListener.onErrorResponse(volleyError);
        }
    }
    
    protected abstract void deliverResponse(final T p0);
    
    void finish(final String s) {
        if (this.mRequestQueue != null) {
            this.mRequestQueue.finish(this);
        }
        if (VolleyLog$MarkerLog.ENABLED) {
            final long id = Thread.currentThread().getId();
            if (Looper.myLooper() == Looper.getMainLooper()) {
                this.mEventLog.add(s, id);
                this.mEventLog.finish(this.toString());
                return;
            }
            new Handler(Looper.getMainLooper()).post((Runnable)new Request$1(this, s, id));
        }
        else {
            final long n = SystemClock.elapsedRealtime() - this.mRequestBirthTime;
            if (n >= 3000L) {
                VolleyLog.d("%d ms: %s", n, this.toString());
            }
        }
    }
    
    public byte[] getBody() {
        final Map<String, String> params = this.getParams();
        if (params != null && params.size() > 0) {
            return this.encodeParameters(params, this.getParamsEncoding());
        }
        return null;
    }
    
    public String getBodyContentType() {
        return "application/x-www-form-urlencoded; charset=" + this.getParamsEncoding();
    }
    
    public Cache$Entry getCacheEntry() {
        return this.mCacheEntry;
    }
    
    public String getCacheKey() {
        return this.getUrl();
    }
    
    public Map<String, String> getHeaders() {
        return Collections.emptyMap();
    }
    
    public int getMethod() {
        return this.mMethod;
    }
    
    protected Map<String, String> getParams() {
        return null;
    }
    
    protected String getParamsEncoding() {
        return "UTF-8";
    }
    
    public byte[] getPostBody() {
        final Map<String, String> postParams = this.getPostParams();
        if (postParams != null && postParams.size() > 0) {
            return this.encodeParameters(postParams, this.getPostParamsEncoding());
        }
        return null;
    }
    
    public String getPostBodyContentType() {
        return this.getBodyContentType();
    }
    
    protected Map<String, String> getPostParams() {
        return this.getParams();
    }
    
    protected String getPostParamsEncoding() {
        return this.getParamsEncoding();
    }
    
    public Request$Priority getPriority() {
        return Request$Priority.NORMAL;
    }
    
    public RetryPolicy getRetryPolicy() {
        return this.mRetryPolicy;
    }
    
    public final int getSequence() {
        if (this.mSequence == null) {
            throw new IllegalStateException("getSequence called before setSequence");
        }
        return this.mSequence;
    }
    
    public Object getTag() {
        return this.mTag;
    }
    
    public final int getTimeoutMs() {
        return this.mRetryPolicy.getCurrentTimeout();
    }
    
    public int getTrafficStatsTag() {
        return this.mDefaultTrafficStatsTag;
    }
    
    public String getUrl() {
        return this.mUrl;
    }
    
    public boolean hasHadResponseDelivered() {
        return this.mResponseDelivered;
    }
    
    public boolean isCanceled() {
        return this.mCanceled;
    }
    
    public void markDelivered() {
        this.mResponseDelivered = true;
    }
    
    protected VolleyError parseNetworkError(final VolleyError volleyError) {
        return volleyError;
    }
    
    protected abstract Response<T> parseNetworkResponse(final NetworkResponse p0);
    
    public void setCacheEntry(final Cache$Entry mCacheEntry) {
        this.mCacheEntry = mCacheEntry;
    }
    
    public void setRequestQueue(final RequestQueue mRequestQueue) {
        this.mRequestQueue = mRequestQueue;
    }
    
    public void setRetryPolicy(final RetryPolicy mRetryPolicy) {
        this.mRetryPolicy = mRetryPolicy;
    }
    
    public final void setSequence(final int n) {
        this.mSequence = n;
    }
    
    public final void setShouldCache(final boolean mShouldCache) {
        this.mShouldCache = mShouldCache;
    }
    
    public void setTag(final Object mTag) {
        this.mTag = mTag;
    }
    
    public final boolean shouldCache() {
        return this.mShouldCache;
    }
    
    @Override
    public String toString() {
        final String string = "0x" + Integer.toHexString(this.getTrafficStatsTag());
        final StringBuilder sb = new StringBuilder();
        String s;
        if (this.mCanceled) {
            s = "[X] ";
        }
        else {
            s = "[ ] ";
        }
        return sb.append(s).append(this.getUrl()).append(" ").append(string).append(" ").append(this.getPriority()).append(" ").append(this.mSequence).toString();
    }
}
