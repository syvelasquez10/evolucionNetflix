// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr;

public interface Playable extends Video
{
    int getEndtime();
    
    int getEpisodeNumber();
    
    boolean getFbDntShare();
    
    String getParentId();
    
    String getParentTitle();
    
    int getPlayableBookmarkPosition();
    
    String getPlayableId();
    
    long getPlayableServerBookmarkUpdateTime();
    
    String getPlayableTitle();
    
    int getRuntime();
    
    int getSeasonNumber();
    
    boolean isPlayableEpisode();
    
    boolean isUserConnectedToFacebook();
}
