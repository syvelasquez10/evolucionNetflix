// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr;

import java.util.List;

public interface CWVideo extends Playable
{
    long getBookmarkLastUpdateTime();
    
    int getBookmarkPosition();
    
    String getCurrentEpisodeId();
    
    int getCurrentEpisodeNumber();
    
    String getCurrentEpisodeTitle();
    
    int getCurrentSeasonNumber();
    
    int getEndtime();
    
    List<FriendProfile> getFacebookFriends();
    
    String getNextEpisodeId();
    
    int getRuntime();
    
    String getStillUrl();
}
