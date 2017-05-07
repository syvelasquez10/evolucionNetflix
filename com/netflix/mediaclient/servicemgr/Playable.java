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
    
    long getPlayableBookmarkUpdateTime();
    
    String getPlayableId();
    
    String getPlayableTitle();
    
    int getRuntime();
    
    int getSeasonNumber();
    
    boolean isAutoPlayEnabled();
    
    boolean isNextPlayableEpisode();
    
    boolean isPinProtected();
    
    boolean isPlayableEpisode();
    
    boolean isUserConnectedToFacebook();
}
