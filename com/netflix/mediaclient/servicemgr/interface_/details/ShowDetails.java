// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr.interface_.details;

import java.util.List;

public interface ShowDetails extends EvidenceDetails, Similarable, VideoDetails
{
    String getCreators();
    
    List<String> getCurrentEpisodeBadges();
    
    String getCurrentEpisodeHorzDispUrl();
    
    String getCurrentEpisodeId();
    
    int getCurrentEpisodeNumber();
    
    String getCurrentEpisodeStoryImgUrl();
    
    String getCurrentEpisodeSynopsis();
    
    String getCurrentEpisodeTitle();
    
    int getCurrentSeasonNumber();
    
    int getNumOfEpisodes();
    
    int getNumOfSeasons();
    
    String getNumSeasonsLabel();
}
