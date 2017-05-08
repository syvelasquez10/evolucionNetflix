// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.falkor.task;

import com.netflix.mediaclient.service.falkor.FalkorAgentStatus;
import com.netflix.mediaclient.StatusCode;
import com.netflix.model.branches.FalkorVideo;
import com.netflix.falkor.CachedModelProxy$GetResult;
import com.netflix.falkor.CachedModelProxy;
import com.netflix.mediaclient.servicemgr.interface_.details.MovieDetails;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.service.browse.BrowseAgentCallback;
import com.netflix.falkor.PQL;
import java.util.List;

public class FetchVideoVolatileNodesTask extends BaseCmpTask
{
    private final boolean isMovie;
    private final String rootBranchName;
    private final String videoId;
    
    public FetchVideoVolatileNodesTask(String s, final boolean isMovie) {
        this.videoId = s;
        this.isMovie = isMovie;
        if (isMovie) {
            s = "movies";
        }
        else {
            s = "shows";
        }
        this.rootBranchName = s;
    }
    
    @Override
    public void buildPqlList(final List<PQL> list) {
        CmpUtils.buildVolatileVideoPQLs(list, this.videoId, this.isMovie);
    }
    
    @Override
    public void callbackForFailure(final BrowseAgentCallback browseAgentCallback, final Status status) {
        browseAgentCallback.onMovieDetailsFetched(null, status);
    }
    
    @Override
    public void fetchResultsAndCallbackForSuccess(final CachedModelProxy cachedModelProxy, final BrowseAgentCallback browseAgentCallback, final CachedModelProxy$GetResult cachedModelProxy$GetResult) {
        final FalkorVideo falkorVideo = (FalkorVideo)cachedModelProxy.getVideo(PQL.create(this.rootBranchName, this.videoId));
        if (falkorVideo != null) {
            cachedModelProxy.updateBookmarkIfExists(this.videoId, falkorVideo.getBookmark());
            browseAgentCallback.onMovieDetailsFetched((MovieDetails)falkorVideo, new FalkorAgentStatus(StatusCode.OK, false));
        }
    }
    
    @Override
    public boolean shouldSkipCache() {
        return true;
    }
}
