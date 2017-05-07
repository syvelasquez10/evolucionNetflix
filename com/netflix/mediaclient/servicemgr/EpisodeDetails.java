// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr;

public interface EpisodeDetails extends VideoDetails
{
    int getBookmarkPosition();
    
    String getEpisodeIdUrl();
    
    int getEpisodeNumber();
    
    String getNextEpisodeId();
    
    String getNextEpisodeTitle();
    
    String getSeasonId();
    
    int getSeasonNumber();
    
    String getShowId();
}
