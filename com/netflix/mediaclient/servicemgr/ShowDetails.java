// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr;

public interface ShowDetails extends VideoDetails
{
    String getCurrentEpisodeId();
    
    int getCurrentEpisodeNumber();
    
    String getCurrentEpisodeTitle();
    
    int getCurrentSeasonNumber();
    
    int getNumOfEpisodes();
    
    int getNumOfSeasons();
    
    boolean isShared();
}
