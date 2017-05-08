// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.falkor.task;

import com.android.volley.Request$Priority;
import com.netflix.mediaclient.ui.kids.KidsUtils;
import com.netflix.mediaclient.ui.signup.OnRampActivity;
import java.util.ArrayList;
import com.netflix.mediaclient.util.DataUtil$StringPair;
import com.netflix.mediaclient.service.falkor.FalkorAgentStatus;
import com.netflix.mediaclient.StatusCode;
import com.netflix.falkor.CachedModelProxy$GetResult;
import com.netflix.mediaclient.android.app.Status;
import java.util.Map;
import com.netflix.mediaclient.service.configuration.persistent.LolomoPayloadABTestConfig;
import com.netflix.falkor.PQL;
import java.util.List;
import com.netflix.mediaclient.service.browse.BrowseAgentCallback;
import com.netflix.falkor.CachedModelProxy;

public class PrefetchLoLoMoTask extends CmpTask
{
    private final boolean includeExtraCharacters;
    private final boolean includeKubrick;
    private final boolean skipCache;
    private final int toBBVideo;
    private final int toCWVideo;
    private final int toLomo;
    private final int toVideo;
    
    public PrefetchLoLoMoTask(final CachedModelProxy cachedModelProxy, final int toLomo, final int toVideo, final int toCWVideo, final int toBBVideo, final boolean includeExtraCharacters, final boolean includeKubrick, final boolean skipCache, final BrowseAgentCallback browseAgentCallback) {
        super(cachedModelProxy, browseAgentCallback);
        this.toLomo = toLomo;
        this.toVideo = toVideo;
        this.toCWVideo = toCWVideo;
        this.toBBVideo = toBBVideo;
        this.includeExtraCharacters = includeExtraCharacters;
        this.includeKubrick = includeKubrick;
        this.skipCache = skipCache;
    }
    
    @Override
    protected void buildPqlList(final List<PQL> list) {
        final String summaryNodeKey = LolomoPayloadABTestConfig.getSummaryNodeKey(this.modelProxy.getContext());
        list.add(PQL.create("lolomo", PQL.range(this.toLomo), "summary"));
        final Map range = PQL.range(this.toLomo);
        final Map range2 = PQL.range(this.toVideo);
        Object array = summaryNodeKey;
        if (this.includeKubrick) {
            array = PQL.array(summaryNodeKey, "kubrick", "rating");
        }
        list.add(PQL.create("lolomo", range, range2, array));
        CmpUtils.buildCwPql(list, null, 0, this.toCWVideo);
        CmpUtils.buildBillboardPql(list, null, 0, this.toBBVideo);
        if (this.includeExtraCharacters) {
            final int n = this.toVideo + 1;
            list.add(PQL.create("lolomo", "characters", PQL.range(n, this.toVideo + n), "summary"));
        }
    }
    
    @Override
    protected void callbackForFailure(final BrowseAgentCallback browseAgentCallback, final Status status) {
        browseAgentCallback.onLoLoMoPrefetched(status);
    }
    
    @Override
    protected void fetchResultsAndCallbackForSuccess(final BrowseAgentCallback browseAgentCallback, final CachedModelProxy$GetResult cachedModelProxy$GetResult) {
        browseAgentCallback.onLoLoMoPrefetched(new FalkorAgentStatus(StatusCode.OK, this.isAllDataLocalToCache()));
        this.modelProxy.sendDetailPageReloadBroadcast();
    }
    
    @Override
    protected List<DataUtil$StringPair> getOptionalRequestParams() {
        final ArrayList<DataUtil$StringPair> list = new ArrayList<DataUtil$StringPair>();
        if (OnRampActivity.getOnRampLatch().getValueAndLatch()) {
            list.add(new DataUtil$StringPair("isFirstLolomoAfterOnRamp", Boolean.TRUE.toString()));
        }
        if (KidsUtils.isKidsParity(this.modelProxy.getContext())) {
            list.add(new DataUtil$StringPair("isKidsParity", Boolean.TRUE.toString()));
        }
        return list;
    }
    
    public Request$Priority getPriorityOverride() {
        return Request$Priority.IMMEDIATE;
    }
    
    @Override
    protected boolean shouldCollapseMissingPql(final List<PQL> list) {
        return list.size() > 25;
    }
    
    @Override
    protected boolean shouldSkipCache() {
        return this.skipCache;
    }
}
