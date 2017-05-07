// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr.model.details;

import com.netflix.mediaclient.servicemgr.model.Video;
import java.util.List;
import com.netflix.mediaclient.servicemgr.model.FriendProfilesProvider;

public interface ShowDetails extends FriendProfilesProvider, EvidenceDetails, VideoDetails
{
    String getCreators();
    
    String getCurrentEpisodeId();
    
    int getCurrentEpisodeNumber();
    
    String getCurrentEpisodeTitle();
    
    int getCurrentSeasonNumber();
    
    int getNumOfEpisodes();
    
    int getNumOfSeasons();
    
    List<Video> getSimilars();
    
    int getSimilarsListPos();
    
    String getSimilarsRequestId();
    
    int getSimilarsTrackId();
}
