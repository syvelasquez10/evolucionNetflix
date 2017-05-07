// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient.volley;

import com.android.volley.Response;
import com.android.volley.NetworkResponse;
import com.netflix.mediaclient.util.StringUtils;
import com.android.volley.AuthFailureError;
import java.util.HashMap;
import java.util.Map;
import com.netflix.mediaclient.util.LogUtils;
import com.netflix.mediaclient.servicemgr.IClientLogging;
import java.util.List;
import com.netflix.mediaclient.service.logging.client.model.FalcorPathResult;
import java.util.ArrayList;
import com.netflix.mediaclient.service.logging.client.model.HttpResponse;
import com.netflix.mediaclient.service.logging.client.model.DeepErrorElement;
import com.android.volley.NetworkError;
import com.android.volley.TimeoutError;
import com.android.volley.ServerError;
import com.netflix.mediaclient.service.logging.client.model.Error;
import android.os.SystemClock;
import com.netflix.mediaclient.util.UriUtil;
import com.netflix.mediaclient.Log;
import java.util.Locale;
import com.netflix.mediaclient.service.logging.client.model.RootCause;
import com.android.volley.VolleyError;
import android.content.Context;
import com.netflix.mediaclient.service.ServiceAgent;
import com.netflix.mediaclient.service.webclient.ApiEndpointRegistry;
import java.util.concurrent.atomic.AtomicLong;

public abstract class FalcorVolleyWebClientRequest<T> extends VolleyWebClientRequest<T>
{
    public static final String SERVER_EXECUTION_TIME_HEADER = "X-Netflix.api-script-execution-time";
    private static final String TAG = "nf_volleyrequest";
    private static AtomicLong sFalcorRequestCount;
    protected ApiEndpointRegistry mApiEndpointRegistry;
    protected ServiceAgent.ConfigurationAgentInterface mConfig;
    protected Context mContext;
    protected long mParseTimeInMs;
    protected long mServerExecTimeInMs;
    protected long mUuid;
    
    static {
        FalcorVolleyWebClientRequest.sFalcorRequestCount = new AtomicLong(1L);
    }
    
    protected FalcorVolleyWebClientRequest(final Context mContext, final ServiceAgent.ConfigurationAgentInterface mConfig) {
        super(0);
        this.mUuid = FalcorVolleyWebClientRequest.sFalcorRequestCount.getAndIncrement();
        this.mConfig = mConfig;
        this.mContext = mContext;
    }
    
    protected static RootCause getRootCauseFromVolleyNetworkError(final VolleyError volleyError) {
        final String message = volleyError.getMessage();
        RootCause rootCause;
        if (message == null) {
            rootCause = RootCause.networkFailure;
        }
        else {
            final String lowerCase = message.toLowerCase(Locale.US);
            if (Log.isLoggable("nf_volleyrequest", 3)) {
                Log.d("nf_volleyrequest", ".next call failed with error =" + lowerCase);
            }
            rootCause = RootCause.networkFailure;
            if (lowerCase.contains("sslhandshakeexception")) {
                rootCause = RootCause.sslHandshakeFailure;
                if (lowerCase.contains("current time") && lowerCase.contains("validation time")) {
                    return RootCause.sslExpiredCert;
                }
                if (lowerCase.contains("no trusted certificate found")) {
                    return RootCause.sslUntrustedCert;
                }
            }
        }
        return rootCause;
    }
    
    protected static String urlEncodPQLParam(final String s, String urlEncodeParam) {
        urlEncodeParam = UriUtil.urlEncodeParam(urlEncodeParam);
        return "&" + s + "=" + urlEncodeParam;
    }
    
    @Override
    public void changeHostUrl(final String s) {
        super.changeHostUrl(s);
        this.mApiEndpointRegistry.updateApiEndpointHost(s);
    }
    
    @Override
    public void deliverError(final VolleyError volleyError) {
        this.mDurationTimeInMs = SystemClock.elapsedRealtime() - this.mDurationTimeInMs;
        final Error error = new Error();
        if (Log.isLoggable("nf_volleyrequest", 5)) {
            Log.w("nf_volleyrequest", "VolleyError: " + volleyError.getMessage());
        }
        if (volleyError.networkResponse != null && Log.isLoggable("nf_volleyrequest", 3)) {
            Log.d("nf_volleyrequest", "Error on response:" + new String(volleyError.networkResponse.data));
        }
        int n = -2;
        if (volleyError instanceof FalcorParseException) {
            n = FalcorParseException.getErrorCode(((FalcorParseException)volleyError).getMessage());
            error.setRootCause(RootCause.serverResponseBad);
        }
        else if (volleyError instanceof FalcorServerException) {
            n = FalcorServerException.getErrorCode(((FalcorServerException)volleyError).getMessage(), this.mErrorLogger);
            error.setRootCause(RootCause.serverFailure);
        }
        else if (volleyError instanceof ServerError) {
            n = -62;
            error.setRootCause(RootCause.serverFailure);
        }
        else if (volleyError instanceof TimeoutError) {
            n = this.getStatusCodeFromVolleyNetworkError(volleyError);
            error.setRootCause(RootCause.tcpConnectionTimeout);
        }
        else if (volleyError instanceof NetworkError) {
            n = this.getStatusCodeFromVolleyNetworkError(volleyError);
            error.setRootCause(getRootCauseFromVolleyNetworkError(volleyError));
        }
        if (this.mContext != null) {
            Log.d("nf_volleyrequest", "Report data request failed");
            final DeepErrorElement deepErrorElement = new DeepErrorElement();
            if (volleyError != null && volleyError.networkResponse != null) {
                deepErrorElement.setErrorCode("" + volleyError.networkResponse.statusCode);
            }
            else {
                Log.e("nf_volleyrequest", "Network response is not set!");
                deepErrorElement.setErrorCode("504");
            }
            deepErrorElement.setFatal(true);
            error.addDeepError(deepErrorElement);
            final HttpResponse httpResponse = new HttpResponse();
            httpResponse.setResponseTime((int)this.mDurationTimeInMs);
            httpResponse.setMimeType("text/x-json");
            httpResponse.setContentLength(this.mResponseSizeInBytes);
            final String[] pqlQueries = this.getPQLQueries();
            int length = 0;
            if (pqlQueries != null) {
                length = pqlQueries.length;
            }
            final ArrayList list = new ArrayList<FalcorPathResult>(length);
            if (pqlQueries != null) {
                for (int length2 = pqlQueries.length, i = 0; i < length2; ++i) {
                    list.add(new FalcorPathResult(pqlQueries[i], false, null));
                }
            }
            LogUtils.reportDataRequestEnded(this.mContext, String.valueOf(this.mUuid), IClientLogging.CompletionReason.failed, (List<FalcorPathResult>)list, error, httpResponse);
        }
        this.onFailure(n);
    }
    
    @Override
    protected void deliverResponse(final T t) {
        super.deliverResponse(t);
        if (this.mContext != null) {
            Log.d("nf_volleyrequest", "Report data request sucess");
            final HttpResponse httpResponse = new HttpResponse();
            httpResponse.setResponseTime((int)this.mDurationTimeInMs);
            httpResponse.setParseTime((int)this.mParseTimeInMs);
            httpResponse.setMimeType("text/x-json");
            httpResponse.setServerExecutionTime((int)this.mServerExecTimeInMs);
            httpResponse.setContentLength(this.mResponseSizeInBytes);
            final String[] pqlQueries = this.getPQLQueries();
            int length = 0;
            if (pqlQueries != null) {
                length = pqlQueries.length;
            }
            final ArrayList list = new ArrayList<FalcorPathResult>(length);
            if (pqlQueries != null) {
                for (int length2 = pqlQueries.length, i = 0; i < length2; ++i) {
                    list.add(new FalcorPathResult(pqlQueries[i], true, null));
                }
            }
            LogUtils.reportDataRequestEnded(this.mContext, String.valueOf(this.mUuid), IClientLogging.CompletionReason.success, (List<FalcorPathResult>)list, null, httpResponse);
        }
    }
    
    Context getContext() {
        return this.mContext;
    }
    
    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        Map<String, String> headers;
        if ((headers = super.getHeaders()) == null) {
            headers = new HashMap<String, String>();
        }
        headers.put("X-Netflix.request.uuid", "" + this.mUuid);
        return headers;
    }
    
    @Override
    protected String getMethodType() {
        return FalcorParseUtils.getMethodNameGet();
    }
    
    protected abstract String[] getPQLQueries();
    
    protected String getQueryPathName() {
        if (StringUtils.safeEquals(FalcorParseUtils.getMethodNameGet(), this.getMethodType())) {
            return FalcorParseUtils.getParamNamePath();
        }
        return FalcorParseUtils.getParamNameCallPath();
    }
    
    protected String getRawPQLQuery() {
        final String[] pqlQueries = this.getPQLQueries();
        if (pqlQueries == null) {
            throw new IllegalArgumentException("List of queries is null!");
        }
        final StringBuilder sb = new StringBuilder();
        for (int length = pqlQueries.length, i = 0; i < length; ++i) {
            sb.append(urlEncodPQLParam(this.getQueryPathName(), pqlQueries[i]));
        }
        return sb.toString();
    }
    
    public long getRequestId() {
        return this.mUuid;
    }
    
    @Override
    protected String getUrl(String string) {
        final String rawPQLQuery = this.getRawPQLQuery();
        final StringBuilder sb = new StringBuilder(string);
        sb.append(StringUtils.buildUnencodedUrlParam("method", this.getMethodType()));
        sb.append(rawPQLQuery);
        final String optionalParams = this.getOptionalParams();
        if (this.getMethodType().endsWith("call") && optionalParams != null) {
            sb.append(optionalParams);
        }
        this.storeReqNetflixId(this.getCurrentNetflixId());
        string = sb.toString();
        if (Log.isLoggable("nf_volleyrequest", 2)) {
            Log.v("nf_volleyrequest", "VolleyWebClientRequest URL = " + string);
        }
        return string;
    }
    
    protected abstract T parseFalcorResponse(final String p0) throws FalcorParseException, FalcorServerException;
    
    @Override
    protected Response<T> parseNetworkResponse(final NetworkResponse networkResponse) {
        while (true) {
            Label_0089: {
                if (networkResponse == null || networkResponse.headers == null) {
                    break Label_0089;
                }
                final String s = networkResponse.headers.get("X-Netflix.api-script-execution-time");
                if (Log.isLoggable("nf_volleyrequest", 3)) {
                    Log.d("nf_volleyrequest", "execTime: " + s);
                }
                try {
                    this.mServerExecTimeInMs = Long.parseLong(s);
                    return super.parseNetworkResponse(networkResponse);
                }
                catch (Throwable t) {
                    Log.e("nf_volleyrequest", "Failed to parse server execution time!", t);
                    return super.parseNetworkResponse(networkResponse);
                }
            }
            Log.w("nf_volleyrequest", "execTime not found!");
            continue;
        }
    }
    
    @Override
    protected T parseResponse(final String s) throws VolleyError {
        this.mParseTimeInMs = SystemClock.elapsedRealtime();
        T falcorResponse;
        try {
            falcorResponse = this.parseFalcorResponse(s);
            this.mParseTimeInMs = SystemClock.elapsedRealtime() - this.mParseTimeInMs;
            if (Log.isLoggable("nf_volleyrequest", 2)) {
                Log.v("nf_volleyrequest", "parse time (ms): " + this.mParseTimeInMs);
            }
            if (falcorResponse == null) {
                throw new FalcorParseException("Parsing returned null.");
            }
        }
        catch (Exception ex) {
            if (!(ex instanceof FalcorParseException) && !(ex instanceof FalcorServerException)) {
                throw new VolleyError(ex);
            }
            throw (VolleyError)ex;
        }
        return falcorResponse;
    }
    
    void setApiEndpointRegistry(final ApiEndpointRegistry mApiEndpointRegistry) {
        this.mApiEndpointRegistry = mApiEndpointRegistry;
    }
}
