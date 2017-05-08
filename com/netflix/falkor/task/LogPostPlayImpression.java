// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.falkor.task;

import com.android.volley.Request$Priority;
import java.util.ArrayList;
import com.netflix.mediaclient.util.DataUtil$StringPair;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.falkor.CachedModelProxy$GetResult;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.falkor.PQL;
import java.util.List;
import com.netflix.mediaclient.service.browse.BrowseAgentCallback;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import com.netflix.falkor.CachedModelProxy;

public class LogPostPlayImpression extends CmpTask
{
    private final String impressionData;
    private final String videoId;
    
    public LogPostPlayImpression(final CachedModelProxy cachedModelProxy, final String videoId, final VideoType videoType, final String impressionData, final BrowseAgentCallback browseAgentCallback) {
        super(cachedModelProxy, browseAgentCallback);
        this.videoId = videoId;
        this.impressionData = impressionData;
    }
    
    @Override
    protected void buildPqlList(final List<PQL> list) {
        list.add(PQL.create("videos", this.videoId, "postPlayImpression"));
    }
    
    @Override
    protected void callbackForFailure(final BrowseAgentCallback browseAgentCallback, final Status status) {
        browseAgentCallback.onPostPlayImpressionLogged(false, status);
    }
    
    @Override
    protected void fetchResultsAndCallbackForSuccess(final BrowseAgentCallback browseAgentCallback, final CachedModelProxy$GetResult cachedModelProxy$GetResult) {
        browseAgentCallback.onPostPlayImpressionLogged(true, CommonStatus.OK);
    }
    
    @Override
    protected List<DataUtil$StringPair> getOptionalRequestParams() {
        final ArrayList<DataUtil$StringPair> list = new ArrayList<DataUtil$StringPair>(1);
        list.add(new DataUtil$StringPair("impressionData", this.impressionData));
        return list;
    }
    
    @Override
    protected Request$Priority getPriorityOverride() {
        return Request$Priority.LOW;
    }
    
    @Override
    protected boolean shouldSkipCache() {
        return true;
    }
}
