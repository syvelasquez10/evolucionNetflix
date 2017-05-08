// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.falkor.task;

import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.falkor.CachedModelProxy$GetResult;
import com.netflix.mediaclient.android.app.Status;
import java.util.Map;
import com.netflix.mediaclient.service.configuration.persistent.LolomoPayloadABTestConfig;
import com.netflix.falkor.PQL;
import java.util.List;
import com.netflix.mediaclient.service.browse.BrowseAgentCallback;
import com.netflix.falkor.CachedModelProxy;

public class PrefetchGenreLoLoMoTask extends CmpTask
{
    private final String genreId;
    private final boolean includeKubrick;
    private final int toLomo;
    private final int toVideo;
    
    public PrefetchGenreLoLoMoTask(final CachedModelProxy cachedModelProxy, final String genreId, final int toLomo, final int toVideo, final boolean includeKubrick, final BrowseAgentCallback browseAgentCallback) {
        super(cachedModelProxy, browseAgentCallback);
        this.genreId = genreId;
        this.toLomo = toLomo;
        this.toVideo = toVideo;
        this.includeKubrick = includeKubrick;
    }
    
    @Override
    protected void buildPqlList(final List<PQL> list) {
        final String summaryNodeKey = LolomoPayloadABTestConfig.getSummaryNodeKey(this.modelProxy.getContext());
        list.add(PQL.create("topGenres", this.genreId, PQL.range(this.toLomo), "summary"));
        final String genreId = this.genreId;
        final Map range = PQL.range(this.toLomo);
        final Map range2 = PQL.range(this.toVideo);
        Object array = summaryNodeKey;
        if (this.includeKubrick) {
            array = PQL.array(summaryNodeKey, "kubrick", "rating");
        }
        list.add(PQL.create("topGenres", genreId, range, range2, array));
    }
    
    @Override
    protected void callbackForFailure(final BrowseAgentCallback browseAgentCallback, final Status status) {
        browseAgentCallback.onGenreLoLoMoPrefetched(status);
    }
    
    @Override
    protected void fetchResultsAndCallbackForSuccess(final BrowseAgentCallback browseAgentCallback, final CachedModelProxy$GetResult cachedModelProxy$GetResult) {
        browseAgentCallback.onGenreLoLoMoPrefetched(CommonStatus.OK);
    }
}
