// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient.volley;

import com.android.volley.Cache;
import java.io.UnsupportedEncodingException;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.NetworkResponse;
import android.net.Uri;
import android.text.TextUtils;
import java.util.Locale;
import com.android.volley.AuthFailureError;
import com.netflix.mediaclient.util.StringUtils;
import com.android.volley.NetworkError;
import com.android.volley.TimeoutError;
import com.android.volley.ServerError;
import com.netflix.mediaclient.Log;
import com.android.volley.VolleyError;
import android.os.SystemClock;
import java.util.HashMap;
import com.android.volley.Response;
import com.netflix.mediaclient.service.webclient.UserCredentialRegistry;
import java.util.Map;
import com.netflix.mediaclient.servicemgr.ErrorLogging;
import com.android.volley.Request;

public abstract class VolleyWebClientRequest<T> extends Request<T>
{
    protected static final String COOKIE_KEY_HEADER = "Cookie";
    protected static final String ORIGINATING_URL_HEADER = "X-Originating-URL";
    protected static final String SET_COOKIE_KEY_HEADER = "Set-Cookie";
    private static final String TAG = "nf_volleyrequest";
    protected int mDefaultTrafficStatsTag;
    protected long mDurationTimeInMs;
    protected ErrorLogging mErrorLogger;
    protected final Map<String, String> mHeaders;
    protected String mReqNetflixId;
    protected int mResponseSizeInBytes;
    protected String mUrl;
    protected UserCredentialRegistry mUserCredentialRegistry;
    
    protected VolleyWebClientRequest(final int n) {
        super(n, null, null);
        this.mHeaders = new HashMap<String, String>(1);
        this.setShouldCache(false);
        this.mDurationTimeInMs = SystemClock.elapsedRealtime();
    }
    
    @Override
    public void changeHostUrl(final String s) {
        this.mUrl = Request.buildNewUrlString(this.mUrl, s);
        this.mDefaultTrafficStatsTag = s.hashCode();
    }
    
    @Override
    public void deliverError(final VolleyError volleyError) {
        this.mDurationTimeInMs = SystemClock.elapsedRealtime() - this.mDurationTimeInMs;
        Log.w("nf_volleyrequest", "VolleyError: " + volleyError.getMessage());
        if (volleyError.networkResponse != null) {
            Log.d("nf_volleyrequest", "Error on response:" + new String(volleyError.networkResponse.data));
        }
        int statusCodeFromVolleyNetworkError = -2;
        if (volleyError instanceof ParseException) {
            statusCodeFromVolleyNetworkError = -300;
        }
        else if (volleyError instanceof ServerError) {
            statusCodeFromVolleyNetworkError = -62;
        }
        else if (volleyError instanceof TimeoutError || volleyError instanceof NetworkError) {
            statusCodeFromVolleyNetworkError = this.getStatusCodeFromVolleyNetworkError(volleyError);
        }
        this.onFailure(statusCodeFromVolleyNetworkError);
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
    
    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        final String string = this.mUserCredentialRegistry.getNetflixIdName() + "=" + this.mUserCredentialRegistry.getNetflixID() + "; " + this.mUserCredentialRegistry.getSecureNetflixIdName() + "=" + this.mUserCredentialRegistry.getSecureNetflixID();
        if (Log.isLoggable("nf_volleyrequest", 2)) {
            Log.v("nf_volleyrequest", "VolleyWebClientRequest with cookies: " + string);
        }
        this.mHeaders.put("Cookie", string);
        return this.mHeaders;
    }
    
    protected abstract String getMethodType();
    
    protected String getOptionalParams() {
        return null;
    }
    
    @Override
    public Priority getPriority() {
        return Priority.HIGH;
    }
    
    protected int getStatusCodeFromVolleyNetworkError(final VolleyError volleyError) {
        final String message = volleyError.getMessage();
        int n;
        if (message == null) {
            n = -3;
        }
        else {
            final String lowerCase = message.toLowerCase(Locale.US);
            Log.d("nf_volleyrequest", ".next call failed with error =" + lowerCase);
            n = -3;
            if (lowerCase.contains("sslhandshakeexception")) {
                n = -120;
                if (lowerCase.contains("current time") && lowerCase.contains("validation time")) {
                    return -121;
                }
                if (lowerCase.contains("no trusted certificate found")) {
                    return -122;
                }
            }
        }
        return n;
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
    
    protected boolean isResponseValid() {
        final boolean safeEquals = StringUtils.safeEquals(this.mReqNetflixId, this.getCurrentNetflixId());
        if (!safeEquals) {
            Log.d("nf_volleyrequest", String.format("response not valid - reqNetflixId %s, currentNetflixId  %s", this.mReqNetflixId, this.getCurrentNetflixId()));
        }
        return safeEquals;
    }
    
    protected abstract void onFailure(final int p0);
    
    protected abstract void onSuccess(final T p0);
    
    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse networkResponse) {
        this.mDurationTimeInMs = SystemClock.elapsedRealtime() - this.mDurationTimeInMs;
        if (networkResponse != null && networkResponse.data != null) {
            this.mResponseSizeInBytes = networkResponse.data.length;
        }
        T response = null;
        Label_0397: {
            Label_0316: {
                if (!this.shouldSkipProcessingOnInvalidUser()) {
                    break Label_0316;
                }
                boolean responseValid = this.isResponseValid();
            Label_0124_Outer:
                while (true) {
                    final String s = networkResponse.headers.get("Set-Cookie");
                    Label_0258: {
                        if (s == null) {
                            break Label_0258;
                        }
                        if (Log.isLoggable("nf_volleyrequest", 2)) {
                            Log.v("nf_volleyrequest", "Received Volley response with Set-Cookie = " + s);
                        }
                        String s2 = null;
                        String s3 = null;
                        final String[] split = s.split(";");
                        final int length = split.length;
                        int n = 0;
                    Label_0187_Outer:
                        while (true) {
                            if (n >= length) {
                                break Label_0258;
                            }
                            final String[] split2 = split[n].split("=");
                            String s4 = s2;
                            String s5 = s3;
                            Label_0322: {
                                if (split2.length >= 2) {
                                    if (!this.mUserCredentialRegistry.getNetflixIdName().equalsIgnoreCase(split2[0].trim())) {
                                        break Label_0322;
                                    }
                                    s4 = split2[1];
                                    s5 = s3;
                                }
                            Label_0281_Outer:
                                while (true) {
                                    Label_0365: {
                                        if (!StringUtils.isNotEmpty(s4) || !StringUtils.isNotEmpty(s5)) {
                                            break Label_0365;
                                        }
                                        Log.d("nf_volleyrequest", String.format("update cookies ? %b - currentNetflixId %s, newId %s", responseValid, this.getCurrentNetflixId(), s4));
                                        if (responseValid) {
                                            this.mUserCredentialRegistry.updateUserCredentials(s4, s5);
                                        }
                                        while (true) {
                                            try {
                                                networkResponse = (NetworkResponse)new String(networkResponse.data, HttpHeaderParser.parseCharset(networkResponse.headers));
                                                if (!responseValid) {
                                                    networkResponse = (NetworkResponse)new String("wrong state ");
                                                    Log.d("nf_volleyrequest", (String)networkResponse);
                                                    return Response.error(new ParseException((String)networkResponse));
                                                }
                                                break Label_0397;
                                                s4 = s2;
                                                s5 = s3;
                                                // iftrue(Label_0187:, !this.mUserCredentialRegistry.getSecureNetflixIdName().equalsIgnoreCase(split2[0].trim()))
                                                Block_15: {
                                                    break Block_15;
                                                    responseValid = true;
                                                    continue Label_0124_Outer;
                                                }
                                                s5 = split2[1];
                                                s4 = s2;
                                                continue Label_0281_Outer;
                                                ++n;
                                                s2 = s4;
                                                s3 = s5;
                                                continue Label_0187_Outer;
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
                if (response == null) {
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
    
    protected abstract T parseResponse(final String p0) throws VolleyError;
    
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
