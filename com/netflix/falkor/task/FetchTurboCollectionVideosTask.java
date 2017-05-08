// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.falkor.task;

import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.falkor.CachedModelProxy$GetResult;
import com.netflix.falkor.CachedModelProxy;
import com.netflix.mediaclient.servicemgr.interface_.Video;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.service.browse.BrowseAgentCallback;
import java.util.List;
import com.netflix.falkor.PQL;

public class FetchTurboCollectionVideosTask extends BaseCmpTask
{
    private final int fromVideo;
    private final PQL pql;
    private final int toVideo;
    private final long turboCollectionId;
    
    public FetchTurboCollectionVideosTask(final long turboCollectionId, final int fromVideo, final int toVideo) {
        this.fromVideo = fromVideo;
        this.toVideo = toVideo;
        this.turboCollectionId = turboCollectionId;
        this.pql = PQL.create("turboCollection", turboCollectionId, PQL.range(fromVideo, toVideo), PQL.array("summary", "detail"));
    }
    
    @Override
    public void buildPqlList(final List<PQL> list) {
        list.add(this.pql);
        list.add(CmpUtils.CW_VIDEO_LEAF_PQL.prepend(PQL.create("turboCollection", this.turboCollectionId, PQL.range(this.fromVideo, this.toVideo))));
        list.add(CmpUtils.CW_CURR_EPISODE_PQL.prepend(PQL.create("turboCollection", this.turboCollectionId, PQL.range(this.fromVideo, this.toVideo))));
    }
    
    @Override
    public void callbackForFailure(final BrowseAgentCallback browseAgentCallback, final Status status) {
        browseAgentCallback.onVideosFetched(null, status);
    }
    
    @Override
    public void fetchResultsAndCallbackForSuccess(final CachedModelProxy cachedModelProxy, final BrowseAgentCallback browseAgentCallback, final CachedModelProxy$GetResult cachedModelProxy$GetResult) {
        browseAgentCallback.onVideosFetched(cachedModelProxy.getItemsAsList(this.pql), CommonStatus.OK);
    }
}
