// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.falkor.task;

import com.netflix.mediaclient.util.UriUtil;
import com.netflix.mediaclient.android.app.NetflixStatus;
import com.netflix.mediaclient.StatusCode;
import com.netflix.mediaclient.util.LogUtils;
import com.netflix.mediaclient.service.webclient.volley.FalkorException;
import com.android.volley.VolleyError;
import com.android.volley.Request$Priority;
import com.netflix.mediaclient.util.DataUtil$StringPair;
import java.util.Iterator;
import com.netflix.mediaclient.service.webclient.volley.FalkorParseUtils;
import com.netflix.mediaclient.servicemgr.interface_.JsonPopulator;
import com.netflix.falkor.Undefined;
import com.netflix.falkor.Ref;
import com.netflix.mediaclient.util.JsonUtils;
import com.netflix.falkor.Sentinel;
import com.google.gson.JsonElement;
import java.util.Map;
import com.netflix.falkor.cache.FalkorCacheHelperInterface;
import com.netflix.falkor.BranchNode;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.util.ThreadUtils;
import com.netflix.mediaclient.service.webclient.ApiEndpointRegistry$ResponsePathFormat;
import java.util.Collection;
import com.netflix.mediaclient.service.falkor.Falkor;
import java.util.ArrayList;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.webclient.volley.FalkorVolleyWebClientRequest;
import com.netflix.falkor.PQL;
import java.util.List;
import com.netflix.mediaclient.android.app.Status;
import com.google.gson.JsonObject;
import com.netflix.falkor.CachedModelProxy;
import com.netflix.falkor.CachedModelProxy$GetResult;
import com.netflix.mediaclient.service.browse.BrowseAgentCallback;

abstract class CmpTask implements Runnable
{
    protected static final boolean ENABLE_LOG_TIMING = false;
    private static boolean FORCE_CMP_TO_LOCAL_CACHE = false;
    public static final String JSON_VALUE = "value";
    protected static final String MS_SUFFIX = "ms";
    static final String REQUEST_PARAM_KEY = "param";
    static final String REQUEST_PATH_SUFFIX_KEY = "pathSuffix";
    protected static final String TAG = "CachedModelProxy";
    private static final String TAG_TIMING = "CachedModelProxy_Timing";
    protected final BrowseAgentCallback callback;
    private CachedModelProxy$GetResult getResult;
    private boolean isAllDataLocalToCache;
    protected CachedModelProxy modelProxy;
    protected final long taskStartTime;
    
    static {
        CmpTask.FORCE_CMP_TO_LOCAL_CACHE = CachedModelProxy.FORCE_CMP_TO_LOCAL_CACHE;
    }
    
    CmpTask(final CachedModelProxy modelProxy, final BrowseAgentCallback callback) {
        this.taskStartTime = -1L;
        this.callback = callback;
        this.modelProxy = modelProxy;
    }
    
    private FalkorVolleyWebClientRequest createRequest(final List<PQL> list) {
        if (Log.isLoggable()) {
            Log.v("CachedModelProxy", "Creating remote request for task " + this.getClass().getSimpleName() + " with pqls: " + list);
        }
        final CmpTask$1 cmpTask$1 = new CmpTask$1(this, this.modelProxy.getContext(), list);
        if (this.getTag() != null) {
            cmpTask$1.setTag(this.getTag());
        }
        return cmpTask$1;
    }
    
    private void doTask() {
        final ArrayList<PQL> list = new ArrayList<PQL>();
        this.buildPqlList(list);
        if (this.shouldSkipCache() || this.shouldUseCallMethod() || this.shouldCustomHandleResponse()) {
            if (Falkor.ENABLE_VERBOSE_LOGGING) {
                Log.v("CachedModelProxy", "Short-cutting to remote execution...");
            }
            this.executeRequest(this.createRequest(list));
            return;
        }
        (this.getResult = this.modelProxy.get(list)).printPaths("CachedModelProxy");
        if (this.getResult.hasMissingPaths() && !this.shouldUseCacheOnly() && !CmpTask.FORCE_CMP_TO_LOCAL_CACHE) {
            final ArrayList<PQL> list2 = new ArrayList<PQL>(this.getResult.missingPqls);
            if (this.shouldCollapseMissingPql(list2)) {
                PQL.collapse(list2);
                if (Falkor.ENABLE_VERBOSE_LOGGING) {
                    Log.v("CachedModelProxy", "Collapsed paths to: " + list2);
                }
            }
            this.executeRequest(this.createRequest(list2));
            return;
        }
        if (Log.isLoggable()) {
            Log.v("CachedModelProxy", String.format("%s: No missing paths found - all data is local to cache. shouldUseCacheOnly(): %b, FORCE_CMP_TO_LOCAL_CACHE: %b", this.getClass().getSimpleName(), this.shouldUseCacheOnly(), CmpTask.FORCE_CMP_TO_LOCAL_CACHE));
        }
        this.isAllDataLocalToCache = true;
        this.handleSuccess();
    }
    
    private void executeRequest(final FalkorVolleyWebClientRequest<?> falkorVolleyWebClientRequest) {
        this.modelProxy.getWebClient().sendRequest(falkorVolleyWebClientRequest, ApiEndpointRegistry$ResponsePathFormat.GRAPH);
    }
    
    private void handleFailure(final BrowseAgentCallback browseAgentCallback, final Status status) {
        this.callbackForFailure(browseAgentCallback, status);
        if (Log.isLoggable()) {
            Log.v("CachedModelProxy", "Called back for failure: " + this.getClass().getSimpleName());
        }
    }
    
    private void handleSuccess() {
        ThreadUtils.assertNotOnMain();
        final BrowseAgentCallback handlerWrapper = this.modelProxy.createHandlerWrapper(this.callback);
        if (this.getResult == null && !this.shouldUseCallMethod() && !this.shouldCustomHandleResponse() && !this.shouldSkipCache()) {
            Log.w("CachedModelProxy", "GetResult is null - shouldn't happen - forcing failure");
            this.handleFailure(handlerWrapper, CommonStatus.INT_ERR_CMP_RESP_NULL);
        }
        else {
            this.fetchResultsAndCallbackForSuccess(handlerWrapper, this.getResult);
            if (Log.isLoggable()) {
                Log.v("CachedModelProxy", "Results fetched - called back for success: " + this.getClass().getSimpleName());
            }
        }
    }
    
    private void merge(final JsonObject jsonObject, final BranchNode branchNode, final ArrayList<String> list, final FalkorCacheHelperInterface falkorCacheHelperInterface) {
        while (true) {
        Label_0070_Outer:
            while (true) {
                String s = null;
                Object value = null;
            Label_0301:
                while (true) {
                    Label_0289: {
                        synchronized (this) {
                            for (final Map.Entry<String, JsonElement> entry : jsonObject.entrySet()) {
                                s = entry.getKey();
                                Object orCreate = branchNode.getOrCreate(s);
                                if (list != null) {
                                    break Label_0289;
                                }
                                final ArrayList<String> list2 = new ArrayList<String>(4);
                                list2.add(s);
                                value = orCreate;
                                if (orCreate instanceof Sentinel) {
                                    value = orCreate;
                                    if (!JsonUtils.isNull((JsonElement)entry.getValue())) {
                                        if (Log.isLoggable()) {
                                            Log.d("CachedModelProxy", "Found sentinel at key: " + s + ", will replace with json data: " + entry.getValue());
                                        }
                                        orCreate = (value = ((Sentinel<BranchNode>)orCreate).getValue());
                                        if (falkorCacheHelperInterface != null) {
                                            falkorCacheHelperInterface.addToCache(list2, entry.getValue());
                                            value = orCreate;
                                        }
                                    }
                                }
                                if (Falkor.ENABLE_VERBOSE_LOGGING) {
                                    Log.v("CachedModelProxy", "Curr node: " + branchNode.getClass().getSimpleName() + ", merging: " + s);
                                }
                                if (!(value instanceof BranchNode)) {
                                    break Label_0301;
                                }
                                this.merge(entry.getValue().getAsJsonObject(), (BranchNode)value, list2, falkorCacheHelperInterface);
                            }
                            break;
                        }
                    }
                    final ArrayList<String> list2 = new ArrayList<String>(list);
                    continue;
                }
                if (value instanceof Ref) {
                    value = value;
                    final Map.Entry<String, JsonElement> entry;
                    final Object orCreate = entry.getValue();
                    if (falkorCacheHelperInterface != null) {
                        final ArrayList<String> list3;
                        falkorCacheHelperInterface.addToCache(list3, (JsonElement)orCreate);
                    }
                    if (((JsonElement)orCreate).isJsonArray()) {
                        ((Ref)value).setRefPath(PQL.fromJsonArray(((JsonElement)orCreate).getAsJsonArray()));
                        continue Label_0070_Outer;
                    }
                    if (!((JsonElement)orCreate).isJsonObject()) {
                        continue Label_0070_Outer;
                    }
                    if (((JsonElement)orCreate).getAsJsonObject().has("_sentinel")) {
                        if (Falkor.ENABLE_VERBOSE_LOGGING) {
                            Log.v("CachedModelProxy", "key points to sentinel: " + Undefined.getInstance());
                        }
                        branchNode.set(s, Undefined.getInstance());
                        continue Label_0070_Outer;
                    }
                    if ("current".equals(s)) {
                        if (Falkor.ENABLE_VERBOSE_LOGGING) {
                            Log.v("CachedModelProxy", "json ref points to an ignored 'current' object: " + entry);
                            continue Label_0070_Outer;
                        }
                        continue Label_0070_Outer;
                    }
                    else {
                        if (Log.isLoggable()) {
                            Log.w("CachedModelProxy", "Don't know how to handle json: " + entry);
                            continue Label_0070_Outer;
                        }
                        continue Label_0070_Outer;
                    }
                }
                else {
                    if (value == null) {
                        continue Label_0070_Outer;
                    }
                    final Map.Entry<String, JsonElement> entry;
                    final Object orCreate = entry.getValue();
                    if (falkorCacheHelperInterface != null) {
                        final ArrayList<String> list3;
                        falkorCacheHelperInterface.addToCache(list3, (JsonElement)orCreate);
                    }
                    if (value instanceof JsonPopulator) {
                        ((JsonPopulator)value).populate((JsonElement)orCreate);
                        continue Label_0070_Outer;
                    }
                    if (Log.isLoggable()) {
                        Log.w("CachedModelProxy", "Creating duplicate Leaf object. JsonPopulator should be implemented by: " + ((JsonPopulator)value).getClass());
                    }
                    branchNode.set(s, FalkorParseUtils.createObjectFromJson("CachedModelProxy", (JsonElement)orCreate, ((JsonPopulator)value).getClass()));
                    continue Label_0070_Outer;
                }
                break;
            }
        }
    }
    // monitorexit(this)
    
    private void mergeFalkorResponse(final JsonObject p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aconst_null    
        //     1: astore_3       
        //     2: aconst_null    
        //     3: astore_2       
        //     4: aload_0        
        //     5: monitorenter   
        //     6: aload_0        
        //     7: getfield        com/netflix/falkor/task/CmpTask.modelProxy:Lcom/netflix/falkor/CachedModelProxy;
        //    10: invokevirtual   com/netflix/falkor/CachedModelProxy.getContext:()Landroid/content/Context;
        //    13: invokestatic    com/netflix/falkor/cache/FalkorCacheHelperFactory.getHelper:(Landroid/content/Context;)Lcom/netflix/falkor/cache/FalkorCacheHelperInterface;
        //    16: astore          4
        //    18: aload           4
        //    20: astore_2       
        //    21: aload           4
        //    23: astore_3       
        //    24: aload           4
        //    26: invokeinterface com/netflix/falkor/cache/FalkorCacheHelperInterface.beginTransaction:()V
        //    31: aload           4
        //    33: astore_2       
        //    34: aload           4
        //    36: astore_3       
        //    37: aload_0        
        //    38: aload_1        
        //    39: ldc             "value"
        //    41: invokevirtual   com/google/gson/JsonObject.getAsJsonObject:(Ljava/lang/String;)Lcom/google/gson/JsonObject;
        //    44: aload_0        
        //    45: getfield        com/netflix/falkor/task/CmpTask.modelProxy:Lcom/netflix/falkor/CachedModelProxy;
        //    48: invokevirtual   com/netflix/falkor/CachedModelProxy.getRoot:()Lcom/netflix/falkor/BranchNode;
        //    51: aconst_null    
        //    52: aload           4
        //    54: invokespecial   com/netflix/falkor/task/CmpTask.merge:(Lcom/google/gson/JsonObject;Lcom/netflix/falkor/BranchNode;Ljava/util/ArrayList;Lcom/netflix/falkor/cache/FalkorCacheHelperInterface;)V
        //    57: aload           4
        //    59: astore_2       
        //    60: aload           4
        //    62: astore_3       
        //    63: aload           4
        //    65: invokeinterface com/netflix/falkor/cache/FalkorCacheHelperInterface.commitTransaction:()V
        //    70: aload           4
        //    72: ifnull          82
        //    75: aload           4
        //    77: invokeinterface com/netflix/falkor/cache/FalkorCacheHelperInterface.close:()V
        //    82: aload_0        
        //    83: monitorexit    
        //    84: return         
        //    85: astore_1       
        //    86: aload_2        
        //    87: ifnull          98
        //    90: aload_2        
        //    91: astore_3       
        //    92: aload_2        
        //    93: invokeinterface com/netflix/falkor/cache/FalkorCacheHelperInterface.cancelTransaction:()V
        //    98: aload_2        
        //    99: astore_3       
        //   100: aload_1        
        //   101: athrow         
        //   102: astore_1       
        //   103: aload_3        
        //   104: ifnull          113
        //   107: aload_3        
        //   108: invokeinterface com/netflix/falkor/cache/FalkorCacheHelperInterface.close:()V
        //   113: aload_1        
        //   114: athrow         
        //   115: astore_1       
        //   116: aload_0        
        //   117: monitorexit    
        //   118: aload_1        
        //   119: athrow         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  6      18     85     102    Ljava/lang/Throwable;
        //  6      18     102    115    Any
        //  24     31     85     102    Ljava/lang/Throwable;
        //  24     31     102    115    Any
        //  37     57     85     102    Ljava/lang/Throwable;
        //  37     57     102    115    Any
        //  63     70     85     102    Ljava/lang/Throwable;
        //  63     70     102    115    Any
        //  75     82     115    120    Any
        //  92     98     102    115    Any
        //  100    102    102    115    Any
        //  107    113    115    120    Any
        //  113    115    115    120    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0098:
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
    
    protected abstract void buildPqlList(final List<PQL> p0);
    
    protected abstract void callbackForFailure(final BrowseAgentCallback p0, final Status p1);
    
    protected boolean canHaveEmptyProfileGuidOverride() {
        return false;
    }
    
    protected void customHandleResponse(final JsonObject jsonObject) {
    }
    
    protected abstract void fetchResultsAndCallbackForSuccess(final BrowseAgentCallback p0, final CachedModelProxy$GetResult p1);
    
    protected List<DataUtil$StringPair> getOptionalRequestParams() {
        return null;
    }
    
    protected Request$Priority getPriorityOverride() {
        return null;
    }
    
    protected Object getTag() {
        return null;
    }
    
    protected VolleyError handleJsonError(final JsonObject jsonObject) {
        return new FalkorException("error found in json response - " + FalkorParseUtils.getErrorMessage(jsonObject, "CachedModelProxy"));
    }
    
    protected boolean isAllDataLocalToCache() {
        return this.isAllDataLocalToCache;
    }
    
    protected void onTaskCompleted() {
    }
    
    protected void onTaskStarted() {
    }
    
    @Override
    public final void run() {
        ThreadUtils.assertNotOnMain();
        if (Falkor.ENABLE_VERBOSE_LOGGING) {
            LogUtils.logCurrentThreadName("CachedModelProxy", "running " + this.getClass().getSimpleName());
        }
        try {
            this.doTask();
        }
        catch (Exception ex) {
            Log.handleException("CachedModelProxy", ex);
            final NetflixStatus netflixStatus = new NetflixStatus(StatusCode.INT_ERR_CMP);
            netflixStatus.setDisplayMessage(false);
            netflixStatus.setMessage(ex.getMessage());
            this.handleFailure(this.modelProxy.createHandlerWrapper(this.callback), netflixStatus);
        }
    }
    
    protected boolean shouldCollapseMissingPql(final List<PQL> list) {
        return list.size() > 20;
    }
    
    protected boolean shouldCustomHandleResponse() {
        return false;
    }
    
    protected boolean shouldDumpCacheToDiskUponMerge() {
        return false;
    }
    
    protected boolean shouldDumpHttpResponseToDisk() {
        return false;
    }
    
    protected boolean shouldSkipCache() {
        return false;
    }
    
    protected boolean shouldUseAuthorization() {
        return true;
    }
    
    protected boolean shouldUseCacheOnly() {
        return false;
    }
    
    protected boolean shouldUseCallMethod() {
        return false;
    }
    
    protected String urlEncode(final String s) {
        return UriUtil.urlEncodeParam(s);
    }
}
