// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service;

import com.netflix.mediaclient.servicemgr.model.Video;
import com.netflix.mediaclient.service.browse.BrowseAgentCallback;
import com.netflix.mediaclient.servicemgr.model.CWVideo;
import com.netflix.mediaclient.servicemgr.model.Billboard;
import java.util.List;

public interface ServiceAgent$BrowseAgentInterface
{
    List<Billboard> fetchBillboardsFromCache(final int p0);
    
    List<CWVideo> fetchCWFromCache(final int p0);
    
    void fetchEpisodeDetails(final String p0, final BrowseAgentCallback p1);
    
    List<Video> fetchIQFromCache(final int p0);
    
    void fetchMovieDetails(final String p0, final BrowseAgentCallback p1);
    
    void fetchPostPlayVideos(final String p0, final BrowseAgentCallback p1);
    
    List<Video> fetchRecommendedListFromCache(final int p0);
    
    void fetchSeasonDetails(final String p0, final BrowseAgentCallback p1);
    
    void fetchShowDetails(final String p0, final String p1, final BrowseAgentCallback p2);
}
