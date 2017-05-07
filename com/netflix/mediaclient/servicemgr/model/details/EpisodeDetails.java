// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr.model.details;

public interface EpisodeDetails extends InterestingVideoDetails
{
    int getBookmarkPosition();
    
    String getEpisodeIdUrl();
    
    int getEpisodeNumber();
    
    String getInterestingUrl();
    
    String getNextEpisodeId();
    
    String getNextEpisodeTitle();
    
    String getSeasonId();
    
    int getSeasonNumber();
    
    String getShowId();
}
