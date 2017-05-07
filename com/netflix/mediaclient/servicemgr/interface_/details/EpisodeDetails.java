// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr.interface_.details;

public interface EpisodeDetails extends PostPlayVideo
{
    String getAvailabilityDateMessage();
    
    int getBookmarkPosition();
    
    String getEpisodeIdUrl();
    
    int getEpisodeNumber();
    
    String getNextEpisodeId();
    
    String getNextEpisodeTitle();
    
    String getSeasonId();
    
    int getSeasonNumber();
    
    String getShowId();
}
