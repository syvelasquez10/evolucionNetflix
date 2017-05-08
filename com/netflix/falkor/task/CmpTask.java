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
import com.netflix.falkor.BranchNode;
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
            if (this.shouldCollapseMissingPql()) {
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
    
    private void merge(final JsonObject jsonObject, final BranchNode branchNode) {
    Label_0133_Outer:
        while (true) {
            while (true) {
            Label_0519:
                while (true) {
                    Map.Entry<String, JsonElement> entry = null;
                    String s = null;
                    BranchNode value = null;
                    Label_0218: {
                        synchronized (this) {
                            final Iterator<Map.Entry<String, JsonElement>> iterator = jsonObject.entrySet().iterator();
                            while (iterator.hasNext()) {
                                entry = (Map.Entry<String, JsonElement>)iterator.next();
                                s = entry.getKey();
                                final Object orCreate = branchNode.getOrCreate(s);
                                if (!(orCreate instanceof Sentinel) || JsonUtils.isNull((JsonElement)entry.getValue())) {
                                    break Label_0519;
                                }
                                if (Log.isLoggable()) {
                                    Log.d("CachedModelProxy", "Found sentinel at key: " + s + ", will replace with json data: " + entry.getValue());
                                }
                                value = ((Sentinel<BranchNode>)orCreate).getValue();
                                if (Falkor.ENABLE_VERBOSE_LOGGING) {
                                    Log.v("CachedModelProxy", "Curr node: " + branchNode.getClass().getSimpleName() + ", merging: " + s);
                                }
                                if (!(value instanceof BranchNode)) {
                                    break Label_0218;
                                }
                                this.merge(entry.getValue().getAsJsonObject(), value);
                            }
                            break;
                        }
                    }
                    if (value instanceof Ref) {
                        final Ref ref = (Ref)value;
                        final JsonElement jsonElement = entry.getValue();
                        if (jsonElement.isJsonArray()) {
                            ref.setRefPath(PQL.fromJsonArray(jsonElement.getAsJsonArray()));
                            continue Label_0133_Outer;
                        }
                        if (!jsonElement.isJsonObject()) {
                            continue Label_0133_Outer;
                        }
                        if (jsonElement.getAsJsonObject().has("_sentinel")) {
                            if (Falkor.ENABLE_VERBOSE_LOGGING) {
                                Log.v("CachedModelProxy", "key points to sentinel: " + Undefined.getInstance());
                            }
                            branchNode.set(s, Undefined.getInstance());
                            continue Label_0133_Outer;
                        }
                        if ("current".equals(s)) {
                            if (Falkor.ENABLE_VERBOSE_LOGGING) {
                                Log.v("CachedModelProxy", "json ref points to an ignored 'current' object: " + entry);
                                continue Label_0133_Outer;
                            }
                            continue Label_0133_Outer;
                        }
                        else {
                            if (Log.isLoggable()) {
                                Log.w("CachedModelProxy", "Don't know how to handle json: " + entry);
                                continue Label_0133_Outer;
                            }
                            continue Label_0133_Outer;
                        }
                    }
                    else {
                        if (value == null) {
                            continue Label_0133_Outer;
                        }
                        if (value instanceof JsonPopulator) {
                            ((JsonPopulator)value).populate(entry.getValue());
                            continue Label_0133_Outer;
                        }
                        if (Log.isLoggable()) {
                            Log.w("CachedModelProxy", "Creating duplicate Leaf object. JsonPopulator should be implemented by: " + ((JsonPopulator)value).getClass());
                        }
                        branchNode.set(s, FalkorParseUtils.createObjectFromJson("CachedModelProxy", entry.getValue(), ((JsonPopulator)value).getClass()));
                        continue Label_0133_Outer;
                    }
                    break;
                }
                continue;
            }
        }
    }
    // monitorexit(this)
    
    protected abstract void buildPqlList(final List<PQL> p0);
    
    protected abstract void callbackForFailure(final BrowseAgentCallback p0, final Status p1);
    
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
    
    protected boolean shouldCollapseMissingPql() {
        return false;
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
