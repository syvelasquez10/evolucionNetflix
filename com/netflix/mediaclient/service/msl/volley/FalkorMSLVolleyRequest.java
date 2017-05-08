// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.msl.volley;

import com.netflix.mediaclient.service.webclient.volley.StatusCodeError;
import android.os.SystemClock;
import com.android.volley.Response;
import com.android.volley.NetworkResponse;
import com.netflix.msl.client.ApiHttpWrapper;
import com.netflix.mediaclient.android.app.NetflixStatus;
import com.android.volley.VolleyError;
import com.netflix.mediaclient.util.VolleyUtils;
import com.netflix.mediaclient.StatusCode;
import java.util.Iterator;
import com.netflix.mediaclient.util.MultiValuedMap;
import com.netflix.mediaclient.util.MultiValuedHashMap;
import java.util.List;
import com.netflix.mediaclient.service.msl.client.AndroidMslClient;
import com.netflix.msl.MslException;
import com.netflix.mediaclient.service.msl.client.MslErrorException;
import com.netflix.android.org.json.JSONException;
import java.io.IOException;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.webclient.volley.FalkorException;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.msl.userauth.UserAuthenticationData;
import com.netflix.msl.userauth.NetflixIdAuthenticationData;
import com.netflix.mediaclient.servicemgr.IMSLClient$MSLUserCredentialRegistry;
import com.netflix.mediaclient.service.webclient.UserCredentialRegistry;
import com.netflix.mediaclient.servicemgr.IMSLClient$MSLApiUnwrappedParams;
import java.util.Map;
import com.netflix.mediaclient.service.webclient.ApiEndpointRegistry$ResponsePathFormat;

public abstract class FalkorMSLVolleyRequest<T> extends MSLVolleyRequest<T>
{
    private static final int MAX_NUMBER_OF_RETRIES = 2;
    public static final String OPTIONAL_URL_REQUEST_PARAM_KEY = "param";
    private static final String PARAM_NAME_CALLPATH = "callPath";
    private static final String PARAM_NAME_PATH = "path";
    public static final String PARAM_NAME_PATH_SUFFIX = "pathSuffix";
    public static final String PARAM_NAME_SIGNATURE = "signature";
    private static final String TAG = "FalkorMSLVolleyRequest";
    protected ApiEndpointRegistry$ResponsePathFormat mResponsePathFormat;
    private int mRetryAttempts;
    
    public FalkorMSLVolleyRequest() {
        super(0);
        this.mResponsePathFormat = ApiEndpointRegistry$ResponsePathFormat.HIERARCHICAL;
    }
    
    public FalkorMSLVolleyRequest(final ApiEndpointRegistry$ResponsePathFormat mResponsePathFormat) {
        super(0);
        this.mResponsePathFormat = mResponsePathFormat;
    }
    
    private IMSLClient$MSLApiUnwrappedParams getMSLApiUnwrappedParams(final Map<String, String> map) {
        this.getMSLHeaders();
        final String mslPayload = this.getMSLPayload();
        final String mslQuery = this.getMSLQuery();
        String s;
        if (this.getMethod() == 0) {
            s = "GET";
        }
        else {
            s = "POST";
        }
        return new IMSLClient$MSLApiUnwrappedParams(this.getMSLUri(), s, map, mslQuery, mslPayload);
    }
    
    private IMSLClient$MSLUserCredentialRegistry getUserAuthorizationData(final UserCredentialRegistry userCredentialRegistry) {
        return new FalkorMSLVolleyRequest$1(this, userCredentialRegistry.getCurrentProfileGuid(), (UserAuthenticationData)new NetflixIdAuthenticationData(userCredentialRegistry.getNetflixID(), userCredentialRegistry.getSecureNetflixID()));
    }
    
    protected static boolean isNotAuthorized(final String s) {
        return StringUtils.isNotEmpty(s) && FalkorException.isNotAuthorized(s.toLowerCase());
    }
    
    @Override
    public byte[] execute(final Map<String, String> map) {
        while (true) {
            final AndroidMslClient mslClient = this.getMSLClient();
            while (true) {
                Label_0297: {
                    Label_0290: {
                        try {
                            final IMSLClient$MSLApiUnwrappedParams mslApiUnwrappedParams = this.getMSLApiUnwrappedParams(map);
                            if (Log.isLoggable()) {
                                Log.d("FalkorMSLVolleyRequest", "API request: " + this.getClass().getSimpleName());
                                Log.d("FalkorMSLVolleyRequest", "API request params: " + mslApiUnwrappedParams);
                            }
                            if (this.getMSLUserCredentialRegistry() == null) {
                                break Label_0290;
                            }
                            final String userId = this.getMSLUserCredentialRegistry().getUserId();
                            final UserAuthenticationData userAuthenticationData = this.getMSLUserCredentialRegistry().getUserAuthenticationData();
                            if (Log.isLoggable()) {
                                Log.d("FalkorMSLVolleyRequest", "userId: " + userId);
                                Log.d("FalkorMSLVolleyRequest", "MSL UserAuthenticationData: " + userAuthenticationData);
                                break Label_0297;
                            }
                            break Label_0297;
                            return mslClient.apiRequest(mslClient.wrapApiRequest(mslApiUnwrappedParams.uri, mslApiUnwrappedParams.method, mslApiUnwrappedParams.mslHeaders, mslApiUnwrappedParams.mslQuery, mslApiUnwrappedParams.mslPayload).getBytes("UTF-8"), userId, userAuthenticationData);
                        }
                        catch (JSONException ex) {
                            Log.e("FalkorMSLVolleyRequest", ex, "API request failed with JSON exception", new Object[0]);
                            throw new IOException(ex);
                        }
                        catch (MslErrorException ex2) {
                            Log.e("FalkorMSLVolleyRequest", ex2, "API request failed with MSL error exception", new Object[0]);
                            throw new IOException(ex2);
                        }
                        catch (MslException ex3) {
                            Log.e("FalkorMSLVolleyRequest", (Throwable)ex3, "API request failed with MSL exception", new Object[0]);
                            final Throwable causeForMslException = MSLVolleyRequest.findCauseForMslException(ex3);
                            if (causeForMslException instanceof IOException) {
                                throw (IOException)causeForMslException;
                            }
                            throw new IOException((Throwable)ex3);
                        }
                    }
                    final UserAuthenticationData userAuthenticationData = null;
                    final String userId = null;
                    continue;
                }
                continue;
            }
        }
    }
    
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
    
    @Override
    protected Map<String, String> getParams() {
        final Map<String, String> params = super.getParams();
        Object o;
        if (params == null) {
            o = new MultiValuedHashMap();
        }
        else {
            o = params;
            if (!(params instanceof MultiValuedMap)) {
                o = new MultiValuedHashMap(params.size());
                ((MultiValuedMap)o).putAll((Map)params);
            }
        }
        ((Map<String, String>)o).put("method", this.getMethodType());
        if (this.shouldMaterializeRequest()) {
            ((Map<String, String>)o).put("materialize", "true");
        }
        final List<String> pqlQueries = this.getPQLQueries();
        if (pqlQueries == null) {
            throw new IllegalArgumentException("List of queries is null!");
        }
        final Iterator<String> iterator = pqlQueries.iterator();
        while (iterator.hasNext()) {
            ((Map<String, String>)o).put(this.getQueryPathName(), iterator.next());
        }
        return (Map<String, String>)o;
    }
    
    protected String getQueryPathName() {
        if ("get".equals(this.getMethodType())) {
            return "path";
        }
        return "callPath";
    }
    
    protected boolean handleNotAuthorized(final FalkorException ex) {
        final NetflixStatus status = VolleyUtils.getStatus((VolleyError)ex, this.mErrorLogger, StatusCode.INT_ERR_FALKOR_EXCEPTION);
        if (status == null || status.getStatusCode() != StatusCode.USER_NOT_AUTHORIZED) {
            Log.d("FalkorMSLVolleyRequest", "handleNotAuthorized:: regular API failure");
            return false;
        }
        Log.w("FalkorMSLVolleyRequest", "handleNotAuthorized:: User is not authorized, this most likely should NOT happen!");
        if (this.mRetryAttempts >= 2) {
            Log.d("FalkorMSLVolleyRequest", "handleNotAuthorized:: regular API failure");
            return false;
        }
        if (!this.mUserAgent.isUserLoggedIn()) {
            Log.d("FalkorMSLVolleyRequest", "handleNotAuthorized:: User is NOT currently logged in, pass this failure regular way...");
            return false;
        }
        final UserCredentialRegistry userCredentialRegistry = this.mUserAgent.getUserCredentialRegistry();
        if (userCredentialRegistry == null || StringUtils.isEmpty(userCredentialRegistry.getNetflixID()) || StringUtils.isEmpty(userCredentialRegistry.getSecureNetflixID())) {
            Log.w("FalkorMSLVolleyRequest", "handleNotAuthorized:: Missing cookies, force user out... This should NOT happen here!");
            this.mUserAgent.logoutUser();
            return false;
        }
        Log.d("FalkorMSLVolleyRequest", "handleNotAuthorized:: Mismatch between user agent and MSL store, user is logged in according to user agent. We have cookies, just retry");
        ++this.mRetryAttempts;
        this.setMSLUserCredentialRegistry(this.getUserAuthorizationData(userCredentialRegistry));
        return true;
    }
    
    protected T parseApiResponse(final ApiHttpWrapper apiHttpWrapper) {
        final String dataAsString = apiHttpWrapper.getDataAsString();
        Log.d("FalkorMSLVolleyRequest", "parseApiResponse: %s", dataAsString);
        return this.parseFalkorResponse(dataAsString);
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
        //     1: ifnull          248
        //     4: aload_1        
        //     5: getfield        com/android/volley/NetworkResponse.headers:Ljava/util/Map;
        //     8: ifnull          248
        //    11: aload_1        
        //    12: getfield        com/android/volley/NetworkResponse.headers:Ljava/util/Map;
        //    15: ldc_w           "X-Netflix.api-script-execution-time"
        //    18: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //    23: checkcast       Ljava/lang/String;
        //    26: astore_2       
        //    27: aload_1        
        //    28: getfield        com/android/volley/NetworkResponse.headers:Ljava/util/Map;
        //    31: ldc_w           "X-Netflix.execution-time"
        //    34: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //    39: checkcast       Ljava/lang/String;
        //    42: astore_3       
        //    43: aload_0        
        //    44: aload_1        
        //    45: getfield        com/android/volley/NetworkResponse.headers:Ljava/util/Map;
        //    48: ldc_w           "X-Netflix.api-script-revision"
        //    51: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //    56: checkcast       Ljava/lang/String;
        //    59: putfield        com/netflix/mediaclient/service/msl/volley/FalkorMSLVolleyRequest.mEndpointRevision:Ljava/lang/String;
        //    62: invokestatic    com/netflix/mediaclient/Log.isLoggable:()Z
        //    65: ifeq            117
        //    68: ldc             "FalkorMSLVolleyRequest"
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
        //   104: getfield        com/netflix/mediaclient/service/msl/volley/FalkorMSLVolleyRequest.mEndpointRevision:Ljava/lang/String;
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
        //   129: putfield        com/netflix/mediaclient/service/msl/volley/FalkorMSLVolleyRequest.mServerExecTimeInMs:J
        //   132: aload_2        
        //   133: invokestatic    com/netflix/mediaclient/util/StringUtils.isNotEmpty:(Ljava/lang/String;)Z
        //   136: ifeq            147
        //   139: aload_0        
        //   140: aload_2        
        //   141: invokestatic    java/lang/Long.parseLong:(Ljava/lang/String;)J
        //   144: putfield        com/netflix/mediaclient/service/msl/volley/FalkorMSLVolleyRequest.mApiScriptExecTimeInMs:J
        //   147: aload_1        
        //   148: ifnull          167
        //   151: aload_1        
        //   152: getfield        com/android/volley/NetworkResponse.data:[B
        //   155: ifnull          167
        //   158: aload_0        
        //   159: aload_1        
        //   160: getfield        com/android/volley/NetworkResponse.data:[B
        //   163: arraylength    
        //   164: putfield        com/netflix/mediaclient/service/msl/volley/FalkorMSLVolleyRequest.mResponseSizeInBytes:I
        //   167: aload_1        
        //   168: getfield        com/android/volley/NetworkResponse.data:[B
        //   171: astore_1       
        //   172: aload_0        
        //   173: invokevirtual   com/netflix/mediaclient/service/msl/volley/FalkorMSLVolleyRequest.getMSLClient:()Lcom/netflix/mediaclient/service/msl/client/AndroidMslClient;
        //   176: aload_1        
        //   177: invokevirtual   com/netflix/mediaclient/service/msl/client/AndroidMslClient.unwrapApiResponse:([B)Lcom/netflix/msl/client/ApiHttpWrapper;
        //   180: astore_1       
        //   181: aload_0        
        //   182: aload_1        
        //   183: invokevirtual   com/netflix/mediaclient/service/msl/volley/FalkorMSLVolleyRequest.parseResponse:(Lcom/netflix/msl/client/ApiHttpWrapper;)Ljava/lang/Object;
        //   186: astore_1       
        //   187: aload_0        
        //   188: invokevirtual   com/netflix/mediaclient/service/msl/volley/FalkorMSLVolleyRequest.parsedResponseCanBeNull:()Z
        //   191: ifne            315
        //   194: aload_1        
        //   195: ifnonnull       315
        //   198: new             Lcom/netflix/mediaclient/service/webclient/volley/ParseException;
        //   201: dup            
        //   202: ldc_w           "Parsing returned null."
        //   205: invokespecial   com/netflix/mediaclient/service/webclient/volley/ParseException.<init>:(Ljava/lang/String;)V
        //   208: invokestatic    com/android/volley/Response.error:(Lcom/android/volley/VolleyError;)Lcom/android/volley/Response;
        //   211: areturn        
        //   212: astore_3       
        //   213: ldc             "FalkorMSLVolleyRequest"
        //   215: aload_3        
        //   216: ldc_w           "Failed to parse server execution time!"
        //   219: iconst_0       
        //   220: anewarray       Ljava/lang/Object;
        //   223: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/Throwable;Ljava/lang/String;[Ljava/lang/Object;)I
        //   226: pop            
        //   227: goto            132
        //   230: astore_2       
        //   231: ldc             "FalkorMSLVolleyRequest"
        //   233: aload_2        
        //   234: ldc_w           "Failed to parse api script execution time!"
        //   237: iconst_0       
        //   238: anewarray       Ljava/lang/Object;
        //   241: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/Throwable;Ljava/lang/String;[Ljava/lang/Object;)I
        //   244: pop            
        //   245: goto            147
        //   248: ldc             "FalkorMSLVolleyRequest"
        //   250: ldc_w           "execTime not found!"
        //   253: invokestatic    com/netflix/mediaclient/Log.w:(Ljava/lang/String;Ljava/lang/String;)I
        //   256: pop            
        //   257: goto            147
        //   260: astore_1       
        //   261: ldc             "FalkorMSLVolleyRequest"
        //   263: aload_1        
        //   264: ldc_w           "Failed to unwrap response "
        //   267: iconst_0       
        //   268: anewarray       Ljava/lang/Object;
        //   271: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/Throwable;Ljava/lang/String;[Ljava/lang/Object;)I
        //   274: pop            
        //   275: new             Lcom/netflix/mediaclient/service/webclient/volley/ParseException;
        //   278: dup            
        //   279: aload_1        
        //   280: invokespecial   com/netflix/mediaclient/service/webclient/volley/ParseException.<init>:(Ljava/lang/Throwable;)V
        //   283: invokestatic    com/android/volley/Response.error:(Lcom/android/volley/VolleyError;)Lcom/android/volley/Response;
        //   286: areturn        
        //   287: astore_1       
        //   288: aload_1        
        //   289: instanceof      Lcom/android/volley/VolleyError;
        //   292: ifeq            303
        //   295: aload_1        
        //   296: checkcast       Lcom/android/volley/VolleyError;
        //   299: invokestatic    com/android/volley/Response.error:(Lcom/android/volley/VolleyError;)Lcom/android/volley/Response;
        //   302: areturn        
        //   303: new             Lcom/android/volley/VolleyError;
        //   306: dup            
        //   307: aload_1        
        //   308: invokespecial   com/android/volley/VolleyError.<init>:(Ljava/lang/Throwable;)V
        //   311: invokestatic    com/android/volley/Response.error:(Lcom/android/volley/VolleyError;)Lcom/android/volley/Response;
        //   314: areturn        
        //   315: aload_1        
        //   316: aconst_null    
        //   317: invokestatic    com/android/volley/Response.success:(Ljava/lang/Object;Lcom/android/volley/Cache$Entry;)Lcom/android/volley/Response;
        //   320: areturn        
        //    Signature:
        //  (Lcom/android/volley/NetworkResponse;)Lcom/android/volley/Response<TT;>;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                        
        //  -----  -----  -----  -----  --------------------------------------------
        //  124    132    212    230    Ljava/lang/Throwable;
        //  139    147    230    248    Ljava/lang/Throwable;
        //  167    181    260    287    Lcom/netflix/android/org/json/JSONException;
        //  181    187    287    315    Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 148, Size: 148
        //     at java.util.ArrayList.rangeCheck(ArrayList.java:653)
        //     at java.util.ArrayList.get(ArrayList.java:429)
        //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3303)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:113)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:210)
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
    
    protected T parseResponse(final ApiHttpWrapper apiHttpWrapper) {
        this.mParseTimeInMs = SystemClock.elapsedRealtime();
        T apiResponse;
        try {
            apiResponse = this.parseApiResponse(apiHttpWrapper);
            this.mParseTimeInMs = SystemClock.elapsedRealtime() - this.mParseTimeInMs;
            if (Log.isLoggable()) {
                Log.v("FalkorMSLVolleyRequest", "parse time (ms): " + this.mParseTimeInMs + ", class: " + this.getClass());
            }
            if (!this.parsedResponseCanBeNull() && apiResponse == null) {
                throw new FalkorException("Parsing returned null.");
            }
        }
        catch (Exception ex) {
            if (ex instanceof FalkorException) {
                this.mUserIsNotLoggedInRetryRequest = this.handleNotAuthorized((FalkorException)ex);
                throw (VolleyError)ex;
            }
            if (ex instanceof StatusCodeError) {
                throw (VolleyError)ex;
            }
            throw new VolleyError(ex);
        }
        return apiResponse;
    }
    
    protected boolean shouldMaterializeRequest() {
        return false;
    }
}
