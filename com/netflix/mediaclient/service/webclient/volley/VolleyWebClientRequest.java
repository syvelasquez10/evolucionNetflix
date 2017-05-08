// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient.volley;

import com.android.volley.Cache$Entry;
import java.io.UnsupportedEncodingException;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.Response;
import com.android.volley.NetworkResponse;
import android.net.Uri;
import android.text.TextUtils;
import com.android.volley.Request$Priority;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.util.log.ConsolidatedLoggingUtils;
import com.netflix.mediaclient.util.VolleyUtils;
import com.android.volley.NetworkError;
import com.android.volley.TimeoutError;
import com.android.volley.ServerError;
import com.netflix.mediaclient.android.app.NetflixStatus;
import com.netflix.mediaclient.StatusCode;
import com.netflix.mediaclient.Log;
import com.android.volley.VolleyError;
import com.netflix.mediaclient.util.StringUtils;
import android.os.SystemClock;
import java.util.HashMap;
import com.android.volley.Response$ErrorListener;
import com.netflix.mediaclient.service.webclient.UserCredentialRegistry;
import java.util.Map;
import com.netflix.mediaclient.servicemgr.ErrorLogging;
import com.android.volley.Request;

public abstract class VolleyWebClientRequest<T> extends Request<T>
{
    private static final String COOKIE_KEY_HEADER = "Cookie";
    private static final String SET_COOKIE_KEY_HEADER = "Set-Cookie";
    private static final String TAG = "nf_volleyrequest";
    private int mDefaultTrafficStatsTag;
    protected long mDurationTimeInMs;
    protected ErrorLogging mErrorLogger;
    private final Map<String, String> mHeaders;
    private String mReqNetflixId;
    protected int mResponseSizeInBytes;
    private String mUrl;
    private UserCredentialRegistry mUserCredentialRegistry;
    
    protected VolleyWebClientRequest(final int n) {
        super(n, null, null);
        this.mHeaders = new HashMap<String, String>(1);
        this.setShouldCache(false);
        this.mDurationTimeInMs = SystemClock.elapsedRealtime();
    }
    
    protected boolean areNetflixCookiesNull() {
        return StringUtils.isEmpty(this.mUserCredentialRegistry.getNetflixID()) || StringUtils.isEmpty(this.mUserCredentialRegistry.getSecureNetflixID());
    }
    
    @Override
    public void changeHostUrl(final String s) {
        this.mUrl = Request.buildNewUrlString(this.mUrl, s);
        this.mDefaultTrafficStatsTag = s.hashCode();
    }
    
    @Override
    public void deliverError(final VolleyError volleyError) {
        this.mDurationTimeInMs = SystemClock.elapsedRealtime() - this.mDurationTimeInMs;
        NetflixStatus status = null;
        if (Log.isLoggable()) {
            Log.w("nf_volleyrequest", "VolleyError: " + volleyError.getMessage());
        }
        if (volleyError.networkResponse != null) {
            Log.d("nf_volleyrequest", "Error on response:" + new String(volleyError.networkResponse.data));
        }
        if (volleyError instanceof ParseException) {
            status = new NetflixStatus(StatusCode.RESPONSE_PARSE_ERROR);
        }
        else if (volleyError instanceof ServerError) {
            status = new NetflixStatus(StatusCode.SERVER_ERROR);
        }
        else if (volleyError instanceof TimeoutError || volleyError instanceof NetworkError) {
            status = VolleyUtils.getStatus(volleyError, this.mErrorLogger);
        }
        NetflixStatus netflixStatus;
        if ((netflixStatus = status) == null) {
            netflixStatus = new NetflixStatus(StatusCode.INTERNAL_ERROR);
        }
        if (netflixStatus.getError() == null) {
            Log.d("nf_volleyrequest", "Error is not set yet, add it.");
            netflixStatus.setError(ConsolidatedLoggingUtils.toError(volleyError));
        }
        this.onFailure(netflixStatus);
    }
    
    @Override
    protected void deliverResponse(final T t) {
        this.mDurationTimeInMs = SystemClock.elapsedRealtime() - this.mDurationTimeInMs;
        this.onSuccess(t);
    }
    
    protected String getCurrentNetflixId() {
        if (this.mUserCredentialRegistry == null || StringUtils.isEmpty(this.mUserCredentialRegistry.getNetflixID())) {
            return null;
        }
        return this.mUserCredentialRegistry.getNetflixID();
    }
    
    protected long getDurationTimeMs() {
        return this.mDurationTimeInMs;
    }
    
    @Override
    public Map<String, String> getHeaders() {
        if (!this.areNetflixCookiesNull()) {
            final String string = this.mUserCredentialRegistry.getNetflixIdName() + "=" + this.mUserCredentialRegistry.getNetflixID() + "; " + this.mUserCredentialRegistry.getSecureNetflixIdName() + "=" + this.mUserCredentialRegistry.getSecureNetflixID();
            if (Log.isLoggable()) {
                Log.v("nf_volleyrequest", "VolleyWebClientRequest with cookies: " + string);
            }
            this.mHeaders.put("Cookie", string);
        }
        else if (Log.isLoggable()) {
            Log.v("nf_volleyrequest", "Cookies null. not adding to headers. url:" + this.getUrl());
        }
        return this.mHeaders;
    }
    
    protected abstract String getMethodType();
    
    protected String getOptionalParams() {
        return null;
    }
    
    @Override
    public Request$Priority getPriority() {
        return Request$Priority.HIGH;
    }
    
    protected int getResponseSizeInBytes() {
        return this.mResponseSizeInBytes;
    }
    
    @Override
    public int getTrafficStatsTag() {
        return this.mDefaultTrafficStatsTag;
    }
    
    @Override
    public String getUrl() {
        return this.mUrl;
    }
    
    protected abstract String getUrl(final String p0);
    
    public void initUrl(String host) {
        if (this.mUrl != null) {
            throw new IllegalStateException("Can not change the URL of a VolleyWebCLientRequest.");
        }
        this.mUrl = this.getUrl(host);
        int hashCode;
        if (TextUtils.isEmpty((CharSequence)this.mUrl)) {
            hashCode = 0;
        }
        else {
            hashCode = Uri.parse(this.mUrl).getHost().hashCode();
        }
        this.mDefaultTrafficStatsTag = hashCode;
        if (TextUtils.isEmpty((CharSequence)this.mUrl)) {
            this.mDefaultTrafficStatsTag = 0;
            return;
        }
        host = Uri.parse(this.mUrl).getHost();
        if (host == null) {
            this.mDefaultTrafficStatsTag = 0;
            return;
        }
        this.mDefaultTrafficStatsTag = host.hashCode();
    }
    
    protected boolean isAuthorizationRequired() {
        return true;
    }
    
    protected boolean isResponseValid() {
        final boolean safeEquals = StringUtils.safeEquals(this.mReqNetflixId, this.getCurrentNetflixId());
        if (!safeEquals) {
            Log.d("nf_volleyrequest", String.format("response not valid - reqNetflixId %s, currentNetflixId  %s", this.mReqNetflixId, this.getCurrentNetflixId()));
        }
        return safeEquals;
    }
    
    protected abstract void onFailure(final Status p0);
    
    protected abstract void onSuccess(final T p0);
    
    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse networkResponse) {
        if (networkResponse != null && networkResponse.data != null) {
            this.mResponseSizeInBytes = networkResponse.data.length;
        }
        T response = null;
        Label_0389: {
            Label_0308: {
                if (!this.isAuthorizationRequired() || !this.shouldSkipProcessingOnInvalidUser()) {
                    break Label_0308;
                }
                boolean responseValid = this.isResponseValid();
            Label_0116_Outer:
                while (true) {
                    final String s = networkResponse.headers.get("Set-Cookie");
                    Label_0250: {
                        if (s == null) {
                            break Label_0250;
                        }
                        if (Log.isLoggable()) {
                            Log.v("nf_volleyrequest", "Received Volley response with Set-Cookie = " + s);
                        }
                        final String[] split = s.split(";");
                        final int length = split.length;
                        int n = 0;
                        String s2 = null;
                        String s3 = null;
                    Label_0179_Outer:
                        while (true) {
                            if (n >= length) {
                                break Label_0250;
                            }
                            final String[] split2 = split[n].split("=");
                            String s4 = s2;
                            String s5 = s3;
                            Label_0314: {
                                if (split2.length >= 2) {
                                    if (!this.mUserCredentialRegistry.getNetflixIdName().equalsIgnoreCase(split2[0].trim())) {
                                        break Label_0314;
                                    }
                                    s5 = split2[1];
                                    s4 = s2;
                                }
                            Label_0273_Outer:
                                while (true) {
                                    Label_0357: {
                                        if (!StringUtils.isNotEmpty(s5) || !StringUtils.isNotEmpty(s4)) {
                                            break Label_0357;
                                        }
                                        Log.d("nf_volleyrequest", String.format("update cookies ? %b - currentNetflixId %s, newId %s", responseValid, this.getCurrentNetflixId(), s5));
                                        if (responseValid) {
                                            this.mUserCredentialRegistry.updateUserCredentials(s5, s4);
                                        }
                                        while (true) {
                                            try {
                                                networkResponse = (NetworkResponse)new String(networkResponse.data, HttpHeaderParser.parseCharset(networkResponse.headers));
                                                if (!responseValid) {
                                                    networkResponse = (NetworkResponse)new String("wrong state ");
                                                    Log.d("nf_volleyrequest", (String)networkResponse);
                                                    return Response.error(new ParseException((String)networkResponse));
                                                }
                                                break Label_0389;
                                                s4 = s2;
                                                s5 = s3;
                                                // iftrue(Label_0179:, !this.mUserCredentialRegistry.getSecureNetflixIdName().equalsIgnoreCase(split2[0].trim()))
                                                Block_16: {
                                                    break Block_16;
                                                    responseValid = true;
                                                    continue Label_0116_Outer;
                                                    ++n;
                                                    s2 = s4;
                                                    s3 = s5;
                                                    continue Label_0179_Outer;
                                                }
                                                s4 = split2[1];
                                                s5 = s3;
                                                continue Label_0273_Outer;
                                            }
                                            catch (UnsupportedEncodingException ex2) {
                                                networkResponse = (NetworkResponse)new String(networkResponse.data);
                                                continue;
                                            }
                                            break;
                                        }
                                    }
                                    break;
                                }
                            }
                            break;
                        }
                    }
                    break;
                }
            }
            try {
                response = this.parseResponse((String)networkResponse);
                if (!this.parsedResponseCanBeNull() && response == null) {
                    return Response.error(new ParseException("Parsing returned null."));
                }
            }
            catch (Exception ex) {
                if (ex instanceof VolleyError) {
                    return Response.error((VolleyError)ex);
                }
                return Response.error(new VolleyError(ex));
            }
        }
        return Response.success(response, null);
    }
    
    protected abstract T parseResponse(final String p0);
    
    protected boolean parsedResponseCanBeNull() {
        return false;
    }
    
    void setErrorLogger(final ErrorLogging mErrorLogger) {
        this.mErrorLogger = mErrorLogger;
    }
    
    public void setUserCredentialRegistry(final UserCredentialRegistry mUserCredentialRegistry) {
        this.mUserCredentialRegistry = mUserCredentialRegistry;
    }
    
    protected boolean shouldSkipProcessingOnInvalidUser() {
        return true;
    }
    
    protected void storeReqNetflixId(final String s) {
        if (StringUtils.isNotEmpty(s)) {
            this.mReqNetflixId = new String(s);
        }
    }
}
