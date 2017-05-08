// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr.interface_.details;

import com.netflix.mediaclient.servicemgr.interface_.Video;
import java.util.List;

public interface ShowDetails extends EvidenceDetails, VideoDetails
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
    
    List<Video> getSimilars();
    
    int getSimilarsListPos();
    
    String getSimilarsRequestId();
    
    int getSimilarsTrackId();
}
