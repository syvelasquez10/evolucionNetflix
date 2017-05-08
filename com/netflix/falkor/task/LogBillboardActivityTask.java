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
import org.json.JSONObject;
import com.netflix.falkor.PQL;
import java.util.List;
import com.netflix.mediaclient.service.browse.BrowseAgentCallback;
import com.netflix.falkor.CachedModelProxy;
import com.netflix.mediaclient.servicemgr.interface_.Video;
import com.netflix.mediaclient.servicemgr.BillboardInteractionType;
import java.util.Map;

public class LogBillboardActivityTask extends CmpTask
{
    private final Map<String, String> params;
    private final BillboardInteractionType type;
    private final Video video;
    
    public LogBillboardActivityTask(final CachedModelProxy cachedModelProxy, final Video video, final BillboardInteractionType type, final Map<String, String> params) {
        super(cachedModelProxy, null);
        this.video = video;
        this.type = type;
        this.params = params;
    }
    
    @Override
    protected void buildPqlList(final List<PQL> list) {
        String string = "{}";
        if (this.params != null) {
            string = new JSONObject((Map)this.params).toString();
        }
        list.add(PQL.create("logBillboardActivity", this.video.getId(), this.type.getName(), string));
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
