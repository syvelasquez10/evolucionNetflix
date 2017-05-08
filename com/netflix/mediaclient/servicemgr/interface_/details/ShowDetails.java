// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr.interface_.details;

import java.util.List;
import java.util.Set;

public interface ShowDetails extends EvidenceDetails, Similarable, Trailerable, VideoDetails
{
    Set<String> getCharacterRoles();
    
    String getCreators();
    
    List<String> getCurrentEpisodeBadges();
    
    String getCurrentEpisodeHorzDispUrl();
    
    String getCurrentEpisodeId();
    
    int getCurrentEpisodeNumber();
    
    String getCurrentEpisodeStoryImgUrl();
    
    String getCurrentEpisodeSynopsis();
    
    String getCurrentEpisodeTitle();
    
    int getCurrentSeasonNumber();
    
    int getNumCreators();
    
    int getNumOfEpisodes();
    
    String getNumSeasonsLabel();
    
    int getSeasonCount();
}
