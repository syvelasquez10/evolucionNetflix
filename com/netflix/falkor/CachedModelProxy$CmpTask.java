// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.falkor;

import com.netflix.model.leafs.advisory.ExpiringContentAdvisory$ContentAction;
import com.netflix.model.leafs.Video$Bookmark;
import com.netflix.mediaclient.servicemgr.Asset;
import com.google.gson.ExclusionStrategy;
import com.google.gson.GsonBuilder;
import java.io.Writer;
import com.netflix.model.leafs.social.IrisNotificationSummary;
import com.netflix.mediaclient.servicemgr.BillboardInteractionType;
import com.netflix.mediaclient.servicemgr.interface_.Video;
import com.netflix.mediaclient.service.NetflixService;
import com.netflix.model.branches.MementoVideoSwatch;
import java.util.LinkedHashSet;
import java.io.IOException;
import java.io.Flushable;
import com.netflix.mediaclient.service.falkor.Falkor$SimilarRequestType;
import com.netflix.mediaclient.ui.player.PostPlayRequestContext;
import com.fasterxml.jackson.core.JsonFactory;
import java.io.Reader;
import java.util.Date;
import java.lang.reflect.Type;
import com.google.gson.stream.JsonWriter;
import com.google.gson.Gson;
import com.netflix.model.leafs.Video$InQueue;
import com.netflix.model.branches.FalkorVideo;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import com.netflix.mediaclient.servicemgr.interface_.JsonPopulator;
import com.netflix.mediaclient.util.JsonUtils;
import com.google.gson.JsonElement;
import com.netflix.mediaclient.android.app.BackgroundTask;
import com.netflix.mediaclient.servicemgr.interface_.genre.GenreList;
import android.text.TextUtils;
import com.netflix.mediaclient.servicemgr.interface_.LoMo;
import java.util.Map;
import com.netflix.mediaclient.service.webclient.ApiEndpointRegistry$ResponsePathFormat;
import com.netflix.mediaclient.util.FileUtils;
import java.util.Iterator;
import java.util.Comparator;
import java.util.Collections;
import com.netflix.mediaclient.util.AlphanumComparator;
import com.netflix.mediaclient.service.browse.PostToHandlerCallbackWrapper;
import com.netflix.mediaclient.util.StringUtils;
import java.util.Arrays;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import com.netflix.model.branches.FalkorObject;
import android.util.Pair;
import com.netflix.mediaclient.servicemgr.interface_.LoMoType;
import android.os.Looper;
import com.netflix.mediaclient.service.webclient.volley.FalkorVolleyWebClient;
import android.os.Handler;
import com.google.gson.JsonParser;
import android.annotation.SuppressLint;
import com.netflix.mediaclient.util.UriUtil;
import com.netflix.mediaclient.android.app.NetflixStatus;
import com.netflix.mediaclient.StatusCode;
import com.netflix.mediaclient.util.LogUtils;
import com.netflix.mediaclient.service.webclient.volley.FalkorException;
import com.netflix.mediaclient.service.webclient.volley.FalkorParseUtils;
import com.android.volley.VolleyError;
import com.android.volley.Request$Priority;
import com.netflix.mediaclient.util.DataUtil$StringPair;
import com.google.gson.JsonObject;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.util.ThreadUtils;
import java.util.Collection;
import com.netflix.mediaclient.service.falkor.Falkor;
import java.util.ArrayList;
import android.content.Context;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.webclient.volley.FalkorVolleyWebClientRequest;
import java.util.List;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.service.browse.BrowseAgentCallback;

abstract class CachedModelProxy$CmpTask implements Runnable
{
    private final BrowseAgentCallback callback;
    private CachedModelProxy$GetResult getResult;
    private boolean isAllDataLocalToCache;
    private final long taskStartTime;
    final /* synthetic */ CachedModelProxy this$0;
    
    private CachedModelProxy$CmpTask(final CachedModelProxy this$0, final BrowseAgentCallback callback) {
        this.this$0 = this$0;
        this.taskStartTime = -1L;
        this.callback = callback;
    }
    
    private FalkorVolleyWebClientRequest createRequest(final List<PQL> list) {
        if (Log.isLoggable()) {
            Log.v("CachedModelProxy", "Creating remote request for task " + this.getClass().getSimpleName() + " with pqls: " + list);
        }
        return new CachedModelProxy$CmpTask$1(this, (Context)this.this$0.getService(), list);
    }
    
    private void doTask() {
        final ArrayList<PQL> list = new ArrayList<PQL>();
        this.buildPqlList(list);
        if (this.shouldSkipCache() || this.shouldUseCallMethod() || this.shouldCustomHandleResponse()) {
            if (Falkor.ENABLE_VERBOSE_LOGGING) {
                Log.v("CachedModelProxy", "Short-cutting to remote execution...");
            }
            this.this$0.executeRequest(this.createRequest(list));
            return;
        }
        (this.getResult = this.this$0.get(list)).printPaths("CachedModelProxy");
        if (this.getResult.hasMissingPaths() && !this.shouldUseCacheOnly() && !CachedModelProxy.FORCE_CMP_TO_LOCAL_CACHE) {
            final ArrayList<PQL> list2 = new ArrayList<PQL>(this.getResult.missingPqls);
            if (this.shouldCollapseMissingPql()) {
                PQL.collapse(list2);
                if (Falkor.ENABLE_VERBOSE_LOGGING) {
                    Log.v("CachedModelProxy", "Collapsed paths to: " + list2);
                }
            }
            this.this$0.executeRequest(this.createRequest(list2));
            return;
        }
        if (Log.isLoggable()) {
            Log.v("CachedModelProxy", String.format("%s: No missing paths found - all data is local to cache. shouldUseCacheOnly(): %b, FORCE_CMP_TO_LOCAL_CACHE: %b", this.getClass().getSimpleName(), this.shouldUseCacheOnly(), CachedModelProxy.FORCE_CMP_TO_LOCAL_CACHE));
        }
        this.isAllDataLocalToCache = true;
        this.handleSuccess();
    }
    
    private void handleFailure(final BrowseAgentCallback browseAgentCallback, final Status status) {
        this.callbackForFailure(browseAgentCallback, status);
        if (Log.isLoggable()) {
            Log.v("CachedModelProxy", "Called back for failure: " + this.getClass().getSimpleName());
        }
    }
    
    private void handleSuccess() {
        ThreadUtils.assertNotOnMain();
        final BrowseAgentCallback access$000 = this.this$0.createHandlerWrapper(this.callback);
        if (this.getResult == null && !this.shouldUseCallMethod() && !this.shouldCustomHandleResponse() && !this.shouldSkipCache()) {
            Log.w("CachedModelProxy", "GetResult is null - shouldn't happen - forcing failure");
            this.handleFailure(access$000, CommonStatus.INTERNAL_ERROR);
        }
        else {
            this.fetchResultsAndCallbackForSuccess(access$000, this.getResult);
            if (Log.isLoggable()) {
                Log.v("CachedModelProxy", "Results fetched - called back for success: " + this.getClass().getSimpleName());
            }
        }
    }
    
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
    
    protected VolleyError handleJsonError(final JsonObject jsonObject) {
        return new FalkorException("error found in json response - " + FalkorParseUtils.getErrorMessage(jsonObject, "CachedModelProxy"));
    }
    
    protected boolean isAllDataLocalToCache() {
        return this.isAllDataLocalToCache;
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
            final NetflixStatus netflixStatus = new NetflixStatus(StatusCode.INTERNAL_ERROR);
            netflixStatus.setDisplayMessage(false);
            netflixStatus.setMessage(ex.getMessage());
            this.handleFailure(this.this$0.createHandlerWrapper(this.callback), netflixStatus);
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
