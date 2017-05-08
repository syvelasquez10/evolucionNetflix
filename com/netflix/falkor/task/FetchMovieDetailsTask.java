// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.falkor.task;

import com.netflix.mediaclient.service.falkor.FalkorAgentStatus;
import com.netflix.mediaclient.StatusCode;
import com.netflix.model.branches.FalkorVideo;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.Log;
import com.netflix.falkor.CachedModelProxy$GetResult;
import com.netflix.mediaclient.servicemgr.interface_.details.MovieDetails;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import com.netflix.mediaclient.util.StringUtils;
import java.util.Collections;
import com.netflix.mediaclient.ui.details.DPPrefetchABTestUtils;
import com.netflix.falkor.PQL;
import java.util.List;
import com.netflix.mediaclient.service.browse.BrowseAgentCallback;
import com.netflix.falkor.CachedModelProxy;

public class FetchMovieDetailsTask extends CmpTask
{
    private final String movieId;
    private final String scene;
    
    public FetchMovieDetailsTask(final CachedModelProxy cachedModelProxy, final String movieId, final String scene, final BrowseAgentCallback browseAgentCallback) {
        super(cachedModelProxy, browseAgentCallback);
        this.movieId = movieId;
        this.scene = scene;
    }
    
    @Override
    protected void buildPqlList(final List<PQL> list) {
        DPPrefetchABTestUtils.latchToPendingRequestsIfExists(this.movieId);
        CmpUtils.buildMovieDetailsPQLs(list, Collections.singletonList(this.movieId));
        if (StringUtils.isNotEmpty(this.scene)) {
            list.add(CmpUtils.buildScenePql(VideoType.MOVIE.getValue(), this.movieId, this.scene));
        }
    }
    
    @Override
    protected void callbackForFailure(final BrowseAgentCallback browseAgentCallback, final Status status) {
        browseAgentCallback.onMovieDetailsFetched(null, status);
    }
    
    @Override
    protected void fetchResultsAndCallbackForSuccess(final BrowseAgentCallback browseAgentCallback, final CachedModelProxy$GetResult cachedModelProxy$GetResult) {
        final MovieDetails movieDetails = (MovieDetails)this.modelProxy.getVideo(PQL.create("movies", this.movieId));
        if (movieDetails == null || StringUtils.isEmpty(movieDetails.getId())) {
            final StringBuilder append = new StringBuilder().append("SPY-7519: FetchMovieDetailsTask got weird movie id: ");
            String id;
            if (movieDetails == null) {
                id = "null";
            }
            else {
                id = movieDetails.getId();
            }
            final String string = append.append(id).toString();
            this.modelProxy.logHandledException(string);
            Log.e("CachedModelProxy", string);
            browseAgentCallback.onMovieDetailsFetched(null, CommonStatus.INT_ERR_CMP_RESP_NULL);
            return;
        }
        if (movieDetails instanceof FalkorVideo) {
            this.modelProxy.updateBookmarkIfExists(this.movieId, ((FalkorVideo)movieDetails).getBookmark());
        }
        browseAgentCallback.onMovieDetailsFetched(movieDetails, new FalkorAgentStatus(StatusCode.OK, this.isAllDataLocalToCache()));
    }
}
