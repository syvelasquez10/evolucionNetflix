// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.falkor.task;

import java.util.Iterator;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.servicemgr.interface_.details.MovieDetails;
import com.netflix.mediaclient.servicemgr.interface_.details.ShowDetails;
import java.util.ArrayList;
import com.netflix.falkor.CachedModelProxy$GetResult;
import com.netflix.mediaclient.servicemgr.interface_.Video;
import com.netflix.model.branches.FalkorPerson;
import com.netflix.mediaclient.android.app.Status;
import java.util.List;
import com.netflix.mediaclient.service.browse.BrowseAgentCallback;
import com.netflix.falkor.CachedModelProxy;
import com.netflix.falkor.PQL;

public class FetchPersonRelated extends CmpTask
{
    private final String actorId;
    PQL detailPQL;
    int maxItems;
    PQL relatedPQL;
    
    public FetchPersonRelated(final CachedModelProxy cachedModelProxy, final String actorId, final BrowseAgentCallback browseAgentCallback) {
        super(cachedModelProxy, browseAgentCallback);
        this.maxItems = 7;
        this.actorId = actorId;
    }
    
    @Override
    protected void buildPqlList(final List<PQL> list) {
        this.detailPQL = PQL.create("people", this.actorId, "detail");
        list.add(this.relatedPQL = PQL.create("people", this.actorId, "videoListForPerson", PQL.range(0, this.maxItems - 1), PQL.array("summary", "detail")));
    }
    
    @Override
    protected void callbackForFailure(final BrowseAgentCallback browseAgentCallback, final Status status) {
        browseAgentCallback.onPersonRelatedFetched(null, null, status);
    }
    
    @Override
    protected void fetchResultsAndCallbackForSuccess(final BrowseAgentCallback browseAgentCallback, final CachedModelProxy$GetResult cachedModelProxy$GetResult) {
        final FalkorPerson falkorPerson = (FalkorPerson)this.modelProxy.getValue(this.detailPQL);
        final ArrayList<ShowDetails> list = new ArrayList<ShowDetails>();
        for (final String s : falkorPerson.videos.videoIds) {
            final ShowDetails showDetails = (ShowDetails)this.modelProxy.getVideo(PQL.create("shows", s));
            if (showDetails != null) {
                list.add(showDetails);
            }
            final MovieDetails movieDetails = (MovieDetails)this.modelProxy.getVideo(PQL.create("movies", s));
            if (movieDetails != null) {
                list.add((ShowDetails)movieDetails);
            }
        }
        browseAgentCallback.onPersonRelatedFetched(falkorPerson, (List<Video>)list, CommonStatus.OK);
    }
    
    @Override
    protected boolean shouldSkipCache() {
        return true;
    }
}
