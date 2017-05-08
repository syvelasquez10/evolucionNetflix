// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.msl.volley;

import android.net.Uri;
import android.text.TextUtils;
import com.android.volley.Request$Priority;
import java.util.HashMap;
import java.util.Collections;
import com.netflix.mediaclient.service.offline.agent.PlayabilityEnforcer;
import com.netflix.mediaclient.service.logging.client.model.Error;
import java.util.List;
import com.netflix.mediaclient.util.log.ApmLogUtils;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import com.netflix.mediaclient.service.logging.client.model.HttpResponse;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.util.log.ConsolidatedLoggingUtils;
import com.netflix.mediaclient.util.LogUtils;
import java.util.Random;
import com.netflix.mediaclient.util.VolleyUtils;
import com.android.volley.NetworkError;
import com.android.volley.TimeoutError;
import com.android.volley.ServerError;
import com.netflix.mediaclient.android.app.NetflixStatus;
import com.netflix.mediaclient.StatusCode;
import com.netflix.mediaclient.service.webclient.volley.ParseException;
import com.android.volley.VolleyError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RetryPolicy;
import com.netflix.mediaclient.Log;
import com.netflix.msl.MslException;
import java.io.IOException;
import java.util.Iterator;
import com.android.volley.VolleyLog;
import java.net.URLEncoder;
import java.util.Map;
import android.os.SystemClock;
import com.android.volley.Response$ErrorListener;
import java.util.UUID;
import com.netflix.mediaclient.service.ServiceAgent$UserAgentInterface;
import com.netflix.mediaclient.servicemgr.IMSLClient$MSLUserCredentialRegistry;
import com.netflix.mediaclient.service.msl.client.AndroidMslClient;
import com.netflix.mediaclient.servicemgr.IMSLClient;
import com.netflix.mediaclient.servicemgr.IMSLClient$NetworkRequestInspector;
import com.netflix.mediaclient.servicemgr.ErrorLogging;
import com.netflix.mediaclient.service.ServiceAgent$ConfigurationAgentInterface;
import com.netflix.mediaclient.servicemgr.IMSLClient$MSLRequest;
import com.android.volley.Request;

public abstract class MSLVolleyRequest<T> extends Request<T> implements IMSLClient$MSLRequest
{
    protected static final String ENDPOINT_REVISION = "X-Netflix.api-script-revision";
    protected static final String NETFLIX_API_SCRIPT_EXECUTION_TIME_HEADER = "X-Netflix.api-script-execution-time";
    protected static final String NETFLIX_SERVER_EXECUTION_TIME_HEADER = "X-Netflix.execution-time";
    private static final String TAG = "nf_volleyrequest";
    protected long mApiScriptExecTimeInMs;
    protected ServiceAgent$ConfigurationAgentInterface mConfig;
    private int mDefaultTrafficStatsTag;
    protected long mDurationTimeInMs;
    protected String mEndpointRevision;
    protected ErrorLogging mErrorLogger;
    protected IMSLClient$NetworkRequestInspector mInspector;
    protected IMSLClient mMslAgent;
    private AndroidMslClient mMslClient;
    private String mMslPath;
    protected IMSLClient$MSLUserCredentialRegistry mMslUserCredentialRegistry;
    protected long mParseTimeInMs;
    protected int mResponseSizeInBytes;
    protected long mServerExecTimeInMs;
    protected int mTimeoutInMs;
    private String mUrl;
    protected ServiceAgent$UserAgentInterface mUserAgent;
    protected boolean mUserIsNotLoggedInRetryRequest;
    protected UUID mUuid;
    
    public MSLVolleyRequest(final int n) {
        super(n, null, null);
        this.mServerExecTimeInMs = -1L;
        this.setShouldCache(false);
        this.mUuid = UUID.randomUUID();
        this.mDurationTimeInMs = SystemClock.elapsedRealtime();
    }
    
    private static String encodeParametersAsString(final Map<String, String> map, final String s) {
        StringBuilder sb;
        while (true) {
            sb = new StringBuilder();
            while (true) {
                Map.Entry<String, String> entry = null;
                Label_0134: {
                    try {
                        final Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
                        while (iterator.hasNext()) {
                            entry = iterator.next();
                            if (entry.getValue() == null) {
                                break Label_0134;
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
                if (MSLVolleyRequest.DEBUG) {
                    VolleyLog.d("valueNull for key: %s, params %s", entry.getKey(), map.toString());
                    continue;
                }
                continue;
            }
        }
        return sb.toString();
    }
    
    protected static Throwable findCause(final Throwable t) {
        Throwable t2;
        if (t == null) {
            t2 = null;
        }
        else {
            t2 = t;
            if (!(t instanceof IOException)) {
                t2 = t;
                if (t.getCause() != null) {
                    return findCause(t.getCause());
                }
            }
        }
        return t2;
    }
    
    protected static Throwable findCauseForMslException(final MslException ex) {
        Object o;
        if (ex == null) {
            o = null;
        }
        else {
            o = ex;
            if (ex.getCause() != null) {
                return findCause(ex.getCause());
            }
        }
        return (Throwable)o;
    }
    
    private String getEncodedPayload() {
        final String s = null;
        try {
            final Map<String, String> params = this.getParams();
            String encodeParametersAsString = s;
            if (params != null) {
                encodeParametersAsString = s;
                if (params.size() > 0) {
                    encodeParametersAsString = encodeParametersAsString(params, this.getParamsEncoding());
                }
            }
            return encodeParametersAsString;
        }
        catch (Throwable t) {
            Log.e("nf_volleyrequest", t, "Failed to get BODY as string", new Object[0]);
            return null;
        }
    }
    
    @Override
    public void changeHostUrl(final String s) {
        this.mUrl = Request.buildNewUrlString(this.mUrl, s);
        this.mDefaultTrafficStatsTag = s.hashCode();
        this.mMslClient.getUrlProvider().updateApiEndpointHost(s);
        super.changeHostUrl(s);
    }
    
    protected RetryPolicy createRetryPolicy() {
        int mTimeoutInMs;
        if (this.mTimeoutInMs <= 0) {
            mTimeoutInMs = 2500;
        }
        else {
            mTimeoutInMs = this.mTimeoutInMs;
        }
        return new DefaultRetryPolicy(mTimeoutInMs, 1, 1.0f);
    }
    
    @Override
    public void deliverError(final VolleyError volleyError) {
        if (Log.isLoggable()) {
            Log.w("nf_volleyrequest", "VolleyError: " + volleyError.getMessage());
        }
        if (volleyError.networkResponse != null) {
            Log.d("nf_volleyrequest", "Error on response:" + new String(volleyError.networkResponse.data));
        }
        if (this.mUserIsNotLoggedInRetryRequest) {
            this.mUserIsNotLoggedInRetryRequest = false;
            if (this.mMslAgent != null) {
                Log.d("nf_volleyrequest", "Retry request %s", this.getClass().getSimpleName());
                this.mMslAgent.addRequest(this);
                return;
            }
        }
        this.mDurationTimeInMs = SystemClock.elapsedRealtime() - this.mDurationTimeInMs;
        NetflixStatus status = null;
        if (volleyError instanceof ParseException) {
            status = new NetflixStatus(StatusCode.RESPONSE_PARSE_ERROR);
        }
        else if (volleyError instanceof ServerError) {
            status = new NetflixStatus(StatusCode.SERVER_ERROR);
        }
        else if (volleyError instanceof TimeoutError || volleyError instanceof NetworkError) {
            status = VolleyUtils.getStatus(volleyError, this.mErrorLogger, StatusCode.INT_ERR_FETCH_TIMEOUT);
        }
        NetflixStatus netflixStatus;
        if ((netflixStatus = status) == null) {
            final NetflixStatus netflixStatus2 = netflixStatus = new NetflixStatus(StatusCode.INT_ERR_FETCH_ERROR);
            if (volleyError != null) {
                netflixStatus2.setDebugMessageForServerLogs(volleyError.getMessage());
                netflixStatus = netflixStatus2;
                if (new Random().nextInt(100) == 31) {
                    LogUtils.reportErrorSafely(volleyError.getMessage(), (Throwable)volleyError);
                    netflixStatus = netflixStatus2;
                }
            }
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
        final long durationTimeMs = this.getDurationTimeMs();
        if (Log.isLoggable()) {
            Log.d("nf_volleyrequest", "request duration time (ms): " + durationTimeMs + ", class: " + this.getClass());
        }
        if (this.mMslClient != null && this.mMslClient.getContext() != null) {
            Log.d("nf_volleyrequest", "Report data request sucess");
            final HttpResponse httpResponse = new HttpResponse();
            httpResponse.setResponseTime((int)durationTimeMs);
            httpResponse.setParseTime((int)this.mParseTimeInMs);
            httpResponse.setMimeType("text/x-json");
            httpResponse.setServerExecutionTime((int)this.mServerExecTimeInMs);
            httpResponse.setContentLength(this.getResponseSizeInBytes());
            httpResponse.setApiScriptExecutionTime((int)this.mApiScriptExecTimeInMs);
            httpResponse.setEndpointRevision(this.mEndpointRevision);
            ApmLogUtils.reportDataRequestEnded(this.mMslClient.getContext(), String.valueOf(this.mUuid), IClientLogging$CompletionReason.success, (List)null, (Error)null, httpResponse);
            PlayabilityEnforcer.updateLastContactNetflix(this.mMslClient.getContext());
        }
        this.onSuccess(t);
    }
    
    public abstract byte[] execute(final Map<String, String> p0);
    
    @Override
    public String getBodyContentType() {
        return "application/msl; charset=" + this.getParamsEncoding();
    }
    
    protected long getDurationTimeMs() {
        return this.mDurationTimeInMs;
    }
    
    @Override
    public Map<String, String> getHeaders() {
        final Map<String, String> headers = super.getHeaders();
        Map<String, String> map;
        if (headers == null || (map = headers) == Collections.EMPTY_MAP) {
            map = new HashMap<String, String>();
        }
        map.put("X-Netflix.request.uuid", "" + this.mUuid);
        return map;
    }
    
    public AndroidMslClient getMSLClient() {
        return this.mMslClient;
    }
    
    @Override
    public Map<String, String> getMSLHeaders() {
        try {
            return this.getHeaders();
        }
        catch (Throwable t) {
            Log.e("nf_volleyrequest", t, "Failed to get MSL headers", new Object[0]);
            return null;
        }
    }
    
    @Override
    public String getMSLPayload() {
        if (this.getMethod() == 0) {
            return null;
        }
        return this.getEncodedPayload();
    }
    
    @Override
    public String getMSLQuery() {
        if (this.getMethod() != 0) {
            return null;
        }
        return this.getEncodedPayload();
    }
    
    @Override
    public String getMSLUri() {
        return this.mMslPath;
    }
    
    @Override
    public IMSLClient$MSLUserCredentialRegistry getMSLUserCredentialRegistry() {
        return this.mMslUserCredentialRegistry;
    }
    
    @Override
    public Request$Priority getPriority() {
        return Request$Priority.HIGH;
    }
    
    public String getRequestId() {
        return this.mUuid.toString();
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
    
    protected void initUrl(final String mUrl) {
        if (this.mUrl != null) {
            throw new IllegalStateException("Can not change the URL of a VolleyWebCLientRequest.");
        }
        this.mUrl = mUrl;
        if (TextUtils.isEmpty((CharSequence)this.mUrl)) {
            this.mDefaultTrafficStatsTag = 0;
            return;
        }
        final Uri parse = Uri.parse(this.mUrl);
        final String host = parse.getHost();
        this.mMslPath = parse.getPath();
        if (this.mMslPath.startsWith("/msl")) {
            this.mMslPath = this.mMslPath.substring(4);
        }
        if (host == null) {
            this.mDefaultTrafficStatsTag = 0;
            return;
        }
        this.mDefaultTrafficStatsTag = host.hashCode();
    }
    
    protected abstract void injectUrl();
    
    protected boolean isAuthorizationRequired() {
        return true;
    }
    
    protected abstract void onFailure(final Status p0);
    
    protected abstract void onSuccess(final T p0);
    
    protected boolean parsedResponseCanBeNull() {
        return false;
    }
    
    public void setConfig(final ServiceAgent$ConfigurationAgentInterface mConfig) {
        this.mConfig = mConfig;
    }
    
    public void setErrorLogger(final ErrorLogging mErrorLogger) {
        this.mErrorLogger = mErrorLogger;
    }
    
    public void setInspector(final IMSLClient$NetworkRequestInspector imslClient$NetworkRequestInspector) {
        throw new IllegalAccessError("Trying to set inspector in release build!");
    }
    
    public void setMSLAgent(final IMSLClient mMslAgent) {
        this.mMslAgent = mMslAgent;
    }
    
    public void setMSLClient(final AndroidMslClient mMslClient) {
        this.mMslClient = mMslClient;
        this.injectUrl();
    }
    
    public void setMSLUserCredentialRegistry(final IMSLClient$MSLUserCredentialRegistry mMslUserCredentialRegistry) {
        this.mMslUserCredentialRegistry = mMslUserCredentialRegistry;
    }
    
    public void setTimeout(final int mTimeoutInMs) {
        this.mTimeoutInMs = mTimeoutInMs;
    }
    
    public void setUserAgent(final ServiceAgent$UserAgentInterface mUserAgent) {
        this.mUserAgent = mUserAgent;
    }
    
    protected boolean shouldSkipProcessingOnInvalidUser() {
        return true;
    }
}
