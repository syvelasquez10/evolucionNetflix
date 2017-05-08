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
import com.netflix.mediaclient.servicemgr.interface_.JsonPopulator;
import com.netflix.falkor.Undefined;
import com.netflix.falkor.Ref;
import com.netflix.mediaclient.util.JsonUtils;
import com.netflix.falkor.Sentinel;
import com.google.gson.JsonElement;
import java.util.Map;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.service.webclient.ApiEndpointRegistry$ResponsePathFormat;
import java.util.Collection;
import java.util.ArrayList;
import com.netflix.falkor.PQL;
import com.netflix.falkor.BranchNode;
import com.netflix.falkor.CachedModelProxy$GetResult;
import com.netflix.mediaclient.service.browse.BrowseAgentCallback;
import com.google.gson.JsonObject;
import com.netflix.falkor.CachedModelProxy;
import android.os.SystemClock;
import com.netflix.mediaclient.service.webclient.volley.FalkorParseUtils;
import com.netflix.mediaclient.service.falkor.Falkor;
import com.netflix.mediaclient.android.app.Status;
import com.android.volley.Request$Priority;
import java.util.Iterator;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.DataUtil;
import com.netflix.mediaclient.util.ThreadUtils;
import android.content.Context;
import com.netflix.mediaclient.util.DataUtil$StringPair;
import java.util.List;
import com.netflix.mediaclient.service.webclient.volley.FalkorVolleyWebClientRequest;

class CmpTask$1 extends FalkorVolleyWebClientRequest<Void>
{
    private final boolean notOnMain;
    private final List<DataUtil$StringPair> optionalRequestParams;
    private final List<String> pqls;
    private final long requestStartTime;
    final /* synthetic */ CmpTask this$0;
    private final boolean useAuthorization;
    private final boolean useCallMethod;
    final /* synthetic */ List val$requestPql;
    
    CmpTask$1(final CmpTask this$0, final Context context, final List val$requestPql) {
        this.this$0 = this$0;
        this.val$requestPql = val$requestPql;
        super(context);
        this.notOnMain = ThreadUtils.assertNotOnMain();
        this.pqls = (List<String>)DataUtil.createStringListFromList(this.val$requestPql);
        this.useCallMethod = this.this$0.shouldUseCallMethod();
        this.useAuthorization = this.this$0.shouldUseAuthorization();
        this.optionalRequestParams = this.this$0.getOptionalRequestParams();
        this.requestStartTime = -1L;
    }
    
    @Override
    protected String getMethodType() {
        if (this.useCallMethod) {
            return "call";
        }
        return "get";
    }
    
    @Override
    protected String getOptionalParams() {
        ThreadUtils.assertNotOnMain();
        String string;
        if (this.optionalRequestParams == null || this.optionalRequestParams.size() == 0) {
            string = null;
        }
        else {
            final StringBuilder sb = new StringBuilder();
            for (final DataUtil$StringPair dataUtil$StringPair : this.optionalRequestParams) {
                sb.append("&").append((String)dataUtil$StringPair.first).append("=").append((String)dataUtil$StringPair.second);
            }
            final String s = string = sb.toString();
            if (Log.isLoggable()) {
                Log.v("CachedModelProxy", "Sending optional url params: " + s);
                return s;
            }
        }
        return string;
    }
    
    @Override
    protected List<String> getPQLQueries() {
        return this.pqls;
    }
    
    @Override
    public Request$Priority getPriority() {
        if (this.this$0.getPriorityOverride() != null) {
            return this.this$0.getPriorityOverride();
        }
        return super.getPriority();
    }
    
    @Override
    protected boolean isAuthorizationRequired() {
        return this.useAuthorization;
    }
    
    @Override
    protected void markInFlight(final boolean b) {
        super.markInFlight(b);
        if (b) {
            this.this$0.onTaskStarted();
            return;
        }
        this.this$0.onTaskCompleted();
    }
    
    @Override
    protected void onFailure(final Status status) {
        ThreadUtils.assertOnMain();
        this.this$0.handleFailure(this.this$0.callback, status);
    }
    
    @Override
    protected void onSuccess(final Void void1) {
        ThreadUtils.assertOnMain();
    }
    
    @Override
    protected Void parseFalkorResponse(final String s) {
        ThreadUtils.assertNotOnMain();
        if (Falkor.ENABLE_VERBOSE_LOGGING) {
            Log.d("CachedModelProxy", "Response: " + s);
        }
        System.currentTimeMillis();
        final JsonObject asJsonObject = this.this$0.modelProxy.getJsonParser().parse(s).getAsJsonObject();
        if (FalkorParseUtils.hasErrors(asJsonObject)) {
            if (Log.isLoggable()) {
                Log.d("CachedModelProxy", "Found errors in json response: " + asJsonObject);
                Log.d("CachedModelProxy", "Error msg: " + FalkorParseUtils.getErrorMessage(asJsonObject, "CachedModelProxy"));
            }
            throw this.this$0.handleJsonError(asJsonObject);
        }
        if (this.this$0.shouldCustomHandleResponse()) {
            this.this$0.customHandleResponse(asJsonObject);
        }
        else {
            final long elapsedRealtime = SystemClock.elapsedRealtime();
            CachedModelProxy.setLastWriteTimeMS(elapsedRealtime);
            Log.v("CachedModelProxy", "parseFalkorResponse: current merge time - %d", elapsedRealtime);
            this.this$0.merge(asJsonObject.getAsJsonObject("value"), this.this$0.modelProxy.getRoot());
        }
        this.this$0.handleSuccess();
        return null;
    }
    
    @Override
    protected boolean parsedResponseCanBeNull() {
        return true;
    }
    
    @Override
    protected boolean shouldMaterializeRequest() {
        return true;
    }
}
