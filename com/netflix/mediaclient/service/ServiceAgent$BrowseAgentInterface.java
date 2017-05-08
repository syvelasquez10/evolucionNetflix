// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service;

import com.netflix.mediaclient.servicemgr.interface_.Video;
import java.util.List;
import com.netflix.falkor.CachedModelProxy$CmpTaskDetails;
import com.netflix.mediaclient.ui.player.PostPlayRequestContext;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import com.netflix.mediaclient.service.browse.BrowseAgentCallback;

public interface ServiceAgent$BrowseAgentInterface
{
    void fetchBillboards(final int p0, final boolean p1, final BrowseAgentCallback p2);
    
    void fetchCW(final int p0, final boolean p1, final BrowseAgentCallback p2);
    
    void fetchCWFromNetwork(final int p0, final BrowseAgentCallback p1);
    
    void fetchEpisodeDetails(final String p0, final String p1, final BrowseAgentCallback p2);
    
    void fetchIQ(final int p0, final boolean p1, final BrowseAgentCallback p2);
    
    void fetchInteractiveVideoMoments(final VideoType p0, final String p1, final String p2, final int p3, final int p4, final BrowseAgentCallback p5);
    
    void fetchLoMos(final int p0, final int p1, final BrowseAgentCallback p2);
    
    void fetchMovieDetails(final String p0, final String p1, final BrowseAgentCallback p2);
    
    void fetchNonMemberVideos(final int p0, final boolean p1, final BrowseAgentCallback p2);
    
    void fetchPostPlayVideos(final String p0, final VideoType p1, final PostPlayRequestContext p2, final BrowseAgentCallback p3);
    
    void fetchRecommendedList(final int p0, final int p1, final boolean p2, final BrowseAgentCallback p3);
    
    void fetchShowDetails(final String p0, final String p1, final boolean p2, final BrowseAgentCallback p3);
    
    void fetchShowDetailsAndSeasons(final String p0, final String p1, final boolean p2, final boolean p3, final BrowseAgentCallback p4);
    
    void fetchTask(final CachedModelProxy$CmpTaskDetails p0, final BrowseAgentCallback p1);
    
    void fetchVideoSummary(final String p0, final BrowseAgentCallback p1);
    
    void logPostPlayImpression(final String p0, final VideoType p1, final String p2, final BrowseAgentCallback p3);
    
    void prefetchVideoListDetails(final List<? extends Video> p0, final BrowseAgentCallback p1);
    
    void updateOfflineGeoPlayability(final List<String> p0, final BrowseAgentCallback p1);
}
