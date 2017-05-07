// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service;

import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import com.netflix.mediaclient.service.browse.BrowseAgentCallback;

public interface ServiceAgent$BrowseAgentInterface
{
    void fetchBillboardsFromCache(final int p0, final BrowseAgentCallback p1);
    
    void fetchCWFromCache(final int p0, final BrowseAgentCallback p1);
    
    void fetchEpisodeDetails(final String p0, final BrowseAgentCallback p1);
    
    void fetchIQFromCache(final int p0, final BrowseAgentCallback p1);
    
    void fetchMovieDetails(final String p0, final BrowseAgentCallback p1);
    
    void fetchPostPlayVideos(final String p0, final VideoType p1, final BrowseAgentCallback p2);
    
    void fetchRecommendedListFromCache(final int p0, final int p1, final BrowseAgentCallback p2);
    
    void fetchVideoSummary(final String p0, final BrowseAgentCallback p1);
}
