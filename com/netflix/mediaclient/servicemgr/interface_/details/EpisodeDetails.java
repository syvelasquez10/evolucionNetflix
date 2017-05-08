// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr.interface_.details;

public interface EpisodeDetails extends PostPlayVideo
{
    boolean episodeIsNSRE();
    
    String getAvailabilityDateMessage();
    
    int getBookmarkPosition();
    
    String getEpisodeIdUrl();
    
    int getEpisodeNumber();
    
    String getInterestingSmallUrl();
    
    String getInterestingUrl();
    
    String getNextEpisodeId();
    
    String getNextEpisodeTitle();
    
    long getPlayableBookmarkUpdateTime();
    
    String getSeasonAbbrSeqLabel();
    
    String getSeasonId();
    
    int getSeasonNumber();
    
    String getShowId();
}
