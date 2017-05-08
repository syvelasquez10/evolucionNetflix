// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.falkor.task;

import java.util.ArrayList;
import com.netflix.mediaclient.util.DataUtil$StringPair;
import com.netflix.mediaclient.android.app.NetflixStatus;
import com.netflix.mediaclient.StatusCode;
import com.netflix.falkor.CachedModelProxy$GetResult;
import com.netflix.mediaclient.service.logging.error.ErrorLoggingManager;
import com.netflix.mediaclient.Log;
import com.google.gson.JsonObject;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.falkor.PQL;
import java.util.List;
import com.netflix.mediaclient.service.browse.BrowseAgentCallback;
import com.netflix.falkor.CachedModelProxy;

public class EndBrowsePlaySessionTask extends CmpTask
{
    private boolean bResult;
    private final int duration;
    private final int position;
    private final int videoId;
    private final long xId;
    
    public EndBrowsePlaySessionTask(final CachedModelProxy cachedModelProxy, final long xId, final int videoId, final int duration, final int position, final BrowseAgentCallback browseAgentCallback) {
        super(cachedModelProxy, browseAgentCallback);
        this.xId = xId;
        this.videoId = videoId;
        this.duration = duration;
        this.position = position;
    }
    
    @Override
    protected void buildPqlList(final List<PQL> list) {
        list.add(PQL.create("setBrowsePlayEnd", this.videoId));
    }
    
    @Override
    protected void callbackForFailure(final BrowseAgentCallback browseAgentCallback, final Status status) {
        browseAgentCallback.onBrowsePlaySessionEnd(false, status);
    }
    
    @Override
    protected void customHandleResponse(final JsonObject jsonObject) {
        this.bResult = false;
        try {
            this.bResult = jsonObject.getAsJsonObject("value").getAsJsonObject("browsePlayEnd").getAsJsonPrimitive(String.valueOf(this.videoId)).getAsBoolean();
            if (Log.isLoggable()) {
                Log.v("CachedModelProxy", "EndBrowsePlaySessionTask - customHandleResponse for json: " + jsonObject + "; got result: " + this.bResult);
            }
        }
        catch (Exception ex) {
            final String string = "SPY-8604 - EndBrowsePlaySessionTask got exception trying to parse JSON: " + ex;
            Log.w("CachedModelProxy", string);
            ErrorLoggingManager.logHandledException(string);
        }
    }
    
    @Override
    protected void fetchResultsAndCallbackForSuccess(final BrowseAgentCallback browseAgentCallback, final CachedModelProxy$GetResult cachedModelProxy$GetResult) {
        browseAgentCallback.onBrowsePlaySessionEnd(this.bResult, new NetflixStatus(StatusCode.OK));
    }
    
    @Override
    protected List<DataUtil$StringPair> getOptionalRequestParams() {
        final ArrayList<DataUtil$StringPair> list = new ArrayList<DataUtil$StringPair>(3);
        list.add(new DataUtil$StringPair("param", String.valueOf(this.xId)));
        list.add(new DataUtil$StringPair("param", String.valueOf(this.duration)));
        list.add(new DataUtil$StringPair("param", String.valueOf(this.position)));
        return list;
    }
    
    @Override
    protected boolean shouldCustomHandleResponse() {
        return true;
    }
    
    @Override
    protected boolean shouldUseCallMethod() {
        return true;
    }
}
