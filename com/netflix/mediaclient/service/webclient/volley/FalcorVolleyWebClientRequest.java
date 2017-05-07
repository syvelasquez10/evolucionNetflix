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
import java.util.UUID;
import android.content.Context;
import com.netflix.mediaclient.service.ServiceAgent;
import com.netflix.mediaclient.service.webclient.ApiEndpointRegistry;

public abstract class FalcorVolleyWebClientRequest<T> extends VolleyWebClientRequest<T>
{
    public static final String ENDPOINT_REVISION = "X-Netflix.api-script-revision";
    public static final String NETFLIX_API_SCRIPT_EXECUTION_TIME_HEADER = "X-Netflix.api-script-execution-time";
    public static final String NETFLIX_SERVER_EXECUTION_TIME_HEADER = "X-Netflix.execution-time";
    private static final String TAG = "nf_volleyrequest";
    protected ApiEndpointRegistry mApiEndpointRegistry;
    protected long mApiScriptExecTimeInMs;
    protected ServiceAgent.ConfigurationAgentInterface mConfig;
    protected Context mContext;
    protected String mEndpointRevision;
    protected long mParseTimeInMs;
    protected long mServerExecTimeInMs;
    protected UUID mUuid;
    
    protected FalcorVolleyWebClientRequest(final Context mContext, final ServiceAgent.ConfigurationAgentInterface mConfig) {
        super(0);
        this.mUuid = UUID.randomUUID();
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
            httpResponse.setApiScriptExecutionTime((int)this.mApiScriptExecTimeInMs);
            httpResponse.setEndpointRevision(this.mEndpointRevision);
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
    
    public String getRequestId() {
        return this.mUuid.toString();
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
    protected Response<T> parseNetworkResponse(final NetworkResponse p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: ifnull          181
        //     4: aload_1        
        //     5: getfield        com/android/volley/NetworkResponse.headers:Ljava/util/Map;
        //     8: ifnull          181
        //    11: aload_1        
        //    12: getfield        com/android/volley/NetworkResponse.headers:Ljava/util/Map;
        //    15: ldc             "X-Netflix.api-script-execution-time"
        //    17: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //    22: checkcast       Ljava/lang/String;
        //    25: astore_2       
        //    26: aload_1        
        //    27: getfield        com/android/volley/NetworkResponse.headers:Ljava/util/Map;
        //    30: ldc             "X-Netflix.execution-time"
        //    32: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //    37: checkcast       Ljava/lang/String;
        //    40: astore_3       
        //    41: aload_0        
        //    42: aload_1        
        //    43: getfield        com/android/volley/NetworkResponse.headers:Ljava/util/Map;
        //    46: ldc             "X-Netflix.api-script-revision"
        //    48: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //    53: checkcast       Ljava/lang/String;
        //    56: putfield        com/netflix/mediaclient/service/webclient/volley/FalcorVolleyWebClientRequest.mEndpointRevision:Ljava/lang/String;
        //    59: ldc             "nf_volleyrequest"
        //    61: iconst_3       
        //    62: invokestatic    com/netflix/mediaclient/Log.isLoggable:(Ljava/lang/String;I)Z
        //    65: ifeq            117
        //    68: ldc             "nf_volleyrequest"
        //    70: new             Ljava/lang/StringBuilder;
        //    73: dup            
        //    74: invokespecial   java/lang/StringBuilder.<init>:()V
        //    77: ldc_w           "execTime: "
        //    80: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    83: aload_2        
        //    84: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    87: ldc_w           ", total server time "
        //    90: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    93: aload_3        
        //    94: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    97: ldc_w           ", revision: "
        //   100: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   103: aload_0        
        //   104: getfield        com/netflix/mediaclient/service/webclient/volley/FalcorVolleyWebClientRequest.mEndpointRevision:Ljava/lang/String;
        //   107: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   110: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   113: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   116: pop            
        //   117: aload_3        
        //   118: invokestatic    com/netflix/mediaclient/util/StringUtils.isNotEmpty:(Ljava/lang/String;)Z
        //   121: ifeq            132
        //   124: aload_0        
        //   125: aload_3        
        //   126: invokestatic    java/lang/Long.parseLong:(Ljava/lang/String;)J
        //   129: putfield        com/netflix/mediaclient/service/webclient/volley/FalcorVolleyWebClientRequest.mServerExecTimeInMs:J
        //   132: aload_2        
        //   133: invokestatic    com/netflix/mediaclient/util/StringUtils.isNotEmpty:(Ljava/lang/String;)Z
        //   136: ifeq            147
        //   139: aload_0        
        //   140: aload_2        
        //   141: invokestatic    java/lang/Long.parseLong:(Ljava/lang/String;)J
        //   144: putfield        com/netflix/mediaclient/service/webclient/volley/FalcorVolleyWebClientRequest.mApiScriptExecTimeInMs:J
        //   147: aload_0        
        //   148: aload_1        
        //   149: invokespecial   com/netflix/mediaclient/service/webclient/volley/VolleyWebClientRequest.parseNetworkResponse:(Lcom/android/volley/NetworkResponse;)Lcom/android/volley/Response;
        //   152: areturn        
        //   153: astore_3       
        //   154: ldc             "nf_volleyrequest"
        //   156: ldc_w           "Failed to parse server execution time!"
        //   159: aload_3        
        //   160: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   163: pop            
        //   164: goto            132
        //   167: astore_2       
        //   168: ldc             "nf_volleyrequest"
        //   170: ldc_w           "Failed to parse api script execution time!"
        //   173: aload_2        
        //   174: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   177: pop            
        //   178: goto            147
        //   181: ldc             "nf_volleyrequest"
        //   183: ldc_w           "execTime not found!"
        //   186: invokestatic    com/netflix/mediaclient/Log.w:(Ljava/lang/String;Ljava/lang/String;)I
        //   189: pop            
        //   190: goto            147
        //    Signature:
        //  (Lcom/android/volley/NetworkResponse;)Lcom/android/volley/Response<TT;>;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  124    132    153    167    Ljava/lang/Throwable;
        //  139    147    167    181    Ljava/lang/Throwable;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0147:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:238)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:138)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
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
