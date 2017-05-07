// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.falkor;

import com.netflix.model.leafs.Video$Bookmark;
import com.netflix.mediaclient.servicemgr.Asset;
import com.netflix.model.leafs.social.SocialNotificationSummary;
import com.netflix.mediaclient.servicemgr.BillboardInteractionType;
import com.netflix.mediaclient.servicemgr.interface_.Video;
import com.netflix.mediaclient.service.NetflixService;
import java.util.LinkedHashSet;
import java.io.IOException;
import java.io.Flushable;
import com.netflix.mediaclient.service.falkor.Falkor$SimilarRequestType;
import com.netflix.mediaclient.util.StringUtils;
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
import java.util.Comparator;
import java.util.Collections;
import com.netflix.mediaclient.util.AlphanumComparator;
import com.netflix.mediaclient.service.browse.PostToHandlerCallbackWrapper;
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
import com.android.volley.VolleyError;
import com.netflix.mediaclient.android.app.CommonStatus;
import java.util.Collection;
import java.util.ArrayList;
import com.netflix.mediaclient.service.browse.BrowseAgentCallback;
import com.google.gson.JsonObject;
import com.netflix.mediaclient.service.webclient.volley.FalkorParseUtils;
import com.netflix.mediaclient.service.falkor.Falkor;
import com.netflix.mediaclient.android.app.Status;
import java.util.Iterator;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.DataUtil;
import com.netflix.mediaclient.util.ThreadUtils;
import android.content.Context;
import com.netflix.mediaclient.util.DataUtil$StringPair;
import java.util.List;
import com.netflix.mediaclient.service.webclient.volley.FalkorVolleyWebClientRequest;

class CachedModelProxy$CmpTask$1 extends FalkorVolleyWebClientRequest<Void>
{
    private final boolean notOnMain;
    private final List<DataUtil$StringPair> optionalRequestParams;
    private final List<String> pqls;
    private final long requestStartTime;
    final /* synthetic */ CachedModelProxy$CmpTask this$1;
    private final boolean useCallMethod;
    final /* synthetic */ List val$requestPql;
    
    CachedModelProxy$CmpTask$1(final CachedModelProxy$CmpTask this$1, final Context context, final List val$requestPql) {
        this.this$1 = this$1;
        this.val$requestPql = val$requestPql;
        super(context);
        this.notOnMain = ThreadUtils.assertNotOnMain();
        this.pqls = DataUtil.createStringListFromList(this.val$requestPql);
        this.useCallMethod = this.this$1.shouldUseCallMethod();
        this.optionalRequestParams = this.this$1.getOptionalRequestParams();
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
    protected void onFailure(final Status status) {
        ThreadUtils.assertOnMain();
        this.this$1.handleFailure(this.this$1.callback, status);
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
        final JsonObject asJsonObject = this.this$1.this$0.jsonParser.parse(s).getAsJsonObject();
        if (FalkorParseUtils.hasErrors(asJsonObject)) {
            if (Log.isLoggable()) {
                Log.d("CachedModelProxy", "Found errors in json response: " + asJsonObject);
                Log.d("CachedModelProxy", "Error msg: " + FalkorParseUtils.getErrorMessage(asJsonObject));
            }
            throw this.this$1.handleJsonError(asJsonObject);
        }
        if (this.this$1.shouldCustomHandleResponse()) {
            this.this$1.customHandleResponse(asJsonObject);
        }
        else {
            this.this$1.this$0.merge(asJsonObject.getAsJsonObject("value"), this.this$1.this$0.root);
        }
        this.this$1.handleSuccess();
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
