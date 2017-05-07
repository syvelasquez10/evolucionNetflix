// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.falkor;

import com.netflix.mediaclient.service.NetflixService;
import java.util.LinkedHashSet;
import java.util.Collections;
import com.netflix.model.branches.FalkorObject;
import java.io.IOException;
import java.io.Flushable;
import com.netflix.mediaclient.service.falkor.Falkor$SimilarRequestType;
import com.netflix.mediaclient.servicemgr.model.VideoType;
import com.netflix.mediaclient.service.webclient.volley.FalcorParseUtils;
import com.netflix.mediaclient.servicemgr.model.JsonPopulator;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.netflix.mediaclient.android.app.BackgroundTask;
import com.netflix.mediaclient.servicemgr.model.LoMo;
import com.netflix.mediaclient.servicemgr.model.genre.GenreList;
import java.util.Iterator;
import java.util.Map;
import com.netflix.mediaclient.service.webclient.ApiEndpointRegistry$ResponsePathFormat;
import com.netflix.mediaclient.service.browse.PostToHandlerCallbackWrapper;
import android.os.Looper;
import com.netflix.mediaclient.service.webclient.volley.FalcorVolleyWebClient;
import android.os.Handler;
import com.google.gson.JsonParser;
import java.util.Collection;
import java.util.ArrayList;
import com.netflix.mediaclient.util.LogUtils;
import com.netflix.mediaclient.util.DataUtil$StringPair;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.util.ThreadUtils;
import android.content.Context;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.webclient.volley.FalcorVolleyWebClientRequest;
import java.util.List;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.service.browse.BrowseAgentCallback;

abstract class CachedModelProxy$CmpTask implements Runnable
{
    private final BrowseAgentCallback callback;
    private CachedModelProxy$GetResult getResult;
    final /* synthetic */ CachedModelProxy this$0;
    
    private CachedModelProxy$CmpTask(final CachedModelProxy this$0, final BrowseAgentCallback callback) {
        this.this$0 = this$0;
        this.callback = callback;
    }
    
    private FalcorVolleyWebClientRequest createRequest(final List<PQL> list) {
        if (Log.isLoggable("CachedModelProxy", 2)) {
            Log.v("CachedModelProxy", "Creating remote request for task " + this.getClass().getSimpleName() + " with pqls: " + list);
        }
        return new CachedModelProxy$CmpTask$1(this, (Context)this.this$0.getService(), list);
    }
    
    private void handleFailure(final BrowseAgentCallback browseAgentCallback, final Status status) {
        this.callbackForFailure(browseAgentCallback, status);
    }
    
    private void handleSuccess() {
        ThreadUtils.assertNotOnMain();
        final BrowseAgentCallback access$400 = this.this$0.createHandlerWrapper(this.callback);
        if (this.getResult == null) {
            Log.w("CachedModelProxy", "GetResult is null - shouldn't happen - forcing failure");
            this.handleFailure(access$400, CommonStatus.INTERNAL_ERROR);
            return;
        }
        this.fetchResultsAndCallbackForSuccess(access$400, this.getResult);
    }
    
    protected abstract void buildPqlList(final List<PQL> p0);
    
    protected abstract void callbackForFailure(final BrowseAgentCallback p0, final Status p1);
    
    protected abstract void fetchResultsAndCallbackForSuccess(final BrowseAgentCallback p0, final CachedModelProxy$GetResult p1);
    
    protected List<DataUtil$StringPair> getOptionalRequestParams() {
        return null;
    }
    
    @Override
    public final void run() {
        ThreadUtils.assertNotOnMain();
        final long nanoTime = System.nanoTime();
        LogUtils.logCurrentThreadName("CachedModelProxy", "running" + this.getClass().getSimpleName());
        final ArrayList<PQL> list = new ArrayList<PQL>();
        this.buildPqlList(list);
        (this.getResult = this.this$0.get(list)).printPaths("CachedModelProxy");
        if (this.getResult.hasMissingPaths()) {
            final ArrayList<PQL> list2 = new ArrayList<PQL>(this.getResult.missingPqls);
            if (this.shouldCollapsePql()) {
                PQL.collapse(list2);
                if (Log.isLoggable("CachedModelProxy", 2)) {
                    Log.v("CachedModelProxy", "Collapsed paths to: " + list2);
                }
            }
            this.this$0.executeRequest(this.createRequest(list2));
        }
        else {
            if (Log.isLoggable("CachedModelProxy", 2)) {
                Log.v("CachedModelProxy", this.getClass().getSimpleName() + ": No missing paths found - all data is local to cache");
            }
            this.handleSuccess();
        }
        if (Log.isLoggable("CachedModelProxy", 2)) {
            Log.v("CachedModelProxy", this.getClass().getSimpleName() + " run() time ms: " + (System.nanoTime() - nanoTime) / 1000000L);
        }
    }
    
    protected boolean shouldCollapsePql() {
        return false;
    }
    
    protected boolean shouldUseCallMethod() {
        return false;
    }
}
