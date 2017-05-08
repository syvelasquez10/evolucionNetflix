// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.falkor.task;

import com.netflix.falkor.CachedModelProxy$GetResult;
import com.netflix.mediaclient.service.webclient.volley.FalkorException;
import com.netflix.mediaclient.service.webclient.volley.FalkorParseUtils;
import com.netflix.mediaclient.service.falkor.Falkor;
import com.google.gson.JsonObject;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.Status;
import java.util.List;
import java.util.Locale;
import com.netflix.mediaclient.service.browse.BrowseAgentCallback;
import com.netflix.model.leafs.advisory.ExpiringContentAdvisory$ContentAction;
import com.netflix.falkor.CachedModelProxy;
import com.netflix.falkor.PQL;

public class UpdateExpiryAdvisoryStatusTask extends CmpTask
{
    private static final String EXPIRING_CONTENT_NOTICE = "expiringContentNotice";
    private PQL pql;
    
    public UpdateExpiryAdvisoryStatusTask(final CachedModelProxy cachedModelProxy, final String s, final ExpiringContentAdvisory$ContentAction expiringContentAdvisory$ContentAction) {
        super(cachedModelProxy, null);
        this.pql = PQL.create("expiringContentNotice", s, expiringContentAdvisory$ContentAction.name().toLowerCase(Locale.ENGLISH));
    }
    
    @Override
    protected void buildPqlList(final List<PQL> list) {
        list.add(this.pql);
    }
    
    @Override
    protected void callbackForFailure(final BrowseAgentCallback browseAgentCallback, final Status status) {
        if (Log.isLoggable()) {
            Log.w("CachedModelProxy", "onFailure, statusCode:" + status);
        }
    }
    
    @Override
    protected void customHandleResponse(final JsonObject jsonObject) {
        if (Falkor.ENABLE_VERBOSE_LOGGING) {
            Log.v("CachedModelProxy", "customHandleResponse for json: " + jsonObject);
        }
        if (FalkorParseUtils.isEmpty(jsonObject.getAsJsonObject("value"))) {
            throw new FalkorException("Empty value");
        }
    }
    
    @Override
    protected void fetchResultsAndCallbackForSuccess(final BrowseAgentCallback browseAgentCallback, final CachedModelProxy$GetResult cachedModelProxy$GetResult) {
    }
    
    @Override
    protected boolean shouldCustomHandleResponse() {
        return true;
    }
}
