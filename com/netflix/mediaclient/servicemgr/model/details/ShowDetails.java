// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr.model.details;

import com.netflix.mediaclient.servicemgr.model.FriendProfilesProvider;

public interface ShowDetails extends FriendProfilesProvider, VideoDetails
{
    String getCreators();
    
    String getCurrentEpisodeId();
    
    int getCurrentEpisodeNumber();
    
    String getCurrentEpisodeTitle();
    
    int getCurrentSeasonNumber();
    
    int getNumOfEpisodes();
    
    int getNumOfSeasons();
}
