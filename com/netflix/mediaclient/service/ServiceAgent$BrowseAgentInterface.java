// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service;

import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import com.netflix.mediaclient.service.browse.BrowseAgentCallback;

public interface ServiceAgent$BrowseAgentInterface
{
    void fetchBillboards(final int p0, final boolean p1, final BrowseAgentCallback p2);
    
    void fetchCW(final int p0, final boolean p1, final BrowseAgentCallback p2);
    
    void fetchEpisodeDetails(final String p0, final BrowseAgentCallback p1);
    
    void fetchIQ(final int p0, final boolean p1, final BrowseAgentCallback p2);
    
    void fetchLoMos(final int p0, final int p1, final BrowseAgentCallback p2);
    
    void fetchMovieDetails(final String p0, final BrowseAgentCallback p1);
    
    void fetchNonMemberVideos(final int p0, final boolean p1, final BrowseAgentCallback p2);
    
    void fetchPostPlayVideos(final String p0, final VideoType p1, final BrowseAgentCallback p2);
    
    void fetchRecommendedList(final int p0, final int p1, final boolean p2, final BrowseAgentCallback p3);
    
    void fetchVideoSummary(final String p0, final BrowseAgentCallback p1);
}
