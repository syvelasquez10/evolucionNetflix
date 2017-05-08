// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient.volley;

import android.os.SystemClock;
import com.android.volley.Response;
import com.android.volley.NetworkResponse;
import com.netflix.mediaclient.util.StringUtils;
import java.util.HashMap;
import com.android.volley.AuthFailureError;
import java.util.Map;
import java.util.Iterator;
import com.netflix.mediaclient.service.offline.agent.PlayabilityEnforcer;
import com.netflix.mediaclient.service.logging.client.model.Error;
import com.netflix.mediaclient.service.logging.client.model.DeepErrorElement;
import com.netflix.mediaclient.service.logging.client.model.FalkorPathResult;
import java.util.ArrayList;
import com.netflix.mediaclient.service.logging.client.model.HttpResponse;
import com.netflix.mediaclient.android.app.NetflixStatus;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.util.log.ApmLogUtils;
import java.util.List;
import com.netflix.mediaclient.util.log.ConsolidatedLoggingUtils;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import com.netflix.mediaclient.util.FetchErrorUtils;
import com.netflix.mediaclient.util.VolleyUtils;
import com.netflix.mediaclient.StatusCode;
import com.netflix.mediaclient.Log;
import com.android.volley.VolleyError;
import com.netflix.mediaclient.util.UriUtil;
import java.util.UUID;
import android.content.Context;
import com.netflix.mediaclient.service.webclient.ApiEndpointRegistry;

public abstract class FalkorVolleyWebClientRequest<T> extends VolleyWebClientRequest<T>
{
    public static final String ENDPOINT_REVISION = "X-Netflix.api-script-revision";
    public static final String NETFLIX_API_SCRIPT_EXECUTION_TIME_HEADER = "X-Netflix.api-script-execution-time";
    public static final String NETFLIX_SERVER_EXECUTION_TIME_HEADER = "X-Netflix.execution-time";
    public static final String OPTIONAL_URL_REQUEST_PARAM_KEY = "&param=";
    private static final String PARAM_NAME_CALLPATH = "callPath";
    private static final String PARAM_NAME_PATH = "path";
    public static final String PARAM_NAME_PATH_SUFFIX = "pathSuffix";
    public static final String PARAM_NAME_SIGNATURE = "signature";
    private static final String TAG = "FalkorVolleyWebClientRequest";
    protected ApiEndpointRegistry mApiEndpointRegistry;
    protected long mApiScriptExecTimeInMs;
    protected Context mContext;
    protected String mEndpointRevision;
    protected long mParseTimeInMs;
    protected long mServerExecTimeInMs;
    protected UUID mUuid;
    
    protected FalkorVolleyWebClientRequest(final Context context) {
        super(0);
        this.mServerExecTimeInMs = -1L;
        this.initParam(context);
    }
    
    protected FalkorVolleyWebClientRequest(final Context context, final int n) {
        super(n);
        this.mServerExecTimeInMs = -1L;
        this.initParam(context);
    }
    
    private void initParam(final Context mContext) {
        this.mUuid = UUID.randomUUID();
        this.mContext = mContext;
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
        final long durationTimeMs = this.getDurationTimeMs();
        if (Log.isLoggable()) {
            Log.d("FalkorVolleyWebClientRequest", "request duration time (ms): " + durationTimeMs + ", class: " + this.getClass() + ", error: " + volleyError);
        }
        final NetflixStatus status = VolleyUtils.getStatus(volleyError, this.mErrorLogger, StatusCode.INT_ERR_FETCH_ERROR);
        if (this.mContext != null) {
            if (FetchErrorUtils.isAccountError(status.getStatusCode())) {
                FetchErrorUtils.notifyOthersOfAccountErrors(this.mContext, status.getStatusCode());
            }
            else {
                ApmLogUtils.reportDataRequestEnded(this.mContext, String.valueOf(this.mUuid), IClientLogging$CompletionReason.failed, ConsolidatedLoggingUtils.toFalkorPathResultList((List)this.getPQLQueries()), ConsolidatedLoggingUtils.toError(volleyError), ConsolidatedLoggingUtils.createHttpResponse(durationTimeMs, this.getResponseSizeInBytes()));
            }
        }
        this.onFailure(status);
    }
    
    @Override
    protected void deliverResponse(final T t) {
        super.deliverResponse(t);
        final long durationTimeMs = this.getDurationTimeMs();
        if (Log.isLoggable()) {
            Log.d("FalkorVolleyWebClientRequest", "request duration time (ms): " + durationTimeMs + ", class: " + this.getClass());
        }
        if (this.mContext != null) {
            Log.d("FalkorVolleyWebClientRequest", "Report data request success");
            final HttpResponse httpResponse = new HttpResponse();
            httpResponse.setResponseTime((int)durationTimeMs);
            httpResponse.setParseTime((int)this.mParseTimeInMs);
            httpResponse.setMimeType("text/x-json");
            httpResponse.setServerExecutionTime((int)this.mServerExecTimeInMs);
            httpResponse.setContentLength(this.getResponseSizeInBytes());
            httpResponse.setApiScriptExecutionTime((int)this.mApiScriptExecTimeInMs);
            httpResponse.setEndpointRevision(this.mEndpointRevision);
            final List<String> pqlQueries = this.getPQLQueries();
            int size = 0;
            if (pqlQueries != null) {
                size = pqlQueries.size();
            }
            final ArrayList list = new ArrayList<FalkorPathResult>(size);
            if (pqlQueries != null) {
                final Iterator<String> iterator = pqlQueries.iterator();
                while (iterator.hasNext()) {
                    list.add(new FalkorPathResult(iterator.next(), true, null));
                }
            }
            ApmLogUtils.reportDataRequestEnded(this.mContext, String.valueOf(this.mUuid), IClientLogging$CompletionReason.success, (List)list, (Error)null, httpResponse);
            PlayabilityEnforcer.updateLastContactNetflix(this.mContext);
        }
    }
    
    Context getContext() {
        return this.mContext;
    }
    
    @Override
    public Map<String, String> getHeaders() {
        if (this.isAuthorizationRequired() && this.areNetflixCookiesNull()) {
            throw new AuthFailureError("Can't build valid headers. Cookies are null. url=" + this.getUrl());
        }
        Map<String, String> headers;
        if ((headers = super.getHeaders()) == null) {
            headers = new HashMap<String, String>();
        }
        headers.put("X-Netflix.request.uuid", "" + this.mUuid);
        return headers;
    }
    
    @Override
    protected String getMethodType() {
        return "get";
    }
    
    protected abstract List<String> getPQLQueries();
    
    public String getPQLQueriesRepresentationAsString() {
        if (this.getPQLQueries() == null) {
            return "null";
        }
        if (this.getPQLQueries().size() == 1) {
            return this.getPQLQueries().get(0);
        }
        return this.getPQLQueries().toString();
    }
    
    protected String getQueryPathName() {
        if ("get".equals(this.getMethodType())) {
            return "path";
        }
        return "callPath";
    }
    
    protected String getRawPQLQuery() {
        final List<String> pqlQueries = this.getPQLQueries();
        if (pqlQueries == null) {
            throw new IllegalArgumentException("List of queries is null!");
        }
        final StringBuilder sb = new StringBuilder();
        final Iterator<String> iterator = pqlQueries.iterator();
        while (iterator.hasNext()) {
            sb.append(urlEncodPQLParam(this.getQueryPathName(), iterator.next()));
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
        if (this.shouldMaterializeRequest()) {
            sb.append(StringUtils.buildUnencodedUrlParam("materialize", "true"));
        }
        sb.append(rawPQLQuery);
        final String optionalParams = this.getOptionalParams();
        if (StringUtils.isNotEmpty(optionalParams)) {
            sb.append(optionalParams);
        }
        this.storeReqNetflixId(this.getCurrentNetflixId());
        string = sb.toString();
        if (Log.isLoggable()) {
            Log.v("FalkorVolleyWebClientRequest", "VolleyWebClientRequest URL = " + string);
        }
        if (Log.isLoggable() && string.length() > 2000) {
            Log.w("FalkorVolleyWebClientRequest", "URL length is over 2000 chars... this will probably cause problems");
            Log.w("FalkorVolleyWebClientRequest", "URL: " + string);
        }
        return string;
    }
    
    protected abstract T parseFalkorResponse(final String p0);
    
    @Override
    protected Response<T> parseNetworkResponse(final NetworkResponse p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: ifnull          178
        //     4: aload_1        
        //     5: getfield        com/android/volley/NetworkResponse.headers:Ljava/util/Map;
        //     8: ifnull          178
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
        //    56: putfield        com/netflix/mediaclient/service/webclient/volley/FalkorVolleyWebClientRequest.mEndpointRevision:Ljava/lang/String;
        //    59: invokestatic    com/netflix/mediaclient/Log.isLoggable:()Z
        //    62: ifeq            114
        //    65: ldc             "FalkorVolleyWebClientRequest"
        //    67: new             Ljava/lang/StringBuilder;
        //    70: dup            
        //    71: invokespecial   java/lang/StringBuilder.<init>:()V
        //    74: ldc_w           "execTime: "
        //    77: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    80: aload_2        
        //    81: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    84: ldc_w           ", total server time "
        //    87: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    90: aload_3        
        //    91: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    94: ldc_w           ", revision: "
        //    97: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   100: aload_0        
        //   101: getfield        com/netflix/mediaclient/service/webclient/volley/FalkorVolleyWebClientRequest.mEndpointRevision:Ljava/lang/String;
        //   104: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   107: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   110: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   113: pop            
        //   114: aload_3        
        //   115: invokestatic    com/netflix/mediaclient/util/StringUtils.isNotEmpty:(Ljava/lang/String;)Z
        //   118: ifeq            129
        //   121: aload_0        
        //   122: aload_3        
        //   123: invokestatic    java/lang/Long.parseLong:(Ljava/lang/String;)J
        //   126: putfield        com/netflix/mediaclient/service/webclient/volley/FalkorVolleyWebClientRequest.mServerExecTimeInMs:J
        //   129: aload_2        
        //   130: invokestatic    com/netflix/mediaclient/util/StringUtils.isNotEmpty:(Ljava/lang/String;)Z
        //   133: ifeq            144
        //   136: aload_0        
        //   137: aload_2        
        //   138: invokestatic    java/lang/Long.parseLong:(Ljava/lang/String;)J
        //   141: putfield        com/netflix/mediaclient/service/webclient/volley/FalkorVolleyWebClientRequest.mApiScriptExecTimeInMs:J
        //   144: aload_0        
        //   145: aload_1        
        //   146: invokespecial   com/netflix/mediaclient/service/webclient/volley/VolleyWebClientRequest.parseNetworkResponse:(Lcom/android/volley/NetworkResponse;)Lcom/android/volley/Response;
        //   149: areturn        
        //   150: astore_3       
        //   151: ldc             "FalkorVolleyWebClientRequest"
        //   153: ldc_w           "Failed to parse server execution time!"
        //   156: aload_3        
        //   157: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   160: pop            
        //   161: goto            129
        //   164: astore_2       
        //   165: ldc             "FalkorVolleyWebClientRequest"
        //   167: ldc_w           "Failed to parse api script execution time!"
        //   170: aload_2        
        //   171: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   174: pop            
        //   175: goto            144
        //   178: ldc             "FalkorVolleyWebClientRequest"
        //   180: ldc_w           "execTime not found!"
        //   183: invokestatic    com/netflix/mediaclient/Log.w:(Ljava/lang/String;Ljava/lang/String;)I
        //   186: pop            
        //   187: goto            144
        //    Signature:
        //  (Lcom/android/volley/NetworkResponse;)Lcom/android/volley/Response<TT;>;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  121    129    150    164    Ljava/lang/Throwable;
        //  136    144    164    178    Ljava/lang/Throwable;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0144:
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
    protected T parseResponse(final String s) {
        this.mParseTimeInMs = SystemClock.elapsedRealtime();
        T falkorResponse;
        try {
            falkorResponse = this.parseFalkorResponse(s);
            this.mParseTimeInMs = SystemClock.elapsedRealtime() - this.mParseTimeInMs;
            if (Log.isLoggable()) {
                Log.v("FalkorVolleyWebClientRequest", "parse time (ms): " + this.mParseTimeInMs + ", class: " + this.getClass());
            }
            if (!this.parsedResponseCanBeNull() && falkorResponse == null) {
                throw new FalkorException("Parsing returned null.");
            }
        }
        catch (Exception ex) {
            if (ex instanceof FalkorException || ex instanceof StatusCodeError) {
                throw (VolleyError)ex;
            }
            throw new VolleyError(ex);
        }
        return falkorResponse;
    }
    
    void setApiEndpointRegistry(final ApiEndpointRegistry mApiEndpointRegistry) {
        this.mApiEndpointRegistry = mApiEndpointRegistry;
    }
    
    protected boolean shouldMaterializeRequest() {
        return false;
    }
}
